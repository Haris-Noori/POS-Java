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
import zip_company.models.products;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static zip_company.Controllers.superController.userType;
import static zip_company.Controllers.superController.username;

public class ControllerStore implements Initializable {


    private superController c = new superController();

    @FXML private TextField txtSearchCode;
    @FXML private MenuButton menuButton;
    @FXML private TableView mainGrid;
    @FXML private TableColumn txtCode;
    @FXML private TableColumn txtName;
    @FXML private TableColumn txtType;
    @FXML private TableColumn txtLength;
    @FXML private TableColumn txtQty;
    @FXML private TableColumn txtPrice;
    @FXML private TableColumn txtDesc;
    @FXML private Button btnActive;

    public ControllerStore() throws SQLException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Setting Property Value Factory for coloumns (whatever that mans :p)
            this.menuButton.setText(username);
            this.btnActive.setStyle("-fx-background-color: #1269ba");
            this.btnActive.setTextFill(Paint.valueOf("#ffffff"));

            txtCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            txtName.setCellValueFactory(new PropertyValueFactory<>("name"));
            txtType.setCellValueFactory(new PropertyValueFactory<>("type"));
            txtLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            txtQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            txtPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            txtDesc.setCellValueFactory(new PropertyValueFactory<>("description"));


            ResultSet rs  = c.show_all_products();
            int i = 0;
            while (rs.next()){
                if(i>0) {
                    mainGrid.getItems().add(new products(rs.getInt("code"), rs.getString("name"), rs.getString("type"),
                            rs.getFloat("length"), rs.getInt("quantity"), rs.getFloat("price"), rs.getString("description")));
                }
                i+=1;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"[STORE]~ Something Went Wrong while setting Data");
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

    public void searchProductDetails() throws SQLException {

        try {
            Integer SearchCode = Integer.valueOf(this.txtSearchCode.getText());
//            JOptionPane.showMessageDialog(null,"is number");
            c.check_product(SearchCode);
        }catch (NumberFormatException e){
            String SearchCode = this.txtSearchCode.getText();

            c.check_product(SearchCode.toLowerCase());
//            JOptionPane.showMessageDialog(null,"name");
        }

        this.addDataToTable(c.getProduct());
        c.nullProduct();

//        Product p = new Product(10, "Name", "A", 10, 10,200, "No Description");
//        addDataToTable(p);
    }

    public void clearTable()
    {
        mainGrid.getItems().removeAll(mainGrid.getItems());
    }

    public void addDataToTable(products P)
    {
        this.clearTable();
        mainGrid.getItems().add(P);
    }


}
