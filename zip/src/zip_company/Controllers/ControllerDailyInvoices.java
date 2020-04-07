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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static zip_company.Controllers.ControllerCreateInvoice.FinalInvoice;
import static zip_company.Controllers.superController.userType;
import static zip_company.Controllers.superController.username;

class customerData{
    private float tax, total, remaining;
    private  int id;
    private  String cust, user;

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getRemaining() {
        return remaining;
    }

    public void setRemaining(float remaining) {
        this.remaining = remaining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public customerData(float tax, float total, float remaining, int id, String cust, String user) {
        this.tax = tax;
        this.total = total;
        this.remaining = remaining;
        this.id = id;
        this.cust = cust;
        this.user = user;

    }
}

public class ControllerDailyInvoices implements Initializable {
    private superController c = new superController();
    private  ResultSet rs;

    @FXML private DatePicker txtSearchDate;

    @FXML private TableView mainGrid;
//    @FXML private TableColumn txtDate;
    @FXML private TableColumn colBill;
    @FXML private TableColumn colCust;
    @FXML private TableColumn colRemaining;
    @FXML private TableColumn colCreator;
    @FXML private MenuButton menuButton;
    @FXML private Button btnActive;

    @FXML private TableView productGrid;
    @FXML private TableColumn colCode;
    @FXML private TableColumn colQty;
    @FXML private TableColumn colPrice;
    @FXML private Label labelRemaining;
    @FXML private TextField txtRemaining;
    @FXML private Button btnUpdateRemaining;
    @FXML private Button btnPrintInvoice;
    @FXML private Button btnDeleteInvoice;


    private LinkedList<customerData> tableData = new LinkedList<>();
    private LinkedList<products> ProductsList = new LinkedList<products>();
    int InvoiceIndex = 0;

