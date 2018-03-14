package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.io.*;

public class SetwarpCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "setwarp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/setwarp <warp>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Invalid number of arguments.")));
        }
        int dim = ((EntityPlayerMP)sender).dimension;
        BlockPos pos = sender.getPosition();

        BufferedReader reader;
        PrintWriter writer;
        try {
            reader = new BufferedReader(new FileReader("mods/MoreCommands/warp"));
            writer = new PrintWriter(new FileWriter("mods/MoreCommands/warp.tmp"));
            String line = reader.readLine();
            int i=0;
            boolean hasBeenReplaced=false;
            while (line != null) {
                if (line.contains(args[0])) {
                    // if registered warp, replacing older location by the newer
                    line = args[0] +" "+pos.getX()+" "+pos.getY()+" "+pos.getZ() + " " + dim + "\n";
                    hasBeenReplaced=true;
                }
                writer.write(line);
                line = reader.readLine();
            }
            if (!hasBeenReplaced){
                writer.write(args[0] +" "+pos.getX()+" "+pos.getY()+" "+pos.getZ() + " " + dim + "\n");
            }
            reader.close();
            writer.close();

            // now remove older and rename newer
            if (!new File("mods/MoreCommands/warp").delete()) {
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Internal error. Please contact an administrator.")));
                return;
            }
            if (!new File("mods/MoreCommands/warp.tmp").renameTo(new File("mods/MoreCommands/warp"))){
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Internal error. Please contact an administrator.")));
                return;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Warp "+args[0]+ " has been set.")));
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }
}
