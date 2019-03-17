package com.company.models.variables;

public class Variable {
    public String name;
    public String value;

    //public int x;
    public static String variableSignature(String input) {
        replaceMapDataStructureWithVariable(input);
        String variableSignature = formatVariableSignature(input);
        return variableSignature;
    }

    private static void replaceMapDataStructureWithVariable(String input){
        input.replaceAll("([a-zA-Z])+(\\s)*(<)(\\s)*(.)+(>)", "variable");
    }

    private static String formatVariableSignature(String input) {
        String variableSignature;
        if(input.contains("=")){
           variableSignature =  convertVariableWithValueAssigned(input);
        }
        else{
           variableSignature = convertVariableWithNoValueAssigned(input);
        }
        return variableSignature;
    }

    private static String convertVariableWithNoValueAssigned(String input){
        Variable variable = new Variable();
        String[] inputStringInParts = input.split("(\\s)+");
        String variableName = inputStringInParts[inputStringInParts.length-1];
        variable.name = outputWithSemicolon(variableName);
        variable.value  = "";
        return variable.name + variable.value;
    }


    private static String convertVariableWithValueAssigned(String input) {
        Variable variable = new Variable();
        String[] inputStringInParts = input.split("=", 2);
        variable.name = varNameWithEqualSign(inputStringInParts[0]);
        variable.value = inputStringInParts[1];

        String outPut = variable.name + variable.value;
        return outPut;
    }

    private static String varNameWithEqualSign(String variableNameDef) {
        String[] varNameDefArray = variableNameDef.split("(\\s)+");
        String variableName = varNameDefArray[varNameDefArray.length-1];
        String output;
        if (varNameDefArray.length == 1){
            output = variableName + "=";
        }
        else {
            output=  "var " + variableName + "=";
        }
        return output;
    }

    private static String outputWithSemicolon(String variableName) {
        String output;
        if (variableName.contains(";")) {
            output=  "var " + variableName + "";
        }
        else {
            output = "var "+ variableName + ";";
        }
        return output;
    }
}
