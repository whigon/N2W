package com.example.n2w;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    // Translator instance
    private Translator translator;
    // Input text field
    private EditText editText;
    // Button for random pick
    private Button randomPick;
    // Button for custom pick
    private Button customPick;
    // Progress bar
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the translator
        translator = new Translator(MainActivity.this);

        editText = findViewById(R.id.input);
        randomPick = findViewById(R.id.button_1);
        customPick = findViewById(R.id.button_2);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        randomPick.setOnClickListener(this);
        customPick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String inputText = editText.getText().toString();

        Log.d(TAG, "onClick: " + inputText);
        // digits, space and dot
        if (inputText.matches("[\\d\\s.]+")) {
            switch (v.getId()) {
                case R.id.button_1:
                    progressBar.setVisibility(View.VISIBLE);
                    String result = translator.translate(inputText);

                    // Create a random pick activity and transfer data
                    intent = new Intent(MainActivity.this, RandomPickActivity.class);
                    intent.putExtra("Result", result);

                    progressBar.setVisibility(View.GONE);
                    startActivity(intent);

                    break;
                case R.id.button_2:
                    progressBar.setVisibility(View.VISIBLE);

                    intent = new Intent(MainActivity.this, CustomPickActivity.class);

                    progressBar.setVisibility(View.GONE);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "Please input digits", Toast.LENGTH_SHORT).show();
        }
    }
}
