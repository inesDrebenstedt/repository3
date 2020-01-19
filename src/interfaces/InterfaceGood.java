package interfaces;

import characters.PlayerCharacter;

/**
 * Das InterfaceGood listet die Methoden auf, 
 * die am PlayerCharacter Schaden verursachen und wird von den Spielfiguren implementiert,
 * die die guten Methoden/die Methoden, die dem PlayerCharacter Heilungspunkte bringen,
 * implementiert. 
 * @author ines
 */

public interface InterfaceGood {
	
	public int heilen(PlayerCharacter pc);
	public int hug(PlayerCharacter pc); 
	public int dream(PlayerCharacter pc); 
	public int soothe(PlayerCharacter pc);
	public int shovel(PlayerCharacter pc);
}
