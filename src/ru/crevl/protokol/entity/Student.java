package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.GroupManager;
import ru.crevl.protokol.manager.ProjectManager;

import java.sql.SQLException;

public class Student {
    private int isuId;
    private String surname;
    private String name;
    private String patronymic;
    private String groupNumber;

    public Student(int isuId, String surname, String name, String patronymic, String groupNumber) {
        this.isuId = isuId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupNumber = groupNumber;
    }

    public String getFIO(){
        return getSurname() + " " + getName() + " " + getPatronymic();
    }

    public int getIsuId() {
        return isuId;
    }

    public void setIsuId(int isuId) {
        this.isuId = isuId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Group getGroup() {
        try {
            return GroupManager.selectById(getGroupNumber());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Project getProject(){
        try {
            return ProjectManager.selectByStudentId(getIsuId());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "isuId=" + isuId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                '}';
    }
}
