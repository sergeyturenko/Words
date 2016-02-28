package com.lx.statistic.data;


/**
 * Created by turenko on 19.02.2016.
 */

public class RowStatistic {

    private Integer id               ;
    private Integer extId            ;
    private String  longWord         ;
    private String  shortWord        ;
    private Integer longWordLenght   ;
    private Integer shortWordLenght  ;
    private Integer rowLenght        ;
    private Integer averageWordLenght;
    private Integer countWords       ;

    public RowStatistic() {}

    public RowStatistic(String longWord, String shortWord, Integer longWordLenght, Integer shortWordLenght, Integer rowLenght, Integer averageWordLenght, Integer countWords) {
        this.longWord          = longWord         ;
        this.shortWord         = shortWord        ;
        this.longWordLenght    = longWordLenght   ;
        this.shortWordLenght   = shortWordLenght  ;
        this.rowLenght         = rowLenght        ;
        this.averageWordLenght = averageWordLenght;
        this.countWords        = countWords       ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
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

    public Integer getRowLenght() {
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

    public Integer getCountWords() {
        return countWords;
    }

    public void setCountWords(Integer countWords) {
        this.countWords = countWords;
    }

    @Override
    public String toString() {
        return "RowStatistic{" +
                "id=" + id +
                ", extId=" + extId +
                ", longWord='" + longWord + '\'' +
                ", shortWord='" + shortWord + '\'' +
                ", longWordLenght=" + longWordLenght +
                ", shortWordLenght=" + shortWordLenght +
                ", rowLenght=" + rowLenght +
                ", averageWordLenght=" + averageWordLenght +
                ", countWords=" + countWords +
                "}\n";
    }
}
