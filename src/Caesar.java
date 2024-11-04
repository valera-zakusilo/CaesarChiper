import java.util.*;

public class Caesar {
    private final List<String> ENG_ALPHABET = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
    private final List<String> UA_ALPHABET = List.of("А", "Б", "В", "Г", "Ґ", "Д", "Е", "Є", "Ж", "З", "И", "І", "Ї", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ь", "Ю", "Я", "а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ь", "ю", "я");
    private final List<String> SYMBOLS_ALPHABET = List.of(".", ",", "«", "»", "\"", "\'", ":", "!", "?", " ");

    public Caesar(Action action, String textPath, int key){
        String text = "";
        if (action.equals(Action.ENCRYPT)){
            text = FileService.readFile(textPath);
            if (!text.equals(""))
                FileService.writeFile(textPath, encrypt(text,key), action);
        } else if (action.equals(Action.DECRYPT)){
            text = FileService.readFile(textPath);
            if (!text.equals(""))
                FileService.writeFile(textPath, decrypt(text,key), action);
        }
    }

    public Caesar(Action action, String textPath, String wordsPath){
        if (action.equals(Action.BRUTE_FORCE)){
            String text = FileService.readFile(textPath);
            String[] dictionary = FileService.readFile(wordsPath).split("\n");
            FileService.writeFile(textPath, bruteForce(text, dictionary), action);
        }
    }

    public String encrypt(String text, int key){
        StringBuilder encryptedText = new StringBuilder();
        int letterIndex = 0;
        for (String symbol:text.split("")){
            List<String> symbolOfAlphabet = getAlphabet(symbol);
            letterIndex = (symbolOfAlphabet.indexOf(symbol) + key) % symbolOfAlphabet.size();
            encryptedText.append(symbolOfAlphabet.get(letterIndex));
        }
        return encryptedText.toString();
    }

    public String decrypt(String text, int key){
        StringBuilder decryptedText = new StringBuilder();
        int letterIndex = 0;
        for (String symbol:text.split("")){
            List<String> symbolOfAlphabet = getAlphabet(symbol);
            letterIndex = (symbolOfAlphabet.indexOf(symbol) - key) % symbolOfAlphabet.size();
            if (letterIndex < 0)
                letterIndex += symbolOfAlphabet.size();
            decryptedText.append(symbolOfAlphabet.get(letterIndex));
        }
        return decryptedText.toString();
    }

    public String bruteForce(String text, String[] dictionary){
        Map<Integer,String> possibleText = new HashMap<>();
        for (int i = 0; i < getAlphabet("A").size(); i++){
            String decryptedText = decrypt(text,i);
            for (String word : dictionary){
                if (decryptedText.toLowerCase().contains(" " + word + " "))
                    possibleText.put(i,decryptedText);
            }
        }
        StringBuilder output = new StringBuilder();
        for (Integer i : possibleText.keySet()){
            output.append("Ключ шифрування = " + i + "\nДешифрований текст: " + possibleText.get(i) + "\n");
        }
        return output.toString();
    }

    private List<String> getAlphabet(String symbol){
        List<String> alhabet = new ArrayList<>();
        alhabet.addAll(ENG_ALPHABET);
        alhabet.addAll(UA_ALPHABET);
        alhabet.addAll(SYMBOLS_ALPHABET);
        if (alhabet.contains(symbol))
            return alhabet;
        return List.of(symbol);
    }

}
