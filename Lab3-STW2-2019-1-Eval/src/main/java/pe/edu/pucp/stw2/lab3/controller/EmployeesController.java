package pe.edu.pucp.stw2.lab3.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.pucp.stw2.lab3.model.bean.Employee;
import pe.edu.pucp.stw2.lab3.model.bean.Job;
import pe.edu.pucp.stw2.lab3.model.repository.EmployeeDAO;
import pe.edu.pucp.stw2.lab3.model.repository.JobDAO;

/**
 *
 * @author Oscar A. Díaz Barriga
 */

@Controller
public class EmployeesController {

    private EmployeeDAO employeesDAO = new EmployeeDAO();
    private JobDAO jobsDAO = new JobDAO();

    @GetMapping({"employee/list", "employee"})
    public String listarEmpleados(Model model) {
        List<Employee> listaEmpleados = employeesDAO.findAll();
        model.addAttribute("listaEmpleados", listaEmpleados);
        return "employeeList";  // Nombre del template HTML (ejemplo: employeeList.html)
    }

    @GetMapping("/employee/info/{id}")
    public String informEmpleado(@PathVariable("id") int id, Model model) {
        Employee employee = employeesDAO.findById(id);
        List<Job> listaTrabajos = jobsDAO.findAll();  // Obtener trabajos para el dropdown
        model.addAttribute("employee", employee);
        model.addAttribute("listaTrabajos", listaTrabajos);
        return "employeeInfo";  // Nombre del template HTML (ejemplo: employeeInfo.html)
    }

    @GetMapping("/employee/delete/{id}")
    public String borrarEmpleado(@PathVariable("id") int id, Model model) {
        boolean eliminado = employeesDAO.deleteById(id);
        if (eliminado) {
            model.addAttribute("tipo_alert", "success");
            model.addAttribute("msg", "Se borró el empleado");
        } else {
            model.addAttribute("tipo_alert", "danger");
            model.addAttribute("msg", "No se pudo borrar el empleado");
        }
        return "redirect:/employee";
    }

}
