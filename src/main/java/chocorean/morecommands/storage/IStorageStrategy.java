package chocorean.morecommands.storage;

import chocorean.morecommands.exception.MoreCommandsException;
import chocorean.morecommands.model.IHome;
import chocorean.morecommands.model.IWarp;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IStorageStrategy {
    IWarp registerWarp(IWarp warp) throws SQLException;
    IWarp findWarp(String name) throws MoreCommandsException;
    ArrayList<String> listWarps() throws SQLException;
    IWarp deleteWarp(String name) throws SQLException;

    IHome registerHome(IHome home) throws SQLException;
    IHome findHome(String username) throws MoreCommandsException;
}
