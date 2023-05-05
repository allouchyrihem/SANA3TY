/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import edu.esprit.entity.Category;
import edu.esprit.entity.Product;
import edu.esprit.entity.User;
import edu.esprit.util.MyConnection;
import java.sql.Connection;
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
public class ProductDao implements Idao<Product>{
    
    private static ProductDao instance;
    private Statement st;
    private ResultSet rs;
    
    public ProductDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ProductDao getInstance(){
        if(instance==null) 
            instance=new ProductDao();
        return instance;
    }
    public static Product find(int productId, List<Product> productList) {
    for (Product product : productList) {
        if (product.getId() == productId) {
            return product;
        }
    }
    return null;
}
public Product find(int id) {
    Product product = null;
    try {
        Connection connection = MyConnection.getInstance().getCnx();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE id=?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            product = new Product(result.getInt("id"), result.getString("name"));
        }
        statement.close();
    } catch (SQLException ex) {
        System.out.println("Error while finding product with id " + id + ": " + ex.getMessage());
    }
    return product;
}

    @Override
    public void insert(Product o) {
        System.out.println(o.getUser().getId());
        String req="insert into product (name,description,price,stock,image,category_id,user_id) values ('"+o.getName()+"','"+o.getDescription()+"','"+o.getPrice()+"','"+o.getStock()+"','"+o.getImage()+"','"+o.getCategory().getId()+"','"+o.getUser().getId()+"')";
        
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(int id) {
        String req="delete from product where id="+id;
        Product p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

  @Override
public ObservableList<Product> displayAll() {
    String req="SELECT p.id, p.name, p.description, p.price, p.stock, p.image,u.name AS user_name, c.name AS category_name " +
               "FROM product p " +
               "JOIN category c ON p.category_id = c.id "+
                "JOIN user u ON p.user_id=u.id";
    ObservableList<Product> list=FXCollections.observableArrayList();

    try {
        rs=st.executeQuery(req);
        while(rs.next()){
            Product p=new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getFloat("price"));
            p.setStock(rs.getString("stock"));
            p.setImage(rs.getString("image"));
            Category category = new Category();
            category.setName(rs.getString("category_name"));
            User user = new User();
            user.setName(rs.getString("user_name"));
            p.setCategory(category);
            list.add(p);
        }

    } catch (SQLException ex) {
        Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}


    public List<Product> displayAllList() {
        String req="select * from product";
        List<Product> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Product p=new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getString("stock"));
                p.setImage(rs.getString("image"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Product displayById(int id) {
           String req="select * from product where id ="+id;
           Product p=new Product();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getString("stock"));
                p.setImage(rs.getString("image"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public void update(Product p){
       String requete = "UPDATE product SET name=?, description=?, price=?, stock=?, image=?, category_id=? WHERE id=?";
try {
    PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
    pstmt.setString(1, p.getName());
    pstmt.setString(2, p.getDescription());
    pstmt.setFloat(3, p.getPrice());
    pstmt.setString(4, p.getStock());
    pstmt.setString(5, p.getImage());
    pstmt.setInt(6,p.getCategory().getId());
    pstmt.setInt(7,p.getId());
    pstmt.executeUpdate();
} catch (SQLException ex) {
    Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
}

}
    
    
    
    
}
