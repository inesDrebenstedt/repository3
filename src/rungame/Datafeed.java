package rungame;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

/**
 * <h3> Die Klasse DataFeed versorgt Die Klasse Gui mit zu erledigenden Jobs.</h3>
 * Änderungen im Spielzustand sollen in der Gui angezeigt werden.
 * Damit die Gui diese Änderungen darstellt, müssen 
 * Bilddaten bzw. Textdateien verarbeitet + geladen werden, was von der Klasse DataFeed
 * erledigt wird. 
 * @author ines
 *
 */
public class Datafeed {
	
	private Gui myGui;
	
	public Datafeed (Gui g) {
		myGui = g;
	}
	
	// Texte auslesen:
	/**
	 *  Methode, die die Textzeilen aus dem file des angegebenen Pfads ausliest.
	 * 
	 * @param filePath gibt den Pfad an, an dem das jeweilige zu lesende file liegt.
	 * Schleifenbedingung prüft, ob die jew. Textzeile leer ist, un wenn nicht, wird sie
	 * an den StringBuilder textBuilder angehängt.
	 * @return wandelt von Datentyp StringBuilder zu Datentyp String um, damit der String in den Textareas angezeigt werden kann
	 * und gibt das jeweilige textsnippet per Spielzug (s. spielzugCounter) aus.
	 */
	public String readTextLines(String filePath) {
		StringBuilder textBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String textZeile;
			while ((textZeile = br.readLine()) != null) {
				textBuilder.append(textZeile).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textBuilder.toString();
	}
	
	//Lied abspielen:
	/**
	 *  <h3>Methode playTune()</h3>
	 *  spiel das Ramones Lied ab, wenn das Spiel an einem
	 *  "ist zu Ende Zustand" angekommen ist. 
	 *  Hier kann wahlweise, für das 80er Jahre feeling auch die 8bit Melodie verwendet werden;
	 *  in diesem Fall muss der DateiPfad entsprechend auf ...8bit.wav geändert werden.
	 */
	public void playTune() {
		try {
		String audioPath = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\PetSematary.wav";   
		AudioInputStream audioInputStream =  AudioSystem.getAudioInputStream(new File(audioPath)); 
		Clip ramones = AudioSystem.getClip();
		ramones.open(audioInputStream); 
		ramones.start();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * verändert die Bildmaße so, daß das jew. Bild den
	 * Platz im picWindow (hat Datentyp JLabel) maximal ausfüllt.
	 * @param icon
	 * Als Parameter wird resizeImg() ImageIcon icon übergeben, 
	 * weil das ja das Objekt ist, mit dem was gemacht werden soll.
	 */
	public void resizeImg(ImageIcon icon) {
		
		double iconWidth = icon.getIconWidth();
		double windowWidth = myGui.getPicWindow().getWidth();
		double faktorWeite = windowWidth / iconWidth;
		double newIconWidth = faktorWeite * iconWidth;
		int nW = (int) newIconWidth;
		icon.setImage(icon.getImage().getScaledInstance(nW, -1, Image.SCALE_DEFAULT));
		ImageIcon resizedImage = new ImageIcon(icon.getImage());
		myGui.getPicWindow().setIcon(resizedImage);
	}
	
	
	/**
	 * Die Methoden loadYesPicAndTexts(), loadNoPicAndTexts() und loadNextPicandTexts()
	 * laden passend zum jeweiligen Spielzug 
	 * das dazugehörende Bild + den Text für das consequencesField. 
	 * Bei jedem laden des neuen Bildes wird die Methode 
	 * resizeImg() wiederverwendet.
	 * Bei jedem laden des neuen Textabschnitts wird die Methode 
	 * readTextLines() wiederverwendet.
	 */
	public void loadYesPicAndTexts() {
		String yesPicPath = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\yesPics\\" + myGui.getSpielzugCounter() + ".png";
		myGui.getPicWindow().setIcon(new ImageIcon(yesPicPath));
		 resizeImg(new ImageIcon(yesPicPath));
		String pathToYesConsequences = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\yesConsequences\\"
				+ (myGui.getSpielzugCounter());
		myGui.getConsequencesField().setText(readTextLines(pathToYesConsequences));
	}
	
	public void loadNoPicAndTexts() {
		String noPicPath = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\noPics\\" + myGui.getSpielzugCounter() + ".png";
		myGui.getPicWindow().setIcon(new ImageIcon(noPicPath));		
		resizeImg(new ImageIcon(noPicPath));		 
		String pathToNoConsequences = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\noConsequences\\"
				+ (myGui.getSpielzugCounter());
		myGui.getConsequencesField().setText(readTextLines(pathToNoConsequences));
	}
	
	public void loadNextPicAndTexts() {
		myGui.getConsequencesField().setText("");
		String nextTextPath = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\textsnippets\\"
				+ (myGui.getSpielzugCounter() + 1);
		String newText = readTextLines(nextTextPath);
		myGui.getTextArea().setText(newText);
		String nextPicPath = "C:\\Users\\Alfa\\Desktop\\Workspace\\PetSemataryTheGame\\nextPics\\" + (myGui.getSpielzugCounter() + 1)
				+ ".png";
		myGui.getPicWindow().setIcon(new ImageIcon(nextPicPath));
		resizeImg(new ImageIcon(nextPicPath));
	}
	
}
