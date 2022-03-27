
/**
* * This class represents the main class
*
* @author Chanul Dandeniya
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Welcomes player to game and starts program. Ensures player inputs valid
 * guesses
 */

public class WordleRunner {
  public static void main(String[] args) throws IOException {
    Wordle play = new Wordle();
    Scanner scanner = new Scanner(System.in);
    String guess;

    System.out.println("Welcome to Wordle!\n\n\n\n\n\n\n");

    while (play.repeat()) {
      System.out.print("Enter your guess: ");
      guess = scanner.next();
      if (guess.length() < play.getWord().length() || guess.length() > play.getWord().length()) {
        System.out.println("Please enter a 5-letter word");
        System.out.print("Enter your guess: ");
        guess = scanner.next();
      }
      play.guessWord(guess);
      play.printTable();
      play.printKeyboard();
      play.printTable();
    }
    play.endGame();
  }
}