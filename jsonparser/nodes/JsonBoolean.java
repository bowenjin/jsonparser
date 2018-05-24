package jsonparser.nodes;
public class JsonBoolean extends JsonElement{
  Boolean bool; 
  public JsonBoolean(boolean bool){
    this.bool = bool;
  }
  public Object getObject(){
    return bool;
  }
}

