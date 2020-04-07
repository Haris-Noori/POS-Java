package zip_company.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import zip_company.Start;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerLogin {
    private superController c = new superController();

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPass;

    public ControllerLogin() throws SQLException {

    }

    public void openDashboard() throws IOException {

        String username = txtUsername.getText();
        String password = txtPass.getText();

//        JOptionPane.showMessageDialog(null,"Username: " + username + "\n" + "Pass: " + password);

        if(username.isEmpty() && password.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter username and password");
        }
        else if(username.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter Username");
        }
        else if(password.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter Password");
        }
        else
        {
            try {
//                JOptionPane.showMessageDialog(null, "IN TRY CASE");
                int i = Integer.parseInt(username);
                JOptionPane.showMessageDialog(null, "Username cannot be number");
            } catch (NumberFormatException e) {
//
                if(c.get_login_details(username,password))    // Username and password is validation from Database
                {
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-dashboard.fxml"));
                    Start.root.setTitle("ZIP COMPANY");
                    Start.root.setScene(new Scene(root));
                    Start.root.show();
                }


            }
//            JOptionPane.showMessageDialog(null, "Out of TRY-CATCH");
        }

    }

    public void openForgotPassword() throws  IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-forgotPassword.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }
}
