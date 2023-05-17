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
import namtn.dto.Category;
import namtn.utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO {

    public static ArrayList<Category> getAllCategories() throws Exception {
        ArrayList<Category> categories = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select CateID, CateName from Categories";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int cateID = rs.getInt("CateID");
                    String cateName = rs.getString("CateName");
                    Category cate = new Category(cateID, cateName);
                    categories.add(cate);
                }
            }
        }
        cn.close();
        return categories;
    }

    public static boolean insertCategory(String newName) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Insert into Categories(CateName) values (?)";
                PreparedStatement pst = cn.prepareStatement(sql); //Run sql query
                pst.setString(1, newName);
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

    public static boolean updateCategory(int cateID, String newName) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Categories set CateName = ? where CateID = ?";
                PreparedStatement pst = cn.prepareStatement(sql); //Run sql query
                pst.setString(1, newName);
                pst.setInt(2, cateID);
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
