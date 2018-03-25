package com.chocorean.morecommands.command;

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

public class WarpsCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "warps";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/warps - Displays a list of available warps";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("config/MoreCommands/warp"));
            String line = reader.readLine();
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("List of available warps :")));

            while(line != null){
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString(line.split(" ")[0])));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
