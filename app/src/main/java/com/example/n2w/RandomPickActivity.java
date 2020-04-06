package com.example.n2w;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RandomPickActivity extends AppCompatActivity {
    private static final String TAG = "RandomPickActivity";
    // Show result
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_pick);

        // Get result
        Intent intent = getIntent();
        String result = intent.getStringExtra("Result");
        Log.d(TAG, "onCreate: " + result);

        textView = findViewById(R.id.Random);
        textView.setText(result);
    }
}
