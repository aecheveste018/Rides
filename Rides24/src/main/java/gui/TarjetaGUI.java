package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Passenger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class TarjetaGUI extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
    private static BLFacadeImplementation BLF;
	public static BLFacade getBusinessLogic(){
		return BLF;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		BLF=(BLFacadeImplementation) afi;
	}
	public TarjetaGUI(double recarga,Passenger p) {
		this.setSize(495, 290);
		setTitle("Pago con targeta");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Número de tarjeta");
		lblNewLabel.setBounds(10, 20, 182, 13);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 301, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Caducidad (MM/YY)");
		lblNewLabel_1.setBounds(10, 72, 152, 13);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 95, 114, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Security Code (CVV 3-4 digits)");
		lblNewLabel_2.setBounds(172, 72, 195, 13);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(172, 95, 139, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre completo como se muestra en la tarjeta (nombre y apellido)");
		lblNewLabel_3.setBounds(10, 160, 416, 13);
		getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 179, 312, 19);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ver si todos los campos están rellenados:
				if(!textField.getText().trim().isEmpty()||!textField_1.getText().trim().isEmpty()||!textField_2.getText().trim().isEmpty()||!textField_3.getText().trim().isEmpty()) {
					try {
						int s = Integer.parseInt(textField.getText());
						if(BLF.doesCardExist(s)) {
							if(BLF.doesCardHaveEnoughMoney(s, recarga)) {
								p.setMonedero(p.getMonedero()+recarga);
								JOptionPane.showMessageDialog(null, "Tarjeta aceptada", "Aceptar", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}else {
								JOptionPane.showMessageDialog(null, "Error: Dinero insuficiente en la tarjeta", "Error", JOptionPane.ERROR_MESSAGE);

							}
							
						}else {
							JOptionPane.showMessageDialog(null, "Error: La tarjeta introducida no existe", "Error", JOptionPane.ERROR_MESSAGE);
							//dispose();
						}
					}catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(null, "Error: Rellene los datos correctamente por favor", "Error", JOptionPane.ERROR_MESSAGE);

					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Error: Rellene todos los campos por favor", "Error", JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		btnNewButton.setBounds(107, 219, 85, 21);
		getContentPane().add(btnNewButton);
	}
}
