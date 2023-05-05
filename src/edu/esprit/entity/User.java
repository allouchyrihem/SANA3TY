/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author Fattouma PC
 */
public class User {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty password ; 
    private SimpleStringProperty description;
    private ArrayList<String> roles; 
    private SimpleBooleanProperty status ; 
    private SimpleStringProperty picture;
    private SimpleStringProperty address;

   

    public User() {
        roles = new ArrayList<>();
    }

    public User(String name, String email, String password,String address,  String description, String picture) {
        this.name =  new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleBooleanProperty(false); 
        this.address = new SimpleStringProperty(address);
        this.picture = new SimpleStringProperty(picture);
        roles = new ArrayList<>(); 
    }
    
     public User(String name, String email, String password,String address,  String description) {
        this.name =  new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleBooleanProperty(false); 
        this.address = new SimpleStringProperty(address);
     
        roles = new ArrayList<>(); 
    }

    public User(String name, String email, String password) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);;
        roles = new ArrayList<>(); 
        this.status = new SimpleBooleanProperty(true);
    }
    
    public User(int id,String name, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        roles = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setEmail(String email) {
        this.email =new SimpleStringProperty( email);
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }
      public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
    
   public void setRoles2(String role){
     if (role.compareTo("[\"ROLE_ADMIN\"]")==0) {
      this.roles.add("ROLE_ADMIN");
      
     }else if(role.compareTo("[\"ROLE_VENDEUR\"]")==0){
       this.roles.add("ROLE_VENDEUR");
     }else if(role.compareTo("[\"ROLE_CLIENT\"]")==0){
         this.roles.add("ROLE_CLIENT");
     }else{
         this.roles.add("ROLE_ADMINF");
     }
    }

    public void setStatus(Boolean  status) {
        this.status = new SimpleBooleanProperty(status);
    }

    public void setPicture(String picture) {
        this.picture = new SimpleStringProperty(picture);
    }
    
    

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }
    
    public int getId() {
        return id.get();
    }
    

    public SimpleStringProperty getNameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty getEmailProperty() {
        return email;
    }
     public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty getPasswordProperty() {
        return password;
    }
    
    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty getDescriptionProperty() {
        return description;
    }
    
     public SimpleStringProperty getAddressProperty() {
        return address;
    }
    
     public SimpleStringProperty getRoleProperty() {
       if(this.roles.contains("ROLE_ADMIN")){
            return new SimpleStringProperty("Admin");
        }else if(this.roles.contains("ROLE_CLIENT")){
            return new SimpleStringProperty("Client");
        }else{
       return new SimpleStringProperty("Vendeur");}
       
    }
    public String getAdress(){
        return address.get();
    }
    
     public String getDescription() {
        return description.get();
    }

    public List<String> getRoles() {
        return roles;
    }

    public SimpleBooleanProperty getStatusProperty() {
        return status;
    }
    
     public SimpleStringProperty getStatusProperty2() {
         
         if(this.getStatus() == true ){
            return new SimpleStringProperty("Valide");
       }else{
       return new SimpleStringProperty("Invalide");}
    }
    
     public Boolean  getStatus() {
        return status.get();
    }

    public SimpleStringProperty getPictureProperty() {
        return picture;
    }
    
    public String getPicture() {
        return picture.get();
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", description=" + description + ", roles=" + roles + '}';
    }
    
    public void  addRole(String s ){
        roles.add(s);
    }

   
    public boolean checkRole(String role){
        if(roles.contains(role)){
            return true;
        }
    return false;
    }
       
}
