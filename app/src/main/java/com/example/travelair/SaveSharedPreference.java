package com.example.travelair;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static final String PREF_USER_EMAIL= "email";
    static final String PREF_USER_PHONE= "phone";
    static final String PREF_USER_NAME= "name";
    static final String PREF_USER_AGE= "age";
    static final String PREF_USER_ID= "id";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void set(Context ctx, String name, String age, String email, String phone, String id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.putString(PREF_USER_PHONE, phone);
        editor.putString(PREF_USER_NAME, name);
        editor.putString(PREF_USER_AGE, age);
        editor.putString(PREF_USER_ID, id);
        editor.commit();
    }

    public static String getEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static String getId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static String getName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getAge(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_AGE, "");
    }

    public static String getPhone(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PHONE, "");
    }

    public static void logout(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, "");
        editor.putString(PREF_USER_PHONE, "");
        editor.putString(PREF_USER_NAME, "");
        editor.putString(PREF_USER_AGE, "");
        editor.putString(PREF_USER_ID, "");
        editor.commit();
    }
}