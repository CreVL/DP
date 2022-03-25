package ru.crevl.protokol.entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Graduation {
    private String date;
    private String time;
    private String room;
    private int mark;
    private int gakIdGak;
    private int projectIdProject;

    public Graduation(String date, String time, String room, int mark, int gakIdGak, int projectIdProject) {
        this.date = date;
        this.time = time;
        this.room = room;
        this.mark = mark;
        this.gakIdGak = gakIdGak;
        this.projectIdProject = projectIdProject;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getGakIdGak() {
        return gakIdGak;
    }

    public void setGakIdGak(int gakIdGak) {
        this.gakIdGak = gakIdGak;
    }

    public int getProjectIdProject() {
        return projectIdProject;
    }

    public void setProjectIdProject(int projectIdProject) {
        this.projectIdProject = projectIdProject;
    }

    @Override
    public String toString() {
        return "Graduation{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", room='" + room + '\'' +
                ", mark=" + mark +
                ", gakIdGak=" + gakIdGak +
                ", projectIdProject=" + projectIdProject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graduation that = (Graduation) o;
        return mark == that.mark && gakIdGak == that.gakIdGak && projectIdProject == that.projectIdProject && Objects.equals(date, that.date) && Objects.equals(time, that.time) && Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, room, mark, gakIdGak, projectIdProject);
    }

}
