package org.example.repository;

import org.example.config.DatabaseConnection;
import org.example.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductRepo {
    private static final String QUERY_FOR_SHOW_TABLE = "SELECT * FROM product where code = ?";
    private static final String QUERY_FOR_FIND_DATA = "SELECT * FROM product where barcode = ?";



    public ArrayList<Product> showTableForProduct(int code) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FOR_SHOW_TABLE);
            preparedStatement.setInt(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCount(resultSet.getInt("count"));
                product.setPrice(resultSet.getInt("price"));
                product.setBarcode(resultSet.getInt("barcode"));
                product.setDescription(resultSet.getString("description"));


                products.add(product);
            }
            resultSet.close();
            connection.close();
            return products;


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Product findDataForProduct(int barcode) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FOR_FIND_DATA);
            preparedStatement.setInt(1,barcode);
            ResultSet resultSet = preparedStatement.executeQuery();


            Product product = new Product();
            while (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCount(resultSet.getInt("count"));
                product.setPrice(resultSet.getInt("price"));
                product.setBarcode(resultSet.getInt("barcode"));
                product.setDescription(resultSet.getString("description"));


            }

            preparedStatement.close();
            connection.close();

            return product;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
