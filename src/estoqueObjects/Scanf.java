package estoqueObjects;
import java.util.Scanner;

public class Scanf {
    /* Classe para facilitar a distribuição e pedido de informações. */
    public static Scanner scanner = new Scanner(System.in);

    public static void write(String txt){
        System.out.print(txt);
    }
    public static void writeln(String txt){
        System.out.println(txt);
    }
    public static String getText(){
        String scannedText = scanner.nextLine();
        return scannedText.trim();
    }
    public static Integer getInt(){
        return Integer.parseInt(getText());
    }
    public static Float getFloat(){
        return Float.parseFloat(getText());
    }
    public static Double getDouble(){
        return Double.parseDouble(getText());
    }
}
