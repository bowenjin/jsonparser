package jsonparser;
class Token{
  TokenType type;
  int lineNumber;
  int columnNumber;
  String image;
  
  enum TokenType{
    LEFTBRACE("\"{\""), RIGHTBRACE("\"}\""), COMMA("\",\""), COLON("\":\""),
    LEFTBRACKET("\"[\""), RIGHTBRACKET("\"]\""), DOT("\".\""), NUMBER("<NUMBER>"),
    STRING("<STRING>"), TRUE("\"true\""), FALSE("\"false\""), NULL("\"null\""), EOF("<EOF>");
    String image;
    private TokenType(String image){
      this.image = image;  
    }   
  }

  public String toString(){
    return "type = " + type.image + ", line = " + lineNumber + ", column = " + columnNumber + ", image = " + image;
  }
}
