package org.example.model;

public class Cart {
    private int id;
    private int count;
    private Product product;
    private UserAccount userAccount;

    public Cart(Product product, int count, UserAccount userAccount) {
        this.count = count;
        this.product = product;
        this.userAccount = userAccount;
    }

    public Cart(){
    }

    public Cart(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", count=" + count +
                 "," + product +
                '}';
    }
}
