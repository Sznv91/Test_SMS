package edu.sazonov.testsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Date;

import edu.sazonov.testsms.utils.JsonUtl;

public class SmsReceiver extends BroadcastReceiver {

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey("pdus")) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[0]);
            String senderNumber = sms.getOriginatingAddress();
            String messageBody = sms.getMessageBody();
            Date date = new Date(sms.getTimestampMillis());

            MessageTo message = new MessageTo(date, messageBody, senderNumber);
            /*if (senderIsInBlackList(senderNumber)) {
                abortBroadcast();
            }*/

            /*SEND MESSAGE*/
            /*new SendDeviceDetails().execute("https://softvillage.ru/test_05.php"
                    , JsonUtl.get(message).toString());*/


            System.out.println(JsonUtl.get(message));
        }
    }
}