/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CategoryDao;
import edu.esprit.dao.CmdDao;
import edu.esprit.dao.ProductDao;
import edu.esprit.dao.UserDao;
import edu.esprit.entity.Category;
import edu.esprit.entity.Commande;
import edu.esprit.entity.Product;
import edu.esprit.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ListData {
      
     /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Commande> commandes=FXCollections.observableArrayList();
    private ObservableList<Product> products=FXCollections.observableArrayList();
    private ObservableList<Category> categories=FXCollections.observableArrayList();
    private ObservableList<String> cat=FXCollections.observableArrayList();
    private ObservableList<User> persons=FXCollections.observableArrayList();

    public ListData() {
        
        ProductDao pdao=ProductDao.getInstance();
        CategoryDao cdao=CategoryDao.getInstance();
         CmdDao cmdao=CmdDao.getInstance();
        products= (ObservableList<Product>)pdao.displayAll();
        categories= (ObservableList<Category>)cdao.displayAll();
        cat=(ObservableList<String>)cdao.displayName();
        UserDao udao=UserDao.getInstance();
        persons= udao.displayAll();
       commandes= (ObservableList<Commande>)cmdao.displayAll();
        System.out.println(commandes);
    }
    
    public ObservableList<Product> getProducts(){
        return products;
    }
    public ObservableList<Category> getcategories(){
        return categories;
    }
    public ObservableList<String> getNames(){
        return cat;
    
    }
    public ObservableList<User> getPersons(){
        return persons;
    }
     public ObservableList<Commande> getcommandes(){
        return commandes;
    } 
}
