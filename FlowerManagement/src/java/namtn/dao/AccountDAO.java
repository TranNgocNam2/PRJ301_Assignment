/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import namtn.dto.Account;
import namtn.utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class AccountDAO {

    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role from Accounts "
                        + "where status=1 and email = ? and  password = ? COLLATE Latin1_General_CS_AI";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, fullname, Status, Phone, Role);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public static boolean updateToken(String token, String email) {
        Connection cn = null;
        boolean isSuccess = false;
        try {
            //Get database connection
            cn = DBUtils.makeConnection();
            if (cn != null) {
                //Prepare SQL statement 
                String sql = "update Accounts set token = ? where email =?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                //Check if update was successfull
                if (pst.executeUpdate() == 1) {
                    isSuccess = true;
                }
                //Close database connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static Account getAccountByToken(String token) {
        Account acc = null;
        Connection cn = null;
        try {
            //Get database connection
            cn = DBUtils.makeConnection();
            if (cn != null) {
                //Prepare SQL statement 
                String sql = "select accID, email, password, fullname, phone, status, role from Accounts where token = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    acc.setAccID(rs.getInt("accID"));
                    acc.setEmail(rs.getString("email"));
                    acc.setFullname(rs.getString("fullname"));
                    acc.setPhone(rs.getString("phone"));
                    acc.setStatus(rs.getInt("status"));
                    acc.setRole(rs.getInt("role"));
                }
                //Close database connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acc;
    }

    public static boolean deleteToken(String token) {
        Connection cn = null;
        boolean isSuccess = false;
        try {
            //Get database connection
            cn = DBUtils.makeConnection();
            if (cn != null) {
                //Prepare SQL statement 
                String sql = "Update Accounts set token = null where token = " + "\'" + token + "\'";
                PreparedStatement pst = cn.prepareStatement(sql);
                //Check if update was successfull
                if (pst.executeUpdate() == 1) {
                    isSuccess = true;
                }
                //Close database connection
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static ArrayList<Account> getAccounts(String txtsearch) throws Exception {
        ArrayList<Account> accounts = new ArrayList<>();
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && txtsearch != null) {
                String sql = "select accID, email, password, fullname, phone, status, role from Accounts "
                        + "where fullname like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + txtsearch + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int accID = rs.getInt("accID");
                        String email = rs.getString("email");
                        String name = rs.getString("fullname");
                        String phone = rs.getString("phone");
                        int status = rs.getInt("status");
                        int role = rs.getInt("role");
                        acc = new Account(accID, email, "", name, status, phone, role);
                        accounts.add(acc);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static boolean updateAccountStatus(String email, int status) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection(); //Connect to database
            if (cn != null) {
                String sql = "Update Accounts set status = ? where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql); //Run sql query
                pst.setInt(1, status); //Set new status
                pst.setString(2, email);
                if (pst.executeUpdate() == 1) { //If it update successfully, return true
                    result = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Update Accounts set password = ? COLLATE Latin1_General_CS_AI, fullname = ?, phone = ? where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql); //Run sql query
                pst.setString(1, newPassword); //Set new password
                pst.setString(2, newFullname); //Set new fullname
                pst.setString(3, newPhone); //Set new phone
                pst.setString(4, email);//Use email to identify Account
                if (pst.executeUpdate() == 1) { //If it update successfully, return true
                    result = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus, int newRole) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Insert into Accounts(email, password, fullname, phone, status, role) values(?,? COLLATE Latin1_General_CS_AI,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql); //Run sql query
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newSatus);
                pst.setInt(6, 0);
                if (pst.executeUpdate() == 1) {
                    //If it update successfully, return true
                    result = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
