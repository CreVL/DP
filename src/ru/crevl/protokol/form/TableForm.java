package ru.crevl.protokol.form;

import ru.crevl.protokol.entity.*;
import ru.crevl.protokol.manager.GakManager;
import ru.crevl.protokol.manager.StudentManager;
import ru.crevl.protokol.util.CustomTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TableForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField tfDate;
    private JTextField tfNumber;
    private JTextField tfChairman;
    private JTextField tfSecretary;
    private JButton studentButton;
    private JTable table1;

    private CustomTableModel<Gak> model;

    public TableForm(){
        super(800,600);
        setContentPane(mainPanel);
        studentButton.addActionListener(e-> new StudentForm());

        model = new CustomTableModel<>(
                new ArrayList<>(),
                Gak.class,
                new String[] { "idGAK", "order_number", "order_date", "chairman", "secretary"}
        );
        table1.setModel(model);


        updateSearch();
        tfDate.getDocument().addDocumentListener(new DocumentListener() {
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
        tfNumber.getDocument().addDocumentListener(new DocumentListener() {
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
                Gak g = model.getList().get(table1.rowAtPoint(e.getPoint()));
                if (g == null) return;

                tfChairman.setText(g.getChairman());
                tfSecretary.setText(g.getSecretary());
            }
        });

        setVisible(true);
    }

    private void updateSearch() {
        try{
            model.setList(GakManager.search(tfNumber.getText(), tfDate.getText()));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
