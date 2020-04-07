package zip_company.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import zip_company.Start;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static zip_company.Controllers.superController.userType;
import static zip_company.Controllers.superController.username;

public class ControllerMyProfile implements Initializable {

    private superController c = new superController();

    @FXML private MenuButton menuButton;
    @FXML private TextField txtNewPassword;

    @FXML private MenuButton btnMenu;
    @FXML private MenuItem menuItem1;
    @FXML private MenuItem menuItem2;
    @FXML private MenuItem menuItem3;
    @FXML private Button btnActive;
    @FXML private TextField txtAnswer;

    public ControllerMyProfile() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.menuButton.setText(username);
        this.btnActive.setStyle("-fx-background-color: #1269ba");
        this.btnActive.setTextFill(Paint.valueOf("#ffffff"));
    }

    public void openAddNewProduct() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-dashboard.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openEditProduct() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-editProduct.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openViewStore() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-store.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openDailyInvoices() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-invoices.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openCreateInvoice() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-createInvoice.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openLoginPage() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-login.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openMyProfile() throws  IOException{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-myprofile.fxml"));
        Start.root.setTitle("ZIP COMPANY");
        Start.root.setScene(new Scene(root));
        Start.root.show();
    }

    public void openAddRemoveAdmin() throws IOException {

            /**
             * YOUR CODE FOR CHECK ADMIN TYPE,
             * If Super Admin, then give access
             */
            if (userType.equals("superuser")){
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-addAdmins.fxml"));
                Start.root.setTitle("ZIP COMPANY");
                Start.root.setScene(new Scene(root));
                Start.root.show();
            }
            else{

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-checkSuperAdmin.fxml"));
                Start.root.setTitle("ZIP COMPANY");
                Start.root.setScene(new Scene(root));
                Start.root.show();
            }
        }

    public void updateNewPassword()
    {
        String Password = this.txtNewPassword.getText();

        if(Password.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Type any password!");
        }
        else
        {
            if(c.updatePassword(username, Password))
            {
                JOptionPane.showMessageDialog(null,"Password Updated!");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Something went wrong when updating password :(");
            }
        }
    }

    public void selectOptionOne()
    {
        this.btnMenu.setText(this.menuItem1.getText());
    }

    public void selectOptionTwo()
    {
        this.btnMenu.setText(this.menuItem2.getText());
    }

    public void selectOptionThree()
    {
        this.btnMenu.setText(this.menuItem3.getText());
    }

    public void setQuestionAnswer()
    {
        String Question = this.btnMenu.getText();
        String Answer = this.txtAnswer.getText();


        if(Question.equals(this.menuItem1.getText()) || Question.equals(this.menuItem2.getText()) || Question.equals(this.menuItem3.getText())  )
        {
             if(Answer.isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"You did not Answer the Question you selected!");
                }
            else
            {
                if(c.add_question_answer(username,Question, Answer))
                {
                    JOptionPane.showMessageDialog(null,"Your Question & Answer is Set!\n" +
                            "You have made your account more secure");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Your Question & Answer are NOT added");
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Please Select any Question!");
        }

    }
}
