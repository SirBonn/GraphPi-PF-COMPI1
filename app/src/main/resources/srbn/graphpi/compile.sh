echo "STARTING JFLEX COMPILING"
java -jar C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\resources\srbn\graphpi\jflex\jflex-full-1.9.1.jar -d C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\java\srbn\graphpi\BackEnd\Lexer C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\jflex\Lexer.flex

echo "STARTING CUP COMPILING"
java -jar C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\resources\srbn\graphpi\cup\cup-11a.jar -parser Parser -symbols ParserSym C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\cup\Parser.cup
mv Parser.java C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\java\srbn\graphpi\BackEnd\Parser\Parser.java
mv ParserSym.java C:\sirbochocho\Universidad\PS2024\COMPI\GraphPi\app\src\main\java\srbn\graphpi\BackEnd\Parser\ParserSym.java
