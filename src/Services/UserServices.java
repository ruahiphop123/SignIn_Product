/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ConnectSQL.ConnectSQLSV;
import Model.Product;
import Model.User;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author pc
 */
public class UserServices {
    public List<User> getAllUser(){
        List<User> users = new ArrayList<User>();
        Connection con = ConnectSQLSV.getConnectSQL();
        
        String sql = "Select * from KhachHang";
        
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                User user = new User();
                user.setiD(rs.getInt("iD"));
                user.setTenKH(rs.getString("tenKH"));
                user.setTaiKhoan(rs.getString("taiKhoan"));
                user.setMatKhau(rs.getString("matKhau"));
                user.setSoDT(rs.getString("soDT"));
                user.setNoiDungCV(rs.getString("noiDungCV"));
                user.setChucVu(rs.getString("chucVu"));
                user.setSoThich(rs.getString("soThich"));
                
                users.add(user);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }
    public void ađdUser(User user){
        Connection con = ConnectSQLSV.getConnectSQL();
        String sql = "insert into KhachHang(tenKH,taiKhoan,matKhau,soDT,noiDungCV,chucVu,soThich)" +
                "Values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getTenKH());
            preparedStatement.setString(2, user.getTaiKhoan());
            preparedStatement.setString(3, user.getMatKhau());
            preparedStatement.setString(4, user.getSoDT());
            preparedStatement.setString(5, user.getNoiDungCV());
            preparedStatement.setString(6, user.getChucVu());
            preparedStatement.setString(7, user.getSoThich());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
