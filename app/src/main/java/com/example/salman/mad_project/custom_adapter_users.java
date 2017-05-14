package com.example.salman.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SALMAN on 4/6/2017.
 */

public class custom_adapter_users extends ArrayAdapter<Users> {
    private Context _context;
    int _layoutId;

    public custom_adapter_users(Context context,
                                int layoutId,
                                List<Users> items) {
        super(context, layoutId, items);
        _context = context;
        _layoutId = layoutId;
    }

    public class viewholder {
        TextView id;
        TextView name;
        TextView email;
    }

    public View getView(int position, View convertview, ViewGroup parent) {

        Users item = getItem(position);
        viewholder holder;
        String id = item.getId();
        String name = item.getName();
        String email = item.getEmail();
        holder = new viewholder();
        View view = convertview;
        if (convertview == null) {
            LayoutInflater inflater = (LayoutInflater) _context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(_layoutId, parent, false);
            holder.id = (TextView) view.findViewById(R.id.Id);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.email = (TextView) view.findViewById(R.id.email);
            view.setTag(holder);
        } else {
            holder = (viewholder) view.getTag();
        }
        holder.id.setText(id);
        holder.name.setText(name);
        holder.email.setText(email);

        return view;
    }
}


