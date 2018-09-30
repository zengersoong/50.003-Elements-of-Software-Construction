package inClassDemo;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue <E> {
    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail
            = new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {//if this is not true, abort since other some other thread has already modified tail;
                if (tailNext != null) {// if this is true, Queue in intermediate state, advance tail someone in the middle of insertion 
                    tail.compareAndSet(curTail, tailNext);   // completion for other threads finish the job, when coming back for second round unlikely to get stuck
                } else {
                    // In normal state, try inserting new node
                    if (curTail.next.compareAndSet(null, newNode)) { 
                        // Insertion succeeded, try advancing tail
                        tail.compareAndSet(curTail, newNode);        
                        return true;
                   }
                }
            }
        }
    }
}