package edu.esprit.dao;

import java.sql.PreparedStatement;
import edu.esprit.entity.Events;
import edu.esprit.util.MyConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventsDao implements Edao<Events>{

    private static EventsDao instance;
    private Statement st;
    private ResultSet rs;

    public EventsDao() {
        MyConnection cs=MyConnection.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static EventsDao getInstance(){
        if(instance==null)
            instance=new EventsDao();
        return instance;
    }
    @Override
    public void insert(Events o) {
       String req = "insert into event (id, name, adresse, date, description, link) values ("+
             o.getId()+",'"+o.getName()+"','"+o.getAdresse()+"','"+o.getDate()+"','"+o.getDescription()+"','"+o.getLink()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    

    @Override
     public void delete(Events o) {
        String req="delete from event where id="+o.getId();
        Events p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public List<Events> displayAll() {
        List<Events> list=new ArrayList<>();
        List<Events> eventsList = new ArrayList<>();
        String requete="select * from event";

        try {
            
            ResultSet rs =st.executeQuery(requete);
            while(rs.next()) {

                //Events e=new Events (rs.getInt("id"),rs.getInt("user_id"),rs.getString("name"),rs.getString("description"),rs.getString("adresse"), rs.getDate("date").toLocalDate(),rs.getString("link"));
                
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String name = rs.getString("name");
                String adresse = rs.getString("adresse");
                LocalDate date = rs.getDate("date").toLocalDate();
                String description = rs.getString("description");
                String link = rs.getString("link");

                Events event = new Events(id, user_id, name, adresse, date, description, link);
                eventsList.add(event);
                //list.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        
           // Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e1);
        }

        return eventsList;
    }
    @Override
    public Events displayById(int id) {
           String req="select * from event where id ="+id;
           Events p=new Events();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
            
                p.setName(rs.getString("name"));
                p.setAdresse(rs.getString("adresse"));
                 p.setDate(rs.getDate("date").toLocalDate());
              
                p.setDescription(rs.getString("description"));
             
                p.setLink(rs.getString("link"));
              
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public void update(Events event){
       String requete = "UPDATE event SET name=?, adresse=?, date=?, description=?, link=? WHERE id=?";
try {
    PreparedStatement pstmt = MyConnection.getInstance().getCnx().prepareStatement(requete);
    pstmt.setString(1, event.getName());
    pstmt.setString(2, event.getAdresse());
    pstmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
    pstmt.setString(4, event.getDescription());
    pstmt.setString(5, event.getLink());
    pstmt.setInt(6, event.getId());
    pstmt.executeUpdate();
} catch (SQLException ex) {
    Logger.getLogger(EventsDao.class.getName()).log(Level.SEVERE, null, ex);
}

    }
    

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<Events> searchByName(String name) {
    List<Events> events = new ArrayList<>();
    try {
        String query = "SELECT * FROM event WHERE name LIKE '%" + name + "%'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Events event = new Events();
            event.setId(rs.getInt("id"));
            event.setName(rs.getString("name"));
            event.setDescription(rs.getString("description"));
            event.setAdresse(rs.getString("adresse"));
            event.setDate(rs.getDate("date").toLocalDate());
            event.setLink(rs.getString("link"));
            events.add(event);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return events;
}

    
}