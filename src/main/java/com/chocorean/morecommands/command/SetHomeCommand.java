package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SetHomeCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "sethome";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/sethome - Define your home";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        BlockPos pos = sender.getPosition();

        BufferedReader reader;
        PrintWriter writer;

        try {
            reader = new BufferedReader(new FileReader("mods/MoreCommands/home"));
            writer = new PrintWriter(new FileWriter("mods/MoreCommands/home.tmp"));
            String line = reader.readLine();
            int i=0;
            boolean hasBeenReplaced=false;
            while (line != null) {
                if (line.contains(sender.getName())) {
                    // if registered home, replacing older location by the newer
                    line = sender.getName() +" "+pos.getX()+" "+pos.getY()+" "+pos.getZ();
                    hasBeenReplaced=true;
                }
                writer.write(line);
                line = reader.readLine();
            }
            if (!hasBeenReplaced){
                writer.write(sender.getName() +" "+pos.getX()+" "+pos.getY()+" "+pos.getZ());
            }
            reader.close();
            writer.close();

            // now remove older and rename newer
            if (!new File("mods/MoreCommands/home").delete()) {
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Internal error. Please contact an administrator.")));
                return;
            }
            if (!new File("mods/MoreCommands/home.tmp").renameTo(new File("mods/MoreCommands/home"))){
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Internal error. Please contact an administrator.")));
                return;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Home has been set.")));
    }
}
