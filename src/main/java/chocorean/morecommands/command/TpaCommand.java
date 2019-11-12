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

public class TpaCommand extends CommandBase {
    private TpHandler handler = MoreCommands.handler;
    private Map<String, String> localization = MoreCommands.localization;

    @Override
    public String getName() {
        return "tpa";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return localization.get("commands.morecommands.tpa.usage");
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
        if (args.length != 1){
            throw new InvalidNumberOfArgumentsException();
        } else {
            EntityPlayerMP p = (EntityPlayerMP)sender;
            if (args[0].equals("no")){
                try {
                    this.handler.getDestForTpah(p.getName()).sendMessage(new TextComponentString(
                            String.format(localization.get("commands.morecommands.tpdeny"), p.getName())));
                    // suppression
                    this.handler.rmTpah(p.getName());
                } catch (NullPointerException e) {
                    p.sendMessage(new TextComponentString(localization.get("commands.morecommands.tpa.norequest")));
                }
            } else if (args[0].equals("yes")){
                try {
                    EntityPlayerMP dest = this.handler.getDestForTpah(p.getName());
                    BlockPos pos = dest.getPosition();
                    PlayerPos pp = new PlayerPos(p);
                    BackCommand.backList.put(p.getName(), pp);
                    if (p.dimension != dest.dimension) p.changeDimension(dest.dimension);
                    p.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), p.rotationYaw, p.rotationPitch);
                    this.handler.rmTpah(p.getName());
                } catch (NullPointerException e) {
                    p.sendMessage(new TextComponentString(localization.get("commands.morecommands.tpa.norequest")));
                }
            } else if (args[0].equals(sender.getName())){ // un boloss essaie de se tp a soi meme
                throw new CommandException(localization.get("commands.morecommands.tpa.self"));
            } else { // on essaie de se tp
                EntityPlayerMP src = (EntityPlayerMP)sender;
                EntityPlayerMP dest = src.getServer().getPlayerList().getPlayerByUsername(args[0]);
                if (dest != null) {
                    // le joueur existe
                    handler.addTpa(dest.getName(), src);
                    dest.sendMessage(new TextComponentString(String.format(
                            localization.get("commands.morecommands.tpa.dst"),
                            src.getName())));
                    src.sendMessage(new TextComponentString(String.format(
                            localization.get("commands.morecommands.tpa.src"),
                            dest.getName())));
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
