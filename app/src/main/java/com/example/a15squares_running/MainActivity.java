package com.example.a15squares_running;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*TITLE: main
  PURPOSE: this sets the start button to go to game page when clicked
  DATE: 24 February 2023
  AUTHOR: Zella Running
*/
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makes the start button clickable
        Button buttonStartGame = this.findViewById(R.id.buttonStart);
        buttonStartGame.setOnClickListener(this::onClick);

    }

    //this method makes it so when the start button is clicked then it will take user to game class
    private void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, game.class);
        startActivity(intent);
    }
}