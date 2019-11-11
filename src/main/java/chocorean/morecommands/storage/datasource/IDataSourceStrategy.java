package chocorean.morecommands.storage.datasource;

import chocorean.morecommands.exception.MoreCommandsException;
import chocorean.morecommands.model.IHome;
import chocorean.morecommands.model.IWarp;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDataSourceStrategy {
    IWarp retrieveWarp(String name) throws MoreCommandsException;
    IWarp addWarp(IWarp warp) throws SQLException;
    ArrayList<String> findAllWarps() throws SQLException;
    IWarp deleteWarp(String name) throws SQLException;

    IHome retrieveHome(String username) throws MoreCommandsException;
    IHome addHome(IHome home) throws SQLException;
}

