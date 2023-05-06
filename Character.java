package Sprites;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import runner.RobbieRunner;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.Sprite;
import visual.statik.TransformableContent;

/**
 * Character class for Robbie, Duck and Ostrich characters.
 * 
 * @author Maxine Payton
 * @version 04/12/2023
 *
 */
public class Character extends RuleBasedSprite implements KeyListener
{
  private static boolean jump = false;
  private static boolean birb = false;
  private int legs = 0; // 0 = left up, 1 = right up
  private AudioSprite jumpAudio;
  private AudioSprite gameOverAudio;
  private int timeInState = 10;
  private int jumpCounter = 0;
  private int timeCounter = 0;
  private int birdCount = 0;
  private Double x, y, initialY;
  private TransformableContent[] contents;

  /**
   * constructor.
   * 
   * @param contents
   * @param width
   * @param height
   * @param jumpAudio
   * @param gameOverAudio
   */
  public Character(final TransformableContent[] contents, final double width, final double height,
      final AudioSprite jumpAudio, final AudioSprite gameOverAudio)
  {
    super(contents[0]);

    this.x = (1.0 / 3.0) * width - 20; // sets character's x position on screen
    initialY = (height / 3.0) + 50;
    this.y = initialY;

    this.contents = contents;

    setLocation(x, y);

    this.jumpAudio = jumpAudio;
    this.gameOverAudio = gameOverAudio;

  }

  /**
   * get the birb.
   * 
   * @return the birb
   */
  public boolean getBirb()
  {
    return birb;
  }

  /**
   * Set the birb.
   * 
   * @param jumpSet
   *          set the birb
   */
  public void setJump(final boolean jumpSet)
  {
    jump = jumpSet;
  }

  /**
   * get the birb.
   * 
   * @return the birb
   */
  public static boolean getJump()
  {
    return jump;
  }

  /**
   * Set the birb.
   * 
   * @param birbSet
   *          set the birb
   */
  public void setBirb(final boolean birbSet)
  {
    birb = birbSet;
  }

  /**
   * get the content.
   * 
   * @return the contents legs
   */
  public TransformableContent getContent()
  {
    return contents[legs];

  }

  @Override
  public void handleTick(final int time)
  {
    // Bird Intersection
    Sprite bird = (Bird) this.antagonists.get(1);

    // Handle Jumping
    // if jump is true
    if (jump)
    {
      setLocation(x, y - 100);
      jumpCounter++;
    }

    // if jump is true and it has been more than 55 ticks
    // if we're intersecting the bird
    // and it has been 200 ticks
    if (jump && jumpCounter > 55)
    {

      if (intersects(bird) && birdCount == 300)
      {
        birdCount = 0;
        setLocation(x, initialY); // set Location to ground
      }
      else
      {
        setJump(false);
        // jump = false;
        jumpCounter = 0;
      }
    }

    if (!jump && !intersects(bird))
    {
      setLocation(x, initialY); // set Location to ground
    }

    // if riding bird and it hasn't been 200 ticks yet
    if ((bird != null) && birdCount < 300 && jump)
    {
      this.setLocation(x, 100); // keep location on the bird
      birdCount++; // increment
      if ((intersects(bird)))
      {
        System.out.println("STOPPING");
        Bird.stopBird(); // Keep the bird in place
      }
    }

    // Handle running
    if (timeCounter == timeInState)
    {
      if (legs == 0)
      {
        legs = 1;
      }
      else
      {
        legs = 0;
      }
      timeCounter = 0;
    }

    timeCounter += 1;

    // Obstacle Stuff
    Sprite obstacle;
    obstacle = null;

    if (time < 1000)
    {
      return;
    }
    if (antagonists.size() > 0)
    {
      obstacle = antagonists.get(0);
    }

    if ((obstacle != null) && (intersects(obstacle)))
    {
      this.gameOverAudio.handleTick(0);
      legs = 0;
      RobbieRunner.instance.stageStop();
    }
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }

  /**
   * get the antagonists.
   * 
   * @return the antagonists
   */
  public ArrayList<Sprite> getAnts()
  {
    return this.antagonists;
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      setJump(true);
      // jump = true;
      this.jumpAudio.handleTick(0);
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {

  }
}
