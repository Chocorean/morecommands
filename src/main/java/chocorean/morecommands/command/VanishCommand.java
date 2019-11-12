package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;

import java.util.Map;

public class VanishCommand extends CommandBase {
    private Map<String, String> localization = MoreCommands.localization;

    @Override
    public String getName() {
        return "vanish";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return localization.get("commands.morecommands.vanish.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        EntityPlayerMP player = ((EntityPlayerMP)sender);
        if (player.isInvisible()) {
            player.setInvisible(false);
            localization.get("commands.morecommands.vanish.visible");
        } else {
            player.setInvisible(true);
            localization.get("commands.morecommands.vanish.invisible");
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
