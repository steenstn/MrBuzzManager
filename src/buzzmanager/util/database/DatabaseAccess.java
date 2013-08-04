package buzzmanager.util.database;

import java.util.ArrayList;
import java.util.List;

import buzzmanager.util.Beverage;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {

	private SQLiteDatabase database;
	
	public static final String ASCENDING = "asc";
	public static final String DESCENDING = "desc";
	
	
	public DatabaseAccess(SQLiteDatabase theDatabase)
	{
		database = theDatabase;
	}
	
	public boolean addBeverage(Beverage beverage)
	{
		ContentValues values = new ContentValues();
		values.put(BuzzDatabase.COLUMN_NAME, beverage.getName());
		values.put(BuzzDatabase.COLUMN_PRICE, beverage.getPrice());
		values.put(BuzzDatabase.COLUMN_STRENGTH, beverage.getStrength());
		values.put(BuzzDatabase.COLUMN_VOLUME, beverage.getVolume());
		values.put(BuzzDatabase.COLUMN_BAR, beverage.getBar());
		
		long insertId = database.insert(BuzzDatabase.TABLE_BEVERAGES, null, values);
		
		if(insertId == -1)
			return false;
		else
			return true;
	}
	
	public List<String> getBars()
	{
		List<String> resultingList = new ArrayList<String>();
		
		Cursor cursor;
		cursor = database.query(BuzzDatabase.TABLE_BARS, new String[]{BuzzDatabase.COLUMN_NAME}, null, null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			resultingList.add(cursor.getString(0));
			cursor.moveToNext();
		}
		cursor.close();
		return resultingList;
	}
	
}
