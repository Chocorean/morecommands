package com.chocorean.morecommands.storage;

import com.chocorean.morecommands.exception.MoreCommandsException;
import com.chocorean.morecommands.model.IHome;
import com.chocorean.morecommands.model.IWarp;
import com.chocorean.morecommands.storage.datasource.IDataSourceStrategy;

import java.sql.SQLException;
import java.util.ArrayList;

public class StorageModule implements IStorageStrategy {
    private final IDataSourceStrategy strategy;

    public StorageModule(IDataSourceStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public IWarp registerWarp(IWarp warp) throws SQLException {
        this.strategy.addWarp(warp);
        return warp;
    }

    @Override
    public IWarp findWarp(String name) throws MoreCommandsException {
        return this.strategy.retrieveWarp(name);
    }

    @Override
    public ArrayList<String> listWarps() throws SQLException {
        return this.strategy.findAllWarps();
    }

    @Override
    public IHome registerHome(IHome home) throws SQLException {
        this.strategy.addHome(home);
        return home;
    }

    @Override
    public IHome findHome(String username) throws MoreCommandsException {
        return this.strategy.retrieveHome(username);
    }
}
