package text.search;

import java.util.*;

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

  public Map<String, Integer> search(String searchTerm) {
    String[] words = searchTerm.split("\\s+");
    Set<String> matched = new HashSet<>();
    for (String word : words) {
      if (matched.size() < words.length && wordIndex.contains(word)) {
        matched.add(word);
      }
    }
    Map<String, Integer> result = new HashMap<>();
    result.put(path, Math.round((float) matched.size() / words.length * 100));
    return result;
  }
}
