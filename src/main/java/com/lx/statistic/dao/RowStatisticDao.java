package com.lx.statistic.dao;

import com.lx.statistic.data.RowStatistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey_PC on 22.02.2016.
 */
public class RowStatisticDao extends DBHelper{
    public void saveRowsStatistic(int extId, RowStatistic rowStatistic) {
        try (Connection conn = getConnection()) {
            saveRowsStatistic(conn, extId, rowStatistic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRowsStatistic(Connection conn, int extId, RowStatistic rowStatistic) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (rowStatistic != null) {
            int key = new CntDao().getCurrKey("ROW_STATISTIC");
            PreparedStatement ps = conn.prepareStatement(
                    "insert into STATISTIC_ROW (ID, EXT_ID, LONG_WORD, SHORT_WORD, LONG_WORD_LENGHT, " +
                            " SHORT_WORD_LENGHT, ROW_LENGHT, AVERAGE_WORD_LENGHT, COUNT_WORD)" +
                            " values (?, ?, ?, ?, ?, ?, ?, ?, ?); ");
            ps.setInt   (1, key);
            ps.setInt   (2, extId                              );
            ps.setString(3, rowStatistic.getLongWord         ());
            ps.setString(4, rowStatistic.getShortWord        ());
            ps.setInt   (5, rowStatistic.getLongWordLenght   ());
            ps.setInt   (6, rowStatistic.getShortWordLenght  ());
            ps.setInt   (7, rowStatistic.getRowLenght        ());
            ps.setInt   (8, rowStatistic.getAverageWordLenght());
            ps.setInt   (9, rowStatistic.getCountWords       ());
            ps.executeUpdate();
        }
    }
    public List<RowStatistic> getRowsStatisticById(int extId) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        List<RowStatistic> list = null;
        RowStatistic statistic = null;
        ResultSet rs = null;
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("select ID, EXT_ID, LONG_WORD, SHORT_WORD, LONG_WORD_LENGHT, " +
                    " SHORT_WORD_LENGHT, ROW_LENGHT, AVERAGE_WORD_LENGHT, COUNT_WORD " +
                    " from STATISTIC_ROW where EXT_ID = ?");
            ps.setInt(1, extId);
            if((rs = ps.executeQuery())!= null){
                list = new ArrayList<>();
                while (rs.next()){
                    statistic = new RowStatistic();
                    statistic.setId               (rs.getInt   (1));
                    statistic.setExtId            (rs.getInt   (2));
                    statistic.setLongWord         (rs.getString(3));
                    statistic.setShortWord        (rs.getString(4));
                    statistic.setLongWordLenght   (rs.getInt   (5));
                    statistic.setShortWordLenght  (rs.getInt   (6));
                    statistic.setRowLenght        (rs.getInt   (7));
                    statistic.setAverageWordLenght(rs.getInt   (8));
                    statistic.setCountWords       (rs.getInt   (9));
                    list.add(statistic);
                }
            }
        }
        return list;
    }
}
