package CSV_to_XML_Converter;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allow to read a CSV file and return an ArrayList of a string array.
 * <b> WARNING : l.34 COMMA it can launch an arrayIndexOutOfBoundsException </b>
 * @author MS
 *
 */
public class CSV_Reader {

	private ArrayList<String[]> datas = new ArrayList<String[]>();

	public CSV_Reader(String filename) {
		datas = read_CSV(filename);
	}

	/**
	 * Read a CSV file with FileReader line per line.
	 * 
	 * @param file_path : name of the CSV File
	 * @return data_csv : contains all datas about CSV file
	 */
	public ArrayList<String[]> read_CSV(String file_path) {
		Scanner reader = null;
		ArrayList<String[]> data_csv = new ArrayList<String[]>();
		try {
			reader = new Scanner(new FileReader(file_path));
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] lineTab = line.split(";");
				data_csv.add(lineTab);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		
		/*for(int i=0;i<data_csv.size();i++) {
			for(int j=0;j<data_csv.get(i).length;j++)
			System.out.println(data_csv.get(i)[j]);
		}*/
		return data_csv;
	}

	public ArrayList<String[]> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<String[]> datas) {
		this.datas = datas;
	}

}
