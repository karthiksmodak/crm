package com.crm.controller;

import com.crm.entity.Employee;
import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

//@Controller used when calling from HTML or UI
//so we use the below @RestController annotation instead acts as mediator between the server and the client
@RestController
@RequestMapping("/api/v1/employee")  //this API will be accessible via http://localhost:8080/api/v1/employee
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    //Before using ResponseEntity for sending the correct status response
//    @PostMapping("/add")
//    public String addEmployee(
//            @RequestBody Employee employee
//    ) {
//        System.out.println(employee.getName());
//        System.out.println(employee.getEmailId());
//        System.out.println(employee.getMobile());
//        employeeService.addEmployee(employee);
//        return "saved";
//    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto dto,
            BindingResult result
    ) {
        System.out.println(dto.getName());
        System.out.println(dto.getEmailId());
        System.out.println(dto.getMobile());

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto employeeDto= employeeService.addEmployee(dto);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

// Before ResponseEntity
//    @DeleteMapping
//    public String deleteEmployee(@RequestParam Long id){
//        employeeService.deleteEmployee(id);
//        return "Deleted";
//    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

// Before ResponseEntity
//    @PutMapping()
//    public String updateEmployee(@RequestParam Long id,@RequestBody EmployeeDto dto){
//        employeeService.updateEmployee(id,dto);
//        return "Updated";
//    }

    @PutMapping()
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam Long id,@RequestBody EmployeeDto dto){
        EmployeeDto employeeDto=employeeService.updateEmployee(id,dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    //Before ResponseEntity
//    @GetMapping()
//    public List<Employee> getEmployee(){
//        List<Employee> employees=employeeService.getEmployee();
//        return employees;
//    }

//    http://localhost:8080/api/v1/employee?pageSize=3&pageNo=1@sortBy=email&sortDit=asc
    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getEmployees(
            @RequestParam(name="pageSize",required=false,defaultValue = "5") int pageSize,
            @RequestParam(name="pageNo",required=false,defaultValue = "0") int pageNo,
            @RequestParam(name="sortBy",required=false,defaultValue = "name") String sortBy,
            @RequestParam(name="sortDir",required=false,defaultValue = "asc") String sortDir
    ){
        List<EmployeeDto> employeesDto=employeeService.getEmployee(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(employeesDto,HttpStatus.OK);
    }

    //getting record with a particular employeeID
    //http://localhost:8080/api/v1/employee/employeeId/1
    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long empId){
        EmployeeDto dto=employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    // to handle exception we use universal class known as exception/ResourceNotFound
}