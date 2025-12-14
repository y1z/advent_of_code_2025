package dayz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Gatherer.Integrator;

import Utility.NumberRange;
import Utility.NumberRangeComparator;

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

        State state = State.READING_RANGES;

        StringBuilder sb = new StringBuilder();

        /// parsing the file
        try (BufferedReader reader = new BufferedReader(new FileReader(path) )) 
        {
            while (state == State.READING_RANGES) 
            {
                    final String[] string_ranges = reader.readLine().split("[-]");
                    if (string_ranges.length < 2)
                    {
                        state = State.READING_EVERYTHING_ELSE;
                        break;
                    }
                    else{
                        number_ranges.add(new NumberRange(Long.parseLong(string_ranges[0]) ,Long.parseLong(string_ranges[1]) ));
                    }
            }

            do {
                    final String temp = reader.readLine();
                    if(temp == null)
                    {
                        state = State.FINISHED;
                    }
                    else{
                        sb.append(temp + "\n");
                    }
            }while(state == State.READING_EVERYTHING_ELSE);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }


        final String number_to_check_string = (sb.toString());
        final String[] individual_numbers =  number_to_check_string.split("[\\n]+");
        for (int i = 0; i < individual_numbers.length; i++) 
        {
            number_to_check.add(Long.parseLong(individual_numbers[i]));
        }

        number_ranges.sort(NumberRangeComparator.comparator);

        for (int i = 0; i < number_to_check.size(); i++) 
        {
            if(is_in_range_linear_search(number_ranges, number_to_check.get(i)))
            {
                result++;
            }
            
        }

        System.out.printf("Result = %d\n", result);

        return result;
    }

    public static long part_2(){
        long result = 0;
        ArrayList<NumberRange> number_ranges = new ArrayList<>();

        final String path = "day_5_test_part_1.txt";

        State state = State.READING_RANGES;

        StringBuilder sb = new StringBuilder();

        /// parsing the file
        try (BufferedReader reader = new BufferedReader(new FileReader(path) )) 
        {
            while (state == State.READING_RANGES) 
            {
                    final String[] string_ranges = reader.readLine().split("[-]");
                    if (string_ranges.length < 2)
                    {
                        state = State.READING_EVERYTHING_ELSE;
                        break;
                    }
                    else{
                        number_ranges.add(new NumberRange(Long.parseLong(string_ranges[0]) ,Long.parseLong(string_ranges[1]) ));
                    }
            }

            do {
                    final String temp = reader.readLine();
                    if(temp == null)
                    {
                        state = State.FINISHED;
                    }
                    else{
                        sb.append(temp + "\n");
                    }
            }while(state == State.READING_EVERYTHING_ELSE);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }

        number_ranges.sort(NumberRangeComparator.comparator);
        for (int i = 0; i < number_ranges.size(); i++) {
            final NumberRange nr = number_ranges.get(i);
            for(long j = nr.lower; j < nr.upper; ++j)
            {
                result++;
            }
        }


        System.out.printf("Result = %d\n", result);
        return result;
    }

    public static boolean is_in_range_binary_search(ArrayList<NumberRange> numbers_range,long number_to_check)
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
            if(number.is_in_range_inclusive(number_to_check)){
                return true;
            }
            else
            {
                //if ((right_most_index - left_most_index) <= 1 ){
                 //   return numbers_range.get(right_most_index).is_in_range_inclusive(number_to_check) || numbers_range.get(left_most_index).is_in_range_inclusive(number_to_check);
                //}

                if(number.is_closer_to_lower(number_to_check))
                {
                    right_most_index = mid_index - 1;
                }
                else{
                    left_most_index = mid_index + 1;
                }

            }

            if(last_mid_index == mid_index){
                break;
            }
            last_mid_index = mid_index;
        }


        return false;
    }

    public static boolean is_in_range_linear_search(ArrayList<NumberRange> numbers_range,long number_to_check){

        for (int i = 0; i < numbers_range.size(); i++) {

            if(numbers_range.get(i).is_in_range_inclusive(number_to_check)){
                return true;
            }
            
        }

        return false;

    }
    
}
