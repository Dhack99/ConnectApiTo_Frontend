package com.example.employeeMS.controller;

import com.example.employeeMS.Uitl.VarList;
import com.example.employeeMS.dto.EmployeeDTO;
import com.example.employeeMS.dto.ResponseDTO;
import com.example.employeeMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res = employeeService.saveEmployee(employeeDTO);
            if (res.equals("00")){
                    responseDTO.setCode(VarList.RSP_SUCCESS);
                    responseDTO.setMessage("Success");
                    responseDTO.setContent(employeeDTO);
                    return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee Registered");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping (value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res = employeeService.updateEmployee(employeeDTO);
            if (res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Employee Not Exists");
                responseDTO.setContent(employeeDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllEmployees")
    public ResponseEntity getAllEmployees(){
       try{
           List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
           responseDTO.setCode(VarList.RSP_SUCCESS);
           responseDTO.setMessage("Success");
           responseDTO.setContent(employeeDTOList);
           return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

       }catch (Exception ex){
           responseDTO.setCode(VarList.RSP_ERROR);
           responseDTO.setMessage(ex.getMessage());
           responseDTO.setContent(null);
           return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }

    @GetMapping(value = "/searchEmployee/{empID}")
    public ResponseEntity searchEmployeeByID(@PathVariable int empID){

        try{
            EmployeeDTO employeeDTO = employeeService.searchEmployee(empID);
           if(employeeDTO !=null){
               responseDTO.setCode(VarList.RSP_SUCCESS);
               responseDTO.setMessage("Success");
               responseDTO.setContent(employeeDTO);
               return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
           }else {
               responseDTO.setCode(VarList.RSP_SUCCESS);
               responseDTO.setMessage("No Employee Available For this empID");
               responseDTO.setContent(null);
               return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

           }

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/deleteEmployee/{empID}")
    public ResponseEntity deleteEmployeeByID(@PathVariable int empID){

        try{
            String res = employeeService.deletemEployee(empID);
            if(res.equals("00")){
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("No Employee Available For this empID");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            }
        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
