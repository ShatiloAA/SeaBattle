package ru.headfirst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.headfirst.ShipBust.getShips;

public class GameHelper {

    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    private int gridLength;
    private int gridSize = gridLength * gridLength;
    private String[] field;

    public GameHelper(int gridLength) {
        setGridLength(gridLength);
        initializeFieldArray();
    }
    //private int grid[] = new int[gridLength*gridLength];
    public void setGridLength(int gridLength) {
        this.gridLength = gridLength;
    }

    List<String> placeShip(int shipSize) {
        ArrayList<String> alphaCells = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (alphaCells.size() < shipSize) {
            sb.setLength(0);
            int typeOfLocation = (int) ((Math.random() * 10) % 2); //if %2 = 0 - vertical if it's possible, else horizontal.
            System.out.println("typeOfLocation " + typeOfLocation);
            int startPlace = (int) (Math.random() * gridSize); // start point
            //typeOfLocation = 1;
            //startPlace = 42;
            String startPlaceInCoords = sb.append(getColumnChar(startPlace)).append(getLineNumber(startPlace)).toString();
            System.out.println(startPlaceInCoords + " " + startPlace);
            if (startPlace < gridSize && !containsThatAddress(startPlaceInCoords)) {
                if ((typeOfLocation == 0) && ((startPlace + gridLength * (shipSize - 1)) < gridSize)) {
                    verticalArrangment(startPlace, shipSize, alphaCells);
                    //break;
                } else {
                    horizontalArrangement(startPlace, shipSize, alphaCells);
                    //if (alphaCells.size() == shipSize) break;
                }
            }
        }
        return alphaCells;
    }

    private boolean containsThatAddress(String address) {
        //checks if the coordinate is contained among the coordinates of already located ships
        boolean check = false;
        for (Ship x : getShips()) {   //check for cross coordinates with hosted ships
            if ((x.getAdresses() != null) && (x.getAdresses().contains(address))) {
                check = true;
                break;
            }
        }
        return check;
    }

    private void verticalArrangment(int startPlace, int comSize, ArrayList<String> alphaCells) {

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
            } else alphaCells.add(sb.toString());

        }

    }

    private void horizontalArrangement(int startPlace, int shipSize, ArrayList<String> alphaCells) {
        //horizontal

        //int place = startPlace;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shipSize; i++) {
            sb.setLength(0);
            int currentPlace = startPlace + i; // current place
            sb.append(getColumnChar(currentPlace)).append(getLineNumber(currentPlace));
            if (i > 0 && (containsThatAddress(sb.toString()) || (currentPlace % gridLength == 0) || (currentPlace > gridSize - 1))) {
                alphaCells.clear();
                break;
            } else {
                alphaCells.add(sb.toString());
            }

        }
    }

    private char getColumnChar(int column) {
        //return letter for this column
        int index = 0;
        for (int i = 0; i < gridLength; i++) {
            if (column == i | ((column - i) % gridLength == 0)) {
                index = i;
            }
        }
        return alphabet[index];
    }

    private int getLineNumber(int line) {
        //return letter for this line
        int lineNumber = 0;
        for (int i = 0; i < gridLength; i++) {
            if (gridLength * i <= line & line <= gridLength * (i + 1)) {
                lineNumber = i + 1;
            }
        }
        return lineNumber;
    }

    String getUserInput(String message) {
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

    int reverseCellCoordinates(String cell) {
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

    void initializeFieldArray() {
        field = new String[gridSize];
        System.out.println(field.length);
        for (int i = 0; i < field.length; i++) {
            field[i] = "~";
        }
    }

    void checkCell(int cell, String result) {
        /*String x = "~";
        String[] field = new String[gridSize];
        for (int i = 0; i < field.length; i++) {
            field[i] = "~";
            if ((i + 1) % gridLength == 0) System.out.println("  " + field[i]);
            else System.out.print("  " + field[i] + "  ");
        }*/

        String x = "";
        if (result == "it's hit BOY!" || result == "Sunk!") {
            field[cell] = "X";

        }
        else if (result == "Miss!") field[cell] = "+";

        if ((cell + 1) % gridLength == 0)  System.out.println("  " + field[cell]);
        else System.out.print("  " + field[cell] + "  ");


    }

    void visualDisplay(String result, int cell) {
                for (int i = 0; i < gridLength; i++) {
                if (i == 0) System.out.print("   " + alphabet[0] + "  ");
                else System.out.print("  " + alphabet[i] + "  ");
            }
            System.out.println();

            for (int i = 0; i < field.length; i++) {

                if (i == 0) {
                    System.out.print(1);
                    //System.out.print("/");

                }
                else if (i > 0 && (i % gridLength == 0)) System.out.print((i / gridLength) + 1);

                if ( cell == i)  checkCell(i, result);
                else {
                    checkCell(i, null);
                    //System.out.print("//");
                }
        }
        /*
        for (int i = 0; i < gridLength; i++) {
            if (i == 0) System.out.print("   " + alphabet[0] + "  ");
            else System.out.print("  " + alphabet[i] + "  ");
        }
        System.out.println();

        for (int i = 0; i < field.length; i++) {

            if (i == 0) System.out.print(1);
            else if (i > 0 && (i % gridLength == 0)) System.out.print((i / gridLength) + 1);

            if (cell != i) checkGrid(i, null);
            else checkGrid(i, result);
        }*/

    }



}
