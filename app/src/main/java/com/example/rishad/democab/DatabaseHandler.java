package com.example.rishad.democab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishad on 16/8/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="profileDetails";
    private static final String TABLE_PROFILES ="profiles";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IMAGEURL="imageUrl";

    public DatabaseHandler(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_PROFILES_TABLE ="CREATE TABLE " +TABLE_PROFILES+"("
                +KEY_ID+"INTEGER PRIMARY KEY," +KEY_NAME+ "TEXT,"
                +KEY_EMAIL+"TEXT,"+KEY_IMAGEURL+"TEXT"+")";
        db.execSQL(CREATE_PROFILES_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion )
    {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_PROFILES);
        onCreate(db);
    }

    void addProfile(ProfileDetails profileDetails)
    {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(KEY_NAME,profileDetails.getNames());
        values.put(KEY_EMAIL,profileDetails.getEmails());
        values.put(KEY_IMAGEURL,profileDetails.getImageUrl());

        db.insert(TABLE_PROFILES, null,values);
        db.close();
    }

    ProfileDetails getProfile(int id)
    {
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor =db.query(TABLE_PROFILES,new String[]{KEY_ID,
                KEY_NAME,KEY_EMAIL,KEY_IMAGEURL},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        ProfileDetails profileDetails = new ProfileDetails(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3));

            return  profileDetails;
    }

    public List<ProfileDetails> getAllProfiles()
    {
        List<ProfileDetails> profileDetailseList = new ArrayList<ProfileDetails>();
        String selectQuery ="SELECT * FROM "+TABLE_PROFILES;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do
            {
                ProfileDetails profileDetails =new ProfileDetails();
                profileDetails.setID(Integer.parseInt(cursor.getString(0)));
                profileDetails.setNames(cursor.getString(1));
                profileDetails.setEmails(cursor.getString(2));
                profileDetails.setImageUrls(cursor.getString(3));

                profileDetailseList.add(profileDetails);
            }
            while (cursor.moveToNext());
        }
        return profileDetailseList;

    }

    public int updateProfile(ProfileDetails profileDetails)
    {
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values =new ContentValues();
        values.put(KEY_NAME,profileDetails.getNames());
        values.put(KEY_EMAIL,profileDetails.getEmails());
        values.put(KEY_IMAGEURL,profileDetails.getImageUrl());

        return db.update(TABLE_PROFILES, values,KEY_ID+"=?",new String[]{String.valueOf(profileDetails.getID())});

    }

    public void deleteProfile(ProfileDetails profileDetails)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROFILES,KEY_ID +"=?",new String[]{ String.valueOf(profileDetails.getID())});
        db.close();
    }

    public int getProfileCount()
    {
        String countQuery ="SELECT * FROM " +TABLE_PROFILES;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
}
