public class Fibonacci 
{
        public static void main(String[] args)
        {
                Fibonacci Fib = new Fibonacci();

                for(int x=0;x<=15;x++)
                        System.out.println("Fibonacci{it}{rek}["+x+"] = {"+Fib.Fibonacci_it(x)+"}{"+Fib.Fibonacci_rek(x)+"}");
        }

        public long Fibonacci_it(int x)
        {
                if(x <= 0)
                        return 0;
                if(x == 1)
                        return 1;

                int val1 = 0;
                int val2 = 1;
                int sum = val1 + val2;

                for(int i=2;i<=x;i++)
                {
                        sum = val1 + val2;
                        val1 = val2;
                        val2 = sum;
                }
                return sum;
        }

        public int Fibonacci_rek(int x)
        {
                if(x <= 0)
                        return 0;
                if(x == 1 )
                        return 1;
                return (Fibonacci_rek(x-2) + Fibonacci_rek(x-1));
        }
}
