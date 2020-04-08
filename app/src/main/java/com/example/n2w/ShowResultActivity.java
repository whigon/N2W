package com.example.n2w;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class ShowResultActivity extends AppCompatActivity {
    private static final String TAG = "ShowResultActivity";
    // Show result
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        // Get result
        Intent intent = getIntent();
        String result = intent.getStringExtra("Result");
        Log.d(TAG, "onCreate: " + result);

        // Show result
        textView = findViewById(R.id.ShowResult);
        textView.setText(result);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
