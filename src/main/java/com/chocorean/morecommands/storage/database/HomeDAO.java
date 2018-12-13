package com.chocorean.morecommands.storage.database;

import com.chocorean.morecommands.model.Home;
import com.chocorean.morecommands.model.IHome;
import net.minecraft.util.math.BlockPos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeDAO<H> implements IHomeDAO {
    private final String table;

    public HomeDAO(String table) {
        this.table = table;
    }

    @Override
    public void create(IHome home) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String query = String.format("INSERT INTO %s(username, x, y, z) VALUES(?,?,?,?,?,?)", this.table);
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, home.getUsername());
            stmt.setInt(2, home.getPosition().getX());
            stmt.setInt(3, home.getPosition().getY());
            stmt.setInt(4, home.getPosition().getZ());
            stmt.setFloat(5, home.getYaw());
            stmt.setFloat(6, home.getPitch());
            stmt.executeUpdate();
        }
        conn.close();
    }

    @Override
    public void modify(IHome home) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String query = String.format("UPDATE %s SET x = ?, y = ?, z = ?, yaw = ?, pitch = ? WHERE name = ?", this.table);
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, home.getPosition().getX());
            stmt.setInt(2, home.getPosition().getY());
            stmt.setInt(3, home.getPosition().getZ());
            stmt.setFloat(4, home.getYaw());
            stmt.setFloat(5, home.getPitch());
            stmt.setString(6, home.getUsername());
            stmt.executeUpdate();
        }
        conn.close();
    }

    @Override
    public IHome findByUsername(String username) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format("SELECT * FROM %s WHERE username = ?", this.table));
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        return createHome(rs);
    }

    private IHome createHome(ResultSet rs) throws SQLException{
        return new Home((rs.getString(1)),
                new BlockPos(rs.getInt(2), rs.getInt(3), rs.getInt(4)),
                rs.getFloat(5),
                rs.getFloat(6));
    }
}
