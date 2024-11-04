import javax.xml.transform.Source;
import java.io.File;

public class Checker {
    public static Checker instance;

    private Checker(){

    }

    public static Checker getInstance(){
        return instance;
    }

    public static boolean isFileExist(String path){
        if (new File(path).exists())
            return true;
        System.out.println("Файл: " + path + " не знайдено!");
        return false;
    }

    public static boolean isNumber(String s){
        int number = 0;
        try {
            number = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            System.out.println(s + " має бути числом!");
        }
        return false;
    }

    public static Action checkAction(String action){
        if (action.equals(Action.ENCRYPT.name()))
            return Action.ENCRYPT;
        else if (action.equals(Action.DECRYPT.name()))
            return Action.DECRYPT;
        else if (action.equals(Action.BRUTE_FORCE.name())){
            return Action.BRUTE_FORCE;
        }
        System.out.println("Невідома команда! Перелік команд: ENCRYPT, DECRYPT або BRUTE_FORCE!");
        return null;
    }
}
