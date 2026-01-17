package ProblemsOnBFSAndDFS;

import java.util.*;
public class L126_Word_Ladder_II {
    private class Pair<K,V>{

        private K key;
        private V value;

        
        private Pair(K key , V value){
            this.key = key;
            this.value = value;
        }

        private K getKey(){
            return this.key;
        }

        private void setKey(K key){
            this.key = key;
        }

        private V getValue(){
            return this.value;
        }

        private void setValue(K key){
            this.key = key;
        }


    }

    public void DFS(String key , Integer currentLevel , List<String> currentList , List<List<String>> toReturn , Map<String , Integer> map , String startword){
        if(key.equals(startword)){
            currentList.add(key);
            toReturn.add(new ArrayList<>(currentList));
            currentList.remove(currentList.size()-1);
            return;
        }

        currentList.add(key);

        for(int i=0; i<key.length(); i++){
            for(int j=97; j<=122; j++){
                char ch = (char)j;
                String toFind = key.substring(0,i)+ch+key.substring(i+1,key.length());
                if(map.containsKey(toFind) && map.get(toFind)+1 == currentLevel){
                    DFS(toFind,currentLevel-1,currentList,toReturn,map,startword);
                }
            }
        }

        currentList.remove(currentList.size()-1);

    }

    public void reverseEachElement(List<List<String>> toReturn){

        for(List<String> list : toReturn){

            int i = 0;
            int j = list.size()-1;

            while(i<j){
                String tempi = list.get(i);
                list.set(i,list.get(j));
                list.set(j,tempi);
                i++;
                j--;
            }

        }

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> ans = new ArrayList<>();

        Map<String,Integer> wordlevel = new HashMap<>();

        Queue<Pair<String,Integer>> queue = new LinkedList<>();

        Set<String> set = new HashSet<>();

        for(String word : wordList) set.add(word);

        Set<String> visited = new HashSet<>();

        queue.offer(new Pair<>(beginWord,0));

        //int maxLevel =-1;

        visited.add(beginWord);

        wordlevel.put(beginWord,0);

        while(!queue.isEmpty()){

            Pair<String,Integer> current = queue.poll();
            String key = current.getKey();
            Integer value = current.getValue();

            if(key.equals(endWord)) {
                // maxLevel = value;
                break;
            }

            for(int i=0; i<key.length(); i++){

                for(int j=97; j<=122; j++){

                    char ch = (char)j;
                    String toFind = key.substring(0,i)+ch+key.substring(i+1,key.length());

                    if(set.contains(toFind) && !visited.contains(toFind)){
                        queue.offer(new Pair(toFind,value+1));
                        visited.add(toFind);
                        wordlevel.put(toFind,value+1);
                    }

                }


            }


        }

        List<String> currentList = new ArrayList<>();

        if(wordlevel.containsKey(endWord)){
            DFS(endWord , wordlevel.get(endWord) ,  currentList , ans ,  wordlevel , beginWord);
        }
        // for(Map.Entry<String,Integer> entry : wordlevel.entrySet()){
        //     System.out.println(entry.getKey()+"->"+entry.getValue());
        // }
        reverseEachElement(ans);
        return ans;




    }

}
