package com.example.n2w;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomPickActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "CustomPickActivity";
    // Numbers split from digits
    private ArrayList<String> numbers;
    // Words corresponding to the split numbers
    private ArrayList<ArrayList<String>> wordSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pick);

        Intent intent = getIntent();
        numbers = intent.getStringArrayListExtra("Numbers");
        wordSet = (ArrayList<ArrayList<String>>) intent.getSerializableExtra("Words");

        // Display data on the screen
        String[] showData = numbers.toArray(new String[numbers.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CustomPickActivity.this, android.R.layout.simple_list_item_1, showData);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get words corresponding to the selected number
        ArrayList<String> words = wordSet.get(position);
        String result = words.toString();
        Log.d(TAG, "onItemClick: " + result);

        Intent intent = new Intent(CustomPickActivity.this, ShowResultActivity.class);
        intent.putExtra("Result", result);
        startActivity(intent);
    }
}
