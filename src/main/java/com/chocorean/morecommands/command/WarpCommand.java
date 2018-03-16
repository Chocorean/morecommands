package com.chocorean.morecommands.command;

import com.chocorean.morecommands.misc.PosPlayer;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WarpCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "warp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/warp <warp>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Invalid number of arguments.")));
            return;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("mods/MoreCommands/warp"));
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(args[0])) {
                    // updating last position for back
                    for (PosPlayer pp : BackCommand.players) {
                        if (pp.player.getName().equals(sender.getName())){
                            pp.position = sender.getPosition();
                            break;
                        }
                    }
                    String[] infos = line.split(" ");
                    ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Warping to "+infos[0]+"...")));
                    if (((EntityPlayerMP) sender).dimension != Integer.parseInt(infos[4]))
                        ((EntityPlayerMP) sender).changeDimension(Integer.parseInt(infos[4]));
                    ((EntityPlayer)sender).setPositionAndUpdate(Double.parseDouble(infos[1]), Double.parseDouble(infos[2]), Double.parseDouble(infos[3]));
                    break;
                }
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
