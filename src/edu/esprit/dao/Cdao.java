package edu.esprit.dao;


import edu.esprit.entity.Category;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public interface Cdao <T> {
    public void insert(T o);
    public void delete(int id);
    public List<T> displayAll();
    public T displayById(int id);
    public List<String>displayName();
    public int displayIdByName(String name);
    public String displayNameById(int id);
    public void update(T os);
}
