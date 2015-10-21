package patterned;

public class PatternedTest {

	public static void main(String[] args) {
		ObservableRandomMessageReceiver rmr = new ObservableRandomMessageReceiver();

		// オブザーバをインスタンス化して登録
		PrinterObserver po = new PrinterObserver();
		rmr.addObserver(po);

		rmr.startFakeReceivingMessages();
	}
}
