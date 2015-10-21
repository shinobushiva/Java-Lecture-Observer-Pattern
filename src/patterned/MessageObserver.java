package patterned;

/**
 * メッセージの到着を処理するクラスのためのインタフェース
 * 
 * @author shiva
 *
 */
public interface MessageObserver {

	public void messageReceived(String msg);

}
