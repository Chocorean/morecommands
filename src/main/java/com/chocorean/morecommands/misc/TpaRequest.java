package com.chocorean.morecommands.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;

public class TpaRequest {
    public EntityPlayer target;
    public EntityPlayer dest;

    public TpaRequest(EntityPlayer t, String dName){
        target=t;
        dest=target.getEntityWorld().getPlayerEntityByName(dName);
        ((EntityPlayerMP)target).connection.sendPacket(new SPacketChat(new TextComponentString("Sending request to "+dest.getName()+"...")));
        ((EntityPlayerMP)dest).connection.sendPacket(new SPacketChat(new TextComponentString(target.getName()+" wants to teleport to you. /tpahere yes to accept or /tphere no to refuse.")));
    }
}
