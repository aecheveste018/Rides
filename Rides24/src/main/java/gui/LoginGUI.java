package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import domain.Driver;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_User;
	private JPasswordField textField_Password;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_User = new JTextField();
		textField_User.setBounds(107, 77, 278, 30);
		contentPane.add(textField_User);
		textField_User.setColumns(10);
		
		textField_Password = new JPasswordField();
		textField_Password.setColumns(10);
		textField_Password.setBounds(107, 133, 278, 30);
		contentPane.add(textField_Password);
		
		JLabel lblNewLabel = new JLabel("Mail:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(191, 53, 83, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(167, 107, 131, 30);
		contentPane.add(lblPassword);
		JLabel JlabelError = new JLabel("");
		JlabelError.setForeground(Color.RED);
		JlabelError.setHorizontalAlignment(SwingConstants.CENTER);
		JlabelError.setFont(new Font("Tahoma", Font.BOLD, 14));
		JlabelError.setBounds(167, 227, 132, 31);
		contentPane.add(JlabelError);
		

		
		JLabel lblNewLabel_1 = new JLabel("No tienes usuario?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(168, 249, 144, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterGUI rgui = new RegisterGUI();
				rgui.setVisible(true);
			}
		});
		RegisterButton.setBounds(168, 274, 134, 36);
		contentPane.add(RegisterButton);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenido");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setBounds(81, 10, 310, 37);
		contentPane.add(lblNewLabel_2);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = textField_User.getText();
				String psw = textField_Password.getText();
				try {
					if(textField_User.getText().trim().isEmpty() |  textField_Password.getText().trim().isEmpty() ) {
						JlabelError.setText("Campos incompletos");
					}
					else if(psw.equals(BLF.findPassword(mail))) {
						JlabelError.setText("Loged");
						System.out.println("ñuifuiasdifuhasduihfiuashdfuihasduihfasuidhpuiasdh");
						if(BLF.getDriverByEmail(mail) ==null) {
							System.out.println("ñuifuiasdifuhasduihfiuashdfuihasduihfasuidhpuiasdh");
							dispose();
							Main2GUI mgui = new Main2GUI(BLF.getPassengerByEmail(mail));
							mgui.setBussinessLogic(BLF);
							mgui.setVisible(true);
						}else {
							dispose();
							Driver d= BLF.getDriverByEmail(mail);
							MainGUI mgui = new MainGUI(d);
								mgui.setBussinessLogic(BLF);
							mgui.setVisible(true);
						}
						

						//Aqui iria el abrir la correspondiente llamada para abrir la siguiente ventana 
					}
					else {
						JlabelError.setText("Error");
					}
				}catch(NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Error: No se encontró su usuario", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		LoginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LoginButton.setBounds(153, 186, 169, 41);
		contentPane.add(LoginButton);

	}
}
