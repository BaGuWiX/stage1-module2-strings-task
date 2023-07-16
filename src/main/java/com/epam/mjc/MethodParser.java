package com.epam.mjc;



import java.util.ArrayList;
import java.util.List;


public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        // Remove leading and trailing whitespaces from the signature string
        signatureString = signatureString.trim();

        String accessModifier = "";
        int startIndex = 0;
        int endIndex = signatureString.indexOf(' ');
        if (endIndex != -1) {
            accessModifier = signatureString.substring(startIndex, endIndex);
            signatureString = signatureString.substring(endIndex + 1).trim();
        }

        endIndex = signatureString.indexOf(' ');
        String returnType = signatureString.substring(startIndex, endIndex);
        signatureString = signatureString.substring(endIndex + 1).trim();

        endIndex = signatureString.indexOf('(');
        String methodName = signatureString.substring(startIndex, endIndex);
        signatureString = signatureString.substring(endIndex).trim();

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (signatureString.startsWith("(") && signatureString.endsWith(")")) {
            signatureString = signatureString.substring(1, signatureString.length() - 1);
            String[] argumentStrings = signatureString.split(",");
            for (String argumentString : argumentStrings) {
                argumentString = argumentString.trim();
                String[] parts = argumentString.split(" ");
                if (parts.length == 2) {
                    String argumentType = parts[0];
                    String argumentName = parts[1];
                    MethodSignature.Argument argument = new MethodSignature.Argument(argumentType, argumentName);
                    arguments.add(argument);
                }
            }
        }

        MethodSignature signature = new MethodSignature(methodName, arguments);
        signature.setAccessModifier(accessModifier);
        signature.setReturnType(returnType);
        return signature;
    }
}
