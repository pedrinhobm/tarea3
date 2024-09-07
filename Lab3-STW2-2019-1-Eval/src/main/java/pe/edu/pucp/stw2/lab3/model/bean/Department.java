/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.stw2.lab3.model.bean;

/**
 *
 * @author Oscar A. Díaz Barriga
 */
public class Department {

    private int departmentId;

    private String departName;
    private Location location; // Relacion x Location

    // Añadir los atributos necesarios
    // No olvidar el encapsulamiento

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
