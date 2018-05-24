package jsonparser.nodes;
public class JsonString extends JsonElement{
  String string;
  public JsonString(String string){
    this.string = string;
  }
  public Object getObject(){
    return string;
  }
  @Override
  public String toString(int spaces){
    return createSpaces(spaces) + '"' + string + '"';
  }
}

