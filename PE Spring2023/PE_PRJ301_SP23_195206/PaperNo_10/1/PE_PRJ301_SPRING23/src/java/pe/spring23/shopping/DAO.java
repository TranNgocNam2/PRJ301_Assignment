/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.spring23.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.spring23.utils.DBUtils;

/**
 *
 * @author hd
 */
public class DAO {
//    your code here

    public static ArrayList<Product> getProductActive() throws Exception {
        ArrayList<Product> productList = new ArrayList<>();
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select productID, productName, description, price, status "
                    + "from tblProducts where status = 1";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int price = rs.getInt("price");
                    boolean status = rs.getBoolean("status");
                    Product p = new Product(productID, productName, description, price, status);
                    productList.add(p);
                }
            }
            cn.close();
        }
        return productList;
    }
}
