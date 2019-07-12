package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<Person> people;
    Adapter adapter;
    ListView listpeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listpeople=(ListView)findViewById(R.id.listview_people);
        getPeopleList();
    }

    private void getPeopleList() {
        people=new ArrayList<>();
        adapter=new Adapter(this,R.layout.listview_item_person,people);
        listpeople.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.mocky.io/v2/5a488f243000004c15c3c57e";
        final String requestBody = null;
        Request postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Log.d("Site", response);
                        JSONArray jsonArray=null;
                        try {
                            jsonArray=new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ObjectMapper objectMapper=new ObjectMapper();
                        for (int i=0;i<jsonArray.length();i++){
                            try {
                                adapter.add(objectMapper.readValue(jsonArray.getString(i),Person.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        try {
                            Log.d("Sites",error.getMessage());
                        } catch (Exception e) {
                            Log.d("Response", e.getMessage());
                        }
                    }
                }
        );
            queue.add(postRequest);
            Person a=new Person();
            a.setPhoto("https://pp.userapi.com/c841621/v841621138/7ad44/SohDfQqQf-0.jpg");
            a.setGender("male");
            a.setFirst_name("Beks");
            a.setLast_name("Baban");
            adapter.add(a);
    }
}
