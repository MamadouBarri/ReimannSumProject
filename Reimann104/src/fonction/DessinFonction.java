package fonction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import aaplication.ModeleDonnees;
/**
 * Composant de dessin pour representer la fonction, ses axes, les rectangles de la somme de Reimann et les graduations
 * @author Barri, Mamadou et Gayta, Reiner
 *
 */
public class DessinFonction extends JPanel {
	/**
	 * serialVersionUID par défaut
	 */
	private static final long serialVersionUID = 1L;
	//Object du modele de données
	private ModeleDonnees md = new ModeleDonnees();
	//Variables locales pour le dessin
	private Path2D.Double axes, ligneBrisee,grille;
	private Rectangle2D.Double rect;
	private double largeurDuRectangle;
	private double xRectangle;
	private double yRectangle;
	private double maxX = md.getMaxX(), minX = md.getMinY();
	private double maxY = md.getMaxY(), minY = md.getMinY();
	private double valeurDeTranslationEnX;
	private double valeurDeTranslationEnY;
	private double valeurDuZoom;
	private double valeurDuZoomEnX;
	private double valeurDuZoomEnY;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private double posX;
	/**
	 * Constructeur : crée la zone de dessin
	 */
	public DessinFonction() {
		setPreferredSize(new Dimension(300, 200));
		this.setBackground(Color.white);
		setLayout(null);
		//Dessin de la fonction
	}

	/**
	 * Dessine la fonction...
	 * @param g Le conexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		//Appel des méthodes à chaque repaint
		creerAxes();
		creerApproxCourbe();
		creerGrille();
		creerGraduations();
		//Variables calculé à chaque repaint
		largeurDuRectangle =  (maxX - minX) / (double) md.getNbRectangles();
		double demiLongueur = getWidth()/2;
		double demiHauteur = getHeight()/2;
		this.pixelsParUniteX = getWidth()/(maxX-minX);
		this.pixelsParUniteY = getHeight()/(maxY-minY);
		xRectangle = minX + largeurDuRectangle/2.0;
		posX = (minX-maxX)/2 + largeurDuRectangle/2.0;
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	


		//Transformations nécessaires pour afficher la fonction

	    g2d.translate(demiLongueur, demiHauteur);
		AffineTransform atr = new AffineTransform();
		atr.scale(pixelsParUniteX, pixelsParUniteY);
		g2d.scale(1,-1);
		//Dessiner
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON  );   //Adoucissement des contours
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.draw(atr.createTransformedShape(grille));
		g2d.setColor(Color.BLUE);
		g2d.draw(atr.createTransformedShape(axes));
		g2d.setColor(Color.red);
		g2d.draw(atr.createTransformedShape(ligneBrisee));
		//Dessiner rectangles
		g2d.setColor(Color.PINK);
		for(int k =0;k<md.getNbRectangles();k++) {
			creerUnRectangle();
			if(yRectangle <0) {
				g2d.scale(1, -1);
				g2d.fill(atr.createTransformedShape(rect));
				g2d.scale(1, -1);
			}else {
				g2d.fill(atr.createTransformedShape(rect));
			}
		}
	}
	private void creerGraduations() {
		
	}

	public void creerUnRectangle() {
		//Dessiner les rectangles
		yRectangle = this.evalFonction(xRectangle);
		int signe = 1;
		if(yRectangle<0) {
			signe = -1;
		}
		rect = new Rectangle2D.Double(posX - largeurDuRectangle/2.0,signe*valeurDeTranslationEnY, largeurDuRectangle, signe *yRectangle);
		xRectangle +=largeurDuRectangle;
		posX +=largeurDuRectangle;
	}
	/**
	 * La méthode crée la ligne brisée qui va approximmer l'allure de la fonction
	 */
	//Mamadou
	private void creerApproxCourbe() {
		double x,y;
		double nbLignesBrisees = md.getNbLignesBrisees();
		double minX = this.minX ;
		double maxX = this.maxX ;
		double posX = this.minX ;
		ligneBrisee = new Path2D.Double();
		x = minX;
		posX = (minX-maxX)/2;
		y = this.evalFonction(x);
		//Tracer l'approximation
		ligneBrisee.moveTo(posX, y+valeurDeTranslationEnY);
		for(int k =1; k<=nbLignesBrisees ;k++) {
			x = minX + k * (maxX-minX)/nbLignesBrisees;
			posX =(minX-maxX)/2+ k * (maxX-minX)/nbLignesBrisees;
			y = this.evalFonction(x);
			ligneBrisee.lineTo(posX, y+valeurDeTranslationEnY);
		}
	}

