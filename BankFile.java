package BankApp;

import java.io.*;

public class BankFile extends Writer {
    public BankFile(String outputPath) {

    }

    public static void main(String[] args) {
        String letters = "nwa.txt";

        try {
            FileWriter fileWriter = new FileWriter(letters);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("How are you?");
            bufferedWriter.newLine();
            bufferedWriter.write("How is your family");
            bufferedWriter.close();

            FileReader fileReader = new FileReader(letters);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                System.out.println(line);
                bufferedReader.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void write(char[] chars, int i, int i1) throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
