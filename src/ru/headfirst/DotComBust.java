package ru.headfirst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static ru.headfirst.GameHelper.reverseCellCoordinates;
import static ru.headfirst.GameHelper.visualDisplay;

public class DotComBust {

    private static ArrayList<DotCom> sites = new ArrayList<>();
    private int numOfGuesses = 0;
    private GameHelper gameHelper = new GameHelper();

    public static ArrayList<DotCom> getSites() {
        return sites;
    }

    private void setUpGame() {
        DotCom siteOne = new DotCom();
        DotCom siteTwo = new DotCom();
        DotCom siteThree = new DotCom();
        siteOne.setName("One");
        siteTwo.setName("Two");
        siteThree.setName("Three");
        sites.add(siteOne);
        sites.add(siteTwo);
        sites.add(siteThree);
        for (DotCom x : sites) {
            x.setAdresses(gameHelper.placeDotCom(3));
        }
    }

    public void startPlaying() {
        while (sites.size() !=0) {
            String userGuess = gameHelper.getUserInput("JUST DO IT!");
            checkUserGuess(userGuess);
        }
        finishGame();
    }



    public void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Miss!";
        for (DotCom x : sites) {
            result = x.checkYourself(userGuess);
            if (result.equals("it's hit BOY!")) break;
            else if (result == "Sunk!") {
                sites.remove(x);
                break;
            }

        }
        visualDisplay(result, reverseCellCoordinates(userGuess));
        System.out.println(result);
    }



    public void finishGame() {
        System.out.println("~~~FINISH GAME!~~~");
        if (numOfGuesses <= 18) System.out.println("YES BOY! Only " + numOfGuesses + " moves!");
        else System.out.println("it's a shame bro! " + numOfGuesses + " moooves! :(");
    }

    public static void main(String[] args) {
        DotComBust dotComBust = new DotComBust();
        dotComBust.setUpGame();
        for (DotCom x : sites) {
            System.out.println(sites.size());
            System.out.println(x.getAdresses().size());
            System.out.println(x.getAdresses());
        }
        dotComBust.startPlaying();

    }



}
