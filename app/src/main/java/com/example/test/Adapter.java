package com.example.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Person> {
    private static final String TAG = "FixationsAdapter";
    private Context context;
    int resource;
    ImageView image_photo;
    TextView  text_firstname;
    TextView text_surname;
    TextView text_gender;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);
        image_photo=convertView.findViewById(R.id.image_photo);
        text_firstname=convertView.findViewById(R.id.text_firstname);
        text_surname=convertView.findViewById(R.id.text_surname);
        text_gender=convertView.findViewById(R.id.text_gender);
        text_firstname.setText(getItem(position).getFirst_name());
        text_surname.setText(getItem(position).getLast_name());
        text_gender.setText(getItem(position).getGender());
        Picasso.get().load(getItem(position).getPhoto()).into(image_photo);

        Log.d("Adapter",getItem(position).getPhoto());
        return convertView;
    }

    public Adapter(Context context, int resource, ArrayList<Person> objects){
        super(context,resource);
        this.context=context;
        this.resource=resource;
    }
}
