package com.chocorean.morecommands.storage.database;

import com.chocorean.morecommands.model.IWarp;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IWarpDAO<W extends IWarp> {
    void create(W warp) throws SQLException;
    void modify(W warp) throws SQLException;
    W findByName(String name) throws SQLException;
    ArrayList<String> findAll() throws SQLException;
    W deleteWarp(String name) throws SQLException;
}
