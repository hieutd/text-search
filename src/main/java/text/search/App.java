package text.search;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(results.entrySet());
        entries.sort((Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> {
              if (o1.getValue().equals(o2.getValue())) return 0;
              return o1.getValue() < o2.getValue() ? 1 : -1;
            }
        );

        for (int i = 0; i < ((entries.size() < 10) ? entries.size() : 10); i++) {
          Map.Entry<String, Integer> entry = entries.get(i);
          System.out.println("file " + entry.getKey() + " match " + entry.getValue() + " %");
        }
      }
    }

  }
}
