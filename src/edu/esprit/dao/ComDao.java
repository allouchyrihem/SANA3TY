package edu.esprit.dao;


import edu.esprit.entity.Comment;
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
public interface ComDao <T> {
    public void insert(T o);
    public void delete(int id);
    public List<T> displayAll(int id);
    public T displayById(int id);
    public void update(T o);
    public List<T> displayAllList();
}