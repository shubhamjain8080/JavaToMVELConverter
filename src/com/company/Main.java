package com.company;

import com.company.convertors.ClassConverter;
import com.company.models.methods.Method;
import com.company.models.variables.Variable;

import java.io.*;
import java.util.Scanner;

public class Main {

//    public static final String CLASS_DEF = "public class (\\w)*";
//    public static final String VAR_WITHOUT_VALUE = "public(\\s)+([\\D && [^=]]+)(\\s)+(\\w+)(\\s)*(;)";
//    public static final String VAR_WITH_VALUE = "public(\\s)+([\\D && [^=]]+)+(\\s)+(\\w)+(\\s)*(=)(\\s)*(.)+(;)";

    public static void main(String[] args) throws IOException {
        File file =
                new File("F:\\JavaToMVELConvertor\\src\\com\\company\\DefinitionsConversionData.txt");
        Scanner sc = new Scanner(file);
        BufferedWriter writer = new BufferedWriter
                (new FileWriter("F:\\JavaToMVELConvertor\\src\\com\\company\\output.txt"));
        while (sc.hasNextLine()) {
            String output = convert(sc.nextLine().replaceAll("^\\s+", ""));
            writer.write(output);
        }
        writer.close();
    }

    private static String convert(String input) throws IOException {
        String output;

        if(isIfForWhile(input)){
            output = input;
        }
        else if (isAClass(input)){
            output = ClassConverter.convertClass(input) + "\n";
        }
        else if (isAMethod(input)){
            output = new Method(input).getMethodSignature() + "\n";
        }
        else if (isAReturnStatement(input)){
            output = input.replaceFirst("return(\\s)+", "");
        }
        else if (isAMethodCall(input)){
            output = input;
        }
        else if(isAVariable(input)){
            output = Variable.variableSignature(input) + "\n";
        }
        else {
            output = input + "\n";
        }
        return output;
    }

    private static boolean isAReturnStatement(String input) {
        return input.contains("return ");
    }

    private static boolean isAMethodCall(String input) {
        return input.contains(".") && !input.contains("=");
    }

    private static boolean isIfForWhile(String input) {
        return isIfElse(input) || isFor(input) || isWhile(input);
    }

    private static boolean isFor(String input) {
        return input.startsWith("for(") || input.contains(" for ")
                || input.startsWith("for ")||input.contains(" for(");
    }
    private static boolean isWhile(String input) {
        return input.startsWith("while(") || input.contains(" while ")
                || input.startsWith("while ")||input.contains(" while(");
    }

    private static boolean isIfElse(String input) {
        return input.startsWith("if(") || input.contains(" if ")
                || input.startsWith("if ")||input.contains(" if(")||
                input.startsWith("else") || input.contains(" else");
    }

    private static boolean isAVariable(String input) {
        return input.contains(";");
    }

    private static boolean isAMethod(String input) {
        return input.contains("(") && input.contains(")") && !input.contains("=") && !input.contains(";") ;
    }

    private static boolean isAClass(String input) {
        return input.contains(" class ");
    }
}
