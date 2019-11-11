package chocorean.morecommands.command;

import chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import chocorean.morecommands.exception.WarpNotFoundException;
import chocorean.morecommands.model.IWarp;
import chocorean.morecommands.storage.StorageModule;
import chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.List;

public class WarpCommand extends CommandBase {
    private final StorageModule storage;

    public WarpCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "warp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.morecommands.warp.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new InvalidNumberOfArgumentsException();
        }
        IWarp warp = this.storage.findWarp(args[0]);
        EntityPlayerMP p = (EntityPlayerMP)sender;
        if (warp != null) {
            if (warp.getDimension() != p.dimension) {
                p.changeDimension(warp.getDimension());
            }
            BlockPos pos = warp.getPosition();
            p.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), p.rotationYaw, p.rotationPitch);
        } else {
            throw new WarpNotFoundException(args[0]);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        try {
            return this.storage.listWarps();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
