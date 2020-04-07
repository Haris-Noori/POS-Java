package zip_company.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import zip_company.Start;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerForgotPassword {

    private superController c = new superController();

    @FXML private TextField txtUsername;
    @FXML private Label question;
    @FXML private Button btnLogin;
    @FXML private TextField txtAnswer;

    public ControllerForgotPassword() throws SQLException {
    }

    public void openLoginPage() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-login.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }


    /**
     * This Function will check the username, by passing parameters to Controller
     * and Show message if not Found
     * If found then set the user Question on Label
     */
    public void checkUsername()
    {
        String Username = this.txtUsername.getText();

        if(Username.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Your Username Please!");
        }
        else
        {
            try
            {
                int i = Integer.parseInt(Username);
                JOptionPane.showMessageDialog(null,"Username cannot be a number");
            } catch (NumberFormatException e)
            {
                if(c.forgot_password(Username))     // If Username is found in database
                {
                    System.out.println("[ControllerForgotPassword]- " + Username + " is found");

                    this.question.setText(c.log.getQuestion());
                    this.btnLogin.setDisable(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,Username + " NOT FOUND!");
                }
            }
        }
    }

    public void openDashboard() throws IOException {
        String Answer = this.txtAnswer.getText();

        if(Answer.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Answer Cannot be Empty!");
        }
        else
        {
            if(Answer.equals(c.log.getAnswer()))
            {
                System.out.println("[ControllerForgotPassword]- Answer is Correct");
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-dashboard.fxml"));
                Start.root.setTitle("ZIP COMPANY");
                Start.root.setScene(new Scene(root));
                Start.root.show();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Answer is not Correct!");
            }
        }
    }
}
