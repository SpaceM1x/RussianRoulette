package com.example.russianroulette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText PlayerBase;
    private EditText skipTurn;
    private EditText Reloads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        PlayerBase = findViewById(R.id.editTextText2);
        skipTurn = findViewById(R.id.editTextText3);
        Reloads = findViewById(R.id.editTextText4);
    }

    public void StartTheGame(View view) {

        if (Reloads.getText().toString().isEmpty()){
            Toast.makeText(this, "Введите число Перезарядок", Toast.LENGTH_SHORT).show();
            return; // Нужен чтобы прервать выполнение програмы без введения числа.
        }
        if (skipTurn.getText().toString().isEmpty()){
            Toast.makeText(this, "Введите число Пропусков", Toast.LENGTH_SHORT).show();
            return; // Нужен чтобы прервать выполнение програмы без введения числа.
        }
        if (PlayerBase.getText().toString().isEmpty()){
            Toast.makeText(this, "Введите число Игроков", Toast.LENGTH_SHORT).show();
            return; // Нужен чтобы прервать выполнение програмы без введения числа.
        }

        int AvailableReloads = Integer.parseInt(Reloads.getText().toString());
        int AvailableSkips = Integer.parseInt(skipTurn.getText().toString());
        int AmountOfPlayers = Integer.parseInt(PlayerBase.getText().toString());

        //Отправляю данные в новое активити
        Intent game = new Intent(MainActivity.this, Game_Activity.class);
        // intent.putExtra("ключ", значение); // "ключ" – это идентификатор данных, значение – это данные, которые передаются

        game.putExtra("AvailableReloads",AvailableReloads);
        game.putExtra("AvailableSkips",AvailableSkips);
        game.putExtra("AmountOfPlayers",AmountOfPlayers);

        startActivity(game);
    }


}