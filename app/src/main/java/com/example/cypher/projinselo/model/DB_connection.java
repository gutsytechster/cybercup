package com.example.cypher.projinselo.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.cypher.projinselo.Activity2;

import java.sql.*;
import java.util.ArrayList;

public class DB_connection extends AsyncTask<String,Void,Void> {

    public String dburl, user, password;
    public static Connection Conn;
    public static String med_code;
    public static String med_name;
    public static String comp;
    public static String conflict_med;
    public static String Salt = "";
    public static String conflict_med_name = "sdfgh";

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

    public String getConflictMedNames(String Mcode) {
        String query = "";

        init_med_name();

        try{
            query = "SELECT Conflict_Med FROM Conflict_Meds WHERE Conflict_Med LIKE ?;";
            PreparedStatement statement1 = Conn.prepareStatement(query);Log.v("Stranger", "1");
            statement1.setString(1, "%" + Mcode + "%");Log.v("Stranger", "1");

            Log.v("stranger", statement1.toString());

            ResultSet rs1 = statement1.executeQuery();Log.v("Stranger", "1");
            while (rs1.next()) {
                conflict_med = rs1.getString(1);Log.v("Stranger", "1");
            }
            String[] conflict_medicine_codes = conflict_med.split(",");Log.v("Stranger", "1");
            PreparedStatement statement2 = Conn.prepareStatement("SELECT Common_Name FROM medicine WHERE Medicine_Code = ?");Log.v("Stranger", "1");
            conflict_med_name = "Conflicting Medicines: ";Log.v("Stranger", "1");
            for (int i = 0; i < conflict_medicine_codes.length; i++){
                statement2.setString(1, conflict_medicine_codes[i]);Log.v("Stranger", "1");
                ResultSet rs2 = statement2.executeQuery();Log.v("Stranger", "1");
                while(rs2.next()){
                    String Medicine_name = rs2.getString(1);Log.v("Stranger", "1");
                    conflict_med_name += Medicine_name;Log.v("Stranger", "1");
                }

                if( i < conflict_medicine_codes.length){
                    conflict_med_name += ",";Log.v("Stranger", "1");
                }

            }
        } catch (Exception e) {
            System.out.println("Error in composition::::" + e);
        }
        return conflict_med_name;
    }

    private void init_med_name() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("");
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


        dburl = "jdbc:mysql://sql12.freemysqlhosting.net/sql12273219";
        user = "sql12273219";
        password = "QgXYMTZXzl";

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
        conflict_med = getConflictMedNames(med_code);
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
