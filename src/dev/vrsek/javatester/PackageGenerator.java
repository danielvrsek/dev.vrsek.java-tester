package dev.vrsek.javatester;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PackageGenerator {
	public static void main(String[] args) throws Exception {
		String sourceCodesPath = "C:\\Tests\\05D_OFPA1_19s_HW_PKG\\OFPA1_19s_HW_PKG\\OFPA1_19s_HW_PKG_SRC\\eu\\pedu\\ofpa1_19s";
		Collection<String> allStudents = readAllStudents();
		Collection<String> submittedPackages = getSubmittedPackages(sourceCodesPath, allStudents);
		writeSubmittedPackages(submittedPackages);
	}

	private static void writeSubmittedPackages(Collection<String> submittedPackages) throws IOException {
		FileWriter fileWriter = new FileWriter("submittedPackages.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (String submittedPackage : submittedPackages) {
			printWriter.println(submittedPackage);
		}

		printWriter.close();
	}

	private static Collection<String> getSubmittedPackages(String baseDir, Collection<String> allStudents) {
		List<String> submittedPackages = new ArrayList<>();
		String currentDirectory = "";

		for (String line : allStudents) {
			if (line.startsWith(":")) {
				currentDirectory = line.substring(1);
				continue;
			}
			Path supposedDirPath = Paths.get(baseDir, currentDirectory, line);

			File dir = supposedDirPath.toFile();

			if (dir.exists()) {
				submittedPackages.add(currentDirectory + "." + line);
			}
		}

		return submittedPackages;
	}

	private static Collection<String> readAllStudents() throws FileNotFoundException {
		File cfg = new File("students.txt");

		BufferedReader reader = new BufferedReader(new FileReader(cfg));
		return reader.lines().collect(Collectors.toList());
	}
}
