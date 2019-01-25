package com.epiclancers.intrototheroomlibrary.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import static com.epiclancers.intrototheroomlibrary.database.Constants.DATABASE_NAME;

@Database( entities = Mobile.class , version = 2 , exportSchema = false)
public abstract class MobileDatabase extends RoomDatabase {

    public abstract MobileDao mobileDao();

    private static Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE " + Constants.TABLE_MOBILE_NAME
                    + " ADD COLUMN "+Constants.COL_MOBILE_IMAGE+" TEXT");
        }
    };

    private static MobileDatabase ourInstance;

    public static MobileDatabase getInstance(Context context){
        if (ourInstance == null){
            ourInstance = Room.databaseBuilder( context , MobileDatabase.class , DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return ourInstance;
    }
}
