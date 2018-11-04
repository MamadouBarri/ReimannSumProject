package fonction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

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
	//Variables locales pour le dessin dans lesquels on assigne les valeurs du modèle de données
	private Path2D.Double axes, ligneBrisee,grille;
	private Rectangle2D.Double rect;
	private double largeurDuRectangle;
	private double valeurDeTranslationEnX;
	private double valeurDeTranslationEnY;
	private double pixelsParUniteX;
	private double pixelsParUniteY;
	private double posX;
	private double minX;
	private double maxX;
	private double minY;
	private double maxY;
	private double valeurDeZoom;
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
	 * Dessine la fonction avec les axes, les graduations, les lignes brises et les rectangles de la somme de Reimann au besoin.
	 * @param g Le conexte graphique
	 */
	@Override
	public void paintComponent(Graphics g) {
		//variables pour la touche secrete
		Random rand = new Random();
		Color couleurAleatoire;
		this.maxX = md.getMaxX();
		this.minX = md.getMinY();
		this.maxY = md.getMaxY();
		this.minY = md.getMinY();
		//Appel des méthodes à chaque repaint
		//On crée ici les axes, la grille, la fonction et remet à zero l'aire géometrique
		creerAxes();
		creerApproxCourbe();
		creerGrille();
		//Variables calculé à chaque repaint
		double demiLongueur = getWidth()/2;
		double demiHauteur = getHeight()/2;
		this.pixelsParUniteX = getWidth()/(maxX-minX);
		this.pixelsParUniteY = getHeight()/(maxY-minY);
		md.setXRect(minX + md.getLargeurDesRectangles()/2);
		posX = (minX-maxX)/2 + md.getLargeurDesRectangles()/2.0;
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
		g2d.setColor(Color.red);
		g2d.draw(atr.createTransformedShape(ligneBrisee));
		//Dessiner rectangles
		g2d.setColor(new Color(1f,0.7f,0.75f,0.5f));
		dessinerTousLesRectangles(g2d, atr);
		g2d.setColor(Color.BLUE);
		g2d.draw(atr.createTransformedShape(axes));
		g2d.setTransform(atr2);
		g2d.setColor(Color.BLACK);
		creerGraduations(g2d);
	}

	/**
	 * La méthode qui dessine tous les rectangles pour la fonction et qui s'occupe aussi de colorier les rectangles pour la touche secrète
	 * @param g2d Le contexte grahpique
	 * @param atr La matrice affine de transformation
	 */
	//Mamadou
	public void dessinerTousLesRectangles(Graphics2D g2d, AffineTransform atr) {
		Random rand = new Random();
		Color couleurAleatoire;
		md.setAireTotaleGeometrique(0);
		if(md.getAffficheRectangles()) {
			for(int k =0;k<md.getNbRectangles();k++) {
				//La touche secrete
				if(md.getSecret()) {
					float a = rand.nextFloat();
					float b = rand.nextFloat();
					float c = rand.nextFloat();
					couleurAleatoire = new Color(a,b,c);
					g2d.setColor(couleurAleatoire);
				}
				creerUnRectangle();
				//Renvverser lorsque le y est négatif et créer le rectange avec la transformation
				if(md.getYRect() <0) {
					g2d.scale(1, -1);
					g2d.fill(atr.createTransformedShape(rect));
					g2d.scale(1, -1);
				}else {
					g2d.fill(atr.createTransformedShape(rect));
				}
			}
		}
	}
	/**
	 * Méthode qui crée un seul rectangle
	 */
	//Mamadou
	public void creerUnRectangle() {
		//Dessiner les rectangles
		double largeurRect = md.getLargeurDesRectangles();
		//yRectangle = md.evalFonction(xRectangle);
		md.setYRect(md.evalFonction(md.getXRect()));
		int signe = 1;
		//Lorsque le y est negatif
		if(md.getYRect()<0) {
			signe = -1;
		}
		//On crée le rectangle
		rect = new Rectangle2D.Double(posX - largeurRect/2.0,signe*valeurDeTranslationEnY, largeurRect, signe *md.getYRect());
		md.ajouterAireRect();
		md.setXRect(md.getXRect() + largeurRect);
		posX +=largeurRect;
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
		y = md.evalFonction(x);
		//Tracer l'approximation
		ligneBrisee.moveTo(posX, y+valeurDeTranslationEnY);
		for(int k =1; k<=nbLignesBrisees ;k++) {
			x = minX + k * (maxX-minX)/nbLignesBrisees;
			posX =(minX-maxX)/2+ k * (maxX-minX)/nbLignesBrisees;
			y = md.evalFonction(x);
			ligneBrisee.lineTo(posX, y+valeurDeTranslationEnY);
		}
	}
	/**
	 * La méthode crée les axes de la fonction 
	 */
	//Mamadou
	private void creerAxes() {
		//Variables
		double posX = (md.getMinX()-md.getMaxX())/2;
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
	private void creerGraduations(Graphics2D g) {
		//changement de la taille des String selon le zoom appliqué
		Font currentFont = g.getFont();
			if(currentFont.getSize() - (float)valeurDeZoom>0) {
				Font newFont = currentFont.deriveFont(currentFont.getSize() - (float)valeurDeZoom);
				g.setFont(newFont);
			} else {
					Font newFont = currentFont.deriveFont((float)0.09);
					g.setFont(newFont);
			}
		//création des graduation en X
		float posX = 0;
		int valeurX = (int)md.getMinX();
		for(int k=0; k<=md.getMaxX()-md.getMinX();k++) {
			//dessine la dernière graduation en X
			if (k==md.getMaxX()-md.getMinX()) {
				g.drawString(valeurX+"", (float)(posX-19+valeurDeZoom), getHeight()/2+10-(float)(valeurDeTranslationEnY*pixelsParUniteY));
			}
			g.drawString(valeurX+"", (float)posX, getHeight()/2+10-(float)(valeurDeTranslationEnY*pixelsParUniteY));
			valeurX++;
			posX+=pixelsParUniteX;
		}
		//création des graduations en Y
		float posY = 0;
		int valeurY = (int)md.getMaxY();
		for(int i=0; i<=md.getMaxY()-md.getMinY();i++) {
			//dessine la première graduation en Y
			if(md.getMaxY()!=0&&i==0) {
				g.drawString(valeurY+"", getWidth()/2-(float)(valeurDeTranslationEnX*pixelsParUniteX)+5, (float)(12));
			}
			if(valeurY!=0||md.getMinY()==0) {
				g.drawString(valeurY+"", getWidth()/2-(float)(valeurDeTranslationEnX*pixelsParUniteX)+5, (float)posY);
			}
			valeurY--;
			posY+=pixelsParUniteY;
		}
		
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
		md.setMaxX(md.getMaxXInitial());
		md.setMinX(md.getMinXInitial());
		md.setMaxY(md.getMaxYInitial());
		md.setMinY(md.getMinYInitial());
		valeurDeTranslationEnX = 0;
		valeurDeTranslationEnY = 0;
		valeurDeZoom = 0;
		repaint();
	}
	
	public void zoom(double z) {
		if((md.getMaxX() - md.getMinX() + z*2) != 0) {
			md.setMaxX(md.getMaxX() + z);
			md.setMinX(md.getMinX() - z);
			md.setMaxY(md.getMaxY() + z);
			md.setMinY(md.getMinY() - z);
			valeurDeZoom+=z;
		}
		repaint();
	}
}
