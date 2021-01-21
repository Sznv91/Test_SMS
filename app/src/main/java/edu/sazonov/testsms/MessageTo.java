package edu.sazonov.testsms;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MessageTo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
    private final String date;
    private final String messageBody;
    private final String senderNumber;

    public MessageTo(Date date, String messageBody, String senderNumber) {
        this.date = formatter.format(date.toInstant().atZone(ZoneId.systemDefault()));
        this.messageBody = messageBody;
        this.senderNumber = senderNumber;
    }

    public String getDate() {
        return date;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    @Override
    public String toString() {
        return "MessageTo{" +
                "date='" + date + '\'' +
                ", messageBody='" + messageBody + '\'' +
                ", senderNumber='" + senderNumber + '\'' +
                '}';
    }
}
