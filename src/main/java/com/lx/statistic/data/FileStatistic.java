package com.lx.statistic.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by turenko on 19.02.2016.
 */

public class FileStatistic {
    private Integer id               ;
    private String  longWord         ;
    private String  shortWord        ;
    private int     longWordLenght   ;
    private int     shortWordLenght  ;
    private int     rowLenght        ;
    private int     averageWordLenght;
    private List<RowStatistic> rows = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FileStatistic() {
        rows = new ArrayList();
    }

    public FileStatistic(List<RowStatistic> rows) {
        this.rows = rows;
    }

    public String getLongWord() {
        return longWord;
    }

    public void setLongWord(String longWord) {
        this.longWord = longWord;
    }

    public String getShortWord() {
        return shortWord;
    }

    public void setShortWord(String shortWord) {
        this.shortWord = shortWord;
    }

    public Integer getLongWordLenght() {
        return longWordLenght;
    }

    public void setLongWordLenght(Integer longWordLenght) {
        this.longWordLenght = longWordLenght;
    }

    public Integer getShortWordLenght() {
        return shortWordLenght;
    }

    public void setShortWordLenght(Integer shortWordLenght) {
        this.shortWordLenght = shortWordLenght;
    }

    public int getRowLenght() {
        return rowLenght;
    }

    public void setRowLenght(Integer rowLenght) {
        this.rowLenght = rowLenght;
    }

    public Integer getAverageWordLenght() {
        return averageWordLenght;
    }

    public void setAverageWordLenght(Integer averageWordLenght) {
        this.averageWordLenght = averageWordLenght;
    }

    public List<RowStatistic> getRows() {
        return rows;
    }

    public void setRows(List<RowStatistic> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "FileStatistic{" +
                "id=" + id +
                ", longWord='" + longWord + '\'' +
                ", shortWord='" + shortWord + '\'' +
                ", longWordLenght=" + longWordLenght +
                ", shortWordLenght=" + shortWordLenght +
                ", rowLenght=" + rowLenght +
                ", averageWordLenght=" + averageWordLenght +
                ", rows=" + rows +
                '}';
    }
}
