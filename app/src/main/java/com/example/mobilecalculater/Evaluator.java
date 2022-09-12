package com.example.mobilecalculater;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mobilecalculater.utils.MathOps;

import java.util.ArrayList;
import java.util.function.Function;
import com.example.mobilecalculater.Expression.Unary;

public class Evaluator implements Expression.Visitor<Double> {
    private Environment env;
    private boolean print;

    public Evaluator(Environment env) {
        this.env = env;
        print = true;
    }

    public Double solve(Expression expr) {
        print = true;
            try{
                double result = evaluate(expr);
                if(print) {
                    if (String.valueOf(result).endsWith(".0")) {
                        return result;
                    } else {
                        return result;
                    }
                }
            } catch(Error e) {
                //System.out.println(e.getMessage());
            }
            return 0.0;
    }

    public double evaluate(Expression expr) {
        return expr.accept(this);
    }

    @Override
    public Double visitNullNode(Expression.Null expr) {
        print = false;
        return 0.0;
    }

    @Override
    public Double visitLiteralNode(Expression.Literal expr) { return Double.parseDouble(expr.getValue()); }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Double visitFunctionNode(Expression.Function expr) {
        if(expr.getState()) {
            double arg = evaluate(expr.getArgument());
            Function<Double, Double> result = MathOps.singleParamFunctions.get(expr.getFunction().getType());
            if(result == null) throw new Error("You used a function that isn't implemented yet");
            return result.apply(arg);
        }
        else {
            ArrayList<Double> args = new ArrayList<>();
            for(Expression arg : expr.getArguments()){
                args.add(evaluate(arg));
            }

            Function<ArrayList<Double>, Double> result = MathOps.multiParamFunctions.get(expr.getFunction().getType());
            if(result == null) throw new Error("You used a function that isn't implemented yet");
            return result.apply(args);
        }
    }
    public Double visitGroupingNode(Expression.Grouping expr) {
        if(expr.getType().equals("grouping")) {
            return evaluate(expr.getExpression());
        }
        else if(expr.getType().equals("abs")) {
            return Math.abs(evaluate(expr.getExpression()));
        }

        //Unreachable
        throw new Error("Failure in a Grouping Node");
    }

    public Double visitBinaryNode(Expression.Binary expr) {
        double left = evaluate(expr.getLeft());
        double right = evaluate(expr.getRight());
        //checkArithmeticErrors(expr.getOperator(), left, right);
        switch (expr.getOperator().getType()) {
            case MINUS:
                return left - right;
            case PLUS:
                return left + right;
            case SLASH:
                return left / right;
            case STAR:
                return left * right;
            case EXP:
                return Math.pow(left, right);
            case MODULO:
                return left % right;
        }
        // Unreachable.
        throw new Error("Failure in a Binary Node");
    }

    @Override
    public Double visitAssignNode(Expression.Assign expr) {
        env.variables.put(expr.getName(), evaluate(expr.getExpression()));
        print = false;
        return 0.0;
    }
    public Double visitUnaryNode(Unary expr) {
		// TODO Auto-generated method stub
		return null;
	}
}