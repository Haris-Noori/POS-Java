package zip_company.Controllers;

import com.itextpdf.text.DocumentException;
import zip_company.models.createUser;
import zip_company.models.dailysales;
import zip_company.models.login;
import zip_company.models.products;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static zip_company.Start.database;

public class superController {
    protected login log;
    private products product;
    public static String userType,username;


    public superController() throws SQLException {
        this.log = new login();
    }


    public ResultSet show_daily_sales(String date){
        dailysales sale = new dailysales();
        return database.get_table(sale.get_invoices_query(date)) ;
    }
    public ResultSet show_daily_sales() throws SQLException {
        dailysales sale = new dailysales();
        return   database.get_table(sale.get_invoices_query()) ;
    }

    public void add_sale(float total,String username,String cust,
                         float remaining, float tax, LinkedList<products> lst,boolean f){
        dailysales sale = new dailysales();
        products temp = new products();
        sale.setUsername(username);
        sale.setCustomer(cust);
        sale.setDiscount(remaining);
        sale.setTax(tax);
        sale.setTotal(total);

        try {

            sale.get_random_num(database.get_table(sale.all_invoices_query())); /// invoice id
            database.insert_update_query(sale.insert_invoice_query());
            for (int i = 0; i<lst.size(); i++){
                temp = lst.get(i);
                sale.setCode(temp.getCode());
                sale.setQty(temp.getQty());
                sale.setName(temp.getName());
                sale.setZipType(temp.getType());
                sale.setDescription(temp.getDescription());
                sale.setPrice(temp.getPrice());
                sale.setLength(temp.getLength());
                if(!database.insert_update_query(sale.update_qty_query(sale.getQty()))){
                    if(database.insert_update_query(sale.add_sale_query()) ){
                        JOptionPane.showMessageDialog(null,"sale did not add");
                        return;
                    }
                }
            }

            sale.generate_invoice(lst, f);
            JOptionPane.showMessageDialog(null,"Pdf generated");

        } catch (DocumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "[add sale SuperC] invoices");
        }
    }

    public ResultSet show_all_products() throws SQLException {
        return database.get_table(new products().get_all_products());
    }

    public boolean delete_invoice(int inv){
        dailysales s = new dailysales();
        s.setInvoiceid(inv);
        return database.delete_query(s.delete_invoice());
    }
    public boolean delete_product(int code) throws SQLException {
        products product = new products();
        product.setCode(code);

        if(database.delete_query(product.delete_product())){
            return  true;
        }
        else{ JOptionPane.showMessageDialog(null,"This Product( "+code+" )is not in store."); }
        return false;
        }

    public products getProduct(){
        return this.product;
    }
    public  void nullProduct(){
        this.product = null;
    }

    public boolean check_product(String name) throws SQLException {
        products product = new products();
        product.setName(name);
        ResultSet rs = database.get_table(product.check_prodect_db_name_query());

        if(rs.next()){
            product.setCode(rs.getInt("code"));
            product.setType(rs.getString("type"));
            product.setDescription(rs.getString("description"));
            product.setLength(rs.getInt("length"));
            product.setPrice(rs.getInt("price"));
            product.setQty(rs.getInt("quantity"));
            this.product = product;
            return true;
        }


        return false;
    }
    public boolean check_product(Integer code) throws SQLException {
        products product = new products();
        product.setCode(code);
        ResultSet rs = database.get_table(product.check_prodect_db_query());

        if(rs.next()){
            product.setName(rs.getString("name"));
            product.setType(rs.getString("type"));
            product.setDescription(rs.getString("description"));
            product.setLength(rs.getFloat("length"));
            product.setPrice(rs.getFloat("price"));
            product.setQty(rs.getInt("quantity"));
            this.product = product;
            return true;
        }


        return false;
    }
    public boolean update_product(products product,Integer preCode) throws SQLException {

            if (!database.insert_update_query(product.update_product_code(preCode))){
                if(!database.insert_update_query(product.update_product())){
                    return true;
                }
                else { JOptionPane.showMessageDialog(null,"product code error in database"); }
            }
            else{ JOptionPane.showMessageDialog(null,"database connection error"); }

        return false;
    }

    public boolean add_product(products product) throws SQLException {
        if (!database.get_table(product.check_prodect_db_query()).next()){
            database.insert_update_query(product.get_insert_query());
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Already exists in store.");
        }
        return false;

    }

    public LinkedList<products> get_products_invoice(int invoiceId) throws SQLException {
        dailysales sale = new dailysales();
        sale.setInvoiceid(invoiceId);
        ResultSet rs = database.get_table(sale.get_invoice_info_query());
        LinkedList<products> lst = new LinkedList<products>();
        products p ;
        while (rs.next()){
           p = new products();
           p.setCode(rs.getInt("code"));
           p.setLength(rs.getFloat("length"));
           p.setName(rs.getString("name"));
           p.setType(rs.getString("type"));
           p.setDescription(rs.getString("description"));
           p.setPrice(rs.getFloat("price"));
           p.setQty(rs.getInt("quantity"));
           lst.add(p);
        }
        return lst;
    }
    public boolean update_remaining(int id, float remain){
        dailysales s = new dailysales();
        return !database.insert_update_query(s.update_remaining_db_query(remain,id));
    }


    public boolean add_question_answer(String user, String q, String ans){
        login l = new login();
        l.set_username(user);
        l.setQuestion(q);
        l.setAnswer(ans);
        if (!database.insert_update_query(l.update_question_answer_query() )){
            JOptionPane.showMessageDialog(null,"Question added"); return true;
        }
        return false;
    }

    public boolean forgot_password(String username) {

        log.set_username(username);
        ResultSet rs = database.get_table(log.get_check_user_db_query());
        try {
            if (rs.next()){
                ResultSet rs2 = database.get_table(log.get_forgot_pass_questions_query());
                if(rs2.next()){
                    log.setQuestion( rs2.getString("question"));
                    log.setAnswer( rs2.getString("answer"));
                    return true;
                }
            }
            else{ JOptionPane.showMessageDialog(null,"User does not exist!"); }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return false;

    }

    public ResultSet get_users(){
        return database.get_table(new createUser().get_users_query());
    }

    public boolean delete_user(String user) throws SQLException {
        createUser newuser = new createUser();
        newuser.setName(user);
        try {
            ResultSet rs = database.get_table(newuser.get_check_user_db_query());

            if (rs.next()) {
                if (rs.getString("type").equals("admin")) {
                    database.delete_query(newuser.get_delete_user_query());
                    return true;
                }
                JOptionPane.showMessageDialog(null, "You cannot remove yourself.");
            }
            else
                JOptionPane.showMessageDialog(null, "User does NOT exist.");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
        }
        return false;
    }

    public boolean create_user(String user, String password) throws SQLException {
        createUser newuser = new createUser();
        newuser.setName(user);
        newuser.setType("admin");
        newuser.setPassword(password);
        if(!database.get_table(newuser.get_check_user_db_query()).next()){
            if(database.delete_query(newuser.get_insert_query())){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"User did not add.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, username+" already exist.\n");
        }

        return false;
    }




    public boolean updatePassword(String user, String Pass) {
        /**
         * YOUR CODE FOR CHANGING PASSWORD IN DB
         */
        login l = new login();
        l.set_username(user);
        l.set_password(Pass);
        if(!database.insert_update_query(l.get_update_password_query())){
            return true;
        }
        return false;
    }



    private boolean validate_login(String u ){
        try {
            ResultSet rs = database.get_table(log.get_type_query());
            if (rs.next()) {
                System.out.println("[SuperController]- (validate_login) -> if is working");
                this.username = u;
                this.userType = rs.getString("type");
                return true;
            }
            else {
                System.out.println("[SuperController]- Username & Password in Invalid");
                JOptionPane.showMessageDialog(null,"username or password is invalid\n");
                return false; }
        } catch (SQLException e) {
            System.out.println("[SuperController]- SQL Exception in function(validate_login)");
            JOptionPane.showMessageDialog(null,e.getMessage()+"  in database");
            return false; }
    }

    public boolean get_login_details(String u, String p){
        /// from view
        log.set_username(u);
        log.set_password(p);
        return this.validate_login(u);
    }


    public boolean update_remaining_value(Integer InvoiceId, Float Remainig)
    {
        return true;
    }

    public boolean delete_invoice(Integer InvoiceId)
    {
        return true;
    }

}
