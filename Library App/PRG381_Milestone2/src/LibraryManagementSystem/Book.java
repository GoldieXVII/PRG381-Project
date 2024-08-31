/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author micha
 */

public class Book extends CRUD {
    protected int id, borrower;
    protected String title,author;
    
    public Book(){
        
    }
    
    public Book(int id, String title, String author, int bor){
        this.title = title;
        this.id = id;
        this.author = author;
        this.borrower = bor;
        
    }

    @Override
    public ArrayList<Book> getData() {
        ArrayList<Book> dataList = new ArrayList<>();
        String query = "SELECT * FROM Books";
        DBConnection conn = new DBConnection();
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

    public void addData(String t, String a, int b) {
        DBConnection db = new DBConnection();
        String query = "INSERT INTO Books (Title, Author, BorrowerID) VALUES ('" + t + "', '" + a + "', " + b + ")";
        db.executeCRUD(query);
    }
    
    public void updateData(int i, String t, String a, int b) {
        DBConnection db = new DBConnection();
        String query = "UPDATE Books SET Title = '" + t + "', Author = '" + a + "', BorrowerID = " + b + " WHERE BookID = " + i;
        db.executeCRUD(query);
    }
    
    public void deleteData(int i) {
        DBConnection db = new DBConnection();
        String query = "DELETE FROM Books WHERE BookID = " + i;
        db.executeCRUD(query);
    }
    
    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
