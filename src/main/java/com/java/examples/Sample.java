package com.java.examples;

public class Sample {
        public static Areas areas(double r, double a) {
            double data1 = r*r/2;
            System.out.println(data1);
            double data2 = Math.sin(22/7 * a/180);
            double segArea = (r*r/2)*((a*22/7) - Math.sin((a*22)/7));

            double area = (r*r*22)/7;
            System.out.println("Area:" + area);
            double remArea = area - segArea;
            Areas data = new Areas(segArea, remArea);
            System.out.println("Seg:"+segArea);
            System.out.println("Rem:" + remArea);
            return data;
        }

        public static class Areas {
            public final double inside, outside;

            public Areas(double inside, double outside) {
                this.inside = inside;
                this.outside = outside;
            }
        }

        public static void main(String[] args) {
            Areas areas = Sample.areas(10, 90);
            System.out.println("Areas: " + areas.inside + ", " + areas.outside);
        }

}
