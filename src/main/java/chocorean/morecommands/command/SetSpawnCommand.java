package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.exception.SetspawnInvalidDimensionException;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.text.TextComponentString;

import java.util.Map;

public class SetSpawnCommand extends CommandBase {
    private Map<String, String> localization = MoreCommands.localization;

    @Override
    public String getName() {
        return "setspawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return localization.get("commands.morecommands.setspawn.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (((EntityPlayerMP)sender).dimension != 0)
            throw new SetspawnInvalidDimensionException();
        if (!sender.getEntityWorld().isRemote) {
            sender.getEntityWorld().setSpawnPoint(sender.getPosition());
            sender.sendMessage(new TextComponentString(localization.get("commands.morecommands.setspawn.success")));
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
