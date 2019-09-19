package com.testxu.basecoder.ThinkingInJava.c15Generic.demo2;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/19 0019 16:01
 * @Author: xu.hai
 * @Description: 链表队列
 */
public class LinkedQueue<T> {
    static class Note<T>{
        private T item;
        private Note<T> next;

        Note(){
            this.item = null;
        }
        Note(T item){
            this.item = item;
        }
    }

    private Note<T> topNode;
    private Note<T> bottonNode;
    private int size;

    public LinkedQueue(){
        this.size = 0;
        this.topNode = new Note<T>(null);
        this.bottonNode = this.topNode;
    }

    /**
     * 入队时，元素从队尾入队，同时变更队尾指针
     * @param item
     * @return
     */
    public LinkedQueue<T> enQueue(T item){
        Note<T> newNote = new Note<>(item);
        this.bottonNode.next = newNote;
        this.bottonNode = newNote;
        size++;
        return this;
    }

    /**
     * 出队时，头节点始终不变，出队元素为 this.topNode.next
     * @return
     */
    public T deQueue(){
        if(this.size == 0){
            return this.bottonNode.item;
        }
        if(this.size == 1){
            T item = this.bottonNode.item;
            this.bottonNode = this.topNode;
            this.size--;
            return item;
        }
        Note<T> next = this.topNode.next;
        this.topNode.next = next.next;
        this.size--;
        return next.item;
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public LinkedQueue<T> clear(){
        this.topNode = this.bottonNode;
        this.size = 0;
        return this;
    }

    public static void main(String[] args) {
        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        // 插入顺序为： 0-9 共10个元素
        for (int i = 0; i < 10; i++) {
            linkedQueue.enQueue("index:" + i);
        }
        System.out.println(linkedQueue.size());
        // 期望结果： 0-9 共10个元素
        while (!linkedQueue.isEmpty()){
            System.out.println(linkedQueue.deQueue());
        }
    }
}
