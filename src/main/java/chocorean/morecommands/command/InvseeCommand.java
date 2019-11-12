package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvseeCommand extends CommandBase {
    @Override
    public String getName() {
        return "invsee";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.localization.get("commands.morecommands.invsee.usage");
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(server.getPlayerList().getOnlinePlayerNames()));
        list.remove(sender.getName());
        return list;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new InvalidNumberOfArgumentsException();
        }
        MoreCommands.LOGGER.info("[MoreCommands] DEBUG: /invsee");
        EntityPlayerMP target = server.getPlayerList().getPlayerByUsername(args[0]);
        MoreCommands.LOGGER.info("[MoreCommands] DEBUG: targer == null: " + (target == null));
        if (target == null) return;
        IInventory targetInventory = target.inventory;
        MoreCommands.LOGGER.info("[MoreCommands] DEBUG: inventory.isEmpty: " + targetInventory.isEmpty());
        ((EntityPlayerMP)sender).displayGUIChest(targetInventory);
        MoreCommands.LOGGER.info("[MoreCommands] DEBUG: displayed inventory");
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