	/**
	 * La méthode crée les axes de la fonction 
	 */
	//Mamadou
	private void creerAxes() {
		//Variables
		double posX = (minX-maxX)/2;
		double posY = (minY-maxY)/2;
		//Axes
		axes = new Path2D.Double();
		//Ligne horizontale
		axes.moveTo(posX, valeurDeTranslationEnY);
		axes.lineTo(-posX, valeurDeTranslationEnY);
		//Ligne verticale
		axes.moveTo(0-valeurDeTranslationEnX,posY);
		axes.lineTo(0-valeurDeTranslationEnX,-posY);
	}
	
	/**
	 * Méthode pour créer la grille
	 */
	private void creerGrille() {
		grille = new Path2D.Double();
		double posX = (minX-maxX)/2;
		for (int k=0;k<=maxX-minX;k++) {
			grille.moveTo(posX, -(minY-maxY)/2);
			grille.lineTo(posX, (minY-maxY)/2);
			posX += 1;
		}
		double posY = (minY-maxY)/2;
		for (int i=0;i<=maxX-minX;i++) {
			grille.moveTo((minX-maxX)/2, posY);
			grille.lineTo(-(minX-maxX)/2, posY);
			posY += 1;
		}
	}

	/**
	 * Cette méthode change le pointeur du modèle de données
	 * @param md Le modele de données universel qui sera passé en paramètre dans la classe Application104
	 */
	//Mamadou
	public void setModeleDonnees(ModeleDonnees md) {
		this.md = md;
		repaint();
	}
	/**
	 * Cette méthode prend le x pour une fonction et retourne le résultat
	 * @param x
	 * @return Le y de la fonction
	 */
	//Mamadou
	private double evalFonction(double x) {
		return(md.getParametreA() * Math.cos(x) + md.getParametreB() * Math.sin(x) + md.getParametreC());
	}
	
	/**
	 * Cette méthode premet la translation de la fonction sur l'axe des x
	 * @param x translation en horizontale
	 */
	//Gayta
	public void translationEnX(int x) {
		//changement de la partie de la fonction dessinée
		this.maxX += x;
		this.minX += x;
		//changement de la position de centre du dessin
		this.valeurDeTranslationEnX += x;
		repaint();
	}
	
	public void translationEnY(int y) {
		//changement de la partie de la fonction dessinée
		this.maxY += y;
		this.minY += y;
		//changement de la position de centre du dessin
		this.valeurDeTranslationEnY -= y;
		repaint();
	}
	
	public void resetTranslation() {
		this.maxX = md.getMaxX();
		this.minX = md.getMinX();
		this.maxY = md.getMaxY();
		this.minY = md.getMinY();
		valeurDeTranslationEnX = 0;
		valeurDeTranslationEnY = 0;
		repaint();
	}
	
	public void zoom(double z) {
		this.maxX += z;
		this.minX -= z;
		//Vu qu'on utilise cette équation comme un diviseur, ce "if" prévient une divison par zéro
		if(maxX-minX == 0) {
			this.maxX -= z;
			this.minX += z;
		}
		this.maxY += z;
		this.minY -= z;
		//Vu qu'on utilise cette équation comme un diviseur, ce "if" prévient une divison par zéro
		if(maxY-minY == 0) {
			this.maxY -= z;
			this.minY += z;
		}
		repaint();
	}
}
