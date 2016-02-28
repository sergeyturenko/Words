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

    public void saveFileAndRowStatistics(FileStatistic fileStatistic) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        RowStatisticDao rowStatisticDao = new RowStatisticDao();
        try {
            conn.setAutoCommit(false);
            saveFileStatistic(conn, fileStatistic);
            rowStatisticDao.saveRowsStatistic(conn, fileStatistic.getId(), fileStatistic.getRows());
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        }finally {
            conn.setAutoCommit(true);
            conn.close();
        }

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
                    "insert into FILESTATISTIC (ID, FILENAME, LONGWORD, SHORTWORD, LONGWORDLENGHT, " +
                            " SHORTWORDLENGHT, ROWLENGHT, AVERAGEWORDLENGHT, COUNTWORDS)" +
                            " values (?, ?, ?, ?, ?, ?, ?, ?, ?); ");
            ps.setInt(1, fileStatistic.getId());
            ps.setString(2, fileStatistic.getFileName());
            ps.setString(3, fileStatistic.getLongWord());
            ps.setString(4, fileStatistic.getShortWord());
            ps.setInt(5, fileStatistic.getLongWordLenght());
            ps.setInt(6, fileStatistic.getShortWordLenght());
            ps.setInt(7, fileStatistic.getRowLenght());
            ps.setInt(8, fileStatistic.getAverageWordLenght());
            ps.setInt(9, fileStatistic.getCountWords());
            ps.executeUpdate();
        }
    }

    public List<FileStatistic> getAllFileStatistic() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        List<FileStatistic> list = null;
        FileStatistic statistic = null;
        RowStatisticDao rowStatisticDao = null;
        ResultSet rs = null;
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select ID, FILENAME, LONGWORD, SHORTWORD, LONGWORDLENGHT, " +
                    " SHORTWORDLENGHT, ROWLENGHT, AVERAGEWORDLENGHT, COUNTWORDS " +
                    " from FILESTATISTIC");
            if ((rs = ps.executeQuery()) != null) {
                rowStatisticDao = new RowStatisticDao();
                list = new ArrayList<>();
                while (rs.next()) {
                    statistic = new FileStatistic();
                    statistic.setId(rs.getInt(1));
                    statistic.setFileName(rs.getString(2));
                    statistic.setLongWord(rs.getString(3));
                    statistic.setShortWord(rs.getString(4));
                    statistic.setLongWordLenght(rs.getInt(5));
                    statistic.setShortWordLenght(rs.getInt(6));
                    statistic.setRowLenght(rs.getInt(7));
                    statistic.setAverageWordLenght(rs.getInt(8));
                    statistic.setCountWords(rs.getInt(9));
                    statistic.setRows(rowStatisticDao.getRowsStatisticById(rs.getInt(1)));
                    list.add(statistic);
                }
            }
        }
        return list;
    }
}
