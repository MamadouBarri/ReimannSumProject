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
	 * serialVersionUID par défaut
	 */
	private static final long serialVersionUID = 1L;
	//Object du modele de données
	private ModeleDonnees md = new ModeleDonnees();
	//Variables locales pour le dessin
	private Path2D.Double axes, ligneBrisee;
	private Rectangle2D.Double rect;
	private double largeurDuRectangle;
	private double xRectangle;
	private double yRectangle;
	/**
	 * Create the panel.
	 */
	public DessinFonction() {
		setPreferredSize(new Dimension(300, 200));
		this.setBackground(Color.white);
		largeurDuRectangle =  (md.getMaxX() - md.getMinX()) / (double) md.getNbRectangles();
		setLayout(null);
		//Dessin de la fonction
	}

	/**
	 * Redefinit la methode de dessin
	 * @param g Le conexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		//Appel des méthodes à chaque repaint
		creerAxes();
		creerApproxCourbe();
		//Variables calculé à chaque repaint
		double demiLongueur = getWidth()/2;
		double demiHauteur = getHeight()/2;
		double pixelsParUniteX = getWidth()/(md.getMaxX()-md.getMinX());
		double pixelsParUniteY = getHeight()/(md.getMaxY()-md.getMinY());
		xRectangle = md.getMinX() + largeurDuRectangle/2.0;
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;	


		//Transformations nécessaires pour afficher la fonction
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
	 * La méthode crée la ligne brisée qui va approximmer l'allure de la fonction
	 */
	private void creerApproxCourbe() {
		double x,y;
		double nbLignesBrisees = md.getNbLignesBrisees();
		ligneBrisee = new Path2D.Double();
		x = md.getMinX();
		y = this.evalFonction(x);
		//Tracer l'approximation
		ligneBrisee.moveTo(x, y);
		for(int k =1; k<=nbLignesBrisees ;k++) {
			x = md.getMinX() + k * (md.getMaxX()-md.getMinX())/nbLignesBrisees;
			y = this.evalFonction(x);
			ligneBrisee.lineTo(x, y);
		}
	}

	/**
	 * La méthode crée les axes de la fonction 
	 */
	private void creerAxes() {
		//Variables

		//Axes
		axes = new Path2D.Double();
		//Ligne horizontale
		axes.moveTo(md.getMinX(), 0);
		axes.lineTo(md.getMaxX(), 0);
		//Ligne verticale
		axes.moveTo(0, md.getMinY());
		axes.lineTo(0, md.getMaxY());
	}

	/**
	 * Cette méthode change le pointeur du modèle de données
	 * @param md Le modele de données universel qui sera passé en paramètre dans la classe Application104
	 */
	public void setModeleDonnees(ModeleDonnees md) {
		this.md = md;
		repaint();
	}
	/**
	 * Cette méthode prend le x pour une fonction et retourne le résultat
	 * @param x
	 * @return Le y de la fonction
	 */
	private double evalFonction(double x) {
		return(md.getParametreA() * Math.cos(x) + md.getParametreB() * Math.sin(x) + md.getParametreC());
	}

}
