package chocorean.morecommands.command;

import chocorean.morecommands.exception.InvalidArgumentException;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

public class KillAllCommand extends CommandBase {
    @Override
    public String getName() {
        return "killall";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("ka");
        return aliases;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.morecommands.killall.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        boolean isPlayer = true;
        if (sender instanceof CommandBlockBaseLogic) {
            // avoid PlayerNotFoundException("command_block");
            isPlayer = false;
        }
        int distance;
        if (args.length > 0) {
            try {
                distance = Integer.parseInt(args[0]);
                if (distance < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                if (isPlayer) {
                    throw new InvalidArgumentException(args[0]);
                }
                return;
            }
        } else {
            distance = 100;
        }
        BlockPos pos = sender.getPosition();
        List<Entity> availableEntities = sender.getEntityWorld().loadedEntityList;
        int cpt = 0;
        for (int i=0; i<availableEntities.size(); i++) {
            Entity e = availableEntities.get(i);
            if (e instanceof EntityLivingBase && !(e instanceof EntityPlayerMP)) {
                BlockPos entityPos = e.getPosition();
                if (entityPos.getDistance(pos.getX(), pos.getY(), pos.getZ()) <= distance) {
                    try {
                        e.onKillCommand();
                        cpt++;
                    } catch (Exception ex) {
                        // nothing to do
                    }
                }
            }
        }
        ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString(String.format("command.morecommands.kilall.success", cpt))));
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
