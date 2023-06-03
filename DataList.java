package com.example.loginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataList extends AppCompatActivity {
    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand", "India",
            "China", "australia", "Portugle", "America", "NewZealand","India", "China", "australia",
            "Portugle", "America", "NewZealand","India", "China", "australia", "Portugle", "America",
            "NewZealand","India", "China", "australia", "Portugle", "America", "NewZealand","India",
            "China", "australia", "Portugle", "America", "NewZealand","India", "China", "australia",
            "Portugle", "America", "NewZealand","India", "China", "australia", "Portugle", "America"};
    String NameUrl = "https://demo.hashup.tech/std/items?std_id=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        Intent intSend = getIntent();
        String usrName = intSend.getStringExtra("UsrName");
        NameUrl = NameUrl+usrName;

        TextView showUsrname = findViewById(R.id.tvUsrname);
        TextView showText = findViewById(R.id.tvDetail);
        Button btnNew = findViewById(R.id.btn_new);
        Button btnExit = findViewById(R.id.btn_exit);

        showUsrname.setText(usrName);
//        Log.d("DataList", NameUrl);
        try {
            run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MainActivity", "position" + position);
                showText.setText(countryList[position]);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataList.this, Insert.class);
                startActivity(intent);
//                setContentView(R.layout.activity_insert);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(NameUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                try {
////                    JSONObject jsonObject = new JSONObject(response.body().string());
////                    JSONArray data = jsonObject.getJSONArray("data");
////                    Log.d("DataList", data.getJSONObject(0).getString("field_a").toString());
//                }catch (JSONException e) {
//                    Log.d("Error", e.toString());
////                    throw new RuntimeException(e);
//                }
                final String myResponse = response.body().string();

                DataList.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Log.d("DataList", NameUrl);
                        Log.d("leena", myResponse);
                    }
                });
            }
        });
    }
}