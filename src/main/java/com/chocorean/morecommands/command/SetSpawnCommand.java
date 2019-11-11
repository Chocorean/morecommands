package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.SetspawnInvalidDimensionException;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.text.TextComponentString;

public class SetSpawnCommand extends CommandBase {
    @Override
    public String getName() {
        return "setspawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.morecommands.setspawn.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (((EntityPlayerMP)sender).dimension != 0)
            throw new SetspawnInvalidDimensionException();
        if (!sender.getEntityWorld().isRemote) {
            sender.getEntityWorld().setSpawnPoint(sender.getPosition());
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString("Spawnpoint has been changed.")));
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
