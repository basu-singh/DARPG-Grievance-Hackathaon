package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MyGrievanceAppDatabase_Impl extends MyGrievanceAppDatabase {
  private volatile MyGrievanceDao _myGrievanceDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MyGrievanceItems` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject` TEXT, `message` TEXT, `ministry` TEXT, `image1` TEXT, `image2` TEXT, `image3` TEXT, `timestamp` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2a9999a6b7477844bc6b84d0a65a1dca')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `MyGrievanceItems`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMyGrievanceItems = new HashMap<String, TableInfo.Column>(8);
        _columnsMyGrievanceItems.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("subject", new TableInfo.Column("subject", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("ministry", new TableInfo.Column("ministry", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("image1", new TableInfo.Column("image1", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("image2", new TableInfo.Column("image2", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("image3", new TableInfo.Column("image3", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMyGrievanceItems.put("timestamp", new TableInfo.Column("timestamp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMyGrievanceItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMyGrievanceItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMyGrievanceItems = new TableInfo("MyGrievanceItems", _columnsMyGrievanceItems, _foreignKeysMyGrievanceItems, _indicesMyGrievanceItems);
        final TableInfo _existingMyGrievanceItems = TableInfo.read(_db, "MyGrievanceItems");
        if (! _infoMyGrievanceItems.equals(_existingMyGrievanceItems)) {
          return new RoomOpenHelper.ValidationResult(false, "MyGrievanceItems(com.basusingh.mygrievance.DatabasePreference.MyGrievance.MyGrievanceItems).\n"
                  + " Expected:\n" + _infoMyGrievanceItems + "\n"
                  + " Found:\n" + _existingMyGrievanceItems);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "2a9999a6b7477844bc6b84d0a65a1dca", "ba2a1cbf880ef50b53b20fd7914367b9");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "MyGrievanceItems");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `MyGrievanceItems`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MyGrievanceDao MyGrievanceDao() {
    if (_myGrievanceDao != null) {
      return _myGrievanceDao;
    } else {
      synchronized(this) {
        if(_myGrievanceDao == null) {
          _myGrievanceDao = new MyGrievanceDao_Impl(this);
        }
        return _myGrievanceDao;
      }
    }
  }
}
