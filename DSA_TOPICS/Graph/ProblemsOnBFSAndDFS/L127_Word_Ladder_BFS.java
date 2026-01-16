package ProblemsOnBFSAndDFS;


import java.util.*;

public class L127_Word_Ladder_BFS {
    private class Pair<K,V>{
        private K key;
        private V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>();

        for(String word : wordList) set.add(word);

        Queue<Pair<String , Integer>> queue = new LinkedList<>();

        queue.add(new Pair<>(beginWord,1));

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while(!queue.isEmpty()){

            Pair<String, Integer> currentPair = queue.poll();
            String key = currentPair.getKey();
            int value = currentPair.getValue();

            if(key.equals(endWord)) return value;

            for(int i=0; i<key.length(); i++){
                for(int j=97; j<=122; j++){
                    char c = (char)j;
                    String toFind = key.substring(0,i)+c+key.substring(i+1,key.length());
                    if(set.contains(toFind) && !visited.contains(toFind)){
                        queue.add(new Pair<>(toFind,value+1));
                        visited.add(toFind);
                    }
                }
            }

        }

        return 0;
    }
}
