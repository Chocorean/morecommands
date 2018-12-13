package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.text.TextComponentString;

public class VanishCommand extends CommandBase {
    @Override
    public String getName() {
        return "vanish";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getVanishUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        EntityPlayerMP player = ((EntityPlayerMP)sender);
        if (player.isInvisible()) {
            player.connection.sendPacket(new SPacketChat(new TextComponentString("You're now visible.")));
            player.setInvisible(false);
        } else {
            player.connection.sendPacket(new SPacketChat(new TextComponentString("You're now invisible.")));
            player.setInvisible(true);
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        UserListOps ops = server.getPlayerList().getOppedPlayers();
        GameProfile gp = ops.getGameProfileFromName(sender.getName());
        try {
            gp.getName(); // can trigger NPE
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
