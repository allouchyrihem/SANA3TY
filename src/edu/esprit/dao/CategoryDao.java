/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class CategoryDao implements Cdao<Category>{
    
    private static CategoryDao instance;
    private Statement st;
    private ResultSet rs;
    
    public CategoryDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CategoryDao getInstance(){
        if(instance==null) 
            instance=new CategoryDao();
        return instance;
    }

    @Override
    public void insert(Category o) {
        String req="insert into category (name,description) values ('"+o.getName()+"','"+o.getDescription()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(int id) {
        String req="delete from category where id="+id;
        Category p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Category> displayAll() {
        String req="select * from category";
        ObservableList<Category> list=FXCollections.observableArrayList();       
        
        try {
            
            rs=st.executeQuery(req);
            while(rs.next()){
                Category p=new Category();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ObservableList<String> displayName() {
        String req="select name from category";     
        ObservableList<String> options = FXCollections.observableArrayList();
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                String ch="";
                ch=rs.getString("name");
                options.add(ch);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return options;
    }
      @Override
    public int displayIdByName(String name) {
        String req="select id from category where name='"+name+"'";

        int options = 0;

        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                options=rs.getInt("id");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return options; 
    }

    
    @Override
    public Category displayById(int id) {
           String req="select * from category where id ="+id;
           Category p=new Category();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    
    @Override
    public String displayNameById(int id) {
           String req="select name from category where id="+id;
           String name="";
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                name=rs.getString("name");
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return name;
    }
    
    @Override
    public void update(Category p) {
        String qry = "UPDATE category SET name=?,description=? WHERE id=?";
        try {
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(qry);
        ps.setString(1,p.getName());
        ps.setString(2,p.getDescription());
        ps.setInt(3, p.getId());
        ps.executeUpdate();
        System.out.println("category updated successfully.");
    } catch (SQLException ex) {
        System.out.println("Error updating category: " + ex.getMessage());
    }   
    
}
    
}
