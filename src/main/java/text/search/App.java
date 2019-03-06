package text.search;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class App {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Usage: text-search <dir_to_index>");
      System.exit(-1);
    }

    Searchable indexedContent = null;

    File toBeIndexed = new File(args[0]);

    try {
      indexedContent = toBeIndexed.isDirectory() ? new DirContent(toBeIndexed)
          : new FileContent(FileUtils.readFileToString(toBeIndexed, Charset.defaultCharset()), toBeIndexed.getAbsolutePath());
    } catch (IOException e) {
      System.err.println("Failed to index given path " + toBeIndexed.getAbsolutePath());
      e.printStackTrace();
    }

    Scanner keyboard = new Scanner(System.in);
    while (true) {
      System.out.print("search> ");
      String textToSearch = keyboard.nextLine();
      if (":q".equals(textToSearch)) {
        System.exit(0);
      } else {
        Map<String, Integer> results = indexedContent.search(textToSearch);
        results.entrySet().stream().sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue).reversed())
            .filter(e -> e.getValue() > 0).limit(10)
            .forEach((entry) -> System.out.println("file " + entry.getKey() + " match " + entry.getValue() + " %"));
      }
    }

  }
}
