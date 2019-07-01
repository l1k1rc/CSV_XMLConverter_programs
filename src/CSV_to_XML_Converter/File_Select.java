package CSV_to_XML_Converter;

import javax.swing.JFileChooser;

/**
 * Allow to select a file.
 * 
 * @author MS
 *
 */
public class File_Select {
	String filename = "";

	public File_Select() {
		try {
			// creation of the dialog box
			JFileChooser dialogue = new JFileChooser();
			// FileFilter test = new FileNameExtensionFilter(".csv", ".CSV");
			dialogue.setDialogTitle("Choisissez votre fichier CSV :");
			// dialogue.setFileFilter(test);
			// display it
			dialogue.showOpenDialog(null);
			filename = dialogue.getSelectedFile().toString();
			// recovery the selected file

		} catch (Exception e) {
			System.out.println("Pas de fichier sélectionné");// Mettres dans un JLabel
		}
	}

	public String getFile() {
		return filename;
	}
}
