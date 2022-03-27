
/**Winner's display
*
* @author Chanul Dandeniya
*/

import java.io.*;
import java.lang.Thread;

/** Colors */
public class Win {
  private final String BLUE = "\u001B[36m";
  private final String RED = "\u001B[31m";
  private final String BROWN = "\u001B[33m";
  private final String WHITE = "\u001B[37m";
  private final String BLACK = "\u001B[30m";
  private final String CYAN = "\033[0;34m";
  private final String BLUE_BRIGHT = "\033[0;94m";
  private final String PURPLE = "\033[0;35m";

  private final String[] colorList = new String[] { BLUE, RED, BROWN, CYAN, BLUE_BRIGHT, PURPLE };

  /** Displays fireworks across the screen */
  public void start() throws InterruptedException {
    System.out.println("You guessed the word! Congrats, now time to celebrate!");
    Thread.sleep(2000);
    clearScreen();
    skipLines(20);
    boolean running = true;
    while (running) {
      int distance = (int) (Math.random() * 50) + 10;
      int height = (int) (Math.random() * 15) + 15;
      for (int i = height; i > 5; i--) {
        skipLines(i);
        System.out.println(stringSpace(distance) + WHITE + "|");
        Thread.sleep(100);
        clearScreen();
      }
      try {
        fireworks(distance, height);
      } catch (Exception e) {
      }
    }
  }

  /**
   * Sets the display of fireworks
   * 
   * @param distance How spread out the fireworks go
   * @param height   How high the fireworks go
   */

  public void fireworks(int distance, int height) throws InterruptedException {
    int firstColor = randomColor();
    int secondColor = randomColor();
    int thirdColor = randomColor();
    // first frame
    skipLines(5);
    System.out.println(stringSpace(distance) + "\uD83C\uDF86");
    Thread.sleep(500);
    clearScreen();
    // second frame
    skipLines(5);
    System.out.println(stringSpace(distance - 1) + colorList[firstColor] + "*✩*");
    Thread.sleep(200);
    clearScreen();
    // third frame
    skipLines(4);
    System.out.println(stringSpace(distance - 1) + colorList[firstColor] + "*೨⋆೨*");
    System.out.println(stringSpace(distance - 4) + colorList[firstColor] + "-೨⋆*✩*⋆೨-");
    System.out.println(stringSpace(distance - 1) + colorList[firstColor] + "೨⋆೨");
    Thread.sleep(200);
    clearScreen();
    // fourth frame
    skipLines(3);
    System.out.println(stringSpace(distance - 1) + colorList[firstColor] + "*೨⋆೨*");
    System.out.println(stringSpace(distance - 3) + colorList[firstColor] + "-೨⋆*⋆೨-");
    System.out.println(stringSpace(distance - 5) + colorList[firstColor] + "*｡೨⋆*✩*⋆೨｡*");
    System.out.println(stringSpace(distance - 3) + colorList[firstColor] + "-೨⋆*⋆೨-");
    System.out.println(stringSpace(distance - 1) + colorList[firstColor] + "*೨⋆೨*");
    Thread.sleep(200);
    clearScreen();
  }

  /**
   * Generates random color for fireworks
   * 
   * @return the color
   */
  private int randomColor() {
    return (int) (Math.random() * 6) + 1;
  }

  /**
   * Moves fireworks around screen
   * 
   * @param lines Where the fireworks skips as it displays
   */
  private void skipLines(int lines) {
    for (int i = 0; i < lines; i++) {
      System.out.println();
    }
  }

  /**
   * Generates where the fireworks jumps around
   * 
   * @return spaces between fireworks
   */
  private String stringSpace(int space) {
    String myString = "";
    for (int i = 0; i < space; i++) {
      myString += " ";
    }
    return myString;
  }

  // Clears screen
  private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}