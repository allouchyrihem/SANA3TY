/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import edu.esprit.entity.Events;
import edu.esprit.entity.PromotionTotale;
import edu.esprit.util.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rouao
 */
public class PromotionTotaleDao implements Idao<PromotionTotale> {
    
     private static PromotionTotaleDao instance;
    private Statement st;
    private ResultSet rs;

    public PromotionTotaleDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionTotaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PromotionTotaleDao getInstance(){
        if(instance==null)
            instance=new PromotionTotaleDao();
        return instance;
    }

    @Override
    public void insert(PromotionTotale o) {
        String req = "insert into discount_totale (id,code_promo, value_promo, date_debut, date_fin) values ("+
             o.getId()+",'"+o.getName()+"','"+o.getValue()+"','"+o.getDateDebut()+"','"+o.getDateFin()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PromotionTotale> displayAll() {
        List<PromotionTotale> list=new ArrayList<>();
        List<PromotionTotale> promoList = new ArrayList<>();
        String requete="select * from discount_totale";

        try {
            
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()) {

                //Events e=new Events (rs.getInt("id"),rs.getInt("user_id"),rs.getString("name"),rs.getString("description"),rs.getString("adresse"), rs.getDate("date").toLocalDate(),rs.getString("link"));
                
                int id = rs.getInt("id");
                //int user_id = rs.getInt("user_id");
                String name = rs.getString("code_promo");
                float value = rs.getFloat("value_promo");
                LocalDate dated = rs.getDate("date_debut").toLocalDate();
                  LocalDate datef = rs.getDate("date_fin").toLocalDate();
               

                PromotionTotale promo = new PromotionTotale(id, name, value, dated, datef);
                promoList.add(promo);
                //list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        
           // Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e1);
        }

        return promoList;
    }

    @Override
    public PromotionTotale displayById(int id) {
String req="select * from discount_totale where id ="+id;
           PromotionTotale p=new PromotionTotale();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
            
                p.setName(rs.getString("code_promo"));
                p.setValue(rs.getFloat("value_promo"));
                 p.setDateDebut(rs.getDate("date_debut").toLocalDate());
               p.setDateFin(rs.getDate("date_fin").toLocalDate());
           
             
          
              
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public void update(PromotionTotale o) {
       String requete = "UPDATE discount_totale SET code_promo=?, value_promo=?, date_debut=?, date_fin=?";
try {
    PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
    pstmt.setString(1, o.getName());
    pstmt.setFloat(2, o.getValue());
    pstmt.setDate(3, java.sql.Date.valueOf(o.getDateDebut()));
    pstmt.setDate(4, java.sql.Date.valueOf(o.getDateFin()));
   
    pstmt.setInt(5, o.getId());
    pstmt.executeUpdate();
} catch (SQLException ex) {
    Logger.getLogger(PromotionTotaleDao.class.getName()).log(Level.SEVERE, null, ex);
}

    }

    @Override
    public void delete(PromotionTotale o) {
String req="delete from discount_totale where id="+o.getId();
        PromotionTotale p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PromotionTotaleDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");    }
    
}
