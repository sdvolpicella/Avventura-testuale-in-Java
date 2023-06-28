/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author valen
 */
public class User implements Serializable {

    private String username;

    int colpiSubiti = 0;

    public User() {
    }

    public int getColpiSubiti() {
        return colpiSubiti;
    }

    public void setColpiSubiti(int colpiSubiti) {
        this.colpiSubiti = colpiSubiti;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
