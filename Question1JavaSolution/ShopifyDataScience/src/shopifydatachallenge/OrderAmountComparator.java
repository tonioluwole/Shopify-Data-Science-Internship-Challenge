
package shopifydatachallenge;
import java.util.Comparator;

/**
 * This class implements Comparator to provide support for sorting using the Java
 * Collections Framework based on the OrderRecord class OrderAmount field.
 * @author Olutoni Oluwole
 * @see java.util.Comparator
 */
public class OrderAmountComparator implements Comparator<OrderRecord>{
	/**
	 * Compares attackChance of each Friend object (t1 and t2), result is based on using Integer.compare(int, int)
	 * @see java.lang.Integer#compareTo(int,int)
	 */
	@Override
	public int compare(OrderRecord t1, OrderRecord t2) {
		
		return Integer.compare(t1.getOrderAmount(), t2.getOrderAmount());
	}

}
