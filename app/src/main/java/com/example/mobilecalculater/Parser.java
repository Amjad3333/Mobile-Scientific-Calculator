package com.example.mobilecalculater;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.calculator.utils.Error;
import com.example.mobilecalculater.utils.MathOps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * My wacky version of EBNF syntax
 * :> represents an equals, so it will define a layer with the rules
 * | represents or, which means it can have a layer include one rule group or another
 * () represents a grouping
 * [] represents repetition, meaning that a group can be repeated many times
 * ; means termination of the rules for a layer
 * */

public class Parser {
    private final ArrayList<Token> tokens;
    private int current = 0;
    private final Environment env;

    public Parser(ArrayList<Token> tokens, Environment env) {
        this.tokens = tokens;
        this.env = env;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public Expression parse() throws IOException {
        return assignment();
    }

    // going from smallest layer to highest layer

    /**
     * literal :>  NUMBER | '(' expression ')' ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression literal() throws IOException {
        if(match(TokenType.NUMBER)) return new Expression.Literal(previous().getLexme());
        if(match(TokenType.VARIABLE)){
            return new Expression.Literal(env.variables.get(String.valueOf(previous().getLexme())));
        }
        if(match(TokenType.PI)) return new Expression.Literal(Math.PI);
        if(match(TokenType.E)) return new Expression.Literal(Math.E);
        if(match(TokenType.PHI)) return new Expression.Literal(MathOps.PHI);
        if(match(TokenType.ANS)) return new Expression.Literal(env.getPreviousResult());
        if(match(TokenType.LEFT_PAREN)) {
            try {
                Expression expr =expression();
                consume(TokenType.RIGHT_PAREN);
                return new Expression.Grouping(expr, "grouping");
            } catch(RuntimeException | IOException ignored) {
            }
        }
        if(match(TokenType.ABS_BRACK)) {
            Expression expr = expression();
            try {
                consume(TokenType.ABS_BRACK);
                return new Expression.Grouping(expr, "abs");
            } catch(RuntimeException ignored) {
            }
        }
        if(match(TokenType.IMPORT)) {
            consume(TokenType.LEFT_PAREN);
            String path = String.valueOf(advance().getLexme());

            if (match(TokenType.COMMA)) {
                boolean override = Boolean.parseBoolean(String.valueOf(advance().getLexme()));
                consume(TokenType.RIGHT_PAREN);
                env.importVariablesFromFile(path, override);
            } else {
                consume(TokenType.RIGHT_PAREN);
                env.importVariablesFromFile(path, false);
            }
            return new Expression.Null();
        }
        throw new Error("Not a valid expression :(");
    }

    /*
    * function :> func_name '(' expression ')' | literal ;
    * func_name is all the functions valid, I can't be bothered to write all of them down
    */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression function() throws IOException {
        ArrayList<TokenType> spFuncs = new ArrayList<>(List.of(TokenType.SQRT,TokenType. LN, TokenType.SIN, TokenType.SINH, TokenType.COS, TokenType.COSH, TokenType.TAN, TokenType.TANH, TokenType.CSC, TokenType.CSCH, TokenType.SEC, TokenType.SECH, TokenType.COT, TokenType.COTH, TokenType.ARCSIN, TokenType.ARCSINH, TokenType.ARCCOS, TokenType.ARCCOSH, TokenType.ARCTAN, TokenType.ARCTANH,
                TokenType.ARCCSC, TokenType.ARCCSCH, TokenType.ARCSEC, TokenType.ARCSECH, TokenType.ARCCOT, TokenType.ARCCOTH, TokenType.VER, TokenType.VCS, TokenType.CVS, TokenType.CVC, TokenType.SEM, TokenType.HVC, TokenType.HCV, TokenType.HCC, TokenType.EXS, TokenType.EXC, TokenType.CRD));
        ArrayList<TokenType> mpFuncs = new ArrayList<>(List.of(TokenType.ROOT, TokenType.LOG, TokenType.NORMALPDF, TokenType.NORMALCDF, TokenType.BINOMIALPDF, TokenType.BINOMIALCDF, TokenType.INVNORM));
        if(match(spFuncs)) {
            Token function = previous();
            consume(TokenType.LEFT_PAREN);
            Expression arg = expression();
            consume(TokenType.RIGHT_PAREN);
            return new Expression.Function(function, arg);
        }
        else if(match(mpFuncs)){
            Token function = previous();
            consume(TokenType.LEFT_PAREN);
            ArrayList<Expression> args = new ArrayList<>();
            args.add(expression());
            while(match(TokenType.COMMA)){
                args.add(expression());
            }
            consume(TokenType.RIGHT_PAREN);
            return new Expression.Function(function, args);
        }
        return literal();

    }

    /*
    * factorial :>  function '!' | function ;
    */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression factorial() throws IOException {
        Expression left = function();
        if(match(TokenType.FACTORIAL)) {
            Token fac = previous();
            return new Expression.Unary(fac, left);
        }

        return left;
    }

    /**
     * negate :>  '-' negate | factorial ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression negate() throws IOException {
        if(match(TokenType.MINUS)) {
            Token minus = previous();
            Expression right = factorial();
            return new Expression.Unary(minus, right);
        }

        return factorial();
    }

    /**
     * power :>  negate [ ( '^' ) negate ] ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression power() throws IOException {
        Expression expr = negate();

        while(match(TokenType.EXP)) {
            Token op = previous();
            Expression right = power();
            expr = new Expression.Binary(expr, op, right);
        }
        return expr;
    }


    /**
     * multidivimod :>  power [ ( '*' | '/' | '%' ) power ] ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression multidivimod() throws IOException {
        Expression expr = power();

        while(match(TokenType.STAR, TokenType.SLASH, TokenType.MODULO)) {
            Token op = previous();
            Expression right = power();
            expr = new Expression.Binary(expr, op, right);
        }
        return expr;
    }

    /**
     * addsub :>  multidivimod [ ( '+' | '-' ) multidivimod ] ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression addsub() throws IOException {
        Expression expr = multidivimod();

        while(match(TokenType.PLUS, TokenType.MINUS)) {
            Token op = previous();
            Expression right = multidivimod();
            expr = new Expression.Binary(expr, op, right);
        }
        return expr;
    }

    /**
     * expression :>  addsub ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression expression() throws IOException {
        return addsub();
    }

    /**
     *assignment :> ( variable = expression ) | expression ;
     */
    @RequiresApi(api = Build.VERSION_CODES.R)
    private Expression assignment() throws IOException {
        if(match(TokenType.VARIABLE)){
            Token var_name = previous();
            if(match(TokenType.EQUAL)){
                Expression expr = expression();
                return new Expression.Assign(String.valueOf(var_name.getLexme()), expr);
            }
            else {
                stepBack();
            }

        }
        return expression();
    }


    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (checkType(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private boolean match(ArrayList<TokenType> types) {
        for (TokenType type : types) {
            if (checkType(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private void consume(TokenType type) {
        if (checkType(type)) {
            advance();
            return;
        }


//        System.err.println(message);
        throw new Error(String.format("Missing Token %s :(", type.toString()));
    }

    private boolean checkType(TokenType type) {
        if (isAtEnd()) return false;
        return peek().getType() == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOL;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
    private void stepBack() { current -= 1; }
}
