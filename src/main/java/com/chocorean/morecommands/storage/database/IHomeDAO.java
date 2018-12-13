package com.chocorean.morecommands.storage.database;

import com.chocorean.morecommands.model.IHome;

import java.sql.SQLException;
import java.util.List;

public interface IHomeDAO<H extends IHome> {
    void create(H home) throws SQLException;
    void modify(H home) throws SQLException;
    H findByUsername(String username) throws SQLException;
}
