package ru.crevl.protokol.manager;

import ru.crevl.protokol.App;
import ru.crevl.protokol.entity.Gak;
import ru.crevl.protokol.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GakManager {
    public static List<Gak> search(String number, String date) throws SQLException{
        try(Connection c = App.getConnection()){
            Map<Integer, String> map = new HashMap<>();
            StringBuilder sql = new StringBuilder("SELECT * FROM gak WHERE ");
            if (!number.isEmpty())
            {
                sql.append("cast(order_number as char) like concat(\"%\", ?,\"%\")");
                map.put(map.size() + 1, number);
            }
            if (!date.isEmpty())
            {
                if (map.size() != 0)
                    sql.append(" AND ");
                sql.append("cast(order_date as char) LIKE concat(\"%\", ?, \"%\")");
                map.put(map.size() + 1, date);
            }
            if (map.size() == 0) sql.append("1");
            PreparedStatement pst = c.prepareStatement(sql.toString());
            for (Map.Entry<Integer, String> z : map.entrySet())
                pst.setString(z.getKey(), z.getValue());
            ResultSet resultSet = pst.executeQuery();
            List<Gak> list = new ArrayList<>();
            while (resultSet.next()){
                list.add( new Gak(
                        resultSet.getInt("idGak"),
                        resultSet.getString("order_number"),
                        resultSet.getString("order_date"),
                        resultSet.getInt("chairman"),
                        resultSet.getInt("secretary")));
            }
            return list;
        }
    }

}
