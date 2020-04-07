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

public class ControllerEditProduct implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.menuButton.setText(username);
        this.btnActive.setStyle("-fx-background-color: #1269ba");
        this.btnActive.setTextFill(Paint.valueOf("#ffffff"));
    }
    @FXML private MenuButton menuButton;

    private superController c = new superController();
    private Integer deleteProductCode = null;
    private  String deleteProductName = null;

    @FXML private TextField txtSearchCode;

    @FXML private TextField txtCode;
    @FXML private TextField txtName;
    @FXML private TextField txtType;
    @FXML private TextField txtLength;
    @FXML private TextField txtQty;
    @FXML private TextField txtPrice;
    @FXML private TextArea txtDesc;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    @FXML private  Button btnActive;

    public ControllerEditProduct() throws SQLException {
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

    public void searchCode() throws SQLException {
        try {
            Integer ProductCode = Integer.valueOf(this.txtSearchCode.getText());
//            JOptionPane.showMessageDialog(null,"is number");

            if(ProductCode < 0)
            {
                JOptionPane.showMessageDialog(null,"Invalid Product Code");
                this.btnUpdate.setDisable(true);
                this.btnDelete.setDisable(true);
            }
            else if(this.txtSearchCode.getText().isEmpty())
            {
                this.btnUpdate.setDisable(true);
                this.btnDelete.setDisable(true);
            }
            else
            {
                if(c.check_product(ProductCode))
                {
                    deleteProductCode = ProductCode;
                    this.btnUpdate.setDisable(false);
                    this.btnDelete.setDisable(false);

                    products p = c.getProduct();
                    // Setting Product Information in TextFields
//
                    this.txtCode.setText(String.valueOf((p.getCode())));
                    this.txtName.setText(p.getName());
                    this.txtType.setText(p.getType());
                    this.txtLength.setText(String.valueOf(p.getLength()));
                    this.txtQty.setText(String.valueOf(p.getQty()));
                    this.txtPrice.setText(String.valueOf(p.getPrice()));
                    this.txtDesc.setText(p.getDescription());
                    c.nullProduct();

                }
                else
                {
                    this.btnUpdate.setDisable(true);
                    this.btnDelete.setDisable(true);
                    JOptionPane.showMessageDialog(null,"This Product Code is not in store");
                }
            }
        }catch (NumberFormatException e){
            String ProductName = this.txtSearchCode.getText();

            c.check_product(ProductName.toLowerCase());
//            JOptionPane.showMessageDialog(null,"name");
            if(this.txtSearchCode.getText().isEmpty())
            {
                this.btnUpdate.setDisable(true);
                this.btnDelete.setDisable(true);
            }
            else {
                if (c.check_product(ProductName)) {
                    deleteProductName = ProductName;
                    this.btnUpdate.setDisable(false);
                    this.btnDelete.setDisable(false);

                    products p = c.getProduct();
                    // Setting Product Information in TextFields
//
                    this.deleteProductCode = p.getCode();
                    this.txtSearchCode.setText(String.valueOf(p.getCode()));
                    this.txtCode.setText(String.valueOf((p.getCode())));
                    this.txtName.setText(p.getName());
                    this.txtType.setText(p.getType());
                    this.txtLength.setText(String.valueOf(p.getLength()));
                    this.txtQty.setText(String.valueOf(p.getQty()));
                    this.txtPrice.setText(String.valueOf(p.getPrice()));
                    this.txtDesc.setText(p.getDescription());
                    c.nullProduct();

                } else {
                    this.btnUpdate.setDisable(true);
                    this.btnDelete.setDisable(true);
                    JOptionPane.showMessageDialog(null, "This Product Code is not in store");
                }
            }
        }



    }

    public void updateProduct() throws SQLException, IOException {
        Integer Code = Integer.valueOf(txtCode.getText());
        String Name = txtName.getText();
        String Type = txtType.getText();
        Float Length = Float.valueOf(txtLength.getText());
        Integer Qty = Integer.valueOf(txtQty.getText());
        Float Price = Float.valueOf(txtPrice.getText());
        String Desc = txtDesc.getText();

        if(this.txtLength.getText().isEmpty()) { Length = 0f; }
        if (this.txtQty.getText().isEmpty()) { Qty = 0; }

//        JOptionPane.showMessageDialog(null,"Code: " + Code + "\n" +
//                "Name: " + Name + "\n" +
//                "Type: " + Type + "\n" +
//                "Length: " + Length + "\n" +
//                "Qty: " + Qty + "\n" +
//                "Price: " + Price + "\n" +
//                "Description: " + Desc);

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
            catch (Exception e) {
                try{    // If Name is some number
                    int i = Integer.parseInt(Type);
                    JOptionPane.showMessageDialog(null,"Product Type cannot be number");
                }
                catch (NumberFormatException ex) {
                    products p = new products(Code, Name, Type, Length, Qty, Price, Desc);
                    if(c.update_product(p,Integer.valueOf(txtSearchCode.getText())))
                    {
                        JOptionPane.showMessageDialog(null,"Product is Updated Successfully!!");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Product Update Failed :(");
                    }

                }

            }

            openEditProduct();

        }
    }

    public void deleteProduct() throws SQLException, IOException {
        int Reply = JOptionPane.showConfirmDialog(null,"This Product will be deleted");
        if(Reply == JOptionPane.YES_OPTION) {
            if(c.delete_product(deleteProductCode)){
                JOptionPane.showMessageDialog(null, "Product Deleted Successfully!!");

            }
            //else
            //{
                //JOptionPane.showMessageDialog(null, "Product Could Not Be Deleted ");
            //}
        }
        openEditProduct();

    }
}
