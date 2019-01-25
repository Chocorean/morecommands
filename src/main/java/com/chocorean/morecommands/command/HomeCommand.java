package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.HomeNotFoundException;
import com.chocorean.morecommands.exception.HomeWrongDimensionException;
import com.chocorean.morecommands.model.IHome;
import com.chocorean.morecommands.model.PlayerPos;
import com.chocorean.morecommands.storage.StorageModule;
import com.chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class HomeCommand extends CommandBase {
    private final StorageModule storage;

    public HomeCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "home";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getUsageConfig().getHomeUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP p = (EntityPlayerMP) sender;
        IHome home = this.storage.findHome(p.getName());
        if (home == null) throw new HomeNotFoundException();
        if (p.dimension != 0) throw new HomeWrongDimensionException();
        PlayerPos newLastPos = new PlayerPos(p.getPosition(), 0, p.rotationYaw, p.rotationPitch);
        BackCommand.backList.put(p.getName(), newLastPos);
        // on tp
        BlockPos bp = home.getPosition();
        p.connection.setPlayerLocation(bp.getX(), bp.getY(), bp.getZ(), home.getYaw(), home.getPitch());
        p.setRotationYawHead(home.getYaw());
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
