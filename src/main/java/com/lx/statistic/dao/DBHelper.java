package com.lx.statistic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Sergey_PC on 24.02.2016.
 */
public class DBHelper {
    private String uri = "jdbc:h2:~/test";
    private String login = "sa";
    private String password = "";

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(uri, login, password);
        return conn;
    }

    private void initDataBase() throws SQLException, ClassNotFoundException {
        if (!isCreatedTables())
            try (Connection conn = getConnection()) {
                conn.prepareStatement(getScriptCreateTables());
            }
    }

    private boolean isCreatedTables() {
        boolean exists = true;
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select top 1 1 from CNT");
        } catch (Exception e) {
            exists = false;
            e.printStackTrace();
        }
        return exists;
    }

    private String getScriptCreateTables() {
        StringBuilder sb = new StringBuilder();
        sb.append("create table CNT ( ")
                .append("ID                   varchar(25)          not null, ")
                .append("CURR_KEY_NUM         integer              not null, ")
                .append("constraint PK_CNT primary key (ID) ")
                .append(");")

                .append("create table STATISTIC_FILE ( ")
                .append("ID                   integer              not null, ")
                .append("LONG_WORD            nvarchar(150)        null, ")
                .append("SHORT_WORD           nvarchar(150)        null, ")
                .append("LONG_WORD_LENGHT     integer              null, ")
                .append("SHORT_WORD_LENGHT    integer              null, ")
                .append("ROW_LENGHT           integer              null, ")
                .append("AVERAGE_WORD_LENGHT  integer              null, ")
                .append("constraint PK_STATISTIC_FILE primary key (ID)  ")
                .append(");")

                .append("create table STATISTIC_ROW ( ")
                .append("ID                   integer              not null, ")
                .append("EXT_ID               integer              null, ")
                .append("LONG_WORD            nvarchar(150)        null, ")
                .append("SHORT_WORD           nvarchar(150)        null, ")
                .append("LONG_WORD_LENGHT     integer              null, ")
                .append("SHORT_WORD_LENGHT    integer              null, ")
                .append("ROW_LENGHT           integer              null, ")
                .append("AVERAGE_WORD_LENGHT  integer              null, ")
                .append("COUNT_WORD           integer              null, ")
                .append("constraint PK_STATISTIC_ROW primary key (ID) ")
                .append("); ")

                .append("alter table STATISTIC_ROW ")
                .append("   add constraint FK_STATISTI_REFERENCE_STATISTI foreign key (EXT_ID) ")
                .append("      references STATISTIC_FILE (ID) ")
                .append("      on delete restrict on update restrict; ");

        return sb.toString();
    }
}
