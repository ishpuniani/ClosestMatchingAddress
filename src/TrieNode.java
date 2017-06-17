import java.util.HashMap;

/**
 * Created by Dhruv on 17/06/17.
 */
public class TrieNode {
    String string;
    HashMap<String, TrieNode> children = new HashMap<String, TrieNode>();
    boolean isLeaf;

    public TrieNode() {}

    public TrieNode(String string){
        this.string = string;
    }
}
