// only allow swap one num at most, to see if it's in the dict || not swap? but one diff?
import java.util.*;
public class TrieQ{
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
        System.out.println("====");
        String test = "1 2 3";
        String[] sp = test.split(" ");
        for (String str : sp) {
            System.out.println(str);
        }
        // List<Integer> a = Arrays.asList(1);
        // System.out.println(a + "!!");
        // System.out.println(1+ 1&(-1));
        // System.out.println(3&(-3));
        printNum(3);
        printNum(-3);
    }

    public static void printNum(int n) {
        while(n != 0) {
            System.out.print((n & 1) + "->");
            n = (n >>> 1);
        }
        System.out.println();
    }

    public boolean existDiffVer(List<String> dict, String tar) { //Old dad's way: change Str in dict ab -> *b; a*  O(NK) memo and same for tar, find if corresponding str exsited in map
        root = new Trie();
        
        constructTree(dict);
        //dfs find tar, backtracking give it a oppotunity to go to wrong branch, but only one    No delete, only diff  Opt: diff length str in dict could be ignore when building the tree
        return true; // dummy
    }

    class Trie{
        Trie[] nexts;
        String str;
        public Trie() {
            nexts = new Trie[26];
        }
        public void setStr(String str) {
            this.str = str;
        }
    }

    Trie root;


    public void constructTree(List<String> dict) {
        for (String str : dict) {
            char[] chars = str.toCharArray();
            Trie node = root;
            for (char c : chars) {
                if (node.nexts[c - 'a'] == null) {
                    node.nexts[c - 'a'] = new Trie();
                }
                node = node.nexts[c - 'a'];
            }
            node.setStr(str);
        }
    }
}