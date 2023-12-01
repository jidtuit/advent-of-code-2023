package org.jid.adventofcode2023.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class D1P1 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    Stream<String> input = Files.lines(Paths.get(D1P1.class.getClassLoader().getResource("d1p1.txt").toURI()));
    int result = new D1P1().run(input);
    System.out.println("result = " + result);
  }

  private int run(Stream<String> input) {
    return input.mapToInt(line -> Integer.parseInt(getFirstLast(line))).sum();
  }

  private String getFirstLast(String line) {
    String init = null;
    String end = null;
    for(char c : line.toCharArray()) {
      if (Character.isDigit(c)) {
        if (init == null) {
          init = String.valueOf(c);
        }
        end = String.valueOf(c);
      }
    }
    return init + end;
  }

}
