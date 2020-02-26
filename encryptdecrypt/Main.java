package encryptdecrypt;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean readFile = false;
        File filew = null;
        String operation = "enc";
        StringBuilder s = new StringBuilder();
        int key = 0;
        String alg = "shift";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-alg":
                    if (!args[i + 1].startsWith("-")) {
                        alg = args[i + 1];
                        i++;
                    } else {
                        errorMsg(args[i]);
                    }
                    break;
                case "-mode":
                    if (!args[i + 1].startsWith("-")) {
                        operation = args[i + 1];
                        i++;
                    } else {
                        errorMsg(args[i]);
                    }
                    break;
                case "-data":
                    if (!args[i + 1].startsWith("-")) {
                        s = new StringBuilder(args[i + 1]);
                        i++;
                    } else {
                        errorMsg(args[i]);
                    }
                    break;
                case "-key":
                    if (!args[i + 1].startsWith("-")) {
                        key = Integer.parseInt(args[i + 1]) % 26 ;
                        i++;
                    } else {
                        errorMsg(args[i]);
                    }
                    break;
                case "-in":
                    if (!args[i + 1].startsWith("-")) {
                        try {
                            File file = new File(args[i + 1]);
                            Scanner sc = new Scanner(file);
                            i++;
                            while (sc.hasNext()) {
                                s.append(sc.nextLine());
                            }
                        } catch (FileNotFoundException e) {
                            errorMsg("File -in");
                        }
                    }
                    break;
                case "-out":
                    readFile = true;
                    if (!args[i + 1].startsWith("-")) {
                        filew = new File(args[i + 1]);
                    }
                    break;
            }
        }
        if (readFile) {
            try {
                filew.createNewFile();
                FileWriter writer = new FileWriter(filew);
                if (alg.equals("unicode")) {
                    switch (operation) {
                        case "enc":
                            for (int j = 0; j < s.length(); j++) {
                                char c = (char) (s.charAt(j) + key);
                                writer.append(c);
                            }
                            break;
                        case "dec":
                            for (int j = 0; j < s.length(); j++) {
                                char c = (char) (s.charAt(j) - key);
                                writer.append(c);
                            }
                            break;
                    }
                } else if (alg.equals("shift")) {
                    switch (operation) {
                        case "enc":
                            for (int i = 0; i < s.length(); i++) {
                                char c = s.charAt(i);
                                if (c >= 'a' && c <= 'z') {
                                    c = (char) ((((c - 'a') + key) % 26) + 'a');
                                } else if (c >= 'A' && c <= 'Z') {
                                    c = (char) ((((c - 'A') + key) % 26) + 'A');
                                }
                                writer.append(c);
                            }
                            break;
                        case "dec":
                            for (int i = 0; i < s.length(); i++) {
                                char c = s.charAt(i);
                                if (c >= 'a' && c <= 'z') {
                                    c = (char) ((((c - 'a') - key + 26) % 26) + 'a');
                                } else if (c >= 'A' && c <= 'Z') {
                                    c = (char) ((((c - 'A') - key + 26) % 26) + 'A');
                                }
                                writer.append(c);
                            }
                            break;
                    }
                }

                writer.close();
            } catch (IOException e) {
                errorMsg("File");
            }
        } else {
            switch (operation) {
                case "enc":
                    for (int j = 0; j < s.length(); j++) {
                        char c = (char) (s.charAt(j) + key);
                        System.out.print(c);
                    }
                    break;
                case "dec":
                    for (int j = 0; j < s.length(); j++) {
                        char c = (char) (s.charAt(j) - key);
                        System.out.print(c);
                    }
                    break;
            }
        }
    }

    public static void errorMsg(String str) {
        System.out.println("Error. " + str + " not found");
    }

}
