package lk.indikapharmacy.dao.custom.impl;

import lk.indikapharmacy.dao.CrudUtil;
import lk.indikapharmacy.dao.custom.UserDAO;
import lk.indikapharmacy.entity.Customer;
import lk.indikapharmacy.entity.Users;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(Users users) throws Exception {
        String sql="INSERT INTO users VALUES(?,?,?,MD5(?))";
        return CrudUtil.executeUpdate(sql,users.getUserID(),users.getName(),users.getUserName(),users.getPassword());
    }

    @Override
    public boolean delete(String s) throws Exception {
        String sql="DELETE FROM users WHERE userID=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Users users) throws Exception {
        String sql = "UPDATE users SET name=?, username=?, password=MD5(?) WHERE userID=?";
        return CrudUtil.executeUpdate(sql, users.getName(),users.getUserName(),users.getPassword(),users.getUserID());
}

    @Override
    public Users search(String s) throws Exception {
        String sql ="SELECT * FROM users WHERE userID=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            return new Users(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public ArrayList<Users> getAll() throws Exception {
        String sql = "SELECT * FROM users";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Users> userArrayList = new ArrayList<>();
        while (rst.next()){
            userArrayList.add(new Users(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return userArrayList;
    }

    @Override
    public boolean validateUser(String u, String p) throws Exception {
        String sql = "SELECT * FROM users WHERE username=? AND password=MD5(?)";
        ResultSet rst = CrudUtil.executeQuery(sql,u,p);
        return rst.next();
    }
}
