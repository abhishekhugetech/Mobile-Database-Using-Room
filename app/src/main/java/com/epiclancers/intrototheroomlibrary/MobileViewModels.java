package com.epiclancers.intrototheroomlibrary;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.epiclancers.intrototheroomlibrary.database.Mobile;
import com.epiclancers.intrototheroomlibrary.database.MobileDao;
import com.epiclancers.intrototheroomlibrary.database.MobileDatabase;

import java.util.List;

public class MobileViewModels extends AndroidViewModel {

    public MobileDao mobileDao;
    public String mobileProcessor = "Not Specified",mobileRam = "Not Specified",mobileName,mobilePrice,mobileUrl;
    public String[] mobileProcs,mobileRams;
    public ArrayAdapter<CharSequence> adapter1,adapter2;
    public LiveData<List<Mobile>> listLiveData;

    public MobileViewModels(@NonNull Application application) {
        super(application);
        MobileDatabase database = MobileDatabase.getInstance(application);
        mobileDao = database.mobileDao();

        adapter1 = ArrayAdapter.createFromResource(application,
                R.array.mobile_processors, android.R.layout.simple_spinner_item);
        adapter2 = ArrayAdapter.createFromResource(application,
                R.array.mobile_rams, android.R.layout.simple_spinner_item);

        mobileProcs = application.getResources().getStringArray(R.array.mobile_processors);
        mobileRams = application.getResources().getStringArray(R.array.mobile_rams);
        listLiveData = mobileDao.getMobiles();
    }

    public boolean isBasicEmpty() {
        boolean res = true;
        if (!mobileName.isEmpty() && !mobilePrice.isEmpty() && !mobileUrl.isEmpty()) res = false;
        return res;
    }
    
    public void addMobile(){
        final Mobile mobile = new Mobile( mobileName , mobilePrice , mobileProcessor ,
                mobileRam , mobileUrl );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mobileDao.addMobile(mobile);
            }
        });
        thread.start();
    }

    public void updateMobile(int mobileId) {
        final Mobile mobile = new Mobile( mobileId , mobileName , mobilePrice , mobileProcessor ,
                mobileRam , mobileUrl );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mobileDao.updateMobile(mobile);
            }
        });
        thread.start();
    }

    public void deleteMobile(int mobileId) {
        final Mobile mobile = new Mobile( mobileId , mobileName , mobilePrice , mobileProcessor ,
                mobileRam , mobileUrl );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mobileDao.deleteMobile(mobile);
            }
        });
        thread.start();
    }
}
