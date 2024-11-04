import java.io.*;

public class FileService {
    public static FileService instance;

    private FileService(){

    }

    public static FileService getInstance(){
        return instance;
    }

    public static String readFile(String path){
        StringBuilder message = new StringBuilder();
        try (FileReader input = new FileReader(path);
        BufferedReader reader = new BufferedReader(input)) {
            while (reader.ready()){
                message.append(reader.readLine() + "\n");
            }
        } catch (IOException e){
            System.out.println("Файл не знайдено!");
        }
        return message.toString();
    }

    public static void writeFile(String path, String text, Action action){
        if (Checker.isFileExist(path)) {
            String outputPath = outputFilePath(path, action);
            File file = new File(outputPath);
            try (FileWriter output = new FileWriter(file.getPath())) {
                output.write(text);
            } catch (IOException e) {
                System.out.println("Файл не збережено!");
            }
        } else if (action.equals(Action.ENCRYPT))
            System.out.println("Цей файл вже зашифровано!");
        else if (action.equals(Action.DECRYPT))
            System.out.println("Цей файл вже розшифровано!");
    }

    private static String outputFilePath(String path, Action action){
        String output = "";
        String pathWithoutTag = path;
        System.out.println(path.replace("[" + Action.ENCRYPT + "ED]",""));
        if (path.contains("[" + Action.ENCRYPT + "ED]"))
            pathWithoutTag = path.replace("[" + Action.ENCRYPT + "ED]","");
        else if (path.contains("[" + Action.DECRYPT + "ED]"))
            pathWithoutTag = path.replace("[" + Action.DECRYPT + "ED]","");
        else if (path.contains("[" + Action.BRUTE_FORCE + "ED]"))
            pathWithoutTag = path.replace("[" + Action.BRUTE_FORCE + "ED]","");
        for (String sym : pathWithoutTag.split("")){
            if (sym.equals(".")){
                output += "[" + action.toString() + "ED]";
            }
            output += sym;
        }
        System.out.println(output);
        return output;
    }
}
