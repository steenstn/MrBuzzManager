package buzzmanager.util;

/**
 * Class containing all information for a beverage
 * @author Steen
 *
 */
public class Beverage {

	// The name of the beverage
	private String name;
	// The volume of the beverage (in litres)
	private float volume;
	// The alcohol strength of a beverage (in percent)
	private float strength;
	// The price for a beverage
	private float price;
	// Alcohol Per Currency, the amount of alcohol you get per currency (in Per mil)
	private float apc;
	
	/**
	 * Creates a new beverage
	 * @param name - Name of the beverage
	 * @param volume - The volume of the beverage (in centilitres)
	 * @param strength - The alcohol strength of the beverage (in percent)
	 * @param price - The price of the beverage
	 */
	public Beverage(String name, float volume, float strength, float price)
	{
		this.name = name;
		this.volume = volume;
		this.strength = strength;
		this.price = price;
		
		calculateApc();
	}
	
	private void calculateApc()
	{
		// Get the amount of alcohol in the beverage
		float amountAlcohol = volume * (strength/100.0f);
		
		// Divide the amount of alcohol with the price to get alcohol per currency
		// Multiply with 1000 to get per mil
		apc = (amountAlcohol / price) * 1000.0f;
		
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setVolume(float volume)
	{
		this.volume = volume;
	}
	
	public void setStrength(float strength)
	{
		this.strength = strength;
	}
	
	public void setPrice(float price)
	{
		this.price = price;
	}
	public String getName()
	{
		return this.name;
	}
	
	public float getVolume()
	{
		return this.volume;
	}
	
	public float getStrength()
	{
		return this.strength;
	}
	
	public float getPrice()
	{
		return this.price;
	}
	
	public float getApc()
	{
		return apc;
	}
}
