package zip_company.models;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class dailysales {
    private int code;
    private int qty;
    private int  invoiceid;
    private float tax=0, discount=0,length, total,price;
    private String username;
    private String customer;
    private String zipType;
    private String description;

    private String name;

    private Document document = new Document(PageSize.A4,37, 37, 40, 40);
    private String pdfName;



    public String getZipType() {
        return zipType;
    }

    public void setZipType(String zipType) {
        this.zipType = zipType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String get_date(){
        Date date = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        return df2.format(date).toString();
    }

    private PdfPCell getCell(String str, boolean flag){
        Font f = new Font(Font.FontFamily.HELVETICA,9);
        Paragraph p = new Paragraph();
        p.setFont(f);
        p.add(str);
        p.setAlignment(p.ALIGN_CENTER);

        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        if(flag){ cell.setBackgroundColor(new BaseColor(153, 153, 153)); }

        return cell;

    }

    public void generate_invoice(LinkedList<products>lst,boolean flag) throws DocumentException, FileNotFoundException {
        PdfPTable table = new PdfPTable(7);
        table.setWidths(new float[]{22f, 26f, 25f, 16f,14f, 22f, 23f});
        String path = "";
        if(flag) { /// update the invoice
            path = "..\\invoices\\updated_invoices\\"+this.get_date();
        }
        else {
            path = "..\\invoices\\"+this.get_date();
        }

        if( (new File(path)).mkdirs()){
//            JOptionPane.showMessageDialog(null,"folder invoices");
            }


        this.pdfName = new SimpleDateFormat("hh_mm_ss_aa").format(new Date()).toString()+"_"+this.getCustomer()+".pdf";
        PdfWriter.getInstance(document, new FileOutputStream( path+"\\"+ this.pdfName));


        document.open();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE dd-MMM-YYYY HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        document.add(new Phrase(dtf.format(now).toString()));

        Font f = new Font(Font.FontFamily.TIMES_ROMAN,12);
        Paragraph p = new Paragraph();
        p.setFont(f);
        p.clear();
        p.add("_________________________________________________________________________");
        p.setAlignment(p.ALIGN_CENTER);
        document.add(p);

         f = new Font(Font.FontFamily.TIMES_ROMAN,18);
         p = new Paragraph("INVOICE",f);
        p.setAlignment(p.ALIGN_CENTER);
        document.addTitle("Zip Company");
        document.add(p);
        f = new Font(Font.FontFamily.TIMES_ROMAN,12);
        p.setFont(f);
        p.clear();
        p.add("_________________________________________________________________________\n\n");
        p.setAlignment(p.ALIGN_CENTER);
        document.add(p);


        f = new Font(Font.FontFamily.TIMES_ROMAN,13);
        p.setFont(f);
        p.clear();
        p.add("Customer Name:           "+ this.getCustomer().toUpperCase());
        p.setAlignment(p.ALIGN_LEFT);
        document.add(p);
        document.add(new Paragraph("\n"));



        // Adding cells to the table2
        table.addCell(this.getCell("Zip Code",true));
        table.addCell(this.getCell("Name",true));
        table.addCell(this.getCell("Type",true));
        table.addCell(this.getCell("Length(in)",true));
        table.addCell(this.getCell("Qty",true));
        table.addCell(this.getCell("PKR/unit",true));
        table.addCell(this.getCell("Total(PKR)",true));

        products temp = new products();
        float subTotal = 0;
        if(lst != null){

            for (int i = 0; i<lst.size(); i++){
                temp = lst.get(i);

                table.addCell(this.getCell(Integer.toString(temp.getCode()),false));
                table.addCell(this.getCell(temp.getName(),false));
                table.addCell(this.getCell(temp.getType(),false));
                table.addCell(this.getCell(Float.toString( temp.getLength()),false));
                table.addCell(this.getCell(Integer.toString(temp.getQty()),false));
                table.addCell(this.getCell(Float.toString( temp.getPrice()),false));
                table.addCell(this.getCell(Float.toString(temp.getPrice()*temp.getQty()),false));
                subTotal += temp.getPrice()*temp.getQty();
            }
        }

        document.add(table);


        f = new Font(Font.FontFamily.HELVETICA, 11);
        p.setFont(f);
        p.clear();
        p.add("\nSub Total:       " + subTotal + " PKR     ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);

        f = new Font(Font.FontFamily.HELVETICA,11);
        p.setFont(f);
        p.clear();
        p.add("Additional Tax:          "+ this.getTax()+" PKR     ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);



        f = new Font(Font.FontFamily.HELVETICA, 11);
        p.setFont(f);
        p.clear();
        p.add("Remainings:         " +this.getDiscount() + " PKR     ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);



        f = new Font(Font.FontFamily.HELVETICA,11);
        p.setFont(f);
        p.clear();
        p.add("---------------------------------------------     ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);
        f = new Font(Font.FontFamily.TIMES_ROMAN,14);
        p.setFont(f);
        p.clear();
        p.add("TOTAL:        "+this.getTotal()+" PKR     ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);

        f = new Font(Font.FontFamily.HELVETICA,11);
        p.setFont(f);
        p.clear();
        p.add("---------------------------------------------     ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);

        f = new Font(Font.FontFamily.TIMES_ROMAN,14);
        p.setFont(f);
        p.clear();
        p.add("\nTHANK YOU!");
        p.setAlignment(p.ALIGN_CENTER);
        document.add(p);
        f = new Font(Font.FontFamily.HELVETICA,12);
        p.setFont(f);
        p.clear();
        p.add("Created by    "+this.username.toUpperCase()+".    ");
        p.setAlignment(p.ALIGN_RIGHT);
        document.add(p);
        document.close();


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  void setTotal(float t){
        this.total = t;
    }
    public float getTotal(){
        return  this.total;

    }

    public void  get_random_num(ResultSet rs) throws SQLException {

        List lst = new ArrayList();
        while(rs.next()){
            lst.add(rs.getInt("id"));
        }
        this.invoiceid  =  ThreadLocalRandom.current().nextInt(100000, 1000000);
        while(lst.contains(this.invoiceid)) {
            this.invoiceid = ThreadLocalRandom.current().nextInt(100000, 1000000);
        }

    }
    public String check_prodect_db_query(){
        return "select * from zips where code = "+this.getCode()+";";
    }
    public String update_qty_query(int updatedQty){
        return  "update zips set quantity =  (select quantity from zips where code = "+this.getCode()
                +")-"+updatedQty+ " where code = "+this.getCode()+";";
    }
    public String update_remaining_db_query(float remain, int id){
        return "update invoices set discount = "+remain+" where id = "+id+";";
    }

    public String get_invoices_query() {
        return "select * from invoices where today = '"+this.get_date()+"';";
    }
    public String get_invoices_query(String date) {
        return "select * from invoices where today = '"+date+"';";
    }
    public  void setInvoiceid(int inv){
        this.invoiceid = inv;
    }
    public String get_invoice_info_query(){
        return  " select code,name,type,length,quantity,price,description from dailysales where invoiceid = " +
                this.invoiceid+";";
    }
    public String all_invoices_query(){
        return  "select * from invoices";
    }

    public String add_sale_query(){
        return "insert into dailysales (code,quantity,invoiceid,name,type,length,price,description) values ("+
                +this.getCode()+","+this.getQty()+","+this.invoiceid+",'"+this.getName()+"','"+this.getZipType()+
                "',"+this.getLength()+","+this.getPrice()+",'"+this.getDescription()+"');";
    }

    public String insert_invoice_query(){
        return "insert into invoices(id,total,customer,username,tax,discount,today) values("
                +this.invoiceid+","+this.total+",'"+this.getCustomer()+"','"+this.getUsername()+"',"+this.getTax()
                +","+this.getDiscount()+",'"+this.get_date()+"');";
    }

    public String delete_invoice(){ return "delete from invoices where id = "+this.invoiceid+";"; }

    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
}
