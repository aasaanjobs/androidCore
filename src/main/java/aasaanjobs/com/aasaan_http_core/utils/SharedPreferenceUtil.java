package aasaanjobs.com.aasaan_http_core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import aasaanjobs.com.aasaan_http_core.exceptions.ObjectToPreferenceConversionException;
import aasaanjobs.com.aasaan_http_core.exceptions.PreferencesToObjectConversionException;
import aasaanjobs.com.aasaan_http_core.models.BaseDO;

/**
 * Created by dineshsingh on 02/03/15.
 */
public class SharedPreferenceUtil {


    private static SharedPreferenceUtil instance;
    private static Context context;


    private SharedPreferenceUtil() {

    }

    public static void initialiseSharedPreference(Context ctx) {
        instance = getInstance();
        context = ctx;


    }

    public static SharedPreferenceUtil getInstance() {
        if (instance == null) {
            instance = new SharedPreferenceUtil();
        }
        return instance;
    }

    public static void addStringToSharedPreference(String name, String key, String value) {
        context.getSharedPreferences(name, 0).edit().putString(key, value).commit();
    }

    public static <T> void addObjectToSharedPreference(Context ctx, String name, String key, T obj) throws ObjectToPreferenceConversionException {

        ObjectMapper mapper = new ObjectMapper();
        try {

            String objectAsString = mapper.writeValueAsString(obj);
            context.getSharedPreferences(name, 0).edit().putString(key, objectAsString).commit();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ObjectToPreferenceConversionException();

        }

    }

    public static <T> T readSharedPreference(Context ctx, String name, String key, Class<T> clazz) throws PreferencesToObjectConversionException {
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();

        //context.getSharedPreferences(name,0).edit().putString(key,value).commit();
        try {


            String s = context.getSharedPreferences(name, 0).getString(key, "");
            obj = mapper.readValue(s, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PreferencesToObjectConversionException();

        }
        return obj;
    }

    public static boolean contains(Context ctx, String name, String key) {

        return context.getSharedPreferences(name, 0).contains(key);
    }

    public static void removePref(Context ctx, String name, String key) {
        context.getSharedPreferences(name, 0).edit().remove(key).commit();

    }

    public static void updateObjectToSharedPreference(Context ctx, String sharedPreferenceName, String bookedSlotCenterDetailsKey, Object object) throws ObjectToPreferenceConversionException {

        removePref(context, sharedPreferenceName, bookedSlotCenterDetailsKey);
        addObjectToSharedPreference(context, sharedPreferenceName, bookedSlotCenterDetailsKey, object);
    }

    public static void addObjectToSharedPreference(String sharedPreferenceName, String key, BaseDO result) throws ObjectToPreferenceConversionException {
        updateObjectToSharedPreference(context, sharedPreferenceName, key, result);
    }

    public static BaseDO readSharedPreference(String sharedPreferenceName, String key, Class<? extends BaseDO> clazz) throws PreferencesToObjectConversionException {
        return readSharedPreference(context, sharedPreferenceName, key, clazz);
    }

    public static String readSharedPreferenceString(String sharedPreferenceName, String key) {
        return context.getSharedPreferences(sharedPreferenceName, 0).getString(key, null);
    }

    public static void addIntToSharedPreference(String sharedPreferenceName, String propertyAppVersion, int appVersion) {
        context.getSharedPreferences(sharedPreferenceName, 0).edit().putInt(propertyAppVersion, appVersion).commit();
    }

    public static int readSharedPreferenceInt(String sharedPreferenceName, String propertyAppVersion) {
        return context.getSharedPreferences(sharedPreferenceName, 0).getInt(propertyAppVersion, 0);
    }

    public static String readDefaultSharedPreferenceString(String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, null);
    }

    public static void addStringToDefaultSharedPreference(String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void addToSharedPreference(Context ctx, String name, String key, String value) {
        context.getSharedPreferences(name, 0).edit().putString(key, value).commit();
    }
}
