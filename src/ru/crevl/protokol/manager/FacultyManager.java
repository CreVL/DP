package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Faculty;

import java.sql.*;

public class FacultyManager {
    public static Faculty selectById(int id) throws SQLException {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM faculty where idfaculty = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new Faculty(
                        resultSet.getInt("idfaculty"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("director")
                );
            }
        }
        return null;

    }
}
