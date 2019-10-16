package ru.headfirst;

import java.util.ArrayList;
import java.util.List;

public class ShipBust {

    private static List<Ship> ships = new ArrayList<>();
    private int numOfGuesses = 0;
    private GameHelper gameHelper = new GameHelper();

    static List<Ship> getShips() {
        return ships;
    }

    private void setUpGame() {
        Ship siteOne = new Ship();
        Ship siteTwo = new Ship();
        Ship siteThree = new Ship();
        siteOne.setName("One");
        siteTwo.setName("Two");
        siteThree.setName("Three");
        ships.add(siteOne);
        ships.add(siteTwo);
        ships.add(siteThree);
        for (Ship x : ships) {
            x.setAdresses(gameHelper.placeShip(3));
        }
    }

    void startPlaying() {
        while (ships.size() !=0) {
            String userGuess = gameHelper.getUserInput("JUST DO IT!");
            checkUserGuess(userGuess);
        }
        finishGame();
    }



    void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Miss!";
        for (Ship x : ships) {
            result = x.checkYourself(userGuess);
            if (result.equals("it's hit BOY!")) break;
            else if (result == "Sunk!") {
                ships.remove(x);
                break;
            }

        }
        gameHelper.visualDisplay(result, gameHelper.reverseCellCoordinates(userGuess));
        System.out.println(result);
    }



    void finishGame() {
        System.out.println("~~~FINISH GAME!~~~");
        if (numOfGuesses <= 18) System.out.println("YES BOY! Only " + numOfGuesses + " moves!");
        else System.out.println("it's a shame bro! " + numOfGuesses + " moooves! :(");
    }

    public static void main(String[] args) {
        ShipBust shipBust = new ShipBust();
        shipBust.setUpGame();
        for (Ship x : ships) {
            System.out.println(ships.size());
            System.out.println(x.getAdresses().size());
            System.out.println(x.getAdresses());
        }
        shipBust.startPlaying();

    }



}
