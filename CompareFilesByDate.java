import java.util.Comparator;

/**
 * This is the CompareFilesByDate class and it creates a method to compare two fileondisk obkects based on their modification date.
 * @author NadiraDewji
 *
 */
public class CompareFilesByDate implements Comparator<FileOnDisk> {
	/**
	 * This is the constructor that creates the class.
	 */
	public CompareFilesByDate() {

	}
	/**
	 * This method overrides the compare method and establishes a method to compare tow objects  their ladmodified difference
	 * is the integer that is returned and if it is 0, it is compared by the natural way of ordering using compareTo.
	 * @return an integer that is the difference
	 * @param it takes two fileondisk objects
	 */
	@Override
	public int compare(FileOnDisk o1, FileOnDisk o2) {
		long comp = o1.lastModified() - o2.lastModified();
		if (comp == 0) {
			return o1.compareTo(o2);
		}
		if (comp > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	public int number(){
		return 1;
	}

}
