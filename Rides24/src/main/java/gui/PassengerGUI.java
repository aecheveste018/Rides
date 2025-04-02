//<<<<<<< HEAD
//package gui;
//
//import java.awt.EventQueue;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.SwingConstants;
//import javax.swing.border.EmptyBorder;
//
//import domain.*;
//import javax.swing.JLabel;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import com.toedter.calendar.JCalendar;
//import domain.Ride;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.beans.*;
//import java.text.DateFormat;
//import java.util.*;
//import java.util.List;
//
//import javax.swing.table.DefaultTableModel;
//import businessLogic.*;
//import configuration.UtilDate;
//
//public class PassengerGUI extends JFrame {
//	
//	 private static final long serialVersionUID = 1L;
//
//	    private JPanel contentPane;
//	    private Passenger passenger;
//
//	    private JComboBox<String> departCity;
//	    private JComboBox<String> arriveCity;
//
//	    private final JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
//	    private final JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo"));
//
//	    private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
//	    private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Rides")); 
//
//	    private JCalendar jCalendar1 = new JCalendar();
//
//	    private List<Date> datesWithRidesCurrentMonth = new Vector<Date>();
//
//	    private JTable tableRides = new JTable();
//	    private DefaultTableModel tableModelRides;
//
//	    private String[] columnNamesRides = new String[] {
//	            ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Driver"), 
//	            ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces"), 
//	            ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Price")
//	    };
//	    private static BLFacadeImplementation BLF;// = new BLFacadeImplementation();
//		public static BLFacade getBusinessLogic(){
//			return BLF;
//		}
//	    public static void setBussinessLogic (BLFacade afi){
//			BLF=(BLFacadeImplementation) afi;
//		}
//	    
//	    /**
//	 * Launch the application.
//	 */
//	    public static void main(String[] args) {
//	        Passenger passenger = new Passenger();
//	        EventQueue.invokeLater(new Runnable() {
//	            public void run() {
//	                try {
//	                    PassengerGUI frame = new PassengerGUI(passenger);
//	                    frame.setTitle(ResourceBundle.getBundle("Etiquetas").getString("PassengerGUI.Title"));
//	                    frame.setVisible(true);
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        });
//	    }
//
//
//	/**
//	 * Create the frame.
//	 */
//    public PassengerGUI(Passenger p) {
//    	
//        passenger = p;
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 700, 500);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//        
//        jLabelOrigin.setBounds(35, 19, 128, 13);
//        jLabelOrigin.setHorizontalAlignment(SwingConstants.CENTER);
//        contentPane.add(jLabelOrigin);
//        
//        jLabelDestination.setBounds(35, 98, 128, 13);
//        jLabelDestination.setHorizontalAlignment(SwingConstants.CENTER);
//        contentPane.add(jLabelDestination);
//
//        departCity = new JComboBox<String>();
//        departCity.setBounds(35, 47, 238, 21);
//        contentPane.add(departCity);
//        
//        JButton reserveButton = new JButton("Reservar");
//        reserveButton.setBounds(199, 399, 288, 40);
//        contentPane.add(reserveButton);
//
//        reserveButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int selectedRow = tableRides.getSelectedRow();
//                    if (selectedRow != -1) { // Si hay una fila seleccionada
//                    	System.out.println(tableModelRides.getValueAt(selectedRow, 3));
//                        Ride selectedRide = (Ride) tableModelRides.getValueAt(selectedRow, 3); // Obtener el objeto Ride de la fila seleccionada
//                        String passengerEmail = passenger.getEmail(); // Obtener el correo electrónico del pasajero actual 
//                        boolean success = BLF.requestReservation(passengerEmail, selectedRide); // Realizar la reserva
//                        if (success) {
//                            JOptionPane.showMessageDialog(contentPane, "Reserva realizada con éxito.");
//                        } else {
//                            JOptionPane.showMessageDialog(contentPane, "No se pudo realizar la reserva.");
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(contentPane, "Seleccione un viaje para reservar.");
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        
//
//        
//        List<String> departCities = BLF.getDepartCities();
//        departCity.addItem(null); // Agregar un elemento nulo al principio
//        for (String depCity : departCities) {
//            departCity.addItem(depCity);
//        }
//
//        arriveCity = new JComboBox<String>();
//        arriveCity.setBounds(35, 125, 238, 21);
//        contentPane.add(arriveCity);
//
//        departCity.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent e) {
//                arriveCity.removeAllItems();
//                List<String> arrivalCities = BLF.getDestinationCities((String) departCity.getSelectedItem());
//                for (String arrCity : arrivalCities) {
//                    arriveCity.addItem(arrCity);
//                }
//            }
//        });
//
//        jLabelEventDate.setBounds(334, 13, 140, 25);
//        jLabelEvents.setBounds(35, 199, 259, 16);
//        contentPane.add(jLabelEventDate);
//        contentPane.add(jLabelEvents);
//
//        jCalendar1.setBounds(300, 50, 225, 150);
//        contentPane.add(jCalendar1);
//
//        tableModelRides = new DefaultTableModel(null, columnNamesRides);
//        tableRides.setModel(tableModelRides);
//        tableModelRides.setDataVector(null, columnNamesRides);
//        tableModelRides.setColumnCount(4);
//        tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
//        tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
//        tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
//        tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3));
//        
//
//
//        JScrollPane scrollPaneEvents = new JScrollPane();
//        scrollPaneEvents.setBounds(35, 230, 601, 150);
//        scrollPaneEvents.setViewportView(tableRides);
//        contentPane.add(scrollPaneEvents);
//
//        jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
//            public void propertyChange(PropertyChangeEvent propertychangeevent) {
//                if (propertychangeevent.getPropertyName().equals("locale")) {
//                    jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
//                } else if (propertychangeevent.getPropertyName().equals("calendar")) {
//                    Calendar calendarAct = (Calendar) propertychangeevent.getNewValue();
//                    try {
//                        tableModelRides.setDataVector(null, columnNamesRides);
//                        tableModelRides.setColumnCount(4);
//                        List<Ride> rides = BLF.getRides((String) departCity.getSelectedItem(),
//                                (String) arriveCity.getSelectedItem(), UtilDate.trim(jCalendar1.getDate()));
//                        if (rides.isEmpty()) {
//                            jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NoRides"));
//                        } else {
//                            jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Rides"));
//                        }
//                        for (Ride ride : rides) {
//                            Vector<Object> row = new Vector<Object>();
//                            row.add(ride.getDriver().getName());
//                            row.add(ride.getnPlaces());
//                            row.add(ride.getPrice());
//                            row.add(ride);
//                            tableModelRides.addRow(row);
//                        }
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        
//        datesWithRidesCurrentMonth = BLF.getThisMonthDatesWithRides((String) departCity.getSelectedItem(),
//                (String) arriveCity.getSelectedItem(), jCalendar1.getDate());
//        paintDaysWithEvents(jCalendar1, datesWithRidesCurrentMonth, Color.CYAN);
//    }
//
//    public static void paintDaysWithEvents(JCalendar jCalendar, List<Date> datesWithEventsCurrentMonth, Color color) {
//        Calendar calendar = jCalendar.getCalendar();
//        int today = calendar.get(Calendar.DAY_OF_MONTH);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        int offset = calendar.get(Calendar.DAY_OF_WEEK);
//        if (Locale.getDefault().equals(new Locale("es")))
//            offset += 4;
//        else
//            offset += 5;
//        for (Date d : datesWithEventsCurrentMonth) {
//            calendar.setTime(d);
//            Component o = (Component) jCalendar.getDayChooser().getDayPanel()
//                    .getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
//            o.setBackground(color);
//        }
//        calendar.set(Calendar.DAY_OF_MONTH, today);
//    }
//
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	}
//
//=======
package gui;

