package org.johnny;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
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
        return findAllFiles(folderStr).filter(f -> f.toString().endsWith(".jar"));
    }

}
