package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;

@RestController
public class Home {
    @Autowired
    EmployeeRepo repo;
    @PostMapping (value = "addEmployee",produces = "application/*")
    public Employee addEmployee(@RequestBody  Employee employee){
        repo.save(employee);
        return employee;
    }
    @GetMapping(value = "getEmployee/{id}")
    public Employee getEmployeee(@PathVariable int id){
        return repo.findById(id).orElse(null);
    }
    @GetMapping(value = "getEmployees",produces = "application/*")
    public List<Employee> getEmployees(){
        List<Employee> Employees=repo.findAll();
        return Employees;
    }
    @DeleteMapping(value = "deleteEmployee/{id}")
    @ResponseBody
    public String deleteEmployee(@PathVariable int id){
        repo.deleteById(id);
        return "Employee Deleted";
    }
    @PutMapping("updateEmployee/{id}")
    @ResponseBody
    public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        Optional<Employee> employee1=repo.findById(id);
        return repo.save(employee);
    }

}
