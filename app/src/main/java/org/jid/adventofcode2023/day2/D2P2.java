package org.jid.adventofcode2023.day2;

import static java.lang.Integer.parseInt;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toUnmodifiableMap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.jid.adventofcode2023.utils.Utils;

public class D2P2 {

  private static final Predicate<Round> isValidRound = round -> {
    if (round.red() != null && round.red() > 12) {
      return false;
    }
    if (round.green() != null && round.green() > 13) {
      return false;
    }
    return round.blue() == null || round.blue() <= 14;
  };

  public static void main(String[] args) throws IOException, URISyntaxException {
    Stream<String> input = Utils.lines("d2p1.txt");
    long resultP1 = new D2P2().runP1(input);
    System.out.println("resultP1 = " + resultP1);

    Stream<String> inputP2 = Utils.lines("d2p1.txt");
    long resultP2 = new D2P2().runP2(inputP2);
    System.out.println("resultP2 = " + resultP2);
  }

  private long runP1(Stream<String> input) {
    return input.filter(not(String::isBlank)).map(this::parse).filter(Game::isValid).mapToInt(Game::id).sum();
  }

  private long runP2(Stream<String> input) {
    return input.filter(not(String::isBlank)).map(this::parse).mapToLong(Game::getPower).sum();
  }

  private Game parse(String line) {
    String[] gameAndRest = line.split(":");
    int gameId = parseInt(gameAndRest[0].substring(4).trim());

    String[] rounds = gameAndRest[1].split(";");
    List<Round> roundsList = Stream.of(rounds).map(round ->
        Stream.of(round.split(",")).map(numberColor -> numberColor.trim().split(" ")).collect(toUnmodifiableMap(nc -> nc[1], nc -> parseInt(nc[0])))
      ).map(colorNumberMap -> new Round(colorNumberMap.get("red"), colorNumberMap.get("green"), colorNumberMap.get("blue")))
      .toList();

    return new Game(gameId, roundsList, isValidRound);
  }

  private record Round(Integer red, Integer green, Integer blue) { }
  private record Game(int id, Collection<Round> rounds, Predicate<Round> isRoundValid) {
    public boolean isValid() {
      return rounds().stream().allMatch(isRoundValid);
    }

    public long getPower() {
      long maxReds = rounds().stream().mapToLong(round -> round.red() == null ? 0 : round.red()).max().orElseThrow();
      long maxGreens = rounds().stream().mapToLong(round -> round.green() == null ? 0 : round.green()).max().orElseThrow();
      long maxBlues = rounds().stream().mapToLong(round -> round.blue() == null ? 0 : round.blue()).max().orElseThrow();
      return maxReds * maxGreens * maxBlues;
    }
  }

}
