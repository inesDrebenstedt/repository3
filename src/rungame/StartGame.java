package rungame;

/**
 * <h3> Die Klasse SartGame enthält die Main Methode und den Gui Konstruktor Aufruf.</h3>
 * @author ines
 * Indem der Konstruktor der Gui Klasse aufgerufen wird, wird als Attribut innerhalb des Konstruktors
 * ein Objekt und ein Konstruktor der Game Klasse aufgerufen.
 * Mit dem Aufruf des Konstruktors der Game Klasse wiederum werden die Klassen Gui und DataFeed als 
 * Parameter aufgenommen. Im Konstruktor der Datafeed Klasse ist die Klasse Gui als
 * Parameter enthalten.
 */
public class StartGame {

	public static void main(String[] args) {
		new Gui();

	}

}
