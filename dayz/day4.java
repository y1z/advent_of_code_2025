package dayz;

import Utility.Util;

final record InputData(String input, int width, int height) { }

// https://adventofcode.com/2025/day/4
public final class day4
{
    final static char roll = '@';
    final static char empty = '.';

    public static long part_1()
    {
        long result = 0;
        String raw_input = Util.read_entire_file("day_4_part_1.txt");

        final int width = raw_input.indexOf('\n', 0) - 1;
        final int new_line_count = Util.count_instances_of(raw_input, '\n') + 1;
        final int height = new_line_count;

        final String clean_input = raw_input.replaceAll("[\\n\\r]|", "");

        InputData input_data = new InputData(clean_input, width, height);

        byte[] adjecencies_count_array = new byte[clean_input.length()];

        for (int i = 0; i < adjecencies_count_array.length; ++i)
        {
            adjecencies_count_array[i] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                final int current_index = (input_data.width() * y) + x;

                if (input_data.input().charAt(current_index) == roll)
                {
                    adjecencies_count_array[current_index] = (byte)adjacencies_count(input_data, x, y);
                }

                sb.append(adjecencies_count_array[current_index]);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());

        for (int i = 0; i < adjecencies_count_array.length; ++i)
        {
            if ((adjecencies_count_array[i] < 4) && (input_data.input().charAt(i) == roll))
            {
                result = result + 1;
            }
        }
        System.out.println(result);
        return result;
    }

    public static int adjacencies_count(InputData input_data, int x_pos, int y_pos)
    {
        assert (x_pos >= 0);
        assert (y_pos >= 0);
        int result = 0;
        final CharSequence cs = input_data.input();
        final int main_node_index = (input_data.width() * y_pos) + x_pos;

        if (input_data.height() <= y_pos)
        {
            System.err.println("outside of range [HEIGHT]");
            return -1;
        }

        if (input_data.width() <= x_pos)
        {
            System.err.println("outside of range [WIDTH]");
            return -1;
        }

        if (cs.length() <= main_node_index)
        {
            return -1;
        }

        // check the adjencencies in a area
        int relative_x_pos = -1;
        int relative_y_pos = (main_node_index) - (input_data.width());
        for (int i = 0; i < 9; ++i)
        {
            final int current_index = relative_y_pos + relative_x_pos;

            final boolean is_inside_x_boundy = (x_pos + relative_x_pos > -1) && (x_pos + relative_x_pos < input_data.width());

            final boolean is_inside_bounds = (current_index > -1) && (current_index < cs.length());

            if (is_inside_bounds && is_inside_x_boundy && (current_index != main_node_index))
            {
                final char found_char = cs.charAt(current_index);
                result = found_char == roll ? (result + 1) : result;
            }

            relative_x_pos++;
            if (relative_x_pos > 1)
            {
                relative_x_pos = -1;
                relative_y_pos = relative_y_pos + input_data.width();
            }
        }

        return result;
    }
}
