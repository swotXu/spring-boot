package com.testwork.demo.enumDemo.demo1;

public class EnumTest1 {

    enum Color{
        RED(0x1F, 0x2D, 0x3A),YELLOW(0xFE, 0xAE, 0xC3),PINK(0x00, 0xFF, 0xFF),GREET(0xFF,0x00,0xFF);

        int x;
        int y;
        int z;
        Color(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("["+this.ordinal()+"]").append(this.name())
                    .append("(").append(this.x).append(",")
                    .append(this.y).append(",")
                    .append(this.z).append(")");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        for (Color c : Color.values()){
            System.out.println(c);
        }
        for (String key : "RED YELLOW PINK GREET".split(" ")){
            Color color = Enum.valueOf(Color.class, key);
            System.out.println(color);
        }
    }
}
