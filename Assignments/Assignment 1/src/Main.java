import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    // File reading method.
    public static String[] readFile(String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // File writing method.
    static void fileWrite(String n) {
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(n);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method returns the x coordinate of white ball.
    static int getX(String[][] arr, int x) {
        for (String[] array : arr) {
            for (String string : array) {
                if (string.equals("*")) {
                    x = Arrays.asList(array).indexOf(string);
                }
            }
        }
        return x;
    }

    // This method returns the y coordinate of white ball.
    static int getY(String[][] arr, int y) {
        int i = 0;
        for (String[] array : arr) {
            for (String string : array) {
                if (string.equals("*")) {
                    y = i;
                    break;
                }
            }
            i += 1;
        }
        return y;
    }

    // This method creates the game board.
    static String[][] boardMaker(String[] arr) {
        int rows = arr.length; // y axis
        int columns = arr[0].length(); // x axis
        String[][] board = new String[rows][(columns + 1)/2]; // (columns + 1)/2 is an easy way to get only the colors, not the whitespaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j = j + 2) {
                board[i][j/2] = String.valueOf(arr[i].charAt(j));
            }
        }
        return board;
    }

    // This method prints (well, writes) the game board.
    static void boardPrinter(String[][] board) {
        for (String[] array : board) {
            for (String str : array) {
                fileWrite(str + " ");
            }
            fileWrite("\n");
        }
        fileWrite("\n");
    }

    // Method for horizontal (R, L) operations.
    static int[] horizontal(String[][] board, int ycor, int xcor, int opt, int optWall) {
        int[] arr = new int[2];
        String target = board[ycor][opt];
        if (target.equals("H")) {
            board[ycor][xcor] = " ";
        }
        else if (target.equals("R")) {
            board[ycor][xcor] = "X";
            board[ycor][opt] = "*";
            xcor = opt;
            arr[1] = 10;
        }
        else if (target.equals("Y")) {
            board[ycor][xcor] = "X";
            board[ycor][opt] = "*";
            xcor = opt;
            arr[1] = 5;
        }
        else if (target.equals("B")) {
            board[ycor][xcor] = "X";
            board[ycor][opt] = "*";
            xcor = opt;
            arr[1] = -5;
        }
        else if (target.equals("W")) {
            String temp = board[ycor][optWall];
            if (temp.equals("R")) {
                board[ycor][xcor] = "X";
                arr[1] = 10;
            }
            else if (temp.equals("Y")) {
                board[ycor][xcor] = "X";
                arr[1] = 5;
            }
            else if (temp.equals("B")) {
                board[ycor][xcor] = "X";
                arr[1] = -5;
            }
            else {
                board[ycor][xcor] = temp;
            }
            board[ycor][optWall] = "*";
            xcor = optWall;
        }
        else {
            board[ycor][xcor] = target;
            board[ycor][opt] = "*";
            xcor = opt;
        }
        arr[0] = xcor;
        return arr;
    }

    // Method for vertical (U, D) operations.
    static int[] vertical(String[][] board, int ycor, int xcor, int opt, int optWall)  {
        int[] arr = new int[2];
        String target = board[opt][xcor];
        if (target.equals("H")) {
            board[ycor][xcor] = " ";
        }
        else if (target.equals("R")) {
            board[ycor][xcor] = "X";
            board[opt][xcor] = "*";
            ycor = opt;
            arr[1] = 10;
        }
        else if (target.equals("Y")) {
            board[ycor][xcor] = "X";
            board[opt][xcor] = "*";
            ycor = opt;
            arr[1] = 5;
        }
        else if (target.equals("B")) {
            board[ycor][xcor] = "X";
            board[opt][xcor] = "*";
            ycor = opt;
            arr[1] = -5;
        }
        else if (target.equals("W")) {
            String temp = board[optWall][xcor];
            if (temp.equals("R")) {
                board[ycor][xcor] = "X";
                arr[1] = 10;
            }
            else if (temp.equals("Y")) {
                board[ycor][xcor] = "X";
                arr[1] = 5;
            }
            else if (temp.equals("B")) {
                board[ycor][xcor] = "X";
                arr[1] = -5;
            }
            else {
                board[ycor][xcor] = temp;
            }
            board[optWall][xcor] = "*";
            ycor = optWall;
        }
        else {
            board[ycor][xcor] = target;
            board[opt][xcor] = "*";
            ycor = opt;
        }
        arr[0] = ycor;
        return arr;
    }

    // This method controls the whole movement.
    static int mover(String[][] board, String[] move, int lengthX, int lengthY) {
        int points = 0;
        int[] array;
        int opt, optWall; //operation and operationWall, they vary in accordance to current operation
        int xcor = getX(board, 0);
        int ycor = getY(board, 0);
        String moves = move[0].replace(" ", "");
        for (int i = 0; i < moves.length(); i++) {
            String operator = String.valueOf(moves.charAt(i)); //checking for operations
            if (operator.equals("L")) {
                if (xcor > 0) {
                    opt = xcor - 1;
                    optWall = xcor + 1;
                }
                else {
                    opt = lengthX - 1;
                    optWall = 1;
                }
                array = horizontal(board, ycor, xcor, opt, optWall);
                xcor = array[0];
                points += array[1];
            }
            else if (operator.equals("R")) {
                if (xcor == lengthX - 1) {
                    opt = 0;
                    optWall = lengthX - 2;
                }
                else {
                    opt = xcor + 1;
                    optWall = xcor - 1;
                }
                array = horizontal(board, ycor, xcor, opt, optWall);
                xcor = array[0];
                points += array[1];
            }
            else if (operator.equals("U")) {
                if (ycor == 0) {
                    opt = lengthY - 1;
                    optWall = 1;
                }
                else {
                    opt = ycor - 1;
                    optWall = ycor + 1;
                }
                array = vertical(board, ycor, xcor, opt, optWall);
                ycor = array[0];
                points += array[1];
            }
            else {
                if (ycor == lengthY - 1) {
                    opt = 0;
                    optWall = lengthY - 2;
                }
                else {
                    opt = ycor + 1;
                    optWall = ycor - 1;
                }
                array = vertical(board, ycor, xcor, opt, optWall);
                ycor = array[0];
                points += array[1];
            }
        }
        return points;
    }

    public static void main(String[] args) {
        String[] rawStrings = readFile(args[0]);
        int lengthY = rawStrings.length; // row count
        int lengthX = (rawStrings[0].length() + 1)/2; // column count, again the shortcut :)
        String[] move = readFile(args[1]);
        String[][] board = boardMaker(rawStrings); // 2D array for board
        fileWrite("Game Board:\n");
        boardPrinter(board);
        fileWrite("Your movement is:\n");
        for (String string : move) {
            fileWrite(string + " ");
        }
        int points = mover(board, move, lengthX, lengthY);
        fileWrite("\n\nYour output is:\n");
        boardPrinter(board);
        fileWrite("Game Over!\nScore: " + points);
    }
}
