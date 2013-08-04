package buzzmanager.util;

import android.widget.TextView;

/**
 * Class used in the ListView. Contains a TextView and a BudgetEntry that is printed in 
 * the TextView
 * @author Steen
 *
 */
public class ViewHolder {
	
    private TextView nameTextView;
    private TextView apcTextView;
    private TextView volumeTextView;
    private TextView strengthTextView;
    private TextView priceTextView;
    
    private Beverage beverage;
    
	public ViewHolder(Beverage beverage)
	{
		this.beverage = beverage;
	
	}
	public ViewHolder(ViewHolder viewHolder)
	{
		this.beverage = viewHolder.getBeverage();
	}
	
    public Beverage getBeverage()
    {
    	return beverage;
    }
    
    public TextView getNameTextView()
    {
    	return nameTextView;
    }
    
    public TextView getApcTextView()
    {
    	return apcTextView;
    }
    
    public TextView getVolumeTextView()
    {
    	return volumeTextView;
    }
    
    public TextView getStrengthTextView()
    {
    	return strengthTextView;
    }
    public TextView getPriceTextView()
    {
    	return priceTextView;
    }
    public void setNameTextView(TextView textView)
    {
    	this.nameTextView = textView;
    }
    public void setApcTextView(TextView textView)
    {
    	this.apcTextView = textView;
    }
    public void setVolumeTextView(TextView textView)
    {
    	this.volumeTextView = textView;
    }
    public void setStrengthTextView(TextView textView)
    {
    	this.strengthTextView = textView;
    }
    public void setPriceTextView(TextView textView)
    {
    	this.priceTextView = textView;
    }
    
    public void setBeverage(Beverage beverage)
    {
    	this.beverage = beverage;
    }
    
    public String toString(){
    	return beverage.getName() + ": " + beverage.getApc()*1000.0f;
    }
   
}
