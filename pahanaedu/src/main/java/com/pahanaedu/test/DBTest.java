package com.pahanaedu.test;

import com.pahanaedu.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT NOW()")) {

            if (rs.next()) {
                System.out.println("DB connected! Current time: " + rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
