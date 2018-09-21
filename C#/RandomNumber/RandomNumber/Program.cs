using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace RandomNumber
{
    class Program
    {
        static void Main(string[] args)
        {
            int num = 0;
            double counter = 0;
            Random rnd = new Random(DateTime.Now.Second);
            Random rnd2 = new Random(DateTime.Now.Millisecond);
            while (num != rnd2.Next(1, 42000)) {
                num = rnd.Next(1, 42000);
                counter++;
            }
            Console.WriteLine("It took {0} tries and the num is {1}", counter, num);
            Console.ReadLine();
        }
    }
}
