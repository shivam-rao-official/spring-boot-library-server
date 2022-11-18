package com.library.librarymanagementsystem.service.Staff;

import com.library.librarymanagementsystem.Utils.GetSysTime;
import com.library.librarymanagementsystem.dto.BooksDto;
import com.library.librarymanagementsystem.dto.StaffDto;
import com.library.librarymanagementsystem.dto.UsersDto;
import com.library.librarymanagementsystem.entity.Books;
import com.library.librarymanagementsystem.entity.IssueReturnBooks;
import com.library.librarymanagementsystem.entity.Staff;
import com.library.librarymanagementsystem.entity.Users;
import com.library.librarymanagementsystem.exception.EntityNotExists;
import com.library.librarymanagementsystem.exception.UnauthorisedAccess;
import com.library.librarymanagementsystem.exception.UserNotExistsException;
import com.library.librarymanagementsystem.repository.BookRepository;
import com.library.librarymanagementsystem.repository.IssueReturnRepository;
import com.library.librarymanagementsystem.repository.StaffRepository;
import com.library.librarymanagementsystem.repository.UsersRepository;
import com.library.librarymanagementsystem.service.serviceInterface.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;


@Service
public class StaffServiceImpl implements StaffServices, BookService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssueReturnRepository issueReturnRepository;

    @Autowired
    private GetSysTime getSysTime;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createAdmin(Staff admin_detail) {
        boolean isEmailExist = this.staffRepository.existsByEmailId(admin_detail.getEmailId());
        if (isEmailExist) {
            throw new UserNotExistsException("Admin already exist with same mail ID>");
        } else {
            boolean isCollegeIdExist = this.staffRepository.existsByCollegeId(admin_detail.getCollegeId());
            if (isCollegeIdExist) {
                throw new UserNotExistsException("User Already exist with same ID");
            }
            Staff newAdmin = Staff.builder()
                    .isAdmin(true)
                    .collegeId(admin_detail.getCollegeId())
                    .name(admin_detail.getName())
                    .emailId(admin_detail.getEmailId())
                    .isActive(true).pswd(passwordEncoder.encode(admin_detail.getPswd()))
                    .createdAt(this.getSysTime.getTimeStamp())
                    .build();
            this.staffRepository.save(newAdmin);
            return true;
        }
    }

    @Override
    public boolean createStaff(StaffDto staff_detail, String admin_email) {
        boolean isAdminExist = this.staffRepository.existsByEmailId(admin_email);
        if (!isAdminExist) {
            throw new UserNotExistsException("No admin exist with the given Id.");
        } else {
            boolean isUserExistWithSameCollegeId = this.staffRepository.existsByCollegeId(staff_detail.getCollegeId());
            if (isUserExistWithSameCollegeId) {
                throw new UserNotExistsException("User Already exist with same College ID");
            }
//            TO CHECK WHETHER ANY STAFF ALREADY EXIST WITH SAME EMAIL OR NOT
            boolean isStaffEmailExist = this.staffRepository.existsByEmailId(staff_detail.getEmailId());
            if (isStaffEmailExist) {
                throw new UserNotExistsException("Staff already exists with same email");
            }
            boolean isUserAdmin = this.staffRepository.findAdminIdByEmailId(admin_email);
            if (!isUserAdmin) {
                throw new UserNotExistsException("User is not admin");
            } else {
                Long adminId = this.staffRepository.findUserIdByEmailId(admin_email);
                Staff newAdmin = Staff.builder()
                        .isAdmin(false)
                        .collegeId(staff_detail.getCollegeId())
                        .name(staff_detail.getName())
                        .emailId(staff_detail.getEmailId())
                        .isActive(true)
                        .pswd(passwordEncoder.encode(staff_detail.getPswd()))
                        .createdAt(this.getSysTime.getTimeStamp())
                        .createdBy(adminId)
                        .build();
                this.staffRepository.save(newAdmin);
                return true;
            }
        }
    }

    @Override
    public boolean deactivateStaff(String staff_email, String admin_email) {
//        ONLY ADMIN CAN DEACTIVATE ANY STAFF AND ITSELF
//        IF USER IS ADMIN
        boolean isUserAdmin = this.staffRepository.findAdminIdByEmailId(admin_email);
        if (!isUserAdmin) throw new UnauthorisedAccess("Unauthorised access");
//        IF STAFF_EMAIL IS EXIST THEN DEACTIVATE
        boolean isStaffExists = this.staffRepository.existsByEmailId(staff_email);
        if (!isStaffExists) throw new UserNotExistsException("No user exist with the given email id.");
        Staff user = this.staffRepository.findByEmailId(staff_email);
        Staff updatedValue = Staff.builder()
                .id(user.getId())
                .name(user.getName())
                .pswd(user.getPswd())
                .collegeId(user.getCollegeId())
                .emailId(user.getEmailId())
                .isActive(false)
                .modified_on(this.getSysTime.getTimeStamp())
                .build();
        this.staffRepository.save(updatedValue);
        return true;
    }

    @Override
    public boolean updatePassword(String staff_email, String oldPassword, String newPassword, String confirmNewPassword) {
        return false;
    }

    @Override
    public boolean forgetPassword(String staff_email, String newPassword, String confirmNewPassword) {
        return false;
    }

    @Override
    public List<Staff> showAllStaff() {
        return this.staffRepository.findAll();
    }

    @Override
    public boolean createUser(UsersDto user_detail, String staff_email) {
        boolean isStaffEmailExists = this.staffRepository.existsByEmailId(staff_email);
        if (!isStaffEmailExists) throw new UserNotExistsException("No staff exist with given email id.");
        Staff staffDetail = this.staffRepository.findByEmailId(staff_email);
        boolean isUserExistsWithSameEmail = this.usersRepository.existsByEmailId(user_detail.getEmailId());
        if (isUserExistsWithSameEmail) throw new UserNotExistsException("User already exist with same mail ID");
        Users newUser = Users.builder()
                .name(user_detail.getName())
                .dept(user_detail.getDept())
                .collegeId(user_detail.getCollegeId())
                .emailId(user_detail.getEmailId())
                .pswd(this.passwordEncoder.encode(user_detail.getPswd()))
                .staff(staffDetail)
                .createdAt(this.getSysTime.getTimeStamp())
                .build();
        this.usersRepository.save(newUser);
        return true;
    }

    @Override
    public boolean staffLogin(String email, String pswd) {
        Staff staff = this.staffRepository.findByEmailId(email);
        if (staff == null) return false;
        return this.passwordEncoder.matches(pswd, staff.getPswd());
    }

    @Override
    public boolean addBook(BooksDto book_detail, String staff_email) {
        boolean isStaffEmailExists = this.staffRepository.existsByEmailId(staff_email);
        if (!isStaffEmailExists) throw new UserNotExistsException("No staff exist with given email id.");
        Staff staffDetail = this.staffRepository.findByEmailId(staff_email);
        Books addNewBook = Books.builder()
                .bookName(book_detail.getBookName())
                .bookEdition(book_detail.getBookEdition())
                .bookPrice(book_detail.getBookPrice())
                .bookISBNNumber(book_detail.getBookISBNNumber())
                .bookAuthorName(book_detail.getBookAuthorName())
                .staff(staffDetail)
                .addedOn(this.getSysTime.getTimeStamp())
                .dept(book_detail.getDept())
                .isInStock(true)
                .build();

        this.bookRepository.save(addNewBook);
        return true;
    }

    @Transactional
    @Override
    public boolean issueBook(String bookISBN,
                             String userEmail,
                             String staffEmail) {
        boolean staffExist = this.staffRepository.existsByEmailId(staffEmail);
        if (!staffExist) throw new UserNotExistsException("No Staff exist with given mail ID.");
        boolean userExist = this.usersRepository.existsByEmailId(userEmail);
        if (!userExist) throw new UserNotExistsException("No User exist with given mail ID.");

        Books bookEntity = this.bookRepository.findByISBN(bookISBN);
        Staff staffEntity = this.staffRepository.findByEmailId(staffEmail);
        Users userEntity = this.usersRepository.findByEmailId(userEmail);
        if (bookEntity != null && bookEntity.isInStock()) {
            IssueReturnBooks issueReturnBooks = IssueReturnBooks.builder()
                    .books(bookEntity)
                    .staff(staffEntity)
                    .user(userEntity)
                    .issueOn(this.getSysTime.getTimeStamp())
                    .issuedTill(this.getSysTime.getTimeStamp(10))
                    .build();

            this.issueReturnRepository.save(issueReturnBooks);

            Books updateBook = Books.builder()
                    .bookId(bookEntity.getBookId())
                    .bookName(bookEntity.getBookName())
                    .bookEdition(bookEntity.getBookEdition())
                    .bookPrice(bookEntity.getBookPrice())
                    .bookISBNNumber(bookEntity.getBookISBNNumber())
                    .bookAuthorName(bookEntity.getBookAuthorName())
                    .staff(bookEntity.getStaff())
                    .addedOn(bookEntity.getAddedOn())
                    .isInStock(false)
                    .build();
            this.bookRepository.save(updateBook);
            return true;
        }
        if (bookEntity == null) throw new UserNotExistsException("Book Not Exist");
        return bookEntity.isInStock();
    }

    public boolean updateBook(String email, Books bookEntity) {
        Staff staff = this.staffRepository.findByEmailId(email);
        if (this.bookRepository.bookIsIssued(bookEntity.getBookISBNNumber()) == null) {
            Books updateBook = Books.builder()
                    .bookId(bookEntity.getBookId())
                    .bookName(bookEntity.getBookName())
                    .bookEdition(bookEntity.getBookEdition())
                    .bookPrice(bookEntity.getBookPrice())
                    .bookISBNNumber(bookEntity.getBookISBNNumber())
                    .bookAuthorName(bookEntity.getBookAuthorName())
                    .staff(staff)
                    .addedOn(bookEntity.getAddedOn())
                    .isInStock(bookEntity.isInStock())
                    .dept(bookEntity.getDept())
                    .build();
            this.bookRepository.save(updateBook);
            return true;
        } else
            return false;

    }

    @Transactional
    @Override
    public boolean returnBook(String bookISBN,
                              String userEmail) {
        Books bookId = this.bookRepository.findByISBN(bookISBN);
        if (bookId == null) throw new EntityNotExists("Book not exists kindly enter correct ISBN");
        Users userId = this.usersRepository.findByEmailId(userEmail);
        if (userId == null) throw new UserNotExistsException("User not exists kindly enter correct Email ID");

        IssueReturnBooks issueDetail = this.issueReturnRepository.findByUserIdAndISBN(
                userId.getId(), bookId.getBookId());
        if (issueDetail == null) return false;

        IssueReturnBooks update = IssueReturnBooks.builder()
                .issuedTill(issueDetail.getIssuedTill())
                .books(issueDetail.getBooks())
                .returnOn(this.getSysTime.getTimeStamp())
                .issueOn(issueDetail.getIssueOn())
                .staff(issueDetail.getStaff())
                .user(issueDetail.getUser())
                .issueId(issueDetail.getIssueId())
                .build();

        this.issueReturnRepository.save(update);

        Books updateBook = Books.builder()
                .bookId(bookId.getBookId())
                .bookName(bookId.getBookName())
                .bookEdition(bookId.getBookEdition())
                .bookPrice(bookId.getBookPrice())
                .bookISBNNumber(bookId.getBookISBNNumber())
                .bookAuthorName(bookId.getBookAuthorName())
                .staff(bookId.getStaff())
                .addedOn(bookId.getAddedOn())
                .isInStock(true)
                .build();

        this.bookRepository.save(updateBook);
        return true;
    }

    @Override
    public List<Books> showAllBooks() {
        List<Books> books = this.bookRepository.findAll();
        return books;
    }

    @Override
    public HashMap<?, ?> bookSummary() {
        HashMap<String, Integer> summary = new HashMap<>();
        int totalBooks = this.bookRepository.countOfBooks();
        int issuedBooks = this.bookRepository.countOfIssuedBooks();
        int unIssuedBooks = this.bookRepository.countOfUnIssuedBooks();
        summary.put("Total", totalBooks);
        summary.put("issued", issuedBooks);
        summary.put("unIssued", unIssuedBooks);

        System.out.println(totalBooks);
        return summary;
    }

    @Override
    public boolean removeBook(Books book, String email) {
        if (this.staffRepository.findAdminIdByEmailId(email)) {
            if (this.bookRepository.findByISBN(book.getBookISBNNumber()) != null) {
                this.bookRepository.delete(book);
                return true;
            } else return false;
        } else return false;

    }

    public List<Books> showBooks(String filterType) {
        if (filterType.equals("all")) {
            return this.bookRepository.findAll();
        } else if (filterType.equals("avl")) {
            return this.bookRepository.findAllByFilter("1");
        } else {
            return this.bookRepository.findAllByFilter("0");
        }
    }


    public HashMap<?, ?> userSummary() {
        HashMap<String, Integer> summary = new HashMap<>();
        int totalUsers = this.usersRepository.countOfUsers();
        int activeUser = this.usersRepository.countOfActiveUser();
        int inActiveUsers = this.usersRepository.countOfInactiveUser();
        System.out.println(summary);
        summary.put("Total", totalUsers);
        summary.put("Active", activeUser);
        summary.put("InActive", inActiveUsers);
        return summary;
    }

    public List<Users> showUsers(String filterType) {
        if (filterType.equals("all")) {
            return this.usersRepository.findAll();
        } else if (filterType.equals("active")) {
            return this.usersRepository.findAllByFilter("0");
        } else {
            return this.usersRepository.findAllByFilter("1");
        }
    }
}
