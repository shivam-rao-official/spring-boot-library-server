package com.library.librarymanagementsystem.Utils;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class GetSysTime {
    public String getTimeStamp() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        return df.format(dateObj);
    }

//    METHOD TO CALCULATE FUTURE DATE
    public String getTimeStamp(int futureDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        return df.format(dateObj.getTime() + 10);
    }
}
