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
public final class FavouriteWordsDao_Impl implements FavouriteWordsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MyGrievancesItems> __insertionAdapterOfFavouriteWordItems;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFavouriteWordById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFavouriteWordByName;

  public FavouriteWordsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavouriteWordItems = new EntityInsertionAdapter<MyGrievancesItems>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MyGrievancesItems` (`uid`,`wordDefinition`,`wordName`,`timestamp`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MyGrievancesItems value) {
        stmt.bindLong(1, value.uid);
        if (value.wordDefinition == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.wordDefinition);
        }
        if (value.wordName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.wordName);
        }
        if (value.timestamp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.timestamp);
        }
      }
    };
    this.__preparedStmtOfDeleteFavouriteWordById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM MyGrievancesItems WHERE uid = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFavouriteWordByName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM MyGrievancesItems WHERE wordName = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(final List<MyGrievancesItems> mList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavouriteWordItems.insert(mList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(final MyGrievancesItems mItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavouriteWordItems.insert(mItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteFavouriteWordById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFavouriteWordById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteFavouriteWordById.release(_stmt);
    }
  }

  @Override
  public void DeleteFavouriteWordByName(final String wordName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFavouriteWordByName.acquire();
    int _argIndex = 1;
    if (wordName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, wordName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteFavouriteWordByName.release(_stmt);
    }
  }

  @Override
  public List<MyGrievancesItems> getAll() {
    final String _sql = "SELECT * FROM MyGrievancesItems ORDER BY uid DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfWordDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "wordDefinition");
      final int _cursorIndexOfWordName = CursorUtil.getColumnIndexOrThrow(_cursor, "wordName");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<MyGrievancesItems> _result = new ArrayList<MyGrievancesItems>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MyGrievancesItems _item;
        _item = new MyGrievancesItems();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.wordDefinition = _cursor.getString(_cursorIndexOfWordDefinition);
        _item.wordName = _cursor.getString(_cursorIndexOfWordName);
        _item.timestamp = _cursor.getString(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<MyGrievancesItems> checkWordForExistence(final String wordName) {
    final String _sql = "SELECT * FROM MyGrievancesItems WHERE wordName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (wordName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, wordName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfWordDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "wordDefinition");
      final int _cursorIndexOfWordName = CursorUtil.getColumnIndexOrThrow(_cursor, "wordName");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
      final List<MyGrievancesItems> _result = new ArrayList<MyGrievancesItems>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MyGrievancesItems _item;
        _item = new MyGrievancesItems();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.wordDefinition = _cursor.getString(_cursorIndexOfWordDefinition);
        _item.wordName = _cursor.getString(_cursorIndexOfWordName);
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
