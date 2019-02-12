package text.search;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DirContent implements Searchable {

  private final Map<String, Searchable> contents = new HashMap<>();

  public DirContent(File dirPath) throws IOException {
    for (File file : dirPath.listFiles()) {
      System.out.println("Indexing " + file.getAbsolutePath());
      contents.put(file.getAbsolutePath(), file.isDirectory() ? new DirContent(file)
          : new FileContent(FileUtils.readFileToString(file, Charset.defaultCharset()), file.getAbsolutePath()));
    }
  }

  @Override
  public Map<String, Integer> search(String searchTerm) {
    Map<String, Integer> results = new HashMap<>();
    for (Map.Entry<String, Searchable> content : contents.entrySet()) {
      Map<String, Integer> result = content.getValue().search(searchTerm);
      results.putAll(result);
    }
    return results;
  }
}
