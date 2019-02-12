package text.search;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class FileContentTest {

    private String content = "one line \n"
            + "two line \n"
            + "three line \n"
        + "a line with a comma, a 'quote' and a dot."
        + "Here are multi spaces  and a\ttab";
    
    private String path = "/a/file";

    @Test
    public void shouldReturn100IfContainAllWords() {
        String searchTerm = "three line";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }

    @Test
    public void shouldReturn100IfContainAllWordsInDifferentOrder() {
        String searchTerm = "line three two";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }

    @Test
    public void shouldReturn0IfContainNoWord() {
        String searchTerm = "four";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 0);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(0));
    }

    @Test
    public void shouldReturn50IfContainOneOutOfTwo() {
        String searchTerm = "four line";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 50);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(50));
    }

    @Test
    public void shouldReturn33IfContainOneOutOfThree() {
        String searchTerm = "one four five";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 33);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(33));
    }

    @Test
    public void shouldReturn67IfContainTwoOutOfThree() {
        String searchTerm = "one two five";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 67);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(67));
    }

    @Test
    public void shouldMatchWordPreceedComma() {
        String searchTerm = "a comma";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }

    @Test
    public void shouldMatchWordPreceedDot() {
        String searchTerm = "dot";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }

    @Test
    public void shouldMatchWordSurroundedByQuote() {
        String searchTerm = "quote";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }

    @Test
    public void shouldMatchWordNextToMultiWhitspace() {
        String searchTerm = "spaces";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }

    @Test
    public void shouldMatchWordNextToTab() {
        String searchTerm = "a tab";
        Searchable target = new FileContent(content, path);
        Map<String, Integer> expected = new HashMap<>();
        expected.put(path, 100);
        Map<String, Integer> result = target.search(searchTerm);
        assertThat(result.size(), is(1));
        assertThat(result.get(path), is(100));
    }
}
