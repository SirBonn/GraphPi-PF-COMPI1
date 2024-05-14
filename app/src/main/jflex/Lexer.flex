package srbn.graphpi.BackEnd.Lexer;

import java.util.ArrayList;
import srbn.graphpi.BackEnd.DomainObjs.Errors.*;
import srbn.graphpi.BackEnd.Parser.ParserSym;
import java_cup.runtime.*;

%%

//JFlex Config
%class Lexer
%unicode
%line
%column
%caseless
%cup
%public

//JFlex Macros

WHITESPCS       = ([\s\t\r\n]+)
DIGIT           = [0-9]
NUMBER          = ([0-9][0-9]*)
LETTER          = [a-zA-Z]
STRING          = (\"([^\"]|[a-zA-Z]|(\s)|[0-9])+\")
NUMBSIZE        = ([0-9]+)"px"
HEXCODE         = ("\"#"[0-9a-fA-F]{6}"\"")
IDENTIFIER      = (-|_|[a-zA-Z]|[a-zA-Z0-9])* "ID" /*| (-|_|[a-zA-Z]|[a-zA-Z0-9])+"id"*/
//DECLARATIONS
DATA            = "\"data\":"
CATEGORY        = "\"category\":"
NAME            = "\"name\":"
TITTLE          = "\"tittle\":" | "\"title\":"
POINTS          = "\"points\":"
CHART           = "\"chart\":"
//-----------------------------------------------
VALUE           = "\"value\":" | "\"value\":"
LABEL           = "\"label\":"
COLOR           = "\"color\":"
Xaxis           = "\"x\":"
Yaxis           = "\"y\":"
LEGENDPOSITION  = "\"legendPosition\":"
XAXISLABEL      = "\"xAxisLabel\":"
YAXISLABEL      = "\"yAxisLabel\":"
SIZE            = "\"size\":"
DESCRIPTION     = "\"description\":"
ICON            = "\"icon\":"
LINK            = "\"link\":"
LINESTYLE       = "\"lineStyle\":"
KEYWORDS        = "\"keywords\":"
HEADER          = "\"header\":"
FOOTER          = "\"footer\":"
BACKGROUNDCOLOR = "\"backgroundColor\":"
FONTFAMILY      = "\"fontFamily\":"
FONTSIZE        = "\"fontSize\":"
COPYRIGHT       = "\"copyRight\":"
//OPERATORS
PLUS            = "+"
MINUS           = "-"
ASTHERISK       = "*"
DIVIDE          = "/"
SUM             = "++"
SUB             = "--"
ASSIGN          = "="
MUL             = "*="
DIV             = "/="
//COMPARATORS
EQUALS          = "=="
DIFFERENT       = "!="
GREATER         = ">"
LESS            = "<"
GREATEREQUAL    = ">="
LESSEQUAL       = "<="
//KEYWORDS
IF              = "if"
ELSE            = "else"
FOR             = "for"
WHILE           = "while"
DOWHILE         = "do"
TRUE            = "true"
FALSE           = "false"
//OTHERS
COMMA           = ","
SEMICOLON       = ";"
DOBLEDOT        = ":"
OPENBRACKET     = "{"
CLOSEBRACKET    = "}"
OPENBRACE       = "["
CLOSEBRACE      = "]"
OPENPARENTHESIS = "("
CLOSEPARENTHESIS= ")"
DOBLEQUOTE      = "\""
SINGLEQUOTE     = "\'"



%{
    StringBuffer sb = new StringBuffer();
    ArrayList<ErrorP> errors = new ArrayList<ErrorP>();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void addError(String message) {
        errors.add(new ErrorP(yyline+1, yycolumn+1, "No se reconoce el simbolo: ", 0, message ));
      }
%}

%eofval{
	return new Symbol(ParserSym.EOF);
%eofval}

%%

//tags

{DATA}                            {return symbol(ParserSym.DATA, yytext());}
{CATEGORY}                        {return symbol(ParserSym.CATEGORY, yytext());}
{NAME}                            {return symbol(ParserSym.NAME, yytext());}
{TITTLE}                          {return symbol(ParserSym.TITTLE, yytext());}
{POINTS}                          {return symbol(ParserSym.POINTS, yytext());}
{VALUE}                           {return symbol(ParserSym.VALUE, yytext());}
{LABEL}                           {return symbol(ParserSym.LABEL, yytext());}
{COLOR}                           {return symbol(ParserSym.COLOR, yytext());}
{CHART}                           {return symbol(ParserSym.CHART, yytext());}
{Xaxis}                           {return symbol(ParserSym.Xaxis, yytext());}
{Yaxis}                           {return symbol(ParserSym.Yaxis, yytext());}
{LEGENDPOSITION}                  {return symbol(ParserSym.LEGENDPOSITION, yytext());}
{XAXISLABEL}                      {return symbol(ParserSym.XAXISLABEL, yytext());}
{YAXISLABEL}                      {return symbol(ParserSym.YAXISLABEL, yytext());}
{SIZE}                            {return symbol(ParserSym.SIZE, yytext());}
{DESCRIPTION}                     {return symbol(ParserSym.DESCRIPTION, yytext());}
{ICON}                            {return symbol(ParserSym.ICON, yytext());}
{LINK}                            {return symbol(ParserSym.LINK, yytext());}
{LINESTYLE}                       {return symbol(ParserSym.LINESTYLE, yytext());}
{KEYWORDS}                        {return symbol(ParserSym.KEYWORDS, yytext());}
{HEADER}                          {return symbol(ParserSym.HEADER, yytext());}
{FOOTER}                          {return symbol(ParserSym.FOOTER, yytext());}
{BACKGROUNDCOLOR}                 {return symbol(ParserSym.BACKGROUNDCOLOR, yytext());}
{FONTFAMILY}                      {return symbol(ParserSym.FONTFAMILY, yytext());}
{FONTSIZE}                        {return symbol(ParserSym.FONTSIZE, yytext());}
{COPYRIGHT}                       {return symbol(ParserSym.COPYRIGHT, yytext());}
{NUMBSIZE}                        {return symbol(ParserSym.NUMBSIZE, yytext());}
{IF}                              {return symbol(ParserSym.IF, yytext());}
{ELSE}                            {return symbol(ParserSym.ELSE, yytext());}
{FOR}                             {return symbol(ParserSym.FOR, yytext());}
{WHILE}                           {return symbol(ParserSym.WHILE, yytext());}
{DOWHILE}                         {return symbol(ParserSym.DOWHILE, yytext());}
{TRUE}                            {return symbol(ParserSym.TRUE, yytext());}
{FALSE}                           {return symbol(ParserSym.FALSE, yytext());}
{EQUALS}                          {return symbol(ParserSym.EQUALS, yytext());}
{DIFFERENT}                       {return symbol(ParserSym.DIFFERENT, yytext());}
{GREATER}                         {return symbol(ParserSym.GREATER, yytext());}
{LESS}                            {return symbol(ParserSym.LESS, yytext());}
{GREATEREQUAL}                    {return symbol(ParserSym.GREATEREQUAL, yytext());}
{LESSEQUAL}                       {return symbol(ParserSym.LESSEQUAL, yytext());}
{COMMA}                           {return symbol(ParserSym.COMMA, yytext());}
{SEMICOLON}                       {return symbol(ParserSym.SEMICOLON, yytext());}
{DOBLEDOT}                        {return symbol(ParserSym.DOBLEDOT, yytext());}
{OPENBRACKET}                     {return symbol(ParserSym.OPENBRACKET, yytext());}
{CLOSEBRACKET}                    {return symbol(ParserSym.CLOSEBRACKET, yytext());}
{OPENBRACE}                       {return symbol(ParserSym.OPENBRACE, yytext());}
{CLOSEBRACE}                      {return symbol(ParserSym.CLOSEBRACE, yytext());}
{OPENPARENTHESIS}                 {return symbol(ParserSym.OPENPARENTHESIS, yytext());}
{CLOSEPARENTHESIS}                {return symbol(ParserSym.CLOSEPARENTHESIS, yytext());}
{DOBLEQUOTE}                      {return symbol(ParserSym.DOBLEQUOTE, yytext());}
{SINGLEQUOTE}                     {return symbol(ParserSym.SINGLEQUOTE, yytext());}
{NUMBER}                          {return symbol(ParserSym.NUMBER, yytext());}
{HEXCODE}                         {return symbol(ParserSym.HEXCODE, yytext());}
{SUM}                             {return symbol(ParserSym.SUM, yytext());}
{SUB}                             {return symbol(ParserSym.SUB, yytext());}
{MUL}                             {return symbol(ParserSym.MUL, yytext());}
{DIV}                             {return symbol(ParserSym.DIV, yytext());}
{ASSIGN}                          {return symbol(ParserSym.ASSIGN, yytext());}
{PLUS}                            {return symbol(ParserSym.PLUS, yytext());}
{MINUS}                           {return symbol(ParserSym.MINUS, yytext());}
{ASTHERISK}                       {return symbol(ParserSym.ASTHERISK, yytext());}
{DIVIDE}                          {return symbol(ParserSym.DIVIDE, yytext());}
{IDENTIFIER}                      {return symbol(ParserSym.IDENTIFIER, yytext());}
{NUMBER}                          {return symbol(ParserSym.NUMBER, yytext());}
{DIGIT}                           {return symbol(ParserSym.DIGIT, yytext());}
{LETTER}                          {return symbol(ParserSym.LETTER, yytext());}
{STRING}                          {return symbol(ParserSym.STRING, yytext());}
{WHITESPCS}                       {/*Ignore*/}
[^]                               {addError(yytext());}