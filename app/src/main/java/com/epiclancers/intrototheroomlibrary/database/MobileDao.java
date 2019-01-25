package com.epiclancers.intrototheroomlibrary.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MobileDao {

    @Insert
    void addMobile(Mobile mobile);

    @Update
    void updateMobile(Mobile mobile);

    @Delete
    void deleteMobile(Mobile mobile);

    @Query("SELECT * FROM mobile_tables order by mob_id desc")
    LiveData<List<Mobile>> getMobiles();

}
