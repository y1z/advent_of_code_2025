package dayz;

import java.util.ArrayList;
import java.util.List;

import Utility.Util;
import Utility.MutPair;

/// https://adventofcode.com/2025/day/7
public final class day7
{
    public static long part_1()
    {
        long result = 0;
        final String file_path = "day_7_part_1.txt";
        List<String> lines = Util.read_every_line(file_path);

        final int starting_index = Util.find_next_character(lines.get(0), 0, 'S');
        assert (starting_index > -1);
        var ray_indexes = new ArrayList<Integer>();
        var current_line_splitter_indexes = new ArrayList<MutPair<Integer, Boolean>>();

        // init ray_indexes
        {
            final String first_line = lines.get(0);
            for (int i = 0; i < first_line.length(); i++)
            {
                if (starting_index != i)
                {
                    ray_indexes.add(0);
                }
                else
                {
                    ray_indexes.add(1);
                }
            }
        }

        for (int i = 0; i < lines.size(); i++)
        {
            final String current_line = lines.get(i);
            int splitter_index = Util.find_next_character(current_line, 0, '^');
            while (splitter_index > -1)
            {
                current_line_splitter_indexes.add(new MutPair<Integer, Boolean>(splitter_index, false));
                splitter_index = Util.find_next_character(current_line, splitter_index + 1, '^');
            }

            // find intersections

            for (int j = 0; j < current_line_splitter_indexes.size(); j++)
            {
                var current_splitter = current_line_splitter_indexes.get(j);
                if (ray_indexes.get(current_splitter.first) == 1)
                {
                    current_splitter.second = true;
                    result += 1;
                }
            }

            create_splits(ray_indexes, current_line_splitter_indexes);

            current_line_splitter_indexes.clear();
        }

        System.out.printf("result = %d\n", result);
        return result;
    }

    public static void create_splits(ArrayList<Integer> ray_indexes, ArrayList<MutPair<Integer, Boolean>> splits)
    {

        for (int i = 0; i < splits.size(); i++)
        {
            final MutPair<Integer, Boolean> current_pair = splits.get(i);

            if (current_pair.second)
            {
                ray_indexes.set(current_pair.first, 0);
                final int to_the_right_of_current_index = current_pair.first + 1;
                if (!(to_the_right_of_current_index > ray_indexes.size() - 1))
                {
                    ray_indexes.set(to_the_right_of_current_index, 1);
                }

                final int to_the_left_of_current_index = current_pair.first - 1;
                if (!(to_the_left_of_current_index < 0))
                {
                    ray_indexes.set(to_the_left_of_current_index, 1);
                }
            }
        }
    }
}
