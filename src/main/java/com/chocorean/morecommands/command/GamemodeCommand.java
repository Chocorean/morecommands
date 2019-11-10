package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.InvalidGamemodeException;
import com.chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand extends CommandBase {
    @Override
    public String getName() {
        return "gamemode";
    }

    @Override
    public List<String> getAliases() {
        ArrayList<String> aliases = new ArrayList<>();
        aliases.add("gm");
        return aliases;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getUsageConfig().getGamemodeUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1)
            throw new InvalidNumberOfArgumentsException();
        int mode;
        try{
            mode = Integer.parseInt(args[0]);
        } catch (Exception e){
            throw new InvalidGamemodeException(args[0]);
        }
        if (mode > 3 || mode < 0)
            throw new InvalidGamemodeException(args[0]);
        // ok
        GameType gt = GameType.getByID(mode);
        EntityPlayerMP p = (EntityPlayerMP)sender;
        p.setGameType(gt);
        p.connection.sendPacket(new SPacketChat(new TextComponentString(String.format(MoreCommands.getConfig().getMessageConfig().getOnGamemodeChangeMessage(), gt.getName()))));
    }
}
