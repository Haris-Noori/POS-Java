package zip_company.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zip_company.Start;
import zip_company.models.products;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static zip_company.Controllers.superController.userType;
import static zip_company.Controllers.superController.username;

public class ControllerCreateInvoice implements Initializable {

    public ControllerCreateInvoice() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.menuButton.setText(username);
        this.btnActive.setStyle("-fx-background-color: #1269ba");
        this.btnActive.setTextFill(Paint.valueOf("#ffffff"));


        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

//        mainGrid.setItems(ProductsList);
    }

    private superController c = new superController();
    private products ps;
    static Invoice FinalInvoice = new Invoice();
    static ControllerPreviewInvoice CP;
    static {
        try {
            CP = new ControllerPreviewInvoice();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"invoice preview error");
        }
    }

    private final TableView<products> table = new TableView<>();
    private LinkedList<products> ProductsList = new LinkedList<>();

    @FXML private TextField txtSearchCode;
    @FXML private MenuButton menuButton;

    @FXML private Button btnActive;

    @FXML private  Label labelCode;
    @FXML private Label labelName;
    @FXML private Label labelDesc;
    @FXML private Label labelType;
    @FXML private Label labelLength;
    @FXML private Label labelStock;
    @FXML private Label labelPrice;

    @FXML private TextField txtQty;
    @FXML private TextField txtRemaining;
    @FXML private TextField txtTax;
    @FXML private TextField txtCust;

    @FXML private Text labelBill;

    @FXML private Button btnApply;
    @FXML private Button btnAddInInvoice;

    @FXML private TableView mainGrid;
    @FXML private TableColumn colCode;
    @FXML private TableColumn colName;
    @FXML private TableColumn colType;
    @FXML private TableColumn colLength;
    @FXML private TableColumn colQty;
    @FXML private TableColumn colPrice;
    @FXML private TableColumn colDesc;
    @FXML private Button btnRemoveProduct;

    float Bill = 0;

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
            Parent root = FXMLLoader.load(getClass().getResource("../views/view-addAdmins.fxml"));
            Start.root.setTitle("ZIP COMPANY");
            Start.root.setScene(new Scene(root));
            Start.root.show();
        }
        else{

            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../views/view-checkSuperAdmin.fxml"));
            Start.root.setTitle("ZIP COMPANY");
            Start.root.setScene(new Scene(root));
            Start.root.show();
        }
    }

    /**
     * Function To Search Product Code
     */
    public void searchCode() throws SQLException {
        this.txtQty.setText(String.valueOf(0));
        Integer ProductCode = Integer.valueOf(txtSearchCode.getText());
        if(ProductCode < 0)
        {
            JOptionPane.showMessageDialog(null,"Invalid Product Code");
        }
        else
        {

            if(c.check_product(ProductCode)) {

                ps = new products();
                ps = c.getProduct();


                /*FinalInvoice.setCode(ps.getCode());
                FinalInvoice.setName(ps.getName());
                FinalInvoice.setDesc(ps.getDescription());
                FinalInvoice.setType(ps.getType());
                FinalInvoice.setLength(ps.getLength());
                FinalInvoice.setPrice(ps.getPrice());*/
                FinalInvoice.setCreator(username);

                this.labelCode.setText(String.valueOf(ps.getCode()));
                this.labelName.setText(ps.getName());
                this.labelDesc.setText(ps.getDescription() + " ");
                this.labelType.setText(ps.getType());
                this.labelLength.setText(String.valueOf(ps.getLength()));
                this.labelStock.setText(String.valueOf(ps.getQty()));
                this.labelPrice.setText(String.valueOf(ps.getPrice()));

                if (ps.getQty() == 0)    // If Product Quantity is zero
                {
                    JOptionPane.showMessageDialog(null, "This Product is out of Stock");
                } else {
                    this.btnApply.setDisable(false);
                    this.btnAddInInvoice.setDisable(false);
                }
            }
            else
                {
                JOptionPane.showMessageDialog(null, "Product is not in store");
                    this.btnAddInInvoice.setDisable(true);
            }

        }

    }

    public void addProductInInvoice()
    {
//        Integer Bill = 0;
        Integer qty = Integer.valueOf(this.txtQty.getText());

        if(ProductsList.size() >= 20)
        {
            JOptionPane.showMessageDialog(null,"You can add only 20 Products!");
        }
        else if(qty < 0 || qty == 0)
        {
            System.out.println("[CreateInvoice]- Qty <= 0");
            JOptionPane.showMessageDialog(null,"Select at least 1 Quantity! ");
        }
        else if(qty >  Integer.valueOf(this.labelStock.getText()))
        {
            System.out.println("[CreateInvoice]- Qty > Stock");
            JOptionPane.showMessageDialog(null,"NOT Enough Quantity in store right now!");
        }
        else if(ProductsList.contains(ps) )
        {
            JOptionPane.showMessageDialog(null,"This Product is already added in Table!");
        }
        else
        {
            int found = 0;
            int listsize = ProductsList.size();
            for(int i = 0; i < listsize; i++)
            {
                if(ps.getCode() == ProductsList.get(i).getCode())
                {
                    found = 1;
                }
            }

            if(found == 1)
            {
                JOptionPane.showMessageDialog(null,"This Product is already added in Invoice!\n" +
                        "To edit the product, remove it from the Invoice");
            }
            else
            {
                System.out.println("[CreateInvoice]- Qty is OK");
//                JOptionPane.showMessageDialog(null,"You are going to sale: " + qty + " quantities");
                Bill = Bill + (qty * ps.getPrice());
                this.labelBill.setText(String.valueOf(Bill));
                FinalInvoice.setBill(Bill);
                ps.setQty(qty);

                try{
                    ProductsList.add(ps);
//                int i = ProductsList.size();
                /*for (int j = 0; j < i; j++) {
                    System.out.println("Code: " + ProductsList.get(j).getCode());
                }*/

                } catch (NumberFormatException e){ JOptionPane.showMessageDialog(null,e.getMessage());}

//            mainGrid.setItems(ProductsList);
                addDataToTable(ps);
            }

        }
    }

    public void removeProductFromInvoice()
    {
        ObservableList<products> productSelected, allProducts;
        allProducts = mainGrid.getItems();
        productSelected = mainGrid.getSelectionModel().getSelectedItems();

        Integer index = mainGrid.getSelectionModel().getSelectedIndex();
        /*System.out.println("Index: " + index);
        System.out.println("Qty: " + ProductsList.get(index).getQty());
        System.out.println("Price: " + ProductsList.get(index).getPrice());*/

        Bill = Bill - (ProductsList.get(index).getQty() * ProductsList.get(index).getPrice());
        this.labelBill.setText(String.valueOf(Bill));
        FinalInvoice.setBill(Bill);

        productSelected.forEach(ProductsList::remove);
        int i = ProductsList.size();
        for (int j = 0; j < i; j++) {
            System.out.println("Code: " + ProductsList.get(j).getCode());
        }
        productSelected.forEach(allProducts::remove);
    }

    /**
     * Function will Validate the Invoice Input Fields,
     * If No Errors, Preview the Invoice
     */
    public void openInvoicePreview() throws IOException{

        FinalInvoice.setBill(FinalInvoice.getBill() - FinalInvoice.getTax());
        Bill = FinalInvoice.getBill();
        FinalInvoice.setTax(0);


        FinalInvoice.setProducts(ProductsList);

        /**
         * Checks For Remaining
         */
        try {
            Float remaining = Float.valueOf(this.txtRemaining.getText());
//                    System.out.println("[CreateInvoice]- Discount is not Empty");

            if (remaining == 0) {
                System.out.println("[CreateInvoice] Discount is zero");
                FinalInvoice.setRemaining(remaining);
            }
            else if (remaining < 0) {
//                        System.out.println("[CreateInvoice]- Discount is too much");
                JOptionPane.showMessageDialog(null, "Invalid Remaining Amount");
            }
            else {
//                        System.out.println("[CreateInvoice]- Discount is Ok");
                /**
                 * SET REMAINING IN FINAL INVOICE
                 */
                FinalInvoice.setRemaining(remaining);
            }
        } catch (NumberFormatException e){
            FinalInvoice.setRemaining(0);
//                    System.out.println("[CreateInvoice] - Discount is Empty");
        }

        /**
         * CALCULATING TAX
         */
        try {
            /*System.out.println("---Before Calculating Tax---");
            System.out.println("Code: " + FinalInvoice.getTax());
            System.out.println("Bill: " + FinalInvoice.getBill());
            System.out.println("Bill: " + Bill);*/


            Float tax = Float.valueOf(this.txtTax.getText());

            if(tax == 0)
            {
//                        System.out.println("[CreateInvoice] Discount is zero");
                FinalInvoice.setTax(tax);

            }
            else if(tax < 0)
            {
//                        System.out.println("[CreateInvoice]- Invalid Tax, cannot be negative");
                JOptionPane.showMessageDialog(null,"Invalid Tax");
            }
            else
            {
                Bill = Bill + tax;
                this.labelBill.setText(String.valueOf(Bill));
                /**
                 * SET TAX IN FINAL INVOICE
                 */
                FinalInvoice.setTax(tax);
                FinalInvoice.setBill(Bill);

               /* System.out.println("---After Tax & Bill is set---");
                System.out.println("Code: " + FinalInvoice.getTax());
                System.out.println("Bill: " + FinalInvoice.getBill());
                System.out.println("Bill: " + Bill);*/

                tax = 0f;
            }

        } catch (NumberFormatException e)
        {
            FinalInvoice.setTax(0);
            System.out.println("[CreateInvoice] - Tax is Empty");
        }

        /**
         * Checks For Customer Name
         */
        String customer = this.txtCust.getText();
        if(customer.isEmpty())
        {
            System.out.println("[CreateInvoice]- Customer Field is empty");
            JOptionPane.showMessageDialog(null,"Please Enter Customer Name");

            FinalInvoice.setBill(FinalInvoice.getBill() - FinalInvoice.getTax());
            Bill = FinalInvoice.getBill();
            FinalInvoice.setTax(0);
        }
        else if(FinalInvoice.getProducts().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Cannot create empty Invoice");
        }
        else {
            System.out.println("[CreateInvoice]- Customer: " + customer);
//                        JOptionPane.showMessageDialog(null,"Customer: " + customer);
            /**
             * SET CUSTOMER IN FINAL INVOICE
             */
            FinalInvoice.setCustomer(customer);

            /**
             * Getting System Date
             */
            Date dddd = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(dddd);
            /**
             * SET DATE IN FINAL INOICE
             */
            FinalInvoice.setDate(strDate);

//            JOptionPane.showMessageDialog(null,"~Final Invoice~\n" +
//                    "Date: " + FinalInvoice.getDate() + "\n" +
//                    "Customer: " + FinalInvoice.getCustomer() + "\n" +
//                    "Creator: " + FinalInvoice.getCreator() + "\n" +
//                    "Number of Products: " + FinalInvoice.getProducts().size() + "\n" +
//                    "Tax: " + FinalInvoice.getTax() + "\n" +
//                    "Remaining: " + FinalInvoice.getRemaining() + " \n" +
//                    "Bill: " + FinalInvoice.getBill()
//            );

            labelBill.setText(String.valueOf(FinalInvoice.getBill()));
            openInvoicePage();
        }

    }

    public void openInvoicePage() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/zip_company/views/view-previewInvoice.fxml"));
        primaryStage.setTitle("ZIP-Company");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



    public void addDataToTable(products P)
    {
        mainGrid.getItems().add(P);
    }

    public void clearTable()
    {
        mainGrid.getItems().removeAll(mainGrid.getItems());
    }
}
