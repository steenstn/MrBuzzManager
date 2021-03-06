package buzzmanager.util.database;

import java.util.List;

import buzzmanager.activities.R;
import buzzmanager.util.Beverage;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BuzzDataSource {

	
	private static SQLiteDatabase database;
	private static BuzzDatabase dbHelper;
	private static DatabaseAccess dbAccess;
	public static final String ASCENDING = "asc";
	public static final String DESCENDING = "desc";
	private Context context;
	
	public BuzzDataSource(Context context)
	{
		this.context = context;
		dbHelper = new BuzzDatabase(context);
		open();
		close();
	}
	
	public boolean addBeverage(Beverage beverage)
	{
		open();
		boolean result = dbAccess.addBeverage(beverage);
		close();
		return result;
	}
	
	public boolean removeBeverage(long id)
	{
		open();
		boolean result = dbAccess.removeBeverage(id);
		close();
		return result;
	}
	
	public boolean addBar(String barName)
	{
		open();
		boolean result = dbAccess.addBar(barName);
		close();
		return result;
	}
	
	public boolean removeBar(String barName)
	{
		open();
		boolean result = dbAccess.removeBar(barName);
		close();
		return result;
	}
	
	public List<String> getBars()
	{
		List<String> result;
		open();
		result = dbAccess.getBars();
		close();
		return result;
	}
	
	/**
	 * Returns beverages from the database that matches a bar
	 * @param bar - The bar to check for
	 * @return - THe beverages for a specific bar
	 */
	public List<Beverage> getBeverages(String bar)
	{
		if(bar.equals(context.getString(R.string.spinnerTextAllBars)))
		{
			bar = null;
		}
		List<Beverage> result;
		open();
		result = dbAccess.getBeverages(bar);
		close();
		return result;
	}
	
	private void open() throws SQLException
	{
		database = dbHelper.getWritableDatabase();
		dbAccess = new DatabaseAccess(database);
	}
	
	private void close()
	{
		dbHelper.close();
	}
}
