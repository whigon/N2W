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
        wordSet = (ArrayList<ArrayList<String>>) intent.getSerializableExtra("WordSet");

        // Display data on the screen
        String[] items = numbers.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CustomPickActivity.this, android.R.layout.simple_list_item_1, items);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get words corresponding to the selected number
        String result = wordSet.get(position).toString();
        Log.d(TAG, "onItemClick: " + result);

        // Display result
        Intent intent = new Intent(CustomPickActivity.this, DisplayResultActivity.class);
        intent.putExtra("Result", result);
        startActivity(intent);
    }
}
