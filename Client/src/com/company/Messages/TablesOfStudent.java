package com.company.Messages;

import java.io.Serializable;
import java.util.List;

public class TablesOfStudent implements Serializable {
    private String username;
    private List<String[]> data1;
    private List<String[]> data2;

    public TablesOfStudent(String Username, List<String[]> data1, List<String[]> data2) {
        this.username = Username;
        this.data1 = data1;
        this.data2 = data2;
    }

    public String getUsername() {
        return username;
    }

    public List<String[]> getData1() {
        return data1;
    }

    public List<String[]> getData2() {
        return data2;
    }
}

