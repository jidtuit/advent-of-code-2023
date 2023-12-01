package org.jid.adventofcode2023.day1;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.function.Predicate.not;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;
import org.jid.adventofcode2023.utils.Utils;

public class D1P2 {

  public static void main(String[] args) throws IOException, URISyntaxException {
    Stream<String> input = Utils.lines("d1p1.txt");
    int result = new D1P2().run(input);
    System.out.println("result = " + result);
  }

  private int run(Stream<String> input) {

    return input
      .filter(not(String::isBlank))
      .map(this::preprocessCharsToDigits)
      .map(this::getFirstLast)
      .mapToInt(Integer::parseInt)
      .sum();
  }

  private String getFirstLast(String line) {
    return line.substring(0, 1) + line.substring(line.length() - 1);
  }

  private String preprocessCharsToDigits(String line) {
    StringBuilder resp = new StringBuilder();

    String[] splittedLine = line.split("");
    for (int i = 0; i < splittedLine.length; i++) {
      String digit = str2Digit(line, i);
      if (digit != null) {
        resp.append(digit);
      }
    }

    return resp.toString();
  }

  private String str2Digit(String line, int currentPos) {
    String originalChar = String.valueOf(line.charAt(currentPos));
    return switch (originalChar) {
      case "o" -> genericCase(line, currentPos, "one", "1");
      case "t" -> genericCase(line, currentPos, Map.of("two", "2", "three", "3"));
      case "f" -> genericCase(line, currentPos, Map.of("four", "4", "five", "5"));
      case "s" -> genericCase(line, currentPos, Map.of("six", "6", "seven", "7"));
      case "e" -> genericCase(line, currentPos, "eight", "8");
      case "n" -> genericCase(line, currentPos, "nine", "9");
      default -> Character.isDigit(originalChar.charAt(0)) ? originalChar : null;
    };
  }

  private String genericCase(String line, int currentPos, Map<String, String> mapper) {
    String resp = null;

    for (var entry : mapper.entrySet()) {
      resp = genericCase(line, currentPos, entry.getKey(), entry.getValue());
      if (resp != null) {
        break;
      }
    }

    return resp;
  }

  private String genericCase(String line, int currentPos, String letter, String resultIfNumber) {
    if (line.length() < currentPos + letter.length()) {
      return null;
    }

    String potentialNumber = line.substring(currentPos, currentPos + letter.length());
    if (!letter.equalsIgnoreCase(potentialNumber)) {
      return null;
    }

    return resultIfNumber;
  }


}
