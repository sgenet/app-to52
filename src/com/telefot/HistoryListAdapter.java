package com.telefot;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HistoryListAdapter extends ArrayAdapter<TelefotAlert> {

    private ArrayList<TelefotAlert> items;

    public HistoryListAdapter(Context context, int textViewResourceId, ArrayList<TelefotAlert> items) {
            super(context, textViewResourceId, items);
            this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.history_row, null);
		}
		TelefotAlert message = items.get(position);
		
		if (message != null) {
			TextView tt = (TextView) v.findViewById(R.id.toptext);
			TextView mt1 = (TextView) v.findViewById(R.id.middletext1);
			TextView mt2 = (TextView) v.findViewById(R.id.middletext2);
			TextView bt = (TextView) v.findViewById(R.id.bottomtext);
			if (tt != null)
				tt.setText(message.getReference());
			if (mt1 != null)
				mt1.setText(message.getFormattedSentDate()); 
			if (mt2 != null)
				mt2.setText(message.getAddress()); 
			if(bt != null)
				bt.setText(message.getProcessingTime() + " ms");
		}
		return v;
	}
}