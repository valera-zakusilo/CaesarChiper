import java.util.Scanner;

public class CLI {
    private final String WELCOME_MESSAGE = "Вітаємо в програмі шифрування повідомлень Caesar!";
    private final String MENU_CHOICE_MESSAGE = "Виберіть один із наступних пунктів меню:";
    private final String MENU_MESSAGE_ENCRYPT = "1. Зашифрувати повідомлення";
    private final String MENU_MESSAGE_DECRYPT = "2. Розшифрувати повідомлення";
    private final String MENU_MESSAGE_BRUTEFORCE = "3. Декодувати повідомлення";
    private final String MENU_MESSAGE_EXIT = "0. Завершити програму";
    private final String INPUT_FILE_NAME_MESSAGE = "Введіть місцерозташування файлу: ";
    private final String INPUT_DICTIONARY_NAME_MESSAGE = "Введіть місцерозташування словника: ";
    private final String INPUT_KEY_MESSAGE = "Введіть ключ шифрування: ";
    private final String UNKNOWN_INPUT_MESSAGE = "Не відома команда. Спробуйте ще раз.";

    private Caesar Caesar;
    private final Scanner scanner = new Scanner(System.in);

    public CLI(){
        boolean isExit = false;
        System.out.println(WELCOME_MESSAGE);
        while (!isExit) {
            printMenu();
            String userInput = scanner.nextLine();
            switch (userInput){
                case "1" -> encryptFileMenu();
                case "2" -> decryptFileMenu();
                case "3" -> bruteforceFileMenu();
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

    public void encryptFileMenu(){
        System.out.print(INPUT_FILE_NAME_MESSAGE);
        String filePath = scanner.nextLine();
        if (Checker.isFileExist(filePath)) {
            System.out.print(INPUT_KEY_MESSAGE);
            String keyString = scanner.nextLine();
            if (Checker.isNumber(keyString, Action.ENCRYPT)) {
                int key = Integer.parseInt(keyString);
                Caesar = new Caesar(Action.ENCRYPT, filePath, key);
            }
        }
    }

    public void decryptFileMenu(){
        System.out.print(INPUT_FILE_NAME_MESSAGE);
        String filePath = scanner.nextLine();
        if (Checker.isFileExist(filePath)) {
            System.out.print(INPUT_KEY_MESSAGE);
            String keyString = scanner.nextLine();
            if (Checker.isNumber(keyString, Action.DECRYPT)) {
                int key = Integer.parseInt(keyString);
                Caesar = new Caesar(Action.DECRYPT, filePath, key);
            }
        }
    }

    public void bruteforceFileMenu(){
        System.out.print(INPUT_FILE_NAME_MESSAGE);
        String filePath = scanner.nextLine();
        if (Checker.isFileExist(filePath)){
            System.out.println(INPUT_DICTIONARY_NAME_MESSAGE);
            String dictionaryPath = scanner.nextLine();
            if (Checker.isFileExist(dictionaryPath)){
                Caesar = new Caesar(Action.BRUTE_FORCE, filePath, dictionaryPath);
            }
        }
    }


}
