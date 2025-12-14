package Utility;

import Utility.NumberRange;
import java.util.Comparator;

public final class NumberRangeComparator implements Comparator<NumberRange>
{
    public static NumberRangeComparator comparator = new NumberRangeComparator();

    @Override
    public int compare(NumberRange left_number_range, NumberRange right_number_range) {

        final int lower_compare = Long.compare(left_number_range.lower, right_number_range.lower);
        if ( lower_compare == 0 )
        {
            return Long.compare(left_number_range.upper, right_number_range.upper);
        }
        return lower_compare;
    }

}