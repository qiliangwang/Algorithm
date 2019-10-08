package leetcode.solutions;


import java.util.HashMap;

class LRUCache {

    /**
     *   HashMap + Double Linked List
     *
     *   put {
     *       exist {
     *           update value
     *       } not exist {
     *           capacity == 0 ? remove last element (here is head)
     *           put new value
     *       }
     *       remove to first position (here is tail)
     *   }
     *
     *   get {
     *       exist {
     *          return value
     *          remove to first position (here is tail)
     *       } not exist {
     *          return -1
     *       }
     *   }
     */

    class Node {
        int key;
        int value;
        Node next;
        Node pre;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) return -1;

        //if node is not tail move to tail
        if (node != tail) {
            if (node == head) {
                //reassign head
                head = head.next;
            } else {
                //(a -> node -> c) -> (a -> c)
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            // add node to tail (first position)
            tail.next = node;
            node.pre = tail;
            node.next = null;
            // reassign tail
            tail = node;
        }
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            if (node != tail) {
                if (node == head) {
                    head = head.next;
                } else {
                    //(a -> node -> c) -> (a -> c)
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
                tail.next = node;
                node.pre = tail;
                node.next = null;
                tail = node;
            }
        } else {
            Node newNode = new Node(key, value);
            if (capacity == 0) {
                Node temp = head;
                head = head.next;
                map.remove(temp.key);
                capacity ++;
            }
            if (head == null && tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                newNode.next = null;
            }
            tail = newNode;
            map.put(key, newNode);
            capacity--;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        System.out.println(cache.get(1));
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        System.out.println(cache.get(2));
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        System.out.println(cache.get(1));
        cache.get(3);       // returns 3
        System.out.println(cache.get(3));
        cache.get(4);       // returns 4
        System.out.println(cache.get(4));
    }
}
