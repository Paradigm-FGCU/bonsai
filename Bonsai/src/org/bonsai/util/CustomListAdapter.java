package org.bonsai.util;

import java.util.List;

import org.srge.bonsai.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class CustomListAdapter extends ArrayAdapter {

    private Context mContext;
    private int id;
    private List <String>items ;

    @SuppressWarnings("unchecked")
	public CustomListAdapter(Context context, int textViewResourceId , List<String> list ) 
    {
        super(context, textViewResourceId, list);           
        mContext = context;
        id = textViewResourceId;
        items = list ;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }

        TextView text = (TextView) mView.findViewById(R.id.textView);


        if(text!=null && items.get(position) != null )
        {
            text.setTextColor(Color.WHITE);
            text.setText(items.get(position));

        }

        return mView;
    }

}