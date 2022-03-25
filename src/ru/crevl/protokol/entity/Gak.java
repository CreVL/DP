package ru.crevl.protokol.entity;

import ru.crevl.protokol.manager.EmployeeManager;

import java.sql.SQLException;
import java.util.Date;

public class Gak {
    private int idGak;
    private String orderNumber;
    private String orderDate;
    private String chairman;
    private String secretary;

    public Gak(int idGak, String orderNumber, String orderDate, int chairman, int secretary) {
        this.idGak = idGak;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        try {
            this.chairman = EmployeeManager.selectById(chairman).getFIO();
            this.secretary = EmployeeManager.selectById(secretary).getFIO();
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return "Gak{" +
                "idGak=" + idGak +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", chairman=" + chairman +
                ", secretary=" + secretary +
                '}';
    }
}
