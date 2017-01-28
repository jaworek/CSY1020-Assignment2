import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CBabyBallBounce extends JFrame implements ActionListener
{
	// menu
	private JMenuBar menuBar;
	private JMenu scenarioMenu, editMenu, controlsMenu, helpMenu;
	private JMenuItem exitItem, helpTopicItem, aboutItem;

	// panels
	private JPanel centerPnl, bottomPnl, rightPnl;

	// right panel
	private JPanel firstPnl, secondPnl, thirdPnl, fourthPnl, fifthPnl;

	// bottom panel
	private JButton actBtn, runBtn, resetBtn;
	private JLabel speedLbl;
	private JSlider speedSld;

	// first panel
	private JLabel timerLbl, colon1Lbl, colon2Lbl, TxtScoreLbl, teamLbl;
	private JTextField TxtHours, TxtMinutes, TxtSeconds, TxtScoreL, TxtScoreR;

	// second panel
	private JLabel optionLbl, squareLbl, directionLbl;
	private JTextField option, square, direction;

	// third panel
	private JButton upBtn, downBtn, leftBtn, rightBtn, middleBtn, blank1Btn, blank2Btn, blank3Btn, blank4Btn;

	// fourth panel
	private JButton compassBtn;

	// fifth panel
	private JButton twoPlayerBtn, fourPlayerBtn, multiBtn, exitBtn;
	
	//images
	private ImageIcon ballIcon, runIcon, actIcon, resetIcon;

	private Container window;

	public static void main(String[] args)
	{
		CBabyBallBounce frame = new CBabyBallBounce();
		frame.setSize(825, 585);
		frame.createGUI();
		frame.setVisible(true);
	}

	private void createGUI()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		window = getContentPane();
		window.setLayout(new BorderLayout());
		setTitle("CBabyBallBounce - Baby Ball Bounce Application");

		// menu
		menuBar();

		// panel creation
		centerPnl();
		rightPnl();
		bottomPnl();
	}

	private void menuBar()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//scenario
		scenarioMenu = new JMenu("Scenario");
		menuBar.add(scenarioMenu);

		exitItem = new JMenuItem("Exit");
		scenarioMenu.add(exitItem);
		
		//edit
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		//controls
		controlsMenu = new JMenu("Controls");
		menuBar.add(controlsMenu);
		
		//help
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		helpTopicItem = new JMenuItem("Help Topic");
		helpMenu.add(helpTopicItem);
		aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
	}

	private void centerPnl()
	{
		centerPnl = new JPanel();
		// centerPnl.setPreferredSize(new Dimension(600, 485));
		centerPnl.setBackground(Color.white);
		window.add(centerPnl, BorderLayout.CENTER);
	}

	private void rightPnl()
	{
		rightPnl = new JPanel();
		rightPnl.setLayout(new GridLayout(5, 1));
		// rightPnl.setPreferredSize(new Dimension(200, 485));
		rightPnl.setBackground(Color.red);
		window.add(rightPnl, BorderLayout.EAST);

		firstPnl();
		secondPnl();
		thirdPnl();
		fourthPnl();
		fifthPnl();
	}

	private void bottomPnl()
	{
		bottomPnl = new JPanel();
		// bottomPnl.setPreferredSize(new Dimension(825, 50));
		bottomPnl.setBackground(Color.gray);
		window.add(bottomPnl, BorderLayout.SOUTH);

		actIcon = new ImageIcon("images/step.png");
		actBtn = new JButton("Act", actIcon);
		bottomPnl.add(actBtn);
		actBtn.addActionListener(this);

		runIcon = new ImageIcon("images/run.png");
		runBtn = new JButton("Run", runIcon);
		bottomPnl.add(runBtn);
		runBtn.addActionListener(this);

		resetIcon = new ImageIcon("images/reset.png");
		resetBtn = new JButton("Reset", resetIcon);
		bottomPnl.add(resetBtn);
		resetBtn.addActionListener(this);

		speedLbl = new JLabel("Speed: ");
		bottomPnl.add(speedLbl);

		speedSld = new JSlider();
		bottomPnl.add(speedSld);
	}

	private void firstPnl()
	{
		firstPnl = new JPanel();
		firstPnl.setPreferredSize(new Dimension(200, 90));
		firstPnl.setBackground(Color.gray);
		rightPnl.add(firstPnl);

		timerLbl = new JLabel("DIGITAL TIMER");
		firstPnl.add(timerLbl);

		TxtHours = new JTextField("hh");
		firstPnl.add(TxtHours);

		colon1Lbl = new JLabel(" : ");
		firstPnl.add(colon1Lbl);

		TxtMinutes = new JTextField("mm");
		firstPnl.add(TxtMinutes);

		colon2Lbl = new JLabel(" : ");
		firstPnl.add(colon2Lbl);

		TxtSeconds = new JTextField("ss");
		firstPnl.add(TxtSeconds);

		TxtScoreLbl = new JLabel("SCORE");
		firstPnl.add(TxtScoreLbl);

		TxtScoreL = new JTextField();
		firstPnl.add(TxtScoreL);

		teamLbl = new JLabel("<L:R>");
		firstPnl.add(teamLbl);

		TxtScoreR = new JTextField();
		firstPnl.add(TxtScoreR);
	}

	private void secondPnl()
	{
		secondPnl = new JPanel();
		secondPnl.setLayout(new GridLayout(3, 2));
		secondPnl.setPreferredSize(new Dimension(200, 90));
		secondPnl.setBackground(Color.blue);
		rightPnl.add(secondPnl);

		optionLbl = new JLabel(" Option:");
		secondPnl.add(optionLbl);

		option = new JTextField();
		secondPnl.add(option);

		squareLbl = new JLabel(" Square:");
		secondPnl.add(squareLbl);

		square = new JTextField();
		secondPnl.add(square);

		directionLbl = new JLabel(" Direction:");
		secondPnl.add(directionLbl);

		direction = new JTextField();
		secondPnl.add(direction);
	}

	private void thirdPnl()
	{
		thirdPnl = new JPanel();
		thirdPnl.setLayout(new GridLayout(3, 3));
		thirdPnl.setPreferredSize(new Dimension(200, 90));
		thirdPnl.setBackground(Color.yellow);
		rightPnl.add(thirdPnl);

		blank1Btn = new JButton(" ");
		thirdPnl.add(blank1Btn);
		blank1Btn.addActionListener(this);
		blank1Btn.setEnabled(false);

		upBtn = new JButton("^");
		thirdPnl.add(upBtn);
		upBtn.addActionListener(this);

		blank2Btn = new JButton(" ");
		thirdPnl.add(blank2Btn);
		blank2Btn.addActionListener(this);
		blank2Btn.setEnabled(false);

		leftBtn = new JButton("<");
		thirdPnl.add(leftBtn);
		leftBtn.addActionListener(this);

		ballIcon = new ImageIcon("images/ball.png");
		middleBtn = new JButton(ballIcon);
		thirdPnl.add(middleBtn);
		middleBtn.addActionListener(this);

		rightBtn = new JButton(">");
		thirdPnl.add(rightBtn);
		rightBtn.addActionListener(this);

		blank3Btn = new JButton(" ");
		thirdPnl.add(blank3Btn);
		blank3Btn.addActionListener(this);
		blank3Btn.setEnabled(false);

		downBtn = new JButton("v");
		thirdPnl.add(downBtn);
		downBtn.addActionListener(this);

		blank4Btn = new JButton(" ");
		thirdPnl.add(blank4Btn);
		blank4Btn.addActionListener(this);
		blank4Btn.setEnabled(false);
	}

	private void fourthPnl()
	{
		fourthPnl = new JPanel();
		fourthPnl.setLayout(new GridLayout(1, 1));
		fourthPnl.setPreferredSize(new Dimension(200, 90));
		fourthPnl.setBackground(Color.green);
		rightPnl.add(fourthPnl);

		compassBtn = new JButton("Compass");
		fourthPnl.add(compassBtn);
		compassBtn.addActionListener(this);
	}

	private void fifthPnl()
	{
		fifthPnl = new JPanel();
		fifthPnl.setLayout(new GridLayout(2, 2));
		fifthPnl.setPreferredSize(new Dimension(200, 90));
		fifthPnl.setBackground(Color.orange);
		rightPnl.add(fifthPnl);

		twoPlayerBtn = new JButton("2 Player");
		fifthPnl.add(twoPlayerBtn);
		twoPlayerBtn.addActionListener(this);

		fourPlayerBtn = new JButton("4 Player");
		fifthPnl.add(fourPlayerBtn);
		fourPlayerBtn.addActionListener(this);

		multiBtn = new JButton("Multi");
		fifthPnl.add(multiBtn);
		multiBtn.addActionListener(this);

		exitBtn = new JButton("Exit");
		fifthPnl.add(exitBtn);
		exitBtn.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent event)
	{
	}
}