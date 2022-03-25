package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Graduation;

import java.sql.*;

public class GraduationManager {
    public static void insertGraduation (Graduation graduation) throws SQLException {
        try(Connection c = App.getConnection()){
            PreparedStatement s = c.prepareStatement("INSERT INTO graduation (date_at, time_at, room, mark, GAK_idGAK, project_idproject) values (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            s.setString(1, graduation.getDate());
            s.setString(2, graduation.getTime());
            s.setString(3, graduation.getRoom());
            s.setInt(4, graduation.getMark());
            s.setInt(4, graduation.getGakIdGak());
            s.setInt(4, graduation.getProjectIdProject());
            s.executeUpdate();
            ResultSet resultSet = s.getGeneratedKeys();
            if (!resultSet.next()){
                throw new SQLException("ERROR");
            }
            graduation.setGakIdGak(resultSet.getInt(1));

        }

    }
}
