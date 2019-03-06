package org.johnny;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CsvCollector {

    public String getCurrentWorkingDir() {
        return System.getProperty("user.dir");
    }

    public Stream<Path> findAllFiles(String folderStr) throws IOException {
        Path folder = Paths.get(folderStr);
        return Files.walk(folder).filter(p -> p.toFile().isFile());
    }

    public Stream<Path> findAllCsvFiles(String folderStr) throws IOException {
        return findAllFiles(folderStr).filter(f -> f.toString().endsWith(".csv"));
    }

    public String concatScvFiles(String folderStr) throws IOException {
        StringBuilder sb = new StringBuilder();
        Stream<Path> allCsvFiles = findAllCsvFiles(folderStr);

        AtomicInteger index = new AtomicInteger(0);
        allCsvFiles.forEach(csv -> {
            try {
                List<String> allLines = Files.readAllLines(csv);
                if (index.getAndIncrement() == 0) {
                    sb.append(allLines.get(0));
                    sb.append("\n");
                }
                sb.append(allLines.get(1));
                sb.append("\n");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        return sb.toString();
    }

    public void collectToFile(String folderStr) throws IOException {
        Path resultPath = Paths.get(folderStr + "\\result.csv");
        Files.deleteIfExists(resultPath);

        String result = concatScvFiles(folderStr);
        Files.write(resultPath, result.getBytes());
    }

}
