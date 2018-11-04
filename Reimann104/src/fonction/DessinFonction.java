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
	
	private double valeurDeTranslationEnX;
	private double valeurDeTranslationEnY;
	private double valeurDuZoom;
	private double valeurDuZoomEnX;
	private double valeurDuZoomEnY;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private double posX;
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	
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
		this.maxX = md.getMaxX();
		this.minX = md.getMinY();
		this.maxY = md.getMaxY();
		this.minY = md.getMinY();
		//Appel des méthodes à chaque repaint
		creerAxes();
		creerApproxCourbe();
		creerGrille();
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
		
		AffineTransform atr2 = g2d.getTransform();

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
		g2d.setTransform(atr2);
		g2d.setColor(Color.BLACK);
		creerGraduations(g2d);
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
		double x = md.getMinX();
		maxX = md.getMaxX();
		minX = md.getMinX();
		double y;
		double nbLignesBrisees = md.getNbLignesBrisees();

		double posX = this.minX ;
		ligneBrisee = new Path2D.Double();
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
	
	private void creerGraduations(Graphics g) {
		int posX = 0;
		int valeurX = (int)md.getMinX();
		for(int k=0; k<md.getMaxX()-md.getMinX();k++) {
			g.drawString(valeurX+"", posX, getHeight()/2+10);
			valeurX++;
			posX+=pixelsParUniteX;
		}
		g.drawString(valeurX+"", posX-14, getHeight()/2+10);
		
		
	}
	
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
		md.setMaxX(maxX + x);
		md.setMinX(minX + x);
		//changement de la position de centre du dessin
		this.valeurDeTranslationEnX += x;
		repaint();
	}
	
	public void translationEnY(int y) {
		//changement de la partie de la fonction dessinée
		md.setMaxY(maxY + y);
		md.setMinY(minY + y);
		//changement de la position de centre du dessin
		this.valeurDeTranslationEnY -= y;
		repaint();
	}
	
	public void resetTranslation() {
		
		valeurDeTranslationEnX = 0;
		valeurDeTranslationEnY = 0;
		repaint();
	}
	
	public void zoom(double z) {
		if((md.getMaxX() - md.getMinX() + z*2) != 0) {
			md.setMaxX(md.getMaxX() + z);
			md.setMinX(md.getMinX() - z);
			md.setMaxY(md.getMaxY() + z);
			md.setMinY(md.getMinY() - z);
		}
		repaint();
	}
}
