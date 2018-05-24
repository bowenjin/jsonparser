package jsonparser;
import static jsonparser.Token.TokenType.*;
import java.io.BufferedReader;
class Tokenizer{
  String currentLine = null;
  char currentChar = 0;
  int currentColumn = 0;
  int currentLineNumber = 0;
  BufferedReader bufferedReader;
  Tokenizer(BufferedReader bufferedReader){
    this.bufferedReader = bufferedReader;
    nextChar();
  }    
  Token nextToken() throws TokenizerException{
    while(Character.isWhitespace(currentChar)){
      nextChar(); 
    }
    if(currentChar == 3){
      return createToken(EOF);
    }
    Token token;
    if(currentChar == '"'){
      StringBuilder sb = new StringBuilder();
      do{
        sb.append(currentChar);
        nextChar();
      }while(currentChar != '"');
      sb.append(currentChar);
      nextChar();
      token = createToken(STRING, sb.toString());
    }else if(Character.isDigit(currentChar)){
      StringBuilder sb = new StringBuilder();
      do{
        sb.append(currentChar);
        nextChar();
      }while(Character.isDigit(currentChar));
      if(currentChar == '.'){
        sb.append(currentChar);
        nextChar();
        while(Character.isDigit(currentChar)){
          sb.append(currentChar);
          nextChar();
        }
      }
      token = createToken(NUMBER, sb.toString());     
    }else if(Character.isLetter(currentChar)){
      StringBuilder sb = new StringBuilder();
      do{
        sb.append(currentChar);
        nextChar();
      }while(Character.isLetterOrDigit(currentChar));
      String keyword = sb.toString();
      switch(keyword){
        case "true":
          token = createToken(TRUE, keyword);
          break;
        case "false":
          token = createToken(FALSE, keyword);
          break;
        case "null":
          token = createToken(NULL, keyword);
          break;
        default:
          throw new TokenizerException("Invalid keyword " + keyword);
      }
    }else{ 
      switch(currentChar){
        case '{':
          token = createToken(LEFTBRACE);
          break;
        case '}':
          token = createToken(RIGHTBRACE);
          break;
        case ',':
          token = createToken(COMMA);
          break;
        case ':':
          token = createToken(COLON);
          break;
        case '[':
          token = createToken(LEFTBRACKET);
          break;
        case ']':
          token = createToken(RIGHTBRACKET);
          break;
        case '.':
          token = createToken(DOT);
          break;
        default:
          throw generateException();
      }
      nextChar();
    }
    System.out.println(token.toString());
    return token;
  }

  private void nextChar(){
    if(currentChar == 3){   
      return;
    }
    if(currentLine == null || currentColumn == currentLine.length()){
      try{
        if((currentLine = bufferedReader.readLine()) != null){
          currentLineNumber++;
          currentColumn = 0;       
        }else{
          currentChar = 3; //3 means EOF
          return;
        }
      }catch(java.io.IOException e){
        throw new TokenizerException(e.getMessage());
      }
    }
    currentChar = currentLine.charAt(currentColumn++); 
  }
  
  private TokenizerException generateException(){
    return new TokenizerException("Encountered '" + currentChar + "' on line " 
      + currentLineNumber + ", column " + currentColumn); 
  }

  private Token createToken(Token.TokenType type, String image){
    Token token = new Token();
    token.type = type;
    token.lineNumber = currentLineNumber;
    token.columnNumber = currentColumn;
    token.image = image;
    return token;
  }

  private Token createToken(Token.TokenType type){
    return createToken(type, "" + currentChar);
  }

  private class TokenizerException extends RuntimeException{
    public TokenizerException(String message){
      super(message);
    }
  }
}         






