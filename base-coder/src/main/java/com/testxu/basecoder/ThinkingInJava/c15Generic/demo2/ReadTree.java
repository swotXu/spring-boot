package com.testxu.basecoder.ThinkingInJava.c15Generic.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        TreeNodeGroup<String> middle2_Node2 = new TreeNodeGroup<>("f_node");
        middle1_Node1.addNode(middle2_Node1, middle2_Node2);

        TreeNode<String> middle2_Node3 = new TreeNode<>("g_node");
        TreeNode<String> middle2_Node4 = new TreeNode<>("h_node");
        middle1_Node3.addNode(middle2_Node3, middle2_Node4);

        // 创建四级节点
        TreeNode<String> middle3_Node1 = new TreeNode<>("i_node");
        TreeNode<String> middle3_Node2 = new TreeNode<>("j_node");
        middle2_Node2.addNode(middle3_Node1, middle3_Node2);

        return root;
    }

    /**
     * 深度优先 - 迭代获取节点
     * 使用栈存储实现
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
     * 采用队列方式实现
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

    /**
     * 广度优先 - 迭代获取节点，并展示树形结构
     * 采用队列方式实现
     * @param root
     */
    static void beradthFirstTree(TreeNode<String> root){
        LinkedQueue<TreeNode<String>> nodeQueue = new LinkedQueue<>();
        TreeNode<String> node = root;
        nodeQueue.enQueue(node);

        int step = 0; // 记录当前层节点数
        int nextStep = 0; // 记录下一层节点数
        while (!nodeQueue.isEmpty()){
            node = nodeQueue.deQueue();
            System.out.print(node.item);

            int nextLen = 0;
            if(node instanceof TreeNodeGroup){
                nextLen = ((TreeNodeGroup) node).getNodeSize();
                System.out.print("("+nextLen+")");
                for (int i = 0, len = ((TreeNodeGroup) node).getNodeSize(); i < len; i++) {
                    nodeQueue.enQueue(((TreeNodeGroup) node).getNodeAtIndex(i));
                }
            }
            // 若当前节点数量 step!=0 说明此层未结束，则继续叠加下层节点数量,并-1
            if(step > 0){
                nextStep += nextLen;
                step--;
            }
            // 若当前节点数量 step=0 ,打印换行，同时更新下一层节点数，否则打印空格
            if(step == 0){
                System.out.println();
                // 如果下层节点 nextStep=0，说明此层可能为根节点（初次进入）或为最后叶子节点，直接赋值临时下层节点即可。
                // 否者，当前为中间层。
                step = nextStep == 0? nextLen : nextStep;
            }else{
                System.out.print(" ");
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
