import java.util.List;

public class Caesar {
    private final List<String> ENG_ALPHABET_CAPITALS = List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private final List<String> ENG_ALPHABET_SMALL = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
    private final List<String> UA_ALPHABET_CAPITALS = List.of("А");
    private final List<String> UA_ALPHABET_SMALL = List.of("а");
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
        int index = 0;
        for (String symbol:input.split("")){
            List<String> symbolOfAlphabet = getAlphabet(symbol);
            index = (symbolOfAlphabet.indexOf(symbol) + key) % symbolOfAlphabet.size();
            encryptedText += symbolOfAlphabet.get(index);
        }
        return encryptedText;
    }


    public String decrypt(String input, int key){
        String decryptedText = "";
        int index = 0;
        for (String symbol:input.split("")){
            List<String> symbolOfAlphabet = getAlphabet(symbol);
            index = (symbolOfAlphabet.indexOf(symbol) - key) % symbolOfAlphabet.size();
            if (index < 0)
                index += symbolOfAlphabet.size();
            decryptedText += symbolOfAlphabet.get(index);
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
