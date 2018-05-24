package jsonparser.nodes;
import java.util.LinkedHashMap;
public abstract class JsonElement{
  public String toString(int spaces){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < spaces; i++){
      sb.append(' ');
    }
    sb.append(getObject().toString());
    return sb.toString();
  }
  abstract public Object getObject(); 
  static String createSpaces(int n){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < n; i++){
      sb.append(' ');
    }
    return sb.toString(); 
  }
}

