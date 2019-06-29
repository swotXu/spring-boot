package com.testwork.demo.AI;

import java.util.Scanner;

public class SjbAI {
    private final int SHI_TOU = 0;
    private final int JIAN_DAO = 1;
    private final int BU = 2;

    private final int ERRO = -1;
    private Scanner scanner = new Scanner(System.in);
    private int[][] memory = new int[3][3];     // 记录前面比赛数据
    private int prev = SHI_TOU;  // 上一步对手记录 初始化石头

    public void start(){
        int user_now = ERRO; // 玩家当前出拳结果
        int i = 0;
        while (ERRO != (user_now = userInput())){
            // 猜测用户当前出的结果
            int guess = getMax(memory, prev);
            System.out.println("~~~~~~~猜测：~~~~~~~" );
            printResult(false, guess);
            // 计算机器人应该出什么能用
            int win = getWin(guess);
            System.out.print("应该出这个能赢：" + win);
            printResult(true, win);
            System.out.println("~~~~~~~~~~~~~~~~~~~" );

            // 查看结果
            int pk = pk(user_now, win);
            pkPrint(pk);

            System.out.println("---------------------");
            // 记录结果
            memory[prev][user_now] ++;
            prev = user_now;
            i ++;
            if(i == 0) break;
        }
        printMemory();
    }
    private void printMemory(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(memory[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 获取最大概率
     * @param memory
     * @param prev
     * @return
     */
    private int getMax(int[][] memory, int prev){
        int max = -1;
        for (int i = 0; i < 3; i++) {
            max = memory[prev][i] > max ? memory[prev][i] : max;
        }
        return max;
    }

    /**
     * 计算应该出什么
     * @param i
     * @return
     */
    private int getWin(int i){
        return (i + 2 ) % 3;
    }

    /**
     * pk
     * @param player    玩家结果
     * @param ai    机器人结果
     * @return  1-机器人赢  0-平局 -1-玩家赢
     */
    private int pk(int player, int ai){
        if(player == ai) return 0;
        return (player + 2 ) % 3 == ai ? -1 : 1;
    }
    private void pkPrint(int result){
        System.out.println("------------公布结果：");
        switch (result) {
            case -1:
                System.out.println("机器人赢"); break;
            case 0:
                System.out.println("平局"); break;
            case 1:
                System.out.println("玩家赢"); break;
        }
    }

    private int userInput(){
        System.out.println("石头剪刀...(0.石头，1.剪刀，2.布)...\n" + "请出：");
        String inStr = scanner.nextLine();
        if (inStr == null) return ERRO;

        inStr = inStr.length() > 1 ? inStr.substring(0,1) : inStr;
        int now = Integer.valueOf(inStr);

        if (now > BU) {
            System.out.println("出拳不符合规范！");
            return ERRO;
        }
        printResult(false, now);
        return now;
    }

    /**
     *
     * @param type      true - 机器人  false - 你
     * @param result
     */
    private void printResult(boolean type, int result){
        String name = type? "机器人" : "玩家";
        switch (result) {
            case 0:
                System.out.println(name + "出：石头"); break;
            case 1:
                System.out.println(name + "出：剪刀"); break;
            case 2:
                System.out.println(name + "出：布"); break;
        }
    }

    public static void main(String[] args) {
        SjbAI ai = new SjbAI();
        ai.start();

    }
}
