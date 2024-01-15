package todo.java;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 * MyFileReaderWriter class enables to file I/O. The charset of an I/O-ed file must be utf8.
 */
class MyFileReaderWriter {
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * Reads {@param} file and outputs the list of its lines.
     *
     * @param filePath the file path to be open
     * @return {@code lineStream.toList()} the list of its lines
     * @throws UncheckedIOException when file opening gets troubled.
     */
    List<String> myFileReader(Path filePath) {
        try (Stream<String> lineStream = Files.lines(filePath, UTF8)) {
            return lineStream.toList();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Writes the lines to {@param} file.
     *
     * @param filePath the file path to be open
     * @param lines the list of lines that would be written
     * @throws UncheckedIOException when file opening gets troubled
     */
    void myFileWriter(Path filePath, List<String> lines) {
        try (BufferedWriter out = Files.newBufferedWriter(filePath, UTF8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : lines) {
                out.write(line);
                out.newLine();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
