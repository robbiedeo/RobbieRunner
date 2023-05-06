package scoreboard;

import visual.dynamic.described.RuleBasedSprite;

/**
 * the score sprite class.
 * 
 * @author Makenzie Williams
 *
 */
public class ScoreSprite extends RuleBasedSprite
{
  public static final int START = 0;
  public static final int MAX = 99999;
  private ScoreContent scoreContent;

  /**
   * the constructor.
   * 
   * @param scoreContent
   */
  public ScoreSprite(final ScoreContent scoreContent)
  {
    super(scoreContent);
    this.scoreContent = scoreContent;
  }

  @Override
  public void handleTick(final int tick)
  {
    scoreContent.handleScoreUpdate();
  }

}
