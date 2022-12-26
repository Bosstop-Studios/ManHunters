package ml.bosstop.commons.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}
