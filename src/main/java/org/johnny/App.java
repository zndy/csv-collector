package org.johnny;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        CsvCollector csvCollector = new CsvCollector();
        System.out.println("JarPath: " + csvCollector.getCurrentWorkingDir());
        Stream<Path> allFiles = csvCollector.findAllCsvFiles(csvCollector.getCurrentWorkingDir());
        Path path = Paths.get(csvCollector.getCurrentWorkingDir() + "\\sum.txt");
        StringBuilder sb = new StringBuilder();
        allFiles.forEach(f -> {
            sb.append(f);
            sb.append("\n");
        });
        Files.write(path, sb.toString().getBytes());
    }
}
