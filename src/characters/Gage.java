package characters;

import characters.GoodGuy;
import interfaces.InterfaceBad;

import java.security.SecureRandom;


/**
 * <h3> Klasse Gage, löst Schadenspunkte oder Heilpunkte am player character aus. </h3>
 * Die Klasse Gage wurde entworfen, um ein NPC Objekt zu erzeugen,
 * welches über die Fähigkeiten der BadGuy Klasse UND der GoodGuy Klasse 
 * verfügt. Daher erbt die Klasse Gage von der Klasse GoodGuy und implementiert
 * das InterfaceBad. Über die Methoden des InterfaceBad hat die Klasse Gage
 * noch zwei eigene, Schaden verursachende Methoden, nämlich
 * wrestles() und scalpel().
 * @author ines
 */
public class Gage extends GoodGuy implements InterfaceBad{
	
	private int npcSchadensrate;
	private SecureRandom ran = new SecureRandom();
	private int damage; 
	private double hitChance = ran.nextInt(2);
	
	public Gage(int schadensrate, int npcHealingFactor) {
		super(npcHealingFactor);
	}
	
	@Override
	public int verwunden(PlayerCharacter pc) {
		pc.setLebenspunkte(pc.getLebenspunkte() - this.npcSchadensrate - damage);
		return pc.getLebenspunkte();
	}

	@Override
	public int claws(PlayerCharacter pc)  {
		damage = 10;
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
	public int haunts(PlayerCharacter pc) {
		damage = 10;
		verwunden(pc);
		return damage;
	}

	@Override
	public int bites(PlayerCharacter pc)  {
		damage = 12;
		damage += npcSchadensrate;
		verwunden(pc);
		return damage;
	}
	
	@Override
	public int growls(PlayerCharacter pc)  {
		damage = 10;
		verwunden(pc);
		return damage;
	}

	public int wrestles(PlayerCharacter pc) {
		damage = 5;
		verwunden(pc);
		return damage;
	}
	
	public int scalpel(PlayerCharacter pc) {
		damage = 15;
		verwunden(pc);
		return damage;
	}


}
