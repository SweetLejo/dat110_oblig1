package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "127.0.0.1";

	public static byte[] encapsulate(Message message) {

		byte[] data = message.getData();
		byte[] segment = new byte[SEGMENTSIZE];

		segment[0] = (byte) data.length;
        System.arraycopy(data, 0, segment, 1, data.length);

		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {


		byte[] data = new byte[segment[0]];
		System.arraycopy(segment, 1, data, 0, segment[0]);

		Message message = new Message(data);

		return message;
		
	}
	
}
