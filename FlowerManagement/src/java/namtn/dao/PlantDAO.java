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
import namtn.dto.Plant;
import namtn.utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws Exception {
        ArrayList<Plant> plants = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID = Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "where Plants.PName like ?";
                } else {
                    sql = sql + "where CateName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String des = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateID = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Plant p = new Plant(id, name, price, imgpath, des, status, cateID, cateName);
                        plants.add(p);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plants;
    }

    public static Plant getPlantByID(int id) throws Exception {
        Plant plant = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName"
                        + " from Plants, Categories where Plants.CateID = Categories.CateID and PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    id = rs.getInt("PID");
                    String pName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgpath = rs.getString("imgPath");
                    String des = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateID = rs.getInt("CateID");
                    String cateName = rs.getString("CateName");
                    plant = new Plant(id, pName, price, imgpath, des, status, cateID, cateName);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plant;
    }

    public static boolean UpdateStatusPlant(int plantid, int status) throws Exception {
        boolean isSuccess = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Plants set status = ? where PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, plantid);
                if(pst.executeUpdate() == 1){
                    isSuccess = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static ArrayList<Plant> getAllPlant() throws Exception {
        ArrayList<Plant> plants = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID, PName, price, imgPath, description, status, CateID "
                        + "from Plants";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String pName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String des = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateID = rs.getInt("CateID");
                        Plant plant = new Plant(id, pName, price, imgpath, des, status, cateID, "");
                        plants.add(plant);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plants;
    }
}
