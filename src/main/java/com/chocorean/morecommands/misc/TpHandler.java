package com.chocorean.morecommands.misc;

import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;

public class TpHandler {
    private HashMap<EntityPlayerMP, EntityPlayerMP> tpa;
    private HashMap<EntityPlayerMP, EntityPlayerMP> tpah;

    public TpHandler() {
        tpa  = new HashMap<>();
        tpah  = new HashMap<>();
    }

    public void addTpa(EntityPlayerMP dest, EntityPlayerMP source) {
        tpa.put(dest, source);
    }

    public void rmTpa(EntityPlayerMP dest) {
        tpa.remove(dest);
    }

    public void addTpah(EntityPlayerMP source, EntityPlayerMP dest) {
        tpah.put(source, dest);
    }

    public void rmTpah(EntityPlayerMP source) {
        tpah.remove(source);
    }

    public void clean(EntityPlayerMP discoPlayer) {
        tpa.forEach((key, value) -> {
            if (key.equals(discoPlayer) || value.equals(discoPlayer)) {
                tpa.remove(key);
            }
        });
        tpah.forEach((key, value) -> {
            if (key.equals(discoPlayer) || value.equals(discoPlayer)) {
                tpah.remove(key);
            }
        });
    }

    public EntityPlayerMP getSrcForTpa(EntityPlayerMP key) {
        return tpa.get(key);
    }

    public EntityPlayerMP getDestForTpah(EntityPlayerMP key) {
        return tpah.get(key);
    }

}
