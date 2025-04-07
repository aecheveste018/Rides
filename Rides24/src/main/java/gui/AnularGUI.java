package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Passenger;
import domain.Ride;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AnularGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Ride currentRide; 
	private DefaultComboBoxModel<Ride> ridesModel = new DefaultComboBoxModel<Ride>();
	private Passenger pas;
	   private static BLFacade appFacadeInterface;
		
		public static BLFacade getBusinessLogic(){
			return appFacadeInterface;
		}
		 
		public static void setBussinessLogic (BLFacade afi){
			appFacadeInterface=afi;
		}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AnularGUI frame = new AnularGUI();
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
	public AnularGUI(Passenger p) {
		pas = p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//ridesModel.removeAllElements();
		ridesModel.removeAllElements();
		for(Ride r : pas.getReservas()) {
			ridesModel.addElement(r);
		}
		//currentRide = (Ride) e.getItem();
	
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ridesModel.removeAllElements();
				for(Ride r : pas.getReservas()) {
					ridesModel.addElement(r);
				}
			//	currentRide = (Ride) e.get;
				comboBox.setModel(ridesModel);
			}
		});
		comboBox.setModel(ridesModel);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
//				ridesModel.removeAllElements();
//				for(Ride r : pas.getReservas()) {
//					ridesModel.addElement(r);
//				}
				currentRide = (Ride) e.getItem();
//				comboBox.setModel(ridesModel);
				//for(Ride r : currentRide.get)
				
				//currentRide.retireBook(pas);
				
			}
		});
		comboBox.setBounds(34, 76, 367, 21);
		contentPane.add(comboBox);
		
		JButton btnAnular = new JButton("Anular");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
					currentRide.retireBook(pas);
					appFacadeInterface.anularReserva(pas, currentRide);
					ridesModel.removeAllElements();
					System.out.println("exeeec");
					ridesModel.removeAllElements();
					for(Ride r : pas.getReservas()) {
						ridesModel.addElement(r);
					}
					comboBox.setModel(ridesModel);
//				}
//				catch(Exception ee) {
//					System.out.println("Error retirando reserva");
//				}
			}
		});
		btnAnular.setBounds(156, 187, 131, 49);
		contentPane.add(btnAnular);
		
		JLabel lblNewLabel = new JLabel("Selecciona la reserva a anular:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(34, 30, 326, 21);
		contentPane.add(lblNewLabel);
	}
}
