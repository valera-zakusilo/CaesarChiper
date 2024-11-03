public class Runner {
    public static void main(String[] args) {
        if (args.length == 0){
            CLI cli = new CLI();
        } else {
            Action action = null;
            if (args[0].equals(Action.ENCRYPT.name()))
                action = Action.ENCRYPT;
            else if (args[0].equals(Action.DECRYPT.name())) {
                action = Action.DECRYPT;
            }
            String path = args[1];
            int key = Integer.parseInt(args[2]);
            Caesar caesar = new Caesar(action,path,key);
        }
    }
}
