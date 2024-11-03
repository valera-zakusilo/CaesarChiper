import java.io.*;

public class FileService {

    public FileService(){

    }

    public String readFile(String path){
        String message = "";
        try (FileReader input = new FileReader(path);
        BufferedReader reader = new BufferedReader(input)) {
            while (reader.ready()){
                message += reader.readLine() + "\n";
            }
        } catch (IOException e){
            System.out.println("File not found!");
        }
        return message;
    }

    public void writeFile(String path, String message, Action action){
        File file = new File(outputFilePath(path, action));
        try (FileWriter output = new FileWriter(file.getPath())){
            output.write(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String outputFilePath(String path, Action action){
        String output = "";
        for (String sym : path.split("")){
            if (sym.equals(".")){
                output += "[" + action.toString() + "ED]";
            }
            output += sym;
        }
        return output;
    }
}
