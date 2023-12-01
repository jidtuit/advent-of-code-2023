package org.jid.adventofcode2023.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.jid.adventofcode2023.day1.D1P2;

public class Utils {

  public static Stream<String> lines(String filename) throws URISyntaxException, IOException {
    return Files.lines(Paths.get(D1P2.class.getClassLoader().getResource(filename).toURI()), UTF_8);
  }

  public static List<String> allLines(String filename) throws URISyntaxException, IOException {
    return Files.readAllLines(Paths.get(D1P2.class.getClassLoader().getResource(filename).toURI()), UTF_8);
  }

}
