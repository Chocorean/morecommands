package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.storage.IStorageStrategy;
import chocorean.morecommands.storage.StorageModule;
import chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class WarpsCommand extends CommandBase {
    private final IStorageStrategy storage;
    private Map<String, String> localization = MoreCommands.localization;

    public WarpsCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "warps";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return localization.get("commands.morecommands.warps.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(localization.get("commands.morecommands.warps.list"));
        try {
            ArrayList<String> warps = storage.listWarps();
            for (int i=0; i<warps.size(); i++) {
                buffer.append(warps.get(i));
                if (i != warps.size()-1) buffer.append(", ");
            }
            sender.sendMessage(new TextComponentString(buffer.toString()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
