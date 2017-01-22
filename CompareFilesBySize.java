import java.util.Comparator;

/**
 * This the CompareFilesBySize class and it implements Comparator and overrides
 * compare method to decipher how to compare the sizes between two FileOnDisk
 * objects.
 * 
 * @author NadiraDewji
 *
 */
public class CompareFilesBySize implements Comparator<FileOnDisk> {
	/**
	 * This is the constructor and it does not take any parameters.
	 */
	public CompareFilesBySize() {

	}

	/**
	 * This method overrides the comapre method and if the length of an object
	 * is greater than the second, then the difference is the returning value,
	 * if they are the same length then the natural way of ordering fileondisk
	 * is implemented.
	 * 
	 * @return it returns an integer that is the difference
	 * @param it
	 *            takes in two file on disk objects.
	 */
	@Override
	public int compare(FileOnDisk o1, FileOnDisk o2) {
		long comp = o1.length() - o2.length();
		if (comp == 0) {
			return o1.compareTo(o2);
		}
		return (int) comp;
	}

}
