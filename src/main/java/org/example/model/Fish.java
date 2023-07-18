package org.example.model;

public class Fish {
    private int id;
    private int totalprice;
    private String confirm;
    private UserAccount userAccount;

    public Fish(int id, int totalprice, String confirm, UserAccount userAccount) {
        this.id = id;
        this.totalprice = totalprice;
        this.confirm = confirm;
        this.userAccount = userAccount;
    }

    public Fish(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    @Override
    public String toString() {
        return "Confirm{" +
                "totalprice=" + totalprice +
                ", confirm='" + confirm + '\'' +
                '}';
    }
}
