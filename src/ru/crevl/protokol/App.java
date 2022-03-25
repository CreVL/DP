package ru.crevl.protokol;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.crevl.protokol.entity.Consultant;
import ru.crevl.protokol.entity.Faculty;
import ru.crevl.protokol.form.ProtectionForm;
import ru.crevl.protokol.form.StudentForm;
import ru.crevl.protokol.form.TableForm;
import ru.crevl.protokol.manager.ConsultantManager;
import ru.crevl.protokol.manager.FacultyManager;
import ru.crevl.protokol.manager.GroupManager;
import ru.crevl.protokol.manager.ProgramManager;
import ru.crevl.protokol.util.DocumentWorker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App {
    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, new javax.swing.plaf.FontUIResource("Arial", Font.TRUETYPE_FONT, 12));
        }


        try {
            XWPFDocument doc = new XWPFDocument(Objects.requireNonNull(ProtectionForm.class.getClassLoader().getResourceAsStream("protocol.docx")));

            Map<String, String> repl = new HashMap<>();
            repl.put("DATE", "01.01.0001");
            repl.put("PR0N", "1");
            repl.put("PR0D", "2");
            repl.put("GCF", "Иванов Иван Иванович");
            repl.put("PRGCN", "999-1");
            repl.put("PRGCD", "02.02.2002");
            repl.put("LR", "X");
            repl.put("LE", "");
            repl.put("QUESTIONS", "ajdfhf oiyew nfoiyewg fiyewgfoi ewgfoi yewgoiyf goiyfg iewfg oiyew goifewy gfoiewyfoewy fvoewyfvoiyewvf oiewvf oyewv fpiew fpioewvofygewvofygew oufewvpy fvewpif vewof vewouf vewof voiewyfvoewiyfg ouew fgpoew few vfoiewv oif vewof ewfo ewif gewif vewfvewo9fewovfew ofoew fef");
            DocumentWorker.replaceMap(doc, repl);

            doc.write(new FileOutputStream(new File("C:\\Users\\Влад\\Desktop\\DP BD\\out.docx")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //new TableForm();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/university", "root", "1234");
    }
}
