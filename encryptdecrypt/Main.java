package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String operation = "enc";
        String s = "";
        int key = 0;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    operation = args[i + 1];
                    i++;
                    break;
                case "-data":
                    s = args[i + 1];
                    i++;
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    i++;
                    break;
            }
        }
        switch (operation) {
            case "enc":
                for (int i = 0; i < s.length(); i++) {
                    char c = (char) (s.charAt(i) + key);
                    System.out.print(c);
                }
                break;
            case "dec":
                for (int i = 0; i < s.length(); i++) {
                    char c = (char) (s.charAt(i) - key);
                    System.out.print(c);
                }
                break;
        }
    }
}
