import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;             // Import the Scanner class to read text files
import java.io.BufferedReader;
import Utility.Util;

final class App
{
    public static void main (String args[])
    {
        System.out.println("solving part 1 day 1");

        System.out.println(Math.abs((10 - 20) % 100));
        System.out.println((10 + 20) % 100);
        System.out.println((100 + 20) % 100);
        System.out.println((99 + 1 ) % 100);
        System.out.println((99 + 1 ) % 100);
        System.out.println(Math.abs ((0 - 1 ) % 100));
        System.out.println(Math.abs ((0 - 15 ) % (-100)));
        System.out.println();

        day_1_part_1();
    }

    // https://adventofcode.com/2025/day/1
    public static void day_1_part_1() 
    {
        List<String> every_line = Util.read_every_line("day_1_part_1.txt");
        int current_dial_value = 50;
        int zeros_count = 0;
        for(int i = 0; i < every_line.size(); ++i)
        {
            final String current_line = every_line.get(i);
            final char direction = current_line.charAt(0);
            if (direction == 'R')
            {
                final int value = Integer.parseInt(current_line.substring(1));
                current_dial_value = Math.abs((current_dial_value + value) % 100);
            }
            else if (direction == 'L')
            {
                final int value = Integer.parseInt(current_line.substring(1));
                for(int j = 0; j < value; ++j)
                {
                    current_dial_value--;
                    if(current_dial_value < 0)
                    {
                        current_dial_value = 99;
                    }
                }
                //current_dial_value = Math.abs((current_dial_value - value) % 100);
            }
            if(current_dial_value == 0){
                zeros_count++;
            }
            System.out.println(current_dial_value);
        }

        System.out.println("total zeros = " + zeros_count);

    }
}