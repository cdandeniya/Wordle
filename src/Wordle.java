
/**Main logic for Wordle game
  *
  *@author Chanul Dandeniya
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Wordle {
  private ArrayList<String> guessWords;
  private Scanner scanner, guessFile;
  private Random random;
  private String word;
  private char[][] table;
  private String[] keyboard;
  private int guessCount;
  private boolean solved;

  /** Creates 2D array of 6 rows/5 col. Displays keyboard of letters */
  public Wordle() throws IOException {
    guessWords = new ArrayList<>();
    scanner = new Scanner(System.in);
    guessFile = new Scanner(new File("five_letter_words.csv"));
    random = new Random();
    table = new char[6][5];
    guessCount = 0;
    solved = false;
    keyboard = new String[26];
    for (int i = 0; i <= 25; i++)
      keyboard[i] = Character.toString((char) (i + 65));
    // Gets random word from file
    while (guessFile.hasNext()) {
      guessWords.add(guessFile.next());
    }

    word = guessWords.get(random.nextInt(guessWords.size())).toUpperCase();
  }

  /**
   * Returns the word
   * 
   * @return word
   */
  public String getWord() {
    return word;
  }

  /**
   * Sets guesses to uppercase and compares to wordle
   *
   * @param user guess
   */
  public void guessWord(String guess) {
    for (int i = 0; i <= 4; i++)
      table[guessCount][i] = guess.toUpperCase().charAt(i);
    guessCount++;
    if (guess.toUpperCase().equals(word))
      solved = true;
  }

  // Stops program once user guessed six times
  public boolean repeat() {
    if (guessCount >= 6)
      return false;
    else if (solved)
      return false;
    return true;
  }

  // Displays message respective to win status
  public void endGame() {
    if (solved) {
      Win program = new Win();
      try {
        program.start();
      } catch (Exception e) {
      }
    } else
      System.out.println("Better luck next time!\nThe word was " + word);
  }

  /**
   * Prints table of guesses and displays colors according to accuracy of guess
   */
  public void printTable() {
    String temp = "";
    for (int row = 0; row <= 5; row++) {
      for (int col = 0; col <= 4; col++) {
        if (table[row][col] == word.charAt(col)) {
          System.out.print("\u001B[32m");
          temp = keyboard[(int) table[row][col] - 65];
          keyboard[(int) table[row][col] - 65] = "\u001B[32m" + temp.substring(temp.length() - 1);
        } else if (word.contains(Character.toString(table[row][col]))) {
          System.out.print("\u001B[31m");
          temp = keyboard[(int) table[row][col] - 65];
          if (temp.length() == 1 || !temp.substring(0, 3).equals("\u001B[32"))
            keyboard[(int) table[row][col] - 65] = "\u001B[31m" + temp.substring(temp.length() - 1);
        } else {
          System.out.print("\u001B[0m");
          if ((int) table[row][col] != 0) {
            temp = keyboard[(int) table[row][col] - 65];
            keyboard[(int) table[row][col] - 65] = "\u001B[90m" + temp;
          }
        }
        System.out.print(table[row][col] + " ");
      }
      System.out.println("\u001B[0m");
    }
    System.out.println();
  }

  // Prints keyboard of letters the user has guessed
  public void printKeyboard() {
    for (int i = 1; i <= 100; i++)
      System.out.println();
    for (int i = 0; i <= 25; i++) {
      if ((i - 1) % 9 == 8)
        System.out.println();
      System.out.print("\u001B[0m" + " " + keyboard[i] + " ");
    }
    System.out.println("\n");
  }
}