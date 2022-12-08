package server;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileServer implements Runnable {	
	private ServerSocket ss;
	int fileSize;
	Socket clientSock;
	
	public int getFileSize() {
		return this.fileSize;
	}
	
	public Socket getClientSock() {
		return this.clientSock;
	}
	
	public FileServer() {}
	
	public FileServer(int port) throws Exception {
		this.ss = new ServerSocket(port);
	}
	
	public void run() {	
		while (true) {
			try {
				clientSock = ss.accept();
				System.out.println("Connecte");
				Thread recp = new Thread(new Reception(clientSock));
				recp.setPriority(Thread.MAX_PRIORITY);
				recp.start();
				System.out.println("Threads actifs " + Thread.activeCount());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	public static void main(String[] args) throws Exception {
		Thread fs = new Thread(new FileServer(1988));
		fs.start();
	}
}