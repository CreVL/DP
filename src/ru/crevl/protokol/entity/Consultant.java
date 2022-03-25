package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.EmployeeManager;

import java.sql.SQLException;

public class Consultant {
    private int idConsultant;
    private int employeeIdEmployee;
    private int projectIdProject;

    public Consultant(int idConsultant, int employeeIdEmployee, int projectIdProject) {
        this.idConsultant = idConsultant;
        this.employeeIdEmployee = employeeIdEmployee;
        this.projectIdProject = projectIdProject;
    }

    public Employee getEmployee(){
        try {
            return EmployeeManager.selectById(getEmployeeIdEmployee());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getIdConsultant() {
        return idConsultant;
    }

    public void setIdConsultant(int idConsultant) {
        this.idConsultant = idConsultant;
    }

    public int getEmployeeIdEmployee() {
        return employeeIdEmployee;
    }

    public void setEmployeeIdEmployee(int employeeIdEmployee) {
        this.employeeIdEmployee = employeeIdEmployee;
    }

    public int getProjectIdProject() {
        return projectIdProject;
    }

    public void setProjectIdProject(int projectIdProject) {
        this.projectIdProject = projectIdProject;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "idConsultant=" + idConsultant +
                ", employeeIdEmployee=" + employeeIdEmployee +
                ", projectIdProject=" + projectIdProject +
                '}';
    }
}
