
public class q7 {

    public static void main(String[] args) {
        Circle c1 = new Circle(10);
        Circle c2 = new Circle(20);
        Circle c3 = new Circle(10);

        System.out.println("Circle c1 : Radius = " + c1.getRadius() + " units, Area = " + c1.getArea() + " sq. units");
        System.out.println("Circle c2 : Radius = " + c2.getRadius() + " units, Area = " + c2.getArea() + " sq. units");
        System.out.println("Circle c3 : Radius = " + c3.getRadius() + " units, Area = " + c3.getArea() + " sq. units");

        System.out.print("c1 == c2 : ");
        if (c1.equals(c2))
            System.out.print("Circles are equal.\n");
        else
            System.out.print("Circles are not equal.\n");
        System.out.print("c1 == c3 : ");
        if (c1.equals(c3))
            System.out.print("Circles are equal.\n");
        else
            System.out.print("Circles are not equal.\n");
    }

    private static class Circle {
        private double radius, area;

        Circle(double r) {
            this.radius = r;
            area = Math.PI * r * r;
        }

        public double getRadius() { return radius; }

        public void setRadius(double radius) { this.radius = radius; }

        public double getArea() { return area; }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Circle c = (Circle) o;
            return this.getRadius() == c.getRadius();
        }
    }
}