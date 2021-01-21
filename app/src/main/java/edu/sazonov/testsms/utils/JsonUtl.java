package edu.sazonov.testsms.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import edu.sazonov.testsms.MessageTo;

public class JsonUtl {

    private JsonUtl(){
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JSONObject get(MessageTo message){
        JSONObject result = new JSONObject();
        try {
            result.put("senderNumber", message.getSenderNumber());
            result.put("date", message.getDate());
            result.put("messageBody", message.getMessageBody());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
