package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FavouriteWordAppDatabase_Impl extends FavouriteWordAppDatabase {
  private volatile FavouriteWordsDao _favouriteWordsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FavouriteWordItems` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `wordDefinition` TEXT, `wordName` TEXT, `timestamp` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9471a2bfc766ed31e4e40a5b46589e1f')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FavouriteWordItems`");
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
        final HashMap<String, TableInfo.Column> _columnsFavouriteWordItems = new HashMap<String, TableInfo.Column>(4);
        _columnsFavouriteWordItems.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteWordItems.put("wordDefinition", new TableInfo.Column("wordDefinition", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteWordItems.put("wordName", new TableInfo.Column("wordName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFavouriteWordItems.put("timestamp", new TableInfo.Column("timestamp", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavouriteWordItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavouriteWordItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavouriteWordItems = new TableInfo("MyGrievancesItems", _columnsFavouriteWordItems, _foreignKeysFavouriteWordItems, _indicesFavouriteWordItems);
        final TableInfo _existingFavouriteWordItems = TableInfo.read(_db, "MyGrievancesItems");
        if (! _infoFavouriteWordItems.equals(_existingFavouriteWordItems)) {
          return new RoomOpenHelper.ValidationResult(false, "MyGrievancesItems(com.basusingh.mygrievance.DatabasePreference.FavouriteWords.MyGrievancesItems).\n"
                  + " Expected:\n" + _infoFavouriteWordItems + "\n"
                  + " Found:\n" + _existingFavouriteWordItems);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9471a2bfc766ed31e4e40a5b46589e1f", "f1deb0e8c0ccef1a7c098fa0b1988cc9");
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
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "MyGrievancesItems");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FavouriteWordItems`");
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
  public FavouriteWordsDao recentWordsDao() {
    if (_favouriteWordsDao != null) {
      return _favouriteWordsDao;
    } else {
      synchronized(this) {
        if(_favouriteWordsDao == null) {
          _favouriteWordsDao = new FavouriteWordsDao_Impl(this);
        }
        return _favouriteWordsDao;
      }
    }
  }
}
