package rungame;


import javax.swing.*;

import characters.BadGuy;
import characters.Gage;
import characters.GoodGuy;
import characters.PlayerCharacter;
import rungame.Gui;

/**
 * <h3>Klasse Game</h3> Die Klasse Game beinhaltet den code für die Spiellogik.
 * Veränderungen im Spielstand werden in der Gui Klasse angezeigt. Der
 * Spielablauf wird durch den Geschichttstrang festgelegt, d. h. es ist
 * festgelegt, wann welcher Character welche Aktion am PlayerCharacter ausführt.
 * 
 * @author ines
 */

public class Game {

	/**
	 * <h3>Spielfiguren, die einen Einfluss auf die Lebenspunkte des player
	 * characters haben</h3> Verschiedene Objekte aus den Klassen
	 * BadGuy, Goodguy und Gage. Der character, der je nach Spielzug an der Reihe
	 * ist, wird referenziert, um die entsprechenden Methoden aufrufen zu können.
	 */
	private BadGuy church = new BadGuy( 8);
	private BadGuy victor = new BadGuy( 10);
	private BadGuy wendigo = new BadGuy( 15);
	private Gage gage = new Gage(10, 10);
	private GoodGuy ellie = new GoodGuy( 3);
	private GoodGuy rachel = new GoodGuy( 5);
	private GoodGuy jud = new GoodGuy( 8);

	/**
	 * <h3>Objekt PlayerCharacter louis</h3> Der player character Louis ist ein
	 * Objekt aus der Klasse PlayerCharacter mit der Referenz louis.
	 * Dazu gehört der getter getPClife() (s. u.), damit der aktuelle Lebenspunktestand
	 * abgerufen werden kann.
	 */
	private PlayerCharacter louis = new PlayerCharacter(70);

	/**
	 * <h3>Gui myGui</h3> Hier wird ein Gui Objekt als Attribut der Game-Klasse
	 * erzeugt, welches über den Konstruktor der Game Klasse als Parameter
	 * modifizierbar wird. Damit kann objektorientiert aus der Game-Klasse auf die
	 * myGui zugegriffen werden und die Gui Schicht wird von der Schicht der Game
	 * Logik getrennt, aber beide Schichten können trotzdem miteinander
	 * kommunizieren. Dadurch wird auch beim starten der myGui bzw. beim Aufruf des
	 * Gui Konstruktors via Mainmethode in der Gui-Klasse automatisch die Game Logik
	 * gestartet. Die Methoden der Game Klasse müssen auf die Referenz myGui
	 * zugreifen können, weswegen myGui außerhalb des Game Konstruktors deklariert
	 * werden muss.
	 */
	private Gui myGui;
	private Datafeed df; //brauch ich hier nicht? = new Datafeed(myGui);

	/**
	 * <h3>Game Konstruktor</h3>
	 * 
	 * @param gui 
	 * Der Konstruktor der Game Klasse enthält das Gui Objekt myGui als
	 *  Parameter, um die GUI Schicht zu separieren.
	 * @param d
	 * Der Konstruktor der Game Klasse enthält das Datafeed Objekt d als
	 *  Parameter, um die Datafeed Schicht zu separieren.
	 */
	public Game(Gui gui, Datafeed d) {
		myGui = gui;
		df = d;
	}
	
	
	/**
	 * <h3>Methode getPClife</h3>
	 * 
	 * @return getPClife() gibt den jeweiligen Lebenspunktestand je nach
	 *         Spielfortschritt als int wieder und wird in der Gui in der Komponente
	 *         "showPClife" aktualisiert.
	 *         Der hier returnte int Wert ist auch wichtig, um zu entscheiden, ob
	 *         GAME OVER oder noch nicht.
	 */

	public int getPClife() {
		return louis.getLebenspunkte();
	}

