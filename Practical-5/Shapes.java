abstract class Shape {
    abstract double area();
}
class Circle extends Shape
{
    private double radius;

    Circle(double radius)
    {
        this.radius = radius;
    }

    double area()
    {
        return Math.PI*radius*radius;
    }
}
class Rectangle extends Shape
{
    private double length;
    private double width;
    Rectangle(double length,double width)
    {
        this.length = length;
        this.width = width;
    }
    double area()
    {
        return length*width;
    }
}
class Triangle extends Shape
{
    private double base;
    private double height;
    Triangle(double base,double height)
    {
        this.base = base;
        this.height = height;
    }
    double area()
    {
        return 0.5*base*height;
    }
}
public class Shapes
{
    public static void main(String[] args) 
    {
        Shape[] Shapes = {
            new Circle(5.2),
            new Rectangle(2.5, 4.2),
            new Triangle(2.4, 3.5)
        };
        
        double total = 0;
        double largest = 0;

         for (Shape Shape : Shapes) {
            double area = Shape.area();

            System.out.println(Shape.getClass().getSimpleName() +" area = " + area);

            total += area;

             if (area > largest) {
                largest = area;
            }
        }

        System.out.println("Running total = " + total);
        System.out.println("Largest area = " + largest);



    }
}
