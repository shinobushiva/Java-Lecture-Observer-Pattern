package patterned;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Observer Pattern を用いて実装したランダムにメッセージを受け取り処理するクラス
 * 
 * @author shiva
 *
 */
public class ObservableRandomMessageReceiver {

	private List<String> messages;

	public ObservableRandomMessageReceiver() {
		messages = readMessages("resources/messages.txt");
		observers = new ArrayList<MessageObserver>();
	}

	/**
	 * メッセージを処理
	 * 
	 * @param msg
	 */
	public void processMessage(String msg) {
		// 受け取ったメッセージを登録されている全てのオブザーバに引き渡す
		for (MessageObserver ob : observers) {
			ob.messageReceived(msg);
		}
		// [ポイント]　パターンを使用している場合、機能を追加するためにこのクラスを変更する必要はない
		// MessageObserverインタフェースを実装したクラスを作りオブザーバとして登録（add）すれば良い
	}

	/**
	 * オブザーバを保持するリスト
	 */
	private List<MessageObserver> observers;

	/**
	 * 指定したオブザーバを追加する
	 * 
	 * @param observer
	 */
	public void addObserver(MessageObserver observer) {
		observers.add(observer);
	}

	/**
	 * 指定したオブザーバを削除する （講義内の説明では使用しません）
	 * 
	 * @param observer
	 */
	public void removeObserver(MessageObserver observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	/**
	 * 登録してある全てのオブザーバを削除する （講義内の説明では使用しません）
	 */
	public void removeAllObservers() {
		observers.clear();
	}

	/**
	 * 擬似的にメッセージをランダムな待ち時間で受け取る
	 */
	public void startFakeReceivingMessages() {
		// 別のスレッドを生成
		Thread th = new Thread() {
			@Override
			public void run() {
				Random rand = new Random();
				while (true) {
					String msg = messages.get(rand.nextInt(messages.size()));
					processMessage(msg);

					// ランダムで0~2000msの間停止
					try {
						Thread.sleep((long) (Math.random() * 2000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		// スレッドをスタート
		th.start();
	}

	/**
	 * ファイルからメッセージを読み込み
	 */
	private List<String> readMessages(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(
				filePath)))) {
			ArrayList<String> list = new ArrayList<String>();

			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			// ArrayListをString[]に変換してリターン
			return list;

		} catch (IOException e) {
			e.printStackTrace();
		}
		// エラーが発生した場合はダミーのリストを返す
		return Arrays.asList(new String[] { "dummy message" });

	}

}
