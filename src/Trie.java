import java.util.*;

/**
 * Created by Dhruv on 17/06/17.
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String line) {
        HashMap<String, TrieNode> children = root.children;

        List<String> wordList = new ArrayList<String>(Arrays.asList(line.split(Main.DELIM)));
        //Collections.reverse(wordList);

        for(int i =0; i<wordList.size(); i++){

            String word = wordList.get(i).trim();

            TrieNode t;
            if(children.containsKey(word)){
                t = children.get(word);
            }else{
                t = new TrieNode(word);
                children.put(word, t);
            }

            children = t.children;

            //set leaf node
            if(i==line.length()-1)
                t.isLeaf = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String line) {
        TrieNode t = searchNode(line);
        if(t != null && t.isLeaf)
            return true;
        else
            return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String line) {
        if(searchNode(line) == null)
            return false;
        else
            return true;
    }

    public TrieNode searchNode(String line){

        Map<String, TrieNode> children = root.children;
        TrieNode t = null;
        List<String> wordList = new ArrayList<String>(Arrays.asList(line.split(Main.DELIM)));
        //Collections.reverse(wordList);
        for(int i=0; i<wordList.size(); i++){
            String word = wordList.get(i).trim();
            if(children.containsKey(word)){
                t = children.get(word);
                children = t.children;
            }else{
                return null;
            }
        }

        return t;
    }

    /*
        It will return the complete closest matching address
     */
    public String getFullLine(String line){

        Map<String, TrieNode> children = root.children;
        TrieNode t = null;
        List<String> wordList = new ArrayList<String>(Arrays.asList(line.split(Main.DELIM)));
        List<String> outputList = new ArrayList<String>();
        //Collections.reverse(wordList);
        for(int i=0; i<wordList.size(); i++){
            String word = wordList.get(i).trim();
            if(children.containsKey(word)){
                t = children.get(word);
                children = t.children;
                outputList.add(word);
            }else{

                while (t!=null && children.size()>0) {
                    outputList.add(children.keySet().toArray()[0].toString());
                    t = children.get(children.keySet().toArray()[0]);
                    children = t.children;
                }
                break;
            }
        }

        Collections.reverse(outputList);
        return StringUtils.joinString(outputList,Main.DELIM);
    }

}

