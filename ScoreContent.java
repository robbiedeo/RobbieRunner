package scoreboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import visual.statik.described.Content;

/**
 * The Score content class.
 * 
 * @author Makenzie Williams
 *
 */
public class ScoreContent extends Content
{
  private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 18);
  protected static final Color BLACK = Color.BLACK;
  private int width;
  private int height;
  private int currScore;
  private int highScore;

  /**
   * The constructor.
   * 
   * @param width
   * @param height
   */
  public ScoreContent(final int width, final int height)
  {
    currScore = 0;
    highScore = 0;
    this.width = width;
    this.height = height;
  }

  /**
   * get the current.
   * 
   * @return the current score.
   */
  public int getCurrScore()
  {
    return currScore;
  }

  /**
   * reset the score.
   */
  public void resetCurrScore()
  {
    currScore = 0;
  }

  /**
   * get the high score.
   * 
   * @return the high score
   */
  public int getHighScore()
  {
    return highScore;
  }

  /**
   * set the high score.
   */
  public void setHighScore()
  {
    if (currScore > highScore)
    {
      highScore = currScore;
    }
  }

  /**
   * render the graphics obj.
   * 
   * @param g
   *          the graphics to use
   */
  public void render(final Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(BLACK);
    g2.setFont(FONT);

    int y = height - 325;
    int x = width - 175;
    g2.drawString("Score:   " + String.valueOf(currScore), x, y);
  }

  /**
   * hand the score updating constantly.
   */
  public void handleScoreUpdate()
  {
    currScore++;
  }
}
