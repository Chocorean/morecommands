package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import com.chocorean.morecommands.exception.PlayerNotFoundException;
import com.chocorean.morecommands.misc.TpHandler;
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
        return MoreCommands.getConfig().getTpaUsage();
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
            if (args[0].equals("no")){
                // TODO
            } else if (args[0].equals("yes")){
                // TODO
            } else if (args[0].equals(sender.getName())){ // un boloss essaie de se tp a soi meme
                throw new CommandException("You can't ask yourself to teleport to you.");
            } else { // on essaie de se tp
                EntityPlayerMP src = (EntityPlayerMP)sender;
                EntityPlayerMP dest = (EntityPlayerMP) sender.getEntityWorld().getPlayerEntityByName(args[0]);
                if (dest != null) {
                    // le joueur existe
                    handler.addTpa(dest, src);
                    dest.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(
                            MoreCommands.getConfig().getOnTpaRequestDestMessage(),
                            src.getName()))));
                    src.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(
                            MoreCommands.getConfig().getOnTpaRequestSrcMessage(),
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
