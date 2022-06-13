package src.searching;
public class IterativeBST<Key extends Comparable<Key>, Value> {

    private Node root;
    private Node cache;

    private class Node{
        Key key;
        Value val;
        private Node left, right;
        
        public Node(Key key, Value val){
            this.key=key;
            this.val=val;
            
        }

    }
   

    public Value get(Key key){
        if(key==null)return null;
        if(key==cache.key)return cache.val;
        Node current=root;
        Value result = null;
        while(current!=null){
            int cmp=key.compareTo(current.key);
            if(cmp>0){ 
                current=current.right;
                continue;
            }
            else if(cmp<0){
                current=current.left;
                continue;
            }
            else{
                result=current.val;
                cache=current;  //store location of most recent get operation
                break;
            }
        }
        return result;

    }
    public void put(Key key, Value val){
        if(root==null){
            root=new Node(key,val);
            cache=root;
            return;
        }
        else if(key==cache.key){
            cache.val=val;
            return;
        }
        Node parent = null, x = root;
        while(x!=null){
            parent=x;
            int cmp=key.compareTo(x.key);
            if(cmp>0)x=x.right;
            else if(cmp<0)x=x.left;
            else{
                x.val=val;
                return;
            }
        }
        int cmp=key.compareTo(parent.key);
        if(cmp>0)parent.right=new Node(key, val);
        else parent.left=new Node(key, val);

        }
        public static void main(String[] args){
            IterativeBST<String, Integer> test =new IterativeBST<String, Integer>();
            test.put("hi",1);
            test.put("hello",4);
            test.put("seven",7);
            test.put("hi",3);
            test.get("hello");
            System.out.println(test.get("hi"));
        }
        
        
    }

    

