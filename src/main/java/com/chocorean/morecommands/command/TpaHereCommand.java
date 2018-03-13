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

public class TpaHereCommand extends AbstractCommand {
    public static ArrayList<TpaHereRequest> requests;

    public TpaHereCommand() {
        super();
        requests = new ArrayList<TpaHereRequest>();
    }
    @Override
    public String getCommandName() {
        return "tpahere";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tpahere <targetPlayer> - Ask the target if he/she wants to teleport to you.";
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
            if (args[0].equals("no")){ // refus de tp qqun
                for (TpaRequest tr : TpaCommand.requests) {
                    if (tr.dest.getName().equals(sender.getName())){
                        ((EntityPlayerMP)tr.dest).connection.sendPacket(new SPacketChat(new TextComponentString("Tpahere request refused.")));
                        ((EntityPlayerMP)tr.target).connection.sendPacket(new SPacketChat(new TextComponentString(tr.target.getName()+" refused your tpa request.")));
                        TpaCommand.requests.remove(tr);
                        break;
                    }
                }
            } else if (args[0].equals("yes")){ // ok pour tp qqun
                for (TpaRequest tr : TpaCommand.requests) {
                    if (tr.dest.getName().equals(sender.getName())) {
                        // updating last position for back
                        for (PosPlayer pp : BackCommand.players) {
                            if (pp.player.getName().equals(tr.target.getName())){
                                pp.position = sender.getPosition();
                            }
                        }
                        ((EntityPlayerMP)tr.dest).connection.sendPacket(new SPacketChat(new TextComponentString("Tpahere request accepted.")));
                        ((EntityPlayerMP)tr.target).connection.sendPacket(new SPacketChat(new TextComponentString(tr.dest +" accepted your request.")));
                        ((EntityPlayerMP)tr.target).setPositionAndUpdate(((EntityPlayerMP) tr.dest).posX, ((EntityPlayerMP) tr.dest).posY, ((EntityPlayerMP) tr.dest).posZ);
                        TpaHereCommand.requests.remove(tr);
                        break;
                    }
                }
            } else if (args[0].equals(sender.getName())){ // un boloss essaie de se tp a soi meme
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("You can't ask yourself to teleport to you.")));
                return;
            } else { // on essaie de tp
                if (sender.getEntityWorld().playerEntities.contains(sender.getEntityWorld().getPlayerEntityByName(args[0]))) {
                    // le joueur existe
                    requests.add(new TpaHereRequest(args[0], (EntityPlayer)sender));
                } else {
                    // le joueur n'existe pas
                    ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Invalid argument.")));
                    return;
                }
            }
        }
    }
}
