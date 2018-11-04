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
	private double minX = -5;
	private double maxX = 5;
	private double minY = -5;
	private double maxY = 5;
	
	
	//constantes 
	final double PARAMETRE_A = 2.4;
	final double PARAMETRE_B = 1.1;
	final double PARAMETRE_C = 0.8;
	final double NB_RECTANGLES = 10;
	final double MIN_X = -5;
	final double MAX_X = 5;
	final double MIN_Y = -5;
	final double MAX_Y = 5;
	
	
	
	
	
	
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
	public void setMinX (double minX) {
		this.minX = minX;
	}
	public void setMaxX (double maxX) {
		this.maxX = maxX;
	}
	public void setMinY (double minY) {
		this.minY = minY;
	}
	public void setMaxY (double maxY) {
		this.maxY = maxY;
	}
	public void setNbLignesBrisees(int nbLignesBrisees) {
		this.nbLignesBrisees = nbLignesBrisees;
	}
	public void setAireAlg(double aireAlg) {
		this.aireAlg = aireAlg;
	}
	public void setAireGeo(double aireGeo) {
		this.aireGeo = aireGeo;
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
	public double getMinX () {
		return(this.minX);
	}
	public double getMaxX () {
		return(this.maxX);
	}
	public double getMinY () {
		return(this.minY);
	}
	public double getMaxY() {
		return(this.maxY);
	}
	public int getNbLignesBrisees() {
		return(this.nbLignesBrisees);
	}
	public double getAireAlg() {
		double aireAlgebrique = (this.parametreA * Math.sin(this.maxX) - this.parametreB * Math.cos(this.maxX) + this.parametreC * this.maxX) - (this.parametreA * Math.sin(this.minX) 
				- this.parametreB * Math.cos(this.minX) + this.parametreC * this.minX);
		return(aireAlgebrique);
	}
	public double getAireGeo() {
		double largeurRect = (this.maxX - this.minX)/this.nbRectangles;
		double aireTotale = 0;
		double y;
		double x = this.minX +  largeurRect/2.0;
		for(int k= 0;k<this.nbRectangles;k++) {
			y = this.parametreA*Math.cos(x) + this.parametreB* Math.sin(x) + this.parametreC;
			aireTotale += y * largeurRect;
			x+=largeurRect;
		}
		return(aireTotale);
	}
	//Recalculer les données 
	// nbRecet --> recalculer longueur rect, recalculer laire etc..

}
