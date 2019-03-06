package org.johnny;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        CsvCollector csvCollector = new CsvCollector();
        csvCollector.collectToFile(csvCollector.getCurrentWorkingDir());
    }
}