import java.awt.EventQueue;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.border.EmptyBorder;

import domain.*;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import businessLogic.*;
import configuration.UtilDate;

public class PassengerGUI extends JFrame {
	
	 private static final long serialVersionUID = 1L;
		private Ride ride=null;

	    private JPanel contentPane;
	    private Passenger passenger;

	    private JComboBox<String> departCity;
	    private JComboBox<String> arriveCity;

	    private final JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	    private final JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo"));

	    private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
	    private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Rides")); 

	    private JCalendar jCalendar1 = new JCalendar();

	    private List<Date> datesWithRidesCurrentMonth = new Vector<Date>();

	    private JTable tableRides = new JTable();
	    private DefaultTableModel tableModelRides;

	    private String[] columnNamesRides = new String[] {
	            ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Driver"), 
	            ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces"), 
	            ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Price")
	    };
	    private static BLFacadeImplementation BLF;// = new BLFacadeImplementation();
		public static BLFacade getBusinessLogic(){
			return BLF;
		}
	    public static void setBussinessLogic (BLFacade afi){
			BLF=(BLFacadeImplementation) afi;
		}
	    
	    /**
	 * Launch the application.
	 */
	    public static void main(String[] args) {
	        Passenger passenger = new Passenger();
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    PassengerGUI frame = new PassengerGUI(passenger);
	                    frame.setTitle(ResourceBundle.getBundle("Etiquetas").getString("PassengerGUI.Title"));
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
    public PassengerGUI(Passenger p) {
    	
        passenger = p;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        jLabelOrigin.setBounds(35, 19, 128, 13);
        jLabelOrigin.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabelOrigin);
        
        jLabelDestination.setBounds(35, 98, 128, 13);
        jLabelDestination.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(jLabelDestination);

        departCity = new JComboBox<String>();
        departCity.setBounds(35, 47, 238, 21);
        contentPane.add(departCity);
        
        JButton reserveButton = new JButton("Reservar");
        reserveButton.setBounds(199, 399, 288, 40);
        contentPane.add(reserveButton);

        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow = tableRides.getSelectedRow();
                    if (selectedRow != -1) { // Si hay una fila seleccionada
                    	System.out.println(tableModelRides.getValueAt(selectedRow, 3));
                        Ride selectedRide = (Ride) tableModelRides.getValueAt(selectedRow, 3); // Obtener el objeto Ride de la fila seleccionada
                        String passengerEmail = passenger.getEmail(); // Obtener el correo electrónico del pasajero actual 
                        boolean success = BLF.requestReservation(passengerEmail, selectedRide); // Realizar la reserva
                      
                        if (success) {
                        	if(selectedRide.getPrice()<=passenger.getMonedero()) {
                        		p.setMonedero(p.getMonedero()-selectedRide.getPrice());
                        		dispose();
                        		JOptionPane.showMessageDialog(contentPane, "Reserva realizada con éxito.");
                                dispose();
                        	}else {
                        		JOptionPane.showMessageDialog(contentPane, "Recargue su monedero.");
                                RecargarMonederoGUI r=new RecargarMonederoGUI(passenger);
                        		r.setVisible(true);
                                dispose();
                        	}
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "No se pudo realizar la reserva.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Seleccione un viaje para reservar.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        

        
        List<String> departCities = BLF.getDepartCities();
        departCity.addItem(null); // Agregar un elemento nulo al principio
        for (String depCity : departCities) {
            departCity.addItem(depCity);
        }

        arriveCity = new JComboBox<String>();
        arriveCity.setBounds(35, 125, 238, 21);
        contentPane.add(arriveCity);

        departCity.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                arriveCity.removeAllItems();
                List<String> arrivalCities = BLF.getDestinationCities((String) departCity.getSelectedItem());
                for (String arrCity : arrivalCities) {
                    arriveCity.addItem(arrCity);
                }
            }
        });

