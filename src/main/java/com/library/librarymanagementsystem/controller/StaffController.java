package com.library.librarymanagementsystem.controller;

import com.library.librarymanagementsystem.dto.BooksDto;
import com.library.librarymanagementsystem.dto.StaffDto;
import com.library.librarymanagementsystem.dto.UsersDto;
import com.library.librarymanagementsystem.entity.Books;
import com.library.librarymanagementsystem.entity.Staff;
import com.library.librarymanagementsystem.entity.Users;
import com.library.librarymanagementsystem.repository.UsersRepository;
import com.library.librarymanagementsystem.response.SuccessResponse.ApiResponse;
import com.library.librarymanagementsystem.service.Staff.StaffServiceImpl;
import com.library.librarymanagementsystem.service.USERS.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "api/v1/lbs/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffServiceImpl staffService;

    @Autowired
    private UsersService usersService;


    @Autowired
    private ApiResponse apiResponse;

    @PostMapping(value = "/create-admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody Staff admin_detail) {

        boolean userCreated = this.staffService.createAdmin(admin_detail);
        if (userCreated) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.CREATED, "Admin created successfully");
        } else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Some error occurred");
        }

    }

    @PostMapping(value = "/create-staff")
    public ResponseEntity<?> createStaff(@Valid @RequestBody StaffDto staff_detail,@RequestParam String admin_email) {

        System.out.println(admin_email);
        boolean userCreated = this.staffService.createStaff(staff_detail, admin_email);
        if (userCreated) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.CREATED, "Staff created successfully");
        } else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Some error occurred");
        }

    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersDto user_detail, @RequestParam String staff_email) {

        System.out.println(staff_email);
        boolean userCreated = this.staffService.createUser(user_detail, staff_email);
        if (userCreated) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.CREATED, "User created successfully");
        } else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Some error occurred");
        }

    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> stafflogin(@RequestParam String email, @RequestParam String pswd) {
        boolean userLoggedIn = this.staffService.staffLogin(email, pswd);
        if (userLoggedIn) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.CREATED, "STAFF","Login successfully");
        }
        userLoggedIn = this.usersService.userLogin(email, pswd);
        if (userLoggedIn) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.CREATED, "STUDENT","Login successfully");
        }
        else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Invalid credential");
        }

    }

    @PostMapping(value = "/add-book")
    public ResponseEntity<?> addBook(@Valid @RequestBody BooksDto book_detail, @RequestParam String staff_email) {
        boolean userCreated = this.staffService.addBook(book_detail, staff_email);
        if (userCreated) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.CREATED, "Book added successfully");
        } else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.INTERNAL_SERVER_ERROR, "Some error occurred");
        }

    }

    @GetMapping(value = "/show")
    public ResponseEntity<?> showAllStaff() {
        return this.apiResponse.apiResponse(true,
                HttpStatus.OK,this.staffService.showAllStaff(),
                "Total "+ this.staffService.showAllStaff().size()+ " staff exist.");
    }

    @PutMapping(value = "/deactive-user")
    public ResponseEntity<?> deactiveStaff(
            @RequestParam String staff_email,
            @RequestParam String admin_email) {
        if (this.staffService.deactivateStaff(staff_email, admin_email)) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, "Staff deactivated successfully.");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.BAD_REQUEST, "Some error occurred.");
        }
    }

    @PostMapping(value = "/issue")
    public ResponseEntity<?> issueBook(
            @RequestParam String isbn,
            @RequestParam String user_email,
            @RequestParam String staff_email) {
        if (this.staffService.issueBook(isbn, user_email, staff_email)) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, "Book issued successfully.");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK, "Book already issued to someone else");
        }
    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateBook(
            @RequestParam String staff_email, @RequestBody Books book) {
        if (this.staffService.updateBook(staff_email, book)) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, "Book updated successfully.");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK, "Book already issued can't update.");
        }
    }

    @PostMapping(value = "/return")
    public ResponseEntity<?> returnBook(
            @RequestParam String isbn,
            @RequestParam String user_email) {
        if (this.staffService.returnBook(isbn, user_email)) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, "Book returned successfully.");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK, "Invalid ISBN or User ID");
        }
    }

    @GetMapping(value = "/showAllBooks")
    public ResponseEntity<?> showAllBooks() {
        if (!this.staffService.showAllBooks().isEmpty()) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, this.staffService.showAllBooks(),"");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK,this.staffService.showAllBooks(), "No Books Found");
        }
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<?> booksSummary() {
        if (!this.staffService.bookSummary().isEmpty()) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, this.staffService.bookSummary(),"");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK,this.staffService.showAllBooks(), "No Books Found");
        }
    }

    @GetMapping(value = "/userSummary")
    public ResponseEntity<?> userSummary() {
        if (!this.staffService.userSummary().isEmpty()) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, this.staffService.userSummary(),"");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK,this.staffService.userSummary(), "No Users Found");
        }
    }

    @DeleteMapping(value = "/delete-books")
    public ResponseEntity<?> removeBook(@RequestBody Books book, @RequestParam String email) {
        if (!this.staffService.removeBook(book, email)) {
            return this.apiResponse.apiResponse(true,
                    HttpStatus.OK, "DONE");
        }else {
            return this.apiResponse.apiResponse(false,
                    HttpStatus.OK,"No Books Found");
        }
    }

    @GetMapping(value = "/showBooks")
    public ResponseEntity<?> showAllBooksByFilter(@RequestParam String filter){
        List<Books> listOfBooks = this.staffService.showBooks(filter);
        return this.apiResponse.apiResponse(true,
                HttpStatus.OK, listOfBooks,"DONE");
    }

    @GetMapping(value = "/showUsers")
    public ResponseEntity<?> showAllUsersByFilter(@RequestParam String filter){
        List<Users> listOfUsers = this.staffService.showUsers(filter);
        return this.apiResponse.apiResponse(true,
                HttpStatus.OK, listOfUsers,"DONE");
    }
}
