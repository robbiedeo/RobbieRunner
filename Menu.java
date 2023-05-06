package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.ResourceFinder;
import resources.Marker;
import runner.RobbieRunner;
import visual.statik.sampled.ImageFactory;

/**
 * The menu class.
 * 
 * @author Robbie Deonarain
 *
 */
public class Menu extends JFrame implements ActionListener
{

  protected static final String CHAR1 = "BANANA ROBBIE";
  protected static final String CHAR2 = "WORKOUT ROBBIE";
  protected static final String CHAR3 = "SIMP ROBBIE";
  protected static final String EXIT = "EXIT";
  /**
   * necessary.
   */
  private static final long serialVersionUID = 1L;
  private static String robbierunner = "ROBBIE RUNNER";
  private static String title = robbierunner;
  private static String charSelect = "";

  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

  private boolean startGame = false;
  private JLabel scoreLabel;

  /**
   * The menu constructor.
   */
  public Menu()
  {
    super(title);
    ResourceFinder rf = ResourceFinder.createInstance(new Marker());
    // create panels
    JPanel mainPanel = new JPanel();
    JPanel titlePanel = new JPanel();
    JPanel scorePanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    // set panel layouts
    mainPanel.setLayout(new BorderLayout());
    titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    // title label
    JLabel titleLabel = new JLabel(robbierunner);
    titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
    titlePanel.add(titleLabel);

    // highscore label
    scoreLabel = new JLabel("HIGHSCORE:  " + 0);
    scoreLabel.setLocation(100, 100);
    scoreLabel.setSize(200, 100);
    scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
    scorePanel.add(scoreLabel);

    // char1 button
    JButton char1 = new JButton(CHAR1);

    ImageFactory image = new ImageFactory(rf);
    Image br = image.createBufferedImage("bananarobbie1.png", 4);
    ImageIcon c1 = new ImageIcon(br);

    Image wr = image.createBufferedImage("workoutrobbie.png", 4);
    ImageIcon c2 = new ImageIcon(wr);

    Image sr = image.createBufferedImage("simprobbie.png", 4);
    ImageIcon c3 = new ImageIcon(sr);

    char1.setBackground(new Color(144, 238, 144));
    char1.setIcon(c1);
    char1.addActionListener(this);
    // char1.setSize(new Dimension(200, 80));
    char1.setPreferredSize(new Dimension(250, 130));
    buttonPanel.add(char1);

    // char2 button
    JButton char2 = new JButton(CHAR2);
    char2.setBackground(new Color(144, 238, 144));
    char2.setIcon(c2);
    char2.addActionListener(this);
    // char2.setSize(new Dimension(200, 80));
    char2.setPreferredSize(new Dimension(250, 130));
    buttonPanel.add(char2);

    // char3 button
    JButton char3 = new JButton(CHAR3);
    char3.setBackground(new Color(144, 238, 144));
    char3.setIcon(c3);
    char3.addActionListener(this);
    // char3.setSize(new Dimension(200, 80));
    char3.setPreferredSize(new Dimension(250, 130));
    buttonPanel.add(char3);

    // exit button
    JButton exit = new JButton(EXIT);
    exit.setBackground(new Color(255, 114, 118));
    exit.addActionListener(this);
    exit.setSize(new Dimension(200, 80));
    buttonPanel.add(exit);

    mainPanel.add(titlePanel, BorderLayout.NORTH);
    mainPanel.add(scorePanel, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);

    this.add(mainPanel);
    this.setSize(new Dimension(1280, 360));
    // mainPanel.setBackground(new Color(173, 216, 230));
    scorePanel.setBackground(new Color(173, 216, 230));
    buttonPanel.setBackground(new Color(173, 216, 230));
    titlePanel.setBackground(new Color(173, 216, 230));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /**
   * Get the char select.
   * 
   * @return the char
   */
  public static String getCharSelect()
  {
    return charSelect;
  }

  /**
   * set the char selection.
   * 
   * @param charSel
   *          the char to set it to.
   */
  public static void setCharSelect(final String charSel)
  {
    charSelect = charSel;
  }

  /**
   * Start the game.
   */
  public void handleStart()
  {
    RobbieRunner.stage.start();
    this.startGame = true;
    this.setVisible(false);
  }

  /**
   * Get the started game.
   * 
   * @return the started game.
   */
  public boolean getStartGame()
  {
    return this.startGame;
  }

  /**
   * set the game start.
   * 
   * @return the started game.
   */
  public boolean setStartGame()
  {
    this.startGame = false;
    return this.startGame;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    // TODO Auto-generated method stub
    String command = e.getActionCommand();
    if (command.equals(CHAR1))
    {
      setCharSelect(CHAR1);
      RobbieRunner.audioIsPlaying = true;
      RobbieRunner.instance.init();
      handleStart();
    }
    else if (command.equals(CHAR2))
    {
      setCharSelect(CHAR2);
      RobbieRunner.audioIsPlaying = true;
      RobbieRunner.instance.init();
      handleStart();
    }
    else if (command.equals(CHAR3))
    {
      setCharSelect(CHAR3);
      RobbieRunner.audioIsPlaying = true;
      RobbieRunner.instance.init();
      handleStart();
    }
    else if (command.equals(EXIT))
    {
      System.exit(0);
    }

  }

  /**
   * set the high score.
   * 
   * @param score
   */
  public void setScore(final String score)
  {
    scoreLabel.setText("Highscore:  " + score);
  }

}
