package Reverse_the_word;

public class Revers_word {

    public static void main(String[] args) {
        String str = "I am an employee of hashedin";
        String reversedString = reverseWords(str);
        System.out.println(reversedString);
    }

    public static String reverseWords(String str) {
        // Split the string into words using space as the delimiter
        String[] words = str.split(" ");
        String revers="";
        // Reverse the order of words
        for (int i = words.length - 1; i >= 0; i--)
            revers += words[i]+" ";
        return revers;
    }

}
