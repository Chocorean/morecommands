package com.chocorean.morecommands.command;

import com.chocorean.morecommands.misc.PosPlayer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        if (((EntityPlayerMP)sender).dimension != 0) {
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("You must be in the overworld to teleport to your home.")));
            return;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("mods/MoreCommands/home"));
            String line=reader.readLine();
            while (line != null) {
                if (line.split(" ")[0].equals(sender.getName())) {
                    reader.close();
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
