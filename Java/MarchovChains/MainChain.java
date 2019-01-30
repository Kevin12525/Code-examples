
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Random;

public class MainChain {
    static int[][] A = new int[28][28];
    static Random rand = new Random();
    public static void main(String[] agrs) {
        File file = new File("C:\\Users\\theke\\Documents\\NetBeansProjects\\MarchovChainNames\\src\\BoysNames.text");
        Scanner scan;
        String name;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            try {
                Formatter f = new Formatter("C:\\Users\\theke\\Documents\\NetBeansProjects\\MarchovChainNames\\src\\BoysNames.text");
                f.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", "Jackson", "Liam", "Noah", "Mason", "Ethan", "Jacob", "Luke", "James", "Connor", "Ryan","John","Robert","Steven","Will","David","Dave");
                f.close();
                scan = new Scanner(file);
            } catch (FileNotFoundException er) {
                System.out.print("ERROR:Folder Not Found");
                scan = null;
            }
        }
        while (scan.hasNextLine()) {
            name = scan.nextLine();
            name=name.toLowerCase();
            addCharacterPair(26,name.charAt(0));
            for(int x=0;x<name.length()-1;x++){
                addCharacterPair(name.charAt(x),name.charAt(x+1));
            }
            addCharacterPair(name.charAt(name.length()-1),27);
        }
        name=GenerateName();
        System.out.println(name.length());
        while(name.length()<4||name.length()>10){
            name=GenerateName();
            System.out.println(name.length());
        }
    }

    
    public static String GenerateName(){
        String finalName="";
        int currentLetterInt=26;
        while(currentLetterInt!=27){
            int total=0;
            for(int x=0;x<28;x++){
                total+=A[currentLetterInt][x];
            }
            int[] choice=new int[total];
            int counter=0;
            for(int x=0;x<28;x++){
                for(int y=0;y<A[currentLetterInt][x];y++){
                    choice[counter]=x;
                    counter++;
                    //System.out.print(convertInt(x));
                }
            }
            currentLetterInt=choice[rand.nextInt(total)];
            //System.out.println(currentLetterInt);
            finalName=finalName+convertInt(currentLetterInt);
            System.out.println(finalName);
        }
        return finalName;
    } 
    
    public static char convertInt(int value){
        switch(value){
            case 27: return '\n';
            default: return (char)(value+'a');
        }
    }
    public static void addCharacterPair(char first, char second) {
        A[first-'a'][second-'a']++;
    }
    public static void addCharacterPair(int first, char second) {
        A[first][second-'a']++;
    }
    public static void addCharacterPair(char first, int second) {
        A[first-'a'][second]++;
    }
}
