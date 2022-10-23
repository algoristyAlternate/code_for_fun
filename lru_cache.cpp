class Node {
public:
    int val,key;
    Node *left,*right;
    
    Node(int key, int val){
        this->val = val;
        this->key = key;
        this->left = NULL;
        this->right = NULL;
    }
    
};

class LRUCache {
public:
    Node *head, *tail;
    int capacity,size;
    unordered_map<int,Node *> key_address;
    
    LRUCache(int capacity) {
        this->capacity = capacity;
        this->size = 0;
        this->head = NULL;
        this->tail = NULL;
        
    }
    
    void delete_node(Node *p){
        if(p->left!=NULL){
            p->left->right = p->right;
        }
        else{
            head = p->right;
        }
        
        if(p->right!=NULL){
            p->right->left = p->left;
        }
        else{
            tail = p->left;
        }
        
        p->left = NULL;
        p->right = NULL;
        
    }
    
    void insert(Node *p){
        if(head == NULL){
            head = tail = p;
        }
        else{
            tail->right = p;
            p->left = tail;
            tail = p;
        }
    }
    
    
    int get(int key) {
        if(key_address.find(key) == key_address.end()){
            return -1;
        }
        
        Node *add = key_address[key];
        
        delete_node(add);
        insert(add);
        
        return add->val;
    }
    
    void put(int key, int value) {
        Node *new_node = new Node(key,value);
        
        if(key_address.find(key)!=key_address.end()){
            key_address[key]->val = value;
            delete_node(key_address[key]);
            insert(key_address[key]);
        }
        else{
            key_address[key]=new_node;
            if(size==capacity){
                key_address.erase(head->key);
                delete_node(head);
                insert(new_node);
            }
            else{
                size++;
                insert(new_node);
            }
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */