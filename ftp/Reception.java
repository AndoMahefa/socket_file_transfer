package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.io.PrintWriter;

public class Reception implements Runnable {
	BufferedReader in;
	String file;
	Socket clientSock;
	int fileSize;
	
	public void getFileDet() throws Exception {
		System.out.println("Reception des fichiers dis");
		System.out.println("get in ");
		file = in.readLine();
		System.out.println("file " + file);
		
		String value = in.readLine();
		this.fileSize = Integer.parseInt(value);
		System.out.println("taille fichier enregistrer " + fileSize);
		
		saveFile();
	}
	
	public Reception(Socket clientSock) throws Exception {
		this.clientSock = clientSock;
		in = new BufferedReader(new InputStreamReader(this.clientSock.getInputStream()));
	}
	
	public void run() {
		try {
			String bool = in.readLine();
			System.out.println(bool);
			
			if(bool.compareToIgnoreCase("true") == 0) {
				getFileDet();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//fonction msave anle fichier
	public void saveFile() throws IOException {
		System.out.println("receiver/"+file.substring(file.lastIndexOf("/")+1));
		FileOutputStream fos = new FileOutputStream("receiver/"+file.substring(file.lastIndexOf("/")+1));
		byte[] buffer = new byte[4096];
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		int read = 0;
		int totalRead = 0;
		int remaining = fileSize;
		int total = 0;

		DataInputStream dis = new DataInputStream(this.clientSock.getInputStream());
		
		while((read = dis.read(buffer,0,Math.min(buffer.length,remaining))) > 0) {
			total++;
			totalRead += read;
			System.out.println("read : "+read);
			System.out.println("read : "+ totalRead + "/" + fileSize);
			remaining -= read;
			fos.write(buffer, 0, read);		
		}
		
		System.out.println("total " + total);
		System.out.println("\nRemaining : "+remaining+"\n");
		System.out.println("file_Size : "+fileSize);
		System.out.println(time + " read " + totalRead + " bytes.");
		
		fos.close();
		dis.close();
	}
}