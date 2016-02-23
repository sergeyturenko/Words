package com.lx.statistic.dao;

import com.lx.statistic.data.FileStatistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey_PC on 21.02.2016.
 */
public class FileStatisticDao extends DBHelper {

    public FileStatisticDao() {
        super();
    }

    public void saveFileStatistic(FileStatistic fileStatistic) {
        try (Connection conn = getConnection()) {
            saveFileStatistic(conn, fileStatistic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFileStatistic(Connection conn, FileStatistic fileStatistic) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (fileStatistic != null) {
            fileStatistic.setId(new CntDao().getCurrKey("FILE_STATISTIC"));
            PreparedStatement ps = conn.prepareStatement(
                    "insert into STATISTIC_FILE (ID, LONG_WORD, SHORT_WORD, LONG_WORD_LENGHT, " +
                                               " SHORT_WORD_LENGHT, ROW_LENGHT, AVERAGE_WORD_LENGHT)" +
                    " values (?, ?, ?, ?, ?, ?, ?); ");
            ps.setInt   (1, fileStatistic.getId               ());
            ps.setString(2, fileStatistic.getLongWord         ());
            ps.setString(3, fileStatistic.getShortWord        ());
            ps.setInt   (4, fileStatistic.getLongWordLenght   ());
            ps.setInt   (5, fileStatistic.getShortWordLenght  ());
            ps.setInt   (6, fileStatistic.getRowLenght        ());
            ps.setInt   (7, fileStatistic.getAverageWordLenght());
            ps.executeUpdate();
        }
    }

    public List<FileStatistic> getAllFileStatistic() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        List<FileStatistic> list = null;
        FileStatistic statistic = null;
        RowStatisticDao rowStatisticDao = null;
        ResultSet rs = null;
        try(Connection conn = getConnection()){
           PreparedStatement ps = conn.prepareStatement("select ID, LONG_WORD, SHORT_WORD, LONG_WORD_LENGHT, " +
                                                        " SHORT_WORD_LENGHT, ROW_LENGHT, AVERAGE_WORD_LENGHT " +
                                                        " from STATISTIC_FILE");
            if((rs = ps.executeQuery())!= null){
                rowStatisticDao = new RowStatisticDao();
                list = new ArrayList<>();
                while (rs.next()){
                    statistic = new FileStatistic();
                    statistic.setId               (rs.getInt   (1));
                    statistic.setLongWord         (rs.getString(2));
                    statistic.setShortWord        (rs.getString(3));
                    statistic.setLongWordLenght   (rs.getInt   (4));
                    statistic.setShortWordLenght  (rs.getInt   (5));
                    statistic.setRowLenght        (rs.getInt   (6));
                    statistic.setAverageWordLenght(rs.getInt   (7));
                    statistic.setRows(rowStatisticDao.getRowsStatisticById(rs.getInt(1)));
                    list.add(statistic);
                }
            }
        }
        return list;
    }
}
