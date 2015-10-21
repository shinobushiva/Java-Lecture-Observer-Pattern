package patterned;

public class PrinterObserver implements MessageObserver {

	@Override
	public void messageReceived(String msg) {
		System.out.println("Received : " + msg);
	}

}
