package Utility;

public final class NumberRange {

    public NumberRange(long _lower, long _upper)
    {
        this.lower = Math.min(_lower, _upper);
        this.upper = Math.max(_lower, _upper);
    }

    public NumberRange()
    {
        this.lower = 0;
        this.upper = 0;
    }

    public void print()
    {
        System.out.printf("[lower = %d, upper = %d]\n", this.lower, this.upper);
    }

    public boolean is_in_range_inclusive(long input)
    {
        return (input >= lower) && (input <= upper);
    }

    public boolean is_in_range_exclusive(long input)
    {
        return (input > lower) && (input < upper);
    }

    public boolean fit_perfectly_within_bound(NumberRange nr)
    {
        return (this.lower >= nr.lower) && (this.upper <= nr.upper);
    }

    public boolean is_closer_to_upper(long input)
    {
        final long distance_from_upper = Math.abs((upper - input));
        final long distance_from_lower = Math.abs((lower - input));

        return distance_from_upper <= distance_from_lower;
    }

    public boolean is_closer_to_lower(long input)
    {
        return !is_closer_to_upper(input);
    }

    public long get_range_between_values() { return upper - lower; }

    public long delta() { return upper - lower; }

    public long lower = 0;
    public long upper = 0;
}
