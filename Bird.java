package Sprites;

import visual.dynamic.described.RuleBasedSprite;
import visual.statik.TransformableContent;

/**
 * Birds in game.
 * 
 * @author Maxine Payton
 * @version 04/12/2023
 *
 */
public class Bird extends RuleBasedSprite
{
  private static boolean stop = false;
  private double x, y, maxX;
  private TransformableContent[] contents;
  private int timeInState = 10;
  private int timeCounter = 0;
  private int state = 0; // 0 = wings up, 1 = wings down
  private int birdCounter = 0;

  /**
   * Constructor.
   * 
   * @param contents
   * @param width
   */
  public Bird(final TransformableContent[] contents, final int width)
  {
    // we dont need the initial location or maxY variables
    super(contents[0]);
    this.maxX = width; // SET TO FURTHEREST SPOT BIRDS CAN GO
    this.contents = contents;
    this.x = -70;
    this.y = 70; // 2/3 of the stage height
    // setLocation(x, y);
    // handleTick(0);
  }

  @Override
  public void handleTick(final int time)
  {
    if (timeCounter == timeInState)
    {
      if (state == 0)
      {
        state = 1;
      }
      else
      {
        state = 0;
      }
      timeCounter = 0;
    }

    timeCounter += 1;

    if (stop && birdCounter > 150)
    {
      setLocation(250, 60);
      birdCounter = 0;
      stop = false;
      x = 250;
    }

    birdCounter++;
    updateLocation();
  }

  /**
   * update the location.
   */
  public void updateLocation()
  {

    if (!stop)
    {
      x += 2;

      if (x == (int) maxX)
      {
        x = -70;
      }
      setLocation(x, y);
    }
    else
    {
      setLocation(250, 60);
    }

  }

  /**
   * get the content.
   * 
   * @return the state of the bird
   */
  public TransformableContent getContent()
  {
    return contents[state];
  }

  /**
   * stop the bird.
   */
  public static void stopBird()
  {
    stop = true;
  }

}
