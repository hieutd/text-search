package text.search;

import java.util.*;
import java.util.stream.Collectors;

public class FileContent implements Searchable {

  private final Scanner fileScanner;

  private final String path;

  private final Set<String> wordIndex = new HashSet<>();

  public FileContent(String content, String path) {
    this.path = path;
    fileScanner = new Scanner(content);
    index();
  }

  private void index() {
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();
      wordIndex.addAll(Arrays.asList(removePunctuation(line).split("\\s+")));
    }
  }

  private String removePunctuation(String lineOfText) {
    return lineOfText.replaceAll("\\p{Punct}+", " ");
  }

  @Override
  public Map<String, Integer> search(String searchTerm) {
    Set<String> words = new HashSet<>(Arrays.asList(searchTerm.split("\\s+")));
    Set<String> matched = wordIndex.stream().filter(words::contains).collect(Collectors.toSet());;
    Map<String, Integer> result = new HashMap<>();
    result.put(path, Math.round((float) matched.size() / words.size() * 100));
    return result;
  }
}
