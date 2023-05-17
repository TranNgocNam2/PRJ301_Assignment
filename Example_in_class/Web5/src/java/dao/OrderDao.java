/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import mylib.DBUtils;

/**
 *
 * @author user
 */
public class OrderDao {

    //ham nay de chen 1 record vao bang order vaf detailorder
    public static int insertOrder(String userid, HashMap<String, Integer> cart) throws Exception {
        //b3: chen 1 recored vao bang order
        //b4: lay orderid cua b3
        //b5: duyet cart de chen orderdetail
        int kq = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            cn.setAutoCommit(false);
            String sql = "insert Orders(createdate,status,userid) values(?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            Date d = new Date(System.currentTimeMillis());
            pst.setDate(1, d);
            pst.setInt(2, 1); //1<-> pendding
            pst.setString(3, userid);
            kq = pst.executeUpdate();
            if (kq > 0) {
                sql = "select top 1 id\n"
                        + "from Orders\n"
                        + "order by id desc";
                pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int orderid = rs.getInt("id");
                    if (cart != null && cart.size() > 0) {
                        for (String carid : cart.keySet()) {
                            int quantity = cart.get(carid);
                            sql = "insert OrderDetails(orderid,carid,quantity) values(?,?,?)";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, orderid);
                            pst.setString(2, carid);
                            pst.setInt(3, quantity);
                            kq = pst.executeUpdate();
                        }
                    }
                }
            }
            cn.commit();
            cn.setAutoCommit(true);
            cn.close();
        }
        return kq;
    }

    public static ArrayList<Order> getOrders(String userid) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select id, createdate,status, userid from Orders where userid=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int orderid = rs.getInt("id");
                Date d = rs.getDate("createdate");
                int status = rs.getInt("status");
                Order o = new Order(orderid, d, status, userid);
                list.add(o);
            }
        }
        cn.close();
        return list;
    }

}
