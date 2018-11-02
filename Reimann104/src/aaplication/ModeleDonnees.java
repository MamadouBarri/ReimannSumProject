package aaplication;
/**
 * Classe qui comprends toutes les données, leurs getters et leurs setters
 * @author Mamadou Barri et Gayta, Reiner
 *
 */
public class ModeleDonnees {
	
	//variables
	private double parametreA = 2.4;
	private double parametreB = 1.1;
	private double parametreC = 0.8;
	private int nbRectangles = 10;
	private int nbLignesBrisees = 100;
	private int centreX = 0;
	private int centreY = 0;
	private int minX = -5;
	private int maxX = 5;
	private int minY = -5;
	private int maxY = 5;
	
	
	//Calculs
	private double aireAlg;
	private double aireGeo;
	private double difference;
	private double pourcentageEcart;
	
	//Setters avec 
	public void setParametreA (double parametreA) {
		this.parametreA = parametreA;
	}
	public void setParametreB (double parametreB) {
		this.parametreB = parametreB;
	}
	public void setParametreC (double parametreC) {
		this.parametreC = parametreC;
	}
	public void setNbRectangles (int nbRectangles) {
		this.nbRectangles = nbRectangles ;
	}
	public void setCentreX (int centreX) {
		this.centreX= centreX;
	}
	public void setCentreY (int centreY) {
		this.centreY = centreY;
	}
	public void setMinX (int minX) {
		this.minX = minX;
	}
	public void setMaxX (int maxX) {
		this.maxX = maxX;
	}
	public void setMinY (int minY) {
		this.minY = minY;
	}
	public void setMaxY (int maxY) {
		this.maxY = maxY;
	}
	public void setNbLignesBrisees(int nbLignesBrisees) {
		this.nbLignesBrisees = nbLignesBrisees;
	}
	
	//Getters
	public double getParametreA () {
		return(this.parametreA);
	}
	public double getParametreB () {
		return(this.parametreB);
	}
	public double getParametreC() {
		return(this.parametreC);
	}
	public int getNbRectangles () {
		return(this.nbRectangles);
	}
	public int getCentreX () {
		return(this.centreX);
	}
	public int getCentreY () {
		return(this.centreY);
	}
	public int getMinX () {
		return(this.minX);
	}
	public int getMaxX () {
		return(this.maxX);
	}
	public int getMinY () {
		return(this.minY);
	}
	public int getMaxY() {
		return(this.maxY);
	}
	public int getNbLignesBrisees() {
		return(this.nbLignesBrisees);
	}
	
	//Recalculer les données 
	// nbRecet --> recalculer longueur rect, recalculer laire etc..

}