        jLabelEventDate.setBounds(334, 13, 140, 25);
        jLabelEvents.setBounds(35, 199, 259, 16);
        contentPane.add(jLabelEventDate);
        contentPane.add(jLabelEvents);

        jCalendar1.setBounds(300, 50, 225, 150);
        contentPane.add(jCalendar1);

        tableModelRides = new DefaultTableModel(null, columnNamesRides);
        tableRides.setModel(tableModelRides);
        tableModelRides.setDataVector(null, columnNamesRides);
        tableModelRides.setColumnCount(4);
        tableRides.getColumnModel().getColumn(0).setPreferredWidth(170);
        tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
        tableRides.getColumnModel().getColumn(1).setPreferredWidth(30);
        tableRides.getColumnModel().removeColumn(tableRides.getColumnModel().getColumn(3));
        


        JScrollPane scrollPaneEvents = new JScrollPane();
        scrollPaneEvents.setBounds(35, 230, 601, 150);
        scrollPaneEvents.setViewportView(tableRides);
        contentPane.add(scrollPaneEvents);

        jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent propertychangeevent) {
                if (propertychangeevent.getPropertyName().equals("locale")) {
                    jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
                } else if (propertychangeevent.getPropertyName().equals("calendar")) {
                    Calendar calendarAct = (Calendar) propertychangeevent.getNewValue();
                    try {
                        tableModelRides.setDataVector(null, columnNamesRides);
                        tableModelRides.setColumnCount(4);
                        List<Ride> rides = BLF.getRides((String) departCity.getSelectedItem(),
                                (String) arriveCity.getSelectedItem(), UtilDate.trim(jCalendar1.getDate()));
                        if (rides.isEmpty()) {
                            jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NoRides"));
                        } else {
                            jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Rides"));
                        }
                        for (Ride ride : rides) {
                            Vector<Object> row = new Vector<Object>();
                            row.add(ride.getDriver().getName());
                            row.add(ride.getnPlaces());
                            row.add(ride.getPrice());
                            row.add(ride);
                            tableModelRides.addRow(row);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        
        datesWithRidesCurrentMonth = BLF.getThisMonthDatesWithRides((String) departCity.getSelectedItem(),
                (String) arriveCity.getSelectedItem(), jCalendar1.getDate());
        paintDaysWithEvents(jCalendar1, datesWithRidesCurrentMonth, Color.CYAN);
    /*    
     // Agregar un MouseListener para capturar clics en la tabla
     		tableRides.addMouseListener(new MouseAdapter() {
     		    @Override
     		    public void mouseClicked(MouseEvent e) {
     		        int filaSeleccionada = tableRides.getSelectedRow();
     		        if (filaSeleccionada != -1) { // Si hay una fila válida seleccionada
     		            Ride rideSeleccionado = (Ride) tableModelRides.getValueAt(filaSeleccionada, 3); // Columna oculta
     		            JOptionPane.showMessageDialog(null, 
     		                "Conductor: " + rideSeleccionado.getDriver().getName() + "\n" +
     		                "Plazas disponibles: " + rideSeleccionado.getnPlaces() + "\n" +
     		                "Precio: " + rideSeleccionado.getPrice(),
     		                "Ride Seleccionado",
     		                JOptionPane.INFORMATION_MESSAGE);
     		            
     		            // Guardar el ride seleccionado en la variable de clase
     		            ride = rideSeleccionado; 
     		        }
     		    }
     		});
        */
    }

    public static void paintDaysWithEvents(JCalendar jCalendar, List<Date> datesWithEventsCurrentMonth, Color color) {
        Calendar calendar = jCalendar.getCalendar();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int offset = calendar.get(Calendar.DAY_OF_WEEK);
        if (Locale.getDefault().equals(new Locale("es")))
            offset += 4;
        else
            offset += 5;
        for (Date d : datesWithEventsCurrentMonth) {
            calendar.setTime(d);
            Component o = (Component) jCalendar.getDayChooser().getDayPanel()
                    .getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
            o.setBackground(color);
        }
        calendar.set(Calendar.DAY_OF_MONTH, today);
    }

		
		
		
		
		
		
		
		
		
		
		
		
	}

//>>>>>>> branch 'master' of https://github.com/aecheveste018/Rides
