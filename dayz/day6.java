package dayz;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatWidthException;

import Utility.Util;

/// https://adventofcode.com/2025/day/6
public final class day6
{
    enum MathOperation
    {
        ADD,
        MUL,
    }

    static final String file_path = "day_6_part_1.txt";

    public static long part_1()
    {
        long result = 0;

        final List<String> lines = Utility.Util.read_every_line(file_path);
        var all_numbers = new ArrayList<ArrayList<Long>>();
        var all_operations = new ArrayList<MathOperation>();

        for (int i = 0; i < lines.size(); i++)
        {
            final String current_line = lines.get(i);

            if (does_line_have_numbers(current_line))
            {
                all_numbers.add(extract_numbers(current_line));
            }
            else if (does_line_have_math_operation(current_line))
            {
                all_operations = extract_math_operations(current_line);
            }
        }

        for (int i = 0; i < all_operations.size(); i++)
        {
            final MathOperation math_operation = all_operations.get(i);
            long colum_result = 0;
            if (MathOperation.MUL == math_operation)
            {
                colum_result = 1;
            }
            for (int j = 0; j < all_numbers.size(); j++)
            {
                final ArrayList<Long> current_line = all_numbers.get(j);
                switch (math_operation)
                {
                case MathOperation.ADD:
                    colum_result += current_line.get(i);
                    break;

                case MathOperation.MUL:
                    colum_result *= current_line.get(i);
                    break;
                }
            }
            result += colum_result;
        }

        System.out.printf("Result = %d\n", result);
        return result;
    }

    /**
     * @param cs
     * @param start_index
     * @return the index of the found index
     */
    public static int find_next_number(CharSequence cs, int start_index)
    {

        for (int i = start_index; i < cs.length(); i++)
        {
            if (Character.isDigit(cs.charAt(i)))
            {
                return i;
            }
        }

        return -1;
    }

    public static int find_next_character(CharSequence cs, int start_index, char character)
    {
        for (int i = start_index; i < cs.length(); i++)
        {
            if (cs.charAt(i) == character)
            {
                return i;
            }
        }

        return -1;
    }

    public static boolean does_line_have_numbers(CharSequence cs)
    {
        return find_next_number(cs, 0) != -1;
    }

    public static boolean does_line_have_math_operation(CharSequence cs)
    {
        return Util.find_next_characters(cs, 0, "+*") != -1;
    }

    public static ArrayList<Long> extract_numbers(CharSequence cs)
    {
        ArrayList<Long> result = new ArrayList<Long>();
        int current_index = find_next_number(cs, 0);
        while (current_index != -1)
        {
            final int number_start_index = current_index;
            final int space_start_index = find_next_character(cs, number_start_index, ' ');

            if (space_start_index == -1)
            {

                result.add(Long.parseLong(cs, number_start_index, cs.length(), 10));
                current_index = -1;
                continue;
            }

            result.add(Long.parseLong(cs, number_start_index, space_start_index, 10));

            current_index = find_next_number(cs, space_start_index);
        }

        return result;
    }

    public static ArrayList<MathOperation> extract_math_operations(CharSequence cs)
    {
        var result = new ArrayList<MathOperation>();
        int current_index = Util.find_next_characters(cs, 0, "+*");
        while (current_index != -1)
        {
            if (cs.charAt(current_index) == '+')
            {
                result.add(MathOperation.ADD);
            }
            else if (cs.charAt(current_index) == '*')
            {
                result.add(MathOperation.MUL);
            }
            current_index = Util.find_next_characters(cs, current_index + 1, "+*");
        }

        return result;
    }
}
