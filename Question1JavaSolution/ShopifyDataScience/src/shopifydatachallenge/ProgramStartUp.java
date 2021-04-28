package shopifydatachallenge;

/**
 * This class launches the application.
 * @author piedas
 */
public class ProgramStartUp {
	/**
	 * The main method simply instantiates the OrderRecordUtility
	 * and calls method processFile() to run the program.
	 * @param args Command Line Arguments are not used by this program.
	 */
	public static void main(String[] args) {
		new OrderRecordUtility().processFile();
	}
}
