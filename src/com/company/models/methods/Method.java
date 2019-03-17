package com.company.models.methods;

public class Method {
    String definition;
    String variables;
    String methodSignature;

    public Method(String input) {
        replaceMapDataStructureWithVariable(input);
        String[] defAndVars = divideMethodIntoDefAndVars(input);
        String def = defAndVars[0];
        String vars = defAndVars[1];
        this.definition = definitionInMVEL(def);
        this.variables = varsInMVEL(vars);
        this.methodSignature = formatMethodSignature(input);
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    private String definitionInMVEL(String methodSignature) {
        String[] split = methodSignature.split("\\s+");
        String methodName = split[split.length-1];
        return "def " + methodName;
    }
    
    private  void replaceMapDataStructureWithVariable(String input){
        input.replaceAll("([a-zA-Z])+(\\s)*(<)(\\s)*(.)+(>)", "variable");
    }

    protected  String[] divideMethodIntoDefAndVars(String input) {
        return input.split("\\(");
    }

    protected  String varsInMVEL(String varString) {
        String methodVars = "()";
        if (!varString.matches("^\\)(.)*")) {
            String[] vars = getDifferentVariables(varString);
            methodVars = variablesInMVEL(vars);
        }
        return methodVars;
    }

    private String formatMethodSignature(String input) {
        String methodSignature;
        if (input.contains("{")){
            methodSignature =  definition + variables + "{";
        }
        else {
            methodSignature =  definition + variables;
        }
        return methodSignature;
    }

    private  String variablesInMVEL(String[] vars) {
        String methodVars = "(";
        for (int i = 0; i < vars.length; i++) {
            String[] methodVarArray = vars[i].split(" ");
            if (methodVarArray.length >1)
                methodVars += methodVarArray[1] + ",";
        }
        if (methodVars.contains(")")) {
            methodVars = methodVars.replaceFirst("\\),*\\s*(\\{)*,*", "\\)");
        }
        else {
            methodVars += ")";
        }
        return methodVars;
    }

    private  String[] getDifferentVariables(String varString) {
        return varString.split(",\\s*");
    }
}
