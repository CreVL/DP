package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Employee;
import ru.crevl.protokol.entity.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeManager {
    public static Employee selectById(int id) throws SQLException {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM employee where idemployee = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new Employee(
                        resultSet.getInt("idemployee"),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("patronymic"),
                        resultSet.getString("post"),
                        resultSet.getString("degree"),
                        resultSet.getString("title"),
                        resultSet.getInt("faculty_idfaculty")
                );
            }
        }
        return null;

    }
}
