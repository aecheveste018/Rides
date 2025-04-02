package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.*;
import domain.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ReservationsGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private BLFacade businessLogic = MainGUI.getBusinessLogic();
    private JComboBox<Ride> reservationsComboBox;
    private DefaultComboBoxModel<Ride> reservationsModel;
    private Driver driver;
    private static BLFacadeImplementation BLF;// = new BLFacadeImplementation();
	public static BLFacade getBusinessLogic(){
		return BLF;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		BLF=(BLFacadeImplementation) afi;
	}

    public ReservationsGUI(Driver d) {
        setTitle("Reservations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 555, 273);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        driver = d;

        JLabel lblReservations = new JLabel("Reservas");
        lblReservations.setBounds(21, 20, 100, 20);
        contentPane.add(lblReservations);

        reservationsModel = new DefaultComboBoxModel<Ride>();
        
        List<Ride> reservations = BLF.getBookingForDriver(driver);
	     reservationsModel.addElement(null);	
	     for (Ride reservation : reservations) {
	    	 
	         reservationsModel.addElement(reservation);
	     }
	     
        
        reservationsComboBox = new JComboBox<Ride>(reservationsModel);
        reservationsComboBox.setBounds(104, 20, 427, 20);
        contentPane.add(reservationsComboBox);
        JTextArea info = new JTextArea();
	     reservationsComboBox.addItemListener(new ItemListener() {
	            public void itemStateChanged(ItemEvent e) {
	            	Ride selectedRide= (Ride)reservationsComboBox.getSelectedItem();
	            	String s="There are still "+selectedRide.getnPlaces()+" seats left";
	            	info.setText(s);
	            }
	        });
        
        /*JButton btnViewDetails = new JButton("");
        btnViewDetails.setBounds(76, 151, 120, 46);
        contentPane.add(btnViewDetails);
        /*
        btnViewDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ride selectedRide = (Ride) reservationsComboBox.getSelectedItem();
                if (selectedRide != null) {
                	System.out.println(selectedRide.toString());
                    System.out.println(selectedRide.getAllStops());
                    RidesGUI ridesGUI = new RidesGUI(selectedRide, p);
                    ridesGUI.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Porfavor seleccione una reserva.");
                } 
            } 
        });
        */

        JButton btnBack = new JButton("Close");
        btnBack.setBounds(342, 151, 120, 46);
        contentPane.add(btnBack);
        
        info.setBounds(104, 66, 427, 46);
        contentPane.add(info);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the ReservationsGUI window
            }
        });

        

    }
}