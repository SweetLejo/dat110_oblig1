package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageConnection {

	private DataOutputStream outStream;
	private DataInputStream inStream;
	private Socket socket;

	public MessageConnection(Socket socket) {
		try {
			this.socket = socket;
			outStream = new DataOutputStream(socket.getOutputStream());
			inStream = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			handleException("Error in constructor", ex);
		}
	}

	public void send(Message message) {
		try {
			outStream.write(MessageUtils.encapsulate(message));
		} catch (IOException e) {
			handleException("Error in send method", e);
		}
	}

	public Message receive() {
		Message message = null;
		byte[] data = new byte[128];
		try {
			inStream.read(data);
			message = MessageUtils.decapsulate(data);
		} catch (IOException e) {
			handleException("Error in receive method", e);
		}
		return message;
	}

	public void close() {
		try {
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException ex) {
			handleException("Error in close method", ex);
		}
	}

	private void handleException(String message, IOException ex) {
		System.out.println("Connection: " + message);
		ex.printStackTrace();
		// Handle the exception appropriately, e.g., log it using a logging framework
	}
}
