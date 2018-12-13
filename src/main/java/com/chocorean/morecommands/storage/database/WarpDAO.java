package com.chocorean.morecommands.storage.database;

import com.chocorean.morecommands.model.IWarp;
import com.chocorean.morecommands.model.Warp;
import net.minecraft.util.math.BlockPos;

import java.sql.*;
import java.util.ArrayList;

public class WarpDAO<W> implements IWarpDAO {
    private String table;

    public WarpDAO(String table) {
        this.table = table;
    }

    @Override
    public void create(IWarp warp) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String query = String.format("INSERT INTO %s(name, x, y, z, dim) VALUES(?,?,?,?,?,?,?)", this.table);
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, warp.getName());
            stmt.setInt(2, warp.getPosition().getX());
            stmt.setInt(3, warp.getPosition().getY());
            stmt.setInt(4, warp.getPosition().getZ());
            stmt.setInt(5, warp.getDimension());
            stmt.setFloat(6, warp.getYaw());
            stmt.setFloat(7, warp.getPitch());
            stmt.executeUpdate();
        }
        conn.close();
    }

    @Override
    public void modify(IWarp warp) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String query = String.format("UPDATE %s SET x = ?, y = ?, z = ?, dim = ?, yaw = ?, pitch = ? WHERE name = ?", this.table);
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, warp.getPosition().getX());
            stmt.setInt(2, warp.getPosition().getY());
            stmt.setInt(3, warp.getPosition().getZ());
            stmt.setInt(4, warp.getDimension());
            stmt.setString(5, warp.getName());
            stmt.setFloat(6, warp.getYaw());
            stmt.setFloat(7, warp.getPitch());
            stmt.executeUpdate();
        }
        conn.close();
    }

    @Override
    public IWarp findByName(String name) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(String.format("SELECT * FROM %s WHERE name = ?", this.table));
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        conn.close();
        return createWarp(rs);
    }

    private IWarp createWarp(ResultSet rs) throws SQLException {
        return new Warp(rs.getString(1),
                new BlockPos(rs.getInt(2), rs.getInt(3), rs.getInt(4)),
                rs.getInt(5),
                rs.getFloat(6),
                rs.getFloat(7));
    }

    @Override
    public ArrayList<String> findAll() throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT name FROM %s", this.table));
        ArrayList<String> warps = new ArrayList<>();
        if (!rs.next()) return warps;
        do {
            warps.add(rs.getString(1));
        } while (rs.next());
        return warps;
    }
}
