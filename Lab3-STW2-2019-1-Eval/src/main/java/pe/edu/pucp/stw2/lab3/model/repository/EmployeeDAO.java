/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.stw2.lab3.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.stw2.lab3.model.bean.Department;
import pe.edu.pucp.stw2.lab3.model.bean.Employee;
import pe.edu.pucp.stw2.lab3.model.bean.Job;
import pe.edu.pucp.stw2.lab3.model.bean.Location;

/**
 *
 * @author Oscar A. Díaz Barriga
 */
public class EmployeeDAO extends BaseDAO {

    public ArrayList<Employee> findAll() {

        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, "
                + "d.department_name, l.city "
                + "FROM employees e "
                + "JOIN departments d ON e.department_id = d.department_id "
                + "JOIN locations l ON d.location_id = l.location_id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));

                // Departamento
                Department department = new Department();
                department.setDepartName(rs.getString("department_name"));
                employee.setDepartment(department);

                // Ubicación
                Location location = new Location();
                location.setLocationCity(rs.getString("city"));
                employee.setLocation(location);

                listaEmpleados.add(employee);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEmpleados;
    }

    public Employee findById(int id) {

        Employee employee = new Employee();

        String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, "
                + "d.department_name, l.city "
                + "FROM employees e "
                + "JOIN departments d ON e.department_id = d.department_id "
                + "JOIN locations l ON d.location_id = l.location_id "
                + "WHERE e.employee_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee.setEmployeeId(rs.getInt("employee_id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));

                    // Departamento
                    Department department = new Department();
                    department.setDepartName(rs.getString("department_name"));
                    employee.setDepartment(department);

                    // Ubicación
                    Location location = new Location();
                    location.setLocationCity(rs.getString("city"));
                    employee.setLocation(location);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employee;
    }

    public boolean deleteById(int id) {
        String sql =  "DELETE FROM employees WHERE employee_id = ?";
        boolean flag = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;  // Se eliminó correctamente
            }

        }  catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

}
