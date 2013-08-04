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
			holder.setLeftTextView((TextView)convertView.findViewById(R.id.listLeftTextView));
			holder.setCenterTextView((TextView)convertView.findViewById(R.id.listCenterTextView));
			holder.setRightTextView((TextView)convertView.findViewById(R.id.listRightTextView));
			
			convertView.setTag(holder);
		}
		else
		{
			ViewHolder tempBeverage = data.get(position);
			holder = (ViewHolder)convertView.getTag();
			holder.setBeverage(tempBeverage.getBeverage());
			
		}
		
			holder.getLeftTextView().setText("heman");
			holder.getCenterTextView().setText("heeyea");
			holder.getRightTextView().setText("yeayea");
		
		
		
		return convertView;
		
	}

}
