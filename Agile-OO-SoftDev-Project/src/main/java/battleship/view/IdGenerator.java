package battleship.view;

public class IdGenerator {

	private static int generator = 0;
	
	public static int get() {
		return generator++;
	}
}
