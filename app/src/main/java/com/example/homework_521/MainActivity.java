package com.example.homework_521;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText loginEdtx;
    private EditText passEdtx;
    private Button loginBtn;
    private Button regBtn;
    private String loginInFile;
    private String passInFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        regListen();
        loginListen();
    }

    private void loginListen() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginEdtx.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.enterPls,
                            Toast.LENGTH_SHORT);
                    toast.show();

                } else if (passEdtx.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.enterPls,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    try {
                        FileInputStream fileInputStream = openFileInput(getString(
                                R.string.loginFileName));
                        InputStreamReader inputStreamReader = new InputStreamReader(
                                fileInputStream);
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        loginInFile = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        FileInputStream fileInputStream = openFileInput(getString(
                                R.string.passFileName));
                        InputStreamReader inputStreamReader = new InputStreamReader(
                                fileInputStream);
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        passInFile = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (loginEdtx.getText().toString().equals(loginInFile) && passEdtx.getText().
                            toString().equals(passInFile)) {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.loginOk,
                                Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(MainActivity.this, R.string.loginErr,
                                Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }

    private void regListen() {
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginEdtx.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.enterPls,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else if (passEdtx.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.enterPls,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else if (loginEdtx.getText().toString().equals(loginInFile) || passEdtx.
                        getText().toString().equals(passInFile)) {
                    Toast toast = Toast.makeText(MainActivity.this, R.string.regFail,
                            Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(getString(R.string.
                                loginFileName), MODE_PRIVATE);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                                fileOutputStream);
                        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                        bw.write(loginEdtx.getText().toString());
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        FileOutputStream fileOutputStream = openFileOutput(getString(R.string.
                                passFileName), MODE_PRIVATE);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                                fileOutputStream);
                        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                        bw.write(passEdtx.getText().toString());
                        bw.close();
                        Toast toast = Toast.makeText(MainActivity.this, R.string.regOk,
                                Toast.LENGTH_LONG);
                        toast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void init() {
        loginEdtx = findViewById(R.id.loginEdtx);
        passEdtx = findViewById(R.id.passEdtx);
        loginBtn = findViewById(R.id.loginBtn);
        regBtn = findViewById(R.id.regBtn);
    }
}