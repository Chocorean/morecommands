package com.chocorean.morecommands.command;

import com.chocorean.morecommands.misc.PosPlayer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;

public class BackCommand extends AbstractCommand {
    public static ArrayList<PosPlayer> players;
    public BackCommand() {
        super();
        players = new ArrayList<PosPlayer>();
    }

    @Override
    public String getCommandName() {
        return "back";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/back";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        for (PosPlayer pp : players) {
            if (pp.player.getName().equals(sender.getName())){
                ((EntityPlayerMP)sender).addChatComponentMessage(new TextComponentString("Back !"));
                    // tp after saving pos
                BlockPos position = sender.getPosition();
                ((EntityPlayerMP)sender).connection.setPlayerLocation(pp.position.getX(),pp.position.getY(),pp.position.getZ(),0,0);
                pp.position = position;
            }
        }
    }
}
