/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import edu.esprit.entity.User;
import edu.esprit.util.MyConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;

/**
 *
 * @author Fattouma PC
 */
public class UserDao implements IUdao<User> {

    private static UserDao instance;
    private Statement st;
    private ResultSet rs;

    private UserDao() {
        MyConnection cs = MyConnection.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public void insertClient(User o) {
        try {
            String pwd = this.hashPassword(o.getPassword());
            String req = "insert into user (name,email,password,status,roles) values"
                    + " ('" + o.getName() + "','" + o.getEmail() + "','" + pwd + "'," + o.getStatus() + "," + " '[\"ROLE_CLIENT\"]')";
            System.out.println(req);
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Probleme avec le hashage du mot de passe");
        }
    }

    @Override
    public void insertVendeur(User o) {
        try {
            String pwd = this.hashPassword(o.getPassword());
            String req = "insert into user (name, email, password, status, description, address, picture, roles) values ('"
                    + o.getName() + "','"
                    + o.getEmail() + "','"
                    + pwd + "',"
                    + o.getStatus() + ",'"
                    + o.getDescription() + "','"
                    + o.getAdress() + "','"
                    + o.getPicture() + "',"
                    + "'[\"ROLE_VENDEUR\"]')";
            System.out.print(req);
            try {
                st.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Probleme avec le hashage du mot de passe");
        }
    }

    @Override
    public ObservableList<User> displayAll() {
        String req = "select * from user";
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                User p = new User();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(6));
                p.setEmail(rs.getString(2));
                p.setRoles2(rs.getString("roles"));
                p.setStatus(rs.getBoolean("status"));
                p.setPicture(rs.getString("picture"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        byte[] passwordBytes = password.getBytes();

        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedPasswordBytes = sha256Digest.digest(passwordBytes);

        // Convert the hashed password bytes to a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedPasswordBytes) {
            sb.append(String.format("%02x", b));
        }
        String hashedPassword = sb.toString();
        return hashedPassword;
    }

    @Override
    public boolean validateUSer(User o) {

        String qry = "UPDATE user SET status = true  where id = " + o.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean refuseUser(User o) {

        String qry = "UPDATE user SET status = false  where id = " + o.getId();
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        String qry = "SELECT * FROM user WHERE email ='" + email + "'";

        try {
            rs = st.executeQuery(qry);
            if (rs.next()) {

                user.setId(rs.getInt(1));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoles2(rs.getString("roles"));
                user.setStatus(rs.getBoolean("status"));
                user.setDescription(rs.getString("description"));
                user.setAddress(rs.getString("address"));
                return user;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(User u) {
        String qry = "UPDATE user SET name = '" + u.getName() + "', email = '" + u.getEmail() + "' WHERE id = " + u.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCode(String email, String code) {
        String qry = "UPDATE user SET verification_code = '" + code + "' WHERE email ='" + email + "'";
        System.out.println(qry);
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean resetPassword(String email, String password) {
        try {
            String qry = "UPDATE user SET password = '" + this.hashPassword(password) + "' WHERE email ='" + email + "'";
            System.out.println(qry);
            try {
                if (st.executeUpdate(qry) > 0) {
                    return true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
        }
        return false;
    }

    public String findCode(String email) {
        String qry = "SELECT verification_code FROM user WHERE email ='" + email + "'";
        try {
            rs = st.executeQuery(qry);
            if (rs.next()) {
                return rs.getString("verification_code");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
