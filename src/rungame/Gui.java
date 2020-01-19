package rungame;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import rungame.Game;

/**
 * Die Klasse Gui macht die Veränderungen sichtbar, die durch die Gamelogik
 * verursacht werden. Andererseits soll die Gamelogik auch durch die Gui
 * Actionlistener gesteuert werden können. Die Klassen Gui und Game sollen
 * miteinander kommunizieren, aber getrennt voneinander programmiert werden.
 * Gui extends JFrame, weil Komponenten von JFrame in der Gui als
 *        Objekte verwendet werden sollen. implements Action Listener, weil die
 *        Buttonklicks Aktionen/Methoden auslösen sollen.
 *        @author ines
 */
public class Gui implements ActionListener {
	/**
	 *  myGame: Hier wird ein Objekt der Klasse Game namens myGame erstellt,
	 *       damit hier in der Gui Klasse myGame auf die Attribute der Klasse Game
	 *       zugreifen kann.
	 *       Referenz myGame muss außerhalb des Konstruktors der Gui Klasse
	 *       stehen, weil auf myGame sonst nicht von außen zugegriffen werden kann.
	 */
	private Game myGame;
	private Datafeed df = new Datafeed(this);

	/**
		Der begruessungstext enthält eine Begrüssung an den User
	 *                   und Infos, was im Spiel zu machen ist. Der Begrüssungstext
	 *                   wird über die Methode readTextLines(path) ausgelesen (s.
	 *                   doc zu readTextLines())
	 */
	private String pathToFirstText = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\textsnippets\\begruessungstext";
	private String begruessungstext = df.readTextLines(pathToFirstText);

	private ImageIcon frameIcon = new ImageIcon("C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\zombiehand.png");
	private Icon backgroundIcon = new ImageIcon("C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\PetSemataryCircle.png");

	private ImageIcon pictureAtStartup = new ImageIcon("C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\0.png");

	private JFrame myFrame = new JFrame("Pet Sematary - The Game");
	private JLabel picWindow = new JLabel();

	// TEXTANZEIGEN:
	private Font myFont = new Font("MONOSPACED", Font.PLAIN, 12);
	private JTextArea textArea = new JTextArea(10, 20); 
	private JTextArea consequencesField = new JTextArea(10, 20);
	private JTextField showPClife = new JTextField("Lebenspunkte: ");

	// BUTTONS:
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	private JButton nextButton = new JButton("Next"); 

	/**
	 *Statische Variable, die Klassenübergreifend zählt, bei welchem Spielzug man gerade ist.
	 *Über den spielzugCounter wird das Laden der jeweligen Bilder/Textanzeigen, die für den jew. Spielzug gerade
	 *an der Reihe sind, gesteuert.
	 *Mit dem spielzugCounter werden auch die Buttons, die in der jew. Situation betätigt werden dürfen, auf 
	 *enabled gesetzt.
	 *In der Game Klasse wird über den Spielzugcounter die Aktion ausgeführt, 
	 *die entsprechend dem spielzugCounter gerade dran ist.
	 */
	private int spielzugCounter = 0;

	/**
	 *  Hier kommt der Konstruktor der GUI mit der buildWindow() Methode
	 */
	public Gui() {
		myGame = new Game(this, df);
		buildWindow();
	}

	/**
	 *  Diese Methode setzt die GUI-Komponenten in der richtigen
	 *  Reihenfolge mit den gewünschten settings zusammen.
	 */
	public void buildWindow() {
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setIconImage(frameIcon.getImage());
		myFrame.setLocation(300, 150);
		myFrame.setSize(1200, 800);
		myFrame.add(textArea);
		myFrame.add(yesButton);
		myFrame.add(noButton);
		myFrame.add(nextButton);

		myFrame.add(showPClife);
		myFrame.add(consequencesField);
		myFrame.add(picWindow);
		myFrame.add(showPClife);
		myFrame.add(new JLabel(backgroundIcon));

		textArea.setBounds(500, 20, 600, 300);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setText(begruessungstext);
		textArea.setEditable(false);
		textArea.setFont(myFont);
		
		/**
		 * Bei yesButton, noButton und nextButton wird der Actionlistener
		 * über je eine lambda-expression aufgerufen. Siehe überschriebene actionPerformed-Methode.
		 * Der yes und der no Button sollen in den ersten zwei Spielzügen nicht klickbar sein.
		 */
		if(spielzugCounter < 2) {
			yesButton.setEnabled(false);
			noButton.setEnabled(false);
		}

		yesButton.addActionListener(yesEvent -> actionPerformed(yesEvent));
		yesButton.setBounds(650, 350, 60, 60);
		noButton.addActionListener(noEvent -> actionPerformed(noEvent));
		noButton.setBounds(800, 350, 60, 60);
		nextButton.addActionListener(nextEvent -> actionPerformed(nextEvent));
		nextButton.setBounds(1050, 550, 80, 60);

		picWindow.setBounds(50, 150, 400, 400);
		picWindow.setOpaque(true);
		picWindow.setBackground(new Color(100, 100, 100));
		df.resizeImg(pictureAtStartup);
		picWindow.setIcon(pictureAtStartup);

		consequencesField.setBounds(500, 430, 500, 260);
		consequencesField.setLineWrap(true);
		consequencesField.setWrapStyleWord(true);
		consequencesField.setEditable(false);
		consequencesField.setFont(myFont);

		showPClife.setBounds(150, 700, 200, 20);
		showPClife.setText("Lebenspunkte: " + myGame.getPClife());
	
		myFrame.setVisible(true);
	}

	/**
	 * Hier wird der Standard-Actionlistener überschrieben, um zu bestimmen,
	 * was beim Klick des jeweiligen Buttons passiert.
	 * Diejenigen Buttons, die in der jeweiligen Spielsizuation nicht klickbar sein 
	 * sollen, werden in .setEnabled() mit dem Parameter false versehen.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == yesButton) {
			myGame.yesButtonActions();
			df.loadYesPicAndTexts();			
			showPClife.setText("Lebenspunkte: " + myGame.getPClife());
			nextButton.setEnabled(true);
			noButton.setEnabled(false);
			yesButton.setEnabled(false);
			myGame.checkIfGameOver();
		}

		if (event.getSource() == noButton) {
			myGame.noButtonActions();
			df.loadNoPicAndTexts();
			showPClife.setText("Lebenspunkte: " + myGame.getPClife());
			nextButton.setEnabled(true);
			noButton.setEnabled(false);
			yesButton.setEnabled(false);
			myGame.checkIfGameOver();
		}

		if (event.getSource() == nextButton) {
			df.loadNextPicAndTexts();
			spielzugCounter++;
			if (spielzugCounter == 0 || spielzugCounter == 1) {
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
				yesButton.setEnabled(true);
				noButton.setEnabled(true);
			}
			myGame.checkIfGameOver();
		}

	}
	
	public JLabel getPicWindow() {
		return picWindow;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getConsequencesField() {
		return consequencesField;
	}

	public JTextField getShowPClife() {
		return showPClife;
	}

	public JButton getYesButton() {
		return yesButton;
	}

	public JButton getNoButton() {
		return noButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public int getSpielzugCounter() {
		return spielzugCounter;
	}


}
