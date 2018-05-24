# jsonparser
parses JSON, returns a Java object parse tree

Instructions:
1. Execute "javac Main.java" from root directory of repository
2. Call the parse() method of the Parser class from some method to get a JsonElement object which is the root of the parse tree
    There are two overloaded parse() methods one accepts a File and the other a String:
      i. JsonElement parse(File file) - Reads JSON from the file and returns the root of the parse tree
      ii. JsonElement parse(String string) - Reads the string and returns the root of the parse tree 
3. JsonElement is a super class of several subclasses:
  i. JsonObject
  ii. JsonArray
  iii. JsonString
  iv. JsonNumber
  v. JsonNull
  vi. JsonBoolean
4. Call getObject() on any of the above classes to get the underlying Object
, which can be casted to actual underlying data type in order to access the data.
Below are the underlying types for each type of parse tree node.
  i. JsonObject - Map<String, JsonElement>
  ii. JsonArray - List<JsonElement>
  iii. JsonString - String
  iv. JsonNumber - Double
  v. JsonNull - none
  vi. JsonBoolean - Boolean
