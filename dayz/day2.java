package dayz;
import java.io.StringReader;
import java.nio.CharBuffer;

import Utility.Util;

final class number_range
{
    public number_range()
    {
        lower = 0;
        upper = 0;
    }
    public long lower = 0;
    public long upper = 0;

    public static number_range init(number_range nr)
    {
        nr = new number_range();
        return nr;
    }
}

public final class day2
{
    // https://adventofcode.com/2025/day/2
    public static void part_1()
    {
        final String entire_file = Util.read_entire_file("day_2_part_1.txt");

        final String[] individual_pair = entire_file.split("[,]");

        number_range[] ranges = new number_range[individual_pair.length];
        for (int i = 0; i < ranges.length; ++i)
        {
            ranges[i] = number_range.init(ranges[i]);
        }

        for (int i = 0; i < individual_pair.length; ++i)
        {
            final int separation_index = individual_pair[i].indexOf('-');

            ranges[i].lower = Long.parseLong(individual_pair[i], 0, separation_index, 10);
            ranges[i].upper = Long.parseLong(individual_pair[i], separation_index + 1, individual_pair[i].length(), 10);
        }

        long result = 0;
        for (int i = 0; i < ranges.length; ++i)
        {
            final long delta = ranges[i].upper - ranges[i].lower;
            long current_value = ranges[i].lower;

            for (int j = 0; j < delta; ++j)
            {
                final long first_half = get_first_half_of_number(current_value);
                final long second_half = get_second_half_of_number(current_value);
                final boolean is_even = (long)(Math.floor(Math.log10(current_value)) + 1) % 2 == 0;
                if (first_half == second_half && is_even)
                {
                    // System.out.printf("first half = %d\nSecond half = %d\nWhole number = %d\n\n",first_half,second_half,current_value);
                    result += current_value;
                }
                current_value += 1;
            }
        }

        System.out.printf("Result = [%d]\n\n", result);
    }

    /**
     * @param number The number to get the first half of.
     * @return the first half of a number example 1245 should return 45 and 124 should return 24
     */
    public static long get_second_half_of_number(long number)
    {
        final long digits = (long)(Math.floor(Math.log10(number)));

        if (digits == 0)
        {
            return number;
        }

        final long digits_left = digits / 2;

        for (long i = digits; i > digits_left; --i)
        {

            final long substraction_amount_base = (long)Math.pow(10, i);
            if (substraction_amount_base > number)
            {
                continue;
            }
            final long quotient = number / substraction_amount_base;
            number = number - (quotient * substraction_amount_base);
        }

        return number;
    }

    public static long get_first_half_of_number(long number)
    {
        final long digits = (long)(Math.floor(Math.log10(number)));

        if (digits == 0)
        {
            return number;
        }

        final boolean is_odd = (digits + 1) % 2 == 1;

        final long leave_in_extra_digit = (is_odd) ? 1 : 0;

        final long digits_to_remove = (digits / 2) - leave_in_extra_digit;
        for (long i = 0; i <= digits_to_remove; ++i)
        {
            number = number / 10;
        }

        return number;
    }

    public static void test_things()
    {
        long number = 69;
        System.out.println("here is our number = " + number);
        System.out.println("here is the second half = " + day2.get_second_half_of_number(number));
        number = 123;
        System.out.println("here is our number = " + number);
        System.out.println("here is the second half = " + get_second_half_of_number(number));

        number = 101;
        System.out.println("here is our number = " + number);
        System.out.println("here is the second half = " + get_second_half_of_number(number));

        number = 1234;
        System.out.println("here is our number = " + number);
        System.out.println("here is the second half = " + get_second_half_of_number(number));

        number = 4321;
        System.out.println("here is our number = " + number);
        System.out.println("here is the second half = " + get_second_half_of_number(number));

        System.out.println();
        number = 69;
        System.out.println("here is our number = " + number);
        System.out.println("here is the first half = " + get_first_half_of_number(number));

        number = 123;
        System.out.println("here is our number = " + number);
        System.out.println("here is the first half = " + get_first_half_of_number(number));

        number = 1234;
        System.out.println("here is our number = " + number);
        System.out.println("here is the first half = " + get_first_half_of_number(number));

        number = 4321;
        System.out.println("here is our number = " + number);
        System.out.println("here is the first half = " + get_first_half_of_number(number));

        number = 101;
        System.out.println("here is our number = " + number);
        System.out.println("here is the first half = " + get_first_half_of_number(number));
    }
}
