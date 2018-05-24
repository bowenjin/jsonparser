# jsonparser
parses JSON, returns a Java object parse tree

## Instructions:
1. Execute "javac jsonparser/*.java" from root directory of repository
2. Add the statement "import jsonparser.Parser;" to your Java source file
3. Create a Parser object
   - "Parser parser = new Parser();"
4. Call the parse method of the Parser object on a File object or a String object 
   - *parser.parse(new File("path/of/your/json/file"));*
   - *parser.parse("{"key1" : "value1", "key2": "value2"}");*
5. parser.parse returns root of the parse tree, which is a JsonElement  
   - JsonElement is a superclass of the following classes:
     - JsonObject
     - JsonArray
     - JsonString
     - JsonNumber
     - JsonBoolean
     - JsonNull
6. Call getObject() on any of the above classes to get the underlying Object

7. Here are the methods to get the underlying types of each class
   - JsonObject - *Map<String, JsonElement> getMap()*
   - JsonArray - *List<JsonElement> getList()*
   - JsonString - *String getString()*
   - JsonNumber - *Double getDouble()*
   - JsonNull - *none*
   - JsonBoolean - *Boolean getBoolean*
