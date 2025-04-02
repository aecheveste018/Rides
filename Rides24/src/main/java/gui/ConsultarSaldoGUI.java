package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Driver;
import domain.Passenger;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ConsultarSaldoGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton ConsultarSaldo;
	private JButton volver;
	private JButton jButtonQueryQueries = null;
	private JButton RecargarMonedero;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * This is the default constructor
	 */
	public ConsultarSaldoGUI(Passenger p) {
		super();
		setTitle("Monedero");

		
		// this.setSize(271, 295);
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
	
		panel = new JPanel();
		
		ConsultarSaldo = new JButton();
		ConsultarSaldo.setText("Consultar mi saldo");
		ConsultarSaldo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Su saldo es de: "+p.getMonedero(), "Aceptar", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		volver = new JButton();
		volver.setText("Volver");
		volver.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				dispose();
			}
		});
		
		RecargarMonedero = new JButton();
		RecargarMonedero.setText("Recargar monedero");
		RecargarMonedero.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new RecargarMonederoGUI(p);

				a.setVisible(true);
			}
		});
		
		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
		jContentPane.add(ConsultarSaldo);
		jContentPane.add(RecargarMonedero);
		jContentPane.add(volver);
		jContentPane.add(panel);
		
		
		setContentPane(jContentPane);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
	

	
} // @jve:decl-index=0:visual-constraint="0,0"

