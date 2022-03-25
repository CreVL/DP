package ru.crevl.protokol.entity;

public class Employee {
    private int idEmployee;
    private String surname;
    private String name;
    private String patronymic;
    private String post;
    private String degree;
    private String title;
    private int facultyIdFaculty;

    public Employee(int idEmployee, String surname, String name, String patronymic, String post, String degree, String title, int facultyIdFaculty) {
        this.idEmployee = idEmployee;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.post = post;
        this.degree = degree;
        this.title = title;
        this.facultyIdFaculty = facultyIdFaculty;
    }
    public String getFIO(){
        return getSurname() + " " + getName() + " " + getPatronymic();
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFacultyIdFaculty() {
        return facultyIdFaculty;
    }

    public void setFacultyIdFaculty(int facultyIdFaculty) {
        this.facultyIdFaculty = facultyIdFaculty;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", post='" + post + '\'' +
                ", degree='" + degree + '\'' +
                ", title='" + title + '\'' +
                ", facultyIdFaculty=" + facultyIdFaculty +
                '}';
    }
}
