package com.chocorean.morecommands.storage;

import com.chocorean.morecommands.exception.MoreCommandsException;
import com.chocorean.morecommands.model.IHome;
import com.chocorean.morecommands.model.IWarp;

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
