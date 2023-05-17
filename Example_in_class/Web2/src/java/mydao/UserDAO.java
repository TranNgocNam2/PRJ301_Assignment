/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydao;

import DTO.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mylib.DBUtils;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

    //Hàm này để lấy 1 dòng trong bảng tblUser
    //vào userid(username) và password
    public static User getUser(String userid, String password) throws Exception {
        User result = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String s = "select userID,fullName,roleID,password from tblUsers where userID=? and password = ? COLLATE Latin1_General_CS_AI";
            // bật tính năng phân biệt hoa thường (COLLATE Latin1_General_CS_AI)
            PreparedStatement pst = cn.prepareStatement(s);
            pst.setString(1, userid);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String name = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String pwd = rs.getString("password");
                    result = new User(id, name, roleID, password);
                }
            }
            cn.close();
        }
        return result;
    }

// ham nay de them 1 dong moi vao table user
    // tra ve 1/0 nen dung int
    public static int insertUser(String us, String fullname, String role, String password) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String s = "INSERT tblUsers(userID,fullName,roleID,password)\n"
                    + "values (?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(s);
            pst.setString(1, us);
            pst.setString(2, fullname);
            pst.setString(3, role);
            pst.setString(4, password);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }

    public static boolean getUser(String userID) throws Exception {
        boolean ketqua = false;
        // buoc 1 tao connection
        Connection cn = DBUtils.makeConnection();
        // buoc 2 viet query
        if (cn != null) {
            String s = "SELECT userID, fullName, roleID, password\n"
                    + "FROM tblUsers\n"
                    + "WHERE userID=? ";
            PreparedStatement pst = cn.prepareStatement(s); //api prepared
            // setter cho ?
            pst.setString(1, userID);
            // exe tra ve 1 table trong java(result set) 
            // su dung driver
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()) { // co rs va co du lieu khac cache
                //neu co tra ve rs thi` la co user
                ketqua = true;
            }
            cn.close();
        }
        return ketqua;
    }
}