    public ControllerDailyInvoices() throws SQLException {
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

    public void populate_table(){
        try {
            // Setting Property Value Factory for columns (whatever that mans :p)

            ResultSet rs = c.show_daily_sales();
            tableData = new LinkedList<customerData>();

            while (rs.next()){
                customerData temp = new customerData(rs.getFloat("tax"), rs.getFloat("total"), rs.getFloat("discount"),
                        rs.getInt("id"), rs.getString("customer"), rs.getString("username"));
                tableData.add(temp);
                this.mainGrid.getItems().add(new dailySalesTab(rs.getFloat("discount"),
                        rs.getFloat("total"),rs.getString("customer"),rs.getString("username")));
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelRemaining.setText("-");
        this.menuButton.setText(username);
        this.btnActive.setStyle("-fx-background-color: #1269ba");
        this.btnActive.setTextFill(Paint.valueOf("#ffffff"));


        // Setting Property Value Factory for columns (whatever that mans :p)
        colCust.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        colRemaining.setCellValueFactory(new PropertyValueFactory<>("Remaining"));
        colBill.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colCreator.setCellValueFactory(new PropertyValueFactory<>("user"));


        this.populate_table();

        /**
         * Side Table
         */
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        txtRemaining.setText(String.valueOf(tableData.element().getRemaining()));
    }


    /**
     * To Clear All The Table
     */
    public void clearTable()
    {
        mainGrid.getItems().removeAll(mainGrid.getItems());
        mainGrid.refresh();

        this.labelRemaining.setText("-");
        this.txtRemaining.setDisable(true);
        this.btnUpdateRemaining.setDisable(true);
        this.btnDeleteInvoice.setDisable(true);
        this.btnPrintInvoice.setDisable(true);
        this.InvoiceIndex = 0;
    }

    /**
     * This function will recieve an INVOICE instance and show data in TableView
     */
    public void searchInvoiceDetails()
    {
        this.labelRemaining.setText("-");
        this.txtRemaining.setDisable(true);
        this.btnUpdateRemaining.setDisable(true);
        this.btnDeleteInvoice.setDisable(true);
        this.btnPrintInvoice.setDisable(true);
        this.InvoiceIndex = 0;
        clearProdcutsTable();

        clearTable();
        tableData.clear();
        tableData = null;
        // Check If Date is not Empty
        try {
            LinkedList<Integer> deletedIds = new LinkedList<Integer>();
            String SearchedDate = String.valueOf(Date.valueOf(this.txtSearchDate.getValue()));
            System.out.println("[DailyInvoice]- Searched Date: " + SearchedDate);
//            JOptionPane.showMessageDialog(null,"You Selected: " + SearchedDate);

             rs = c.show_daily_sales(SearchedDate);
             tableData = new LinkedList<customerData>();

            while (rs.next()){
                customerData temp = new customerData(rs.getFloat("tax"), rs.getFloat("total"), rs.getFloat("discount"),
                        rs.getInt("id"), rs.getString("customer"), rs.getString("username"));
                tableData.add(temp);
                this.mainGrid.getItems().add(new dailySalesTab(rs.getFloat("discount"),
                        rs.getFloat("total"),rs.getString("customer"),rs.getString("username")));


            }

        }
        catch(Exception e)
        {   // If Date is Empty
            JOptionPane.showMessageDialog(null,"You did not select any date!");
        }
    }


    /**
     * Function will get the index of row selected and extract the product details to show on the side TableView
     */
    public void showProductDetails() throws SQLException {
        clearProdcutsTable();
        InvoiceIndex = mainGrid.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + InvoiceIndex);

        if(tableData.get(InvoiceIndex).getRemaining() == 0f)
        {
            this.labelRemaining.setText("-");
            this.txtRemaining.setDisable(true);
            this.btnUpdateRemaining.setDisable(true);
            this.btnDeleteInvoice.setDisable(false);
        }
        else
        {
            this.labelRemaining.setText(String.valueOf(tableData.get(InvoiceIndex).getRemaining()));
            this.txtRemaining.setText(String.valueOf(tableData.get(InvoiceIndex).getRemaining()));
            this.txtRemaining.setDisable(false);
            this.btnUpdateRemaining.setDisable(false);
            this.btnDeleteInvoice.setDisable(false);
        }


        int InvoiceId = tableData.get(InvoiceIndex).getId();
        System.out.println("Invoice ID: " + InvoiceId);

        ProductsList = c.get_products_invoice(InvoiceId);

        int listSize = ProductsList.size();
        for(int j = 0; j < listSize; j++)
        {
            System.out.println(ProductsList.get(j));
            addDataToTable(ProductsList.get(j));
        }

    }

    public void updateRemaining() throws IOException {
        Float newRemaining = Float.valueOf(this.txtRemaining.getText());
        if(newRemaining < 0)
        {
            JOptionPane.showMessageDialog(null,"Invalid Remaining Amount!");
        }
        else if(newRemaining == tableData.get(InvoiceIndex).getRemaining())
        {
            JOptionPane.showMessageDialog(null,"New balance cannot be same as old balance!");
        }
        else if(newRemaining > tableData.get(InvoiceIndex).getRemaining())
        {
            JOptionPane.showMessageDialog(null,"New balance can not be more than old balance!");
        }
        else
        {
//            this.btnUpdateRemaining.setDisable(false);

            /**
             * UPDATE REMAINING VALUE IN DATABASE
             */
            if(c.update_remaining(tableData.get(InvoiceIndex).getId(), newRemaining))
            {
                TableView t = new TableView();
                t = mainGrid;
                t.setSelectionModel(mainGrid.getSelectionModel());
                mainGrid.getItems().removeAll(mainGrid.getItems());
                this.populate_table();
                mainGrid.setSelectionModel(t.getSelectionModel());

                tableData.get(InvoiceIndex).setRemaining(newRemaining);
                JOptionPane.showMessageDialog(null,"Invoice Updated Successfully!");
                this.btnPrintInvoice.setDisable(false);
                this.labelRemaining.setText(String.valueOf(newRemaining));

//                mainGrid.getSelectionModel().getSelectedCells().set(1,newRemaining);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invoice Update Failed !!");
                this.btnPrintInvoice.setDisable(true);
            }
        }
    }

    public void deleteInvoice() throws IOException {

        int Reply = JOptionPane.showConfirmDialog(null,"This Invoice will be deleted");
        if(Reply == JOptionPane.YES_OPTION) {
            if(c.delete_invoice(tableData.get(InvoiceIndex).getId()))
            {
                clearProdcutsTable();
                mainGrid.getSelectionModel().getSelectedItems().forEach(mainGrid.getItems()::remove);
                this.btnPrintInvoice.setDisable(true);
                JOptionPane.showMessageDialog(null,"Invoice Deleted Successfully!");
                openDailyInvoices();
            }
            else {
                JOptionPane.showMessageDialog(null,"Invoice Deletion Failed !");
            }
        }

    }

    public void printInvoice() throws IOException {
        /**
         * PRINT INVOICE CODE
         * reset the FinalInvoice values and then uncomment the below code
         */
        FinalInvoice.setBill(tableData.get(InvoiceIndex).getTotal());
        FinalInvoice.setCreator(tableData.get(InvoiceIndex).getUser());
        FinalInvoice.setCustomer(tableData.get(InvoiceIndex).getCust());
        FinalInvoice.setRemaining(tableData.get(InvoiceIndex).getRemaining());
        FinalInvoice.setTax(tableData.get(InvoiceIndex).getTax());
        FinalInvoice.setProducts(ProductsList);

        JOptionPane.showMessageDialog(null,"~Final Invoice~\n" +
                    "Date: " + FinalInvoice.getDate() + "\n" +
                    "Customer: " + FinalInvoice.getCustomer() + "\n" +
                    "Creator: " + FinalInvoice.getCreator() + "\n" +
                    "Number of Products: " + FinalInvoice.getProducts().size() + "\n" +
                    "Tax: " + FinalInvoice.getTax() + "\n" +
                    "Remaining: " + FinalInvoice.getRemaining() + " \n" +
                    "Bill: " + FinalInvoice.getBill()
            );

        c.add_sale(FinalInvoice.getBill(), FinalInvoice.getCreator(),  FinalInvoice.getCustomer(),
                FinalInvoice.getRemaining(), FinalInvoice.getTax(),
                FinalInvoice.getProducts(),true);

        openDailyInvoices();
    }

    public void addDataToTable(products P)
    {
        productGrid.getItems().add(P);
    }

    public void clearProdcutsTable()
    {
        productGrid.getItems().removeAll(productGrid.getItems());
//        productGrid.refresh();
        this.labelRemaining.setText("-");
        this.txtRemaining.setDisable(true);
        this.btnUpdateRemaining.setDisable(true);
//        this.btnDeleteInvoice.setDisable(true);
        this.InvoiceIndex = 0;
    }
}
