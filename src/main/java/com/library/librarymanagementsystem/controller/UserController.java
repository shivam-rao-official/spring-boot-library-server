package com.library.librarymanagementsystem.controller;


import com.library.librarymanagementsystem.entity.IssueReturnBooks;
import com.library.librarymanagementsystem.response.SuccessResponse.ApiResponse;
import com.library.librarymanagementsystem.service.USERS.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/lbs/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> userLogin(@RequestParam String email, @RequestParam String pswd) {
        boolean loginSuccess  = this.usersService.userLogin(email, pswd);

        if (loginSuccess){
            return new ApiResponse().apiResponse(true, HttpStatus.OK, "STUDENT", "Logged IN");
        }else {
            return new ApiResponse().apiResponse(false, HttpStatus.NOT_FOUND, "FAILED");
        }
    }

    @GetMapping(value = "/report")
    public ResponseEntity<?> showReport(@RequestParam String email, @RequestParam String filter){
        if(this.usersService.showReport(email, filter).isEmpty()){
            return new ApiResponse().apiResponse(false, HttpStatus.BAD_REQUEST, "No Records");
        }
        return new ApiResponse().apiResponse(true ,HttpStatus.OK,this.usersService.showReport(email, filter) , "");
    }
}
