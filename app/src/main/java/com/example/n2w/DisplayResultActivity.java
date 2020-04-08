package com.example.n2w;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayResultActivity extends AppCompatActivity {
    private static final String TAG = "ShowResultActivity";
    // Display result
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        // Get result
        Intent intent = getIntent();
        String result = intent.getStringExtra("Result");
        Log.d(TAG, "onCreate: " + result);

        // Display result
        textView = findViewById(R.id.ShowResult);
        textView.setText(result);
        // Set scroll
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
