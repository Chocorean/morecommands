package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.model.PlayerPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class SpawnCommand extends CommandBase {
    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.localization.get("command.morecommands.spawn.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        EntityPlayerMP p = (EntityPlayerMP)sender;
        PlayerPos newLastPos = new PlayerPos(p);
        BackCommand.backList.put(sender.getName(), newLastPos);
        BlockPos spawn = server.getEntityWorld().getSpawnPoint();
        if (p.dimension != 0) p.changeDimension(0);
        p.connection.setPlayerLocation(spawn.getX(), spawn.getY(), spawn.getZ(), p.rotationYaw, p.rotationPitch);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
