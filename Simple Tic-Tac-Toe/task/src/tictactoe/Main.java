package tictactoe;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char[][] twoDimArray = new char[3][3];

        for (char[] chars : twoDimArray) {
            Arrays.fill(chars, '0');
        }

        printEmptyGrid(twoDimArray);

        Scanner scanner = new Scanner(System.in);

        String firstNumber;
        int firstNum, secondNum;
        boolean correctInput = false;
        boolean gameEnds = false;
        boolean isX = true;

        do {
            firstNumber = scanner.nextLine();
            firstNumber = firstNumber.replaceAll("\\s", "");
            if (!isNumeric(firstNumber)) {
                System.out.println("You should enter numbers!");
            } else {
                String[] newStr = firstNumber.split("");
                if (newStr.length != 2) {
                    System.out.println("You should enter two numbers!");
                } else {
                    firstNum = Integer.parseInt(newStr[0]);
                    secondNum = Integer.parseInt(newStr[1]);

                    if (firstNum > 3 || firstNum < 1 || secondNum > 3 || secondNum < 1) {
                        System.out.println("Coordinates should be from 1 to 3!");
                    } else {
                        if(isOccupied(firstNum, secondNum, twoDimArray)) {
                            System.out.println("This cell is occupied! Choose another one!");
                        } else {
                            correctInput = true;

                            if (isX) {
                                printXGrid(firstNum, secondNum, twoDimArray);
                                isX = false;
                            } else {
                                printOGrid(firstNum, secondNum, twoDimArray);
                                isX = true;
                            }

                            if (checkWhoWins(twoDimArray)) {
                                gameEnds = true;
                            }
                        }
                    }
                }
            }
        } while (!correctInput || !gameEnds);
    }

    public static void printEmptyGrid(char[][] ourArray) {
        System.out.print("""
                ---------
                |\s""");

        for (int i = 0; i < ourArray.length; i++) {
            for (int j = 0; j < ourArray[i].length; j++) {
                if (ourArray[i][j] == '0') {
                    System.out.print("  ");
                } else {
                    System.out.print(ourArray[i][j] + " ");
                }

                if (j == 2 && i == 2) {
                    System.out.print("""
                        |""");
                } else if (j == 2) {
                    System.out.print("""
                        |
                        |\s""");
                }
            }
        }

        System.out.print("""
                
                ---------
                """);
    }

    public static void printFirstGrid(char[][] ourArray) {
        System.out.print("""
                ---------
                |\s""");

        for (int i = 0; i < ourArray.length; i++) {
            for (int j = 0; j < ourArray[i].length; j++) {
                if (ourArray[i][j] == '0') {
                    System.out.print("  ");
                } else {
                    System.out.print(ourArray[i][j] + " ");
                }

                if (j == 2 && i == 2) {
                    System.out.print("""
                        |""");
                } else if (j == 2) {
                    System.out.print("""
                        |
                        |\s""");
                }
            }
        }

        System.out.print("""
                
                ---------
                """);
    }

    public static boolean checkWhoWins(char[][] ourArray) {
        boolean winX = false;
        boolean winO = false;

        for (int i = 0; i < 3; i++) {
            // check columns & rows
            if (ourArray[0][i] == 'X' && ourArray[1][i] == 'X' && ourArray[2][i] == 'X' || ourArray[i][0] == 'X' && ourArray[i][1] == 'X' && ourArray[i][2] == 'X') {
                winX = true;
                break;
            }
        }

        // check diagonals
        if (ourArray[0][0] == 'X' && ourArray[1][1] == 'X' && ourArray[2][2] == 'X'
                || ourArray[0][2] == 'X' && ourArray[1][1] == 'X' && ourArray[2][0] == 'X') {
            winX = true;
        }

        for (int i = 0; i < 3; i++) {
            // check columns & rows
            if (ourArray[0][i] == 'O' && ourArray[1][i] == 'O' && ourArray[2][i] == 'O' || ourArray[i][0] == 'O' && ourArray[i][1] == 'O' && ourArray[i][2] == 'O') {
                winO = true;
                break;
            }
        }

        // check diagonals
        if (ourArray[0][0] == 'O' && ourArray[1][1] == 'O' && ourArray[2][2] == 'O'
                || ourArray[0][2] == 'O' && ourArray[1][1] == 'O' && ourArray[2][0] == 'O') {
            winO = true;
        }

        int count = 0;
        for (char[] chars : ourArray) {
            for (char aChar : chars) {
                if (aChar == '0') {
                    count++;
                }
            }
        }

        if (winX && winO) {
            System.out.println("Impossible");
            return true;
        } else if (winX) {
            System.out.println("X wins");
            return true;
        } else if (winO) {
            System.out.println("O wins");
            return true;
        } else if (count == 0) {
            System.out.println("Draw");
            return true;
        } else {
            return false;
        }
    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    public static boolean isOccupied(int first, int second, char[][] ourArray) {
        return ourArray[first - 1][second - 1] != '0';
    }

    public static void printXGrid(int first, int second, char[][] ourArray) {
        ourArray[first - 1][second - 1] = 'X';
        printFirstGrid(ourArray);
    }

    public static void printOGrid(int first, int second, char[][] ourArray) {
        ourArray[first - 1][second - 1] = 'O';
        printFirstGrid(ourArray);
    }
}


