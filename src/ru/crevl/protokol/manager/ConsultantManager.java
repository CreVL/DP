package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Consultant;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConsultantManager {
    public static List<Consultant> selectByProjectIdProject(int id) throws SQLException {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM consultant where project_idproject = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();


            List<Consultant> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Consultant(
                        resultSet.getInt("idconsultant"),
                        resultSet.getInt("employee_idemployee"),
                        resultSet.getInt("project_idproject")
                ));

            }
            return list;
        }
    }
}
