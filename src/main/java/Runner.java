import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public List<DefineLetter> parseFile(String inputFileName) {
        List<DefineLetter> letters = new ArrayList<>();
        try {
            Path path = Paths.get(ClassLoader.getSystemResource(inputFileName).toURI());
            try (Stream<String> stream = Files.lines(path)) {
                stream.forEach((line) -> {
                    String[] words = DefineConstants.myPattern.split(line);

                    for (String x : words) {
                        if (x.length() != 0) {
                            parseWord(x, letters);
                        }
                    }
                });
            }
        } catch (IOException | URISyntaxException exception) {
            exception.printStackTrace();
        }
        return letters;

    }

    private void parseWord(String word, List<DefineLetter> letters) {
        Set<Character> characters = new HashSet<>();
        long count = 0L;
        for (int i = 0; i < word.length(); i++) {
            Character c = Character.toLowerCase(word.charAt(i));
            if (DefineConstants.vowels.contains(c)) {
                characters.add(c);
                count++;
            }
        }
        letters.add(new DefineLetter(word.length(), characters, count));
    }

    public void saveFile(Map<Set<Character>, Map<Integer, Double>> grouped, String outputFilename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFilename))) {
            for (Set<Character> characters : grouped.keySet()) {
                for (Integer wordLength : grouped.get(characters).keySet())
                    out.write("(" + characters + "," + wordLength + ") -> " + grouped.get(characters).get(wordLength) + '\n');
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Map<Set<Character>, Map<Integer, Double>> groupV(List<DefineLetter> letters) {
        Map<Set<Character>, Map<Integer, Double>> result = new HashMap<>();
        letters.stream()
                .collect(Collectors.groupingBy(DefineLetter::getCharacterSet,
                        Collectors.groupingBy(DefineLetter::getWordLength,
                                Collectors.averagingDouble(DefineLetter::getVowelNumber))))
                .forEach(result::put);
        return result;
    }

    public static void main(String[] args) {
        String inputFileName = "INPUT.TXT";
        String outputFileName = "OUTPUT.TXT";
        Runner runner = new Runner();
        getLogger().log(Level.INFO, "Parsing : {0}", inputFileName);
        List<DefineLetter> letters = runner.parseFile(inputFileName);
        getLogger().log(Level.INFO, "Saved in : {0}", outputFileName);
        runner.saveFile(runner.groupV(letters), outputFileName);
        getLogger().log(Level.INFO, "Executed successfully");
    }

    private static Logger getLogger() {
        return Logger.getLogger(Runner.class.getName());
    }
}
