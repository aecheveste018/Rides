package gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Driver;
import domain.Passenger;
import domain.Ride;

public class VisualizeRequestsGUI extends JFrame {

    private Driver driver;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private DefaultComboBoxModel<Ride> viajes = new DefaultComboBoxModel<Ride>();
    private DefaultListModel<Driver> reservas = new DefaultListModel<Driver>();
    private static BLFacadeImplementation BLF;// = new BLFacadeImplementation();
	public static BLFacade getBusinessLogic(){
		return BLF;
	}
    public static void setBussinessLogic (BLFacade afi){
		BLF=(BLFacadeImplementation) afi;
	}

    /**
     * Create the frame.
     */
    public VisualizeRequestsGUI(Passenger u) {
        setTitle("View Register");

        driver = u;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox<Ride> comboBox = new JComboBox<Ride>();
        comboBox.setModel(viajes);
        List<Ride> rides = driver.getRides();
        for (int i = 0; i < rides.size(); i++) {
            viajes.addElement(rides.get(i));
        }

        comboBox.setBounds(30, 11, 112, 22);
        contentPane.add(comboBox);

        JList<Driver> list = new JList<Driver>();
        list.setModel(reservas);
        List<Driver> users = driver.getRides().get(Integer.parseInt(String.valueOf(comboBox.getSelectedItem().toString().charAt(0)))).getRequest();
        for (int i = 0; i < driver.size(); i++) {
            reservas.addElement(driver.get(i));
        }

        list.setBounds(96, 71, 238, 145);
        contentPane.add(list);
    }
}
