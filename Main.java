import jsonparser.Parser;
import jsonparser.nodes.JsonElement;
import java.io.File;
class Main{
  static boolean file = false;
  public static void main(String [] args) throws java.io.IOException{
    if(args.length < 1){
      System.out.println("Please enter a file name");
      System.exit(1);
    }
    if(args.length > 1){
      System.out.println("Too many arguments");
      System.exit(1);
    }
    Parser parser = new Parser();
    JsonElement root = parser.parse(new File(args[0]));
    System.out.println(root.toString(0)); 
  }
}
