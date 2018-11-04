package aaplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.TitledBorder;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import fonction.DessinFonction;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;

/**
 * Classe de l'application qui affiche une fonction et calcule son aire sous la courbe avec la somme de Reimann
 * @author Mamadou Barri et Gayta, Reiner Luis
 *
 */
public class Application104 extends JFrame {

	private JPanel contentPane;
	private java.net.URL  urlHaut = getClass().getClassLoader().getResource("a_haut.png");
	private java.net.URL  urlBas = getClass().getClassLoader().getResource("a_bas.png");
	private java.net.URL  urlGauche = getClass().getClassLoader().getResource("a_gauche.png");
	private java.net.URL  urlDroite = getClass().getClassLoader().getResource("a_droite.png");
	private java.net.URL  urlZoomIn = getClass().getClassLoader().getResource("ZoomIn (1).png");
	private java.net.URL  urlZoomOut = getClass().getClassLoader().getResource("ZoomOut (1).png");
	
	//Objet du mod�le de donn�es
	private ModeleDonnees md = new ModeleDonnees();
	private JLabel lblAireAlgNumerique;
	private JLabel lblAireGeoNumerique;
	private JLabel lblDifferenceNumerique;
	private JLabel lblPourcentageNumerique;
	private JLabel lblNbRectangles;
	private JSlider sldNbRectangles;
	private JButton btnResetParametres;
	private JLabel lblAireAlgebrique;
	private JLabel lblAireGeometrique;
	private JLabel lblDifference;
	private JLabel lblPourEcart;
	private JPanel panel;
	private JSpinner spnValeurA;
	private JSpinner spnValeurB;
	private JSpinner spnValeurC;
	private JLabel lblFonction;
	
	//variables
	
	private double nbRectanglesCourant;
	/**
	 * Demarrage de l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application104 frame = new Application104();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructeur qui g�n�re l'inferface de l'applicaiton
	 */
	//Reiner
	public Application104() {
		setTitle("Somme de Reimann");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1181, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnFonction = new JPanel();
		pnFonction.setBorder(new TitledBorder(null, "Fonction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnFonction.setBounds(374, 0, 781, 711);
		contentPane.add(pnFonction);
		pnFonction.setLayout(null);
		
		DessinFonction dessinFonction = new DessinFonction();
		dessinFonction.setModeleDonnees(md);
		dessinFonction.setBounds(72, 11, 600, 600);
		pnFonction.add(dessinFonction);
		
		if(urlDroite == null) {
			JOptionPane.showMessageDialog(null, "Fichier a_droite.png introuvable");
			System.exit(0);
		}
		ImageIcon droite = new ImageIcon(urlDroite);
		JButton btnDroite = new JButton(droite);
		btnDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.translationEnX(1);
				miseAJour();
			}
		});
		btnDroite.setBounds(632, 615, 40, 40);
		pnFonction.add(btnDroite);
		
