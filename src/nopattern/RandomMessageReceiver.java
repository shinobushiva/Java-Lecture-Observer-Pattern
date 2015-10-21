package nopattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ランダムにメッセージを受け取り処理するクラス
 * 
 * @author shiva
 *
 */
public class RandomMessageReceiver {

	private List<String> messages;

	public RandomMessageReceiver() {
		messages = readMessages("resources/messages.txt");
	}

	/**
	 * メッセージを処理
	 * 
	 * @param msg
	 */
	public void processMessage(String msg) {
		// 受け取ったメッセージを表示
		System.out.println("Received : " + msg);

		// [ポイント]　パターンを使用してない場合、機能を追加するためにはこのクラスの変更が必要
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
