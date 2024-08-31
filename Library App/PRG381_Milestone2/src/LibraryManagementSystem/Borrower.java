/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author micha
 */

public class Borrower extends CRUD {
    protected int id;
    protected String name,surname,contact;
    
    public Borrower(){
        
    }
    
    public Borrower(int id, String name, String sur, String contact){
        this.id = id;
        this.name = name;
        this.surname = sur;
        this.contact = contact;
    }
    

    @Override
    public ArrayList<Borrower> getData() {
        ArrayList<Borrower> dataList = new ArrayList<>();
        String query = "SELECT * FROM Borrower";
        DBConnection conn = new DBConnection();
        try
        {
            ResultSet table = conn.getData(query);
            if(table != null)
            {
                while(table.next())
                {
                    Borrower bor = new Borrower();
                    int id = table.getInt("BorrowerID");
                    String n = table.getString("FirstName");
                    String s = table.getString("Surname");
                    String ci = table.getString("ContactInfo");
                    bor.id = id;
                    bor.name = n;
                    bor.surname = s;
                    bor.contact = ci;
                    dataList.add(bor);
                }
                table.close();
            }
        }catch (SQLException | ClassNotFoundException ex )
        {
            ex.printStackTrace();
        }
       return dataList;  
    }

    
    public void updateData(int i, String n, String s, String c) {
        DBConnection db = new DBConnection();
        String query = "UPDATE Borrower SET FirstName = '" + n + "', Surname = '" + s + "', ContactInfo = '" + c + "' WHERE BorrowerID = " + i;
        db.executeCRUD(query);
    }

    public void addData(String n, String s, String c) {
        DBConnection db = new DBConnection();
        String query = "INSERT INTO Borrower (FirstName, Surname, ContactInfo) VALUES ('" + n + "', '" + s + "', '" + c + "')";
        db.executeCRUD(query);
    }
    
    public ArrayList<Book> CheckBorrowing(int i){
        ArrayList<Book> dataList = new ArrayList<>();
        DBConnection conn = new DBConnection();
        String query = "SELECT * FROM Books WHERE BorrowerID = "+i;
        try
        {
            ResultSet table = conn.getData(query);
            if(table != null)
            {
                while(table.next())
                {
                    Book book = new Book();
                    int id = table.getInt("BookID");
                    String t = table.getString("Title");
                    String a = table.getString("Author");
                    int borrowId = table.getInt("BorrowerID");
                    book.id = id;
                    book.author = a;
                    book.title = t;
                    book.borrower = borrowId;
                    dataList.add(book);
                }
                table.close();
            }
        }catch (SQLException | ClassNotFoundException ex )
        {
            ex.printStackTrace();
        }
       return dataList;
    }
    
    public void deleteData(int i) {
        DBConnection db = new DBConnection();
        ArrayList<Book> booksbor = this.CheckBorrowing(i);
        if(booksbor!=null){
            String query = "UPDATE Books SET BorrowerID = 0" + " WHERE BorrowerID = " + i;
            db.executeCRUD(query);
        }
        String query = "DELETE FROM Borrower WHERE BorrowerID = " + i;
        db.executeCRUD(query);
    }

    @Override
    public void deleteData() {
        
    }

    @Override
    public void addData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
