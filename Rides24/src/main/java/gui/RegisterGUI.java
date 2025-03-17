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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EmailRegisterBox;
	private JTextField NameRegisterBox;
	private JPasswordField Password2RegsiterBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		EmailRegisterBox = new JTextField();
		EmailRegisterBox.setColumns(10);
		EmailRegisterBox.setBounds(103, 54, 278, 30);
		contentPane.add(EmailRegisterBox);
		
		NameRegisterBox = new JTextField();
		NameRegisterBox.setColumns(10);
		NameRegisterBox.setBounds(103, 110, 278, 30);
		contentPane.add(NameRegisterBox);
		JLabel JlbaleErrorLog = new JLabel("");
		JlbaleErrorLog.setHorizontalAlignment(SwingConstants.CENTER);
		JlbaleErrorLog.setForeground(new Color(255, 0, 0));
		JlbaleErrorLog.setBounds(138, 283, 200, 30);
		contentPane.add(JlbaleErrorLog);
		Password2RegsiterBox = new JPasswordField();
		Password2RegsiterBox.setColumns(10);
		Password2RegsiterBox.setBounds(103, 170, 278, 30);
		contentPane.add(Password2RegsiterBox);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(201, 25, 64, 30);
		contentPane.add(lblNewLabel);
		
		JLabel namelabel = new JLabel("Name");
		namelabel.setHorizontalAlignment(SwingConstants.CENTER);
		namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		namelabel.setBounds(189, 82, 83, 30);
		contentPane.add(namelabel);
		
		JLabel lblRepeatPassword = new JLabel("Password");
		lblRepeatPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRepeatPassword.setBounds(154, 140, 164, 30);
		contentPane.add(lblRepeatPassword);
		JRadioButton DriverRbutton = new JRadioButton("");
		DriverRbutton.setSelected(true);
		buttonGroup.add(DriverRbutton);
		DriverRbutton.setVerticalAlignment(SwingConstants.TOP);
		DriverRbutton.setHorizontalAlignment(SwingConstants.RIGHT);
		DriverRbutton.setBounds(185, 221, 21, 21);
		contentPane.add(DriverRbutton);
		
		JRadioButton ClienteRbutton = new JRadioButton("");
		buttonGroup.add(ClienteRbutton);
		ClienteRbutton.setVerticalAlignment(SwingConstants.TOP);
		ClienteRbutton.setHorizontalAlignment(SwingConstants.RIGHT);
		ClienteRbutton.setBounds(265, 221, 21, 21);
		contentPane.add(ClienteRbutton);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BLF.addCredential("","", null);
				String mail = EmailRegisterBox.getText();
				String name = NameRegisterBox.getText();
				String psw = Password2RegsiterBox.getText();
				if(EmailRegisterBox.getText().trim().isEmpty() |  NameRegisterBox.getText().trim().isEmpty() |Password2RegsiterBox.getText().trim().isEmpty() ) {
					JlbaleErrorLog.setText("Debes rellenar todos los campos");
					
				}
				
				else if(BLF.exists(mail)) {
					
					JlbaleErrorLog.setText("Ese mail ya esta registrado");
				}
				
				else {
					Character tipo;
					if(DriverRbutton.isSelected()) {
						tipo = 'D';
					}
					else {
						tipo = 'P';
					}
					BLF.addUser(mail, name, tipo);
					BLF.addCredential(mail, psw, tipo);
					JlbaleErrorLog.setText("Registro correcto");
					dispose();
					LoginGUI rgui = new LoginGUI();
					rgui.setVisible(true);
					
				}
			}
		});
		RegisterButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		RegisterButton.setBounds(154, 248, 164, 38);
		contentPane.add(RegisterButton);
		

		
		JLabel lblNewLabel_1 = new JLabel("Driver");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(180, 210, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel textDriver = new JLabel("Cliente");
		textDriver.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textDriver.setBounds(260, 210, 45, 13);
		contentPane.add(textDriver);
		

	}
}
