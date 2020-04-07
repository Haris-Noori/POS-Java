package zip_company.models;

import javax.swing.*;
import java.sql.*;

public class databaseConnection {
    private static Connection connection = null;
    private static Statement statement = null;
    private  static ResultSet rs = null;

    public databaseConnection() throws SQLException {

        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        connection.setAutoCommit(true);
    }
    public void closed(){
        try
        {
            if(connection != null){ connection.close(); }
            if(statement != null){ statement.close(); }
            if(rs != null){ rs.close(); }

        }
        catch(SQLException e)
        {
            // connection close failed.
            JOptionPane.showMessageDialog(null,e.getMessage()+" not closed:]");
        }
    }


    public boolean delete_query(String query)  {

        try {
            connection.setAutoCommit(false);
             statement = connection.createStatement();
            statement.execute(query);
            connection.setAutoCommit(true);
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getLocalizedMessage());
        }

        return  false;
    }

    public boolean insert_update_query(String query){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
            return   statement.execute(query);
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,e.getMessage()+" insertion");
        }


        return false;
    }

    public ResultSet get_table(String query) {
//        Connection connection = null;
        try {
            System.out.println("[Models -> databaseConnection]- (get_table) -> Try executed");
            statement = connection.createStatement();
            statement.setQueryTimeout(20);
             rs =  statement.executeQuery(query);
            return  rs;

        } catch (SQLException e) {
            System.out.println("[Models -> databaseConnection]- (get_table) -> SQL Exception Catch executed");
//            JOptionPane.showMessageDialog(null,e.getMessage()+ " tables");

        }


         return rs;

    }


}
