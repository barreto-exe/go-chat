package com.barreto.exe.gochat.Models;

import android.content.SharedPreferences;
import android.content.Context;

import java.util.UUID;

public class Configs {
    public static String GetUserUuid(Context context) {
        //Try to get the GUID from Shared Preferences
        SharedPreferences sharedPrefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String guid = sharedPrefs.getString("my_guid", null);

        // If the GUID is not saved, generate a new one
        if (guid != null) {
            return guid;
        }

        // Generate the GUID
        UUID newGuid = UUID.randomUUID();

        // Save in Shared Preferences
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("my_guid", newGuid.toString());
        editor.apply();

        return newGuid.toString();
    }
}
