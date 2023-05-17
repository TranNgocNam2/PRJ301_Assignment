/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author user
 */
public class CarDAO {
    public static ArrayList<Car> getCars(String name) throws Exception{
        ArrayList<Car> list=new ArrayList<>();// chua dap an
        Connection cn=DBUtils.makeConnection();
        if(cn!=null){
            String sql = "select id,name,description,price,speed,status\n"
                    + "from dbo.tblCars\n"
                    + "where status=1 and name like ?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, "%" + name+"%");
            ResultSet rs=pst.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    String id=rs.getString("id");
                    String nameCar=rs.getString("name");
                    String des=rs.getString("description");
                    float price=rs.getFloat("price");
                    int speed=rs.getInt("speed");
                    boolean status=rs.getBoolean("status");
                    Car c=new Car(id, nameCar, des, price, speed, status);
                    list.add(c);
                }
            }
            cn.close();
        }
        return list;
    }
    public static Car getCar(String id) throws Exception
    {   
        Car c=null;
        Connection cn=DBUtils.makeConnection();
        if(cn!=null){
            String sql = "select id,name,description,price,speed,status\n"
                    + "from dbo.tblCars\n"
                    + "where status=1 and id = ?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            if(rs!=null){
                while(rs.next()){
                   // String id=rs.getString("id");
                    String nameCar=rs.getString("name");
                    String des=rs.getString("description");
                    float price=rs.getFloat("price");
                    int speed=rs.getInt("speed");
                    boolean status=rs.getBoolean("status");
                    c=new Car(id, nameCar, des, price, speed, status);
                    
                }
            }
            cn.close();
        }
        return c;
    }
}
