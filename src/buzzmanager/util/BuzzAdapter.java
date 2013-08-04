package buzzmanager.util;
/**
 * Adapter class for adding a transaction entry to a ListView
 */
import java.util.ArrayList;

import buzzmanager.activities.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BuzzAdapter extends BaseAdapter {
	
	private ArrayList<ViewHolder> data = new ArrayList<ViewHolder>();
	private LayoutInflater inflater;
	
	public BuzzAdapter(Context theContext ) {
		inflater = (LayoutInflater) theContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	public void add(ViewHolder item)
	{
		data.add(item);
	}
	
	public void remove(int pos)
	{
		data.remove(pos);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position)
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}
	
	/**
	 * Overridden function for getting a View from the ListView, uses recycling to 
	 * save resources
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		ViewHolder holder = null;
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.beverage_listitem,null);
			holder = new ViewHolder(data.get(position));
			holder.setNameTextView((TextView)convertView.findViewById(R.id.listNameTextView));
			holder.setApcTextView((TextView)convertView.findViewById(R.id.listApcTextView));
			holder.setVolumeTextView((TextView)convertView.findViewById(R.id.listVolumeTextView));
			
			holder.setStrengthTextView((TextView)convertView.findViewById(R.id.listStrengthTextView));
			holder.setPriceTextView((TextView)convertView.findViewById(R.id.listPriceTextView));
			
			convertView.setTag(holder);
		}
		else
		{
			ViewHolder tempBeverage = data.get(position);
			holder = (ViewHolder)convertView.getTag();
			holder.setBeverage(tempBeverage.getBeverage());
			
		}
		
		Beverage beverage = data.get(position).getBeverage();
		holder.getNameTextView().setText(beverage.getName());
		holder.getApcTextView().setText(String.format("%.2f", beverage.getApc()*1000.0f));
		holder.getVolumeTextView().setText(""+beverage.getVolume()*100.0f);
		
		holder.getStrengthTextView().setText(""+beverage.getStrength()*100.0f + "%");
		holder.getPriceTextView().setText(""+data.get(position).getBeverage().getPrice());
		
		
		
		return convertView;
		
	}

}
