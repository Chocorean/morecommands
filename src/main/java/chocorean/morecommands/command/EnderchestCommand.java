package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;

import java.util.ArrayList;
import java.util.List;

public class EnderchestCommand extends CommandBase {

    @Override
    public String getName() {
        return "enderchest";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("ec");
        return aliases;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.localization.get("commands.morecommands.enderchest.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof CommandBlockBaseLogic) {
            throw new PlayerNotFoundException("command_block");
        }
        EntityPlayerMP player = (EntityPlayerMP)sender;
        InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();
        player.displayGUIChest(inventoryenderchest);
    }
}