package com.example.a15squares_running;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*TITLE:
  PURPOSE:
  DATE:
  AUTHOR:
*/
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartGame = this.<Button>findViewById(R.id.buttonStart);
        buttonStartGame.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, game.class);
        startActivity(intent);
    }
}