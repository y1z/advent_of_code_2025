package Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.List;

public final class Util 
{

    /**
     * @param path the path to the given file
     * @return the entire content of a file as a string
     */
    public static String read_entire_file(String path){
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path) )) {
            result.append(reader.readAllAsString());
        } catch (Exception e) {
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
        List<String> result = null ;
            
        try (BufferedReader reader = new BufferedReader(new FileReader(path) )) {
           result = reader.readAllLines();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static CharBuffer convert_to_char_buffer(String s, int start_index){
        return CharBuffer.wrap(s, start_index, s.length() - start_index);
    }
}
