package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MyGrievanceItems.class}, version = 1)
public abstract class MyGrievanceAppDatabase extends RoomDatabase {
    public abstract MyGrievanceDao MyGrievanceDao();
}