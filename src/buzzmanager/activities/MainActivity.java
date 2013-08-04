package buzzmanager.activities;

import java.util.ArrayList;
import java.util.List;

import buzzmanager.fragments.AddBarDialogFragment;
import buzzmanager.fragments.RemoveBarDialogFragment;
import buzzmanager.util.Beverage;
import buzzmanager.util.BuzzAdapter;
import buzzmanager.util.ViewHolder;
import buzzmanager.util.database.BuzzDataSource;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private Spinner barSpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpSpinner();
		setUpListView(getString(R.string.spinnerTextAllBars));
		
		
		TextView result = (TextView)findViewById(R.id.textViewResult);
		result.setText(getString(R.string.apc) + ": 0,00");
	
		setUpListeners();
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
	
	public void addBeverageToBar(View view)
	{
		EditText nameEditText = (EditText)findViewById(R.id.editTextBeverageName);
		String name = nameEditText.getText().toString();
		if(name.equals(""))
			return;
		
		EditText volumeEditText = (EditText)findViewById(R.id.editTextBeverageVolume);
		float volume = parseFloatFromEditText(volumeEditText) / 100.0f;
		
		EditText strengthEditText = (EditText)findViewById(R.id.editTextBeverageStrength);
		float strength = parseFloatFromEditText(strengthEditText) / 100.0f;
		
		EditText priceEditText = (EditText)findViewById(R.id.editTextBeveragePrice);
		float price = parseFloatFromEditText(priceEditText);
		
		Beverage beverage = new Beverage(name, volume, strength, price);
		
		if(Float.isNaN(beverage.getApc()) || Float.isInfinite(beverage.getApc()))
			return;

		String bar = (String)barSpinner.getSelectedItem();
		if(!bar.equals(getString(R.string.spinnerTextAllBars)))
		{
			beverage.setBar(bar);
		}
		
		BuzzDataSource ds = new BuzzDataSource(this);
		ds.addBeverage(beverage);
		nameEditText.setText("");
		volumeEditText.setText("");
		strengthEditText.setText("");
		priceEditText.setText("");
		
	}
	
	public boolean addBar(String barName)
	{
		BuzzDataSource ds = new BuzzDataSource(this);
		return ds.addBar(barName);
	}
	
	public boolean removeBar(String barName)
	{
		BuzzDataSource ds = new BuzzDataSource(this);
		return ds.removeBar(barName);
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
		
		float theApc = beverage.getApc()*1000.0f;
		
		if(!(Float.isInfinite(theApc) || Float.isNaN(theApc)))
			result.setText(getString(R.string.apc) + ": " + String.format("%.2f", theApc));
	
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
	

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
    	DialogFragment newFragment;
        switch (item.getItemId()) {
	        case R.id.menuAddBar:
	        	newFragment = new AddBarDialogFragment();
            	newFragment.show(getSupportFragmentManager(), "addBar");
	        	return true;
	        case R.id.menuRemoveBar:
	        	newFragment = new RemoveBarDialogFragment();
            	newFragment.show(getSupportFragmentManager(), "addBar");
	        	return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
	public void setUpSpinner()
	{
		BuzzDataSource ds = new BuzzDataSource(this);
		List<String> bars = new ArrayList<String>();
		bars = ds.getBars();
		bars.add(0, getString(R.string.spinnerTextAllBars)); // Insert "All bars"-string to the spinner
		Spinner barSpinner = (Spinner)findViewById(R.id.spinnerBar);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, bars);
		barSpinner.setAdapter(adapter);
	}
	
	public void setUpListView(String targetBar)
	{
		BuzzDataSource ds = new BuzzDataSource(this);
		List<Beverage> beverages = new ArrayList<Beverage>();
		beverages = ds.getBeverages(targetBar);
		ListView l=(ListView) findViewById(R.id.listViewBeverages);
		BuzzAdapter adapter=new BuzzAdapter(getApplicationContext());
		for(int i = 0; i < beverages.size(); i++)
		{
			adapter.add(new ViewHolder(beverages.get(i)));
		}
		l.setAdapter(adapter);		
	}

	private void setUpListeners()
	{
		// A watcher that is added to all edit texts
		TextWatcher editTextWatcher = new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				calculateAPC(null);
			}
			
		};

		EditText nameEditText = (EditText)findViewById(R.id.editTextBeverageName);
		nameEditText.addTextChangedListener(editTextWatcher);
		
		EditText volumeEditText = (EditText)findViewById(R.id.editTextBeverageVolume);
		volumeEditText.addTextChangedListener(editTextWatcher);
		
		EditText strengthEditText = (EditText)findViewById(R.id.editTextBeverageStrength);
		strengthEditText.addTextChangedListener(editTextWatcher);
		
		EditText priceEditText = (EditText)findViewById(R.id.editTextBeveragePrice);
		priceEditText.addTextChangedListener(editTextWatcher);
		
		barSpinner = (Spinner)findViewById(R.id.spinnerBar);
		barSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String targetBar = (String) barSpinner.getItemAtPosition(position);
				setUpListView(targetBar);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		
	}
}
