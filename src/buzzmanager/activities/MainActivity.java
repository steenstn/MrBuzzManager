package buzzmanager.activities;

import buzzmanager.util.Beverage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Beverage bev = new Beverage("test", 10.3f,5.3f,90);
		System.out.println("APC: " + bev.getApc());
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
		float volume = parseFloatFromEditText(volumeEditText);
		
		EditText strengthEditText = (EditText)findViewById(R.id.editTextBeverageStrength);
		float strength = parseFloatFromEditText(strengthEditText);
		
		EditText priceEditText = (EditText)findViewById(R.id.editTextBeveragePrice);
		float price = parseFloatFromEditText(priceEditText);
		
		Beverage beverage = new Beverage(name, volume, strength, price);
		
		TextView result = (TextView)findViewById(R.id.textViewResult);
		result.append(beverage.getName() + " - APC: " + beverage.getApc() + "\n");
		
		
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

}
