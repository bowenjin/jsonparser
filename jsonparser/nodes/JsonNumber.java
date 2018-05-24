package jsonparser.nodes;
public class JsonNumber extends JsonElement{
  Double number;
  public JsonNumber(double number){
    this.number = number;
  }
  public Object getObject(){
    return number;
  }

  public Double getDouble(){
    return number;
  }
}

