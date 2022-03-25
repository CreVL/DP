package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManager {
    public static List<Student> search(String text) throws SQLException {
        try(Connection c = App.getConnection()){
            Map<Integer, String> map = new HashMap<>();
            StringBuilder sql = new StringBuilder("SELECT * FROM student WHERE cast(isu_id as char) like concat(\"%\", ?,\"%\")");
            map.put(1, text);
            for (String s: text.split(" ")) {
                if (s.isEmpty()) continue;
                if (map.size() == 1)
                    sql.append(" OR (");
                else
                    sql.append(" AND ");
                sql.append("CONCAT(name, surname, patronymic) LIKE CONCAT(\"%\", ?,\"%\")");
                map.put(1 + map.size(), s);
            }
            if (map.size() != 1) sql.append(")");
            PreparedStatement pst = c.prepareStatement(sql + " order by surname LIMIT 20;");
            for (Map.Entry<Integer, String> z : map.entrySet())
                pst.setString(z.getKey(), z.getValue());
            ResultSet resultSet = pst.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()){
                list.add( new Student(
                        resultSet.getInt("isu_id"),
                        resultSet.getString("surname"),
                        resultSet.getString("name"),
                        resultSet.getString("patronymic"),
                        resultSet.getString("group_number")));
            }
            return list;
        }
    }
}
