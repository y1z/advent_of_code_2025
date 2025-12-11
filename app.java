import java.util.List;
import Utility.Util;
import dayz.*;
import dayz.day1;

final class App
{
    public static void main (String args[])
    {

        System.out.println("mod operations");
        for (int i = 0; i < 30; i++) {
            System.out.println(i % 2);
            
        }

        System.out.println("solving day 1 part 1\n");
        day1.part_1();
        System.out.println("solving day 1 part 2\n");
        day1.part_2_burte_force();

        System.out.println("solving day 2 part 1\n");
        day2.part_1();
    }
}