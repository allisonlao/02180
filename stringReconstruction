import java.util.*;
public class Main {

// Insert your StringReconstruction function here, along with any subroutines you need 
// java.util has already been imported for you
public static String StringReconstruction(List<String> patterns, int k) {

    Map<String, List<String>> graph = new HashMap<>();
    Map<String, Integer> inDegree = new HashMap<>();
    Map<String, Integer> outDegree = new HashMap<>();
    
    for (String pattern : patterns) {
        String prefix = pattern.substring(0, k - 1);
        String suffix = pattern.substring(1);
        

        graph.computeIfAbsent(prefix, x -> new ArrayList<>()).add(suffix);
        
        outDegree.put(prefix, outDegree.getOrDefault(prefix, 0) + 1);
        inDegree.put(suffix, inDegree.getOrDefault(suffix, 0) + 1);
        
        inDegree.putIfAbsent(prefix, inDegree.getOrDefault(prefix, 0));
        outDegree.putIfAbsent(suffix, outDegree.getOrDefault(suffix, 0));
    }
    

    String startNode = "";
    for (String node : graph.keySet()) {
        int out = outDegree.getOrDefault(node, 0);
        int in = inDegree.getOrDefault(node, 0);
        
    
        if (out == in + 1) {
            startNode = node;
            break;
        }
    }
    
    if (startNode.isEmpty()) {
        startNode = graph.keySet().iterator().next();
    }
    
    Stack<String> stack = new Stack<>();
    List<String> path = new ArrayList<>();
    


    Map<String, List<String>> graphCopy = new HashMap<>();
    for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
        graphCopy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
    }
    
    stack.push(startNode);
    



    while (!stack.isEmpty()) {
        String current = stack.peek();
        
        if (graphCopy.containsKey(current) && !graphCopy.get(current).isEmpty()) {
   
            String next = graphCopy.get(current).remove(0);
            stack.push(next);
        } else {
            path.add(stack.pop());
        }
    }
    
    Collections.reverse(path);
    
    StringBuilder genome = new StringBuilder(path.get(0));
    
    //put it together
    for (int i = 1; i < path.size(); i++) {
        String node = path.get(i);
        genome.append(node.charAt(node.length() - 1));
    }
    
    return genome.toString();
}
}