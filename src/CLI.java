import java.util.Scanner;

public class CLI {
    private final String WELCOME_MESSAGE = "Вітаємо в програмі шифрування повідомлень Caesar!";
    private final String MENU_CHOICE_MESSAGE = "Виберіть один із наступних пунктів меню:";
    private final String MENU_MESSAGE_ENCRYPT = "1. Зашифрувати повідомлення";
    private final String MENU_MESSAGE_DECRYPT = "2. Розшифрувати повідомлення";
    private final String MENU_MESSAGE_BRUTEFORCE = "3. Декодувати повідомлення";
    private final String MENU_MESSAGE_EXIT = "0. Завершити програму";
    private final String INPUT_FILE_NAME_MESSAGE = "Введіть місцерозташування файлу: ";
    private final String INPUT_KEY_MESSAGE = "Введіть ключ шифрування: ";
    private final String UNKNOWN_INPUT_MESSAGE = "Не відома команда. Спробуйте ще раз.";

    private Caesar Caesar = null;
    private final FileService FILE_SERVICE = new FileService();

    public CLI(){
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        String userInput = "";
        System.out.println(WELCOME_MESSAGE);
        while (!isExit) {
            printMenu();
            userInput = scanner.nextLine();
            switch (userInput){
                case "1" -> encryptFileMenu(scanner);
                case "2" -> decryptFileMenu(scanner);
                case "3" -> bruteforceFileMenu(new Scanner(System.in));
                case "0" -> {
                    System.out.println("До наступної зустрічі!");
                    isExit = true;
                }
                default -> System.out.println(UNKNOWN_INPUT_MESSAGE);
            }
        }
    }

    public void printMenu(){
        System.out.println(MENU_CHOICE_MESSAGE);
        System.out.println(MENU_MESSAGE_ENCRYPT);
        System.out.println(MENU_MESSAGE_DECRYPT);
        System.out.println(MENU_MESSAGE_BRUTEFORCE);
        System.out.println(MENU_MESSAGE_EXIT);
    }

    public void encryptFileMenu(Scanner scanner){
        System.out.print(INPUT_FILE_NAME_MESSAGE);
        String filePath = scanner.nextLine();
        System.out.print(INPUT_KEY_MESSAGE);
        int key = Integer.parseInt(scanner.nextLine());
        Caesar = new Caesar(Action.ENCRYPT,filePath,key);
        System.out.println("Файл зашифровано!");
    }

    public void decryptFileMenu(Scanner scanner){
        System.out.print(INPUT_FILE_NAME_MESSAGE);
        String filePath = scanner.nextLine();
        System.out.print(INPUT_KEY_MESSAGE);
        int key = Integer.parseInt(scanner.nextLine());
        //String textFromFile = FILE_SERVICE.readFile(filePath);
        //String encryptedText = Caesar.decrypt(textFromFile,key);
        //FILE_SERVICE.writeFile(filePath,encryptedText,Action.DECRYPT);
        Caesar = new Caesar(Action.DECRYPT,filePath,key);
        System.out.println("Файл розшифровано!");
    }

    public void bruteforceFileMenu(Scanner scanner){
        System.out.print(INPUT_FILE_NAME_MESSAGE);
        String filePath = scanner.nextLine();
    }


}
