public static List<String> FindClumps(String Genome, int k, int L, int t) {
    HashSet<String> resultSet = new HashSet<>();

    java.util.HashMap<String, Integer> kmerCount = new java.util.HashMap<>();
    
    // Initialize the first window of length L
    for (int i = 0; i <= L - k; i++) {
        String kmer = Genome.substring(i, i + k);
        kmerCount.put(kmer, kmerCount.getOrDefault(kmer, 0) + 1);
    }
    
    // Check the first window
    for (String kmer : kmerCount.keySet()) {
        if (kmerCount.get(kmer) >= t) {
            resultSet.add(kmer);
        }
    }
    
    // slide the window across the genome
    for (int i = 1; i <= Genome.length() - L; i++) {

        // Remove the k-mer leaving the window
        String leavingKmer = Genome.substring(i - 1, i - 1 + k);
        int leavingCount = kmerCount.get(leavingKmer);
        if (leavingCount == 1) {
            kmerCount.remove(leavingKmer);
        } else {
            kmerCount.put(leavingKmer, leavingCount - 1);
        }
        
        // Add the new kmer entering the window
        String enteringKmer = Genome.substring(i + L - k, i + L);
        kmerCount.put(enteringKmer, kmerCount.getOrDefault(enteringKmer, 0) + 1);
        
        // check if this kmer now meets the threshold
        if (kmerCount.get(enteringKmer) >= t) {
            resultSet.add(enteringKmer);
        }
    }
    
    //sorted list
    List<String> result = new ArrayList<>(resultSet);
    Collections.sort(result);
    
    return result;
}