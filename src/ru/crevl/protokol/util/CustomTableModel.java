package ru.crevl.protokol.util;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;

public class CustomTableModel<T> extends AbstractTableModel {
    protected List<T> list;
    protected Class<T> cls;
    protected String[] names;
    protected Predicate<T> filter1;
    protected Comparator<T> sort1;

    public CustomTableModel(List<T> list, Class<T> cls, String[] names) {
        this.list = list;
        this.cls = cls;
        this.names = names;
        fireTableDataChanged();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public List<T> getFilteredList(){
        List<T> rows = new ArrayList<>(getList());
        if (filter1 != null){
            rows.removeIf(e -> !filter1.test(e));
        }
        if (sort1 !=null){
            Collections.sort(rows,sort1);
        }

        return rows;
    }

    public List<T>  getPagedList(){
        return getFilteredList();
    }

    public Predicate<T> getFilter1() {
        return filter1;
    }

    public void setFilter1(Predicate<T> filter1) {
        this.filter1 = filter1;
        fireTableDataChanged();
    }

    public void setSort1(Comparator<T> sort1) {
        this.sort1 = sort1;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return names[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return cls.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public int getRowCount() {
        return getPagedList().size();
    }

    @Override
    public int getColumnCount() {
        return cls.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T ri = getPagedList().get(rowIndex);
        Field ci = cls.getDeclaredFields()[columnIndex];
        ci.setAccessible(true);
        try {
            return ci.get(ri);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
