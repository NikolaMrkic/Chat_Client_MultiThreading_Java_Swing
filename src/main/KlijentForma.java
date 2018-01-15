package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.AdresaPort;
import domen.Poruka;
import niti.NitCitanje;
import niti.NitPisanje;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class KlijentForma extends JFrame implements AdresaPort {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPoljeZaUnosPoruke;
	private JButton btnPosaljiPoruku;
	private Poruka p;
	private BufferedReader bf;
	private PrintWriter pw;
	private JTextArea taPoljeZaCitanjePoruke;
	private SimpleDateFormat sdf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KlijentForma frame = new KlijentForma();
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
	public KlijentForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 446);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(10, 21, 750, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("Username :");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUserName.setBounds(29, 11, 95, 23);
		panel.add(lblUserName);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(146, 14, 154, 20);
		panel.add(tfUsername);
		tfUsername.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			
				String username = tfUsername.getText().toString();
				
				if(username !=null && !username.isEmpty()){
					
					btnPosaljiPoruku.setVisible(true);
					p = new Poruka();
					try {
						
						Socket s=new Socket(adresa,PORT);
						BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
						PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
						pw.println(username);
						System.out.println("Usao sam u nik " +username);
						NitCitanje nc=new NitCitanje(s, br,p,taPoljeZaCitanjePoruke);
						NitPisanje np=new NitPisanje(pw,p,s);
						
					} catch (Exception e) {
					e.printStackTrace();
					}
				}
			
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBounds(636, 11, 89, 23);
		panel.add(btnLogin);
		
		taPoljeZaCitanjePoruke = new JTextArea();
		taPoljeZaCitanjePoruke.setBounds(57, 78, 627, 183);
		contentPane.add(taPoljeZaCitanjePoruke);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(10, 272, 609, 124);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPoljeZaUnosPoruke = new JLabel("Polje za unos poruke :");
		lblPoljeZaUnosPoruke.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPoljeZaUnosPoruke.setBounds(10, 11, 146, 14);
		panel_1.add(lblPoljeZaUnosPoruke);
		
		tfPoljeZaUnosPoruke = new JTextField();
		tfPoljeZaUnosPoruke.setBounds(10, 34, 578, 79);
		panel_1.add(tfPoljeZaUnosPoruke);
		tfPoljeZaUnosPoruke.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(629, 272, 131, 124);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		btnPosaljiPoruku = new JButton("Send ");
		btnPosaljiPoruku.setBackground(UIManager.getColor("Button.shadow"));
		btnPosaljiPoruku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String porukaZaSlanje = tfPoljeZaUnosPoruke.getText().toString();
				taPoljeZaCitanjePoruke.append(tfUsername.getText()+" <<  "+porukaZaSlanje+"  >>\n");
				p.setPoruka(porukaZaSlanje);
				ocistiPoljeZaUnosPoruke();
			}

			private void ocistiPoljeZaUnosPoruke() {
				tfPoljeZaUnosPoruke.setText("");
			}
		});
		btnPosaljiPoruku.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPosaljiPoruku.setBounds(10, 11, 111, 102);
		panel_2.add(btnPosaljiPoruku);
		btnPosaljiPoruku.setVisible(false);
	}
}
