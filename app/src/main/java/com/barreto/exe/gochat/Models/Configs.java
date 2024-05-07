package com.barreto.exe.gochat.models;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

public class Configs {
    public static String GetUserUuid(Context context) {
        //if (true) return "123456";

        SharedPreferences sharedPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String guid = sharedPrefs.getString("my_guid", null);

        if (guid != null) {
            return guid;
        }

        return "";
    }

    public static void SaveUserUuid(Context context, String uuid) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("my_guid", uuid);
        editor.apply();
    }

    public static String GetUsername(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String username = sharedPrefs.getString("my_username", null);

        if (username != null) {
            return username;
        }

        return "";
    }

    public static void SaveUsername(Context context, String username) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("my_username", username);
        editor.apply();
    }
}
