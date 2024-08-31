/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
/**
 *
 * @author micha
 */
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String dbName = "LibraryDB3";
    private static final String JDBC_URL = "jdbc:derby:" + dbName;
    Connection con;
    public DBConnection(){
    
    }
    
   /* public void connect() throws ClassNotFoundException{
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL);
            if(this.con != null){
                System.out.println("Connected to database");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }*/
    
    public void createTable()
    {
        try
        {
            con = DriverManager.getConnection(JDBC_URL);
            String query = "CREATE TABLE Books(" +
            "BookID INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "Title VARCHAR(225) NOT NULL," +
            "Author VARCHAR(150) NOT NULL," +
            "BorrowerID INT" +
            ")";

            this.con.createStatement().execute(query);
            
            query = "CREATE TABLE Borrower(" +
            "BorrowerID INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
            "FirstName VARCHAR (225) NOT NULL," +
            "Surname VARCHAR (225) NOT NULL," +
            "ContactInfo VARCHAR(225) NOT NULL" +
            ")";
            
            this.con.createStatement().execute(query);
            
            query ="INSERT INTO Borrower (FirstName, Surname, ContactInfo)" +
            "VALUES" +
            "('Brandon', 'Goldhawk', '0834283233')," +
            "('Micheal', 'Pautz', '0771234567')," +
            "('Jeandre', 'Sefontein', 'jeandre@msoutlook.com')," +
            "('Kyle', 'Beford', 'kb@hotmail.com')";
            
                    
            this.con.createStatement().execute(query);
            
            query = "INSERT INTO Books (Title, Author, BorrowerID)" +
            "VALUES" +
            "('The Shining','Stephen King',0)," +
            "('Harry Potter','JK Rowling',1)," +
            "('IT','Stephen King',2)," +
            "('Game of Thrones','George RR Martin',1)," +
            "('IT','Stephen King',0)";
            
            this.con.createStatement().execute(query);
            
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    /*public DBConnection(){
    
    }*/
    
    public void connect() throws ClassNotFoundException{
        try
        {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connected to existing database: " + dbName);
        } catch (SQLException e)
        {
             if (e.getSQLState().equals("XJ004") || e.getSQLState().equals("08004")) {
                System.out.println("Database does not exist. Creating a new database: " + dbName);
                String createURL = JDBC_URL + ";create=true";
                try {
                    Connection conn = DriverManager.getConnection(createURL);
                    System.out.println("Database created successfully: " + dbName);
                    createTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        }finally
        {
            try
            {
                if (con != null) con.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        } 
    }
    
    public ResultSet getData(String query) throws ClassNotFoundException
    {
        try
        {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(JDBC_URL);
            ResultSet table = con.createStatement().executeQuery(query);
            return table;
        }catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }/*finally
        {
            try
            {
                if (con != null) con.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }  */
    }
    
    public void executeCRUD(String q)
    {
        try
        {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(JDBC_URL);
            int rowsAffected = con.createStatement().executeUpdate(q);
            
            if (rowsAffected > 0)
            {
                System.out.println("Data inserted successfully!");
            } else
            {
                System.out.println("No data was inserted.");
            }  
        }catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }/*finally
        {
            try
            {
                if (con != null) con.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }*/
    }
}
