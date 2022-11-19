package com.library.librarymanagementsystem.Utils;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Service
public class GetSysTime {

    Calendar calendar = Calendar.getInstance();

    public String getTimeStamp() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = new Date();
        return df.format(dateObj);
    }

//    METHOD TO CALCULATE FUTURE DATE
    public String getTimeStamp(int futureDate) {
        Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateObj = calendar.getTime();
        return df.format(dateObj.getTime());
    }
}
