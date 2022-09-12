package com.example.mobilecalculater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.mobilecalculater.TokenType.*;

public class Lexer {
    private final String line;
    private final ArrayList<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private final Map<String, TokenType> functions;

    public Lexer(String line) {
        this.line = line;
        functions = new HashMap<>();
        functions.put("ans", TokenType.ANS);
        functions.put("sqrt", TokenType.SQRT);
        functions.put("root", TokenType.ROOT);
        functions.put("log", TokenType.LOG);
        functions.put("ln", TokenType.LN);
        functions.put("sin", TokenType.SIN);
        functions.put("sinh", TokenType.SINH);
        functions.put("cos", TokenType.COS);
        functions.put("cosh", TokenType.COSH);
        functions.put("tan", TokenType.TAN);
        functions.put("tanh", TokenType.TANH);
        functions.put("csc", TokenType.CSC);
        functions.put("csch", TokenType.CSCH);
        functions.put("sec", TokenType.SEC);
        functions.put("sech", TokenType.SECH);
        functions.put("cot", TokenType.COT);
        functions.put("coth", TokenType.COTH);
        functions.put("arcsin", TokenType.ARCSIN);
        functions.put("arccos", TokenType.ARCCOS);
        functions.put("arctan", TokenType.ARCTAN);
        functions.put("arccsc", TokenType.ARCCSC);
        functions.put("arcsec", TokenType.ARCSEC);
        functions.put("arccot", TokenType.ARCCOT);
        functions.put("ver", TokenType.VER);
        functions.put("vcs", TokenType.VCS);
        functions.put("cvs", TokenType.CVS);
        functions.put("cvc", TokenType.CVC);
        functions.put("sem", TokenType.SEM);
        functions.put("hvc", TokenType.HVC);
        functions.put("hcv", TokenType.HCV);
        functions.put("hcc", TokenType.HCC);
        functions.put("exs", TokenType.EXS);
        functions.put("exc", TokenType.EXC);
        functions.put("crd", TokenType.CRD);
        functions.put("normalpdf", TokenType.NORMALPDF);
        functions.put("normalcdf", TokenType.NORMALCDF);
        functions.put("binomialpdf", TokenType.BINOMIALPDF);
        functions.put("binomialcdf", TokenType.BINOMIALCDF);
        functions.put("invnorm", TokenType.INVNORM);

        functions.put("import", TokenType.IMPORT);
        functions.put("pi", TokenType.PI);
        functions.put("phi", TokenType.PHI);
        functions.put("e", TokenType.E);
    }

    public ArrayList<Token> scanTokens() {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }
        tokens.add(new Token(TokenType.EOL, null));
        return tokens;

    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '|': addToken(TokenType.ABS_BRACK); break;
            case '-': addToken(TokenType.MINUS); break;
            case '+': addToken(TokenType.PLUS); break;
            case '*': addToken(TokenType.STAR); break;
            case '/': addToken(TokenType.SLASH); break;
            case '^': addToken(TokenType.EXP); break;
            case '%': addToken(TokenType.MODULO); break;
            case '!': addToken(TokenType.FACTORIAL); break;
            case '=': addToken(TokenType.EQUAL); break;
            case ',': addToken(TokenType.COMMA); break;
            case ' ':
            case '\r':
            case '\t': break;
            default:
                if (isDigit(c)) {
                    number();
                } else if(isLetter(c)) {
                    name();
                } else if(c == '"') {
                    arg();
                } else {
                    //System.err.printf("Error, char %s is not allowed >:(%n", c);
                }
                break;
        }
    }




    //helper methods
    private boolean isAtEnd() {
        return current >= line.length();
    }

    private char advance() {
        current++;
        return line.charAt(current - 1);
    }

    private char peek(int d) {
        if (current + d >= line.length()) return '\0';
        return line.charAt(current + d);
    }

    private void addToken(TokenType type, Object lexeme) { tokens.add(new Token(type, lexeme)); }

    private void addToken(TokenType type) {
        String text = line.substring(start, current);
        tokens.add(new Token(type, text));
    }

    private boolean matchNext(char expected) {
        if (isAtEnd()) return false;
        if (line.charAt(current) != expected) return false;

        current++;
        return true;
    }


    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    private boolean isLetter(char c) { return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_'; }

    private boolean isAlphaNumeric(char c) { return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '_';}

    private void number() {
        while (isDigit(peek(0))) advance();

        // Look for a fractional part.
        if (peek(0) == '.' && isDigit(peek(1))) {
            // Consume the "."
            advance();

            while (isDigit(peek(0))) advance();
        }

        addToken(TokenType.NUMBER, Double.parseDouble(line.substring(start, current)));
    }

    private void name() {
        while(isAlphaNumeric(peek(0))) advance();

        String text = line.substring(start, current);
        TokenType type = functions.get(text);
//        if(type == null) throw new Error(String.format("Invalid text '%s' smh", text));
        if(type == null) {
            addToken(TokenType.VARIABLE, text);
        } else {
            addToken(type);
        }

    }

    private void arg() {
        while((peek(0) != '"')) advance();
        advance();

        String arg = line.substring(start + 1, current - 1);
        addToken(TokenType.ARG, arg);
    }


}
