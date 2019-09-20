package com.testxu.basecoder.ThinkingInJava.c15Generic.demo2;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/19 0019 11:16
 * @Author: xu.hai
 * @Description: 链表栈
 */
public class LinkedStack<T> {
    private class Note{
        private T item;
        private Note next;
        private Note(){
            this.item = null;
            this.next = null;
        }
        private Note(T item, Note next){
            this.item = item;
            this.next = next;
        }
        private boolean isEmpty(){
            return next == null && item == null;
        }
    }

    private Note top;
    private int size;

    public LinkedStack(){
        this.top = new Note();
        this.size = 0;
    }

    /**
     * 入栈时，元素从头插入
     * @param item
     */
    public void push(T item){
        this.top = new Note(item, this.top);
        size++;
    }

    /**
     * 出栈时，元素从头移除
     * @return
     */
    public T pop(){
        T item = this.top.item;
        if(!this.top.isEmpty()){
            this.top = this.top.next;
            size--;
        }
        return item;
    }

    public int size(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public LinkedStack<T> clear(){
        this.top = new Note();
        this.size = 0;
        return this;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        // 插入顺序为： 0-9 共10个元素
        for (int i=0; i<10; i++)
            stack.push("index:" + i);

        System.out.println(stack.size);
        // 期望结果： 9-0 共10个元素
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}
