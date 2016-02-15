package eu.learnpad.transformations.preprocessing;

/**
 * Comparator strings based on custom length, used for sorting lists.
 * @author Basciani Francesco
 * @version 1.0
 *
 */
public class MyComparator implements java.util.Comparator<String> {

	/**
	 * Nulls first, then by increasing length, break ties using String's natural order.
	 * It return: if x = y; +1 if x > y; -1 if x < y
	 * @param x
	 * @param y
	 * @return 0 if x = y; +1 if x > y; -1 if x < y
	 * 
	 */
    public int compare(String x, String y) {
        if (x == null)
            return y==null ? 0 : -1;
        else if (y == null)
            return +1;
        else {
            int lenx = x.length();
            int leny = y.length();
            if (lenx == leny)
                return x.compareTo(y); //break ties?
            else
                return lenx < leny ? -1 : +1;
        }
    }
}
