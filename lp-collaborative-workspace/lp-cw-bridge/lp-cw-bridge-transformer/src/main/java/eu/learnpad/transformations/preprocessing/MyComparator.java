package eu.learnpad.transformations.preprocessing;

public class MyComparator implements java.util.Comparator<String> {

	//nulls first, then by increasing length, break ties using String's natural order
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
