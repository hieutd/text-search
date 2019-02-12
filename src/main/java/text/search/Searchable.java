package text.search;

import java.util.Map;

/**
 * Implementation must perform a search and return all resource paths together with their matching ranks
 */
interface Searchable {

  /**
   * Return a map of resources and there matching rank
   * @param searchTerm the phrases to search for
   * @return map of resource path and the corresponding search rank
   */
  Map<String, Integer> search(String searchTerm);
}
