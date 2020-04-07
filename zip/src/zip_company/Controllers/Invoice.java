package zip_company.Controllers;

import zip_company.models.products;

import java.util.LinkedList;

public class Invoice {

   private LinkedList<products> PRODCUTS;

    private float remaining;
    private float tax;
    private float bill;
    private String customer;
    private String creator;
    private String date;


    Invoice()
    {
        this.remaining = 0;
        this.tax = 0;
        this.bill = 0;
        this.customer = null;
        this.creator = null;
        this.date = null;
        this.PRODCUTS = null;
    }

    Invoice(Float Remaining, Float Tax, Float Bill, String Customer, String Creator, String Date, LinkedList<products> ProductsList)
    {
        this.remaining = Remaining;
        this.tax = Tax;
        this.bill = Bill;
        this.customer = Customer;
        this.creator = Creator;
        this.date = Date;
        this.PRODCUTS = ProductsList;
    }

    /**
     * SET FUNCTIONS
     */
    public void setRemaining(float Remaining)
    {
        this.remaining = Remaining;
    }

    public void setTax(float Tax)
    {
        this.tax = Tax;
    }

    public void setBill(float Bill)
    {
        this.bill = Bill;
    }

    public void setCustomer(String Customer)
    {
        this.customer = Customer;
    }

    public void setCreator(String Creator)
    {
        this.creator = Creator;
    }

    public void setDate(String Date)
    {
        this.date = Date;
    }

    public void setProducts(LinkedList<products> Products)
    {
        this.PRODCUTS = Products;
    }

    /**
     * GET FUNCTIONS
     */
    public Float getRemaining()
    {
        return this.remaining;
    }

    public Float getTax()
    {
        return this.tax;
    }

    public Float getBill()
    {
        return this.bill;
    }

    public String getCustomer()
    {
        return this.customer;
    }

    public String getCreator()
    {
        return this.creator;
    }

    public String getDate()
    {
        return date;
    }

    public LinkedList<products> getProducts()
    {
        return this.PRODCUTS;
    }

    public void clearInvoice()
    {
        this.remaining = 0;
        this.tax = 0;
        this.bill = 0;
        this.customer = null;
        this.creator = null;
        this.date = null;
        this.PRODCUTS = null;
    }

}
