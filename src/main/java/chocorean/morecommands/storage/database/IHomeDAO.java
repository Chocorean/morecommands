package chocorean.morecommands.storage.database;

import chocorean.morecommands.model.IHome;

import java.sql.SQLException;

public interface IHomeDAO<H extends IHome> {
    void create(H home) throws SQLException;
    void modify(H home) throws SQLException;
    H findByUsername(String username) throws SQLException;
}
