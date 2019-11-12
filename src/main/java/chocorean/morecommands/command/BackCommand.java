package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.exception.PlayerHasNoLastPositionException;
import chocorean.morecommands.model.PlayerPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class BackCommand extends CommandBase {
    public static HashMap<String, PlayerPos> backList = new HashMap<>();

    @Override
    public String getName() {
        return "back";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.localization.get("commands.morecommands.back.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP p = ((EntityPlayerMP)sender);
        // récupération de la destination
        PlayerPos lastPos = backList.get(p.getName());
        if (lastPos == null) throw new PlayerHasNoLastPositionException();
        // on note pour le prochain /back
        PlayerPos newLastPos = new PlayerPos(p);
        // on tp
        if (p.dimension != lastPos.getDimension()) p.changeDimension(lastPos.getDimension());
        BlockPos bp = lastPos.getPosition();
        p.connection.setPlayerLocation(bp.getX(), bp.getY(), bp.getZ(), lastPos.getYaw(), lastPos.getPitch());
        p.setRotationYawHead(lastPos.getYaw());
        // on maj la backlist
        backList.put(p.getName(), newLastPos);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
