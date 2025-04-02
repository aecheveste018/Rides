package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Passenger;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecargarMonederoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField monedero;
	private Passenger passenger;
	private JButton btnNewButton= new JButton("Aceptar");;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        Passenger passenger = new Passenger();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecargarMonederoGUI frame = new RecargarMonederoGUI(passenger);
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
	public RecargarMonederoGUI(Passenger p) {
		passenger = p;
		
		setTitle("RecargarMonedero");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Indique la cantidad de dinero que desee recargar");
		lblNewLabel.setBounds(10, 11, 261, 35);
		contentPane.add(lblNewLabel);
		
		monedero = new JTextField();

		
		monedero.setBounds(10, 46, 86, 20);
		contentPane.add(monedero);
		monedero.setColumns(10);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int recarga= Integer.parseInt(monedero.getText());
				if(recarga>0){
					TarjetaGUI t=new TarjetaGUI(recarga,p);
					t.setVisible(true);
					//passenger.setMonedero((double)aux);
					dispose();
				}
			}
		});
		
		
		btnNewButton.setBounds(182, 180, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}
