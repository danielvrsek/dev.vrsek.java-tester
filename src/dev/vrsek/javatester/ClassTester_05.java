package dev.vrsek.javatester;

import dev.vrsek.javatester.core.configuration.ClassTestConfigurationDeserializer;
import dev.vrsek.utils.Logger;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class ClassTester_05 {
	private static String classDir = "C:\\Tests\\build\\classes\\";
	private static String submittedPackagesBase = "eu.pedu.ofpa1_19s";

	public static void main(String[] args) throws Exception {
		ClassTester tester = new ClassTester(classDir, submittedPackagesBase);

		ClassTestConfigurationDeserializer deserializer = new ClassTestConfigurationDeserializer();

		String[] submittedPackages = tester.readPackages("submittedPackages.txt").toArray(String[]::new);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Damácí úkol 05");

		for (int i = 0; i < submittedPackages.length; i++) {
			XSSFRow row = sheet.createRow(i);

			String fullPackageName = submittedPackagesBase + "." + submittedPackages[i];

			if (!tester.packageExists(fullPackageName)) {
				Logger.log("- Špatný název baličku");
			}

			// Factory
			String classIdentifier = tester.getClassName(fullPackageName, "Factory.class");
			if (classIdentifier == null) {
				Logger.log("- V balíčku se nenachazí tovarní třída nebo je ve špatnem balíčku.");
			} else {
				tester.evaluate(classIdentifier, deserializer.deserialize(tester.readConfig("config/factory.config.json")));
			}

			// Test
			classIdentifier = tester.getClassName(fullPackageName, "_Test.class");
			if (classIdentifier == null) {
				Logger.log("- V balíčku se nenachazí testovací třída nebo je ve špatnem balíčku.");
			} else {
				tester.evaluate(classIdentifier, deserializer.deserialize(tester.readConfig("config/test.config.json")));
			}

			// VehicleN
			classIdentifier = tester.getClassName(fullPackageName, "1N.class");
			if (classIdentifier == null) {
				Logger.log("- V balíčku se nenachazí 'VehicleN' nebo je ve špatnem balíčku.");
			} else {
				tester.evaluate(classIdentifier, deserializer.deserialize(tester.readConfig("config/vehicle.config.json")));
			}

			// VehicleE
			classIdentifier = tester.getClassName(fullPackageName, "1E.class");
			if (classIdentifier == null) {
				Logger.log("- V balíčku se nenachazí 'VehicleE' nebo je ve špatnem balíčku.");
			} else {
				tester.evaluate(classIdentifier, deserializer.deserialize(tester.readConfig("config/vehicle.config.json")));
			}

			// VehicleW
			classIdentifier = tester.getClassName(fullPackageName, "1W.class");
			if (classIdentifier == null) {
				Logger.log("- V balíčku se nenachazí 'VehicleW' nebo je ve špatnem balíčku.");
			} else {
				tester.evaluate(classIdentifier, deserializer.deserialize(tester.readConfig("config/vehicle.config.json")));
			}

			// VehicleS
			classIdentifier = tester.getClassName(fullPackageName, "1S.class");
			if (classIdentifier == null) {
				Logger.log("- V balíčku se nenachazí 'VehicleS' je ve špatnem balíčku.");
			} else {
				tester.evaluate(classIdentifier, deserializer.deserialize(tester.readConfig("config/vehicle.config.json")));
			}

			Logger logger = Logger.refresh();

			row.createCell(0).setCellValue(submittedPackages[i]);
			StringBuilder sb = new StringBuilder();
			for (String message : logger.getMessages()) {
				sb.append(" " + message).append("\r\n");
			}
			row.createCell(1).setCellValue(sb.toString());
			XSSFCellStyle style = workbook.createCellStyle();
			style.setWrapText(true);
			row.setRowStyle(style);
			row.setHeight((short)-1);
		}

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);

		String fileName = "domaciukol_05.xlsx";
		FileOutputStream out = new FileOutputStream(fileName);
		workbook.write(out);
		out.close();
	}
}
