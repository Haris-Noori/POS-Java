package zip_company.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import zip_company.Start;
import zip_company.models.products;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static zip_company.Controllers.superController.userType;
import static zip_company.Controllers.superController.username;

public class ControllerDashboard implements  Initializable{

    private superController c = new superController();
    @FXML private MenuButton menuButton;
    @FXML private TextField txtCode;
    @FXML private TextField txtName;
    @FXML private TextField txtType;
    @FXML private TextField txtLength;
    @FXML private TextField txtQty;
    @FXML private TextField txtPrice;
    @FXML private TextArea txtDesc;
    @FXML private Button btnActive;

    public ControllerDashboard() throws SQLException {
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
//        JOptionPane.showMessageDialog(null,userType);


        if (userType.equals("superuser")){
//            JOptionPane.showMessageDialog(null,"daddy");
            System.out.println("[ControllerDashboard]- User is SuperAdmin");

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-addAdmins.fxml"));
            Start.root.setTitle("ZIP COMPANY");
            Start.root.setScene(new Scene(root));
            Start.root.show();
        }
        else{

//            JOptionPane.showMessageDialog(null,"son");
            System.out.println("[ControllerDashboard]- User is not SuperAdmin");
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-checkSuperAdmin.fxml"));
            Start.root.setTitle("ZIP COMPANY");
            Start.root.setScene(new Scene(root));
            Start.root.show();
        }
    }

    public void addNewProduct() throws IOException, SQLException {
        Integer Code = Integer.valueOf(txtCode.getText());
        String Name = txtName.getText();
        String Type = txtType.getText();
        Float Length = Float.valueOf(txtLength.getText());
        Integer Qty = Integer.valueOf(txtQty.getText());
        Float Price = Float.valueOf(txtPrice.getText());
        String Desc = txtDesc.getText();

        if(this.txtLength.getText().isEmpty()) { Length = 0f; }
        if (this.txtQty.getText().isEmpty()) { Qty = 0; }

        JOptionPane.showMessageDialog(null,"Code: " + Code + "\n" +
                "Name: " + Name + "\n" +
                "Type: " + Type + "\n" +
                "Length: " + Length + "\n" +
                "Qty: " + Qty + "\n" +
                "Price: " + Price + "\n" +
                "Description: " + Desc);

        if(Code == 0)
        {
            JOptionPane.showMessageDialog(null,"Product Code cannot be Empty!");
        }
        else if(Code < 0)
        {
            JOptionPane.showMessageDialog(null,"Invalid Product Code");
        }
        else if(Name.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Product Name cannot be Empty!");
        }
        else if(Type.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Product Type cannot be Empty!");
        }
        else if(Length < 0)
        {
            JOptionPane.showMessageDialog(null,"Invalid Product Length!");
        }
        else if(Qty < 0)
        {
            JOptionPane.showMessageDialog(null,"Invalid Product Quantity");
        }
        else if(Price == 0)
        {
            JOptionPane.showMessageDialog(null,"Product Price cannot be Empty!");
        }
        else if(Price < 0)
        {
            JOptionPane.showMessageDialog(null,"Invalid Product Price!");
        }
        else    // If All Fields are not Empty
        {
            try{    // If Name is some number
                int i = Integer.parseInt(Name);
                JOptionPane.showMessageDialog(null,"Product Name cannot be number");
            }
            catch (NumberFormatException ex) {

                try{    // If Name is some number
                    int i = Integer.parseInt(Type);
                    JOptionPane.showMessageDialog(null,"Product Type cannot be number");
                }
                catch (NumberFormatException e) {

                    products p = new products(Code, Name, Type, Length, Qty, Price, Desc);
                    if(c.add_product(p) )
                    {
                        JOptionPane.showMessageDialog(null,"New Product Added!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Product did not add!");
                    }

                }

            }


        }

        openAddNewProduct();
    }
}
