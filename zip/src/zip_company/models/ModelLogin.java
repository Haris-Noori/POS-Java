package zip_company.models;

import javax.swing.*;

public class ModelLogin {

    public boolean validate(String user, String pass) {
        if(user.equals("admin"))
        {
            System.out.println("User Equals to ADMIN");
            if(pass.equals("admin"))
            {
                System.out.println("Pass Equals to ADMIN");
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null,"Incorrect Password");
                return false;
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Incorrect Username");
            return false;
        }
    }
}
