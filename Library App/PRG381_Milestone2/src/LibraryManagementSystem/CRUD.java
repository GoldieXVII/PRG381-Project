/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

import java.util.List;

/**
 *
 * @author micha
 */
public abstract class CRUD {
    public CRUD(){
    }
    public abstract List getData();
    public abstract void updateData();
    public abstract void addData();
    public abstract void deleteData();
}
