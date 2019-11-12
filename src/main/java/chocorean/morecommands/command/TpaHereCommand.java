package chocorean.morecommands.command;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import chocorean.morecommands.exception.PlayerNotFoundException;
import chocorean.morecommands.misc.TpHandler;
import chocorean.morecommands.model.PlayerPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TpaHereCommand extends CommandBase {
    private TpHandler handler = MoreCommands.handler;
    private Map<String, String> localization = MoreCommands.localization;

    @Override
    public String getName() {
        return "tpahere";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return localization.get("command.morecommands.tpahere.usage");
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(server.getPlayerList().getOnlinePlayerNames()));
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
        } else {
            EntityPlayerMP p = (EntityPlayerMP)sender;
            if (args[0].equals("no")){
                try {
                    this.handler.getSrcForTpa(p.getName()).connection.sendPacket(new SPacketChat(new TextComponentString(String.format("command.morecommands.tpdeny", p.getName()))));
                    // suppression
                    this.handler.rmTpa(p.getName());
                } catch (NullPointerException e) {
                    p.connection.sendPacket(new SPacketChat(new TextComponentString(
                            localization.get("command.morecommands.tpa.norequest"))));
                }
            } else if (args[0].equals("yes")){
                try {
                    EntityPlayerMP playerToTp = this.handler.getSrcForTpa(p.getName());
                    BlockPos pos = p.getPosition();
                    PlayerPos pp = new PlayerPos(playerToTp);
                    BackCommand.backList.put(playerToTp.getName(), pp);
                    if (playerToTp.dimension != p.dimension) playerToTp.changeDimension(p.dimension);
                    playerToTp.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), p.rotationYaw, p.rotationPitch);
                    this.handler.rmTpa(p.getName());
                } catch (NullPointerException e) {
                    p.connection.sendPacket(new SPacketChat(new TextComponentString(
                            localization.get("command.morecommands.tpa.norequest"))));
                }
            } else if (args[0].equals(sender.getName())){ // un boloss essaie de se tp a soi meme
                throw new CommandException(localization.get("command.morecommands.tpa.self"));
            } else { // on essaie de tp
                EntityPlayerMP src = p.getServer().getPlayerList().getPlayerByUsername(args[0]);
                if (src != null) {
                    // le joueur existe
                    handler.addTpah(src.getName(), p);
                    src.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(
                            localization.get("command.morecommands.tpahere.src"),
                            p.getName()))));
                    p.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(
                            localization.get("command.morecommands.tpahere.dst"),
                            src.getName()))));
                } else {
                    throw new PlayerNotFoundException(args[0]);
                }
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
