package com.chocorean.morecommands;

import com.chocorean.morecommands.command.BackCommand;
import com.chocorean.morecommands.model.PlayerPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class Handler {
    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event){
        if (!event.player.getEntityWorld().isRemote){
            EntityPlayer player = event.player;

            // Back command
            PlayerPos firstPos = new PlayerPos(player);
            BackCommand.backList.put(player.getName(), firstPos);
        }
    }

    @SubscribeEvent
    public static void onLeave(PlayerEvent.PlayerLoggedOutEvent event){
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        BackCommand.backList.remove(player.getName());
        MoreCommands.handler.clean(player.getName());
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        try {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
            PlayerPos pos = new PlayerPos(player);
            BackCommand.backList.put(player.getName(), pos);
        } catch (ClassCastException e) {
            // nothing to do: not a player
        }
    }
}
