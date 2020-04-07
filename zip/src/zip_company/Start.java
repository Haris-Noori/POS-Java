package zip_company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import zip_company.models.databaseConnection;

import javax.swing.*;
import java.sql.SQLException;

import static java.lang.System.exit;

public class Start extends Application {

    public static databaseConnection database ;
    public static Stage root = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
//         Old Code
        Parent r = FXMLLoader.load(getClass().getResource("/zip_company/views/view-login.fxml"));
        root = primaryStage;
        primaryStage.setTitle("ZIP COMPANY");
        primaryStage.setScene(new Scene(r));
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            database = new databaseConnection();
            launch(args);
            System.out.println("[START]- Database Connection Established Successfully :)  ");
        } catch (SQLException e) {
            System.out.println("[START]- Database Connection Failed :(");
            JOptionPane.showMessageDialog(null,e.getMessage()+" connection error");
        }
        database.closed();
        database = null;
        exit(0);
    }


}