package com.example.a2048game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    private long backPressedTime;
    public View phon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phon = findViewById(R.id.Phon);

        SharedPreferences sharedPref = this.getSharedPreferences("my_prefs", this.MODE_PRIVATE);
        int bg = sharedPref.getInt("background", android.R.color.white); // the second parameter will be fallback if the preference is not found
        phon.setBackgroundResource(bg);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonset = (Button) findViewById(R.id.ButtonSetings);
        buttonset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent(MainActivity.this, com.example.a2048game.Settings.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){}
            }
        });

        Button buttontet = (Button) findViewById(R.id.tetbutton);
        buttontet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent(MainActivity.this, two048.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){}
            }
        });
        Button buttonexit = (Button) findViewById(R.id.Buttonexit);
        buttonexit.setOnClickListener(this.ExitButton);
    }

    View.OnClickListener ExitButton = new View.OnClickListener(){
        @Override
        public void onClick(View view){  System.exit(0);
        }
    };

    @Override
    public void onBackPressed(){


        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(),"Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}