package com.library.librarymanagementsystem.service.Staff;

import com.library.librarymanagementsystem.dto.BooksDto;
import com.library.librarymanagementsystem.dto.StaffDto;
import com.library.librarymanagementsystem.dto.UsersDto;
import com.library.librarymanagementsystem.entity.Books;
import com.library.librarymanagementsystem.entity.Staff;

import java.util.List;

public interface StaffServices {

    //    CREATING A NEW ADMIN
    public boolean createAdmin(StaffDto admin_detail);

    //    CREATING NEW STAFF
    public boolean createStaff(StaffDto staff_detail, String admin_email);

    //    DE-ACTIVATING STAFF/ADMIN
    public boolean deactivateStaff(String staff_email, String admin_email);

    //    UPDATE PASSWORD
    public boolean updatePassword(String staff_email, String oldPassword, String newPassword, String confirmNewPassword);

    //    FORGOT PASSWORD
    public boolean forgetPassword(String staff_email, String newPassword, String confirmNewPassword);

    public boolean staffLogin(String email, String pswd);
    //    LIST ALL STAFF
    public List<Staff> showAllStaff();

    //    CREATE NEW USER
    public boolean createUser(UsersDto user_detail, String staff_email);

    public boolean addBook(BooksDto book_detail, String staff_email);

    public boolean issueBook(String bookIsbn, String userEmail, String staffEmail);

    public boolean returnBook(String bookIsbn, String userEmail);

    public List<Books> showAllBooks();
}
