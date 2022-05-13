package ru.crevl.protokol.form;

import ru.crevl.protokol.entity.*;
import ru.crevl.protokol.manager.ConsultantManager;
import ru.crevl.protokol.manager.ProgramManager;
import ru.crevl.protokol.manager.StudentManager;
import ru.crevl.protokol.util.CustomTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StudentForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField tfGroup;
    private JTextField tfSearch;
    private JTextField tfFaculty;
    private JTextField tfSpecialization;
    private JTextField tfDirection;
    private JTextField tfTitleVkr;
    private JTextField tfDirector;
    private JTextField tfConsultant;
    private JTable table1;
    private JButton performButton;
    private JButton cancelButton;
    private JTextField tfStudentFIO;

    private CustomTableModel<Student> model;

    private Student student;

    private Gak gak;


    public StudentForm(Gak g) {
        super(800,600);
        setContentPane(mainPanel);

            model = new CustomTableModel<>(
                    new ArrayList<>(),
                    Student.class,
                    new String[] { "Номер ISU", "Фамилия", "Имя", "Отчество", "Группа"}
            );
            gak = g;
        table1.setModel(model);

        performButton.addActionListener(e-> {
            if (student == null) return;
            new ProtectionForm(student,gak);

        });
        
        cancelButton.addActionListener(e -> dispose());

        updateSearch();
        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearch();
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Student s = model.getList().get(table1.rowAtPoint(e.getPoint()));
                if (s == null) return;

                student = s;

                Group g = s.getGroup();
                Program p = g.getProgram();
                Faculty f = p.getFaculty();
                Project pj = s.getProject();

                tfStudentFIO.setText(s.getFIO());
                tfGroup.setText(s.getGroupNumber());
                tfFaculty.setText(f.getName());
                tfSpecialization.setText(p.getSpecialization());
                tfDirection.setText(p.getDirection());
                tfTitleVkr.setText(pj.getTheme());
                tfDirector.setText(pj.getSupervisor().getFIO());
                tfConsultant.setText(pj.getConsultants()
                        .stream()
                        .map(Consultant::getEmployee)
                        .map(Employee::getFIO)
                        .collect(Collectors.joining(", ")));


            }
        });

        setVisible(true);
    }

    private void updateSearch() {
        try{
            model.setList(StudentManager.search(tfSearch.getText()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
