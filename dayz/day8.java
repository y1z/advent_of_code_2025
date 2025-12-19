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

                System.out.println(numbers_as_string);
            
            }
        }

        return result;
    }
}
