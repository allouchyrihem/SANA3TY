/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.dao;

import java.util.List;

/**
 *
 * @author asus
 */
public interface Idao <T> {
    public void insert(T o);
    public void delete(int id);
    
    public List<T> displayAll();
    public T displayById(int id);
    
    //public boolean update(T os);
  public void update(T o);
    public void delete(T o);
}