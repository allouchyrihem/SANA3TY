/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.controller;

/**
 *
 * @author HP
 */
import java.util.HashMap;
import java.util.Map;

public class Session {

    private static Map<String, Object> sessionData = new HashMap<>();

    public static void setUserData(Map<String, Object> data) {
        sessionData = data;
    }

    public static Map<String, Object> getUserData() {
        return sessionData;
    }

    public static void setAttribute(String key, Object value) {
        sessionData.put(key, value);
    }

    public static Object getAttribute(String key) {
        return sessionData.get(key);
    }

    public static void removeAttribute(String key) {
        sessionData.remove(key);
    }

    public static void clear() {
        sessionData.clear();
    }
}

