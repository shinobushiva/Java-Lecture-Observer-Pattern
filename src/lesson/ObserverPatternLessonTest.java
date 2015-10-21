package lesson;

import patterned.ObservableRandomMessageReceiver;
import patterned.PrinterObserver;

/**
 * [課題] MessageObserverインタフェースを実装したクラスを作成し、その動作をテストしてください<br>
 * クラスは独立したファイルに作る、内部クラスや無名内部クラスとして作るなど、方法は限定しません。
 * 
 * @author shiva
 *
 */
public class ObserverPatternLessonTest {

	public static void main(String[] args) {
		ObservableRandomMessageReceiver rmr = new ObservableRandomMessageReceiver();

		// オブザーバをインスタンス化して登録
		PrinterObserver po = new PrinterObserver();
		rmr.addObserver(po);

		rmr.startFakeReceivingMessages();
	}

}
