import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is FileOnDisk class that creates a file object while extending File
 * class.
 * 
 * @author NadiraDewji
 *
 */
public class FileOnDisk extends File {
	/**
	 * These variables are accessed in the main method and are set as private.
	 */
	private String units = "bytes";
	private int padding = 3;

	/**
	 * This is the constructor and it calls the super class because it takes in
	 * an argumment s that is passed onto the superclass.
	 * 
	 * @param s
	 *            a string that represents the file path
	 */
	public FileOnDisk(String s) {
		super(s);
	}

	/**
	 * This overrides the toString method present in higher classes. It uses
	 * SimpleDateFormat to creat an object that is displayed using the format.
	 * it has a series of if else statements that determine the units for the
	 * file: if the bytes are divisible by 1024 recurring then it continues
	 * until it is not and uses that unit and divides by the number of bytes in
	 * that unit of measure.
	 */
	@Override
	public String toString() {
		SimpleDateFormat myDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		String myFormattedDate = myDate.format(this.lastModified());
		Long mySize = this.length();
		Double newSize = 0.00;
		if (mySize < 1024) {
			newSize = (double) mySize;
		} else if (mySize < Math.pow(1024, 2)) {
			units = "KB";
			newSize = ((double) mySize) / Math.pow(1024, 1);

		} else if (mySize < Math.pow(1024, 3)) {
			units = "MB";
			newSize = ((double) mySize) / Math.pow(1024, 2);
		} else {
			units = "GB";
			newSize = ((double) mySize) / Math.pow(1024, 3);

		}
		/**
		 * returns a string that is formatted.
		 */
		return String.format("%7.2f %s %" + (16 + padding) + "s %s", newSize, units, myFormattedDate,
				this.getAbsolutePath());

	}

	/**
	 * a getter because the units are private.
	 * 
	 * @return a string representation of units
	 */

	public String getUnits() {
		return this.units;
	}

	/**
	 * a setter for the units because they are private
	 * 
	 * @param units
	 *            the units in string
	 */
	public void setUnits(String units) {
		this.units = units;
	}

	/**
	 * a getter for padding as it is a private field
	 * 
	 * @return the integer for padding
	 */
	public int getPadding() {
		this.toString();
		return this.padding;
	}

	/**
	 * a setter for padding as it is accessed
	 * 
	 * @param padding
	 *            the padding that is now applied
	 */
	public void setPadding(int padding) {
		this.padding = padding;
	}

}
