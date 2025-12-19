package Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.List;
import java.util.ArrayList;

public final class Util
{

    /**
     * @param path the path to the given file
     * @return the entire content of a file as a string
     */
    public static String read_entire_file(String path)
    {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            result.append(reader.readAllAsString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * @param path the path to the file
     * @return every string in the file separated by new lines
     */
    public static List<String> read_every_line(String path)
    {
        List<String> result = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            result = reader.readAllLines();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public static CharBuffer convert_to_char_buffer(String s, int start_index)
    {
        return CharBuffer.wrap(s, start_index, s.length() - start_index);
    }

    public static int indexOf(CharBuffer sequence, char character, int start_index)
    {
        int result = -1;
        assert (start_index < sequence.length());
        for (int i = start_index; i < sequence.length(); ++i)
        {
            if (sequence.get() == character)
            {
                result = i;
                break;
            }
        }
        return result;
    }

    public static int first_index_of(CharBuffer sequence, char character)
    {
        return indexOf(sequence, character, 0);
    }

    public static int count_instances_of(CharSequence cs, char character)
    {
        int result = 0;
        for (int i = 0; i < cs.length(); ++i)
        {
            if (cs.charAt(i) == character)
            {
                result += 1;
            }
        }

        return result;
    }

    /**
     *
     * @param cs
     * @param start_index were to start
     * @param character the character too look for
     * @return The index of the character if it exist, if the char does not it return -1
     */
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

    /**
     * @param cs the character sequence the method looks at
     * @param start_index were to start looking into cs
     * @param characters_to_look_for The series of characters to look for
     * @return The index of the first match for a character inside characters_to_look_for
     */
    public static int find_next_characters(CharSequence cs, int start_index, CharSequence characters_to_look_for)
    {
        for (int i = start_index; i < cs.length(); i++)
        {
            final char current_character = cs.charAt(i);
            for (int j = 0; j < characters_to_look_for.length(); ++j)
            {
                if (current_character == characters_to_look_for.charAt(j))
                {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 
     * @param cs the sequence to look at
     * @param delimiter what character to separate the strings by
     * @return the string separated by a delimiter PLUS the remainder of the string after the last delimiter.
     */
    public static ArrayList<String> delimiter_separated_string_inclusive(CharSequence cs, char delimiter)
    {
        return delimiter_separated_string(cs, delimiter, true);
    }

    /**
     * 
     * @param cs the sequence to look at
     * @param delimiter what character to separate the strings by
     * @return the string separated by a delimiter MINUS the remainder of the string after the last delimiter.
     */
    public static ArrayList<String> delimiter_separated_string_exclusive(CharSequence cs, char delimiter)
    {
        return delimiter_separated_string(cs, delimiter, false);
    }

    public static ArrayList<String> delimiter_separated_string(CharSequence cs, char delimiter, boolean is_inclusive)
    {
        ArrayList<String> result = new ArrayList<>();

        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < cs.length(); i++)
        {
            if (cs.charAt(i) == delimiter)
            {
                end_index = i;
                result.add(cs.subSequence(start_index, end_index).toString());
                start_index = end_index + 1;
            }
        }

        if (is_inclusive && start_index < cs.length() - 1)
        {
            result.add(cs.subSequence(start_index, cs.length()).toString());
        }

        return result;
    }
}
