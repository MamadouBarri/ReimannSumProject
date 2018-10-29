package fonction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import aaplication.ModeleDonnees;
/**
 * Dans cette classe on dessine la fonction, ses axes, les rectangles de la somme de Reimann et les graduations
 * @author Mamadou Barri
 *
 */
public class DessinFonction extends JPanel {
	/**
	 * serialVersionUID par d�faut
	 */
	private static final long serialVersionUID = 1L;
	//Object du modele de donn�es
	private ModeleDonnees md = new ModeleDonnees();
	//Variables locales pour le dessin
	private Path2D.Double axes, ligneBrisee;
	private Rectangle2D.Double rect;
	private double largeurDuRectangle;
	private double xRectangle;
	private double yRectangle;
	private int maxX = md.getMaxX(), minX = md.getMinY();
	private int maxY = md.getMaxY(), minY = md.getMinY();
	private double valeurDeTranslationEnX;
	private double valeurDeTranslationEnY;
	/**
	 * Create the panel.
	 */
	public DessinFonction() {
		setPreferredSize(new Dimension(300, 200));
		this.setBackground(Color.white);
		setLayout(null);
		//Dessin de la fonction
	}

	/**
	 * Redefinit la methode de dessin
	 * @param g Le conexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		//Appel des m�thodes � chaque repaint
		creerAxes();
		creerApproxCourbe();
		//Variables calcul� � chaque repaint
		largeurDuRectangle =  (maxX - minX) / (double) md.getNbRectangles();
		double demiLongueur = getWidth()/2+valeurDeTranslationEnX;
		double demiHauteur = getHeight()/2+valeurDeTranslationEnY;
		double pixelsParUniteX = getWidth()/(maxX-minX);
		double pixelsParUniteY = getHeight()/(maxY-minY);
		xRectangle = minX + largeurDuRectangle/2.0;
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	


		//Transformations n�cessaires pour afficher la fonction
		g2d.translate(demiLongueur, demiHauteur);
		AffineTransform atr = new AffineTransform();
		atr.scale(pixelsParUniteX, pixelsParUniteY);
		g2d.scale(1,-1);
		//Dessiner
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON  );   //Adoucissement des contours
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
	public void creerUnRectangle() {
		//Dessiner les rectangles
		yRectangle = this.evalFonction(xRectangle);
		int signe = 1;
		if(yRectangle<0) {
			signe = -1;
		}
		rect = new Rectangle2D.Double(xRectangle - largeurDuRectangle/2.0,0, largeurDuRectangle, signe *yRectangle);
		xRectangle +=largeurDuRectangle;
	}
	/**
	 * La m�thode cr�e la ligne bris�e qui va approximmer l'allure de la fonction
	 */
	private void creerApproxCourbe() {
		double x,y;
		double nbLignesBrisees = md.getNbLignesBrisees();
		ligneBrisee = new Path2D.Double();
		x = minX;
		y = this.evalFonction(x);
		//Tracer l'approximation
		ligneBrisee.moveTo(x, y);
		for(int k =1; k<=nbLignesBrisees ;k++) {
			x = minX + k * (maxX-minX)/nbLignesBrisees;
			y = this.evalFonction(x);
			ligneBrisee.lineTo(x, y);
		}
	}

	/**
	 * La m�thode cr�e les axes de la fonction 
	 */
	private void creerAxes() {
		//Variables

		//Axes
		axes = new Path2D.Double();
		//Ligne horizontale
		axes.moveTo(minX, 0);
		axes.lineTo(maxX, 0);
		//Ligne verticale
		axes.moveTo(0, minY);
		axes.lineTo(0, maxY);
	}

	/**
	 * Cette m�thode change le pointeur du mod�le de donn�es
	 * @param md Le modele de donn�es universel qui sera pass� en param�tre dans la classe Application104
	 */
	public void setModeleDonnees(ModeleDonnees md) {
		this.md = md;
		repaint();
	}
	/**
	 * Cette m�thode prend le x pour une fonction et retourne le r�sultat
	 * @param x
	 * @return Le y de la fonction
	 */
	private double evalFonction(double x) {
		return(md.getParametreA() * Math.cos(x) + md.getParametreB() * Math.sin(x) + md.getParametreC());
	}
	
	/**
	 * Cette m�thode premet la translation de la fonction sur l'axe des x
	 * @author Gayta
	 */
	
	public void translationEnX(int x) {
		//changement de la partie de la fonction dessin�e
		this.maxX = this.maxX+x;
		this.minX = this.minX+x;
		//changement de la position de centre du dessin
		double pixelsParUniteX = getWidth()/(minX-maxX);
		this.valeurDeTranslationEnX = this.valeurDeTranslationEnX+pixelsParUniteX*x;
		repaint();
	}
	
	public void translationEnY(int y) {
		//changement de la partie de la fonction dessin�e
		this.maxY = this.maxY+y;
		this.minY = this.minY+y;
		//changement de la position de centre du dessin
		double pixelsParUniteY = getHeight()/(maxY-minY);
		this.valeurDeTranslationEnY = this.valeurDeTranslationEnY+pixelsParUniteY*y;
		repaint();
	}

}
