import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CBabyBallBounce extends JFrame implements ActionListener {

	// panels
	private JPanel centerPnl, bottomPnl, rightPnl;

	// right panel
	private JPanel firstPnl, secondPnl, thirdPnl, fourthPnl, fifthPnl;

	// bottom panel
	private JButton actBtn, runBtn, resetBtn;
	private JLabel speedLbl;
	private JSlider speedSld;

	// first panel
	private JLabel timerLbl, colon1Lbl, colon2Lbl, scoreLbl, teamLbl;
	private JTextField hours, minutes, seconds, scoreL, scoreR;

	// second panel
	private JLabel optionLbl, squareLbl, directionLbl;
	private JTextField option, square, direction;

	// third panel
	private JButton upBtn, downBtn, leftBtn, rightBtn, middleBtn, blank1Btn, blank2Btn, blank3Btn, blank4Btn;

	// fourth panel
	private JButton compassBtn;

	// fifth panel
	private JButton twoPlayerBtn, fourPlayerBtn, multiBtn, exitBtn;

	public static void main(String[] args) {
		CBabyBallBounce frame = new CBabyBallBounce();
		frame.setSize(825, 585);
		frame.createGUI();
		frame.setVisible(true);
	}

	private void createGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		window.setLayout(new FlowLayout());

		// panel creation
		centerPnl = new JPanel();
		centerPnl.setPreferredSize(new Dimension(600, 485));
		centerPnl.setBackground(Color.white);
		window.add(centerPnl);

		rightPnl = new JPanel();
		rightPnl.setPreferredSize(new Dimension(200, 485));
		rightPnl.setBackground(Color.red);
		window.add(rightPnl);

		bottomPnl = new JPanel();
		bottomPnl.setPreferredSize(new Dimension(825, 100));
		bottomPnl.setBackground(Color.gray);
		window.add(bottomPnl);

		// right panel
		firstPnl = new JPanel();
		firstPnl.setPreferredSize(new Dimension(200, 90));
		firstPnl.setBackground(Color.gray);
		rightPnl.add(firstPnl);

		secondPnl = new JPanel();
		secondPnl.setPreferredSize(new Dimension(200, 90));
		secondPnl.setBackground(Color.blue);
		rightPnl.add(secondPnl);

		thirdPnl = new JPanel();
		thirdPnl.setPreferredSize(new Dimension(200, 90));
		thirdPnl.setBackground(Color.yellow);
		rightPnl.add(thirdPnl);

		fourthPnl = new JPanel();
		fourthPnl.setPreferredSize(new Dimension(200, 90));
		fourthPnl.setBackground(Color.green);
		rightPnl.add(fourthPnl);

		fifthPnl = new JPanel();
		fifthPnl.setPreferredSize(new Dimension(200, 90));
		fifthPnl.setBackground(Color.orange);
		rightPnl.add(fifthPnl);

		// first panel
		colon1Lbl = new JLabel(" : ");
		colon2Lbl = new JLabel(" : ");

		timerLbl = new JLabel("DIGITAL TIMER");
		firstPnl.add(timerLbl);

		hours = new JTextField("hh");
		firstPnl.add(hours);

		firstPnl.add(colon1Lbl);

		minutes = new JTextField("mm");
		firstPnl.add(minutes);

		firstPnl.add(colon2Lbl);

		seconds = new JTextField("ss");
		firstPnl.add(seconds);

		scoreLbl = new JLabel("SCORE");
		firstPnl.add(scoreLbl);

		scoreL = new JTextField();
		firstPnl.add(scoreL);

		teamLbl = new JLabel("<L:R>");
		firstPnl.add(teamLbl);

		scoreR = new JTextField();
		firstPnl.add(scoreR);

		// second panel
		optionLbl = new JLabel("Option:");
		secondPnl.add(optionLbl);

		option = new JTextField();
		secondPnl.add(option);

		squareLbl = new JLabel("Square:");
		secondPnl.add(squareLbl);

		square = new JTextField();
		secondPnl.add(square);

		directionLbl = new JLabel("Direction:");
		secondPnl.add(directionLbl);

		direction = new JTextField();
		secondPnl.add(direction);

		// third panel
		upBtn = new JButton(" ");
		thirdPnl.add(upBtn);
		upBtn.addActionListener(this);

		blank1Btn = new JButton("^");
		thirdPnl.add(blank1Btn);
		blank1Btn.addActionListener(this);

		blank2Btn = new JButton(" ");
		thirdPnl.add(blank2Btn);
		blank2Btn.addActionListener(this);

		leftBtn = new JButton("<");
		thirdPnl.add(leftBtn);
		leftBtn.addActionListener(this);

		middleBtn = new JButton("Middle");
		thirdPnl.add(middleBtn);
		middleBtn.addActionListener(this);

		rightBtn = new JButton(">");
		thirdPnl.add(rightBtn);
		rightBtn.addActionListener(this);

		blank3Btn = new JButton(" ");
		thirdPnl.add(blank3Btn);
		blank3Btn.addActionListener(this);

		downBtn = new JButton("v");
		thirdPnl.add(downBtn);
		downBtn.addActionListener(this);

		blank4Btn = new JButton(" ");
		thirdPnl.add(blank4Btn);
		blank4Btn.addActionListener(this);

		// fourth panel
		compassBtn = new JButton("Compass");
		fourthPnl.add(compassBtn);
		compassBtn.addActionListener(this);

		// fifth panel
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

		// bottom panel
		actBtn = new JButton("Act");
		bottomPnl.add(actBtn);
		actBtn.addActionListener(this);

		runBtn = new JButton("Run");
		bottomPnl.add(runBtn);
		runBtn.addActionListener(this);

		resetBtn = new JButton("Reset");
		bottomPnl.add(resetBtn);
		resetBtn.addActionListener(this);

		speedLbl = new JLabel("Speed: ");
		bottomPnl.add(speedLbl);

		speedSld = new JSlider();
		bottomPnl.add(speedSld);
	}

	public void actionPerformed(ActionEvent event) {
	}
}