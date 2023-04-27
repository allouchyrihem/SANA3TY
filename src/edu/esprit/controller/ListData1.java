/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

import edu.esprit.dao.CommentDao;
import edu.esprit.dao.ReclamationDao;
import edu.esprit.entity.Comment;
import edu.esprit.entity.Product;
import edu.esprit.entity.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class ListData1 {
      
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Reclamation> reclamations=FXCollections.observableArrayList();
    private ObservableList<Comment> comments=FXCollections.observableArrayList();

    public ListData1() {
        
        ReclamationDao pdao=ReclamationDao.getInstance();
        CommentDao cdao=CommentDao.getInstance();
        reclamations= (ObservableList<Reclamation>)pdao.displayAll();
        comments= (ObservableList<Comment>)cdao.displayAll();

        System.out.println(reclamations);
    }
    
    public ObservableList<Reclamation> getReclamations(){
        return reclamations;
    }
    public ObservableList<Comment> getcomments(){
        return comments;
    }
   
}