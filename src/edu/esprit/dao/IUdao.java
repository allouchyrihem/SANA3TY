package edu.esprit.dao;


import edu.esprit.entity.User;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fattouma PC
 */
public interface IUdao<T> {
    
    public void insertClient(T o);
    public void insertVendeur(T o);
    public boolean validateUSer(T o);
    public boolean refuseUser(T o);
   // public void delete(T o);
    public List<T> displayAll();
    public User findUserByEmail(String e);
   // public T displayById(int id);
   // public boolean update(T os);
    
}
