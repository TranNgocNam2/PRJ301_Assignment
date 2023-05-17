/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class Tester {

    public static void main(String[] args) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String s = "select userID,fullName,roleID,password from tblUsers";
                Statement st = cn.createStatement();
                ResultSet rs =st.executeQuery(s);
                if(rs!=null){
                    while(rs.next()){
                        String id = rs.getString("userID");
                        String name = rs.getString("fullName");
                        String roleID = rs.getString("roleID");
                        String password = rs.getString("password");
                        System.out.println(id + ", " + name + ", " + roleID + ", " + password);
                    }
                    cn.close();
                }
            }
        } catch (Exception e) {

        }
    }
}
