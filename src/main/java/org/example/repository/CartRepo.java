package org.example.repository;

import org.example.config.DatabaseConnection;
import org.example.model.Cart;
import org.example.model.Product;
import org.example.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartRepo {
    private static final String INSERT_QUERY = "insert into cart(barcode,count,user_id) values (?,?,?)";
    private static final String DELETE_QUERY = "DELETE FROM cart where barcode = ?";
    private static final String QUERY_FOR_SHOW_TABLE = "SELECT * FROM cart where user_id = ?";
    private static final String QUERY_FOR_CHECK_COUNT = "SELECT count(*) FROM cart";


    public void createCart(Cart cart) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, cart.getProduct().getBarcode());
            preparedStatement.setInt(2,cart.getCount());
            preparedStatement.setInt(3, cart.getUserAccount().getId());


            preparedStatement.execute();
            System.out.println("cart updated Successfully");

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public ArrayList<Cart> deleteItem(Cart cart) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, cart.getProduct().getBarcode());

            preparedStatement.execute();
            System.out.println("Item deleted successfully.");

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public ArrayList<Cart> showTableForCart(int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FOR_SHOW_TABLE);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Cart> carts = new ArrayList<>();
            while (resultSet.next()) {
                ProductRepo productRepo = new ProductRepo();
                Cart cart = new Cart();
                cart.setId(resultSet.getInt("id"));
                cart.setCount(resultSet.getInt("count"));
                int barcode = resultSet.getInt("barcode");
                int id1 = resultSet.getInt("user_id");
                UserAccount userAccount = new UserAccount(id1);
                Product product1 = productRepo.findDataForProduct(barcode);
                cart.setProduct(product1);
                cart.setUserAccount(userAccount);

                carts.add(cart);
            }
            resultSet.close();
            connection.close();
            return carts;


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public int checkRecordCount() {
        int count = 0;
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FOR_CHECK_COUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                 count = resultSet.getInt(1);

            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }


    }
