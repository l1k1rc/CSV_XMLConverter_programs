package CSV_to_XML_Converter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Allows to display the user guide. Contains the configuration of the window,
 * graphic components, sequential instructions for data verification.
 * 
 * @author MS
 *
 */
public class UI_Converter extends JPanel {

	private static final long serialVersionUID = 1L;

	private JFrame frame = new JFrame();

	private GridLayout grid = new GridLayout(2, 1);

	private JButton b1 = new JButton("Choisir le fichier utilisateur :");

	private JButton b2 = new JButton("Choisir le fichier structure :");
	private JButton validate = new JButton("Générer le fichier XML de l'organisation :");
	private static JLabel l1 = new JLabel();

	private static JLabel l2 = new JLabel();
	private JLabel info = new JLabel("Veuillez vérifier l'encodage UTF-8 des fichiers en entrée.");
	private JLabel warning = new JLabel();
	private JPanel container = new JPanel();

	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel[] cells = { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
			new JPanel() };

	private File_Select file1, file2;

	private boolean isRightFormat1, isRightFormat2 = false;

	/*
	 * Constructor called by launcher class. 
	 */
	public UI_Converter() {
		this.setLayout(grid);
		this.setBackground(Color.WHITE);

		openGuide();

	}

	/* Allow to set the frame with all option */
	public void openGuide() {
		frame.setTitle("XMLExchanger");
		frame.setBackground(Color.WHITE);

		PanelMenu();
		setUI();

		frame.setContentPane(container);
		frame.setSize(new Dimension(750, 290));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	/* Allow to set the action button */
	public void setUI() {
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				file1 = new File_Select();
				File file_selected = new File(file1.getFile());
				String ext = file1.getFile().substring(file1.getFile().lastIndexOf("."));
				if (!file_selected.exists()) {
					warning.setText("Erreur : le fichier n'existe pas.");
					isRightFormat1 = false;
				} else if (!ext.contains("csv")) {
					warning.setText("Erreur : le fichier n'est pas un fichier .csv.");
					isRightFormat1 = false;
				} else {
					isRightFormat1 = true;
					warning.setText("");
				}
				l1.setText(file1.getFile());
				if (isRightFormat1 && isRightFormat2) {
					validate.setEnabled(true);
				} else
					validate.setEnabled(false);
			}
		});
		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				file2 = new File_Select();
				File file_selected = new File(file2.getFile());
				String ext = file2.getFile().substring(file2.getFile().lastIndexOf("."));
				if (!file_selected.exists()) {
					warning.setText("Erreur : le fichier n'existe pas.");
					isRightFormat2 = false;
				} else if (!ext.contains("csv")) {
					warning.setText("Erreur : le fichier n'est pas un fichier .csv.");
					isRightFormat2 = false;
				} else {
					isRightFormat2 = true;
					warning.setText("");
				}
				l2.setText(file2.getFile());
				if (isRightFormat1 && isRightFormat2) {
					validate.setEnabled(true);
				} else
					validate.setEnabled(false);
			}
		});
		validate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new XML_Writer();
			}
		});

	}

	/*
	 * Static method to retrieve it in another class.
	 */
	public static String getFile1() {
		return l1.getText();
	}

	public static String getFile2() {
		return l2.getText();
	}

	/**
	 * To build the container with a gridBagConstraint layout.
	 */
	public void PanelMenu() {
		// --------------------------------------------------------
		validate.setEnabled(false);
		for (int i = 0; i < cells.length; i++) {
			cells[i].setBackground(Color.white);
			cells[i].setPreferredSize(new Dimension(400, 30));
			cells[i].setOpaque(false);
		}
		// *******************************************************
		warning.setForeground(Color.RED);
		container.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		container.add(info, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(b1, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		container.add(l1, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		container.add(b2, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		container.add(l2, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		container.add(validate, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		container.add(warning, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;

		for (int i = 0; i < cells.length; i++) {
			container.add(cells[i], gbc);
			gbc.gridx = 0;
			gbc.gridy = i;
			container.add(cells[i], gbc);
		}

	}
}
