import java.util.Scanner;
import java.util.InputMismatchException;
class DivideByZeroException extends Exception 
{
    public DivideByZeroException(String message)
    {
        super();
    }    
}

public class GuardedCalculator
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean success = false;

        while(!success)
        {
            try
            {
             System.out.println("Enter first Number: ");
              double num1 =  sc.nextDouble();   
             System.out.println("Enter second Number: ");
              double num2 = sc.nextDouble();

              System.out.print("Enter operator (+, -, *, /): ");
                char op = sc.next().charAt(0);

                double result = 0;
                   switch (op) {
                    case '+':
                        result = num1 + num2;
                        break;

                    case '-':
                        result = num1 - num2;
                        break;

                    case '*':
                        result = num1 * num2;
                        break;

                    case '/':
                        if (num2 == 0) {
                            throw new DivideByZeroException("Division by zero is not allowed!");
                        }
                        result = num1 / num2;
                        break;

                    default:
                        System.out.println("Invalid operator!");
                        continue;
                }
                 System.out.println("Result = " + result);
                success = true; 

            }

           
            catch (InputMismatchException e) {
                System.out.println("Error: Please enter valid numeric values.");
                sc.nextLine(); 
            }

           
            catch (DivideByZeroException e) {
                System.out.println("Error: " + e.getMessage());
            }

           
            finally {
                System.out.println("Calculation attempt logged.\n");
            }
        }

        sc.close();
    }
}
