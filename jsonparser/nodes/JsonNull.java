package jsonparser.nodes;
public class JsonNull extends JsonElement{
  public Object getObject(){
    return null;
  }
  @Override
  public String toString(int spaces){
    return createSpaces(spaces) + "null";
  }
}


