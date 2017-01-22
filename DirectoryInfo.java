import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
/**
 * 
 * @author NadiraDewji This is the DirectoryInfo and it contains the main method
 *         that executes the program.
 */
public class DirectoryInfo extends ArrayList {
	/**
	 * This creates to ArrayList objects, myArrayList stores all the files in
	 * the directory while mytwentyArrayList stores the top 20 desired files.
	 */
	private static ArrayList<FileOnDisk> myArrayList = new ArrayList<FileOnDisk>();
	private static ArrayList<FileOnDisk> mytwentyArrayList = new ArrayList<FileOnDisk>();

	public static void main(String[] args) throws IOException {
		System.out.println();
		System.out.println();

		File f = new File(args[0]);
		if (args.length != 2) {
			System.out.println("The arguments are not the correct amount");
			System.exit(1);
		}

		if (!(f.exists())) {
			System.out.println("No such directory exists");
			System.exit(1);
		}
		/**
		 * This creates the two comparefile objects one is for size the other
		 * date so that they can be used.
		 */
		CompareFilesBySize c = new CompareFilesBySize();
		CompareFilesByDate cd = new CompareFilesByDate();
		/**
		 * getSize is called to get the size of all files within directories and
		 * sub directories.
		 */
		long totalSize = getSize(f);
		/**
		 * If the argument 1 is equal to largest, then sort is called to sort
		 * myarraylist, and it is compared by c which is the variable that
		 * represents sort by filesize.
		 */
		if (args[1].equalsIgnoreCase("largest")) {
			Collections.sort(myArrayList, c);
			System.out.printf("Total space used: %s bytes, Total number of files: %s\nLargest 20 files:\n", totalSize,
					myArrayList.size());
			System.out.println();
			/**
			 * This loops through the entire array of all files and gets the
			 * first 20, as they are sorted.
			 */
			for (int i = myArrayList.size() - 1; i >= myArrayList.size() - 20; i--) {
				mytwentyArrayList.add(myArrayList.get(i));
				myArrayList.get(i).toString();
			}
			/**
			 * this is a method that finds sets the padding before implementing
			 * the tostirng method.
			 */
			setPadding(mytwentyArrayList);
			/**
			 * this loops through mytwentarray and prints the string.
			 */
			for (FileOnDisk myFile : mytwentyArrayList) {
				System.out.println(myFile.toString());
			}
			/**
			 * this is the case that it is oldest.
			 */
		} else if (args[1].equalsIgnoreCase("oldest")) {
			/**
			 * This sorts myArrayList according to the modification date
			 */
			Collections.sort(myArrayList, cd);
			System.out.printf(
					"Total space used: %s bytes, Total number of files: %s\nLeast recently modified 20 files:",
					totalSize, myArrayList.size());
			int count = 0;
			System.out.println();
			/**
			 * This loops through myarraylist until 20
			 */
			for (FileOnDisk myFileOnDisk : myArrayList) {
				mytwentyArrayList.add(myFileOnDisk);
				myFileOnDisk.toString();
				count = count + 1;
				if (count == 20) {
					break;
				}
				// else{
				// myFileOnDisk.toString();
				// }
			}
			/**
			 * this sets the padding correctly
			 */
			setPadding(mytwentyArrayList);
			/**
			 * prints out each object in the array list.
			 */
			for (FileOnDisk myFile : mytwentyArrayList) {
				System.out.println(myFile.toString());
			}
			/**
			 * This is the case it is newest
			 */
		} else if (args[1].equalsIgnoreCase("newest")) {
			Collections.sort(myArrayList, cd);
			System.out.printf(
					"Total space used: %s bytes, Total number of files: %s\nMost recently modified 20 files:\n",
					totalSize, myArrayList.size());
			/**
			 * this loops backwards because the newest is at the bottom of the
			 * arraylist class.
			 */
			for (int i = myArrayList.size() - 1; i >= myArrayList.size() - 20; i--) {
				mytwentyArrayList.add(myArrayList.get(i));
				myArrayList.get(i).toString();
			}
			setPadding(mytwentyArrayList);
			for (FileOnDisk myFile : mytwentyArrayList) {
				System.out.println(myFile.toString());
			}

		} else {
			System.out.println("This is operation is not supported");
		}
	}

	/**
	 * This is a getSize class that uses recursive methoods to get the total
	 * size.
	 * 
	 * @param dir
	 *            it takes ina directory and tries to decipher if there is an
	 *            existing file embedded, it continues until it reaches a
	 *            regualr file
	 * @return the sum of the lengths of all the files.
	 */
	private static long getSize(File dir) {
		long totalSize = 0;
		try {
			/**
			 * this is the base case where the file is a regular file and so
			 * returns length
			 */
			if (dir.getAbsolutePath() == dir.getCanonicalPath()) {
				if (dir.isDirectory()) {
					totalSize = totalSize + (long) dir.length();
					// myArrayList.add(dir);
					return (long) dir.length();

				}

			}
			/**
			 * this is another base case where the directory is afile and not
			 * symbolic link.
			 */
			if (dir.isFile()) {
				FileOnDisk myFileOnDisk = new FileOnDisk(dir.getPath());
				myArrayList.add(myFileOnDisk);
				return (long) dir.length();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * this is the recursive case when a file exists within another and we
		 * have to split it to find the regular file.
		 */
		if (dir.isDirectory()) {
			totalSize = totalSize + (long) dir.length();
			File[] myList = dir.listFiles();
			for (File myDir : myList) {
				totalSize = totalSize + getSize(myDir);
			}
		} else {
			totalSize = totalSize + (long) dir.length();
		}
		return totalSize;
	}

	/**
	 * This is the setPadding class and it sets the padding by iterating through
	 * my arraylist, if there is bytes present then it will set the padding for
	 * every object to 6, else if its bytes itself 3. if there is no bytes it
	 * follows its default which is defined in tostring methond in file ondisk.
	 * 
	 * @param myArrayList
	 */
	private static void setPadding(ArrayList<FileOnDisk> myArrayList) {
		Boolean isBytes = false;
		for (FileOnDisk fD : myArrayList) {
			if (fD.getUnits() == "bytes") {
				isBytes = true;
				break;
			}

		}
		if (isBytes) {
			for (FileOnDisk fD : myArrayList) {
				if (fD.getUnits() == "bytes") {
					fD.setPadding(3);
				} else {
					fD.setPadding(6);
				}

			}
		}

	}

}
