package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import chocorean.morecommands.exception.WarpNotFoundException;
import chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import chocorean.morecommands.storage.StorageModule;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.text.TextComponentString;

import java.sql.SQLException;
import java.util.Map;

public class DelWarpCommand extends CommandBase {
    private final StorageModule storage;
    private Map<String, String> localization = MoreCommands.localization;

    public DelWarpCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "delwarp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return localization.get("commands.morecommands.delwarp.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new InvalidNumberOfArgumentsException();
        }
        try {
            if (!this.storage.listWarps().contains(args[0])) {
                throw new WarpNotFoundException(
                        String.format(localization.get("commands.morecommands.delwarp.error"), args[0]));
            } else {
                this.storage.deleteWarp(args[0]);
                sender.sendMessage(new TextComponentString(
                        String.format(localization.get("commands.morecommands.delwarp.success"), args[0])));
                //((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString(String.format("command.morecommands.back.success", args[0]))));
            }
        } catch (SQLException e) {
            throw new WarpNotFoundException(args[0]);
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
