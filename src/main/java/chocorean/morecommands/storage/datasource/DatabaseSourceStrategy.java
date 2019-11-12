package chocorean.morecommands.storage.datasource;

import chocorean.morecommands.MoreCommands;
import chocorean.morecommands.exception.HomeNotFoundException;
import chocorean.morecommands.exception.WarpNotFoundException;
import chocorean.morecommands.storage.database.HomeDAO;
import chocorean.morecommands.config.MoreCommandsConfig;
import chocorean.morecommands.exception.MoreCommandsException;
import chocorean.morecommands.model.Home;
import chocorean.morecommands.model.IHome;
import chocorean.morecommands.model.IWarp;
import chocorean.morecommands.model.Warp;
import chocorean.morecommands.storage.database.WarpDAO;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseSourceStrategy implements IDataSourceStrategy {
    private final HomeDAO<Home> homesDAO;
    private final WarpDAO<Warp> warpsDAO;
    private final Logger LOGGER = FMLLog.log;

    public DatabaseSourceStrategy() {
        this.homesDAO = new HomeDAO<>(MoreCommandsConfig.DatabaseCategory.hometable);
        this.warpsDAO = new WarpDAO<>(MoreCommandsConfig.DatabaseCategory.warptable);
    }

    @Override
    public IWarp retrieveWarp(String name) throws MoreCommandsException {
        IWarp w;
        try {
            w = this.warpsDAO.findByName(name);
        } catch(SQLException e) {
            LOGGER.catching(Level.ERROR, e);
            throw new WarpNotFoundException(MoreCommands.localization.get("morecommands.database.connecterror"));
        }
        return w;
    }

    @Override
    public IWarp addWarp(IWarp warp) throws SQLException {
        IWarp saved = this.warpsDAO.findByName(warp.getName());
        if(saved != null)
            this.warpsDAO.modify(warp);
        else
            this.warpsDAO.create(warp);
        return warp;
    }

    @Override
    public ArrayList<String> findAllWarps() throws SQLException {
        return this.warpsDAO.findAll();
    }

    @Override
    public IWarp deleteWarp(String name) throws  SQLException {
        return this.warpsDAO.deleteWarp(name);
    }

    @Override
    public IHome retrieveHome(String username) throws MoreCommandsException {
        IHome h;
        try {
            h = this.homesDAO.findByUsername(username);
        } catch(SQLException e) {
            LOGGER.catching(Level.ERROR, e);
            throw new HomeNotFoundException();
        }
        return h;
    }

    @Override
    public IHome addHome(IHome home) throws SQLException {
        IHome saved = this.homesDAO.findByUsername(home.getUsername());
        if(saved != null)
            this.homesDAO.modify(home);
        else
            this.homesDAO.create(home);
        return home;
    }
}
