package com.library.librarymanagementsystem.service.USERS;

import com.library.librarymanagementsystem.entity.IssueReturnBooks;
import com.library.librarymanagementsystem.entity.Users;
import com.library.librarymanagementsystem.exception.UserNotExistsException;
import com.library.librarymanagementsystem.repository.IssueReturnRepository;
import com.library.librarymanagementsystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private IssueReturnRepository issueReturnRepository;


    public boolean userLogin(String email, String pswd) {
        Users user = this.usersRepository.findByEmailId(email);
        if (user == null) {
            return false;
        }
        boolean psdMatched = this.bCryptPasswordEncoder.matches(pswd, user.getPswd());
        return psdMatched;
    }

    public List<IssueReturnBooks> showReport(String email, String filter) {
        Users user = this.usersRepository.findByEmailId(email);
        if (user == null) throw new UserNotExistsException("No User found");
        if (filter.isEmpty())
            return this.issueReturnRepository.findAllByEmail(user.getId().toString());
        if (filter.equals("nonactive")) {
            System.out.println("CALLED");
            System.out.println("ouput"+this.issueReturnRepository.findAllNonActiveByEmail(user.getId().toString()));
            return this.issueReturnRepository.findAllNonActiveByEmail(user.getId().toString());
        }
        if(filter.equals("active")) {
            return this.issueReturnRepository.findAllActiveByEmail(user.getId().toString());
        }

        return null;
    }
}
