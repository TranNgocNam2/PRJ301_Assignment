/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import namtn.dto.Order;
import namtn.dto.OrderDetail;
import namtn.utils.DBUtils;

/**
 *
 * @author ADMIN
 */
public class OrderDAO {

    public static ArrayList<Order> getAllOrders() throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Select OrderID, OrdDate, shipdate, Orders.status, AccID"
                    + " from Orders";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipDate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order o = new Order(orderID, ordDate, shipDate, status, accID);
                    orders.add(o);
                }
            }
        }
        cn.close();
        return orders;
    }

    public static ArrayList<Order> getOrdersByStatus(String email, int option) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Select OrderID, OrdDate, shipdate, Orders.status, Orders.AccID "
                    + "from Orders join Accounts on Accounts.accID = Orders.AccID "
                    + "where Accounts.email = ?";
            switch (option) {
                //Print orders which is in process
                case 1:
                    sql = sql + " and Orders.status = 1";
                    break;
                //Completed
                case 2:
                    sql = sql + " and Orders.status = 2";
                    break;
                //Canceled
                case 3:
                    sql = sql + " and Orders.status = 3";
                    break;
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipDate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order o = new Order(orderID, ordDate, shipDate, status, accID);
                    orders.add(o);
                }
            }
        }
        cn.close();
        return orders;
    }

    public static ArrayList<Order> getAllOrdersByEmail(String email) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Select OrderID, OrdDate, shipdate, Orders.status, Orders.AccID "
                    + "from Orders "
                    + "join Accounts on Accounts.accID = Orders.AccID "
                    + "where Accounts.email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipDate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order o = new Order(orderID, ordDate, shipDate, status, accID);
                    orders.add(o);
                }
            }
        }
        cn.close();
        return orders;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws Exception {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderID, PID, PName, price,imgPath ,quantity "
                        + "from OrderDetails o, Plants p "
                        + "where o.OrderID = ? and o.FID = p.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int plantID = rs.getInt("PID");
                        String plantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderdetail = new OrderDetail(detailID, orderID, plantID, plantName, price, imgPath, quantity);
                        orderDetails.add(orderdetail);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return orderDetails;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        boolean flag = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0;
                int orderid = 0;
                //Turn off auto commit
                cn.setAutoCommit(false);
                String sql = "select accID from Accounts where email = ?";
                //Get Account ID by Email
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }

                Date d = new Date(System.currentTimeMillis());
                //Insert new order
                sql = "insert into Orders (OrdDate, status, AccID ) values (?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();

                //Get order id that is the largest number
                sql = "select top 1 OrderID from Orders order by OrderID desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("OrderID");
                }
                //Insert OrderDetails
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert into OrderDetails (OrderID, FID, quantity) values (?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return flag;
    }

    public static boolean updateOrder(int orderID, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Orders set status = ? where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, orderID);
                if (pst.executeUpdate() == 1) {
                    result = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateShipDate(int orderID, Date shipDate) throws Exception {
        boolean isSuccess = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "Update Orders set shipdate = ? where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, shipDate);
                pst.setInt(2, orderID);
                if (pst.executeUpdate() == 1) {
                    isSuccess = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public static boolean updateOrderStatusAndDate(int orderID, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                Date date = new Date(System.currentTimeMillis());
                String sql = "update Orders set status = ?, OrdDate = ? where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setDate(2, date);
                pst.setInt(3, orderID);
                if (pst.executeUpdate() == 1) {
                    result = true;
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Order> getOrderByDate(String email, Date startDate, Date endDate) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Select OrderID, OrdDate, shipdate, Orders.status, Orders.AccID "
                    + "from Orders "
                    + "join Accounts on Accounts.accID = Orders.AccID "
                    + "where Orders.OrdDate between ? and ? and Accounts.email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setDate(1, startDate);
            pst.setDate(2, endDate);
            pst.setString(3, email);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipDate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order o = new Order(orderID, ordDate, shipDate, status, accID);
                    orders.add(o);
                }
            }
        }
        return orders;
    }

    public static ArrayList<Order> getOrdersByDateForAdmin(Date startDate, Date endDate) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Select OrderID, OrdDate, shipdate, Orders.status, AccID"
                    + " from Orders where OrdDate between ? and ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setDate(1, startDate);
            pst.setDate(2, endDate);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    Date ordDate = rs.getDate("OrdDate");
                    Date shipDate = rs.getDate("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order o = new Order(orderID, ordDate, shipDate, status, accID);
                    orders.add(o);
                }
            }
        }
        return orders;
    }
}
