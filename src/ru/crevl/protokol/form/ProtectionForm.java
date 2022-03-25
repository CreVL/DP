package ru.crevl.protokol.form;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.crevl.protokol.entity.*;
import ru.crevl.protokol.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProtectionForm extends BaseForm {
    private JPanel mainPanel;
    private JButton saveButton;
    private JTextField tfDate;
    private JTextField tfTime;
    private JTextField tfRoom;
    private JTextField tfMark;
    private JComboBox languageСomboBox;
    private JComboBox formatСomboBox;
    private JCheckBox vkrCheckBox;
    private JCheckBox directorCheckBox;
    private JCheckBox reviewCheckBox;
    private JCheckBox drawingCheckBox;
    private JCheckBox presentationCheckBox;
    private JCheckBox progressCheckBox;
    private JTextArea questionTextArea;
    private CustomTableModel<Gak> model;

    public ProtectionForm(){
        super(800, 600);

        setContentPane(mainPanel);

        setVisible(true);
    }
}
