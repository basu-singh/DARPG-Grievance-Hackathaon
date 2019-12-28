package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import android.content.Context;

import androidx.room.Room;

public class MyGrievanceDatabaseClient {

    private Context mCtx;
    private static MyGrievanceDatabaseClient mInstance;

    private MyGrievanceAppDatabase appDatabase;

    private MyGrievanceDatabaseClient(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx, MyGrievanceAppDatabase.class, "MyGrievances").build();
    }

    public static synchronized MyGrievanceDatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new MyGrievanceDatabaseClient(mCtx);
        }
        return mInstance;
    }

    public MyGrievanceAppDatabase getAppDatabase() {
        return appDatabase;
    }
}
