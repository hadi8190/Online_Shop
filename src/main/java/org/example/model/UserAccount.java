package org.example.model;

public class UserAccount {
    private int id;
    private String username;
    private String nationalcode;

    public UserAccount(int id, String username, String nationalcode) {
        this.id = id;
        this.username = username;
        this.nationalcode = nationalcode;
    }

    public UserAccount(String username, String nationalcode) {
        this.username = username;
        this.nationalcode = nationalcode;
    }

    public UserAccount(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public String getNationalcode() {
        return nationalcode;
    }
}
