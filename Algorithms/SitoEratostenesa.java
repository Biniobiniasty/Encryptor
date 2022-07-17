public class prog
{
        public static void main(String[] args)
        {

                int tabLength = 200;

                int[] tab = new int[tabLength];

                for(int x=0;x<tabLength;x++)
                        tab[x] = 0;

                for(int x=2;x<=(int)Math.sqrt(tabLength);x++)
                {
                        int index = x*2;
                        while(index < tabLength)
                        {
                                tab[index] = 1;
                                index += x;
                        }
                }

                for(int x=2;x<tabLength;x++)
                        if(tab[x] == 0)
                                System.out.print(x + ", ");



        }
}
