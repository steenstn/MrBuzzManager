package buzzmanager.util;

import android.widget.TextView;

/**
 * Class used in the ListView. Contains a TextView and a BudgetEntry that is printed in 
 * the TextView
 * @author Steen
 *
 */
public class ViewHolder {
	
    private TextView leftTextView;
    private TextView centerTextView;
    private TextView rightTextView;
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
    
    public TextView getLeftTextView()
    {
    	return leftTextView;
    }
    
    public TextView getCenterTextView()
    {
    	return centerTextView;
    }
    
    public TextView getRightTextView()
    {
    	return rightTextView;
    }
    public void setLeftTextView(TextView textView)
    {
    	this.leftTextView = textView;
    }
    public void setCenterTextView(TextView textView)
    {
    	this.centerTextView = textView;
    }
    public void setRightTextView(TextView textView)
    {
    	this.rightTextView = textView;
    }
    
    public void setBeverage(Beverage beverage)
    {
    	this.beverage = beverage;
    }
    
    public String toString(){
    	return beverage.getName() + ": " + beverage.getApc()*1000.0f;
    }
   
}
