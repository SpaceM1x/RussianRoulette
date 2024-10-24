package com.example.russianroulette;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Game_Activity extends AppCompatActivity {
    private TextView ActivePlayer;
    private Button Shoot;
    private Button Reload;
    private Button Skip;
    private TextView PlayerList;
    int ActualPlayer=0;
    int Turns=0;
    ArrayList<Player> ListOfPlayers = new ArrayList<>();
    int AmountOfPlayers1;
    Revolver revolver = new Revolver();
    // Получаем массив Magazine из Revolver
    int[] magazineArray = revolver.getRevolver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_activity);
        ActivePlayer = findViewById(R.id.ActivePlayer);
        Shoot = findViewById(R.id.button2);
        Reload = findViewById(R.id.button3);
        Skip = findViewById(R.id.button4);
        PlayerList = findViewById(R.id.alive_players_textBox);
        Intent intent = getIntent(); // можно применять только внутри методов жизненного цикла Activity

        // Извлекаем данные по ключу
        int AvailableReloads = intent.getIntExtra("AvailableReloads", 0); // Второй параметр — это значение по умолчанию, если данных нет
        int AvailableSkips  = intent.getIntExtra("AvailableSkips", 0);
        int AmountOfPlayers = intent.getIntExtra("AmountOfPlayers", 0);
        AmountOfPlayers1=AmountOfPlayers;
        for (int i = 0; i < AmountOfPlayers; i++) {
            String PlayerName = "Игрок " + i+1;
            Player player = new Player(AvailableReloads,AvailableSkips,PlayerName);
            ListOfPlayers.add(player);
        }
        StringBuilder stringb = new StringBuilder();
        stringb.append("Живые участники: \n");
        ActivePlayer.setText("Ход Игрока: " + ListOfPlayers.get(ActualPlayer).getPlayerName());
        Shoot.setText("Выстрелить");
        Reload.setText("Прокрутить барабан(" + ListOfPlayers.get(ActualPlayer).getReloads()+")" );
        Skip.setText("Пропустить Ход ("+ ListOfPlayers.get(ActualPlayer).getSkips() + ")");
        for (Player player : ListOfPlayers) {
            stringb.append(player.getPlayerName());
            stringb.append("\n");
        }
        PlayerList.setText(stringb);
    }

    public void onShootClick(View view){
        if (magazineArray[Turns]==1){
            Toast.makeText(this, "Вы погибли,УВЫ", Toast.LENGTH_SHORT).show();
            PlayerList.setText("Погиб: " + ListOfPlayers.get(ActualPlayer).toString());
            ActivePlayer.setText("Игра завершена");
        }
        else {
            ActualPlayer++;
            if (ActualPlayer>=AmountOfPlayers1){
                ActualPlayer=0;
            }
            Turns++;
            if (Turns>=6){
                Turns=0;
            }
            ActivePlayer.setText("Ход Игрока: " + ListOfPlayers.get(ActualPlayer).getPlayerName());
            Reload.setText("Прокрутить барабан(" + ListOfPlayers.get(ActualPlayer).getReloads() + ")");
            Skip.setText("Пропустить Ход (" + ListOfPlayers.get(ActualPlayer).getSkips() + ")");
        }

    }

    public void onReloadClick(View view){
        int rels = ListOfPlayers.get(ActualPlayer).getReloads();
        if (rels>0) {
            revolver = null;
            revolver = new Revolver();
            // Получаем массив Magazine из Revolver
            magazineArray = revolver.getRevolver();
            ListOfPlayers.get(ActualPlayer).setReloads(rels-1);
        }
        else {
            Toast.makeText(this, "У вас кончились перезарядки", Toast.LENGTH_SHORT).show();
        }
        Reload.setText("Прокрутить барабан(" + ListOfPlayers.get(ActualPlayer).getReloads() + ")");
    }

    public void onSkipClick(View view){
        int skips = ListOfPlayers.get(ActualPlayer).getSkips();
        ListOfPlayers.get(ActualPlayer).setSkips(skips-1);
        if (skips>0) {
            ActualPlayer++;
            if (ActualPlayer >= AmountOfPlayers1) {
                ActualPlayer = 0;
            }
            ActivePlayer.setText("Ход Игрока: " + ListOfPlayers.get(ActualPlayer).getPlayerName());
            Reload.setText("Прокрутить барабан(" + ListOfPlayers.get(ActualPlayer).getReloads() + ")");
            Skip.setText("Пропустить Ход (" + ListOfPlayers.get(ActualPlayer).getSkips() + ")");
        }
        else {
            Toast.makeText(this, "У вас кончились пропуски хода", Toast.LENGTH_SHORT).show();
            Skip.setText("Пропустить Ход (0)");
            ListOfPlayers.get(ActualPlayer).setSkips(0);
        }
    }

}