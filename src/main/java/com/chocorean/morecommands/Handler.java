package com.chocorean.morecommands;

import com.chocorean.morecommands.command.BackCommand;
import com.chocorean.morecommands.misc.PosPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class Handler {
    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event){
        if (!event.player.getEntityWorld().isRemote){
            EntityPlayer player = event.player;

            // Back command
            BackCommand.players.add(new PosPlayer(player,player.getPosition()));
        }
    }

    @SubscribeEvent
    public static void onLeave(PlayerEvent.PlayerLoggedOutEvent event){
        if (!event.player.getEntityWorld().isRemote){
            EntityPlayer player = event.player;

            // Back command
            for (PosPlayer pp : BackCommand.players) {
                if (pp.player.equals(player)) {
                    BackCommand.players.remove(pp);
                    break;
                }
            }

            // Tpa command


            // Tpahere command

        }
    }
}
