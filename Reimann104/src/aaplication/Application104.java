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

/**
 * Classe de l'application qui affiche une fonction et calcule son aire sous la courbe avec la somme de Reimann
 * @author Mamadou Barri et Gayta, Reiner Luis
 *
 */
public class Application104 extends JFrame {

	private JPanel contentPane;
	private JTextField txtfdValeurX;
	private JTextField txtfdValeurY;
	private java.net.URL  urlHaut = getClass().getClassLoader().getResource("a_haut.png");
	private java.net.URL  urlBas = getClass().getClassLoader().getResource("a_bas.png");
	private java.net.URL  urlGauche = getClass().getClassLoader().getResource("a_gauche.png");
	private java.net.URL  urlDroite = getClass().getClassLoader().getResource("a_droite.png");
	private java.net.URL  urlZoomIn = getClass().getClassLoader().getResource("ZoomIn (1).png");
	private java.net.URL  urlZoomOut = getClass().getClassLoader().getResource("ZoomOut (1).png");
	
	//Objet du modèle de données
	private ModeleDonnees md = new ModeleDonnees();
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
	 * Constructeur qui génère l'inferface de l'applicaiton
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
		
		txtfdValeurX = new JTextField();
		txtfdValeurX.setBounds(24, 673, 40, 30);
		pnFonction.add(txtfdValeurX);
		txtfdValeurX.setColumns(10);
		
		JLabel lblValeurX = new JLabel("X:");
		lblValeurX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValeurX.setBounds(6, 673, 16, 25);
		pnFonction.add(lblValeurX);
		
		JLabel lblValeurY = new JLabel("Y:");
		lblValeurY.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValeurY.setBounds(74, 673, 16, 25);
		pnFonction.add(lblValeurY);
		
		txtfdValeurY = new JTextField();
		txtfdValeurY.setColumns(10);
		txtfdValeurY.setBounds(91, 673, 40, 30);
		pnFonction.add(txtfdValeurY);
		
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
			}
		});
		btnZoomOut.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnZoomOut.setBounds(24, 551, 40, 40);
		pnFonction.add(btnZoomOut);
		
		
		
		JPanel pnParametres = new JPanel();
		pnParametres.setBorder(new TitledBorder(null, "Param\u00E8tres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnParametres.setBounds(0, 0, 375, 355);
		contentPane.add(pnParametres);
		pnParametres.setLayout(null);
		
		JSpinner spnValeurA = new JSpinner();
		spnValeurA.setModel(new SpinnerNumberModel(md.getParametreA(), null, null, new Integer(1)));
		spnValeurA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurA.setBounds(68, 38, 45, 46);
		pnParametres.add(spnValeurA);
		
		JSpinner spnValeurB = new JSpinner();
		spnValeurB.setModel(new SpinnerNumberModel(md.getParametreB(), null, null, new Integer(1)));
		spnValeurB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurB.setBounds(189, 38, 45, 46);
		pnParametres.add(spnValeurB);
		
		JSpinner spnValeurC = new JSpinner();
		spnValeurC.setModel(new SpinnerNumberModel(md.getParametreC(), null, null, new Integer(1)));
		spnValeurC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurC.setBounds(308, 38, 45, 46);
		pnParametres.add(spnValeurC);
		
		JLabel lblFonction = new JLabel("F(x) =            cos(x) +             sin(x) +     ");
		lblFonction.setHorizontalAlignment(SwingConstants.LEFT);
		lblFonction.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFonction.setBounds(15, 38, 355, 46);
		pnParametres.add(lblFonction);
		
		JCheckBox chckbxRectangle = new JCheckBox("Rectangles:");
		chckbxRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxRectangle.isSelected()) {
					//md.setAfficheRectangle(true);
				}else {
					//md.setAfficheRectangle(false);
				}
			}
		});
		chckbxRectangle.setSelected(true);
		chckbxRectangle.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		chckbxRectangle.setBounds(6, 101, 135, 26);
		pnParametres.add(chckbxRectangle);
		
		JSlider sldNbRectangles = new JSlider();
		sldNbRectangles.setBounds(6, 151, 364, 60);
		pnParametres.add(sldNbRectangles);
		
		JButton btnResetParametres = new JButton("R\u00E9initialiser les Param\u00E8tres");
		btnResetParametres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnResetParametres.setBounds(6, 222, 364, 38);
		pnParametres.add(btnResetParametres);
		
		JLabel lblNbSegments = new JLabel("Nombre de Segments sur la courbe:");
		lblNbSegments.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNbSegments.setBounds(6, 271, 364, 26);
		pnParametres.add(lblNbSegments);
		
		JSlider sldNbSegements = new JSlider();
		sldNbSegements.setBounds(10, 318, 360, 26);
		pnParametres.add(sldNbSegements);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "R\u00E9sultats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 352, 375, 359);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAireAlgbrique = new JLabel("Aire Alg\u00E9brique (u\u00B2) :");
		lblAireAlgbrique.setBounds(10, 11, 121, 76);
		panel.add(lblAireAlgbrique);
		
		JLabel lblAireGeometrique = new JLabel("Aire G\u00E9om\u00E9trique (u\u00B2) :");
		lblAireGeometrique.setBounds(10, 87, 121, 76);
		panel.add(lblAireGeometrique);
		
		JLabel lblDifference = new JLabel("Diff\u00E9rence (u\u00B2) :");
		lblDifference.setBounds(10, 174, 121, 76);
		panel.add(lblDifference);
		
		JLabel lblPourEcart = new JLabel("Pourcentage d'\u00E9cart (%) :");
		lblPourEcart.setBounds(10, 261, 136, 76);
		panel.add(lblPourEcart);
		
		JLabel lblAireAlgNumerique = new JLabel("New label");
		lblAireAlgNumerique.setBounds(141, 42, 46, 14);
		lblAireAlgNumerique.setText(String.format("%.3f", md.getAireAlg()));
		panel.add(lblAireAlgNumerique);
		
		JLabel lblAireGeoNumerique = new JLabel("New label");
		lblAireGeoNumerique.setBounds(141, 118, 46, 14);
		lblAireGeoNumerique.setText(String.format("%.3f",  md.getAireGeo()));
		panel.add(lblAireGeoNumerique);
		
		JLabel lblDifferenceNumerique = new JLabel("New label");
		lblDifferenceNumerique.setBounds(128, 205, 46, 14);
		//lblDifferenceNumerique.setText(String.format("%.3f", md.getDifferenceNumerique) );
		panel.add(lblDifferenceNumerique);
		
		JLabel lblPourcentageNumerique = new JLabel("New label");
		lblPourcentageNumerique.setBounds(175, 292, 46, 14);
		//lblPourcentageNumerique.setText(String.format("%.3f",md.getPourecentageNumerique));
		panel.add(lblPourcentageNumerique);
	}
}
