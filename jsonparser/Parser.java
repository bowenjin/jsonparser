package jsonparser;
import static jsonparser.Token.TokenType.*;
import jsonparser.nodes.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.FileReader;
import java.io.File;
public class Parser{
  Token currentToken;
  Tokenizer tokenizer;
 
  public JsonElement parse(String string){
    tokenizer = new Tokenizer(new BufferedReader(new StringReader(string)));
    return parse();
  }
  public JsonElement parse(File file){
    try{
      tokenizer = new Tokenizer(new BufferedReader(new FileReader(file)));
    }catch(java.io.IOException e){
      throw new ParserException(e.getMessage());
    }
    return parse();
  }
  private JsonElement parse(){
    JsonElement jsonElement;
    advance();
    jsonElement = JsonElement();
    consume(EOF);
    return jsonElement;
  }

  private class Pair{
    String key;
    JsonElement element;
  }

  JsonObject JsonObject(){
    JsonObject jsonObject = new JsonObject();
    consume(LEFTBRACE);
    switch(currentToken.type){
      case STRING:
        Pair pair = Pair();
        jsonObject.put(pair.key, pair.element);
        //add pair to JsonObject map
        while(currentToken.type == COMMA){
          advance();
          pair = Pair();
          jsonObject.put(pair.key, pair.element); 
        }
        break;
      case RIGHTBRACE:
        break;
      default:
        throw generateException("JsonObject()", STRING.image, RIGHTBRACE.image);
    }
    consume(RIGHTBRACE);
    return jsonObject;            
  }

  Pair Pair(){
    Pair pair = new Pair();
    if(currentToken.type == STRING){
      pair.key = currentToken.image.substring(1, currentToken.image.length() - 1); //trim quotes off
      advance();
    }else{
        throw generateException("Pair()", STRING.image);
    }
    consume(COLON);
    switch(currentToken.type){
      case NUMBER: case STRING: case TRUE: case FALSE: case NULL:
      case LEFTBRACKET: case LEFTBRACE:
        pair.element = JsonElement(); 
        break;
      default:
        throw generateException("Pair()", "JsonElement");
    }
    return pair; 
  }

  JsonArray JsonArray(){
    JsonArray jsonArray = new JsonArray();
    consume(LEFTBRACKET);
    switch(currentToken.type){
      case NUMBER: case STRING: case TRUE: case FALSE: case NULL:
      case LEFTBRACKET: case LEFTBRACE:
        jsonArray.addElement(JsonElement());
        while(currentToken.type == COMMA){
          advance();
          jsonArray.addElement(JsonElement());
        }
        break;
      case RIGHTBRACKET:
        break;
      default:
        throw generateException("JsonArray()", "JsonElement", RIGHTBRACKET.image);
    }
    consume(RIGHTBRACKET);
    return jsonArray; 
  }
  
  JsonElement JsonElement(){
    String tokenImage = currentToken.image;
    JsonElement jsonElement;
    switch(currentToken.type){
      case NUMBER:
        jsonElement = new JsonNumber(Double.parseDouble(tokenImage));
        advance();
        return jsonElement;
      case STRING:
        String trimQuotes = tokenImage.substring(1, tokenImage.length() - 1);
        jsonElement = new JsonString(trimQuotes);
        advance();
        return jsonElement; 
      case TRUE: case FALSE:
        jsonElement = new JsonBoolean(Boolean.parseBoolean(tokenImage));
        advance();
        return jsonElement;
      case NULL:
        jsonElement = new JsonNull();
        advance();
        return jsonElement;
      case LEFTBRACE:
        return JsonObject();
      case LEFTBRACKET:
        return JsonArray();
      default:
        throw generateException("JsonElement()");         
    }
  }


  private ParserException generateException(String methodName, String... expected){
    StringBuilder sb = new StringBuilder();
    sb.append("Failed to parse " + methodName + ".");
    sb.append(" Encountered " + currentToken.image + " on line: " + currentToken.lineNumber + ", col: " + currentToken.columnNumber + ".");
    if(expected.length > 1){ 
      sb.append(" Expected: " + expected[0]);
      for(int i = 0; i < expected.length; i++){
        sb.append(", " + expected[i]);
      }
      sb.append(".");
    }
    return new ParserException(sb.toString());
  }
  private ParserException generateException(Token.TokenType expectedType){
    return new ParserException("Encountered: " + currentToken.image + ". Expected: " + expectedType.image); 
  }

  void consume(Token.TokenType type){
    if(type != currentToken.type){
      throw generateException(type);
    }
    advance();
  } 
  void advance(){
    currentToken = tokenizer.nextToken();
  }
  
  private class ParserException extends RuntimeException{
    public ParserException(String s){
      super(s);
    }
  }
}
