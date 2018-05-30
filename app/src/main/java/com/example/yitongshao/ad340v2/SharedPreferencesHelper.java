package com.example.yitongshao.ad340v2;

import android.content.SharedPreferences;

 class SharedPreferencesHelper {

    static final String KEY_ENTRY = "text_entry";

    private final SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public boolean saveEntry(String message){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_ENTRY, message);

        return editor.commit();
    }

    public String getEntry() {
        return mSharedPreferences.getString(KEY_ENTRY, "");
    }
}
