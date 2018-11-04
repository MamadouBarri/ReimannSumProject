package aaplication;
/**
 * Cette classe comprend toutes les variables nécessaires pour la création de la fonction, le calcul des aires alébriques et géometriques.
 * @author Mamadou Barri et Gayta, Reiner
 *
 */
public class ModeleDonnees {
	//variables
	
	//reels
	private double parametreA = 2.4;
	private double parametreB = 1.1;
	private double parametreC = 0.8;;
	private double minX = -5;
	private double maxX = 5;
	private double minY = -5;
	private double maxY = 5;
	private double differencePourcentage;
	private double differenceUnites;
	private double largeurDesRectangles;
	private double yRect;
	private double xRect;
	//entiers
	private int nbRectangles = 10;
	private int nbLignesBrisees = 100;
	//booleans
	private boolean afficheRectangles = true;
	private boolean curseurDansDessin;
	private boolean secret;
	
	//variables qui resteront probablement consantes (leur changement est cependant possible avec les setters)
	
	//reels
	public double parametreAInitial = 2.4;
	public double parametreBInitial = 1.1;
	public double parametreCInitial = 0.8;
	private double minXInitial = -5;
	private double maxXInitial = 5;
	private double minYInitial = -5;
	private double maxYInitial = 5;
	//entiers
	public int nbRectanglesInitial = 10;
	
	//varibles avec calculs
	private double aireAlg;
	private double aireTotaleGeometrique;
	//Setters
	/**
	 * Setter du parametre A de la fonction 
	 * @param parametreA le parametre a
	 */
	public void setParametreA (double parametreA) {
		this.parametreA = parametreA;
	}
	/**
	 * Setter du parametre B de la fonction 
	 * @param parametreB le parametre b
	 */
	public void setParametreB (double parametreB) {
		this.parametreB = parametreB;
	}
	/**
	 * Setter du parametre C de la fonction 
	 * @param parametreC le parametre c
	 */
	public void setParametreC (double parametreC) {
		this.parametreC = parametreC;
	}
	/**
	 * Setter du nombre de rectangles 
	 * @param nbRectangles nombre de rectangles
	 */
	public void setNbRectangles (int nbRectangles) {
		this.nbRectangles = nbRectangles ;
	}
	/**
	 * Setter de la valeur minimale de x
	 * @param minX valeur minale de x
	 */
	public void setMinX (double minX) {
		this.minX = minX;
	}
	/**
	 * Setter de la valeur maximale de x
	 * @param maxX valeur maximale de x
	 */
	public void setMaxX (double maxX) {
		this.maxX = maxX;
	}
	/**
	 * Setter de la valeur minimale de y
	 * @param minY valeur minale de y
	 */
	public void setMinY (double minY) {
		this.minY = minY;
	}
	/**
	 * Setter de la valeur maximale de y
	 * @param maxY valeur maximale de y
	 */
	public void setMaxY (double maxY) {
		this.maxY = maxY;
	}
	/**
	 * Setter du nombre de lignes
	 * @param nbLignesBrisees nombre de lignes
	 */
	public void setNbLignesBrisees(int nbLignesBrisees) {
		this.nbLignesBrisees = nbLignesBrisees;
	}
	/**
	 * Setter de l'aire alebrique
	 * @param aireAlg aire algebrique
	 */
	public void setAireAlg(double aireAlg) {
		this.aireAlg = aireAlg;
	}
	/**
	 * Setter du parametre a initial
	 * @param parametreAInitial le parametre a initial de la fonction
	 */
	public void setParametreAInitial(double parametreAInitial) {
		this.parametreAInitial = parametreAInitial;
	}
	/**
	 * Setter du parametre b initial
	 * @param parametreBInitial parametre b initial
	 */
	public void setParametreBInitial(double parametreBInitial) {
		this.parametreBInitial = parametreBInitial;
	}
	/**
	 * Setter du parametre c initial
	 * @param parametreCInitial parametre c initial
	 */
	public void setParametreCInitial(double parametreCInitial) {
		this.parametreCInitial = parametreCInitial;
	}
	/**
	 * Setter du nombre de rectangles initial
	 * @param nombreRectInitial nombre de rectangles initial
	 */
	public void setNbRectanglesInitial(int nombreRectInitial) {
		this.nbRectanglesInitial = nombreRectInitial;
	}
	/**
	 * Setter de la valeur de x max initiale
	 * @param x le parametre x intiial
	 */
	public void setMaxXInitial(double xMaxInitial) {
		this.maxXInitial = xMaxInitial;
	}
	/**
	 * Setter de la valeur de x min initiale
	 * @param xMinInitial
	 */
	public void setMinXInitial(double xMinInitial) {
		this.minXInitial = xMinInitial;
	}
	/**
	 * Setter de la valeur de y max initiale
	 * @param yMaxInitial
	 */
	public void setMaxYInitial(double yMaxInitial) {
		this.maxYInitial = yMaxInitial;
	}
	/**
	 * Setter de la valeur de y min initiale 
	 * @param yMinInitial
	 */
	public void setMinYInitial(double yMinInitial) {
		this.minYInitial = yMinInitial;
	}
	/**
	 * Setter de la difference entre les aires alg. et geo. en pourcentage
	 * @param differencePourcentage la difference de pourcentage
	 */
	public void setDifferencePourecentage(double differencePourcentage) {
		this.differencePourcentage = differencePourcentage;
	}
	/**
	 * Setter de la difference entre les aires alg. et geo. en unites
	 * @param differenceUnites difference en unites
	 */
	public void setDifferenceUnites(double differenceUnites) {
		this.differenceUnites = differenceUnites;
	}
	/**
	 * Setter du boolean d'affichage des rectangles
	 * @param afficheRectangles le boolean de l'affichage des rectangles
	 */
	public void setAfficheRectangles(boolean afficheRectangles) {
		this.afficheRectangles = afficheRectangles;
	}
	/**
	 * Setter du boolean de la presence du curseur dans le composant
	 * @param curseurDansComposantle boolean de la presence du curseur dans le composant
	 */
	public void setCurseurDansComposant(boolean curseurDansComposant) {
		this.curseurDansDessin = curseurDansComposant;
	}
	/**
	 * Setter du boolean de la touche secrete
	 * @param secret boolean de la touche secrete
	 */
	public void setSecret(boolean secret) {
		this.secret = secret;
	}
	/**
	 * Setter de la largeur des rectangles
	 * @param largeurDesRectangles largeur des rectangles
	 */
	public void setLargeurDesRectangles(double largeurDesRectangles) {
		this.largeurDesRectangles = largeurDesRectangles;
	}
	/**
	 * Setter de la coordonnee en x du rectangle
	 * @param xRect la coordonnee en x du rectangle
	 */
	public void setXRect(double xRect) {
		this.xRect = xRect;
	}
	/**
	 * Setter de la coordonnee en y du rectangle
	 * @param yRect la coordonnee en y du rectangle
	 */
	public void setYRect(double yRect) {
		this.yRect = yRect;
	}
	/**
	 * Setter de l'aire totale geometrique
	 * @param aireTotaleGeometrique aire totale geometrique
	 */
	public void setAireTotaleGeometrique(double aireTotaleGeometrique) {
		this.aireTotaleGeometrique = aireTotaleGeometrique;
	}
	/**
	 * Setter du nombre de lignes brisees pour le calcul de la fonction
	 * @param nbLignesBrisees nombre de lignes brisees
	 */
	public void setNombreDeLignesBrisees(int nbLignesBrisees) {
		this.nbLignesBrisees = nbLignesBrisees;
	}
	
