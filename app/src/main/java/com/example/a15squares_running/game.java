package com.example.a15squares_running;
import static android.widget.Toast.LENGTH_LONG;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;
/*TITLE: game
  PURPOSE: this is the code that actually lets the user play 15 squares, it give boundaries for if the player
  is able to play, it makes it so the player can switch tiles, and it also tells the player whether or not
  they have won, once all of the squares are in order 1-15
  ENHANCEMENT: randomly initializes game with the restriction that the game is winnable
  DATE: 22 February 2022
  AUTHOR: Zella Running
*/
public class game extends AppCompatActivity {

    //private instance variables
    private int emptyX = 3;
    private int emptyY = 3;
    private RelativeLayout group;
    private Button[][] buttons;
    private int[] tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //actually implements the methods from down below
        loadViews();
        numbers();
        generateNum();
        loadData();
    }

    //this method sets the tiles that aren't empty to the default color, and sets the ocrrect text to them
    //if they are empty then there will be zero text and set to the colorFreeButton
    private void loadData() {
        emptyX = 3;
        emptyY = 3;
        for (int i = 0; i < group.getChildCount()-1; i++) {
            buttons[i/4][i%4].setText(String.valueOf(tiles[i]));
            buttons[i/4][1%4].setBackgroundResource(android.R.drawable.btn_default);
        }
        buttons[emptyX][emptyY].setText("");
        buttons[emptyX][emptyY].setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
    }

    //this generates random numbers to the tiles so each time the game is different
    private void generateNum() {
        int n=15;
        Random rand = new Random();
        while(n>1) {
            int randNum = rand.nextInt(n--);
            int temp = tiles[randNum];
            tiles[randNum] = tiles[n];
            tiles[n] = temp;
        }
        //ENHANCEMENT: randomly initalizes with the restriction that the game is winnable
        if(!isWinnable()) {
            generateNum();
        }
    }

    //ENHANCEMENT: checks to see if game is winnable
    private boolean isWinnable() {
        int count = 0;
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < i; j++) {
                if(tiles[j]>tiles[i]) {
                    count++;
                }
            }
        }
        return count%2 == 0;
    }

    //loads the numbers on to the tiles after randomizing the numbers
    private void numbers() {
        tiles = new int[16];
        for(int i = 0; i < group.getChildCount() - 1; i++) {
            tiles[i] = i +1;
        }
    }

    //this loads the buttons and the group, sets boundaries
    private void loadViews() {
        group = findViewById(R.id.group);
        buttons = new Button[4][4];
        
        for (int i = 0; i < group.getChildCount(); i++) {
            buttons[i/4][i%4] = (Button) group.getChildAt(i);
        }
    }

    //method that makes the buttons be clickable, the buttons are checked to see if there is an empty space
    //next to them, then the button will switch places with the colorFreeButton, lastly it will check if
    //player has won
    public void buttonClick(View view) {
        Button button = (Button) view;
        int x = button.getTag().toString().charAt(0) - '0';
        int y = button.getTag().toString().charAt(1) - '0';

        if((Math.abs(emptyX-x) == 1 && emptyY == y) || (Math.abs(emptyY-y) == 1 && emptyX == x)) {
            buttons[emptyX][emptyY].setText(button.getText().toString());
            buttons[emptyX][emptyY].setBackgroundResource(android.R.drawable.btn_default);
            button.setText("");
            button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorFreeButton));
            emptyX = x;
            emptyY = y;
            checkWin();
        }
    }

    //checks if player has put all tiles in the correct place, if they are, program will pop up a message saying "YOU WON!!"
    private void checkWin() {
        boolean win = false;
        //checks to see if its a win
        if(emptyX == 3 && emptyY == 3) {
            for (int i = 0; i < group.getChildCount()-1; i++) {
                if(buttons[i/4][i%4].getText().toString().equals(String.valueOf(i+1))) {
                    win = true;
                }else{
                    win = false;
                    break;
                }
            }
        }
        //shows message if win = true
        if(win){

            String you_won = "YOU WON!!";
            SpannableStringBuilder biggerText = new SpannableStringBuilder(you_won);
            biggerText.setSpan(new RelativeSizeSpan(7f), 0, you_won.length(), 0);
            Toast.makeText(this, biggerText, LENGTH_LONG).show();
            for (int i = 0; i < group.getChildCount(); i++) {
                buttons[i/4][i%4].setClickable(false);

            }
        }
    }
}