public class Runner {
    public static void main(String[] args) {
        if (args.length == 0){
            CLI cli = new CLI();
        } else if (args.length == 3){
            Action action = Checker.checkAction(args[0]);
            if (Checker.isFileExist(args[1])){
                String textPath = args[1];
                if (Checker.isNumber(args[2])) {
                    int key = Integer.parseInt(args[2]);
                    Caesar caesar = new Caesar(action, textPath, key);
                } else {
                    String dictionaryPath = args[2];
                    if (Checker.isFileExist(dictionaryPath)) {
                        Caesar caesar = new Caesar(action, textPath, dictionaryPath);
                    }
                }
            }
        }
    }
}