	/**
	 * <p>
	 * Methode yesButtonActions()
	 * </p>
	 * (Bzw. analog zu noButtonActions.) Diese Methode prüft über ifs, welchen
	 * Zustand spielzuCounter gerade hat und führt dementsprechend die dazu
	 * gehörenden Aktionen der NPC's aus, wenn man auf den yes oder no Button
	 * klickt. Der Ablauf der aufgerufenen Methoden/Charaktere ist durch den Ablauf der
	 * Geschichte vorgeschrieben. 
	 */
	public void yesButtonActions() {
		if (myGui.getSpielzugCounter() == 2) {
			church.bites(louis);
		}
		if (myGui.getSpielzugCounter() == 3) {
			victor.curse(louis);
		}
		if (myGui.getSpielzugCounter() == 4) {
			gage.hug(louis);
		}
		if (myGui.getSpielzugCounter() == 5) {
			church.claws(louis);
		}
		if (myGui.getSpielzugCounter() == 6) {
			church.growls(louis);
		}
		if (myGui.getSpielzugCounter() == 7) {
			church.bites(louis);
		}
		if (myGui.getSpielzugCounter() == 8) {
			ellie.hug(louis);
			gage.curse(louis);
		}
		if (myGui.getSpielzugCounter() == 9) {
			gage.haunts(louis);
		}
		if (myGui.getSpielzugCounter() == 10) {
			ellie.hug(louis);
		}
		if (myGui.getSpielzugCounter() == 11) {
			church.growls(louis);
		}
		if (myGui.getSpielzugCounter() == 12) {
			rachel.hug(louis);
		}
		if (myGui.getSpielzugCounter() == 13) {
			df.playTune();
			String hallelujahPath = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\13YouWin.png";
			String pathToYesConsequences = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\yesConsequences\\"
					+ (myGui.getSpielzugCounter());
			myGui.getConsequencesField().setText(df.readTextLines(pathToYesConsequences));
			myGui.getPicWindow().setIcon(new ImageIcon(hallelujahPath));
			df.resizeImg(new ImageIcon(hallelujahPath));
			myGui.getNextButton().setEnabled(false);
			myGui.getNoButton().setEnabled(false);
			myGui.getYesButton().setEnabled(false);
			JOptionPane.showMessageDialog(null, "Hallelujah, YOU WIN!");
			System.exit(-1);
		}
		if (myGui.getSpielzugCounter() == 14) {
			rachel.dream(louis);
		}
		if (myGui.getSpielzugCounter() == 15) {
			jud.shovel(louis);
		}
		if (myGui.getSpielzugCounter() == 16) {

		}
		if (myGui.getSpielzugCounter() == 17) {
			gage.bites(louis);
		}
		if (myGui.getSpielzugCounter() == 18) {
			gage.haunts(louis);
		}
		if (myGui.getSpielzugCounter() == 19) {
			df.playTune();
			df.loadYesPicAndTexts();
			myGui.getNextButton().setEnabled(false);
			myGui.getNoButton().setEnabled(false);
			myGui.getYesButton().setEnabled(false);
			myGui.getShowPClife().setText("Alle Lebenspunkte verloren.");
			JOptionPane.showMessageDialog(null, "Bad decision. GAME OVER!");
			System.exit(-1);
		}
		if (myGui.getSpielzugCounter() == 20) {
			df.loadNextPicAndTexts();
		}

	}

	/**
	 * <h3>Methode noButtonActions()</h3> Siehe Erläuterungen zu Methode
	 * yesButtonActions().
	 */
	public void noButtonActions() {
		if (myGui.getSpielzugCounter() == 2) {
			church.claws(louis);
		}
		if (myGui.getSpielzugCounter() == 3) {
			victor.haunts(louis);
		}
		if (myGui.getSpielzugCounter() == 4) {
			ellie.hug(louis);
		}
		if (myGui.getSpielzugCounter() == 5) {
			church.bites(louis);
		}
		if (myGui.getSpielzugCounter() == 6) {
			church.growls(louis);
		}
		if (myGui.getSpielzugCounter() == 7) {

		}
		if (myGui.getSpielzugCounter() == 8) {
			gage.haunts(louis);
		}
		if (myGui.getSpielzugCounter() == 9) {
			rachel.soothe(louis);
		}
		if (myGui.getSpielzugCounter() == 10) {
			jud.soothe(louis);
		}
		if (myGui.getSpielzugCounter() == 11) {
			rachel.soothe(louis);
		}
		if (myGui.getSpielzugCounter() == 12) {
			ellie.hug(louis);
		}
		if (myGui.getSpielzugCounter() == 13) {

		}
		if (myGui.getSpielzugCounter() == 14) {
			victor.haunts(louis);
		}
		if (myGui.getSpielzugCounter() == 15) {
			wendigo.haunts(louis);
		}
		if (myGui.getSpielzugCounter() == 16) {
			gage.scalpel(louis);
		}
		if (myGui.getSpielzugCounter() == 17) {
			gage.growls(louis);
		}
		if (myGui.getSpielzugCounter() == 18) {
			gage.bites(louis);
		}
		if (myGui.getSpielzugCounter() == 19) {
			gage.wrestles(louis);
		}
		if (myGui.getSpielzugCounter() == 20) {
		}
	}

	/**
	 * <h3>Methode checkIfGameOver()</h3> wird der überschriebenen
	 * actionPerformed-Methode in der Gui Klasse übergeben, um zu überprüfen, ob ein
	 * "Spiel ist zu Ende" Zustand besteht (z. B. wenn der PlayerCharacter keine
	 * Lebenspunkte mehr hat, man bis zum Ende gekommen ist oder eine Sackgasse
	 * gewählt wurde.).
	 * Es sind hier jedoch zwei Arten der "Spiel zuende" Situationen zu unterscheiden:
	 * a) Es ist ein durch die yes/no Verzweigung ausgelöstes Spielende, wie in 
	 * yes choices # 13  und # 19 
	 * und b)
	 * Spiel zu Ende Situationen, die unabhängig von der Wahl von yes oder no sind, also
	 * vorzeitiges Ableben des player characters durch Verlust aller Lebenspunkte.
	 */
	public void checkIfGameOver() {
		if (myGui.getSpielzugCounter() <= 20 && louis.getLebenspunkte() <= 0) {
			myGui.getShowPClife().setText("Lebenspunkte: 0");
			df.playTune();
			JOptionPane.showMessageDialog(null, "Alle Lebenspunkte verloren. YOU LOSE!");
			System.exit(-1);
		}
		if (myGui.getSpielzugCounter() == 20 && louis.getLebenspunkte() > 0) {
			myGui.getYesButton().setEnabled(false);
			myGui.getNoButton().setEnabled(false);
			myGui.getNextButton().setEnabled(false);
			df.playTune();
			JOptionPane.showMessageDialog(null, "Congratulations, you got all the way through. YOU WIN!");
			System.exit(-1);
		}
	}
	

	

}
