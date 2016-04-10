package com.example.shivam.grannycures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rishabh on 4/10/16.
 */
public class AilmentAdapter extends ArrayAdapter<String> {

    private Context myContext ;
    private List<String> myAilmentNames;
    public AilmentAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        myContext = context ;
        myAilmentNames = objects ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if(convertView == null){
            view = LayoutInflater.from(myContext).inflate(R.layout.list_item, null);
        }else {
            view = convertView ;
        }


        TextView textView = (TextView) view.findViewById(R.id.list_item_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_image);

        textView.setText(myAilmentNames.get(position));

        imageView.setImageResource(Utility.iconId[position]);

        return view;
    }
}
