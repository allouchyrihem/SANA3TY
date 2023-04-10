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
    private List<String> roles; 
    private SimpleBooleanProperty status ; 
    private SimpleStringProperty picture;
    private SimpleStringProperty address;

   

    public User() {
    }

    public User(String name, String email, String password,String address,  String description) {
        this.name =  new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);;
        this.password = new SimpleStringProperty(password);;
        this.description = new SimpleStringProperty(description);;
        this.status = new SimpleBooleanProperty(false); 
        this.address = new SimpleStringProperty(address);
        roles = new ArrayList<>(); 
    }

    public User(String name, String email, String password) {
        this.name = new SimpleStringProperty(name);;
        this.email = new SimpleStringProperty(email);;
        this.password = new SimpleStringProperty(password);;
        roles = new ArrayList<>(); 
        this.status = new SimpleBooleanProperty(true);
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

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
    

    public void setStatus(Boolean  status) {
        this.status = new SimpleBooleanProperty(status);
    }

    public void setPicture(SimpleStringProperty picture) {
        this.picture = picture;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.picture, other.picture)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
    public boolean checkRole(String role){
        if(roles.contains(role)){
            return true;
        }
    return false;
    }
       
    
}
