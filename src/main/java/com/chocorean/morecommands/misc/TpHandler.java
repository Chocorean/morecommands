package com.chocorean.morecommands.misc;

import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;

public class TpHandler {
    private HashMap<String, EntityPlayerMP> tpa;
    private HashMap<String, EntityPlayerMP> tpah;

    public TpHandler() {
        tpa  = new HashMap<>();
        tpah  = new HashMap<>();
    }

    public void addTpa(String dest, EntityPlayerMP source) {
        tpa.put(dest, source);
    }

    public void rmTpa(String dest) {
        tpa.remove(dest);
    }

    public void addTpah(String source, EntityPlayerMP dest) {
        tpah.put(source, dest);
    }

    public void rmTpah(String source) {
        tpah.remove(source);
    }

    public void clean(String discoPlayer) {
        tpa.forEach((key, value) -> {
            if (key.equals(discoPlayer) || value.getName().equals(discoPlayer)) {
                tpa.remove(key);
            }
        });
        tpah.forEach((key, value) -> {
            if (key.equals(discoPlayer) || value.getName().equals(discoPlayer)) {
                tpah.remove(key);
            }
        });
    }

    public EntityPlayerMP getSrcForTpa(String key) {
        return tpa.get(key);
    }

    public EntityPlayerMP getDestForTpah(String key) {
        return tpah.get(key);
    }

}
