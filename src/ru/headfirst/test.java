package ru.headfirst;

public class test {
    private static char[] alphabet = {'a','b','c','d','e','f','g'};
    private static int gridLength = 7;
    private static int grid = gridLength*gridLength;
    public static void main(String[] args) {
    }

    public void graphicalView (int gridLength, String userGuess, String result) {
        gridLength = gridLength + 1; // new length of the string
        grid = gridLength * gridLength; // new size of the grid
        String[] gameField = new String[grid];



        for (int i = 0; i < grid; i++) {
            if (i == 0) {
                gameField[i] = " ";
                System.out.print(gameField[i]);
            }
            else if (0 < i & i < gridLength) {
                checkEndAndPrint(i, alphabet, gridLength);
            }

            else if (i % gridLength == 0) System.out.print(i / gridLength);
            else checkEndAndPrint(i, gameField, gridLength);
        }
    }

    private void checkEndAndPrint(int indexOfCell, String[] gameField, int gridLength) {
        if ((indexOfCell + 2) % gridLength == 0) System.out.println(gameField[indexOfCell]);
        else System.out.print(gameField[indexOfCell]);
    }

    private void checkEndAndPrint(int indexOfCell, char[] alphabet, int gridLength) {
        if ((indexOfCell + 2) % gridLength == 0) System.out.println(alphabet[indexOfCell-1]);
        else System.out.print(alphabet[indexOfCell]);
    }
}