	//Getters
	/**
	 * Getter qui retourne le parametre a
	 * @return parametre a
	 */
	public double getParametreA () {
		return(this.parametreA);
	}
	/**
	 * Getter qui retourne le parametre b
	 * @return parametre b
	 */
	public double getParametreB () {
		return(this.parametreB);
	}
	/**
	 * Getter qui retourne le parametre c
	 * @return parametre c
	 */
	public double getParametreC() {
		return(this.parametreC);
	}
	/**
	 * Getter qui retourne le nombre de rectangles
	 * @return le nombre de rectangles
	 */
	public int getNbRectangles () {
		return(this.nbRectangles);
	}
	/**
	 * Getter qui retourne le x minimal
	 * @return le x minimal
	 */
	public double getMinX () {
		return(this.minX);
	}
	/**
	 * Getter qui retourne le x miximal
	 * @return le x maximal
	 */
	public double getMaxX () {
		return(this.maxX);
	}
	/**
	 * Getter qui retourne le y minimal
	 * @return le y minimal
	 */
	public double getMinY () {
		return(this.minY);
	}
	/**
	 * Getter qui retourne le y maximal
	 * @return le y maximal
	 */
	public double getMaxY() {
		return(this.maxY);
	}
	/**
	 * Getter qui retourne le nombre de lignes brisees
	 * @return le nombre de lignes brisees
	 */
	public int getNbLignesBrisees() {
		return(this.nbLignesBrisees);
	}
	/**
	 * Getter qui retourne le parametre a initial
	 * @return le parametre a initial
	 */
	public double getParametreAInitial() {
		return this.parametreAInitial;
	}
	/**
	 * Getter qui retourne le parametre b initial
	 * @return le parametre b initial
	 */
	public double getParametreBInitial() {
		return this.parametreBInitial;
	}
	/**
	 * Getter qui retourne le parametre c initial
	 * @return le parametre c initial
	 */
	public double getParametreCInitial() {
		return this.parametreCInitial;
	}
	/**
	 * Getter qui retourne le nombre de rectangles initial
	 * @return le nombre de rectangles initial
	 */
	public int getNbRectanglesInitial() {
		return this.nbRectanglesInitial;
	}
	/**
	 * Getter qui retourne la valeur max initiale de x 
	 * @return la valeur max initiale de x 
	 */
	public double getMaxXInitial() {
		return this.maxXInitial;
	}
	/**
	 * Getter qui retourne la valeur min initiale de x 
	 * @return la valeur min initiale de x 
	 */
	public double getMinXInitial() {
		return this.minXInitial;
	}
	/**
	 * Getter qui retourne la valeur max initiale de y 
	 * @returnla valeur max initiale de y
	 */
	public double getMaxYInitial() {
		return this.maxYInitial;
	}
	/**
	 * Getter qui retourne la valeur min initiale de y
	 * @returnla valeur min initiale de y
	 */
	public double getMinYInitial() {
		return this.minYInitial;
	}
	/**
	 * Getter qui calcul,retourne et met a jour la valeur de l'aire algebrique
	 * @return l'aire algebrique
	 */
	public double getAireAlg() {
		double aireAlgebrique = (this.parametreA * Math.sin(this.maxX) - this.parametreB * Math.cos(this.maxX) + this.parametreC * this.maxX) - (this.parametreA * Math.sin(this.minX) 
				- this.parametreB * Math.cos(this.minX) + this.parametreC * this.minX);
		this.setAireAlg(aireAlgebrique);
		return(aireAlgebrique);
	}
	/**
	 * Getter qui retourne la valeur de l'aire totale geometrique
	 * @return l'aire totale geometrique
	 */
	public double getAireTotaleGeometrique() {
		return(this.aireTotaleGeometrique);
	}
	/**
	 * Getter qui retourne la valeur de la coordonee du rectangle en x
	 * @returnla  coordonee du rectangle en x
	 */
	public double getXRect() {
		return(xRect);
	}
	/**
	 * Getter qui retourne la valeur de la coordonne du rec tangle en y
	 * @returnla coordonne du rec tangle en y
	 */
	public double getYRect() {
		return(yRect);
	}
	/**
	 * Getter qui retourne la differnce en pourcentage entre l'aire alg. et geo.
	 * @return pourcentage entre l'aire alg. et geo.
	 */
	public double getDifferencePourcentage() {
		this.setDifferencePourecentage((this.aireAlg - this.getAireTotaleGeometrique())/this.aireAlg * 100.0);
		return(differencePourcentage);
	}
	/**
	 * Getter qui retourne la differnce  entre l'aire alg. et geo. en unites
	 * @return la differnce  entre l'aire alg. et geo. en unites
	 */
	public double getDifferenceUnites() {
		this.setDifferenceUnites(this.getAireAlg()-this.getAireTotaleGeometrique());
		return(this.differenceUnites);
	}
	/**
	 * Getter qui retourne le boolean de l'affichage des rectangles
	 * @return le boolean de l'affichage des rectangles
	 */
	public boolean getAffficheRectangles() {
		return(this.afficheRectangles);
	}
	/**
	 * Getter qui retourne le boolean indiquant si le curseur est bien dans le composant graphique voulu
	 * @return le boolean indiquant si le curseur est bien dans le composant graphique voulu
	 */
	public boolean getCurseurDansComposant() {
		return(this.curseurDansDessin);
	}
	/**
	 * Getter qui retourne le boolean indiquant si le secret est actif
	 * @return le boolean indiquant si le secret est actif
	 */
	public boolean getSecret() {
		return(this.secret);
	}
	/**
	 * Getter qui retourne la largeur des rectangles
	 * @return la largeur des rectangles
	 */
	public double getLargeurDesRectangles() {
		this.setLargeurDesRectangles((this.maxX - this.minX)/this.nbRectangles);
		return(this.largeurDesRectangles);
	}
	
	
	//Methodes speciales qui font des calculs
	/**
	 * Cette méthode prend le x pour une fonction et retourne le résultat
	 * @param x
	 * @return Le y de la fonction
	 */
	//Mamadou
	public double evalFonction(double x) {
			return(this.parametreA * Math.cos(x) + this.parametreB * Math.sin(x) + this.parametreC);
	}
	/**
	 * Méthode qui ajoute l'aire de chaque rectangle a l'aire totale afin d'éviter de calculer les coordonées deux fois (cet ajout ce fait lors du dessin de la fonction)
	 */
	//Mamadou
	public void ajouterAireRect() {
		this.setAireTotaleGeometrique(this.getAireTotaleGeometrique() + this.yRect * this.getLargeurDesRectangles());
		System.out.println(this.aireTotaleGeometrique);
	}
}
