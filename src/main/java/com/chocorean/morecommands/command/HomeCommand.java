package com.chocorean.morecommands.command;

import com.chocorean.morecommands.misc.PosPlayer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "home";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/home - Teleport to your home";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("mods/MoreCommands/home"));
            String line=reader.readLine();
            while (line != null) {
                if (line.contains(sender.getName())) {
                    // updating last position for back
                    for (PosPlayer pp : BackCommand.players) {
                        if (pp.player.getName().equals(sender.getName())){
                            pp.position = sender.getPosition();
                        }
                    }
                    String[] infos = line.split(" ");
                    ((EntityPlayerMP)sender).setPositionAndUpdate(Double.parseDouble(infos[1]), Double.parseDouble(infos[2]), Double.parseDouble(infos[3]));
                    ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Teleported to home.")));
                    return;
                }
            }
            // if we come here, player has no home
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("You are homeless.")));
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
