package edu.sazonov.testsms.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.Telephony;

import androidx.annotation.RequiresApi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.sazonov.testsms.MessageTo;

public class SmsGetter {

    private SmsGetter() {

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Map<Long, MessageTo> getAllIncoming(Context context) {
        //ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        Map<Long, MessageTo> result = new HashMap<>();

        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(Telephony.Sms.CONTENT_URI, null, null, null, null);
        int totalSMS = 0;
        if (c != null) {
            totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int j = 0; j < totalSMS; j++) {
                    String smsDate = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String number = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
//                    System.out.println(number + " NUMBER"); //DELETE
                    String body = c.getString(c.getColumnIndexOrThrow(Telephony.Sms.BODY));
//                    System.out.println(body + " BODY"); //DELETE
                    Date dateFormat = new Date(Long.parseLong(smsDate));
//                    System.out.println(dateFormat + " SMS DATE"); //DELETE
                    String type;

                    result.put(Long.parseLong(smsDate), new edu.sazonov.testsms.MessageTo(dateFormat, body, number));
                    switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
                        case Telephony.Sms.MESSAGE_TYPE_INBOX:
                            type = "inbox";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_SENT:
                            type = "sent";
                            break;
                        case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
                            type = "outbox";
                            break;
                        default:
                            break;
                    }


                    c.moveToNext();
                }
            }

            c.close();
            return result;
        } else {
            return null;
//            Toast.makeText(this, "No message to show!", Toast.LENGTH_SHORT).show();
        }
    }
}
