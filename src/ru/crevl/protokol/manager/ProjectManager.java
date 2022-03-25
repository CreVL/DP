package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Program;
import ru.crevl.protokol.entity.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectManager {
    public static Project selectByStudentId(int id) throws SQLException {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM project where student_isu_id  = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new Project(
                        resultSet.getInt("idproject"),
                        resultSet.getString("theme"),
                        resultSet.getInt("employee_idemployee"),
                        resultSet.getInt("student_isu_id")

                );
            }
        }
        return null;

    }
}
