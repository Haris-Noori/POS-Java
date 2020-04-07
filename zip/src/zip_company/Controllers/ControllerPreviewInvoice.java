package zip_company.Controllers;

import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import zip_company.models.products;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import static zip_company.Controllers.ControllerCreateInvoice.FinalInvoice;

public class ControllerPreviewInvoice implements Initializable {

    private superController c = new superController();

    @FXML private  Label labelDate;
    @FXML private  Label labelCust;

    @FXML private TableView mainGrid;
    @FXML private TableColumn colCode;
    @FXML private TableColumn colName;
    @FXML private TableColumn colType;
    @FXML private TableColumn colLength;
    @FXML private TableColumn colQty;
    @FXML private TableColumn colPrice;
    @FXML private TableColumn colTotal;

    @FXML private Label labelSubTotal;
    @FXML private Label labelTax;
    @FXML private  Label labelDiscount;

    @FXML private  Label labelBill;

    @FXML private Label labelCreator;

    @FXML private Button btnEdit;
    @FXML private Button btnPrint;




    private LinkedList<FinalProduct> tableData = new LinkedList<>();

    public ControllerPreviewInvoice() throws SQLException {
    }

    public void goBackToInvoice() throws IOException{
        Stage stage = (Stage) btnEdit.getScene().getWindow();
        stage.close();
        FinalInvoice.setBill(FinalInvoice.getBill() - FinalInvoice.getTax());
        FinalInvoice.setTax(0);
    }


    public void generateInvoice() throws IOException, DocumentException {
        c.add_sale(FinalInvoice.getBill(), FinalInvoice.getCreator(),  FinalInvoice.getCustomer(),
                FinalInvoice.getRemaining(), FinalInvoice.getTax(),
                FinalInvoice.getProducts(),false);

        Stage stage = (Stage) btnPrint.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelDate.setText(FinalInvoice.getDate());
        this.labelCust.setText(FinalInvoice.getCustomer().toUpperCase());


        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        Float SubTotal = Float.valueOf(0);

        LinkedList<products> lst = FinalInvoice.getProducts();
        int listSize = lst.size();

        for(int i = 0; i< listSize; i++)
        {
            FinalProduct fp = new FinalProduct();
            fp.setCode(lst.get(i).getCode());
            fp.setName(lst.get(i).getName());
            fp.setType(lst.get(i).getType());
            fp.setLength(lst.get(i).getLength());
            fp.setQty(lst.get(i).getQty());
            fp.setPrice(lst.get(i).getPrice());
            fp.setTotal();
            mainGrid.getItems().add(fp);
            SubTotal = SubTotal + fp.getTotal();

//            System.out.println("~PRODUCT~");
//            System.out.println("Code: " + fp.getCode());
//            System.out.println("Name: " + fp.getName());
//            System.out.println("Type: " + fp.getType());
//            System.out.println("Length: " + fp.getLength());
//            System.out.println("Qty: " + fp.getQty());
//            System.out.println("Price: " + fp.getPrice());
//            System.out.println("Total: " + fp.getTotal());
            fp = null;
        }

        this.labelSubTotal.setText(String.valueOf(SubTotal));
        this.labelDiscount.setText(String.valueOf(FinalInvoice.getRemaining()));
        this.labelTax.setText(String.valueOf(FinalInvoice.getTax()));
        this.labelBill.setText(String.valueOf(FinalInvoice.getBill()));
        this.labelCreator.setText(FinalInvoice.getCreator().toUpperCase());

    }

}
