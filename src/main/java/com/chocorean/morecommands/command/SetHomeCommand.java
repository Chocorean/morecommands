package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.NoHomeException;
import com.chocorean.morecommands.model.Home;
import com.chocorean.morecommands.storage.StorageModule;
import com.chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.sql.SQLException;

public class SetHomeCommand extends CommandBase {
    private final StorageModule storage;

    public SetHomeCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "sethome";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.morecommands.sethome.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP p = (EntityPlayerMP)sender;
        if (p.dimension != 0) {
            throw new NoHomeException();
        }
        BlockPos pos = p.getPosition();
        Home home = new Home(p.getName(), pos, p.rotationYaw, p.rotationPitch);
        try {
            this.storage.registerHome(home);
            p.connection.sendPacket(new SPacketChat(new TextComponentString("commands.morecommands.sethome.success")));
        } catch (SQLException e) {
            MoreCommands.LOGGER.error(e);
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
