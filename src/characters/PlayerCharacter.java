package characters;

/**
 *In diesem Spiel gibt es nur einen player character, der in der 
 *Geschichte Louis ist. der User spielt aus der Perspektive von Louis.
 *Die einzigen Eigenschaften, die hier benötigt werden, sind der Name des PlayerCharacter
 *und sein Lebenspunkte-Zustand. Der PlayerCharacter führt selbst keine Aktionen an den NPCs aus.
 *@author ines
 */
public class PlayerCharacter {
	
	private int lebenspunkte;

	public PlayerCharacter (int lifepoints) {
		this.lebenspunkte = lifepoints;
		}

	public int getLebenspunkte() {
		return lebenspunkte;
	}

	public void setLebenspunkte(int lebenspunkte) {
		if(this.lebenspunkte <= 0) {
			System.out.println("Kontrollausgabe aus setLebenspunkte()");
			this.lebenspunkte = 0;
		}
		if(this.lebenspunkte > 0){
		this.lebenspunkte = lebenspunkte;
		}
	}

}
