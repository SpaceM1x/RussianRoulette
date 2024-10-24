package com.example.russianroulette;

import java.util.ArrayList;
import java.util.Random;

public class Revolver {
    Random random = new Random();
    private int[] Magazine = {0, 0, 0, 0, 0, 0};

    public Revolver(){
        int randomIndex = random.nextInt(Magazine.length); // Получаем случайное число от 0 до длины массива
        // Замена случайного элемента на 1
//        Magazine[randomIndex] = 1;
    }
    public  int[] getRevolver(){
        return Magazine;
    }
}
