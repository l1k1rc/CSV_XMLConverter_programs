package CSV_to_XML_Converter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Allow to generate a XML file associated to the Bonita BPM WebApp
 * 
 * @author MS
 * @see CSV_Reader
 */
public class XML_Writer {
	private CSV_Reader csv_Reader;
	private File file = new File("DI_users.xml");

	/**
	 * Allow to write a XML file with FileWriter methods.
	 */
	public XML_Writer() {
		csv_Reader = new CSV_Reader(UI_Converter.getFile1());
		// csv_Reader = new CSV_Reader("DI_usersv2.csv");
		FileWriter fw;

		try {
			fw = new FileWriter(file);
			String prologue = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
					+ "<organization:Organization xmlns:organization=\"http://documentation.bonitasoft.com/organization-xml-schema/1.1\">\n";

			String firstOne = "<customUserInfoDefinitions>\n" + "        <customUserInfoDefinition>\n"
					+ "            <name>NumDSP</name>\n" + "            <description></description>\n"
					+ "        </customUserInfoDefinition>\n" + "    </customUserInfoDefinitions>\n";
			String root = "<users>\n";
			String end_root = "</users>\n";
			fw.write(prologue);
			fw.write(firstOne);
			fw.write(root);
			String data_lines_users = null;
			String data_lines_memberships = null;
			String data_lines_role = null;
			// 14 et 16 !

			/*
			 * for (int i = 0; i < csv_Reader.getDatas().size(); i++) { for (int j = 0; j <
			 * csv_Reader.getDatas().get(i).length; j++)
			 * System.out.println(csv_Reader.getDatas().get(i)[j]); }
			 */

			for (int i = 1; i < csv_Reader.getDatas().size(); i++) {
				data_lines_users = "     <user userName=\"" + csv_Reader.getDatas().get(i)[0] + "\">\n"
						+ "            <password encrypted=\"false\">" + csv_Reader.getDatas().get(i)[1]
						+ "</password>\n" + "            <firstName>" + csv_Reader.getDatas().get(i)[2]
						+ "</firstName>\n" + "            <lastName>" + csv_Reader.getDatas().get(i)[3]
						+ "</lastName>\n" + "            <title>" + csv_Reader.getDatas().get(i)[4] + "</title>\n"
						+ "            <jobTitle>" + csv_Reader.getDatas().get(i)[5] + "</jobTitle>\n"
						+ askForManager(data_wanted(i, 0), data_wanted(i, 6)) + "            <enabled>true</enabled>\n"
						+ "            <personalData/>\n" + "            <professionalData>\n"
						+ "                <email>" + csv_Reader.getDatas().get(i)[7] + "</email>\n"
						+ "                <phoneNumber>" + csv_Reader.getDatas().get(i)[8] + "</phoneNumber>\n"
						+ "                <building>" + csv_Reader.getDatas().get(i)[9] + "</building>\n"
						+ "                <room>" + csv_Reader.getDatas().get(i)[10] + "</room>\n"
						+ "            </professionalData>\n" + "            <customUserInfoValues>\n"
						+ "                <customUserInfoValue>\n" + "                    <name>NumDSP</name>\n"
						+ "                    <value>" + csv_Reader.getDatas().get(i)[11] + "</value>\n"
						+ "                </customUserInfoValue>\n" + "            </customUserInfoValues>\n"
						+ "                 </user>\n";
				fw.write(data_lines_users);
			}
			data_lines_role = " <roles>\n" + "        <role name=\"Admins\">\n"
					+ "            <displayName>Administrateurs</displayName>\n"
					+ "            <description>Les administrateurs</description>\n" + "        </role>\n"
					+ "        <role name=\"CSI\">\n" + "            <displayName>CSI</displayName>\n"
					+ "            <description>Correspondant du Système d'Information</description>\n"
					+ "        </role>\n" + "        <role name=\"DOC-TRAIT-AIX\">\n"
					+ "            <displayName>DOC-TRAIT-AIX</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"DOC-TRAIT-CHINON\">\n"
					+ "            <displayName>DOC-TRAIT-CHINON</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"DOC-TRAIT-SEXTANT\">\n"
					+ "            <displayName>DOC-TRAIT-SEXTANT</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"DOC-VISU-AIX\">\n" + "            <displayName>DOC-VISU-AIX</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"DOC-VISU-CHINON\">\n"
					+ "            <displayName>DOC-VISU-CHINON</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"DOC-VISU-SEXTANT\">\n"
					+ "            <displayName>DOC-VISU-SEXTANT</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"GCOMB\">\n"
					+ "            <displayName>GCOMB</displayName>\n"
					+ "            <description>Rôle GCOMB</description>\n" + "        </role>\n"
					+ "        <role name=\"LOG-TRAIT-AIX\">\n"
					+ "            <displayName>LOG-TRAIT-AIX</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"LOG-TRAIT-CHINON\">\n"
					+ "            <displayName>LOG-TRAIT-CHINON</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"LOG-TRAIT-SEXTANT\">\n"
					+ "            <displayName>LOG-TRAIT-SEXTANT</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"LOG-VISU-AIX\">\n"
					+ "            <displayName>LOG-VISU-TEGG</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"LOG-VISU-CHINON\">\n"
					+ "            <displayName>LOG-VISU-CHINON</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"LOG-VISU-SEXTANT\">\n"
					+ "            <displayName>LOG-VISU-SEXTANT</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"Magasin\">\n"
					+ "            <displayName>Magasin</displayName>\n"
					+ "            <description>Magasin pour dotation de matériel</description>\n" + "        </role>\n"
					+ "        <role name=\"Manager\">\n" + "            <displayName>Manager</displayName>\n"
					+ "            <description>Manager</description>\n" + "        </role>\n"
					+ "        <role name=\"Membre\">\n" + "            <displayName>Membre</displayName>\n"
					+ "            <description>Tous les utilisateurs</description>\n" + "        </role>\n"
					+ "        <role name=\"MOE\">\n" + "            <displayName>MOE</displayName>\n"
					+ "            <description>Prestataires MOE</description>\n" + "        </role>\n"
					+ "        <role name=\"NA-TRAIT-AIX\">\n" + "            <displayName>NA-TRAIT-AIX</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "		   <role name=\"Externe\">\n" + "            <displayName>Externe</displayName>\n"
					+ "            <description>Prestataires</description>\n" + "        </role>\n"
					+ "        <role name=\"NA-TRAIT-CHINON\">\n"
					+ "            <displayName>NA-TRAIT-CHINON</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"NA-TRAIT-SEXTANT\">\n"
					+ "            <displayName>NA-TRAIT-SEXTANT</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"NA-VISU-AIX\">\n" + "            <displayName>NA-VISU-AIX</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"NA-VISU-CHINON\">\n"
					+ "            <displayName>NA-VISU-CHINON</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"NA-VISU-SEXTANT\">\n"
					+ "            <displayName>NA-VISU-SEXTANT</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"OIU\">\n"
					+ "            <displayName>OIU</displayName>\n"
					+ "            <description>Role OIU</description>\n" + "        </role>\n"
					+ "        <role name=\"RSI\">\n" + "            <displayName>RSI</displayName>\n"
					+ "            <description>Rôle RSI</description>\n" + "        </role>\n"
					+ "        <role name=\"SI-TRAIT-AIX\">\n" + "            <displayName>SI-TRAIT-AIX</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"SI-TRAIT-CHINON\">\n"
					+ "            <displayName>SI-TRAIT-CHINON</displayName>\n"
					+ "            <description>Traitement des demandes du site</description>\n" + "        </role>\n"
					+ "        <role name=\"SI-TRAIT-SEXTANT\">\n"
					+ "            <displayName>SI-TRAIT-SEXTANT</displayName>\n"
					+ "            <description>Traitement des demandes du site      </description>\n"
					+ "        </role>\n" + "        <role name=\"SI-VISU-AIX\">\n"
					+ "            <displayName>SI-VISU-AIX</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"SI-VISU-CHINON\">\n"
					+ "            <displayName>SI-CHINON</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "        <role name=\"SI-VISU-SEXTANT\">\n"
					+ "            <displayName>SI-VISU-SEXTANT</displayName>\n"
					+ "            <description>Visualistation des demandes du site</description>\n"
					+ "        </role>\n" + "    </roles>\n";
			fw.write(end_root);
			fw.write(data_lines_role);
			// Groups Part
			fw.write("  <groups>\n");
			fw.write(XML_Writer_Structure(UI_Converter.getFile2()));
			fw.write("  </groups>\n");
			// Memberships Part
			fw.write("<memberships>\n");
			/* For DI members */
			for (int i = 1; i < csv_Reader.getDatas().size(); i++) {
				data_lines_memberships = "        <membership>\n" + "            <userName>"
						+ csv_Reader.getDatas().get(i)[0] + "</userName>\n" + "            <roleName>"
						+ data_wanted(i, 12) + "</roleName>\n" + "            <groupName>"
						+ data_wanted(i, 13).replace("&", "et") + "</groupName>\n"
						+ "            <assignedBy>I09675</assignedBy>\n"
						+ "            <assignedDate>0</assignedDate>\n" + "        </membership>\n";
				fw.write(data_lines_memberships);
			}
			/* For role associated */
			/*
			 * Take all users three by three from fourteen to the max length of the data for
			 * one user <b>Exemple :</b> If the user selected have 3 roles, the loop will go
			 * until the data 22 from 14 because you have 3 data for 1 role each time
			 */
			for (int i = 1; i < csv_Reader.getDatas().size(); i++) {
				/* take all data for the user one by one */
				for (int j = 14; j < csv_Reader.getDatas().get(i).length; j += 3) {
					data_lines_memberships = "        <membership>\n" + "            <userName>"
							+ csv_Reader.getDatas().get(i)[0] + "</userName>\n" + "            <roleName>"
							+ data_wanted(i, j) + "</roleName>\n" + "            <groupName>"
							+ data_wanted(i, j + 1).replace("&", "et") + "</groupName>\n"
							+ "            <groupParentPath>" + data_wanted(i, j + 2).replace("&", "et")
							+ "</groupParentPath>\n" + "            <assignedBy>I09675</assignedBy>\n"
							+ "            <assignedDate>0</assignedDate>\n" + "        </membership>\n";
					fw.write(data_lines_memberships);
				}
			}
			fw.write("</memberships>\n");
			// End of XML file
			fw.write("</organization:Organization>");
			// flows closed
			fw.close();
			System.out.println("Fichier créer.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error : Vérifier le séparateur de CSV_Reader");
		}
		// open the XML file created before.
		if (Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)) {
			try {
				java.awt.Desktop.getDesktop().open(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Check if the size of the table at the requested value is transferable or not.
	 * 
	 * @param position : the position of the user.
	 * @param asked    : the data wanted in the array of string which contains all
	 *                 datas associated to the user selected.
	 */
	public String data_wanted(int position, int asked) {
		// System.out.println(csv_Reader.getDatas().get(position).length);
		if (csv_Reader.getDatas().get(position).length <= asked)
			return "";
		else
			return csv_Reader.getDatas().get(position)[asked];
	}

	/**
	 * Allow to check if the user have the same nni than himself.
	 * 
	 * @param nni        : user nni
	 * @param nniManager : nni of the manager node
	 * @return the string which contain the node
	 */
	public String askForManager(String nni, String nniManager) {
		if (nni.contains(nniManager))
			return "	<manager/>";
		else
			return "	<manager>" + nniManager + "</manager>\n";
	}

	/**
	 * Allow to write the structure part of the XML file.
	 * 
	 * @param file_path : file send by the user
	 * @return a string which contains all XML code associated to the structyre
	 *         root.
	 */
	public String XML_Writer_Structure(String file_path) {
		CSV_Reader csv_Reader_structure = new CSV_Reader(file_path);
		String data_lines_group = "      <group name=\"" + csv_Reader_structure.getDatas().get(0)[0].replace("&", "et")
				+ "\">\n" + "            <displayName>" + csv_Reader_structure.getDatas().get(0)[2].replace("&", "et")
				+ "</displayName>\n" + "            <description>" + csv_Reader_structure.getDatas().get(0)[3]
				+ "</description>\n" + "        </group>\n";

		try {

			for (int i = 1; i < csv_Reader_structure.getDatas().size(); i++) {
				data_lines_group += "      <group name=\""
						+ csv_Reader_structure.getDatas().get(i)[0].replace("&", "et") + "\" parentPath=\""
						+ csv_Reader_structure.getDatas().get(i)[1].replace("&", "et") + "\">\n"
						+ "            <displayName>" + csv_Reader_structure.getDatas().get(i)[2].replace("&", "et")
						+ "</displayName>\n" + "            <description>" + csv_Reader_structure.getDatas().get(i)[3]
						+ "</description>\n" + "        </group>\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data_lines_group;
	}
}
