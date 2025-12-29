package dayz;

import java.util.ArrayList;
import java.util.List;

import Utility.Util;
import Utility.Vector3;

final class LineData
{
    Vector3 pos;
    int id;
}

/// https://adventofcode.com/2025/day/8
public final class day8
{

    public static long part_1()
    {
        long result = 0;
        final String file_path = "day_8_test_part_1.txt";
        var data = new ArrayList<LineData>();
        {
            final List<String> lines = Utility.Util.read_every_line(file_path);
            for (int i = 0; i < lines.size(); ++i)
            {
                var new_data = new LineData();
                new_data.id = i;
                final String current_line = lines.get(i);
                var numbers_as_string = Util.delimiter_separated_string_inclusive(current_line, ',');
                var x_pos = Float.parseFloat(numbers_as_string.get(0));
                var y_pos = Float.parseFloat(numbers_as_string.get(1));
                var z_pos = Float.parseFloat(numbers_as_string.get(2));

                new_data.pos = Vector3.zero;

                new_data.pos.x = x_pos;
                new_data.pos.y = y_pos;
                new_data.pos.z = z_pos;
                data.add(new_data);
            }
        }

        int finding_closes_connection_index = 0;

        for (int i = 0; i < data.size(); ++i)
        {
            var current_data = data.get(i);
            int nearest_pos_index = 0;
            float current_lowest_distance = Float.MAX_VALUE;
            for (int j = 0; j < data.size(); ++j)
            {
                if (j == i)
                {
                    continue;
                }

                final float current_distance = current_data.pos.distance(data.get(j).pos);
                if (current_distance < current_lowest_distance)
                {
                    current_lowest_distance = current_distance;
                    nearest_pos_index = j;
                }
            }
        }

        return result;
    }
}
