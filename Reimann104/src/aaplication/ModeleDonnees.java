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
	private double differencePourcentage;
	private double differenceUnites;
	private boolean afficheRectangles = true;
	private boolean curseurDansDessin;
	private boolean secret;
	
	
	//constantes 
	public double parametreAInitiale = 2.4;
	public double parametreBInitiale = 1.1;
	public double parametreCInitiale = 0.8;
	public int nbRectanglesInitiales = 10;
	private double minXInitiale = -5;
	private double maxXInitiale = 5;
	private double minYInitiale = -5;
	private double maxYInitiale = 5;
	
	
	
	
	
	
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
	public void setParametreAInitiale(double PARAM_A) {
		this.parametreAInitiale = PARAM_A;
	}
	public void setParametreBInitiale(double PARAM_B) {
		this.parametreBInitiale = PARAM_B;
	}
	public void setParametreCInitiale(double PARAM_C) {
		this.parametreCInitiale = PARAM_C;
	}
	public void setNbRectanglesInitiales(int NB_RECT) {
		this.nbRectanglesInitiales = NB_RECT;
	}
	public void setMaxXInitiale(double X) {
		this.maxXInitiale = X;
	}
	public void setMinXInitiale(double X) {
		this.minXInitiale = X;
	}
	public void setMaxYInitiale(double Y) {
		this.maxYInitiale = Y;
	}
	public void setMinYInitiale(double Y) {
		this.minYInitiale = Y;
	}
	public void setDifferencePourecentage(double differencePourecentage) {
		this.differencePourcentage = differencePourcentage;
	}
	public void setDifferenceUnites(double differenceUnites) {
		this.differenceUnites = differenceUnites;
	}
	public void setAfficheRectangles(boolean afficheRectangles) {
		this.afficheRectangles = afficheRectangles;
	}
	public void setCurseurDansComposant(boolean curseurDansComposant) {
		this.curseurDansDessin = curseurDansComposant;
	}
	public void setSecret(boolean secret) {
		this.secret = secret;
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
	public double getParametreAInitiale() {
		return this.parametreAInitiale;
	}
	public double getParametreBInitiale() {
		return this.parametreBInitiale;
	}
	public double getParametreCInitiale() {
		return this.parametreCInitiale;
	}
	public int getNbRectanglesInitiales() {
		return this.nbRectanglesInitiales;
	}
	public double getMaxXInitiale() {
		return this.maxXInitiale;
	}
	public double getMinXInitiale() {
		return this.minXInitiale;
	}
	public double getMaxYInitiale() {
		return this.maxYInitiale;
	}
	public double getMinYInitiale() {
		return this.minYInitiale;
	}
	public double getAireAlg() {
		double aireAlgebrique = (this.parametreA * Math.sin(this.maxX) - this.parametreB * Math.cos(this.maxX) + this.parametreC * this.maxX) - (this.parametreA * Math.sin(this.minX) 
				- this.parametreB * Math.cos(this.minX) + this.parametreC * this.minX);
		this.setAireAlg(aireAlgebrique);
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
		this.setAireGeo(aireTotale);
		return(aireTotale);
	}
	public double getDifferencePourcentage() {
		return((this.aireAlg - this.aireGeo)/this.aireAlg * 100.0);
	}
	public double getDifferneceUnites() {
		return(this.aireAlg - this.aireGeo);
	}
	public boolean getAffficheRectangles() {
		return(this.afficheRectangles);
	}
	public boolean getCurseurDansComposant() {
		return(this.curseurDansDessin);
	}
	public boolean getSecret() {
		return(this.secret);
	}
	//Recalculer les données 
	// nbRecet --> recalculer longueur rect, recalculer laire etc..

}
