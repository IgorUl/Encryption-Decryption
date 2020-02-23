package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String s = "we found a treasure!";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'm') c = (char) ('z' - c + 'a');
            else if  (c >= 'A' && c <= 'M') c = (char) ('Z' - c + 'A');
            else if  (c >= 'n' && c <= 'z') c = (char) ('a' - c + 'z');
            else if  (c >= 'N' && c <= 'Z') c = (char) ('a' - c + 'z');
            System.out.print(c);
        }
    }
}
