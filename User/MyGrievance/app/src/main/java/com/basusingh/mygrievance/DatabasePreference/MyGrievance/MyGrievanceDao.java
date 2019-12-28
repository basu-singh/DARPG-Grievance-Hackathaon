package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface MyGrievanceDao {

    @Query("SELECT * FROM MyGrievanceItems ORDER BY uid DESC")
    List<MyGrievanceItems> getAll();

    @Insert
    void insertAll(List<MyGrievanceItems> mList);

    @Insert
    void insert(MyGrievanceItems mItem);

    @Query("DELETE FROM MyGrievanceItems")
    void deleteAll();

}
