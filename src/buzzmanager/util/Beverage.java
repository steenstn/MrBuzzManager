package buzzmanager.util;

/**
 * Class containing all information for a beverage
 * @author Steen
 *
 */
public class Beverage {

	private long id;
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
	// The bar which this beverage belongs to
	private String bar;
	
	/**
	 * Creates a new beverage
	 * @param name - Name of the beverage
	 * @param volume - The volume of the beverage
	 * @param strength - The alcohol strength of the beverage
	 * @param price - The price of the beverage
	 */
	public Beverage(String name, float volume, float strength, float price)
	{
		this.name = name;
		this.volume = volume;
		this.strength = strength;
		this.price = price;
		this.bar = "";
		calculateApc();
	}
	
	public Beverage(String name, float volume, float strength, float price, String bar)
	{
		this.name = name;
		this.volume = volume;
		this.strength = strength;
		this.price = price;
		this.bar = bar;
		calculateApc();
	}
	public Beverage(long id, String name, float volume, float strength, float price, String bar)
	{
		this.id = id;
		this.name = name;
		this.volume = volume;
		this.strength = strength;
		this.price = price;
		this.bar = bar;
		calculateApc();
	}
	
	private void calculateApc()
	{
		float amountAlcohol = volume * strength;
		apc = (amountAlcohol / price);
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
	public void setBar(String bar)
	{
		this.bar = bar;
	}
	public long getId()
	{
		return id;
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
	
	public String getBar()
	{
		return bar;
	}
}
