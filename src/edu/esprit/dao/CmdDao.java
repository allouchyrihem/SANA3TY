/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Commande;
import edu.esprit.entity.CommandeProduit;
import edu.esprit.entity.Product;
import edu.esprit.entity.ProductCmd;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author HP
 */
public class CmdDao implements ICmd<Commande>{
    
    private static CmdDao instance;
    private Statement st;
    private ResultSet rs;
    private List<Commande> commandes;
    private List<Product> produits;
    private List<CommandeProduit> commandeProduits;
    public CmdDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addCommandeProduit(CommandeProduit commandeProduit) {
        commandeProduits.add(commandeProduit);
    }

    public List<CommandeProduit> getCommandeProduitsForCommande(Commande commande) {
        List<CommandeProduit> result = new ArrayList<>();
        for (CommandeProduit commandeProduit : commandeProduits) {
            if (commandeProduit.getIdCommande() == commande.getId()) {
                result.add(commandeProduit);
            }
        }
        return result;
    }

    public List<CommandeProduit> getCommandeProduitsForProduit(Product produit) {
        List<CommandeProduit> result = new ArrayList<>();
        for (CommandeProduit commandeProduit : commandeProduits) {
            if (commandeProduit.getIdProduct() == produit.getId()) {
                result.add(commandeProduit);
            }
        }
        return result;
    }
     public static CmdDao getInstance(){
        if(instance==null) 
            instance=new CmdDao();
        return instance;
    }

    @Override
    public void insert(Commande o) {
        String req="insert into commande (adresse,description,etat,datecmd) values ('"+o.getAdresse()+"','"+o.getDescription()+"','"+"en attente"+"','"+java.sql.Date.valueOf(LocalDate.now())+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(int id) {
        String req="delete from commande where id="+id;
        Commande p=displayById(id);
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
public void updateCommandState(Commande commande) {
    String req = "UPDATE commande SET etat=? WHERE id=?";
    try {
        PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
        ps.setString(1, commande.getEtat());
        ps.setInt(2, commande.getId());
        ps.executeUpdate();
        System.out.println("Command state updated successfully.");
    } catch (SQLException ex) {
        System.out.println("Error updating command state: " + ex.getMessage());
    }
}

    @Override
public ObservableList<Commande> displayAll() {
     String req="SELECT c.*,p.id, p.name FROM commande c JOIN product_commande cp ON c.id = cp.commande_id JOIN product p ON cp.product_id = p.id";

    ObservableList<Commande> list = FXCollections.observableArrayList();
    
    try {
        rs = st.executeQuery(req);
        while (rs.next()) {
            int commandeId = rs.getInt("c.id");
            Commande commande = null;
            
            // Check if the Commande object already exists in the list
            for (Commande c : list) {
                if (c.getId() == commandeId) {
                    // The Commande object already exists, so use it
                    commande = c;
                    break;
                }
            }
            
            // If the Commande object doesn't exist yet, create a new one and add it to the list
            if (commande == null) {
                commande = new Commande();
                commande.setId(commandeId);
                commande.setAdresse(rs.getString("c.adresse"));
                commande.setDescription(rs.getString("c.description"));
                commande.setEtat(rs.getString("c.etat"));
                commande.setTotale(rs.getFloat("c.totale"));
                commande.setDatecmd(rs.getDate("c.datecmd").toLocalDate());
                commande.setCommandeProducts(FXCollections.observableArrayList());
                list.add(commande);
            }
            
            // Create a new Product object and add it to the Commande's list of associated Products
            Product product = new Product();
            product.setId(rs.getInt("p.id"));
            product.setName(rs.getString("p.name"));
            commande.getCommandeProducts().add(new ProductCmd(commande, product));
        }
    } catch (SQLException ex) {
        Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return list;
}

    public List<Commande> displayAllList() {
        String req="select * from commande  LEFT JOIN product_commande ON product.id = product_commande.productid \"\n" +
"               + \"LEFT JOIN commande ON product_commande.commande_id = commande.id";
        List<Commande> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Commande p=new Commande();
                p.setId(rs.getInt(1));
                p.setAdresse(rs.getString("adresse"));
                p.setDescription(rs.getString("description"));
                p.setEtat(rs.getString("etat"));
                p.setTotale(rs.getFloat("totale"));
                p.setDatecmd(rs.getDate("datecmd").toLocalDate());
                Product s = new Product();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                p.getProducts().add(new ProductCmd(p, s, 0));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Commande displayById(int id) {
           String req="select * from commande where id ="+id;
           Commande p=new Commande();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setAdresse(rs.getString("adresse"));
                p.setDescription(rs.getString("description"));
                p.setEtat(rs.getString("etat"));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    /**
     *
     * @param os
     * @param o */
     
    @Override
    public void  update(Commande os) {
        String qry = "UPDATE commande SET adresse=?, description=?, etat=? WHERE id=?";
        
        try {

        PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(qry);
        pstmt.setString(1, os.getAdresse());
        pstmt.setString(2, os.getDescription());
       pstmt.setString(2, os.getEtat());
       
        pstmt.setInt(5, os.getId());
        pstmt.executeUpdate();
    } catch (SQLException ex) {
    Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
   }

    }
    
}