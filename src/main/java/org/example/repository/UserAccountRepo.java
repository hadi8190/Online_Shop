package org.example.repository;

import org.example.config.DatabaseConnection;
import org.example.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRepo {

    private static final String INSERT_QUERY = "insert into useraccount(username,nationalcode) values (?,?)";
    private static final String SELECT_BY_PASSWORD = "select * from useraccount where nationalcode=?";
    private static final String SELECT_QUERY = "select * from useraccount where (nationalcode=?)";

    public void createAdmin(UserAccount userAccount) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, userAccount.getUsername());
            preparedStatement.setString(2, userAccount.getNationalcode());



            preparedStatement.execute();
            System.out.println("Your account created Successfully");

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public  UserAccount findUserAccountByPassword(String inputNationalcode) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_PASSWORD);
            preparedStatement.setString(1, inputNationalcode);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id =resultSet.getInt(1);
                String username = resultSet.getString(2);
                String nationalcode = resultSet.getString(3);
                return new UserAccount(id,username,nationalcode);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public UserAccount findUserAccount(String inputnationalcode) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, inputnationalcode);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id =resultSet.getInt(1);
                String username = resultSet.getString(2);
                String nationalcode = resultSet.getString(3);
                return new UserAccount(id,username,nationalcode);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
