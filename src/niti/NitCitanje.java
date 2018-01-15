package niti;

import java.io.BufferedReader;
import java.net.Socket;

import javax.swing.JTextArea;

import domen.Poruka;

public class NitCitanje extends Thread{
	
	private Socket s;
	private BufferedReader br;
	private Poruka p;
	private JTextArea razmenaPoruke;
	
	public NitCitanje(Socket s, BufferedReader bf, Poruka p, JTextArea razmenaPoruke) {
		super();
		this.s = s;
		this.br = bf;
		this.p = p;
		this.razmenaPoruke = razmenaPoruke;
		start();
	}
	
	public void run(){
		
		String nekaPoruka;
		
		while(true){
			
			try {
				
				nekaPoruka = br.readLine();
				if(nekaPoruka != null && !nekaPoruka.isEmpty()){
					
					p.setPoruka(nekaPoruka);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