		JButton btnResetFonction = new JButton("R\u00E9initialiser la Fonction");
		btnResetFonction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.resetTranslation();
			}
		});
		btnResetFonction.setBounds(562, 666, 209, 34);
		pnFonction.add(btnResetFonction);
		
		if(urlGauche == null) {
			JOptionPane.showMessageDialog(null, "Fichier a_gauche.png introuvable");
			System.exit(0);
		}
		ImageIcon gauche = new ImageIcon(urlGauche);
		JButton btnGauche = new JButton(gauche);
		btnGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.translationEnX(-1);
				miseAJour();
			}
		});
		btnGauche.setBounds(74, 615, 40, 40);
		pnFonction.add(btnGauche);
		
		if(urlHaut == null) {
			JOptionPane.showMessageDialog(null, "Fichier a_haut.png introuvable");
			System.exit(0);
		}
		ImageIcon haut = new ImageIcon(urlHaut);
		JButton btnHaut = new JButton(haut);
		btnHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.translationEnY(1);
				miseAJour();
			}
		});
		btnHaut.setBounds(682, 11, 40, 40);
		pnFonction.add(btnHaut);
		
		if(urlBas == null) {
			JOptionPane.showMessageDialog(null, "Fichier a_bas.png introuvable");
			System.exit(0);
		}
		ImageIcon bas = new ImageIcon(urlBas);
		JButton btnBas = new JButton(bas);
		btnBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.translationEnY(-1);
				miseAJour();
			}
		});
		btnBas.setBounds(682, 569, 40, 40);
		pnFonction.add(btnBas);
		
		if(urlZoomIn == null) {
			JOptionPane.showMessageDialog(null, "Fichier ZoomIn.png introuvable");
			System.exit(0);
		}
		ImageIcon ZoomIn = new ImageIcon(urlZoomIn);
		JButton btnZoonin = new JButton(ZoomIn);
		btnZoonin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.zoom(-1);
				miseAJour();
			}
		});
		btnZoonin.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnZoonin.setBounds(24, 27, 40, 40);
		pnFonction.add(btnZoonin);
		
		if(urlZoomOut == null) {
			JOptionPane.showMessageDialog(null, "Fichier ZoomOut.png introuvable");
			System.exit(0);
		}
		ImageIcon ZoomOut = new ImageIcon(urlZoomOut);
		JButton btnZoomOut = new JButton(ZoomOut);
		btnZoomOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dessinFonction.zoom(+1);
				miseAJour();
			}
		});
		btnZoomOut.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnZoomOut.setBounds(24, 551, 40, 40);
		pnFonction.add(btnZoomOut);
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "R\u00E9sultats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 352, 375, 359);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAireAlgebrique = new JLabel("Aire Alg\u00E9brique : 3.397u\u00B2");
		lblAireAlgebrique.setBounds(10, 11, 355, 76);
		panel.add(lblAireAlgebrique);
		
		lblAireGeometrique = new JLabel("Aire G\u00E9om\u00E9trique : " + String.format("%.3f",  md.getAireGeo()) + "u\u00B2");
		lblAireGeometrique.setBounds(10, 87, 355, 76);
		panel.add(lblAireGeometrique);
		
		lblDifference = new JLabel("Diff\u00E9rence : "+ String.format("%.3f", md.getDifferneceUnites()) + "u\u00B2");
		lblDifference.setBounds(10, 174, 355, 76);
		panel.add(lblDifference);
		
		lblPourEcart = new JLabel("Pourcentage d'\u00E9cart : " + String.format("%.3f",md.getDifferencePourcentage()) + "%");
		lblPourEcart.setBounds(10, 261, 340, 76);
		panel.add(lblPourEcart);
		
		JPanel pnParametres = new JPanel();
		pnParametres.setBorder(new TitledBorder(null, "Param\u00E8tres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnParametres.setBounds(0, 0, 375, 355);
		contentPane.add(pnParametres);
		pnParametres.setLayout(null);
		
		spnValeurA = new JSpinner();
		spnValeurA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				md.setParametreA((double)spnValeurA.getValue());
				miseAJour();
				dessinFonction.repaint();
			}
		});
		spnValeurA.setModel(new SpinnerNumberModel(md.getParametreA(), null, null, new Integer(1)));
		spnValeurA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurA.setBounds(68, 38, 45, 46);
		pnParametres.add(spnValeurA);
		
		spnValeurB = new JSpinner();
		spnValeurB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				md.setParametreB((double)spnValeurB.getValue());
				miseAJour();
				dessinFonction.repaint();
			}
		});
		spnValeurB.setModel(new SpinnerNumberModel(md.getParametreB(), null, null, new Integer(1)));
		spnValeurB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurB.setBounds(189, 38, 45, 46);
		pnParametres.add(spnValeurB);
		
		spnValeurC = new JSpinner();
		spnValeurC.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				md.setParametreC((double)spnValeurC.getValue());
				miseAJour();
				dessinFonction.repaint();
			}
		});
		spnValeurC.setModel(new SpinnerNumberModel(md.getParametreC(), null, null, new Integer(1)));
		spnValeurC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurC.setBounds(308, 38, 45, 46);
		pnParametres.add(spnValeurC);
		
		lblFonction = new JLabel("F(x) =            cos(x) +             sin(x) +     ");
		lblFonction.setHorizontalAlignment(SwingConstants.LEFT);
		lblFonction.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFonction.setBounds(15, 38, 355, 46);
		pnParametres.add(lblFonction);
		
		JCheckBox chckbxRectangle = new JCheckBox("Rectangles:");
		chckbxRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxRectangle.isSelected()) {
					md.setNbRectangles((int)nbRectanglesCourant);
					sldNbRectangles.setForeground(Color.BLACK);
					md.setAfficheRectangles(true);
					miseAJour();
				}else {
					nbRectanglesCourant = md.getNbRectangles();
					md.setNbRectangles(0);
					sldNbRectangles.setForeground(Color.LIGHT_GRAY);
					md.setAfficheRectangles(false);
					lblAireGeometrique.setText("Aire G\u00E9om\u00E9trique : ");
					lblDifference.setText("Diff\u00E9rence : ");
					lblPourEcart.setText("Pourcentage d'\u00E9cart : ");
				}
				dessinFonction.repaint();
			}
		});
		chckbxRectangle.setSelected(true);
		chckbxRectangle.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		chckbxRectangle.setBounds(6, 101, 135, 26);
		pnParametres.add(chckbxRectangle);
		
		lblNbRectangles = new JLabel("10");
		lblNbRectangles.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblNbRectangles.setBackground(Color.WHITE);
		lblNbRectangles.setBounds(172, 93, 74, 46);
		pnParametres.add(lblNbRectangles);
		
		sldNbRectangles = new JSlider();
		sldNbRectangles.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(chckbxRectangle.isSelected()) {
					md.setNbRectangles((int)sldNbRectangles.getValue());
					lblNbRectangles.setText(md.getNbRectangles() + "");
					miseAJour();
					dessinFonction.repaint();
				} else {
					sldNbRectangles.setValue((int)nbRectanglesCourant);
				}
			}
		});
		sldNbRectangles.setValue(10);
		sldNbRectangles.setMajorTickSpacing(20);
		sldNbRectangles.setPaintLabels(true);
		sldNbRectangles.setMinorTickSpacing(1);
		sldNbRectangles.setMaximum(200);
		sldNbRectangles.setBounds(6, 176, 364, 60);
		pnParametres.add(sldNbRectangles);
		
		btnResetParametres = new JButton("R\u00E9initialiser les Param\u00E8tres");
		btnResetParametres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				md.setParametreA(md.getParametreAInitiale());
				md.setParametreB(md.getParametreBInitiale());
				md.setParametreC(md.getParametreCInitiale());
				md.setNbRectangles(md.getNbRectanglesInitiales());
				sldNbRectangles.setValue(md.getNbRectangles());
				dessinFonction.setModeleDonnees(md);
				miseAJour();
			}
		});
		btnResetParametres.setBounds(6, 281, 364, 38);
		pnParametres.add(btnResetParametres);
		
		
	}
	/**
	 * Methode qui met a jour toutes les informations dans l'application
	 */
	//Mamadou
	public void miseAJour() {
		lblAireAlgebrique.setText("Aire Alg\u00E9brique : " + String.format("%.3f", md.getAireAlg())+"u\u00B2");
	    lblAireGeometrique.setText("Aire G\u00E9om\u00E9trique : " + String.format("%.3f",  md.getAireGeo()) + "u\u00B2");
		lblDifference.setText("Diff\u00E9rence : "+String.format("%.3f", md.getDifferneceUnites()) +"u\u00B2");
		lblPourEcart.setText("Pourcentage d'\u00E9cart : " + String.format("%.3f",md.getDifferencePourcentage()) + "%");
		lblNbRectangles.setText(md.getNbRectangles()+"");
		spnValeurA.setValue(md.getParametreA());
		spnValeurB.setValue(md.getParametreB());
		spnValeurC.setValue(md.getParametreC());
	}
	/**
	 * Methode qui enleve toutes les composantes graphiques visibles se rapportant aux rectangles
	 */
	//Mamadou
	public void enleverComposantesRectangles() {
		lblNbRectangles.setVisible(false);
		sldNbRectangles.setVisible(false);
		lblAireGeometrique.setVisible(false);
		lblDifference.setVisible(false);
		lblPourEcart.setVisible(false);
	}
	/**
	 * Methode qui remet toutes les composantes graphiques visibles se rapportant aux rectangles
	 */
	public void remettreComposantesRectangles() {
		lblNbRectangles.setVisible(true);
		sldNbRectangles.setVisible(true);
		lblAireGeometrique.setVisible(true);
		lblDifference.setVisible(true);
		lblPourEcart.setVisible(true);
	}

}
