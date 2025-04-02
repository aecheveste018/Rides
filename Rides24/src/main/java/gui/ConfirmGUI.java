package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Driver;
import domain.Passenger;
import domain.Ride;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Collection;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel<Ride> ridesModel = new DefaultComboBoxModel<Ride>();
	private DefaultComboBoxModel<Passenger> passengerModel = new DefaultComboBoxModel<Passenger>();
	private Driver driver;
	private Ride selectedRide;
	private Passenger currentPassenger;
	private static BLFacadeImplementation BLF;
	public static BLFacade getBusinessLogic(){
		return BLF;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		BLF=(BLFacadeImplementation) afi;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConfirmGUI frame = new ConfirmGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ConfirmGUI(Driver d) {
		driver = d;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnAcept = new JButton("Aceptar solicitud");
		btnAcept.setEnabled(false);
		btnAcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLF.aproveRide(selectedRide, currentPassenger);
			}
		});
		btnAcept.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAcept.setBounds(137, 233, 149, 20);
		contentPane.add(btnAcept);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JComboBox comboBoxPassenger = new JComboBox();
		comboBoxPassenger.setEnabled(false);
		passengerModel.removeAllElements();
		Passenger[] pList = selectedRide.getBooked();
		for(Passenger p : pList) {
			passengerModel.addElement(p);
		}
		comboBoxPassenger.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					currentPassenger = (Passenger) comboBoxPassenger.getSelectedItem();
					btnAcept.setEnabled(true);
				}
			}
		});
		
		comboBoxPassenger.setBounds(39, 162, 352, 21);
		contentPane.add(comboBoxPassenger);
		
		JLabel lblNewLabel = new JLabel("Selecciones el viaje a confirmar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(39, 43, 223, 20);
		contentPane.add(lblNewLabel);
		
		
		
		JComboBox comboBoxRides = new JComboBox();
		
		comboBoxRides.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()== ItemEvent.SELECTED) {
					selectedRide = (Ride) comboBoxRides.getSelectedItem();
					comboBoxPassenger.setEnabled(true);
					
				}
			}
		});
		
		ridesModel.removeAllElements();
		Collection<Ride> rList = d.getRides();
		for(Ride r : rList) {
			ridesModel.addElement(r);
		}
		
		comboBoxRides.setModel(ridesModel);
		comboBoxRides.setBounds(39, 73, 352, 21);
		contentPane.add(comboBoxRides);
		
		JLabel lblSeleccioneElPasajero = new JLabel("Seleccione el pasajero");
		lblSeleccioneElPasajero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneElPasajero.setBounds(39, 122, 223, 20);
		contentPane.add(lblSeleccioneElPasajero);
		
		
		
	}
}
