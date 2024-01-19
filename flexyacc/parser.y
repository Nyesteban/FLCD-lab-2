%{
#include <stdio.h>
#include <stdlib.h>
#define YYDEBUG 1

%}


%token INTEGER
%token STRING
%token INPUT
%token OUTPUT
%token IF
%token ELSE
%token FOR
%token RETURN
%token ARRAY

%token plus
%token minus
%token mul
%token division
%token mod
%token lessOrEqual
%token moreOrEqual
%token less
%token more
%token equal
%token not
%token eq
%token and
%token or

%token leftCurlyBracket
%token rightCurlyBracket
%token leftRoundBracket
%token rightRoundBracket
%token leftBracket
%token rightBracket
%token semicolon
%token comma
%token quote

%token IDENTIFIER
%token NUMBER_CONST
%token STRING_CONST

%%
Program : Statement Program {printf("Program -> Statement ; Program\n");} | Statement {printf("Program -> Statement ;\n");} ;
Type : INTEGER {printf("Type-> INTEGER\n");} | STRING {printf("Type-> STRING\n");} ;
Statement : ArrDecl semicolon {printf("Statement -> ArrDecl\n");} | SimpleDecl semicolon {printf("Statement -> SimpleDecl\n");} | AssignStmt semicolon {printf("Statement -> AssignStmt\n");} | IfStatement {printf("Statement -> IfStatement\n");} | ForStatement {printf("Statement -> ForStatement\n");} | InputStatement semicolon {printf("Statement -> InputStatement\n");} | OutputStatement semicolon {printf("Statement -> OutputStatement\n");} ;
SimpleDecl : INTEGER IdList {printf("SimpleDecl -> INTEGER IdList\n");} | STRING IdList {printf("SimpleDecl -> STRING IdList\n");} ;
ArrDecl : ARRAY Type leftBracket INTEGER rightBracket IdList {printf("ArrDecl-> ARRAY Type leftBracket INTEGER rightBracket IdList\n");} ;
IdList : IDENTIFIER {printf("IdList -> IDENTIFIER \n");} | IDENTIFIER comma IdList {printf("IdList -> IDENTIFIER , IdList \n");} ;
AssignStmt : IDENTIFIER eq Expression {printf("AssignStmt -> IDENTIFIER = Expression \n");} ;
Expression : IntExpression {printf("Expression -> IntExpression \n");} | StringExpression {printf("Expression -> StringExpression \n");} | LogicalExpression {printf("Expression -> LogicalExpression \n");} ;
MathematicalOperator : plus {printf("MathematicalOperator -> + \n");} | minus {printf("MathematicalOperator -> - \n");} | mul {printf("MathematicalOperator -> * \n");} | division {printf("MathematicalOperator -> / \n");} | mod {printf("MathematicalOperator -> % \n");} ;
IntExpression : NUMBER_CONST {printf("IntExpression -> NUMBER_CONST \n");} | IDENTIFIER {printf("IntExpression -> IDENTIFIER \n");} | IntExpression MathematicalOperator IntExpression {printf("IntExpression -> IntExpression MathematicalOperator IntExpression \n");} | leftRoundBracket IntExpression rightRoundBracket {printf("IntExpression -> ( IntExpression ) \n");} ;
StringExpression : STRING_CONST {printf("StringExpression -> STRING_CONST \n");} | IDENTIFIER {printf("StringExpression -> IDENTIFIER \n");} | leftRoundBracket StringExpression rightRoundBracket {printf("StringExpression -> ( StringExpression ) \n");} ;
LogicalOperator : lessOrEqual {printf("LogicalOperator -> <= \n");} | moreOrEqual {printf("LogicalOperator -> >= \n");} | less {printf("LogicalOperator -> < \n");} | more {printf("LogicalOperator -> > \n");} | equal {printf("LogicalOperator -> == \n");} | and {printf("LogicalOperator -> && \n");} | or {printf("LogicalOperator -> || \n");} ;
LogicalExpression : NUMBER_CONST {printf("LogicalExpression -> NUMBER_CONST \n");} | IDENTIFIER {printf("LogicalExpression -> IDENTIFIER \n");} | IntExpression {printf("LogicalExpression -> IntExpression \n");} | LogicalExpression LogicalOperator LogicalExpression {printf("LogicalExpression -> LogicalExpression LogicalOperator LogicalExpression \n");} | leftRoundBracket LogicalExpression rightRoundBracket {printf("LogicalExpression -> ( LogicalExpression ) \n");} | not LogicalExpression {printf("LogicalExpression -> ! LogicalExpression \n");};
IfStatement : IF leftRoundBracket LogicalExpression rightRoundBracket leftCurlyBracket Program rightCurlyBracket {printf("IfStatement -> if ( Condition ) { Program } \n");} | IF leftRoundBracket LogicalExpression rightRoundBracket leftCurlyBracket Program rightCurlyBracket ELSE leftCurlyBracket Program rightCurlyBracket {printf("IfStatement -> if ( Condition ) { Program } else { Program } \n");} ;
ForStatement : FOR leftRoundBracket AssignStmt semicolon LogicalExpression semicolon AssignStmt rightRoundBracket leftCurlyBracket Program rightCurlyBracket {printf("ForStatement -> for (AssignStmt; LogicalExpression; AssignStmt) { Program } \n");} ;
InputStatement : INPUT leftRoundBracket IDENTIFIER rightRoundBracket {printf("InputStatement -> input(IDENTIFER) \n");} ;
OutputStatement : OUTPUT leftRoundBracket IDENTIFIER rightRoundBracket {printf("OutputStatement -> output(IDENTIFER) \n");} | OUTPUT leftRoundBracket NUMBER_CONST rightRoundBracket {printf("OutputStatement -> output(NUMBER_CONST) \n");} | OUTPUT leftRoundBracket STRING_CONST rightRoundBracket {printf("OutputStatement -> output(STRING_CONST) \n");} ;
%%
yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
  if(argc>1) yyin = fopen(argv[1], "r");
  if((argc>2)&&(!strcmp(argv[2],"-d"))) yydebug = 1;
  if(!yyparse()) fprintf(stderr,"\tO.K.\n");
}


