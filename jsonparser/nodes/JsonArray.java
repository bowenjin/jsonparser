package jsonparser.nodes;
import java.util.ArrayList;
public class JsonArray extends JsonElement implements JsonContainerElement{
  ArrayList<JsonElement> elements = new ArrayList<JsonElement>();
  public Object getObject(){
    return elements;
  }
  public void addElement(JsonElement element){
    elements.add(element);
  }
  public String deepToString(int spaces){
    StringBuilder sb = new StringBuilder();
    sb.append(createSpaces(spaces));
    sb.append("[\n");
    int count = 0;
    for(JsonElement element: elements){
      sb.append(createSpaces(spaces + 2));
      sb.append(element instanceof JsonContainerElement ? "\n" + element.toString(spaces + 4) : element.toString(0));
      sb.append(++count == elements.size() ? "\n" : ",\n");
    }
    sb.append(createSpaces(spaces));
    sb.append("]\n"); 
    return sb.toString();   
  }
  @Override
  public String toString(int spaces){
    return deepToString(spaces);
  }
}

