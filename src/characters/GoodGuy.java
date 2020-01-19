package characters;

import java.security.SecureRandom;

import interfaces.InterfaceGood;

/**
 *  <h3> Klasse GoodGuy, löst Heilpunkte am player character aus. </h3>
 * Die GoodGuy Objekte führen die Methoden aus, die am PlayerCharacter
 * Heilungspunkte verursachen, die durch die heilen() Methode 
 * auf seine Lebenspunkte aufaddiert werden.
 * GoodGuy Objekte haben einen Namen und eine jeweils eigene npcHeilrate.
 * Jede GoodGuy Methode hat eine eigene Heilrate.
 * Die KLasse GoodGuy implementiert die heilenden Methoden aus 
 * dem InterfaceGood.
 * @author ines
 */

public class GoodGuy implements InterfaceGood{
	
	private SecureRandom ran = new SecureRandom();
	private int npcHeilrate; 
	private int heilPunkteDerMethode;
	private int randHealfactor = ran.nextInt(3);

	
	public GoodGuy(int npcHeilrate) {
		this.npcHeilrate = npcHeilrate;
	}
	
/**
 * Heilpunkte werden erst gutgeschrieben, wenn die Lebenspunkte des PlayerCharacter 
 * kleiner als der willkürlich bestimmte Grenzwert von 60 sind. 
 * @return Hier ebenfalls Wiedergabe der pc.lebenpunkte (wie in Klasse BadGuy)
 * zwecks Aktualisierung im Anzeigefeld showPClife in der Klasse Gui.
 */
	@Override
	public int heilen(PlayerCharacter pc) {
		if(pc.getLebenspunkte() < 60) {
			//pc.getLebenspunkte() = pc.getLebenspunkte() + npcHeilrate + heilPunkteDerMethode;
			pc.setLebenspunkte(pc.getLebenspunkte() + npcHeilrate + heilPunkteDerMethode);
		}
		return pc.getLebenspunkte();
	}

	/**
	 * Zu den Heilpunkten der Heilmethoden wird noch ein 
	 * jeweils randomisierter Hailfaktor aufaddiert. 
	 * @return
	 * hug() Gibt, so wie in den anderen, Heilfaktor erwirkenden Methoden, die heilPunkteDerMethode wieder, damit sie in der Gui
	 * über den Aufruf der heilen() Methode in showPClife angezeigt/aktualisiert werden können.
	 */
	@Override
	public int hug(PlayerCharacter pc) {
		heilPunkteDerMethode = 5 + randHealfactor;
		heilen(pc);
		return  heilPunkteDerMethode; 
	}

	@Override
	public int dream(PlayerCharacter pc) {
		heilPunkteDerMethode = 3 + randHealfactor;
		heilen(pc);
		return  heilPunkteDerMethode;
	}

	@Override
	public int soothe(PlayerCharacter pc) {
		heilPunkteDerMethode = 7 + randHealfactor;
		heilen(pc);
		return heilPunkteDerMethode;
	}


	@Override
	public int shovel(PlayerCharacter pc) {
		heilPunkteDerMethode = 10 + randHealfactor;
		heilen(pc);
		return heilPunkteDerMethode;
	}
	

}
