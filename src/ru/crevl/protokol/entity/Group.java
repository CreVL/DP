package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.ProgramManager;

import java.sql.SQLException;

public class Group {
    private String number;
    private String course;
    private int programIdProgram;

    public Group(String number, String course, int programIdProgram) {
        this.number = number;
        this.course = course;
        this.programIdProgram = programIdProgram;
    }
    public Program getProgram(){
        try {
            return ProgramManager.selectById(getProgramIdProgram());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getProgramIdProgram() {
        return programIdProgram;
    }

    public void setProgramIdProgram(int programIdProgram) {
        this.programIdProgram = programIdProgram;
    }

    @Override
    public String toString() {
        return "Group{" +
                "number='" + number + '\'' +
                ", course='" + course + '\'' +
                ", programIdProgram=" + programIdProgram +
                '}';
    }
}
