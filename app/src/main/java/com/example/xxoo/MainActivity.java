package com.example.xxoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private Button [][] buttons = new Button[3][3];
    private boolean player1turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView textViewPlyer1;
    private TextView textViewPlyer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlyer1 = findViewById(R.id.text1);
        textViewPlyer2 = findViewById(R.id.text2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button"+i+j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }


        }
        Button buttonReset = findViewById(R.id.buttonRes);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v ).getText().toString().equals("")) {
            return;

        }
        if (player1turn) {
            ((Button) v).setText("X");
        }else {
            ((Button) v).setText("O");
        }
        roundCount++;
        if (checkwin()) {
            if (player1turn) {
                player1win();
            }else {
                player2win();
            }

        }else if(roundCount == 9){
            draw();
        }else{
            player1turn = !player1turn;
        }

    }

    private boolean checkwin(){
        String [][] checkMas = new String[3][3];
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                checkMas[i][j] = buttons[i][j].getText().toString();
            }

        }
        for (int i = 0; i <3 ; i++) {
            if (checkMas[i][0].equals(checkMas[i][1])
                    && checkMas[i][0].equals(checkMas[i][2])
                    && !checkMas[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i <3 ; i++) {
            if (checkMas[0][i].equals(checkMas[1][i])
                    && checkMas[0][i].equals(checkMas[2][i])
                    && !checkMas[0][i].equals("")) {
                return true;
            }
        }
        if (checkMas[0][0].equals(checkMas[1][1])
                && checkMas[0][0].equals(checkMas[2][2])
                && !checkMas[0][0].equals("")) {
            return true;
        }
        if (checkMas[0][2].equals(checkMas[1][1])
                && checkMas[0][2].equals(checkMas[2][0])
                && !checkMas[0][2].equals("")) {
            return true;
        }
        return false;

    }
    private void player1win(){
        player1Points++;
        Toast.makeText(this,"Первый игрок выйграл",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetboard();
    }
    private void player2win(){
        player2Points++;
        Toast.makeText(this,"Второй игрок выйграл",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetboard();
    }
    private void draw(){
        Toast.makeText(this,"Ничья",Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void updatePoints(){
        textViewPlyer1.setText("Игрок 1: " +player1Points);
        textViewPlyer2.setText("Игрок 2: " +player2Points);
    }
    private void resetboard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3 ; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1turn = true;


    }
    private void resetgame(){
        player1Points = 0;
        player2Points = 0;
        updatePoints();
        resetboard();
    }
}
