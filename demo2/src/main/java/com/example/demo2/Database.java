package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {
    public boolean authenticateUser(String name, String pass, String role) throws SQLException {
        // Initialize connection and prepared statement
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root", // Replace with your MySQL username
                "1215" // Replace with your MySQL password
        )) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE Email = ? AND Password = ? AND Role = ?;")) {

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, role);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    connection.close();
                    return true;
                } else {
                    connection.close();
                    return false;
                }
            }
        }
    }

    public void registerClient(String name, String email, String password, String cnic, String phoneNumber) throws SQLException {
        // Initialize connection and prepared statement
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root", // Replace with your MySQL username
                "1215" // Replace with your MySQL password
        )) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users (Name, Email, Role, Password, CNIC, PhoneNumber) VALUES (?, ?, ?, ?, ?, ?);")) {

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, "Client");
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, cnic);
                preparedStatement.setString(6, phoneNumber);

                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void fetchClientData(ObservableList<String> clientList1) {

        clientList1.add("Name\tEmail\tRole\tPassword\tCNIC\tPhoneNumber");

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root", // Replace with your MySQL username
                "1215" // Replace with your MySQL password
        )) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE Role = ?;")) {

                preparedStatement.setString(1, "Client");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String email = resultSet.getString("Email");
                    String role = resultSet.getString("Role");
                    String password = resultSet.getString("Password");
                    String cnic = resultSet.getString("CNIC");
                    String phoneNumber = resultSet.getString("PhoneNumber");

                    User aUser = new User(name, email, role, password, cnic, phoneNumber);

                    String record= name + "\t" + email + "\t" + role + "\t" + password + "\t" + cnic + "\t" + phoneNumber;
                    clientList1.add(record);

                }
                connection.close();
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void makeMembership(int userID, String membershipStatus, Date membershipStartDate, Date membershipEndDate, String membershipTier) throws SQLException {
        // Initialize connection and prepared statement
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root", // Replace with your MySQL username
                "1215" // Replace with your MySQL password
        )) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Membership (UserID, MembershipStatus, MembershipStartDate, MembershipEndDate, MembershipTier) VALUES (?, ?, ?, ?, ?);")) {

                preparedStatement.setInt(1, userID);
                preparedStatement.setString(2, membershipStatus);
                preparedStatement.setDate(3, membershipStartDate);
                preparedStatement.setDate(4, membershipEndDate);
                preparedStatement.setString(5, membershipTier);

                preparedStatement.executeUpdate();
                connection.close();
            }
        }
    }

    public void fetchExerciseData(ObservableList<String> exerciseList) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root", // Replace with your MySQL username
                "1215" // Replace with your MySQL password
        )) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Exercise;")) {

                ResultSet resultSet = preparedStatement.executeQuery();
                exerciseList.add("ExerciseID\tExerciseName\tDescription");

                while (resultSet.next()) {
                    int exerciseID = resultSet.getInt("ExerciseID");
                    String exerciseName = resultSet.getString("ExerciseName");
                    String description = resultSet.getString("Description");
                    String difficulty = resultSet.getString("Difficulty");

                   String isert= exerciseID + "\t" + exerciseName + "\t" + description ;
                    exerciseList.add(isert);
                }
                connection.close();
            }
        }
    }

    public void getExcerciseOfDifficulty(ObservableList<String> exerciseList, String Diff) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root", // Replace with your MySQL username
                "1215" // Replace with your MySQL password
        )) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Exercise where Difficulty = ?;")) {

                preparedStatement.setString(1, Diff);
                ResultSet resultSet = preparedStatement.executeQuery();
                exerciseList.add("ExerciseID\tExerciseName\tDescription");
                while (resultSet.next()) {
                    int exerciseID = resultSet.getInt("ExerciseID");
                    String exerciseName = resultSet.getString("ExerciseName");
                    String description = resultSet.getString("Description");
                    String difficulty = resultSet.getString("Difficulty");

                    String isert= exerciseID + "\t" + exerciseName + "\t" + description ;
                    exerciseList.add(isert);
                }
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
