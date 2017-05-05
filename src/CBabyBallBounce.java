
/**
Program: Assignment 2: Application – Baby Ball Bounce
Filename: CBabyBallBounce.java
@author: © Jan Jaworski (student id: 16443007)
Course: BEng Computing(Network Engineering) Year 1
Module: CSY1020 Problem Solving & Programming
Tutor: Gary Hill
@version: 1.0
Date: 30/04/2017
*/

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.*;

import com.apple.eawt.Application;

public class CBabyBallBounce extends JFrame implements ActionListener, ChangeListener, KeyListener
{
	// Window
	private Container window;

	// Menu
	private JMenuBar menuBar;
	private JMenu menuScenario, menuEdit, menuControls, menuHelp;
	private JMenuItem itemUp, itemDown, itemLeft, itemRight;
	private JMenuItem itemExit, itemHelp, itemAbout;
	private JMenuItem item2Players, item4Players, itemMulti;

	// Panels
	private JPanel panelCenter, panelBottom, panelRight;

	// Main panel
	private JButton buttonGrid[] = new JButton[208];
	private int ballPosition = 101; // Initial position of the ball
	// Initial position of the babies
	private int babyPosition[] = { 100, 107, 113, 126, 35, 44 };
	private boolean babyDirection[] = { false, false, false, false, false, false };
	private int players = 2; // Number of players

	// Right panel
	private JPanel panelFirst, panelSecond, panelThird, panelFourth, panelFifth;

	// Bottom panel
	private JButton buttonAct, buttonRun, buttonReset;
	private JLabel labelSpeed;
	private JSlider sliderSpeed;
	private boolean run = false;

	// First panel
	private JLabel labelTimer, labelColon1, labelColon2, labelScore, labelTeam;
	private JTextField textHours, textMinutes, textSeconds, textScoreL, textScoreR;
	private int scoreL = 0, scoreR = 0;

	// Second panel
	private JLabel labelOption, labelSquare, labelDirection;
	private JTextField textOption, textSquare, textDirection;

	// Third panel
	private JButton buttonUp, buttonDown, buttonLeft, buttonRight, buttonMiddle, buttonTopLeft, buttonTopRight,
			buttonBottomLeft, buttonBottomRight;

	// Fourth panel
	private JButton buttonCompass;

	// Fifth panel
	private JButton buttonTwoPlayers, buttonFourPlayers, buttonMulti, buttonExit;
	// Images
	private ImageIcon iconBall, iconRun, iconPause, iconAct, iconReset, iconNorth, iconSouth, iconWest, iconEast,
			iconWall, iconBaby1, iconBaby2, iconBaby3, iconBaby4, iconBaby5, iconBaby6, iconWhite, iconBallBricks;

	// Timer
	boolean started = false;
	private int ticks = 0;
	private Timer timer;
	private Timer timerMove;
	private DecimalFormat decimalFormat = new DecimalFormat("00");

	public static void main(String[] args)
	{
		CBabyBallBounce frame = new CBabyBallBounce();

		// source:
		// http://stackoverflow.com/questions/6006173/how-do-you-change-the-dock-icon-of-a-java-program
		// date: 17.02.2017
		if (isMac())
		{
			Application application = Application.getApplication();
			Image image = Toolkit.getDefaultToolkit()
					.createImage(CBabyBallBounce.class.getResource("images/greenfoot.png"));
			application.setDockIconImage(image);
		} else
		{
			// source:
			// http://stackoverflow.com/questions/15657569/how-to-set-icon-to-jframe
			// date: 09.02.2017
			frame.setIconImage(new ImageIcon("images/greenfoot.png").getImage());
		}

		frame.setSize(825, 585);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.createGUI();
		frame.setVisible(true);
	}

	private void createGUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		window = getContentPane();
		window.setLayout(new BorderLayout());
		setTitle("CBabyBallBounce - Baby Ball Bounce Application");
		window.setFocusable(true);
		window.addKeyListener(this);

		// menu
		menuBar();

		// add images
		addImages();

