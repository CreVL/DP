package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.ConsultantManager;
import ru.crevl.protokol.manager.EmployeeManager;

import java.sql.SQLException;
import java.util.List;

public class Project {
    private int idProject;
    private String theme;
    private int employeeIdEmployee;
    private int studentIsuId;

    public Project(int idProject, String theme, int employeeIdEmployee, int studentIsuId) {
        this.idProject = idProject;
        this.theme = theme;
        this.employeeIdEmployee = employeeIdEmployee;
        this.studentIsuId = studentIsuId;
    }

    public Employee getSupervisor(){
        try {
            return EmployeeManager.selectById(getEmployeeIdEmployee());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Consultant> getConsultants(){
        try {
            return ConsultantManager.selectByProjectIdProject(getIdProject());
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getEmployeeIdEmployee() {
        return employeeIdEmployee;
    }

    public void setEmployeeIdEmployee(int employeeIdEmployee) {
        this.employeeIdEmployee = employeeIdEmployee;
    }

    public int getStudentIsuId() {
        return studentIsuId;
    }

    public void setStudentIsuId(int studentIsuId) {
        this.studentIsuId = studentIsuId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idProject=" + idProject +
                ", theme='" + theme + '\'' +
                ", employeeIdEmployee=" + employeeIdEmployee +
                ", studentIsuId=" + studentIsuId +
                '}';
    }
}
