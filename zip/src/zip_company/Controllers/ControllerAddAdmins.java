package zip_company.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import zip_company.Start;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static zip_company.Controllers.superController.userType;
import static zip_company.Controllers.superController.username;

public class ControllerAddAdmins implements Initializable {

    private superController c = new superController();

    @FXML private MenuButton menuButton;
    @FXML private TextField txtNewUsername;
    @FXML private TextField txtNewPassword;
    @FXML private TableView mainGrid;
    @FXML private TableColumn columnAdminName;
    @FXML private  TextField txtUsername;
    @FXML private Button btnActive;

    public ControllerAddAdmins() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.menuButton.setText(username);
        this.btnActive.setStyle("-fx-background-color: #1269ba");
        this.btnActive.setTextFill(Paint.valueOf("#ffffff"));

        try {
            // Setting Property Value Factory for columns (whatever that mans :p)
            columnAdminName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ResultSet rs = c.get_users();
            while (rs.next()){
                addDataToTable(new Users(rs.getString("name")));
            }

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"[ControllerAddAdmins]~ Something Went Wrong while setting Data");
        }
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
//        JOptionPane.showMessageDialog(null,userType);
        if (userType.equals("superuser")){
//            JOptionPane.showMessageDialog(null,"daddy");
            System.out.println("[ControllerAddAdmins]- User is SuperAdmin");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-addAdmins.fxml"));
            Start.root.setTitle("ZIP COMPANY");
            Start.root.setScene(new Scene(root));
            Start.root.show();
        }
        else{
//            JOptionPane.showMessageDialog(null,"son");
            System.out.println("[ControllerAddAdmins]- User is SuperAdmin");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-checkSuperAdmin.fxml"));
            Start.root.setTitle("ZIP COMPANY");
            Start.root.setScene(new Scene(root));
            Start.root.show();
        }
    }

    public void addNewAdmin() throws SQLException {
        String newUsername = this.txtNewUsername.getText();
        String newPassword = this.txtNewPassword.getText();

        if(newUsername.isEmpty() && newPassword.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter username and password");
        }
        else if(newUsername.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter Username");
        }
        else if(newPassword.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Enter Password");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Username: " + newUsername + "\n" + "Pass: " + newPassword);

            try
            {
                int i = Integer.parseInt(newUsername);
                JOptionPane.showMessageDialog(null, "Username cannot be number");
            } catch (NumberFormatException e){

                /**
                 * SEND Username Password to Controller, so can be saved in Database
                 */
                if(c.create_user(newUsername, newPassword))
                {
                    JOptionPane.showMessageDialog(null,"New Admin Created!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"New Admin Could not be created!");
                }

            }
        }
    }

    /**
     * This function will remove the Admin from the DataBase
     */
    public void removeAdmin() throws SQLException {
        String AdminName = this.txtUsername.getText();

        if(AdminName.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter the Admin name!");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Admin Name: " + AdminName);

            if(c.delete_user(AdminName))
            {
                JOptionPane.showMessageDialog(null,AdminName + " is removed!");
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Something went wrong while removing Admin!");
            }
        }
    }


    public void addDataToTable(Users A)
    {
        mainGrid.getItems().add(A);
    }

    public void clearTable()
    {
        mainGrid.getItems().removeAll(mainGrid.getItems());
    }
}
