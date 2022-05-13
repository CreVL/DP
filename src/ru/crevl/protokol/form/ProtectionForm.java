package ru.crevl.protokol.form;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.crevl.protokol.entity.*;
import ru.crevl.protokol.util.CustomTableModel;
import ru.crevl.protokol.util.DialogUtil;
import ru.crevl.protokol.util.DocumentWorker;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;

public class ProtectionForm extends BaseForm {
    private JPanel mainPanel;
    private JButton saveButton;
    private JTextField tfRoom;
    private JComboBox languageСomboBox;
    private JComboBox formatСomboBox;
    private JCheckBox vkrCheckBox;
    private JCheckBox directorCheckBox;
    private JCheckBox reviewCheckBox;
    private JCheckBox drawingCheckBox;
    private JCheckBox presentationCheckBox;
    private JCheckBox progressCheckBox;
    private JTextArea questionTextArea;
    private JTextField textField1;
    private JComboBox hasRemarksComboBox;
    private JComboBox cmbLevel;
    private JComboBox markComboBox;
    private JRadioButton rbNotA;
    private JRadioButton rbIsA;
    private JTextField tfRecommendation;
    private DateTimePicker pickerDate;
    private CustomTableModel<Gak> model;

    private Student student;
    private Gak gak;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("«dd» MMMM yyyy г.", Locale.forLanguageTag("ru"));

    public ProtectionForm(Student student, Gak g) {
        super(800, 600);
        this.student = student;
        gak=g;

        languageСomboBox.addItem("русском");
        languageСomboBox.addItem("английском");

        formatСomboBox.addItem("очном");
        formatСomboBox.addItem("дистанционном");

        cmbLevel.addItem("соответствует");
        cmbLevel.addItem("не соответствует");

        markComboBox.addItem("отлично");
        markComboBox.addItem("хорошо");
        markComboBox.addItem("удовлетворительно");
        markComboBox.addItem("неудовлетворительно");

        hasRemarksComboBox.addItem("Да");
        hasRemarksComboBox.addItem("Нет");
        hasRemarksComboBox.setSelectedIndex(1);

        ButtonGroup group = new ButtonGroup();
        group.add(rbNotA);
        group.add(rbIsA);

        pickerDate.datePicker.setDate(LocalDate.now());
        pickerDate.timePicker.setTime(LocalTime.now());

        setContentPane(mainPanel);

        saveButton.addActionListener(e -> makeProtocol());

        setVisible(true);
    }

