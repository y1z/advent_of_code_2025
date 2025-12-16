import java.util.List;
import Utility.Util;
import dayz.*;
import dayz.day1;

interface PrintDayCaller
{
    void callback();
}

final class App
{
    public static void main (String args[])
    {
        format_day_solution(() -> {day1.part_1();}, 1,1);

        format_day_solution(() -> {day1.part_2_burte_force();}, 1,2);

        format_day_solution(() -> {day2.part_1();}, 2,1);

        format_day_solution(() -> {day3.part_1();}, 3,1);

        format_day_solution(() -> {day4.part_1();}, 4,1);

        format_day_solution(() -> {day5.part_1();}, 5,1);

        //format_day_solution(() -> {day5.part_2();}, 5,2);

        format_day_solution(() -> {day6.part_1();}, 6,1);

    }

    public static void format_day_solution(PrintDayCaller p, int day_num ,int part){
        StringBuilder message = new StringBuilder();
        message.append("Solving Day " + day_num);
        message.append(" Part " + part);
        System.out.println(message);

        p.callback(); 
        System.out.println("-------------------------------------------");
    }

}