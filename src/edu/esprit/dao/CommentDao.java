/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import edu.esprit.dao.ComDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Comment;
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
public class CommentDao implements ComDao<Comment>{
    
    private static CommentDao instance;
    private Statement st;
    private ResultSet rs;
    
    public CommentDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CommentDao getInstance(){
        if(instance==null) 
            instance=new CommentDao();
        return instance;
    }

    @Override
    public void insert(Comment o) {
        String req="insert into comment (description,product_id) values ('"+o.getDescription()+"','"+o.getProduct().getId()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(int id) {
        String req="delete from comment where id="+id;
        Comment p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Comment> displayAll(int id) {
        String req="SELECT c.description " +
"FROM comment c "+
"JOIN product p ON c.product_id = p.id AND c.product_id="+id;
        
        ObservableList<Comment> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Comment p=new Comment();
                p.setDescription(rs.getString("description"));
                list.add(p);
                System.out.println(list);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
@Override
    public ObservableList<Comment> displayAllList() {
        String req="SELECT * FROM comment";
        ObservableList<Comment> list=FXCollections.observableArrayList();   
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Comment p=new Comment();
                p.setId(rs.getInt(1));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Comment displayById(int id) {
           String req="select * from comment where id ="+id;
           Comment p=new Comment();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();

                p.setDescription(rs.getString("description"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    /**
     *
     * @param c
     */
    @Override
    public void update(Comment c){
       String requete = "UPDATE comment SET description=? WHERE id=?";
try {
    PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
    
    
    pstmt.setString(1, c.getDescription());
    pstmt.setInt(2, c.getId());
  
    pstmt.executeUpdate();
} catch (SQLException ex) {
    Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
}

    }
  

    
    
}