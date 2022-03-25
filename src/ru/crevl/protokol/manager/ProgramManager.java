package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Faculty;
import ru.crevl.protokol.entity.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramManager {
    public static Program selectById(int id) throws SQLException {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM program where idprogram  = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new Program(
                        resultSet.getInt("idprogram"),
                        resultSet.getString("direction"),
                        resultSet.getString("profile"),
                        resultSet.getString("specialization"),
                        resultSet.getString("qualification"),
                        resultSet.getString("status"),
                        resultSet.getInt("faculty_idfaculty")
                );
            }
        }
        return null;

    }
}
