package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MyGrievanceDao_Impl implements MyGrievanceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MyGrievanceItems> __insertionAdapterOfMyGrievanceItems;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MyGrievanceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMyGrievanceItems = new EntityInsertionAdapter<MyGrievanceItems>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MyGrievanceItems` (`uid`,`subject`,`message`,`ministry`,`image1`,`image2`,`image3`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyGrievanceItems value) {
        stmt.bindLong(1, value.uid);
        if (value.subject == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.subject);
        }
        if (value.message == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.message);
        }
        if (value.ministry == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.ministry);
        }
        if (value.image1 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.image1);
        }
        if (value.image2 == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.image2);
        }
        if (value.image3 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.image3);
        }
        if (value.timestamp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.timestamp);
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM MyGrievanceItems";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final List<MyGrievanceItems> mList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMyGrievanceItems.insert(mList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final MyGrievanceItems mItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMyGrievanceItems.insert(mItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<MyGrievanceItems> getAll() {
    final String _sql = "SELECT * FROM MyGrievanceItems ORDER BY uid DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMinistry = CursorUtil.getColumnIndexOrThrow(_cursor, "ministry");
      final int _cursorIndexOfImage1 = CursorUtil.getColumnIndexOrThrow(_cursor, "image1");
      final int _cursorIndexOfImage2 = CursorUtil.getColumnIndexOrThrow(_cursor, "image2");
      final int _cursorIndexOfImage3 = CursorUtil.getColumnIndexOrThrow(_cursor, "image3");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<MyGrievanceItems> _result = new ArrayList<MyGrievanceItems>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MyGrievanceItems _item;
        _item = new MyGrievanceItems();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.subject = _cursor.getString(_cursorIndexOfSubject);
        _item.message = _cursor.getString(_cursorIndexOfMessage);
        _item.ministry = _cursor.getString(_cursorIndexOfMinistry);
        _item.image1 = _cursor.getString(_cursorIndexOfImage1);
        _item.image2 = _cursor.getString(_cursorIndexOfImage2);
        _item.image3 = _cursor.getString(_cursorIndexOfImage3);
        _item.timestamp = _cursor.getString(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
