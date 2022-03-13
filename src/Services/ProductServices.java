/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ConnectSQL.ConnectSQLSV;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class ProductServices {
    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<Product>();
        Connection con = ConnectSQLSV.getConnectSQL();
        String sql = "Select * from Product";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while(rs.next())
            {
                Product product = new Product();
                product.setIdProduct(rs.getString("idProduct"));
                product.setTenPro(rs.getString("tenPro"));
                product.setSoLuongHienCon(rs.getInt("soLuongHienCon"));
                product.setDonGiaBan(rs.getInt("donGiaBan"));
                
                products.add(product);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }
    public void addProduct(Product product){
        Connection con = ConnectSQLSV.getConnectSQL();
        String sql = "Insert into Product(idProduct,tenPro,soLuongHienCon,donGiaBan)" + "Values(?,?,?,?)";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,product.getIdProduct());
            preparedStatement.setString(2, product.getTenPro());
            preparedStatement.setInt(3, product.getSoLuongHienCon());
            preparedStatement.setDouble(4, product.getDonGiaBan());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteProduct(String iDProduct){
        Connection con = ConnectSQLSV.getConnectSQL();
        String sql = "Delete from Product where idProduct = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, iDProduct);
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void updateProduct(Product product){
        Connection con = ConnectSQLSV.getConnectSQL();
        String sql = "Update Product set tenPro = ?, soLuongHienCon = ?, donGiaBan = ? where idProduct = ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, product.getTenPro());
            preparedStatement.setInt(2, product.getSoLuongHienCon());
            preparedStatement.setDouble(3, product.getDonGiaBan());
            preparedStatement.setString(4, product.getIdProduct());
         
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static String getIdNext(){
        Connection con = ConnectSQLSV.getConnectSQL();
        String sql = "select dbo.ft_idNext_Product()";
        String idNext = null; 
        try{
            PreparedStatement preparedStatement = con.prepareCall(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                idNext = rs.getString("");
            }
            return idNext;
        }
        catch(SQLException e)
        {
            e.printStackTrace();;
        }
    return idNext;
    }
}
