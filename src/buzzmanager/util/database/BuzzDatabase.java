package buzzmanager.util.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BuzzDatabase extends SQLiteOpenHelper{

		public static final String TABLE_BARS = "bars";
		public static final String TABLE_BEVERAGES = "beverages";
	
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_VOLUME = "volume";
		public static final String COLUMN_STRENGTH = "strength";
		public static final String COLUMN_PRICE = "price";
		public static final String COLUMN_APC = "apc";
		public static final String COLUMN_BAR = "bar";
		
		private static final String DATABASE_CREATE_TABLE_BEVERAGES = "create table if not exists "
				+ TABLE_BEVERAGES + "(" + COLUMN_ID
				+ " integer primary key autoincrement, " + COLUMN_NAME
				+ " text, " + COLUMN_VOLUME + " double not null, " + COLUMN_STRENGTH + " double not null, "
				+ COLUMN_PRICE + " double not null, " + COLUMN_APC + " double, " + COLUMN_BAR + " text);";
		
		private static final String DATABASE_NAME = "buzz.db";
		private static final int DATABASE_VERSION = 1;
		
		public static final String DATABASE_CREATE_TABLE_BARS = "create table if not exists "
				+ TABLE_BARS + "(" + COLUMN_ID
				+ " integer primary key autoincrement, " + COLUMN_NAME
				+ " text);";
		public BuzzDatabase(Context context)
		{
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase database)
		{
			database.execSQL(DATABASE_CREATE_TABLE_BARS);
			database.execSQL(DATABASE_CREATE_TABLE_BEVERAGES);
			
			ContentValues values = new ContentValues();
			values.put(BuzzDatabase.COLUMN_NAME, "Trappan");
			database.insert(BuzzDatabase.TABLE_BARS, null,values);
			values.put(BuzzDatabase.COLUMN_NAME, "Hugos");
			database.insert(BuzzDatabase.TABLE_BARS, null,values);
			values.put(BuzzDatabase.COLUMN_NAME, "Världens bar");
			database.insert(BuzzDatabase.TABLE_BARS, null,values);

			values.put(BuzzDatabase.COLUMN_NAME, "Carlsberg");
			values.put(BuzzDatabase.COLUMN_PRICE, 15.4);
			values.put(BuzzDatabase.COLUMN_VOLUME, 0.5);
			values.put(BuzzDatabase.COLUMN_STRENGTH, 0.05);
			values.put(BuzzDatabase.COLUMN_BAR, "Trappan");
			values.put(BuzzDatabase.COLUMN_APC, 0.0002);

			database.insert(BuzzDatabase.TABLE_BEVERAGES, null,values);
			

			values.put(BuzzDatabase.COLUMN_NAME, "Dansk");
			values.put(BuzzDatabase.COLUMN_PRICE, 10.4);
			values.put(BuzzDatabase.COLUMN_VOLUME, 0.33);
			values.put(BuzzDatabase.COLUMN_STRENGTH, 0.06);
			values.put(BuzzDatabase.COLUMN_BAR, "Systemet");
			values.put(BuzzDatabase.COLUMN_APC, 0.0001);
			database.insert(BuzzDatabase.TABLE_BEVERAGES, null,values);

			
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("drop tables");
			onCreate(db);
		}
}
