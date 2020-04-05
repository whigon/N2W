package com.example.n2w;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Translator translator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the translator
        translator = new Translator(MainActivity.this);

        Button randomPick = (Button) findViewById(R.id.button_1);
        Button customPick = (Button) findViewById(R.id.button_2);

        randomPick.setOnClickListener(this);
        customPick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        EditText editText = findViewById(R.id.input);
        String inputText = editText.getText().toString();

        Log.d(TAG, "onClick: " + inputText);
        // 数字空格小数点
        if(inputText.matches("[\\d\\s\\.]+")){
            switch (v.getId()) {
                case R.id.button_1:
                    intent = new Intent(MainActivity.this, RandomPickActivity.class);
                    startActivity(intent);
                    break;
                case R.id.button_2:
                    intent = new Intent(MainActivity.this, CustomPickActivity.class);
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
/**
 * ProgressBar 或者 progressDialog
 * 对话风格的<activity	android:name=".DialogActivity"				android:theme="@style/Theme.AppCompat.Dialog">
 * 两次退出：退出提示
 * ListView + 点击事件
 * <p>
 * 先把设计思路写报告，再写代码
 **/