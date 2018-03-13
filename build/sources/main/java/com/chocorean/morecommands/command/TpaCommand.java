package com.chocorean.morecommands.command;

import com.chocorean.morecommands.misc.PosPlayer;
import com.chocorean.morecommands.misc.TpaHereRequest;
import com.chocorean.morecommands.misc.TpaRequest;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpaCommand extends AbstractCommand {
    public static ArrayList<TpaRequest> requests;

    public TpaCommand() {
        super();
        requests = new ArrayList<TpaRequest>();
    }
    @Override
    public String getCommandName() {
        return "tpa";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tpa <targetPlayer> - Ask the target if you can teleport to him.";
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(server.getPlayerList().getAllUsernames()));
        list.remove(sender.getName());
        return list;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length >= 2){
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Invalid number of arguments.")));
            return;
        }
        if (args.length == 1) {
            if (args[0].equals("no")){ // refus de se tp a qqun
                for (TpaHereRequest tr : TpaHereCommand.requests) {
                    if (tr.target.getName().equals(sender.getName())){
                        ((EntityPlayerMP)tr.dest).connection.sendPacket(new SPacketChat(new TextComponentString(tr.target.getName()+" refused your tpahere request.")));
                        ((EntityPlayerMP)tr.target).connection.sendPacket(new SPacketChat(new TextComponentString("Tpa request refused.")));
                        TpaHereCommand.requests.remove(tr);
                        break;
                    }
                }
            } else if (args[0].equals("yes")){ // ok pour se tp a qqun
                for (TpaHereRequest tr : TpaHereCommand.requests) {
                    if (tr.target.getName().equals(sender.getName())) {
                        ((EntityPlayerMP)tr.dest).connection.sendPacket(new SPacketChat(new TextComponentString(tr.target.getName()+" accepted your tpahere request.")));
                        ((EntityPlayerMP)tr.target).connection.sendPacket(new SPacketChat(new TextComponentString("Tpa request accepted.")));
                        ((EntityPlayerMP)sender).setPositionAndUpdate(((EntityPlayerMP) tr.dest).posX, ((EntityPlayerMP) tr.dest).posY, ((EntityPlayerMP) tr.dest).posZ);
                        TpaHereCommand.requests.remove(tr);
                        break;
                    }
                }
            } else if (args[0].equals(sender.getName())){ // un boloss essaie de se tp a soi meme
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("You can't ask yourself to teleport to you.")));
                return;
            } else { // on essaie de se tp
                if (sender.getEntityWorld().playerEntities.contains(sender.getEntityWorld().getPlayerEntityByName(args[0]))) {
                    // le joueur existe
                    requests.add(new TpaRequest((EntityPlayer)sender, args[0]));
                } else {
                    // le joueur n'existe pas
                    ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Invalid argument.")));
                    return;
                }
            }
        }
    }
}
