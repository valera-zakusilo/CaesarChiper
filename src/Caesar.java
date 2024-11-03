import java.util.List;

public class Caesar {
    private final List<String> ENG_ALPHABET_CAPITALS = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private final List<String> ENG_ALPHABET_SMALL = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
    private final List<String> UA_ALPHABET_CAPITALS = List.of("А", "Б", "В", "Г", "Ґ", "Д", "Е", "Є", "Ж", "З", "И", "І", "Ї", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ь", "Ю", "Я");
    private final List<String> UA_ALPHABET_SMALL = List.of("а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ь", "ю", "я");
    private final List<String> SYMBOLS_ALPHABET = List.of(".", ",", "«", "»", "\"", "\'", ":", "!", "?", " ");

    private final FileService FILE_SERVICE = new FileService();

    public Caesar(Action action, String path, int key){
        if (action.equals(Action.ENCRYPT)){
            String text = FILE_SERVICE.readFile(path);
            FILE_SERVICE.writeFile(path, encrypt(text,key), action);
        } else if (action.equals(Action.DECRYPT)){
            String text = FILE_SERVICE.readFile(path);
            FILE_SERVICE.writeFile(path, decrypt(text,key), action);
        }
    }

    public String encrypt(String input, int key){
        String encryptedText = "";
        int letterIndex = 0;
        for (String symbol:input.split("")){
            List<String> symbolOfAlphabet = getAlphabet(symbol);
            letterIndex = (symbolOfAlphabet.indexOf(symbol) + key) % symbolOfAlphabet.size();
            encryptedText += symbolOfAlphabet.get(letterIndex);
        }
        return encryptedText;
    }


    public String decrypt(String input, int key){
        String decryptedText = "";
        int letterIndex = 0;
        for (String symbol:input.split("")){
            List<String> symbolOfAlphabet = getAlphabet(symbol);
            letterIndex = (symbolOfAlphabet.indexOf(symbol) - key) % symbolOfAlphabet.size();
            if (letterIndex < 0)
                letterIndex += symbolOfAlphabet.size();
            decryptedText += symbolOfAlphabet.get(letterIndex);
        }
        return decryptedText;
    }

    private List<String> getAlphabet(String symbol){
        if (ENG_ALPHABET_CAPITALS.contains(symbol))
            return ENG_ALPHABET_CAPITALS;
        else if (ENG_ALPHABET_SMALL.contains(symbol))
            return ENG_ALPHABET_SMALL;
        else if (SYMBOLS_ALPHABET.contains(symbol))
            return SYMBOLS_ALPHABET;
        return List.of(symbol);
    }


}
