package com.example.cypher.projinselo.model;

import android.os.AsyncTask;
import com.example.cypher.projinselo.Activity2;

import java.sql.*;

public class DB_connection extends AsyncTask<String,Void,Void> {

    public String dburl, user, password;
    public static Connection Conn;
    public static String med_code;
    public static String med_name;
    public static String comp;
    public static String Salt = "";

    public String getMedCode(String MName) {
        String query = "";

        try {
            query = "SELECT Medicine_Code FROM medicine WHERE Common_Name =?";
            PreparedStatement statement = Conn.prepareStatement(query);
            statement.setString(1, MName);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                med_code = rs.getString(1);
                System.out.println("InnerTesting:" + rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Error in MED CODE::::::::" + e);
        }
        System.out.println("Med code::::::" + med_code);

        return med_code;
    }

    public String getComposition(String MCode) {
        String query;

        try {
            query = "SELECT Comp FROM composition WHERE Medicine_Code = ?";
            PreparedStatement statement = Conn.prepareStatement(query);
            statement.setString(1, MCode);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                comp = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("Error in Composition:::::" + e);
        }

        return comp;
    }

    public String getSalt(String Comp) {

        String[] salt_comp = Comp.split(",");

        try {
            PreparedStatement statement = Conn.prepareStatement("SELECT Salt_Name FROM salt WHERE Salt_Code = ?");
            for (int i = 0; i < salt_comp.length; i++) {
                String salt_code[] = salt_comp[i].split(":");

                statement.setString(1, salt_code[0]);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    String salt_name = rs.getString(1);
                    Salt += salt_name;
                }
                Salt += ":" + salt_code[1];

                if (i < salt_comp.length - 1) {
                    Salt += ",";
                }

            }

        } catch (Exception e) {
            System.out.println("Error in Get salt::::: " + e);
        }

        return Salt;
    }

    @Override
    protected Void doInBackground(String... strings) {


        dburl = "jdbc:mysql://sql12.freemysqlhosting.net/sql12256759";
        user = "sql12256759";
        password = "XWd9zvUiXg";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Conn = DriverManager.getConnection(dburl, user, password);
            System.out.println("Connection Successful to Database: " + dburl);

        } catch (Exception e) {
            System.out.println("ERROR IN DO in backgrounnd :- " + e);
        }
        med_name = strings[0];
        med_code = getMedCode(strings[0]);
        comp = getComposition(med_code);
        Salt = getSalt(comp);
        return null;

    }

    protected void onPostExecute(Void result) {
        if (med_name != null)
        {
            Activity2.med_details.setText(med_name);
            Activity2.salt_details.setText(Salt);
        }
        else
        {
            Activity2.med_details.setText(med_name);
            Activity2.salt_details.setText("No Medicine Found");

        }
    }
}
