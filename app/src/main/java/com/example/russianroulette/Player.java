package com.example.russianroulette;

public class Player {
    private int Reloads;
    private int Skips;
    private String PlayerName;

    private static int NumberOfPlayers=0;

    public Player(){
        Reloads = 1;
        Skips = 1;
        NumberOfPlayers++;
    }
    public Player(int Reloads, int Skips, String PlayerName){
        this.Reloads = Reloads;
        this.Skips = Skips;
        this.PlayerName = PlayerName;
        NumberOfPlayers++;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public int getReloads() {
        return Reloads;
    }

    public void setReloads(int reloads) {
        Reloads = reloads;
    }

    public int getSkips() {
        return Skips;
    }

    public void setSkips(int skips) {
        Skips = skips;
    }

    public static int getNumberOfPlayers() {
        return NumberOfPlayers;
    }

    public static void setNumberOfPlayers(int numberOfPlayers) {
        NumberOfPlayers = numberOfPlayers;
    }
}