		// panel creation
		panelCenter();
		panelRight();
		panelBottom();
	}

	// source:
	// http://stackoverflow.com/questions/228477/how-do-i-programmatically-determine-operating-system-in-java
	// date: 17.02.2017
	private static boolean isMac()
	{
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("mac") >= 0);
	}

	private void menuBar()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// scenario
		menuScenario = new JMenu("Scenario");
		menuBar.add(menuScenario);

		itemExit = new JMenuItem("Exit");
		menuScenario.add(itemExit);
		itemExit.addActionListener(this);

		// edit
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		item2Players = new JMenuItem("2 Players");
		menuEdit.add(item2Players);
		item2Players.addActionListener(this);

		item4Players = new JMenuItem("4 Players");
		menuEdit.add(item4Players);
		item4Players.addActionListener(this);

		itemMulti = new JMenuItem("Multi");
		menuEdit.add(itemMulti);
		itemMulti.addActionListener(this);

		// controls
		menuControls = new JMenu("Controls");
		menuBar.add(menuControls);

		itemUp = new JMenuItem("Up");
		menuControls.add(itemUp);
		itemUp.addActionListener(this);

		itemDown = new JMenuItem("Down");
		menuControls.add(itemDown);
		itemDown.addActionListener(this);

		itemLeft = new JMenuItem("Left");
		menuControls.add(itemLeft);
		itemLeft.addActionListener(this);

		itemRight = new JMenuItem("Right");
		menuControls.add(itemRight);
		itemRight.addActionListener(this);

		// help
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		itemHelp = new JMenuItem("Help");
		menuHelp.add(itemHelp);
		itemHelp.addActionListener(this);

		itemAbout = new JMenuItem("About");
		menuHelp.add(itemAbout);
		itemAbout.addActionListener(this);
	}

	private void addImages()
	{
		try
		{
			iconBall = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/ball.png")));
			iconRun = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/run.png")));
			iconPause = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/pause.png")));
			iconAct = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/step.png")));
			iconReset = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/reset.png")));
			iconNorth = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/north.jpg")));
			iconSouth = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/south.jpg")));
			iconWest = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/west.jpg")));
			iconEast = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/east.jpg")));
			iconWall = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/bricks2.jpg")));
			iconBaby1 = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/baby1.png")));
			iconBaby2 = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/baby2.png")));
			iconBaby3 = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/baby3.png")));
			iconBaby4 = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/baby4.png")));
			iconBaby5 = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/baby5.png")));
			iconBaby6 = new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(CBabyBallBounce.class.getResource("images/baby6.png")));
			iconWhite = new ImageIcon(Toolkit.getDefaultToolkit()
					.createImage(CBabyBallBounce.class.getResource("images/white32x32.jpg")));
			iconBallBricks = new ImageIcon(Toolkit.getDefaultToolkit()
					.createImage(CBabyBallBounce.class.getResource("images/ballbricks.jpg")));
		} catch (Exception e)
		{
			System.err.println("Baby Icon ImageIcon " + e);
		}
	}

	// Main panel
	private void panelCenter()
	{
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(13, 16));
		panelCenter.setPreferredSize(new Dimension(625, 545));
		panelCenter.setBackground(Color.white);
		window.add(panelCenter, BorderLayout.WEST);

		// button grid creation
		for (int i = 0; i < 208; i++)
		{
			buttonGrid[i] = new JButton();
			buttonGrid[i].setBorderPainted(false);
			buttonGrid[i].setMargin(new Insets(0, 0, 0, 0));
			buttonGrid[i].setFocusable(false);
			panelCenter.add(buttonGrid[i]);
			if (i == babyPosition[0])
			{
				buttonGrid[i].setIcon(iconBaby1);
			} else if (i == babyPosition[1])
			{
				buttonGrid[i].setIcon(iconBaby2);
			} else if (i == ballPosition)
			{
				buttonGrid[i].setIcon(iconBall);
			} else if (i % 16 == 7 || i % 16 == 8)
			{
				buttonGrid[i].setIcon(iconWall);
			} else
			{
				buttonGrid[i].setIcon(iconWhite);
			}
		}
	}

	private void panelRight()
	{
		panelRight = new JPanel();
		panelRight.setLayout(new FlowLayout());
		panelRight.setPreferredSize(new Dimension(200, 545));
		window.add(panelRight, BorderLayout.EAST);

		panelFirst();
		panelSecond();
		panelThird();
		panelFourth();
		panelFifth();
	}

	private void panelBottom()
	{
		panelBottom = new JPanel();
		panelBottom.setPreferredSize(new Dimension(825, 40));
		window.add(panelBottom, BorderLayout.SOUTH);

		buttonAct = new JButton("Act", iconAct);
		panelBottom.add(buttonAct);
		buttonAct.addActionListener(this);
		buttonAct.setFocusable(false);

		buttonRun = new JButton("Run", iconRun);
		panelBottom.add(buttonRun);
		buttonRun.addActionListener(this);
		buttonRun.setFocusable(false);

		buttonReset = new JButton("Reset", iconReset);
		panelBottom.add(buttonReset);
		buttonReset.addActionListener(this);
		buttonReset.setFocusable(false);

		labelSpeed = new JLabel("Speed: ");
		panelBottom.add(labelSpeed);

		sliderSpeed = new JSlider(JSlider.HORIZONTAL, 1, 2000, 1000);
		sliderSpeed.setInverted(true);
		sliderSpeed.setMajorTickSpacing(100);
		sliderSpeed.setPaintTicks(true);
		sliderSpeed.addChangeListener(this);
		panelBottom.add(sliderSpeed);
		sliderSpeed.setFocusable(false);
	}

	// Right panel
	private void panelFirst()
	{
		panelFirst = new JPanel();
		panelFirst.setPreferredSize(new Dimension(200, 110));
		panelRight.add(panelFirst);

		labelTimer = new JLabel("          DIGITAL TIMER          ");
		panelFirst.add(labelTimer);

		textHours = new JTextField("00", 2);
		setDefaults(textHours);
		panelFirst.add(textHours);

		labelColon1 = new JLabel(" : ");
		panelFirst.add(labelColon1);

		textMinutes = new JTextField("00", 2);
		setDefaults(textMinutes);
		panelFirst.add(textMinutes);

		labelColon2 = new JLabel(" : ");
		panelFirst.add(labelColon2);

		textSeconds = new JTextField("00", 2);
		setDefaults(textSeconds);
		panelFirst.add(textSeconds);

		labelScore = new JLabel("               SCORE               ");
		panelFirst.add(labelScore);

		textScoreL = new JTextField(decimalFormat.format(scoreL), 2);
		setDefaults(textScoreL);
		panelFirst.add(textScoreL);

		labelTeam = new JLabel("<L:R>");
		panelFirst.add(labelTeam);

		textScoreR = new JTextField(decimalFormat.format(scoreR), 2);
		setDefaults(textScoreR);
		panelFirst.add(textScoreR);
	}

	private void panelSecond()
	{
		panelSecond = new JPanel();
		panelSecond.setLayout(new GridLayout(3, 2));
		panelSecond.setPreferredSize(new Dimension(200, 100));
		panelRight.add(panelSecond);

		labelOption = new JLabel("  Option:");
		panelSecond.add(labelOption);

		textOption = new JTextField("2 players");
		setDefaults(textOption);
		panelSecond.add(textOption);

		labelSquare = new JLabel("  Square:");
		panelSecond.add(labelSquare);

		textSquare = new JTextField("" + ballPosition);
		setDefaults(textSquare);
		panelSecond.add(textSquare);

		labelDirection = new JLabel("  Direction:");
		panelSecond.add(labelDirection);

		textDirection = new JTextField("East");
		setDefaults(textDirection);
		panelSecond.add(textDirection);
	}

	private void panelThird()
	{
		panelThird = new JPanel();
		panelThird.setLayout(new GridLayout(3, 3));
		panelThird.setPreferredSize(new Dimension(200, 100));
		panelRight.add(panelThird);

		buttonTopLeft = new JButton();
		panelThird.add(buttonTopLeft);
		buttonTopLeft.addActionListener(this);
		buttonTopLeft.setFocusable(false);

		buttonUp = new JButton("^");
		panelThird.add(buttonUp);
		buttonUp.addActionListener(this);
		buttonUp.setFocusable(false);

		buttonTopRight = new JButton();
		panelThird.add(buttonTopRight);
		buttonTopRight.addActionListener(this);
		buttonTopRight.setFocusable(false);

		buttonLeft = new JButton("<");
		panelThird.add(buttonLeft);
		buttonLeft.addActionListener(this);
		buttonLeft.setFocusable(false);

		buttonMiddle = new JButton(iconBall);
		panelThird.add(buttonMiddle);
		buttonMiddle.setFocusable(false);

		buttonRight = new JButton(">");
		panelThird.add(buttonRight);
		buttonRight.addActionListener(this);
		buttonRight.setFocusable(false);

		buttonBottomLeft = new JButton();
		panelThird.add(buttonBottomLeft);
		buttonBottomLeft.addActionListener(this);
		buttonBottomLeft.setFocusable(false);

		buttonDown = new JButton("v");
		panelThird.add(buttonDown);
		buttonDown.addActionListener(this);
		buttonDown.setFocusable(false);

		buttonBottomRight = new JButton();
		panelThird.add(buttonBottomRight);
		buttonBottomRight.addActionListener(this);
		buttonBottomRight.setFocusable(false);
	}

	private void panelFourth()
	{
		panelFourth = new JPanel();
		panelFourth.setLayout(new FlowLayout());
		panelFourth.setPreferredSize(new Dimension(200, 100));
		panelRight.add(panelFourth);

		buttonCompass = new JButton(iconEast);
		panelFourth.add(buttonCompass);
		buttonCompass.setFocusable(false);
	}

	private void panelFifth()
	{
		panelFifth = new JPanel();
		panelFifth.setLayout(new GridLayout(2, 2));
		panelFifth.setPreferredSize(new Dimension(200, 65));
		panelRight.add(panelFifth);

		buttonTwoPlayers = new JButton("2 Players");
		panelFifth.add(buttonTwoPlayers);
		buttonTwoPlayers.addActionListener(this);
		buttonTwoPlayers.setFocusable(false);

		buttonFourPlayers = new JButton("4 Players");
		panelFifth.add(buttonFourPlayers);
		buttonFourPlayers.addActionListener(this);
		buttonFourPlayers.setFocusable(false);

		buttonMulti = new JButton("Multi");
		panelFifth.add(buttonMulti);
		buttonMulti.addActionListener(this);
		buttonMulti.setFocusable(false);

		buttonExit = new JButton("Exit");
		panelFifth.add(buttonExit);
		buttonExit.addActionListener(this);
	}

	// Actions
	public void buttonAct()
	{
		if (players > 2)
		{
			babyMove();
		}

		if (textDirection.getText().equals("North"))
		{
			buttonUp();
		} else if (textDirection.getText().equals("South"))
		{
			buttonDown();
		} else if (textDirection.getText().equals("West"))
		{
			buttonLeft();
		} else if (textDirection.getText().equals("East"))
		{
			buttonRight();
		} else if (textDirection.getText().equals("Northwest"))
		{
			topLeft();
		} else if (textDirection.getText().equals("Northeast"))
		{
			topRight();
		} else if (textDirection.getText().equals("Southwest"))
		{
			bottomLeft();
		} else if (textDirection.getText().equals("Southeast"))
		{
			bottomRight();
		}
	}

	public void buttonRun()
	{
		if (run)
		{
			buttonRun.setIcon(iconRun);
			buttonRun.setText("Run");
			timer.stop();
			timerMove.stop();
			run = false;
			buttonAct.setEnabled(true);
			started = false;
		} else
		{
			buttonRun.setIcon(iconPause);
			buttonRun.setText("Pause");
			timer = new Timer(1000, this);
			timer.start();
			timerMove = new Timer(sliderSpeed.getValue(), this);
			timerMove.start();
			run = true;
			buttonAct.setEnabled(false);
			started = true;
		}
	}

	public void buttonReset()
	{
		// reset timer
		ticks = 0;
		textHours.setText("00");
		textMinutes.setText("00");
		textSeconds.setText("00");

		// stop game if it is running
		if (run)
		{
			buttonRun.setIcon(iconRun);
			buttonRun.setText("Run");
			timer.stop();
			timerMove.stop();
			run = false;
			buttonAct.setEnabled(true);
			started = false;
		}

		// reset ball position
		if (ballPosition % 16 == 7 || ballPosition % 16 == 8)
		{
			buttonGrid[ballPosition].setIcon(iconWall);
		} else
		{
			buttonGrid[ballPosition].setIcon(iconWhite);
		}
		ballPosition = 101;
		textSquare.setText("" + ballPosition);
		buttonGrid[ballPosition].setIcon(iconBall);

		// reset score
		scoreL = 0;
		scoreR = 0;
		textScoreL.setText(decimalFormat.format(scoreL));
		textScoreR.setText(decimalFormat.format(scoreR));

		// reset players position
		int initialPosition[] = { 100, 107, 113, 126, 35, 44 };
		for (int i = 0; i < players; i++)
		{
			buttonGrid[babyPosition[i]].setIcon(iconWhite);
			babyPosition[i] = initialPosition[i];
			if (i % 2 == 0)
			{
				buttonGrid[babyPosition[i]].setIcon(iconBaby1);
			} else
			{
				buttonGrid[babyPosition[i]].setIcon(iconBaby2);
			}
		}
	}

	// Movement
	public void buttonUp()
	{
		buttonCompass.setIcon(iconNorth);
		textDirection.setText("North");
		boolean move = true;

		if (ballPosition - 16 < 0)
		{
			// textDirection.setText("South");
			changeDirection();
		} else
		{
			for (int i = 0; i < players; i++)
			{
				if (ballPosition - 16 == babyPosition[i])
				{
					if (players == 2)
					{
						textDirection.setText("South");
					} else
					{
						changeDirection();
					}
					move = false;
				}
			}
			if (move)
			{
				buttonGrid[ballPosition].setIcon(iconWhite);
				buttonGrid[ballPosition - 16].setIcon(iconBall);
				ballPosition -= 16;
				textSquare.setText("" + ballPosition);
			}
			if ((ballPosition + 16) % 16 == 7 || (ballPosition + 16) % 16 == 8)
			{
				buttonGrid[ballPosition + 16].setIcon(iconWall);
			}
			if ((ballPosition) % 16 == 7 || (ballPosition) % 16 == 8)
			{
				buttonGrid[ballPosition].setIcon(iconBallBricks);
			}
		}
	}

	public void buttonDown()
	{
		buttonCompass.setIcon(iconSouth);
		textDirection.setText("South");
		boolean move = true;

		if (ballPosition + 16 > 207)
		{
			// textDirection.setText("North");
			changeDirection();
		} else
		{
			for (int i = 0; i < players; i++)
			{
				if (ballPosition + 16 == babyPosition[i])
				{
					if (players == 2)
					{
						textDirection.setText("North");
					} else
					{
						changeDirection();
					}
					move = false;
				}
			}
			if (move)
			{
				buttonGrid[ballPosition].setIcon(iconWhite);
				buttonGrid[ballPosition + 16].setIcon(iconBall);
				ballPosition += 16;
				textSquare.setText("" + ballPosition);
			}
			if ((ballPosition - 16) % 16 == 7 || (ballPosition - 16) % 16 == 8)
			{
				buttonGrid[ballPosition - 16].setIcon(iconWall);
			}
			if ((ballPosition) % 16 == 7 || (ballPosition) % 16 == 8)
			{
				buttonGrid[ballPosition].setIcon(iconBallBricks);
			}
		}
	}

	public void buttonLeft()
	{
		buttonCompass.setIcon(iconWest);
		textDirection.setText("West");
		boolean move = true;

		if (ballPosition % 16 == 0)
		{
			buttonGrid[ballPosition].setIcon(iconWhite);
			ballPosition = 101;
			buttonGrid[ballPosition].setIcon(iconBall);
			scoreR++;
			textScoreR.setText("" + decimalFormat.format(scoreR));
			textDirection.setText("East");
		} else
		{
			for (int i = 0; i < players; i++)
			{
				if (ballPosition - 1 == babyPosition[i])
				{
					if (players == 2)
					{
						textDirection.setText("East");
					} else
					{
						changeDirection();
					}
					move = false;
				}
			}
			if (move)
			{
				buttonGrid[ballPosition].setIcon(iconWhite);
				buttonGrid[ballPosition - 1].setIcon(iconBall);
				ballPosition--;
				textSquare.setText("" + ballPosition);
			}
			if ((ballPosition + 1) % 16 == 7 || (ballPosition + 1) % 16 == 8)
			{
				buttonGrid[ballPosition + 1].setIcon(iconWall);
			}
			if ((ballPosition) % 16 == 7 || (ballPosition) % 16 == 8)
			{
				buttonGrid[ballPosition].setIcon(iconBallBricks);
			}
		}
	}

	public void buttonRight()
	{
		buttonCompass.setIcon(iconEast);
		textDirection.setText("East");
		boolean move = true;

		if ((ballPosition + 1) % 16 == 0)
		{
			buttonGrid[ballPosition].setIcon(iconWhite);
			ballPosition = 106;
			buttonGrid[ballPosition].setIcon(iconBall);
			scoreL++;
			textScoreL.setText("" + decimalFormat.format(scoreL));
			textDirection.setText("West");
		} else
		{
			for (int i = 0; i < players; i++)
			{
				if (ballPosition + 1 == babyPosition[i])
				{
					if (players == 2)
					{
						textDirection.setText("West");
					} else
					{
						changeDirection();
					}
					move = false;
				}
			}
			if (move)
			{
				buttonGrid[ballPosition].setIcon(iconWhite);
				buttonGrid[ballPosition + 1].setIcon(iconBall);
				ballPosition++;
				textSquare.setText("" + ballPosition);
			}
			if ((ballPosition - 1) % 16 == 7 || (ballPosition - 1) % 16 == 8)
			{
				buttonGrid[ballPosition - 1].setIcon(iconWall);
			}
			if ((ballPosition) % 16 == 7 || (ballPosition) % 16 == 8)
			{
				buttonGrid[ballPosition].setIcon(iconBallBricks);
			}
		}
	}

	public void topRight()
	{
		buttonRight();
		buttonUp();
		//textDirection.setText("Northeast");
	}

	public void topLeft()
	{
		buttonLeft();
		buttonUp();
		//textDirection.setText("Northwest");
	}

	public void bottomRight()
	{
		buttonRight();
		buttonDown();
		//textDirection.setText("Southeast");
	}

	public void bottomLeft()
	{
		buttonLeft();
		buttonDown();
		//textDirection.setText("Southwest");
	}

	// Game modes
	public void buttonTwoPlayers()
	{
		buttonReset();
		players = 2;
		textOption.setText("2 players");
		buttonGrid[babyPosition[2]].setIcon(iconWhite);
		buttonGrid[babyPosition[3]].setIcon(iconWhite);
		buttonGrid[babyPosition[4]].setIcon(iconWhite);
		buttonGrid[babyPosition[5]].setIcon(iconWhite);
	}

	public void buttonFourPlayers()
	{
		buttonReset();
		players = 4;
		textOption.setText("4 players");
		buttonGrid[babyPosition[2]].setIcon(iconBaby3);
		buttonGrid[babyPosition[3]].setIcon(iconBaby4);
		buttonGrid[babyPosition[4]].setIcon(iconWhite);
		buttonGrid[babyPosition[5]].setIcon(iconWhite);
	}

	public void buttonMulti()
	{
		buttonReset();
		players = 6;
		textOption.setText("Multi");
		buttonGrid[babyPosition[2]].setIcon(iconBaby3);
		buttonGrid[babyPosition[3]].setIcon(iconBaby4);
		buttonGrid[babyPosition[4]].setIcon(iconBaby5);
		buttonGrid[babyPosition[5]].setIcon(iconBaby6);
	}

	public void buttonExit()
	{
		System.exit(0);
	}

	// Information in menuBar
	public void help()
	{
		JOptionPane.showMessageDialog(window, "Bob", "Help", JOptionPane.INFORMATION_MESSAGE);
	}

	public void about()
	{
		JOptionPane.showMessageDialog(window, "Created by Jan Jaworski\n" + "Tutor: Gary Hill \n" + "2017", "About",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// Timers
	private void timer()
	{
		// buttonAct();
		int seconds = ticks % 60;
		int minutes = (ticks / 60) % 60;
		int hours = ticks / 3600;
		textHours.setText(decimalFormat.format(hours));
		textMinutes.setText(decimalFormat.format(minutes));
		textSeconds.setText(decimalFormat.format(seconds));
		ticks++;
	}

	private void timerMove()
	{
		buttonAct();
	}

	// Baby movement
	private void babyMove()
	{
		for (int i = 0; i < players; i++)
		{
			Icon temp = buttonGrid[babyPosition[i]].getIcon();
			int random = (int) Math.floor(Math.random() * 2);
			if (random == 0)
			{
				if (ballPosition < babyPosition[i])
				{
					if (babyPosition[i] - 16 < 0)
					{
						babyDirection[i] = true;
					} else
					{
						babyDirection[i] = false;
					}
				}
				if (ballPosition > babyPosition[i])
				{
					if (babyPosition[i] + 16 > 207)
					{
						babyDirection[i] = false;
					} else
					{
						babyDirection[i] = true;
					}
				}

				if (babyDirection[i])
				{
					if (babyPosition[i] + 16 != ballPosition)
					{
						babyPosition[i] += 16;
						buttonGrid[babyPosition[i] - 16].setIcon(iconWhite);
						buttonGrid[babyPosition[i]].setIcon(temp);
					}
				} else
				{
					if (babyPosition[i] - 16 != ballPosition)
					{
						babyPosition[i] -= 16;
						buttonGrid[babyPosition[i] + 16].setIcon(iconWhite);
						buttonGrid[babyPosition[i]].setIcon(temp);
					}
				}
			}
		}
	}

	// Random ball bounce
	private void changeDirection()
	{
		int random = (int) Math.floor(Math.random() * 8);
		switch (random)
		{
		case 0:
			textDirection.setText("East");
			break;
		case 1:
			textDirection.setText("West");
			break;
		case 2:
			textDirection.setText("North");
			break;
		case 3:
			textDirection.setText("South");
			break;
		case 4:
			textDirection.setText("Northwest");
			break;
		case 5:
			textDirection.setText("Northeast");
			break;
		case 6:
			textDirection.setText("Southwest");
			break;
		case 7:
			textDirection.setText("Southeast");
			break;
		default:
			break;
		}
	}
 
	public void actionPerformed(ActionEvent event)
	{
		Object source = event.getSource();
		if (source == buttonAct)
		{
			buttonAct();
		} else if (source == buttonRun)
		{
			buttonRun();
		} else if (source == buttonReset)
		{
			buttonReset();
		} else if (source == buttonUp || source == itemUp)
		{
			buttonUp();
		} else if (source == buttonDown || source == itemDown)
		{
			buttonDown();
		} else if (source == buttonLeft || source == itemLeft)
		{
			buttonLeft();
		} else if (source == buttonRight || source == itemRight)
		{
			buttonRight();
		} else if (source == buttonTopLeft)
		{
			topLeft();
		} else if (source == buttonTopRight)
		{
			topRight();
		} else if (source == buttonBottomLeft)
		{
			bottomLeft();
		} else if (source == buttonBottomRight)
		{
			bottomRight();
		} else if (source == buttonTwoPlayers || source == item2Players)
		{
			buttonTwoPlayers();
		} else if (source == buttonFourPlayers || source == item4Players)
		{
			buttonFourPlayers();
		} else if (source == buttonMulti || source == itemMulti)
		{
			buttonMulti();
		} else if (source == buttonExit || source == itemExit)
		{
			buttonExit();
		} else if (source == itemHelp)
		{
			help();
		} else if (source == itemAbout)
		{
			about();
		} else if (source == timer)
		{
			timer();
		} else if (source == timerMove)
		{
			timerMove();
		}
	}

	public void stateChanged(ChangeEvent e)
	{
		// System.out.println(timer.start());
		if (started == true)
		{
			timerMove.stop();
			timerMove = new Timer(sliderSpeed.getValue(), this);
			timerMove.start();
		}
	}

	public void setDefaults(JTextField textField)
	{
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);
		textField.setFocusable(false);
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.WHITE);
	}

	// source:
	// https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
	// date: 27.02.2017
	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int source = e.getKeyCode();
		if (source == 37)
		{
			buttonLeft();
		} else if (source == 38)
		{
			buttonUp();
		} else if (source == 39)
		{
			buttonRight();
		} else if (source == 40)
		{
			buttonDown();
		} else if (source == 32)
		{
			buttonRun();
		}
		//System.out.println(source);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}
}