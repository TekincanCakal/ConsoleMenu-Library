package ConsoleMenu;

import java.util.Scanner;

public class Main {
    private static ConsoleMenu consoleMenu;

    public static void main(String[] args) {
        consoleMenu = new ConsoleMenu(Main.class);
        try {
            consoleMenu.addMenu("Show Money", 0, Main.class.getMethod("ShowMoney"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        consoleMenu.show();
    }
    public void ShowMoney(){
        System.out.println("Money: 50");
        System.out.println("Enter anything for back to main menu");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        if(answer.length() > 0){
            consoleMenu.transferTo(-1);
        }
    }
}
