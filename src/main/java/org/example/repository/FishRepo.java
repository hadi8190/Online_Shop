package org.example.repository;

import org.example.config.DatabaseConnection;
import org.example.model.Fish;
import org.example.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FishRepo {
    private static final String INSERT_QUERY = "insert into fish(user_id) values (?)";
    private static final String UPDATE_QUERY = "update fish set totalprice = (totalprice + ?) where user_id = ?";
    private static final String UPDATE_CONFIRM_QUERY = "update fish set confirm = ? where user_id = ?";
    private static final String SELECT_QUERY = "select * from fish where user_id = ?";


    public void createConfirm(Fish fish) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, fish.getUserAccount().getId());



            preparedStatement.execute();
            System.out.println("Your cart created Successfully");

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Fish updateTotalPrice(int totalPrice, int id) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(1, totalPrice);
            preparedStatement.setInt(2, id);


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public void updateConfirm(Fish fish, int confirm1){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONFIRM_QUERY);

            if(confirm1 == 1)
                preparedStatement.setString(1, "Yes");
            else if (confirm1 == 2)
                preparedStatement.setString(1, "No");

            preparedStatement.setInt(2, fish.getUserAccount().getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public Fish findConfirmFromConfirmTable(int id1) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setInt(1, id1);
            ResultSet resultSet = preparedStatement.executeQuery();

            UserAccount userAccount = new UserAccount(id1);
            Fish fish1 = new Fish(userAccount);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String confirm = resultSet.getString("confirm");
                int totalPrice = resultSet.getInt("totalprice");
                int id2 = resultSet.getInt("user_id");
                UserAccount userAccount1 = new UserAccount(id2);
                return new Fish(id,totalPrice,confirm,userAccount1);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
