package dayz;

import java.util.ArrayList;
import java.util.List;

public final class day6
{

    static final String file_path = "day_6_test_part_1.txt";

    public static long part_1()
    {
        long result = 0;

        final List<String> lines = Utility.Util.read_every_line(file_path);
        var all_numbers = new ArrayList<ArrayList<Long>>();
        int last_current_index = 0;

        for (int i = 0; i < lines.size(); i++)
        {
            final String current_line = lines.get(i);
            int current_index = current_line.indexOf(' ', 0);
            var numbers_of_current_line = new ArrayList<Long>();
            while (current_index != -1)
            {
                numbers_of_current_line.add(Long.parseLong(current_line, last_current_index, current_index, 10));
                last_current_index = current_index + 1;
                current_index = current_line.indexOf(' ', current_index + 1);
            }
            System.out.println(numbers_of_current_line);
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
}
