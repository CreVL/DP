package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Group;
import ru.crevl.protokol.entity.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupManager {
    public static Group selectById(String id) throws SQLException {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM `group` WHERE number = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new Group(
                        resultSet.getString("number"),
                        resultSet.getString("course"),
                        resultSet.getInt("program_idprogram")
                );
            }
        }
        return null;

    }
}
