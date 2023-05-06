package runner;

import java.awt.Color;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

import app.JApplication;
import gui.Ground;
import gui.Menu;
import io.ResourceFinder;
import scoreboard.ScoreContent;
import scoreboard.ScoreSprite;
import Sprites.AudioSprite;
import Sprites.Bird;
import Sprites.Character;
import Sprites.Obstacle;
import visual.VisualizationView;
import visual.dynamic.described.Stage;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;

/**
 * The main runner class.
 * 
 * @author Makenzie Williams, Robbie Deonarain, Katherine Hassler, Maxine Payton
 *
 */
public class RobbieRunner extends JApplication implements ActionListener
{
  public static Stage stage;
  public static RobbieRunner instance;
  public static final int WIDTH = 1280;
  public static final int HEIGHT = 360;
  public static Menu menu = new Menu();
  public static boolean audioIsPlaying = false;
  protected static final String CHAR1 = "BANANA ROBBIE";
  protected static final String CHAR2 = "WORKOUT ROBBIE";
  protected static final String CHAR3 = "SIMP ROBBIE";
  static ScoreContent scoreContent = new ScoreContent(WIDTH, HEIGHT);
  private static ArrayList<Content> c = new ArrayList<Content>();
  private String br1 = "bananarobbie1.png";
  private String br2 = "bananarobbie2.png";
  private String cloud = "clouds.png";
  private String character1 = br1;
  private String character2 = br2;

  ScoreSprite scoreSprite = new ScoreSprite(scoreContent);

  /**
   * The constructor.
   * 
   * @param args
   *          the args to use
   */
  public RobbieRunner(final String[] args)
  {
    super(args, WIDTH, HEIGHT);
  }

  @Override
  public void init()
  {
    JPanel contentPane = (JPanel) getContentPane();
    contentPane.removeAll();
    contentPane.setLayout(null);

    stage = new Stage(10);
    VisualizationView stageView = stage.getView();
    stageView.setBounds(0, 0, WIDTH, HEIGHT);
    stageView.setBackground(new Color(173, 216, 230));

    ResourceFinder finder = ResourceFinder.createInstance(resources.Marker.class);
    ContentFactory tcFactory = new ContentFactory(finder);

    // Audio taken from https://mixkit.co/free-stock-music/
    InputStream bgMusic = new BufferedInputStream(finder.findInputStream("background_music.wav"));
    InputStream jumpAudio = new BufferedInputStream(finder.findInputStream("jump_sound.wav"));
    InputStream gameOverAudio = new BufferedInputStream(finder.findInputStream("game_over.wav"));

    AudioSprite jumpAudioSprite = null;
    AudioSprite gameOverAudioSprite = null;

    try
    {
      if (!audioIsPlaying)
      {
        AudioSprite audio = new AudioSprite(bgMusic, 0);
        audio.handleTick(0);
      }

      jumpAudioSprite = new AudioSprite(jumpAudio, 0);
      gameOverAudioSprite = new AudioSprite(gameOverAudio, 0);

    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (LineUnavailableException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (UnsupportedAudioFileException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // creates sun
    Content sun = tcFactory.createContent("sun.png", 4, false);
    sun.setLocation(10, 10);
    stage.add(sun);

    // creates clouds
    Content clouds1 = tcFactory.createContent(cloud, 4, false);
    clouds1.setLocation(426, 10);
    stage.add(clouds1);

    Content clouds2 = tcFactory.createContent(cloud, 4, false);
    clouds2.setLocation(852, 10);
    stage.add(clouds2);
    if (Menu.getCharSelect().equals(CHAR1))
    {
      character1 = br1;
      character2 = br2;
    }
    else if (Menu.getCharSelect().equals(CHAR2))
    {
      character1 = "workoutrobbie.png";
      character2 = "workoutrobbie2.png";
    }
    else if (Menu.getCharSelect().equals(CHAR3))
    {
      character1 = "simprobbie.png";
      character2 = "simprobbie2.png";
    }

    Content robbie1 = tcFactory.createContent(character1, 4, false);
    Content robbie2 = tcFactory.createContent(character2, 4, false);
    Content[] robbieContent = new Content[] {robbie1, robbie2};

    // flying bird
    Content[] birdContents = new Content[2];
    Character robbieCharacter = new Character(robbieContent, 640, 480, jumpAudioSprite,
        gameOverAudioSprite);
    for (int i = 0; i < birdContents.length; i++)
    {
      if (i == 0)
      {
        birdContents[i] = tcFactory.createContent("birb_up.png", 4, false);
      }
      else
      {
        birdContents[i] = tcFactory.createContent("birb_down.png", 4, false);

      }
    }
    Bird bird = new Bird(birdContents, WIDTH);
    stage.add(bird);

    Content groundContent = tcFactory.createContent("ground.png", 4, false);
    Ground ground = new Ground(groundContent, WIDTH, HEIGHT, 30.0);
    stage.add(ground);

    Content lam = tcFactory.createContent("lam.png", 4, false);
    Content molloy = tcFactory.createContent("molloy.png", 4, false);
    Content stewart = tcFactory.createContent("stewart.png", 4, false);
    Content johnson = tcFactory.createContent("johnson.png", 4, false);

    c.add(lam);
    c.add(molloy);
    c.add(stewart);
    c.add(johnson);

    Content[] obsList = new Content[] {lam, molloy, stewart, johnson};
    Obstacle obs = new Obstacle(obsList, WIDTH, HEIGHT, 5.0);

    robbieCharacter.addAntagonist(obs);
    robbieCharacter.addAntagonist(bird);

    stage.add(robbieCharacter);
    stage.addKeyListener(robbieCharacter);

    scoreContent.resetCurrScore();
    stage.add(scoreSprite);

    stage.add(obs);

    contentPane.add(stage.getView());
  }

  /**
   * The method used to stop the stage and restart the game.
   */
  public void stageStop()
  {
    scoreContent.setHighScore();
    stage.stop();
    audioIsPlaying = true;
    menu.setScore(Integer.toString(scoreContent.getHighScore()));
    instance.init();
    starter();
  }

  /**
   * Help start the menu and the program.
   */
  public static void starter()
  {
    menu.setVisible(true);
    menu.setAlwaysOnTop(true);
  }

  /**
   * Main method to start the game.
   * 
   * @param args
   *          command-line args
   */
  public static void main(final String[] args)
  {
    starter();
    instance = new RobbieRunner(null);
    invokeInEventDispatchThread(instance);
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // TODO Auto-generated method stub

  }

}
