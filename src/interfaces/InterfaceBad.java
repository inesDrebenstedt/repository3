package interfaces;

import characters.PlayerCharacter;

/**
 * Das InterfaceBad listet die Methoden auf, 
 * die am PlayerCharacter Schaden verursachen und wird von den Spielfiguren implementiert,
 * die die schadhaften Methoden/die Methoden, die dem PlayerCharacter Lebenspunkte abziehen,
 * implementiert. 
 * @author ines
 */
public interface InterfaceBad {
	

	public int verwunden(PlayerCharacter pc); 
	public int claws(PlayerCharacter pc);
	public int curse(PlayerCharacter pc);
	public int haunts(PlayerCharacter pc);
	public int bites(PlayerCharacter pc);
	public int growls(PlayerCharacter pc);


}
