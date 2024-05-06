package com.example.demo2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.*;
import java.sql.*;
//--Users table
// CREATE TABLE Users (
//    UserID INT AUTO_INCREMENT PRIMARY KEY,
//    Name VARCHAR(100),
//    Email VARCHAR(100) ,
//    Role VARCHAR(50),
//    Password VARCHAR(100),
//    CNIC VARCHAR(15),
//    PhoneNumber VARCHAR(20),
//    Gender CHAR(1),
//    BMI FLOAT,
//    Height FLOAT,
//    Weight FLOAT,
//    Age INT,
//    BloodType VARCHAR(5),
//    DateOfJoining DATE
//);
//--Membership table
//CREATE TABLE Membership (
//    MembershipID INT AUTO_INCREMENT PRIMARY KEY,
//    UserID INT,
//    FOREIGN KEY (UserID) REFERENCES Users(UserID),
//    MembershipStatus ENUM('Active', 'Inactive'),
//    MembershipStartDate DATE,
//    MembershipEndDate DATE,
//    MembershipTier VARCHAR(50)
//);
//--Exercise table
//CREATE TABLE Exercise (
//    ExerciseID INT AUTO_INCREMENT PRIMARY KEY,
//    ExerciseName VARCHAR(100),
//    Description TEXT,
//    Difficulty ENUM('Easy', 'Medium', 'Hard', 'Expert')
//);



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Login Page");

        // Set the minimum and maximum window size
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxWidth(800);
        primaryStage.setMaxHeight(600);

        // Creating the layout - GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER); // Center align the grid contents
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set background color

        // Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setAlignment(Pos.CENTER); // Center align the username field

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setAlignment(Pos.CENTER); // Center align the password field

        // Dropdown for user roles
        ComboBox<String> userRole = new ComboBox<>();
        userRole.getItems().addAll("Admin", "Trainer", "Client");
        userRole.setPromptText("Select User Role");

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String selectedRole = userRole.getValue();

            Database aDatabase = new Database();

            boolean authorization = true;
            try {
                authorization = aDatabase.authenticateUser(username, password, selectedRole);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if (authorization==true) {
                primaryStage.close();
                openRoleSpecificPage(selectedRole); // Open specific page based on role
            } else {
                // Display an error message for unsuccessful authentication
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText(null);
                alert.setContentText("Invalid credentials or unauthorized access!");
                alert.showAndWait();
            }
        });

        // Adding components to the GridPane
        gridPane.add(new Label("Username:"), 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(new Label("Password:"), 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(new Label("User Role:"), 0, 2);
        gridPane.add(userRole, 1, 2);
        gridPane.add(submitButton, 1, 3);

        // Creating the scene and setting it on the stage
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openRoleSpecificPage(String role) {
        Stage newStage = new Stage();
        newStage.setTitle("Welcome " + role);

        if (role.equals("Admin")) {
            BorderPane adminPane = createAdminPage(); // Function to create Admin-specific page
            Scene adminScene = new Scene(adminPane, 800, 600);
            newStage.setScene(adminScene);
        } else if (role.equals("Trainer")) {
            // Create Trainer-specific scene

        } else if (role.equals("Client")) {
            // Create Client-specific scene
            newStage.setScene(new Scene(CreateClientPage(), 800, 600));

        }

        newStage.show();
    }

    private BorderPane createAdminPage() {
        BorderPane adminPane = new BorderPane();

        // Create options for Admin page (e.g., buttons to register/manage clients)
        Button registerClientButton = new Button("Register Client");
        Button manageClientAccountsButton = new Button("View Client Accounts");
        Button makeClientMembership = new Button("Make Client Membership");

        // Define actions for buttons
        registerClientButton.setOnAction(e -> {
            // Code to handle registering clients
            Stage newStage = new Stage();
            newStage.setTitle("Register Client");

            // Create a layout to display the form
            GridPane layout = new GridPane();
            layout.setPadding(new Insets(20));
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setAlignment(Pos.CENTER); // Center align the grid contents
            layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set background color

            // Create form fields
            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            nameField.setAlignment(Pos.CENTER); // Center align the username field

            TextField emailField = new TextField();
            emailField.setPromptText("Email");
            emailField.setAlignment(Pos.CENTER); // Center align the username field

            TextField passwordField = new TextField();
            passwordField.setPromptText("Password");
            passwordField.setAlignment(Pos.CENTER); // Center align the username field

            TextField cnicField = new TextField();
            cnicField.setPromptText("CNIC");
            cnicField.setAlignment(Pos.CENTER); // Center align the username field

            TextField phoneNumberField = new TextField();
            phoneNumberField.setPromptText("Phone Number");
            phoneNumberField.setAlignment(Pos.CENTER); // Center align the username field

            // Submit button
            Button submitButton = new Button("Submit");

            // Adding components to the GridPane
            layout.add(new Label("Name:"), 0, 0);
            layout.add(nameField, 1, 0);
            layout.add(new Label("Email:"), 0, 1);
            layout.add(emailField, 1, 1);
            layout.add(new Label("Password:"), 0, 2);
            layout.add(passwordField, 1, 2);
            layout.add(new Label("CNIC:"), 0, 3);
            layout.add(cnicField, 1, 3);
            layout.add(new Label("Phone Number:"), 0, 4);
            layout.add(phoneNumberField, 1, 4);
            layout.add(submitButton, 1, 5);

            // Create a scene and set it on the stage
            Scene scene = new Scene(layout, 800, 600);
            newStage.setScene(scene);
            newStage.show();

            submitButton.setOnAction(e1 -> {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String cnic = cnicField.getText();
                String phoneNumber = phoneNumberField.getText();

                Database aDatabase = new Database();

                try {
                    aDatabase.registerClient(name, email, password, cnic, phoneNumber);
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                newStage.close();
            });


        });

        manageClientAccountsButton.setOnAction(e -> {
// Mock Database class
            Database aDatabase = new Database();

            // Fetch data from the database and add it to the observable list
            ObservableList<String> clientList = FXCollections.observableArrayList();
            aDatabase.fetchClientData(clientList);

            // Create a layout to display the table
            VBox layout = new VBox(20);
            layout.setAlignment(Pos.CENTER);

            // Create the ListView and add a CSS style class
            ListView<String> listView = new ListView<>(clientList);
            listView.getStyleClass().add("client-list-view");

            layout.getChildren().addAll(new Label("Client Accounts"), listView);

            // Create a scene and set it on the stage
            Scene scene = new Scene(layout, 800, 600);

            // Load CSS for ListView styling
            scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

            Stage newStage = new Stage();
            newStage.setTitle("Client Accounts");
            newStage.setScene(scene);
            newStage.show();


        });

        makeClientMembership.setOnAction(e -> {
            // Code to handle registering clients
            Stage newStage = new Stage();
            newStage.setTitle("Make Client Membership");

            // Create a layout to display the form
            GridPane layout = new GridPane();
            layout.setPadding(new Insets(20));
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setAlignment(Pos.CENTER); // Center align the grid contents
            layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set background color

            // Create form fields
            TextField userIDField = new TextField();
            userIDField.setPromptText("User ID");
            userIDField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipStatusField = new TextField();
            membershipStatusField.setPromptText("Membership Status");
            membershipStatusField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipStartDateField = new TextField();
            membershipStartDateField.setPromptText("Membership Start Date");
            membershipStartDateField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipEndDateField = new TextField();
            membershipEndDateField.setPromptText("Membership End Date");
            membershipEndDateField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipTierField = new TextField();
            membershipTierField.setPromptText("Membership Tier");
            membershipTierField.setAlignment(Pos.CENTER); // Center align the username field

            // Submit button
            Button submitButton = new Button("Submit");

            // Adding components to the GridPane
            layout.add(new Label("User ID:"), 0, 0);
            layout.add(userIDField, 1, 0);
            layout.add(new Label("Membership Status:"), 0, 1);
            layout.add(membershipStatusField, 1, 1);
            layout.add(new Label("Membership Start Date:"), 0, 2);
            layout.add(membershipStartDateField, 1, 2);
            layout.add(new Label("Membership End Date:"), 0, 3);
            layout.add(membershipEndDateField, 1, 3);
            layout.add(new Label("Membership Tier:"), 0, 4);
            layout.add(membershipTierField, 1, 4);
            layout.add(submitButton, 1, 5);

            // Create a scene and set it on the stage
            Scene scene = new Scene(layout, 800, 600);
            newStage.setScene(scene);
            newStage.show();

            submitButton.setOnAction(e1 -> {
                int userID = Integer.parseInt(userIDField.getText());
                String membershipStatus = membershipStatusField.getText();
                Date membershipStartDate = Date.valueOf(membershipStartDateField.getText());
                Date membershipEndDate = Date.valueOf(membershipEndDateField.getText());
                String membershipTier = membershipTierField.getText();

                Database aDatabase = new Database();

                try {
                    aDatabase.makeMembership(userID, membershipStatus, membershipStartDate, membershipEndDate, membershipTier);
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                newStage.close();
            });
        });

        // Add buttons to the adminPane layout
        VBox adminOptions = new VBox(20);
        adminOptions.setAlignment(Pos.CENTER);
        adminOptions.getChildren().addAll(registerClientButton, manageClientAccountsButton, makeClientMembership);

        adminPane.setCenter(adminOptions);

        return adminPane;
    }

    private BorderPane CreateTrainerPage()
    {
        return null;
    }

    private BorderPane CreateClientPage()
    {
        BorderPane clientPane = new BorderPane();
        // Create 2 options to choose from 1 is start exercise and 2 is view exercise
        Button startExercise = new Button("Start Exercise");
        Button viewExercise = new Button("View Exercise");
        Button payMembership = new Button("Pay Membership");
        Button generateExercise = new Button("Generate Exercise");

        //on click of start exercise button it will open a new window with a list of exercises
        viewExercise.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("Start Exercise");

            // Create a database object to fetch data from the database
            Database aDatabase = new Database();

            // Create an observable list to store the data fetched from the database
            ObservableList<String> exerciseList = FXCollections.observableArrayList();

            // Fetch data from the database and add it to the observable list
            try {
                aDatabase.fetchExerciseData(exerciseList);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // Create a layout to display the table
            VBox layout = new VBox(20);
            layout.setAlignment(Pos.CENTER);

            // Create the ListView and add a CSS style class
            ListView<String> listView = new ListView<>(exerciseList);
            listView.getStyleClass().add("exercise-list-view");

            layout.getChildren().addAll(new Label("Exercise List"), listView);

            // Create a scene and set it on the stage
            Scene scene = new Scene(layout, 800, 600);

            // Load CSS for ListView styling
            scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

            newStage.setScene(scene);
            newStage.show();


        });

        startExercise.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("Start Exercise");

            String pythonScriptPath = "/Desktop/Test.py";

            // Create ProcessBuilder for running Python
            ProcessBuilder processBuilder = new ProcessBuilder("Python", System.getProperty("user.home") + pythonScriptPath);

            // Start the process
            Process process = null;
            try {
                process = processBuilder.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Read output from the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(line); // Print output of Python script
            }

            // Wait for the process to complete
            int exitCode = 0;
            try {
                exitCode = process.waitFor();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Python script execution completed with exit code: " + exitCode);


        });

        payMembership.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("Pay Membership");

            // Create a layout to display the form
            GridPane layout = new GridPane();
            layout.setPadding(new Insets(20));
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setAlignment(Pos.CENTER); // Center align the grid contents
            layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set background color

            // Create form fields
            TextField userIDField = new TextField();
            userIDField.setPromptText("User ID");
            userIDField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipStatusField = new TextField();
            membershipStatusField.setPromptText("Membership Status");
            membershipStatusField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipStartDateField = new TextField();
            membershipStartDateField.setPromptText("Membership Start Date");
            membershipStartDateField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipEndDateField = new TextField();
            membershipEndDateField.setPromptText("Membership End Date");
            membershipEndDateField.setAlignment(Pos.CENTER); // Center align the username field

            TextField membershipTierField = new TextField();
            membershipTierField.setPromptText("Membership Tier");
            membershipTierField.setAlignment(Pos.CENTER); // Center align the username field

            TextField cardNumberField = new TextField();
            cardNumberField.setPromptText("Card Number");
            cardNumberField.setAlignment(Pos.CENTER); // Center align the username field

            TextField cardHolderNameField = new TextField();
            cardHolderNameField.setPromptText("Card Holder Name");
            cardHolderNameField.setAlignment(Pos.CENTER); // Center align the username field


            // Submit button
            Button submitButton = new Button("Submit");

            // Adding components to the GridPane
            layout.add(new Label("User ID:"), 0, 0);
            layout.add(userIDField, 1, 0);
            layout.add(new Label("Membership Status:"), 0, 1);
            layout.add(membershipStatusField, 1, 1);
            layout.add(new Label("Membership Start Date:"), 0, 2);
            layout.add(membershipStartDateField, 1, 2);
            layout.add(new Label("Membership End Date:"), 0, 3);
            layout.add(membershipEndDateField, 1, 3);
            layout.add(new Label("Membership Tier:"), 0, 4);
            layout.add(membershipTierField, 1, 4);
            layout.add(new Label("Card Number:"), 0, 5);
            layout.add(cardNumberField, 1, 5);
            layout.add(new Label("Card Holder Name:"), 0, 6);
            layout.add(cardHolderNameField, 1, 6);
            layout.add(submitButton, 1, 7);



            // Create a scene and set it on the stage
            Scene scene = new Scene(layout, 800, 600);
            newStage.setScene(scene);
            newStage.show();

            submitButton.setOnAction(e1 -> {
                int userID = Integer.parseInt(userIDField.getText());
                String membershipStatus = membershipStatusField.getText();
                Date membershipStartDate = Date.valueOf(membershipStartDateField.getText());
                Date membershipEndDate = Date.valueOf(membershipEndDateField.getText());
                String membershipTier = membershipTierField.getText();
                String cardNumber = cardNumberField.getText();
                String cardHolderName = cardHolderNameField.getText();

                Database aDatabase = new Database();

                try {
                    aDatabase.makeMembership(userID, membershipStatus, membershipStartDate, membershipEndDate, membershipTier);
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                newStage.close();
            });
        });

        generateExercise.setOnAction(e -> {
            Stage newStage = new Stage();
            newStage.setTitle("Generate Exercise");

            // Create a layout to display the form
            GridPane layout = new GridPane();
            layout.setPadding(new Insets(20));
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setAlignment(Pos.CENTER); // Center align the grid contents
            layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); // Set background color

            // Create form fields
            TextField difficultyField = new TextField();
            difficultyField.setPromptText("Difficulty");
            difficultyField.setAlignment(Pos.CENTER); // Center align the username field

            // Submit button
            Button submitButton = new Button("Submit");

            // Adding components to the GridPane
            layout.add(new Label("Difficulty:"), 0, 0);
            layout.add(difficultyField, 1, 0);
            layout.add(submitButton, 1, 1);

            // Create a scene and set it on the stage
            Scene scene = new Scene(layout, 800, 600);
            newStage.setScene(scene);
            newStage.show();

            submitButton.setOnAction(e1 -> {
                String difficulty = difficultyField.getText();

                Database aDatabase = new Database();

                // Create an observable list to store the data fetched from the database
                ObservableList<String> exerciseList = FXCollections.observableArrayList();

                // Fetch data from the database and add it to the observable list
                aDatabase.getExcerciseOfDifficulty(exerciseList,difficulty);

                // Create a layout to display the table
                VBox layout1 = new VBox(20);
                layout1.setAlignment(Pos.CENTER);

                // Create the ListView and add a CSS style class
                ListView<String> listView = new ListView<>(exerciseList);
                listView.getStyleClass().add("exercise-list-view");

                layout1.getChildren().addAll(new Label("Exercise List"), listView);

                // Create a scene and set it on the stage
                Scene scene1 = new Scene(layout1, 800, 600);

                // Load CSS for ListView styling
                scene1.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

                newStage.setScene(scene1);
                newStage.show();

            });
        });

        // Add buttons to the Client layout
        VBox ClientOptions = new VBox(20);
        ClientOptions.setAlignment(Pos.CENTER);
        ClientOptions.getChildren().addAll(startExercise, viewExercise, payMembership, generateExercise);

        clientPane.setCenter(ClientOptions);
        return clientPane;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
