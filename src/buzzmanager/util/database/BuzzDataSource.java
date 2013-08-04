package buzzmanager.util.database;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BuzzDataSource {

	
	private static SQLiteDatabase database;
	private static BuzzDatabase dbHelper;
	private static DatabaseAccess dbAccess;
	public static final String ASCENDING = "asc";
	public static final String DESCENDING = "desc";
	
	
	public BuzzDataSource(Context context)
	{
		dbHelper = new BuzzDatabase(context);
		open();
		close();
	}
	
	public List<String> getBars()
	{
		List<String> result;
		open();
		result = dbAccess.getBars();
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
