package characters;

import java.security.SecureRandom;

import interfaces.InterfaceBad;

/**
 * <h3> Klasse BadGuy, löst Schadenspunkte am player character aus. </h3>
 * Objekte der BadGuy Klasse rufen Methoden aus, die Schadenspunkte erzeugen,welche von
 * den Lebenspunkten des PCs abgezogen werden. Die entsprechenden Methoden wurden 
 * aus dem InterfaceBad implementiert, welches alle Schaden verursachenden Methoden listet.
 * Jene Methoden geben int damage zurück und rufen die Methode verwunden() auf, die die  
 * zurückgegebenen int damage von den PlayerCharacter Lebenspunkten abziehen.
 * Jede damage erzeugende Methode hat ihre eigene damage Rate und 
 * jedes BadGuy Objekt hat seine eigene NPC Schadensrate (analaog zur NPC Heilrate in Klasse GoodGuy).
 * Die Methode haunts hat eine randomisierte, zusätzliche Schadensrate. 
 * Die Methoden claws() und curse() haben zusätzlich noch eine randomisierte 
 * Trefferchance.
 * @author ines
 */
public class BadGuy implements InterfaceBad{


	private int npcSchadensrate;
	private SecureRandom ran = new SecureRandom();
	private int damage; //= ran.nextInt(10) + 5;
	private double hitChance = ran.nextInt(2);

	//private boolean isDead;

	public BadGuy( int npcSchadensrate) {

		this.npcSchadensrate = npcSchadensrate; 
	}

	/**
	 *  <h3> Methode verwunden () </h3>
	 * verwunden() zieht von den PlayerCharacter Lebenspunkten den int damage der jew.
	 * Schaden erzeugenden Methode sowie die jew. NPC Schadensrate ab.
	 * @param pc: ein PlayerCharacter Objekt muss bei allen Schaden verursachenden Methoden 
	 * übergeben werden, weil das Attribut "lebenspunkte" aus der Klasse PlayerCharacter hier aufgerufen wird. 
	 * @return pc.lebenspunkte: die resultierenden pc.lebenspunkte werden
	 * in der Gui Klasse in der JComponent showPClife angezeigt.
	 * Außerdem ist GAME OVER, wenn der PlayerCharacter alle Lebenspunkte verloren hat.
	 */
	@Override
	public int verwunden(PlayerCharacter pc) {
		pc.setLebenspunkte(pc.getLebenspunkte() - damage - this.npcSchadensrate);
		System.out.println();
		return pc.getLebenspunkte();
	}

	/**
	 * Die Schadenbringenden Methoden geben int damage zurück, welcher wiederum
	 * in der Methode verwunden() von den pc.lebenspunkten abgezogen wird.
	 */
	@Override
	public int haunts(PlayerCharacter pc) {
		damage = 10;
		verwunden(pc);
		return damage;
	}

	/**
	 * @return
	 * Die Methoden claws() und curse() haben zusätzlich noch eine 50/50 hitchance.
	 */
	@Override
	public int claws(PlayerCharacter pc)  {
		damage = 8;
		damage = damage * (int)hitChance;
		verwunden(pc);
		return damage;
	}

	@Override
	public int curse(PlayerCharacter pc)  {
		damage = 8;
		damage = damage * (int)hitChance;
		verwunden(pc);
		return damage;
	}

	@Override
	public int bites(PlayerCharacter pc) {
		damage = 12;
		verwunden(pc);
		return damage;
	}

	@Override
	public int growls(PlayerCharacter pc)  {
		damage = 5;
		verwunden(pc);
		return damage;
	}

}