    private void makeProtocol() {

        try (XWPFDocument doc = new XWPFDocument(
                Objects.requireNonNull(
                        ProtectionForm.class.getClassLoader().getResourceAsStream("protocol.docx")))        ) {

            Map<String, String> repl = new HashMap<>();

            repl.put("NU","1"); // Номер протокола
            repl.put("PR0N", "000-сп"); // Номер приказа ГЭК
            repl.put("PR0D", "07.07.2222"); // Дата приказа ГЭК
            repl.put("GCF", "Иванов Иван Иванович"); // Председатель ГЭК
            repl.put("PRGCN", "999-1"); // Номер приказа Председатель ГЭК
            repl.put("PRGCD", "02.02.2002"); // Дата приказа Председатель ГЭК
            repl.put("EG", "Иванов Павел Николаевич"); // Секретарь ГЭК
            repl.put("NP","1011-од"); // Номер приказа Секретарь ГЭК
            repl.put("DP","09.10.2020 г."); // Дата приказа Секретарь ГЭК
            repl.put("LR", "X"); // Галочка на русском языке
            repl.put("LE", ""); // Галочка на английском языке
            repl.put("L2",""); // Галочка на русском/английском языке
            repl.put("TM",""); // По теме, предложенной в «Перечне тем ВКР»
            repl.put("TM2"," "); // По инициативной теме, предложенной обучающимся(-ейся)
            repl.put("TM3"," "); // По заявке(-ам) профильной(-ых) организации(-й)-партнера(-ов)
            repl.put("SEC","Зеленкова Е.В"); // Секретарь ГЭК
            repl.put("RR",""); // Представленные в ВКР "к участию в конкурсе на лучшую НИВКР Университета ИТМО"
            repl.put("RS"," "); // Рекомендация к поступлению
            repl.put("CH","Кудрявцев А.Н."); // Подпись Председатель ГЭК
            repl.put("SC","Зеленкова Е.В."); // Подпись Секретарь ГЭК
            repl.put("MO",""); // Другие представленные материалы в ГЭК
            repl.put("TMM","X"); // ВКР подготовлена на на русском языке

            repl.clear();

            repl.put("DATE", pickerDate.datePicker.getDate().format(formatter));
            repl.put("PROD", pickerDate.datePicker.getDate().format(formatter));
            repl.put("STU", student.getFIO()); // ФИО Студента
            repl.put("GR", student.getGroupNumber()); // Номер группы в шапке
            repl.put("FAC", student.getGroup().getProgram().getFaculty().getName()); // Название факультета в шапке
            repl.put("SPEC1", student.getGroup().getProgram().getSpecialization()); // Напрваление подготовки
            repl.put("PROG", student.getGroup().getProgram().getDirection()); // Направленость (профиль) об.программы
            repl.put("SPEC2", student.getGroup().getProgram().getProfile()); // Специализация
            repl.put("THVK", student.getProject().getTheme()); // Тема выпускной квалификационной работы
            repl.put("RKVKR", student.getProject().getSupervisor().getFullDescription()); // ВКР выполнена под руководством
            repl.put("CONS", student.getProject().getConsultants().stream() // ВКР подготовлена Консультант(ы)
                    .map(Consultant::getEmployee)
                    .map(Employee::getFullDescription)
                    .collect(Collectors.joining("; ")));
            repl.put("VKR1", vkrCheckBox.isSelected() ? "X" : ""); // ВКР в эл виде
            repl.put("VKR2", directorCheckBox.isSelected() ? "X" : ""); // ВКР Чертежи/иллюстративный материалы
            repl.put("VKR3", drawingCheckBox.isSelected() ? "X" : ""); //  ВКР Презентация для защиты ВКР
            repl.put("VKR4", presentationCheckBox.isSelected() ? "X" : ""); // ВКР Отзыв руководителя ВКР
            repl.put("VKR5", reviewCheckBox.isSelected() ? "X" : ""); // ВКР Рецензия(-и) на ВКР
            repl.put("VKR6", progressCheckBox.isSelected() ? "X" : ""); // Список достижений из портфолио в ИСУ
            repl.put("LR2", languageСomboBox.getSelectedIndex() == 0 ? "X" : ""); // Защита ВКР на русском языке
            repl.put("OF", formatСomboBox.getSelectedIndex() == 0 ? "X" : ""); // Защита ВКР на очной форме
            repl.put("LE2", languageСomboBox.getSelectedIndex() == 1 ? "X" : ""); // Защита ВКР на английском языке
            repl.put("DF", formatСomboBox.getSelectedIndex() == 1 ? "X" : ""); // Защита ВКР с применением дистанционных технологий
            repl.put("QUESTIONS", questionTextArea.getText()); // Вопросы заданные студенту
            repl.put("REM", hasRemarksComboBox.getSelectedItem().toString()); // Есть ли замечания к защите ВКР выбор НЕТ/ДА
            repl.put("NS", student.getFIO()); // ФИО Студента в Решении ГЭК
            repl.put("O", markComboBox.getSelectedItem().toString().toUpperCase()); // Выбор оценки
            repl.put("SOOT", cmbLevel.getSelectedItem().toString()); // соответсвует/ не сооственует
            repl.put("FAC2", student.getGroup().getProgram().getDirection()); // ГЭК освоешвему программу
            repl.put("FACNAM", student.getGroup().getProgram().getFaculty().getName()); // ГЭК направление подготовки
            repl.put("CV", student.getGroup().getProgram().getQualification()); // ГЭК квалификация
            repl.put("NOOTL", rbNotA.isSelected() ? "X" : ""); // Выбор диплом без отличия
            repl.put("OTL", rbIsA.isSelected() ? "X" : ""); // Выбор диплом с отличием
            repl.put("RS", tfRecommendation.getText()); // Рекомендация к постпулению
            repl.put("SEC", gak.getSecretary().getFIO());

            DocumentWorker.replaceMap(doc, repl);

            try (var fileStream = new FileOutputStream("C:\\Users\\Влад\\Desktop\\DP BD\\out.docx")) {
                doc.write(fileStream);
            }

            DialogUtil.showInfo(null, "Готово!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
