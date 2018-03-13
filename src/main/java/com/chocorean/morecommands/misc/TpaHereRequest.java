package com.chocorean.morecommands.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;

public class TpaHereRequest {
    public EntityPlayer target;
    public EntityPlayer dest;

    public TpaHereRequest(String tName, EntityPlayer d){
        dest=d;
        target=dest.getEntityWorld().getPlayerEntityByName(tName);
        ((EntityPlayerMP)target).connection.sendPacket(new SPacketChat(new TextComponentString(dest.getName()+" wants you to teleport to him/her. /tpa yes to accept or /tpa no to refuse.")));
        ((EntityPlayerMP)dest).connection.sendPacket(new SPacketChat(new TextComponentString("Request sent to "+target.getName()+"...")));
    }
}
