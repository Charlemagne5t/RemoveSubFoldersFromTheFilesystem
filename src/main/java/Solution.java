import java.util.*;
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>();
        Trie trie = new Trie();
        for(int i = 0; i < folder.length; i++){
            trie.insert(folder[i], i);
        }
        dfs(res, folder, trie.root);
        return res;
    }
    void dfs(List<String> res, String[] folder, TrieNode root){
        if(root.isTerminal){
            res.add(folder[root.index]);
        }
        for(TrieNode child : root.children.values()){
            dfs(res, folder, child);
        }

    }
}
class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode("", -1);
    }

    public void insert(String path, int index) {
        String[] dirs = path.split("/");
        TrieNode temp = root;
        for (int i = 0; i < dirs.length; i++) {
            if(!temp.children.containsKey(dirs[i])){
                TrieNode node = new TrieNode(dirs[i], index);
                temp.children.put(dirs[i], node);
            }else if(temp.children.containsKey(dirs[i]) && temp.children.get(dirs[i]).isTerminal){
                return;
            }
            temp = temp.children.get(dirs[i]);
            if(i == dirs.length - 1){
                temp.isTerminal = true;
                temp.children = new HashMap<>();
                temp.index = index;
            }
        }

    }


}

class TrieNode{
    String value;
    Map<String, TrieNode> children = new HashMap<>();
    boolean isTerminal;
    int index = -1;
    public TrieNode(String value, int index) {
        this.value = value;
        this.index = index;
        this.isTerminal = false;
    }
}