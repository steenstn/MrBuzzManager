package buzzmanager.util.database;

import java.util.ArrayList;
import java.util.List;

import buzzmanager.activities.R;
import buzzmanager.util.Beverage;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAccess {

	private SQLiteDatabase database;
	
	public static final String ASCENDING = "asc";
	public static final String DESCENDING = "desc";
	
	private String[] allBeverageColumns = { BuzzDatabase.COLUMN_ID, BuzzDatabase.COLUMN_NAME, 
											BuzzDatabase.COLUMN_VOLUME, BuzzDatabase.COLUMN_STRENGTH, 
											BuzzDatabase.COLUMN_PRICE, BuzzDatabase.COLUMN_APC,
											BuzzDatabase.COLUMN_BAR};
	
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
		values.put(BuzzDatabase.COLUMN_APC, beverage.getApc());
		
		long insertId = database.insert(BuzzDatabase.TABLE_BEVERAGES, null, values);
		
		if(insertId == -1)
			return false;
		else
			return true;
	}
	
	public boolean addBar(String barName)
	{
		ContentValues values = new ContentValues();
		values.put(BuzzDatabase.COLUMN_NAME, barName);
		long insertId = database.insert(BuzzDatabase.TABLE_BARS, null, values);
		if(insertId == -1)
			return false;
		else
			return true;
	}
	
	/**
	 * Gets beverages from the database
	 * @param bar - Returns beverages that matches a bar, or all if value = null
	 * @return - A list of beverages, ordered by APC
	 */
	public List<Beverage> getBeverages(String targetBar)
	{
		List<Beverage> beverages = new ArrayList<Beverage>();
		
		Cursor cursor;
		if(targetBar !=null)
		{
			cursor = database.query(BuzzDatabase.TABLE_BEVERAGES, allBeverageColumns, BuzzDatabase.COLUMN_BAR + " = '" + targetBar + "'", null, null, null, BuzzDatabase.COLUMN_APC + " desc");
		}
		else
		{
			cursor = database.query(BuzzDatabase.TABLE_BEVERAGES, allBeverageColumns, null, null, null, null, BuzzDatabase.COLUMN_APC + " desc");
		}
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			Beverage beverage = new Beverage(cursor.getString(1), cursor.getFloat(2), 
											 cursor.getFloat(3), cursor.getFloat(4),
											 cursor.getString(6));
			
			beverages.add(beverage);
			cursor.moveToNext();
		}
		return beverages;
	}
	/**
	 * Gets all the bars in the database
	 * @return - All bars
	 */
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
