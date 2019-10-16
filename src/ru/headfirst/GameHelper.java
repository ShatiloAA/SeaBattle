package ru.headfirst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static ru.headfirst.DotComBust.getSites;

public class GameHelper {

    private static char[] alphabet = {'a','b','c','d','e','f','g'};
    private static int gridLength = 7;
    private static int gridSize = gridLength*gridLength;
    //private int grid[] = new int[gridLength*gridLength];

    public ArrayList<String> placeDotCom(int comSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
       while (alphaCells.size() != 3) {
           sb.setLength(0);
           int hrzntlOrVrtcl = (int) ((Math.random() * 10) % 2); //if %2 = 0 - vertical if it's possible, else horizontal.
           //System.out.println("hrzntlOrVrtcl "+hrzntlOrVrtcl);
           int startPlace = (int) (Math.random() * gridSize); // start point
           String startPlaceInCoords = sb.append(getColumnChar(startPlace)).append(getLineNumber(startPlace)).toString();
           if (startPlace < gridSize && !containsThatAddress(startPlaceInCoords)) {
               if ((hrzntlOrVrtcl == 0 ) && ((startPlace + gridLength * (comSize-1))< gridSize)) {
                   verticalArrangment(startPlace, comSize, alphaCells);
                   //break;
               }
               else {
                   horizontalArrangement(startPlace,comSize, alphaCells);
                   //if (alphaCells.size() == comSize) break;
               }
           }
       }
       return alphaCells;
    }

    public boolean containsThatAddress(String address) {
        //checks if the coordinate is contained among the coordinates of already located ships
        boolean check = false;
        for (DotCom x :  getSites()) {   //check for cross coordinates with hosted ships
            if ((x.getAdresses() != null) && (x.getAdresses().contains(address))) {
                check = true;
                break;
            }
        }
       return check;
    }

    public void verticalArrangment(int startPlace, int comSize, ArrayList<String> alphaCells){

        //if (((startPlace + (comSize-1)) % gridLength == 0) & ((startPlace + gridLength * (comSize-1))< gridSize)) {

        //vertical
        StringBuilder sb = new StringBuilder();
            for (int i = 0; i < comSize; i++) {
                sb.setLength(0);
                sb.append(getColumnChar(startPlace)).append(getLineNumber(startPlace + (gridLength * i)));
                //alphaCells.add(sb.toString());

                    if (containsThatAddress(sb.toString())) {

                        alphaCells.clear();
                        break;
                    }
                    else alphaCells.add(sb.toString());

                }

    }

    public void horizontalArrangement(int startPlace, int comSize, ArrayList<String> alphaCells) {
        //horizontal

        //int place = startPlace;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < comSize; i++) {
            sb.setLength(0);
            if (((startPlace + i) % gridLength != 0) & (startPlace < gridSize - 1)) {
                sb.append(getColumnChar(startPlace+i)).append(getLineNumber(startPlace+i));
                if (containsThatAddress(sb.toString())) {
                    //alphaCells.clear();
                    sb.setLength(0);
                    break;
                }
                else {
                    alphaCells.add(sb.toString());
                }
            }
        }
    }

    public static char getColumnChar (int column) {
        //return letter for this column
        int index = 0;
        for (int i = 0; i < gridLength; i++) {
            if (column == i | ((column - i) % gridLength == 0)) {
                index = i;
            }
        }
        return alphabet[index];
    }

    public static int getLineNumber (int line) {
        //return letter for this line
        int lineNumber = 0;
        for (int i = 0; i < gridLength; i++) {
            if ( gridLength * i <= line & line <= gridLength * (i+1)){
                    lineNumber = i + 1;
            }
        }
        return lineNumber;
    }

    public static String getUserInput(String message) {
        String userInput = null;
        System.out.println(message);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userInput = reader.readLine().toLowerCase();
            if (userInput.isEmpty()) return null;
        } catch (IOException e) {
            System.out.println("Please enter a valid cell!");
            e.printStackTrace();
        }
        return userInput;
    }

    public static int reverseCellCoordinates (String cell) {
        int indexGrid = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gridSize; i++) {

            String thisCell = sb.append(getColumnChar(i)).append(getLineNumber(i)).toString();
            //System.out.println(thisCell);
            if (cell.equals(thisCell)) {
                indexGrid = i;
                System.out.println(cell + " " + indexGrid);
            }
            sb.setLength(0);
        }

        return indexGrid;
    }

    public static void visualDisplay (String result, int cell) {
        for (int i = 0; i < gridLength; i++) {
            if (i == 0)System.out.print("   " + alphabet[0] + "  ");
            else System.out.print("  " + alphabet[i] + "  ");
        }
        System.out.println();

        for (int i = 0; i < gridSize; i++ ) {

            if (i == 0) System.out.print(1);
            else if (i > 0 && (i % gridLength == 0)) System.out.print((i/gridLength)+1);

            if (cell != i) checkGrid(i, null);
            else checkGrid(i, result);
        }
    }

    public static void checkGrid (int cell, String result) {
        String x = "";
        if (result == "it's hit BOY!" | result == "Sunk!") x = "X";
        else if (result == "Miss!") x = "0";
        else x = "~";

        if ((cell + 1) % 7 == 0)  System.out.println("  " + x);
        else System.out.print("  " + x + "  ");
    }


}
