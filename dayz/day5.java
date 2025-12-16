package dayz;

import Utility.NumberRange;
import Utility.NumberRangeComparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public final class day5
{
    public enum State
    {
        READING_RANGES,
        READING_EVERYTHING_ELSE,
        FINISHED
    }

    public static long part_1()
    {
        long result = 0;

        ArrayList<NumberRange> number_ranges = new ArrayList<>();
        ArrayList<Long> number_to_check = new ArrayList<>();

        final String path = "day_5_part_1.txt";

        /// parsing the file
        parse_file(number_ranges, number_to_check, path);

        number_ranges.sort(NumberRangeComparator.comparator);
        remove_unnecessary_nums(number_ranges);

        for (int i = 0; i < number_to_check.size(); i++)
        {
            if (is_in_range_linear_search(number_ranges, number_to_check.get(i)))
            {
                result++;
            }
        }

        System.out.printf("Result = %d\n", result);

        return result;
    }

    public static long part_2()
    {
        long result = 0;
        ArrayList<NumberRange> number_ranges = new ArrayList<>();

        final String path = "day_5_test_part_1.txt";

        /// parsing the file
        parse_file(number_ranges, null, path);

        number_ranges.sort(NumberRangeComparator.comparator);

        {
            int index_of_biggest_delta = -1;
            long biggest_delta = Long.MIN_VALUE;
            // find the largest delta
            for (int i = 0; i < number_ranges.size(); i++)
            {
                final long current_delta = number_ranges.get(i).delta();
                if (current_delta > biggest_delta)
                {
                    biggest_delta = current_delta;
                    index_of_biggest_delta = i;
                }
            }
        }
        // swap it to first element

        System.out.printf("Result = %d\n", result);
        return result;
    }

    public static boolean
    is_in_range_binary_search(ArrayList<NumberRange> numbers_range,
                              long number_to_check)
    {
        System.out.println(number_to_check);
        int right_most_index = numbers_range.size() - 1;
        int left_most_index = 0;

        int last_mid_index = -99999999;

        // binary search
        while (left_most_index < right_most_index)
        {
            final int mid_index = left_most_index + (right_most_index - left_most_index) / 2;
            final NumberRange number = numbers_range.get(mid_index);
            if (number.is_in_range_inclusive(number_to_check))
            {
                return true;
            }
            else
            {
                // if ((right_most_index - left_most_index) <= 1 ){
                //    return
                //    numbers_range.get(right_most_index).is_in_range_inclusive(number_to_check)
                //    ||
                //    numbers_range.get(left_most_index).is_in_range_inclusive(number_to_check);
                //}

                if (number.is_closer_to_lower(number_to_check))
                {
                    right_most_index = mid_index - 1;
                }
                else
                {
                    left_most_index = mid_index + 1;
                }
            }

            if (last_mid_index == mid_index)
            {
                break;
            }
            last_mid_index = mid_index;
        }

        return false;
    }

    public static boolean
    is_in_range_linear_search(ArrayList<NumberRange> numbers_range,
                              long number_to_check)
    {

        for (int i = 0; i < numbers_range.size(); i++)
        {

            if (numbers_range.get(i).is_in_range_inclusive(number_to_check))
            {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param numbers_range
     * @param numbers_to_check Can be null be if is not null are the number needed
     *     for the problem of day5
     * @param file_path were the file to parse is found
     */
    public static void parse_file(ArrayList<NumberRange> numbers_range,
                                  ArrayList<Long> numbers_to_check,
                                  String file_path)
    {
        State state = State.READING_RANGES;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_path)))
        {
            while (state == State.READING_RANGES)
            {
                final String[] string_ranges = reader.readLine().split("[-]");
                if (string_ranges.length < 2)
                {
                    state = State.READING_EVERYTHING_ELSE;
                    break;
                }
                else
                {
                    numbers_range.add(new NumberRange(Long.parseLong(string_ranges[0]),
                                                      Long.parseLong(string_ranges[1])));
                }
            }

            do
            {
                final String temp = reader.readLine();
                if (temp == null)
                {
                    state = State.FINISHED;
                }
                else
                {
                    sb.append(temp + "\n");
                }
            } while (state == State.READING_EVERYTHING_ELSE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (numbers_to_check == null)
        {
            return;
        }

        final String number_to_check_string = (sb.toString());
        final String[] individual_numbers = number_to_check_string.split("[\\n]+");
        for (int i = 0; i < individual_numbers.length; i++)
        {
            numbers_to_check.add(Long.parseLong(individual_numbers[i]));
        }
    }

    public static void
    remove_unnecessary_nums(ArrayList<NumberRange> number_ranges)
    {
        int sorted_numbers_index = 0;
        while (sorted_numbers_index != -1)
        {
            long biggest_delta = -99999;
            int index_of_biggest_delta = 0;
            for (int i = sorted_numbers_index; i < number_ranges.size(); ++i)
            {
                final long current_delta = number_ranges.get(i).delta();
                if (current_delta > biggest_delta)
                {
                    biggest_delta = current_delta;
                    index_of_biggest_delta = i;
                }
            }

            // swap it to the start of the array ( aka were sorted_numbers_index is
            // pointing )
            if (index_of_biggest_delta != sorted_numbers_index)
            {
                final var current_biggest = number_ranges.get(index_of_biggest_delta);
                final var number_to_swap = number_ranges.get(sorted_numbers_index);
                final var temp = number_to_swap;
                number_ranges.set(sorted_numbers_index, current_biggest);
                number_ranges.set(index_of_biggest_delta, temp);
            }

            final var current_biggest = number_ranges.get(sorted_numbers_index);
            // remove all elements who fit inside the range
            for (int i = number_ranges.size() - 1; i > sorted_numbers_index + 1;
                 --i)
            {
                final NumberRange current_number = number_ranges.get(i);
                if (current_biggest.fit_perfectly_within_bound(current_number))
                {
                    System.out.printf("removed value = [%d, %d]\n", current_number.lower,
                                      current_number.upper);
                    current_biggest.print();
                    number_ranges.remove(i);
                }
            }
        }
    }
}
