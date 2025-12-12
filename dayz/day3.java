package dayz;

import java.util.HashMap;

import Utility.Util;

// https://adventofcode.com/2025/day/3
public final class day3 
{
    static final String file_path = "day_3_part_1.txt";

    public static long part_1()
    {
        long result = 0;

        var lines = Util.read_every_line(file_path);

        for (int i = 0; i < lines.size(); i++) 
        {
            result += find_biggest_num(lines.get(i));
            //System.out.printf("biggest value = [%d]\n",  find_biggest_num(lines.get(i)));
        }

        System.out.printf("Result = [%d]\n",result);
        return result;
    }

    public static long find_biggest_num(CharSequence cs){
        long result = 0;
        long biggest_tens_digit = 0;
        long biggest_ones_digit = 0;

        var value_map = new HashMap<Character, Long>();

        for(long i = 0; i < 10; ++i)
        {
            value_map.put((char)('0' + i), i);
        }

        int biggest_digit_index = 0;
        // check everything except the last one
        for(int i = 0; i < cs.length() - 1; ++i)
        {
            final long value = value_map.get(cs.charAt(i));
            if(biggest_tens_digit < value)
            {
                biggest_digit_index = i;
                biggest_tens_digit = value;
            }
        }

        
        for (int i = biggest_digit_index + 1; i < cs.length(); ++i)
        {
            final long value = value_map.get(cs.charAt(i));
            if(biggest_ones_digit < value){
                biggest_ones_digit = value;
            }

        }

        result = (biggest_tens_digit * 10) + (biggest_ones_digit);

        return result;
    }

    
}
