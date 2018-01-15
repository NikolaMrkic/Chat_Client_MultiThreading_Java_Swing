package niti;

import java.io.PrintWriter;
import java.net.Socket;

import domen.Poruka;

public class NitPisanje extends Thread{
	private PrintWriter pw;
	private Poruka p;
	private Socket s;
	String username;
	

	public NitPisanje(PrintWriter pw, Poruka p, Socket s) {
		// TODO Auto-generated constructor stub
		this.p=p;
		this.pw=pw;
		this.s=s;
		
		start();
	}
	public void run(){
		
		while(true){
			//System.out.println("Nit pisanje "+p.getPoruka());
			pw.println(p.getPoruka());
			//pw.flush();
		}
	}

}
