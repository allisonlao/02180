import java.util.*;

public class Main {
    
    public static List<String> ContigGeneration(List<String> Patterns) {

        // build the de Bruijn graph
        Map<String, List<String>> adjacencyList = buildDeBruijnGraph(Patterns);
        
        // Find all maximal non branching paths
        List<List<String>> paths = findMaximalNonBranchingPaths(adjacencyList);
        
        // Convert paths to contigs
        List<String> contigs = new ArrayList<>();
        for (List<String> path : paths) {
            contigs.add(assembleContig(path));
        }
        
        // Sort the contigs in order
        Collections.sort(contigs);
        
        return contigs;
    }
    
    //building deBruijn graph
    private static Map<String, List<String>> buildDeBruijnGraph(List<String> patterns) {
        Map<String, List<String>> graph = new HashMap<>();
        
        for (String pattern : patterns) {
            String prefix = pattern.substring(0, pattern.length() - 1);
            String suffix = pattern.substring(1);
            
            if (!graph.containsKey(prefix)) {
                graph.put(prefix, new ArrayList<>());
            }
            graph.get(prefix).add(suffix);
        }
        
        return graph;
    }

    
    private static List<List<String>> findMaximalNonBranchingPaths(Map<String, List<String>> graph) {
        List<List<String>> paths = new ArrayList<>();
        
        // Calculate in degree for each node
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Integer> outDegree = new HashMap<>();
        
        //initialize all degrees to 0
        for (String node : graph.keySet()) {
            inDegree.putIfAbsent(node, 0);
            outDegree.putIfAbsent(node, 0);
        }
        
        //calculate degrees, make sure all nodes are in outDegree
        for (String node : graph.keySet()) {
            outDegree.put(node, graph.get(node).size());
            for (String neighbor : graph.get(node)) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
                outDegree.putIfAbsent(neighbor, 0);
            }
        }
        
        Set<String> visited = new HashSet<>();
        
        // find paths from nodes where (in != 1 or out != 1)
        for (String node : graph.keySet()) {
            if (!(inDegree.getOrDefault(node, 0) == 1 && outDegree.getOrDefault(node, 0) == 1)) {
                if (outDegree.getOrDefault(node, 0) > 0) {
                    for (String neighbor : graph.get(node)) {
                        List<String> path = new ArrayList<>();
                        path.add(node);
                        path.add(neighbor);
                        visited.add(node);
                        visited.add(neighbor);
                        String current = neighbor;
                        while (inDegree.getOrDefault(current, 0) == 1 && 
                               outDegree.getOrDefault(current, 0) == 1) {
                            List<String> neighbors = graph.get(current);
                            if (neighbors == null || neighbors.isEmpty()) {
                                break;
                            }
                            String next = neighbors.get(0);
                            path.add(next);
                            visited.add(current);
                            current = next;
                        }
                        
                        paths.add(path);
                    }
                }
            }
        }
        
        // 2. find the isolated cycles (nodes not visited yet that have in=1 and out=1)
        for (String node : graph.keySet()) {
            if (!visited.contains(node) && 
                inDegree.getOrDefault(node, 0) == 1 && 
                outDegree.getOrDefault(node, 0) == 1) {
                
                List<String> cycle = new ArrayList<>();
                String start = node;
                String current = node;
                
                do {
                    cycle.add(current);
                    visited.add(current);
                    List<String> neighbors = graph.get(current);
                    if (neighbors == null || neighbors.isEmpty()) {
                        break;
                    }
                    current = neighbors.get(0);
                } while (!current.equals(start) && !visited.contains(current));
                
                // if we completed a cycle back to start
                if (current.equals(start) && cycle.size() > 0) {
                    cycle.add(start);
                    paths.add(cycle);
                }
            }
        }
        
        return paths;
    }
    
    private static String assembleContig(List<String> path) {
        if (path.size() == 0) return "";
        
        StringBuilder contig = new StringBuilder(path.get(0));
        
        for (int i = 1; i < path.size(); i++) {
            String node = path.get(i);
            contig.append(node.charAt(node.length() - 1));
        }
        
        return contig.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> patterns = new ArrayList<>();
        
        // Read input
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] kmers = line.split(" ");
            patterns.addAll(Arrays.asList(kmers));
        }
        
        // Generate contigs
        List<String> contigs = ContigGeneration(patterns);
        
        // Output contigs
        for (int i = 0; i < contigs.size(); i++) {
            System.out.print(contigs.get(i));
            if (i < contigs.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}