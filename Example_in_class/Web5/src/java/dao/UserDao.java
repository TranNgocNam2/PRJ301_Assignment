/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dto.User;
import mylib.DBUtils;

/**
 *
 * @author user
 */
public class UserDao {
    //ham nay de lay 1 dong trong bang tblUser
    //dua vao userid, password
    public static User getUser(String userid, String password) throws Exception{
      User kq=null;
        Connection cn=DBUtils.makeConnection();
        if(cn!=null){
            String s = "select userID,fullName,roleID,password\n"
                    + "from tblUsers\n"
                    + "where  userID=? and password=? COLLATE Latin1_General_CS_AI";
            PreparedStatement pst=cn.prepareStatement(s);
            pst.setString(1,userid);
            pst.setString(2,password);
            ResultSet table=pst.executeQuery();
            if(table!=null){
                while(table.next()){
                    String id=table.getString("userID");
                    String name=table.getString("fullName");
                    String role=table.getString("roleID");
                    String pwd=table.getString("password");
                    kq=new User(id, name, role, pwd);
                }
            }
        }
      
      return kq;
    }
}
