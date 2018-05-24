package jsonparser.nodes;
import java.util.LinkedHashMap;
import java.util.Map;
public class JsonObject extends JsonElement implements JsonContainerElement{
  LinkedHashMap<String, JsonElement> map = new LinkedHashMap<String, JsonElement>();
  public Object getObject(){
    return map;
  }
  public void put(String key, JsonElement element){
    map.put(key, element);
  }

  public String deepToString(int spaces){
    StringBuilder sb = new StringBuilder();
    sb.append(createSpaces(spaces));
    sb.append("{\n");
    int count = 0;
    for(Map.Entry<String, JsonElement> entry: map.entrySet()){
      sb.append(createSpaces(spaces + 2));
      sb.append('"' + entry.getKey() + '"' + ": ");
      JsonElement element = entry.getValue();
      sb.append(element instanceof JsonContainerElement ? "\n" + element.toString(spaces + 4) : element.toString(0));
      sb.append(++count == map.entrySet().size() ? "\n" : ",\n");
    }
    sb.append(createSpaces(spaces));
    sb.append("}");
    return sb.toString();
  }
  @Override
  public String toString(int spaces){
    return deepToString(spaces);
  }
}

