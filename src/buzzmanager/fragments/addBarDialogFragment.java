package buzzmanager.fragments;
/**
 * Dialog Fragment for adding a new category
 * 
 */
import buzzmanager.activities.MainActivity;
import buzzmanager.activities.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;

public class AddBarDialogFragment extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
    
		final View view = inflater.inflate(R.layout.dialog_add_bar, null);
		final MainActivity activity = (MainActivity)getActivity();
    
		builder.setView(view);
 
		builder.setPositiveButton(activity.getString(R.string.add), new DialogInterface.OnClickListener() 
		{   
			@Override
			public void onClick(DialogInterface dialog, int id) 
			{
				EditText bar = (EditText)view.findViewById(R.id.dialogBarName);
				if(activity.addBar(bar.getText().toString())==true)
				{
				    Toast.makeText(view.getContext(), activity.getString(R.string.successfullyAdded)+ " " + bar.getText().toString() , Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(view.getContext(), activity.getString(R.string.failedToAdd)+ " " + bar.getText().toString() , Toast.LENGTH_LONG).show();
				activity.setUpListView(getString(R.string.spinnerTextAllBars));
				activity.setUpSpinner();
			}
		})
        .setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() 
        {
		    public void onClick(DialogInterface dialog, int id) 
		    {
		        AddBarDialogFragment.this.getDialog().cancel();
		    }
        });   

    return builder.create();
	}
}