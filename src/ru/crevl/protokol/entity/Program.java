package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.FacultyManager;

import java.sql.SQLException;

public class Program {
    private int idProgram;
    private String direction;
    private String profile;
    private String specialization;
    private String qualification;
    private String status;
    private int facultyIdFaculty;

    public Program(int idProgram, String direction, String profile, String specialization, String qualification, String status, int facultyIdFaculty) {
        this.idProgram = idProgram;
        this.direction = direction;
        this.profile = profile;
        this.specialization = specialization;
        this.qualification = qualification;
        this.status = status;
        this.facultyIdFaculty = facultyIdFaculty;
    }

    public Faculty getFaculty(){
        try {
            return FacultyManager.selectById(getFacultyIdFaculty());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFacultyIdFaculty() {
        return facultyIdFaculty;
    }

    public void setFacultyIdFaculty(int facultyIdFaculty) {
        this.facultyIdFaculty = facultyIdFaculty;
    }

    @Override
    public String toString() {
        return "Program{" +
                "idProgram=" + idProgram +
                ", direction='" + direction + '\'' +
                ", profile='" + profile + '\'' +
                ", specialization='" + specialization + '\'' +
                ", qualification='" + qualification + '\'' +
                ", status='" + status + '\'' +
                ", facultyIdFaculty=" + facultyIdFaculty +
                '}';
    }
}
