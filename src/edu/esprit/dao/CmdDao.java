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
import edu.esprit.entity.user;
import edu.esprit.util.MyConnection;
import java.sql.Connection;
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
public int getLastInsertedId() throws SQLException {
    Connection connection = MyConnection.getInstance().getCnx();
    PreparedStatement ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        return rs.getInt(1);
    } else {
        throw new SQLException("Unable to retrieve last inserted ID");
    }
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
    public static Commande find(int commandeId, List<Commande> productList) {
    for (Commande product : productList) {
        if (product.getId() == commandeId) {
            return product;
        }
    }
    return null;
}

  @Override
public void insert(Commande o) {
    try {
        // Insertion de la commande avec la quantité renseignée par le client
        String req="INSERT INTO commande (adresse, description, etat, datecmd, quantity, totale, user_id) VALUES ('"+o.getAdresse()+"', '"+o.getDescription()+"', 'en attente', '"+java.sql.Date.valueOf(LocalDate.now())+"', '"+o.getQuantite()+"', '" + o.getTotale()+"', '" +6 +"')";
        st.executeUpdate(req);
    } catch (SQLException ex) {
        Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void insertP(int commande_id, int product_id) {
    MyConnection cs = MyConnection.getInstance();
    try {
        Connection connection = cs.getCnx();
        // Check if the commande_id exists in the commande table
        String checkCommande = "SELECT * FROM commande WHERE id = ?";
        PreparedStatement checkCommandeStmt = connection.prepareStatement(checkCommande);
        checkCommandeStmt.setInt(1, commande_id);
        ResultSet commandeResult = checkCommandeStmt.executeQuery();
        if (!commandeResult.next()) {
            throw new SQLException("Commande with id " + commande_id + " does not exist");
        }

        // Check if the product_id exists in the product table
        String checkProduct = "SELECT * FROM product WHERE id = ?";
        PreparedStatement checkProductStmt = connection.prepareStatement(checkProduct);
        checkProductStmt.setInt(1, product_id);
        ResultSet productResult = checkProductStmt.executeQuery();
        if (!productResult.next()) {
            throw new SQLException("Product with id " + product_id + " does not exist");
        }

        // Insert the product into the product_commande table
        String insertProduct = "INSERT INTO product_commande (product_id, commande_id) VALUES (?, ?)";
        PreparedStatement insertProductStmt = connection.prepareStatement(insertProduct);
        insertProductStmt.setInt(1, product_id);
        insertProductStmt.setInt(2, commande_id);
        insertProductStmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

public Commande find(int id) {
    String query = "SELECT * FROM commande WHERE id = ?";
    Commande commande = null;
    try (PreparedStatement statement = MyConnection.getInstance().getCnx().prepareStatement(query)) {
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int commandeId = result.getInt("id");
            commande = new Commande(commandeId);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return commande;
}
public int getLastIdCommande() {
    int lastId = 0;
    MyConnection cs=MyConnection.getInstance();
    try {
        Connection conn = cs.getCnx();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(id) FROM commande");
        if (rs.next()) {
            lastId = rs.getInt(1);
        }
        conn.close();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return lastId;
}


    public int getIdCommandeProduit(int idProduit, int idCommande) {
    int idCommandeProduit = -1;
    String req = "SELECT id_commande_produit FROM product_commande WHERE product_id = ? AND commande_id = ?";
    try(Connection cnx = MyConnection.getInstance().getCnx();
         PreparedStatement ps = cnx.prepareStatement(req)){
        ps.setInt(1, idProduit); // le premier paramètre est le product_id
        ps.setInt(2, idCommande); // le deuxième paramètre est le commande_id
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                idCommandeProduit = rs.getInt("id_commande_produit");
            }
        }
    } catch (SQLException ex) {
        System.err.println("Erreur lors de la récupération de l'identifiant du commande produit : " + ex.getMessage());
    }
    return idCommandeProduit;
}



public void insertPc(ProductCmd productCommande) {
    Connection conn = null;
    PreparedStatement ps = null;
    try {
        conn = MyConnection.getInstance().getCnx();
        ps = conn.prepareStatement("INSERT INTO product_commande (product_id, commande_id) VALUES (?, ?)");
        ps.setInt(1, productCommande.getProduct().getId());
        ps.setInt(2, productCommande.getCommande().getId());
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CmdDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
     String req="SELECT c.*,u.name,p.id, p.name FROM commande c JOIN product_commande cp ON c.id = cp.commande_id JOIN product p ON cp.product_id = p.id  JOIN user u ON c.user_id = u.id";

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
                commande.setQuantite(rs.getInt("c.quantity"));
                 // Set the User object associated with this Commande
                user user = new user();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                commande.setUser(user);
                
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
