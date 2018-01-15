package domen;

public class Poruka {
	
	private String poruka;

	public synchronized String getPoruka() {
		try {
			System.out.println("pre waita");
			wait();
			System.out.println("Posle waita");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Posle waita i catcha");
		return poruka;
		
	}

	public synchronized void setPoruka(String poruka) {
		this.poruka = poruka;
		System.out.println("Poruka postavljena notify "+poruka);
		notify();
	}
	
	

}
