package org.example.service;

import org.example.model.*;
import org.example.repository.CartRepo;

import java.util.ArrayList;

public class CartService {
    public void createCart(Product barcode , int count, UserAccount id) {
        CartRepo cartRepo = new CartRepo();
        Cart cart = new Cart(barcode,count,id);
        cartRepo.createCart(cart);
    }

    public  void deleteItem(int barcode) {
        CartRepo cartRepo = new CartRepo();
        Product product = new Product(barcode);
        Cart cart = new Cart(product);
        cartRepo.deleteItem(cart);
    }


    public void showTableForCart(int id){
        CartRepo cartRepo = new CartRepo();
        ArrayList<Cart> carts = cartRepo.showTableForCart(id);
        for (int i = 0; i <carts.size() ; i++) {
            System.out.println(carts.get(i));
        }
    }

    public void checkCount(){
            CartRepo cartRepo = new CartRepo();
            int recoredCount = cartRepo.checkRecordCount();
            if (recoredCount > 5){
                cartRepo.checkRecordCount();
                throw new RuntimeException("Your cart is full!");
            }

    }

}
