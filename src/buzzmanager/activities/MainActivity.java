package buzzmanager.activities;

import java.util.ArrayList;
import java.util.List;

import buzzmanager.util.Beverage;
import buzzmanager.util.database.BuzzDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpSpinner();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
	
	public void calculateAPC(View view)
	{
		EditText nameEditText = (EditText)findViewById(R.id.editTextBeverageName);
		String name = nameEditText.getText().toString();
		
		
		EditText volumeEditText = (EditText)findViewById(R.id.editTextBeverageVolume);
		float volume = parseFloatFromEditText(volumeEditText) / 100.0f;
		
		EditText strengthEditText = (EditText)findViewById(R.id.editTextBeverageStrength);
		float strength = parseFloatFromEditText(strengthEditText) / 100.0f;
		
		EditText priceEditText = (EditText)findViewById(R.id.editTextBeveragePrice);
		float price = parseFloatFromEditText(priceEditText);
		
		Beverage beverage = new Beverage(name, volume, strength, price);
		
		TextView result = (TextView)findViewById(R.id.textViewResult);
		result.append(beverage.getName() + " - APC: " + beverage.getApc()*1000.0f + "\n");
	
		
		
	}
	
	/**
	 * Parses a float from an EditText and returns the result
	 * @param editText - The EditText to parse from
	 * @return - The resulting float or 0.0f if empty
	 */
	private float parseFloatFromEditText(EditText editText)
	{
		float result;
		
		try
		{
			result = Float.parseFloat(editText.getText().toString());
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			result = 0.0f;
		}
		
		return result;
	}
	
	private void setUpSpinner()
	{
		BuzzDataSource ds = new BuzzDataSource(this);
		List<String> bars = new ArrayList<String>();
		bars = ds.getBars();
		Spinner barSpinner = (Spinner)findViewById(R.id.spinnerBar);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bars);
		barSpinner.setAdapter(adapter);
	}
	private void setUpListView()
	{
		BuzzDataSource ds = new BuzzDataSource(this);
		ArrayList<String> strings = new ArrayList<String>();
		strings = (ArrayList<String>) ds.getBars();
		ListView l=(ListView) findViewById(R.id.listViewBeverages);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);
		l.setAdapter(adapter);		
	}

}
