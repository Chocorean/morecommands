package chocorean.morecommands.storage.datasource;

import chocorean.morecommands.model.Home;
import chocorean.morecommands.model.IHome;
import chocorean.morecommands.model.IWarp;
import chocorean.morecommands.model.Warp;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

public class FileDataSourceStrategy implements IDataSourceStrategy {
    private File file;
    private HashMap<String, IWarp> warps;
    private HashMap<String, IHome> homes;

    private static final Logger LOGGER = FMLLog.log;
    private static final String SEPARATOR = ";";
    private long lastModification;

    public FileDataSourceStrategy(File file) {
        this.file = file;
        warps = new HashMap<>();
        homes = new HashMap<>();
        this.readFile();
    }

    private void readFile() {
        this.warps.clear();
        this.homes.clear();
        try {
            this.file.createNewFile();
            try(BufferedReader bf = new BufferedReader(new FileReader(this.file))) {
                String line;
                while((line = bf.readLine()) != null && line.trim().length() > 0) {
                    if(!line.startsWith("#")) {
                        String[] parts = line.trim().split(SEPARATOR);
                        switch(parts[0].trim()) {
                            case "HOME":
                                BlockPos homePos = new BlockPos(
                                        Integer.parseInt(parts[2].trim()),
                                        Integer.parseInt(parts[3].trim()),
                                        Integer.parseInt(parts[4].trim()));
                                Home home = new Home(parts[1].trim(),
                                        homePos,
                                        Float.parseFloat(parts[5].trim().replace(',','.')),
                                        Float.parseFloat(parts[6].trim().replace(',','.')));
                                this.homes.put(parts[1].trim(), home);
                                break;
                            case "WARP":
                                BlockPos warpPos = new BlockPos(Integer.parseInt(parts[2].trim()), Integer.parseInt(parts[3].trim()), Integer.parseInt(parts[4].trim()));
                                Warp w = new Warp(parts[1].trim(),
                                        warpPos,
                                        Integer.parseInt(parts[5].trim()),
                                        Float.parseFloat(parts[6].trim().replace(',','.')),
                                        Float.parseFloat(parts[7].trim().replace(',','.')));
                                this.warps.put(parts[1].trim(), w);
                        }
                    }
                }
                this.lastModification = this.file.lastModified();
            }
        } catch (IOException e) { LOGGER.catching(e); }
    }

    private void reloadFile() {
        if (lastModification != this.file.lastModified())
            this.readFile();
    }

    private void saveFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
            bw.write(String.join(SEPARATOR,
                    "# OBJECT",
                    " identifier",
                    " data related to OBJECT"));
            bw.newLine();
            for(Map.Entry<String, IHome> entry: this.homes.entrySet()) {
                bw.write(String.join(SEPARATOR,
                        "HOME",
                        entry.getKey(),
                        // evite le doublon
                        // entry.getValue().getUsername(),
                        String.format("%d",entry.getValue().getPosition().getX()),
                        String.format("%d",entry.getValue().getPosition().getY()),
                        String.format("%d",entry.getValue().getPosition().getZ()),
                        String.format("%f",entry.getValue().getYaw()),
                        String.format("%f",entry.getValue().getPitch())));
                bw.newLine();
            }
            for(Map.Entry<String, IWarp> entry: this.warps.entrySet()) {
                bw.write(String.join(SEPARATOR,
                        "WARP",
                        entry.getKey(),
                        // evite le doublon
                        //entry.getValue().getName(),
                        String.format("%d",entry.getValue().getPosition().getX()),
                        String.format("%d",entry.getValue().getPosition().getY()),
                        String.format("%d",entry.getValue().getPosition().getZ()),
                        String.format("%d",entry.getValue().getDimension()),
                        String.format("%f",entry.getValue().getYaw()),
                        String.format("%f",entry.getValue().getPitch())));
                bw.newLine();
            }

        } catch (IOException e) { LOGGER.catching(e); }
    }

    @Override
    public IWarp retrieveWarp(String name) {
        this.reloadFile();
        return this.warps.get(name);
    }

    @Override
    public IWarp addWarp(IWarp warp) {
        this.reloadFile();
        this.warps.put(warp.getName(), warp);
        this.saveFile();
        return warp;
    }

    @Override
    // transformer ca en liste de string
    public ArrayList<String> findAllWarps() {
        this.reloadFile();
        ArrayList<String> warps = new ArrayList<>();
        warps.addAll(this.warps.keySet());
        return warps;
    }

    @Override
    public IWarp deleteWarp(String name) {
        this.reloadFile();
        return this.warps.remove(name);
    }

    @Override
    public IHome retrieveHome(String username) {
        this.reloadFile();
        return this.homes.get(username);
    }

    @Override
    public IHome addHome(IHome home) {
        this.reloadFile();
        this.homes.put(home.getUsername(), home);
        this.saveFile();
        return home;
    }
}
