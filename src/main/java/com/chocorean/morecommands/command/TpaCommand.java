package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import com.chocorean.morecommands.exception.PlayerNotFoundException;
import com.chocorean.morecommands.misc.TpHandler;
import com.chocorean.morecommands.model.PlayerPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TpaCommand extends CommandBase {
    private TpHandler handler = MoreCommands.handler;

    @Override
    public String getName() {
        return "tpa";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getUsageConfig().getTpaUsage();
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(server.getPlayerList().getOnlinePlayerNames()));
        list.remove(sender.getName());
        return list;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1){
            throw new InvalidNumberOfArgumentsException();
        } else {
            EntityPlayerMP p = (EntityPlayerMP)sender;
            if (args[0].equals("no")){
                try {
                    this.handler.getDestForTpah(p.getName()).connection.sendPacket(new SPacketChat(new TextComponentString(String.format(MoreCommands.getConfig().getMessageConfig().getOnTpDenyMessage(),p.getName()))));
                    // suppression
                    this.handler.rmTpah(p.getName());
                } catch (NullPointerException e) {
                    p.connection.sendPacket(new SPacketChat(new TextComponentString("There is no /tpa request to anwser to.")));
                }
            } else if (args[0].equals("yes")){
                try {
                    EntityPlayerMP dest = this.handler.getDestForTpah(p.getName());
                    BlockPos pos = dest.getPosition();
                    PlayerPos pp = new PlayerPos(p);
                    BackCommand.backList.put(p.getName(), pp);
                    if (p.dimension != dest.dimension) p.changeDimension(dest.dimension);
                    p.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), p.rotationYaw, p.rotationPitch);
                    this.handler.rmTpah(p.getName());
                } catch (NullPointerException e) {
                    p.connection.sendPacket(new SPacketChat(new TextComponentString("There is no /tpa request to anwser to.")));
                }
            } else if (args[0].equals(sender.getName())){ // un boloss essaie de se tp a soi meme
                throw new CommandException("You can't ask yourself to teleport to you.");
            } else { // on essaie de se tp
                EntityPlayerMP src = (EntityPlayerMP)sender;
                EntityPlayerMP dest = src.getServer().getPlayerList().getPlayerByUsername(args[0]);
                if (dest != null) {
                    // le joueur existe
                    handler.addTpa(dest.getName(), src);
                    dest.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(
                            MoreCommands.getConfig().getMessageConfig().getOnTpaRequestDestMessage(),
                            src.getName()))));
                    src.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(
                            MoreCommands.getConfig().getMessageConfig().getOnTpaRequestSrcMessage(),
                            dest.getName()))));
                } else {
                    throw new PlayerNotFoundException(args[0]);
                }
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
