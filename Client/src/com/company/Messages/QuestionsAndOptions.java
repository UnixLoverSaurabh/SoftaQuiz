package com.company.Messages;

import java.io.Serializable;
import java.util.List;

public class QuestionsAndOptions implements Serializable {
    private List<String[]> rowList;

    public QuestionsAndOptions(List<String[]> rowList) {
        this.rowList = rowList;
    }

    public List<String[]> getRowList() {
        return rowList;
    }

    public void setRowList(List<String[]> rowList) {
        this.rowList = rowList;
    }
}

