import java.util.*;
public class Main {
// Insert your DeBruijnKmers function here, along with any subroutines you need 
// java.util has already been imported for you
  public static Map<String, List<String>> DeBruijnKmers(List<String> kMers) {
      Map<String, List<String>> graph = new HashMap<>();
    
      //getting all the prefixes and suffixes
      for (String k: kMers) {
          String suffix = k.substring(1);
          String prefix = k.substring(0,k.length() - 1);
          graph.putIfAbsent(prefix, new ArrayList<>());
          graph.get(prefix).add(suffix);
    
}
      
      //sort
      for (String key : graph.keySet()) {
          Collections.sort(graph.get(key));
      }
      
      return graph;
  }

}