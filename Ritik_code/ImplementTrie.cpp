/*
    Implementing a trie data structure
*/

struct TrieNode{
    char data;
    bool we;
    TrieNode * child[26];
};

TrieNode * root;

class Trie {

public:

    /** Initialize your data structure here. */
    Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    void insert(string word) {
        TrieNode * node = root;
        for(int i = 0; i < word.length(); i++){
            int idx = word[i] - 'a';
            if(node -> child[idx] == NULL){
                TrieNode * newNode = new TrieNode();
                newNode -> data = word[i];
                newNode -> we = false;
                node -> child[idx] = newNode;
            }
            node = node -> child[idx];
        }
        node -> we = true;
    }

    /** Returns if the word is in the trie. */
    bool search(string word) {
        int n = word.size();
        TrieNode * node = root;
        for(int i = 0; i < n; i++){
            int idx = word[i] - 'a';
            if(node -> child[idx] == NULL)
                return false;
            
            node = node -> child[idx];
        }
        return node -> we;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string word) {
        int n = word.size();
        TrieNode * node = root;
        for(int i = 0; i < n; i++){
            int idx = word[i] - 'a';
            if(node -> child[idx] == NULL)
                return false;
            node = node -> child[idx];
        }
        return true;
    }
};