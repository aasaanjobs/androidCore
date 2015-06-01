package aasaanjobs.com.aasaan_http_core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import aasaanjobs.com.aasaan_http_core.exceptions.ObjectToPreferenceConversionException;
import aasaanjobs.com.aasaan_http_core.exceptions.PreferencesToObjectConversionException;
import aasaanjobs.com.aasaan_http_core.models.BaseDO;

// TODO: Auto-generated Javadoc
/**
 * Created by dineshsingh on 02/03/15.
 */
public class SharedPreferenceUtil {


    /** The instance. */
    private static SharedPreferenceUtil instance;
    
    /** The context. */
    private static Context context;


    /**
     * Instantiates a new shared preference util.
     */
    private SharedPreferenceUtil() {

    }

    /**
     * Initialise shared preference.
     *
     * @param ctx the ctx
     */
    public static void initialiseSharedPreference(Context ctx) {
        instance = getInstance();
        context = ctx;


    }

    /**
     * Gets the single instance of SharedPreferenceUtil.
     *
     * @return single instance of SharedPreferenceUtil
     */
    public static SharedPreferenceUtil getInstance() {
        if (instance == null) {
            instance = new SharedPreferenceUtil();
        }
        return instance;
    }

    /**
     * Adds the string to shared preference.
     *
     * @param name the name
     * @param key the key
     * @param value the value
     */
    public static void addStringToSharedPreference(String name, String key, String value) {
        context.getSharedPreferences(name, 0).edit().putString(key, value).commit();
    }

    /**
     * Adds the object to shared preference.
     *
     * @param <T> the generic type
     * @param ctx the ctx
     * @param name the name
     * @param key the key
     * @param obj the obj
     * @throws ObjectToPreferenceConversionException the object to preference conversion exception
     */
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

    /**
     * Read shared preference.
     *
     * @param <T> the generic type
     * @param ctx the ctx
     * @param name the name
     * @param key the key
     * @param clazz the clazz
     * @return the t
     * @throws PreferencesToObjectConversionException the preferences to object conversion exception
     */
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

    /**
     * Contains.
     *
     * @param ctx the ctx
     * @param name the name
     * @param key the key
     * @return true, if successful
     */
    public static boolean contains(Context ctx, String name, String key) {

        return context.getSharedPreferences(name, 0).contains(key);
    }

    /**
     * Removes the pref.
     *
     * @param ctx the ctx
     * @param name the name
     * @param key the key
     */
    public static void removePref(Context ctx, String name, String key) {
        context.getSharedPreferences(name, 0).edit().remove(key).commit();

    }

    /**
     * Update object to shared preference.
     *
     * @param ctx the ctx
     * @param sharedPreferenceName the shared preference name
     * @param bookedSlotCenterDetailsKey the booked slot center details key
     * @param object the object
     * @throws ObjectToPreferenceConversionException the object to preference conversion exception
     */
    public static void updateObjectToSharedPreference(Context ctx, String sharedPreferenceName, String bookedSlotCenterDetailsKey, Object object) throws ObjectToPreferenceConversionException {

        removePref(context, sharedPreferenceName, bookedSlotCenterDetailsKey);
        addObjectToSharedPreference(context, sharedPreferenceName, bookedSlotCenterDetailsKey, object);
    }

    /**
     * Adds the object to shared preference.
     *
     * @param sharedPreferenceName the shared preference name
     * @param key the key
     * @param result the result
     * @throws ObjectToPreferenceConversionException the object to preference conversion exception
     */
    public static void addObjectToSharedPreference(String sharedPreferenceName, String key, BaseDO result) throws ObjectToPreferenceConversionException {
        updateObjectToSharedPreference(context, sharedPreferenceName, key, result);
    }

    /**
     * Read shared preference.
     *
     * @param sharedPreferenceName the shared preference name
     * @param key the key
     * @param clazz the clazz
     * @return the base do
     * @throws PreferencesToObjectConversionException the preferences to object conversion exception
     */
    public static BaseDO readSharedPreference(String sharedPreferenceName, String key, Class<? extends BaseDO> clazz) throws PreferencesToObjectConversionException {
        return readSharedPreference(context, sharedPreferenceName, key, clazz);
    }

    /**
     * Read shared preference string.
     *
     * @param sharedPreferenceName the shared preference name
     * @param key the key
     * @return the string
     */
    public static String readSharedPreferenceString(String sharedPreferenceName, String key) {
        return context.getSharedPreferences(sharedPreferenceName, 0).getString(key, null);
    }

    /**
     * Adds the int to shared preference.
     *
     * @param sharedPreferenceName the shared preference name
     * @param propertyAppVersion the property app version
     * @param appVersion the app version
     */
    public static void addIntToSharedPreference(String sharedPreferenceName, String propertyAppVersion, int appVersion) {
        context.getSharedPreferences(sharedPreferenceName, 0).edit().putInt(propertyAppVersion, appVersion).commit();
    }

    /**
     * Read shared preference int.
     *
     * @param sharedPreferenceName the shared preference name
     * @param propertyAppVersion the property app version
     * @return the int
     */
    public static int readSharedPreferenceInt(String sharedPreferenceName, String propertyAppVersion) {
        return context.getSharedPreferences(sharedPreferenceName, 0).getInt(propertyAppVersion, 0);
    }

    /**
     * Read default shared preference string.
     *
     * @param key the key
     * @return the string
     */
    public static String readDefaultSharedPreferenceString(String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, null);
    }

    /**
     * Adds the string to default shared preference.
     *
     * @param key the key
     * @param value the value
     */
    public static void addStringToDefaultSharedPreference(String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Adds the to shared preference.
     *
     * @param ctx the ctx
     * @param name the name
     * @param key the key
     * @param value the value
     */
    public void addToSharedPreference(Context ctx, String name, String key, String value) {
        context.getSharedPreferences(name, 0).edit().putString(key, value).commit();
    }
}
