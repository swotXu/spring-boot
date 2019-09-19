package com.testxu.basecoder.ThinkingInJava.c15Generic.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

/**
 * @Classname: spring-boot
 * @Date: 2019/9/19 0019 15:05
 * @Author: xu.hai
 * @Description:
 */
public class ReadTree {
    static class TreeNode<T>{
        T item;
        TreeNode(){
            this.item = null;
        }
        TreeNode(T item){
            this.item = item;
        }

    }
    static class TreeNodeGroup<T> extends TreeNode<T>{
        private List<TreeNode<T>> treeNodeArr = new ArrayList<TreeNode<T>>();

        TreeNodeGroup(){
            super(null);
        }
        TreeNodeGroup(T item){
            super(item);
        }

        int getNodeSize(){
            return this.treeNodeArr.size();
        }
        TreeNode getNodeAtIndex(int index){
            return this.treeNodeArr.get(index);
        }
        void addNode(TreeNode<T>... nodes){
            this.treeNodeArr.addAll(Arrays.asList(nodes));
        }
    }

    /**
     * 模拟获取一颗树
     * @return
     */
    static TreeNode getTree(){
        // 创建根节点
        TreeNodeGroup<String> root = new TreeNodeGroup<>("root");
        // 创建二级节点
        TreeNodeGroup<String> middle1_Node1 = new TreeNodeGroup<>("b_group");
        TreeNode<String> middle1_Node2 = new TreeNode<>("c_node");
        TreeNodeGroup<String> middle1_Node3 = new TreeNodeGroup<>("d_group");
        root.addNode(middle1_Node1, middle1_Node2, middle1_Node3);

        // 创建三级节点
        TreeNode<String> middle2_Node1 = new TreeNode<>("e_node");
        TreeNode<String> middle2_Node2 = new TreeNode<>("f_node");
        middle1_Node1.addNode(middle2_Node1, middle2_Node2);

        TreeNode<String> middle2_Node3 = new TreeNode<>("g_node");
        TreeNode<String> middle2_Node4 = new TreeNode<>("h_node");
        middle1_Node3.addNode(middle2_Node3, middle2_Node4);

        return root;
    }

    /**
     * 深度优先 - 迭代获取节点
     * @param root
     */
    static void depthFirst(TreeNode<String> root){
        LinkedStack<TreeNode<String>> nodeStack = new LinkedStack<>();
        TreeNode<String> node = root;
        nodeStack.push(node);

        while (!nodeStack.isEmpty()){
            node = nodeStack.pop();
            System.out.println(node.item);
            if (node instanceof TreeNodeGroup){
                for (int i = 0, len = ((TreeNodeGroup) node).getNodeSize(); i < len; i++) {
                    nodeStack.push(((TreeNodeGroup) node).getNodeAtIndex(i));
                }
            }
        }
    }

    /**
     * 广度优先 - 迭代获取节点
     * @param root
     */
    static void beradthFirst(TreeNode<String> root){
        LinkedQueue<TreeNode<String>> nodeQueue = new LinkedQueue<>();
        TreeNode<String> node = root;
        nodeQueue.enQueue(node);
        while (!nodeQueue.isEmpty()){
            node = nodeQueue.deQueue();
            System.out.println(node.item);
            if(node instanceof TreeNodeGroup){
                for (int i = 0, len = ((TreeNodeGroup) node).getNodeSize(); i < len; i++) {
                    nodeQueue.enQueue(((TreeNodeGroup) node).getNodeAtIndex(i));
                }
            }
        }
    }
    static void beradthFirstTree(TreeNode<String> root){
        LinkedQueue<TreeNode<String>> nodeQueue = new LinkedQueue<>();
        TreeNode<String> node = root;
        nodeQueue.enQueue(node);

        LinkedQueue<Integer> stepQueue = new LinkedQueue<>();
        int step = -1;
        while (!nodeQueue.isEmpty()){
            node = nodeQueue.deQueue();
            System.out.print(node.item);

            if(stepQueue.isEmpty()){
                System.out.println();
            }else if(step == -1) {
                step = stepQueue.deQueue();
                System.out.print(" ");
            }else if(step == 1){
                System.out.println();
            }else{
                step--;
                System.out.print(" ");
            }

            if(node instanceof TreeNodeGroup){
                stepQueue.enQueue(((TreeNodeGroup) node).getNodeSize());
                for (int i = 0, len = ((TreeNodeGroup) node).getNodeSize(); i < len; i++) {
                    nodeQueue.enQueue(((TreeNodeGroup) node).getNodeAtIndex(i));
                }
            }else{
                stepQueue.enQueue(0);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = getTree();

        depthFirst(root);
        System.out.println("==========================");
        beradthFirst(root);
        System.out.println("==========================");
        beradthFirstTree(root);
    }
}
