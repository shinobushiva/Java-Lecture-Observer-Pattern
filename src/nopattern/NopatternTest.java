package nopattern;

public class NopatternTest {

	public static void main(String[] args) {
		RandomMessageReceiver rmr = new RandomMessageReceiver();
		rmr.startFakeReceivingMessages();
	}
}
