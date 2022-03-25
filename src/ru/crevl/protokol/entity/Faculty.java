package ru.crevl.protokol.entity;

public class Faculty {
    private int idFaculty;
    private String name;
    private String address;
    private int director;

    public Faculty(int idFaculty, String name, String address, int director) {
        this.idFaculty = idFaculty;
        this.name = name;
        this.address = address;
        this.director = director;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "idFaculty=" + idFaculty +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", director=" + director +
                '}';
    }
}
