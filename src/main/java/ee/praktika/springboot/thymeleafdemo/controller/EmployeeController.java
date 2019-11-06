package ee.praktika.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ee.praktika.springboot.thymeleafdemo.entity.Employee;
import ee.praktika.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping( "/employees" )
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController( EmployeeService theEmployeeService ) {
        employeeService = theEmployeeService;
    }

    //add mapping for /list
    @GetMapping( "/list" )
    public String listEmployees( Model theModel ){

        //get employees from the DB
        List<Employee> theEmployees = employeeService.findAll();

        //add to the spring model
        theModel.addAttribute( "employees", theEmployees );

        return "employees/list-employees";
    }

    //add a new get-mapping to add employee
    @GetMapping( "/showFormForAdd" )
    public String showFormForAdd( Model theModel ){

        //create the model attribute to bind the model data
        Employee theEmployee = new Employee();

        theModel.addAttribute( "employee", theEmployee );

        return "employees/employees-form";
    }

    //add a mapping for saving
    @PostMapping( "/save" )
    public String saveEmployee( @ModelAttribute( "employee" ) Employee theEmployee ){

        //save the employee
        employeeService.save( theEmployee );

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    //add mapping to update employee
    @GetMapping( "/showFormForUpdate" )
    public String showFormForUpdate( @RequestParam( "employeeId" ) int theId, Model theModel ){

        //get the employee from the service or db
        Employee theEmployee = employeeService.findById( theId );

        //set the employee as a model attribute to pre-populate the form
        theModel.addAttribute( "employee", theEmployee );

        //send this over to our form
        return "employees/employees-form";

    }
}
