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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author 
 *
 */
public class Application104 extends JFrame {

	private JPanel contentPane;
	private JTextField txtfdValeurX;
	private JTextField txtfdValeurY;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public Application104() {
		setTitle("Somme de Reimann");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnFonction = new JPanel();
		pnFonction.setBorder(new TitledBorder(null, "Fonction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnFonction.setBounds(374, 0, 610, 355);
		contentPane.add(pnFonction);
		pnFonction.setLayout(null);
		
		JButton btnResetFonction = new JButton("R\u00E9initialiser la Fonction");
		btnResetFonction.setBounds(381, 310, 209, 34);
		pnFonction.add(btnResetFonction);
		
		txtfdValeurX = new JTextField();
		txtfdValeurX.setBounds(36, 310, 66, 30);
		pnFonction.add(txtfdValeurX);
		txtfdValeurX.setColumns(10);
		
		JLabel lblValeurX = new JLabel("X:");
		lblValeurX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValeurX.setBounds(10, 310, 16, 25);
		pnFonction.add(lblValeurX);
		
		JLabel lblValeurY = new JLabel("Y:");
		lblValeurY.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValeurY.setBounds(112, 310, 16, 25);
		pnFonction.add(lblValeurY);
		
		txtfdValeurY = new JTextField();
		txtfdValeurY.setColumns(10);
		txtfdValeurY.setBounds(138, 310, 66, 30);
		pnFonction.add(txtfdValeurY);
		
		JPanel pnParametres = new JPanel();
		pnParametres.setBorder(new TitledBorder(null, "Param\u00E8tres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnParametres.setBounds(0, 0, 375, 355);
		contentPane.add(pnParametres);
		pnParametres.setLayout(null);
		
		JSpinner spnValeurA = new JSpinner();
		spnValeurA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurA.setBounds(68, 38, 45, 46);
		pnParametres.add(spnValeurA);
		
		JSpinner spnValeurB = new JSpinner();
		spnValeurB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurB.setBounds(189, 38, 45, 46);
		pnParametres.add(spnValeurB);
		
		JSpinner spnValeurC = new JSpinner();
		spnValeurC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spnValeurC.setBounds(308, 38, 45, 46);
		pnParametres.add(spnValeurC);
		
		JLabel lblFonction = new JLabel("F(x) =            cos(x) +             sin(x) +     ");
		lblFonction.setHorizontalAlignment(SwingConstants.LEFT);
		lblFonction.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblFonction.setBounds(15, 38, 355, 46);
		pnParametres.add(lblFonction);
		
		JCheckBox chckbxRectangle = new JCheckBox("Rectangles:");
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
		panel.setBounds(0, 352, 984, 359);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAireAlgbrique = new JLabel("Aire Alg\u00E9brique (u\u00B2) :");
		lblAireAlgbrique.setBounds(10, 11, 305, 76);
		panel.add(lblAireAlgbrique);
		
		JLabel lblAireGeometrique = new JLabel("Aire G\u00E9om\u00E9trique (u\u00B2) :");
		lblAireGeometrique.setBounds(10, 87, 305, 76);
		panel.add(lblAireGeometrique);
		
		JLabel lblDifference = new JLabel("Diff\u00E9rence (u\u00B2) :");
		lblDifference.setBounds(10, 174, 305, 76);
		panel.add(lblDifference);
		
		JLabel lblPourEcart = new JLabel("Pourcentage d'\u00E9cart (%) :");
		lblPourEcart.setBounds(10, 261, 305, 76);
		panel.add(lblPourEcart);
	}
}
