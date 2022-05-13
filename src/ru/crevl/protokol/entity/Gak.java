package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.EmployeeManager;

import java.sql.SQLException;
import java.util.Date;

public class Gak {
    private int idGak;
    private String orderNumber;
    private Date orderDate;
    private Employee chairman;
    private String chairmanOrderNumber;
    private Date chairmanOrderDate;
    private Employee secretary;
    private String secretaryOrderNumber;
    private Date secretaryOrderDate;


    public Gak(int idGak, String orderNumber, Date orderDate, int chairman, String chairmanOrderNumber, Date chairmanOrderDate, int secretary, String secretaryOrderNumber, Date secretaryOrderDate) {
        this.idGak = idGak;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.chairmanOrderNumber = chairmanOrderNumber;
        this.chairmanOrderDate = chairmanOrderDate;
        this.secretaryOrderNumber = secretaryOrderNumber;
        this.secretaryOrderDate = secretaryOrderDate;

        try {
            this.chairman = EmployeeManager.selectById(chairman);
            this.secretary = EmployeeManager.selectById(secretary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdGak() {
        return idGak;
    }

    public void setIdGak(int idGak) {
        this.idGak = idGak;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Employee getChairman() {
        return chairman;
    }

    public void setChairman(Employee chairman) {
        this.chairman = chairman;
    }

    public String getChairmanOrderNumber() {
        return chairmanOrderNumber;
    }

    public void setChairmanOrderNumber(String chairmanOrderNumber) {
        this.chairmanOrderNumber = chairmanOrderNumber;
    }

    public Date getChairmanOrderDate() {
        return chairmanOrderDate;
    }

    public void setChairmanOrderDate(Date chairmanOrderDate) {
        this.chairmanOrderDate = chairmanOrderDate;
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    public String getSecretaryOrderNumber() {
        return secretaryOrderNumber;
    }

    public void setSecretaryOrderNumber(String secretaryOrderNumber) {
        this.secretaryOrderNumber = secretaryOrderNumber;
    }

    public Date getSecretaryOrderDate() {
        return secretaryOrderDate;
    }

    public void setSecretaryOrderDate(Date secretaryOrderDate) {
        this.secretaryOrderDate = secretaryOrderDate;
    }

    @Override
    public String toString() {
        return "Gak{" +
                "idGak=" + idGak +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", chairman=" + chairman +
                ", chairmanOrderNumber='" + chairmanOrderNumber + '\'' +
                ", chairmanOrderDate=" + chairmanOrderDate +
                ", secretary=" + secretary +
                ", secretaryOrderNumber='" + secretaryOrderNumber + '\'' +
                ", secretaryOrderDate=" + secretaryOrderDate +
                '}';
    }
}
