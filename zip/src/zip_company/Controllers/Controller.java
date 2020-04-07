package zip_company.Controllers;


import zip_company.models.ModelLogin;

import javax.swing.*;


public class Controller {

    private ModelLogin m = new ModelLogin();
//    private static Product TempProduct;
//    private Invoice i = new Invoice();

    public boolean validateLogin(String user, String pass)
    {
        if(m.validate(user,pass))
        {
            System.out.println("[CONTROLLER] - USER AND PASS IS VALID");
            return true;
        }
        else
        {
            System.out.println("[CONTROLLER] - USER AND PASS IS NOT VALID");
            return false;
        }

    }

    public boolean sendProductDetails(Product P)
    {
        JOptionPane.showMessageDialog(null,"Product Recieved\n" +
                "Code: " + P.getCode() + "\n" +
                "Name: " + P.getName() + "\n" +
                "Type: " + P.getType() + "\n" +
                "Length: " + P.getLength() + "\n" +
                "Qty: " + P.getQty() + "\n" +
                "Price: " + P.getPrice() + "\n" +
                "Description: " + P.getDescription());
        return true;
    }

    /**
     * This fuction will find the Product the code in Database, by sending this code to Model and Model will create a
     * Static Product(Class) instance and set all the values including the Product Code
     * @param ProductCode
     * @return Boolean Value
     */
    public boolean searchProductDetails(Integer ProductCode)
    {
        if(ProductCode == 10)  // ProductCode Found in Database
        {
            // Get all the values and Store it in a static product instance

            /* YOUR CODE HERE */
            return true;
        }
        else    // ProductCode is not found in Database
        {
            System.out.println("[CONTROLLER] ~ Product Code is not in Store");
            return false;
        }
    }

    /**
     * This function will get the Product Details and send to Model to update the Searched Product
     * @param P
     * @return boolean if successfully updated
     */
    public boolean updateProduct(Product P)
    {
        Product PModel = P;
        JOptionPane.showMessageDialog(null,"Product to Update:\n" +
                "Code: " + PModel.getCode() + "\n" +
                "Name: " + PModel.getName() + "\n" +
                "Type: " + PModel.getType() + "\n" +
                "Length: " + PModel.getLength() + "\n" +
                "Qty: " + PModel.getQty() + "\n" +
                "Price: " + PModel.getPrice() + "\n" +
                "Description: " + PModel.getDescription());
        return true;

    }

    public Product getProduct(Integer scode)
    {
        // send this integer to modelStore...and this model will return a product instance
        if(scode == 10)
        {
            Product p = new Product(10, "Pata nahi", "B", 5, 10,400, "No Description");
            return p;
        }
        else
        {
            Product p = new Product(null, null, null, 0, null, null, null);
            return p;
        }

    }

    public Invoice getInvoiceDetails(String D)
    {
        System.out.println("[Controller]~ Getting Invoice Details. . .");

        /**
         * YOUR CODE FOR FINDING DATE IN DATABASE FROM MODEL....
         */
        // "YYYY-MM-DD" Format will work
        String tempDate = "2020-03-14";

        if(D.equals(tempDate))    // Send this Date to Model & Get the Details of Invoice in a Invoice Instance
        {
            System.out.println("[Controller]~ Searched Date FOUND :) !");
//            JOptionPane.showMessageDialog(null,"Date found");
            Invoice i1 = new Invoice();
            i1.setDate(D);
//            i1.setCode(10);
            i1.setBill(2000);
            i1.setCustomer("FAST");

            return i1;

        }
        else    // If No Invoice Found on this Date
        {
//            JOptionPane.showMessageDialog(null,"NO INVOICE found on " + D);
            System.out.println("[Controller]~ Searched Date NOT FOUND!");
            Invoice i2 = new Invoice();
            return i2;


        }

    }


}