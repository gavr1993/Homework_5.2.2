package com.example.homework_521;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String EXTERNAL_STORAGE_FILE = "loginPassExternal.txt";
    private EditText loginEdtx;
    private EditText passEdtx;
    private Button loginBtn;
    private Button regBtn;
    private String loginInFile;
    private String passInFile;
    private CheckBox checkBox;
    private SharedPreferences sharPref;
    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharPref = getSharedPreferences("my_prefs", MODE_PRIVATE);
        init();
        checkBoxListen();
        checkExternalStorage();
    }

    private void checkExternalStorage() {
        if (isExternalStorageWritable()) {
            boolean checked = checkBox.isChecked();
            if (!checked) {
                regListen();
                loginListen();
            } else {
                regListenExternal();
                loginListenExternal();
            }
        }
    }

    private void loginListenExternal() {
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
                    File file = new File(getExternalFilesDir(null), EXTERNAL_STORAGE_FILE);
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        loginInFile = reader.readLine();
                        passInFile = reader.readLine();
                        Toast toast = Toast.makeText(MainActivity.this, R.string.loginOkExternal,
                                Toast.LENGTH_LONG);
                        toast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void regListenExternal() {
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
                    File file = new File(getExternalFilesDir(null), EXTERNAL_STORAGE_FILE);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write((loginEdtx.getText().toString()) + "\n");
                        writer.write(passEdtx.getText().toString());
                        Toast toast = Toast.makeText(MainActivity.this, R.string.regOkExternal,
                                Toast.LENGTH_LONG);
                        toast.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void checkBoxListen() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean boxCheck = checkBox.isChecked();
                SharedPreferences.Editor editor = sharPref.edit();
                editor.putBoolean(NOTE_TEXT, boxCheck);
                editor.apply();
            }
        });
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
                    try (FileInputStream fileInputStream = openFileInput(getString(
                            R.string.loginPassFileName));
                         InputStreamReader inputStreamReader = new InputStreamReader(
                                 fileInputStream);
                         BufferedReader reader = new BufferedReader(inputStreamReader)) {
                        loginInFile = reader.readLine();
                        passInFile = reader.readLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                    try (FileOutputStream fileOutputStream = openFileOutput(getString(
                            R.string.loginPassFileName), MODE_PRIVATE);
                         OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                                 fileOutputStream);
                         BufferedWriter bw = new BufferedWriter(outputStreamWriter)) {

                        bw.write(loginEdtx.getText().toString());
                        bw.append("\n");
                        char[] pass = passEdtx.getText().toString().toCharArray();
                        for (char ch : pass) {
                            bw.append(ch);
                        }
                        Toast toast = Toast.makeText(MainActivity.this, R.string.regOk,
                                Toast.LENGTH_SHORT);
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
        checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(sharPref.getBoolean(NOTE_TEXT, false));
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}