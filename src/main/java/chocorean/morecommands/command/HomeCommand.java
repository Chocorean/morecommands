package chocorean.morecommands.command;

import chocorean.morecommands.exception.HomeNotFoundException;
import chocorean.morecommands.exception.HomeWrongDimensionException;
import chocorean.morecommands.model.IHome;
import chocorean.morecommands.model.PlayerPos;
import chocorean.morecommands.storage.StorageModule;
import chocorean.morecommands.storage.datasource.IDataSourceStrategy;
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
        return "commands.morecommands.home.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP p = (EntityPlayerMP) sender;
        IHome home = this.storage.findHome(p.getName());
        if (home == null) throw new HomeNotFoundException();
        if (p.dimension != 0) throw new HomeWrongDimensionException();
        PlayerPos newLastPos = new PlayerPos(p);
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
