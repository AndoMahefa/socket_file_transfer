package client;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import aff.ChooseComponent;

public class FileClient implements Runnable {
	Socket s;
	PrintWriter out;
	String file;
	ChooseComponent chooseComponent;

	//Getters && Setters
	public Socket getS() {
		return this.s;
	}
	public void setS(Socket s){
		this.s = s;
		System.out.println("\nset Socket ------\n");
	}
	public void setChooseComponent(ChooseComponent chooseComponent) {
		this.chooseComponent = chooseComponent;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public ChooseComponent getChooseComponent() {
		return chooseComponent;
	}

	public void run() {
		try {
			sendFile(file);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendBoolean(String b) throws Exception {
		out = new PrintWriter(s.getOutputStream());
		out.println(b);
	}

	public FileClient(String host, int port) throws Exception {
		System.out.println("host " + host);
		System.out.println("port " + port);
		try {
			s = new Socket(host, port);
		}
		catch(java.net.UnknownHostException e) {
			System.out.println("InetAdress.getByName : "+InetAddress.getByName(host));
			System.out.println("Dans catch");
			s = new Socket(InetAddress.getByName(host),port);
		}
		
		sendBoolean("false");
	}

	public FileClient(String host, int port, String file, ChooseComponent chooseComponent) {
		try {
			s = new Socket(host, port);
			sendBoolean("true");
			setChooseComponent(chooseComponent);
			this.file = file;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void sendFile(String file) throws Exception {
		System.out.println("Socket " + s.getLocalPort());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		System.out.println("file " + file);
		file = file.replace("\\","/");
		FileInputStream fis = new FileInputStream(file);		
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		///Ajout du fichier a l'affichage swing
		JProgressBar jpb = new JProgressBar(0,100);
		getChooseComponent().addLabel(new JLabel(file));
		getChooseComponent().addJPB(jpb);
		jpb.setValue(0);
		jpb.setStringPainted(true);

		///Envoi de la taille du fichier
		out.println(file);
		out.println(""+fis.available());
		out.flush();
		
		byte[] buffer = new byte[4096];
		int read = 0;
		int remaining = fis.available();
		long totalRead = 0;
		System.out.println("taille fichier " + fis.available());
		
		long fileSize = fis.available();
		
		int nb = 0;
		
		while((read=fis.read(buffer)) > 0) {
			totalRead += read;
			System.out.println("totalRead " + totalRead + "/" + fileSize);
			dos.write(buffer);

			int progress = (int)((totalRead*100)/fileSize);
			System.out.println("progress " + progress);
			jpb.setValue(progress);
			nb++;
		}
		
		System.out.println("NB " + nb);
		
		System.out.println("taille fichier " + fileSize);		
		System.out.println("after write");
		System.out.println(time + " read " + totalRead);
		
		fis.close();
		dos.close();
		System.out.println("--------------------------------------");
	}	
	
	public static void main(String[] args) throws Exception {
		// FileClient fc = new FileClient("localhost", 1988, "Examen-OneDay-17-Dec-2018-P11.pdf");
		// FileClient fc = new FileClient("localhost", 1988, "C:/Users/ITU/Documents/VR.pptx");
		// FileClient fc = new FileClient("localhost", 1988);
	}
}