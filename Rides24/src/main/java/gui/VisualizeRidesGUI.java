package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Passenger;
import domain.Ride;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

public class VisualizeRidesGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Passenger user;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VisualizeRidesGUI frame = new VisualizeRidesGUI(null);
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
	public VisualizeRidesGUI(Passenger pUser) {
//		addWindowListener(new WindowAdapter() {
//			@Override
//	//		public void windowClosing(WindowEvent e) {
////				dispose();
////				Main2GUI launch = new Main2GUI(pUser);
////				launch.setVisible(true);
//				//setVisible(false);
//				
//		//	}
//		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lista de todos sus viajes\r\n:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(64, 10, 208, 27);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 62, 347, 124);
		contentPane.add(scrollPane);
		
		JList RideList = new JList();
		scrollPane.setViewportView(RideList);
//MIRAR
		DefaultListModel<String> listModel = new DefaultListModel<>();//(DefaultListModel<Ride>) pUser.getReservas();
       List<Ride> lrides = pUser.getReservas();
       Iterator<Ride> it = lrides.iterator();
       while(it.hasNext()) {
    	   Ride current = it.next();
    	   listModel.addElement(current.toString());
       
       
       }
       RideList.setModel(listModel);
	}
}
