import java.util.*;
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        List<List<StringIndex>> list = new ArrayList<>();
        int n = folder.length;

        for(int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            String[] fol = folder[i].split("/");
            for(int j = 1; j < fol.length; j++) {
                list.get(i).add(new StringIndex(fol[j], i));
            }
        }
        list.sort(Comparator.comparing(List::size));
        List<String> res = new ArrayList<>();
        Trie trie = new Trie();
        for(int i = 0; i < n; i++) {
            if(trie.insert(list.get(i))){
                res.add(folder[list.get(i).get(0).index] );
            }
        }
        return res;
    }
}
class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean insert(List<StringIndex> path) {
        int pathLength = path.size();
        TrieNode temp = root;
        for (int i = 0; i < pathLength; i++) {
            if(!temp.children.containsKey(path.get(i).str)){
                TrieNode node = new TrieNode();
                temp.children.put(path.get(i).str, node);
            } else {
                if(temp.children.get(path.get(i).str).isTerminal) {
                    return false;
                }
            }
            temp = temp.children.get(path.get(i).str);
            if(i == pathLength - 1){
                temp.isTerminal = true;
            }
        }
        return true;

    }


}

class TrieNode{

    Map<String, TrieNode> children = new HashMap<>();
    boolean isTerminal;

    public TrieNode() {

        this.isTerminal = false;
    }
}
class StringIndex {
    String str;
    int index;

    public StringIndex(String str, int index) {
        this.str = str;
        this.index = index;
    }

    @Override
    public String toString() {
        return "StringIndex{" +
                "str='" + str + '\'' +
                ", index=" + index +
                '}';
    }
}