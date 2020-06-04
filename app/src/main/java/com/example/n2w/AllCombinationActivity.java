package com.example.n2w;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllCombinationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "AllCombinationActivity";
    // All combinations
    private ArrayList<String> combinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_combination);

        Intent intent = getIntent();
        combinations = intent.getStringArrayListExtra("Combinations");

        // Display data on the screen
        String[] items = combinations.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AllCombinationActivity.this, android.R.layout.simple_list_item_1, items);
        ListView listView = findViewById(R.id.list_view_all_combination);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Get the selected sentence
        String result = combinations.get(position);
        Log.d(TAG, "onItemClick: " + result);

        // Display result
        Intent intent = new Intent(AllCombinationActivity.this, DisplayResultActivity.class);
        intent.putExtra("Result", result);
        startActivity(intent);
    }
}
