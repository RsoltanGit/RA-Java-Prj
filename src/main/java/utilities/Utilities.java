package utilities;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;

public class Utilities {

    /**
     * The Method gets the request and parameter and looks-up for the parameter's values within the request and returns an arrayList of all those values.
     * @param request {@link HttpServletRequest}
     * @param parameter {@link String}
     * @return arrayList {@link ArrayList}
     */
    public static ArrayList<Integer> extractParamListFromRequest(HttpServletRequest request, String parameter){
        ArrayList<Integer> arrayList = new ArrayList<>();
        String[] paramValues;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            if (StringUtils.equals(key, parameter)) {
                paramValues = request.getParameterValues(key);
                for (int counter = 0; counter < paramValues.length; counter ++) {
                    arrayList.add(Integer.valueOf(paramValues[counter]));
                }
            }
        }
        return arrayList;
    }

    /**
     * The Method gets the request and parameter and looks-up for the parameter's values within the request and returns a {@link Boolean} arrayList of all those values.
     * @param request {@link HttpServletRequest}
     * @param parameter {@link String}
     * @return arrayList {@link ArrayList<Boolean>}
     */
    public static ArrayList<Boolean> extractBooleanParamListFromRequest(HttpServletRequest request, String parameter){
        ArrayList<Boolean> arrayList = new ArrayList<>();
        String[] paramValues;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            if (StringUtils.equals(key, parameter)) {
                paramValues = request.getParameterValues(key);
                for (int counter = 0; counter < paramValues.length; counter ++) {
                    arrayList.add(Boolean.valueOf(paramValues[counter]));
                }
            }
        }
        return arrayList;
    }

    /**
     * The Method gets the request and parameter and looks-up for the parameter's values within the request and returns a {@link Float} arrayList of all those values.
     * @param request {@link HttpServletRequest}
     * @param parameter {@link String}
     * @return arrayList {@link ArrayList<Float>}
     */
    public static ArrayList<Float> extractFloatParamListFromRequest(HttpServletRequest request, String parameter){
        ArrayList<Float> arrayList = new ArrayList<>();
        String[] paramValues;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            if (StringUtils.equals(key, parameter)) {
                paramValues = request.getParameterValues(key);
                for (int counter = 0; counter < paramValues.length; counter ++) {
                    arrayList.add(Float.valueOf(paramValues[counter]));
                }
            }
        }
        return arrayList;
    }

    /**
     * The method takes the arrayList of {@link Integer} and returns an int array int[] of Integers.
     * @param arrayList {@link ArrayList<Integer>}
     * @return intArray of int[]
     */
    public static int[] arrayListOfIntegersToIntArray(ArrayList<Integer> arrayList) {
        int[] intArray = new int[arrayList.size()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = arrayList.get(i);
        }
        return intArray;
    }

    /**
     * The method takes the arrayList of {@link Integer} and returns an int matrix - a 2D matix - of Integers (int[][]).
     * @param arrayList {@link ArrayList<Integer>}
     * @param dimension1 int
     * @param dimension2 int
     * @return result int[][]
     */
    public static int[][] arrayListOfIntToIntMatrix(ArrayList<Integer> arrayList, int dimension1, int dimension2) {
        int[][] result = new int[dimension1][dimension2];
        int index = 0;
        for (int i = 0; i < dimension1; i++) {
            for (int j = 0; j < dimension2; j++) {
                result[i][j] = arrayList.get(index++);
            }
        }
        return result;
    }

    /**
     * The method takes the arrayList of {@link Float} and returns a float matrix - a 2D matix - of floats (float[][]).
     * @param arrayList {@link ArrayList<Float>}
     * @param dimension1 int
     * @param dimension2 int
     * @return result float[][]
     */
    public static float[][] arrayListOfFloatToFloatMatrix(ArrayList<Float> arrayList, int dimension1, int dimension2) {
        float[][] result = new float[dimension1][dimension2];
        int index = 0;
        for (int i = 0; i < dimension1; i++) {
            for (int j = 0; j < dimension2; j++) {
                result[i][j] = arrayList.get(index++);
            }
        }
        return result;
    }

    /**
     * The method takes the arrayList of {@link Boolean} and returns a boolean matrix - a 2D matix - of boolean (boolean[][]).
     * @param arrayList {@link ArrayList}
     * @param dimension1 int
     * @param dimension2 int
     * @return result boolean[][]
     */
    public static boolean[][] arrayListOfBooleanToBooleanMatrix2D(ArrayList arrayList, int dimension1, int dimension2) {
        boolean[][] result = new boolean[dimension1][dimension2];
        int index = 0;
        for (int i = 0; i < dimension1; i++) {
            for (int j = 0; j < dimension2; j++) {
                result[i][j] = (boolean) arrayList.get(index++);
            }
        }
        return result;
    }

    /**
     * The method takes the arrayList of {@link Integer} and returns an int matrix - a 3D matix - of Integers (int[][][]).
     * @param arrayList {@link ArrayList<Integer>}
     * @param dimension1 int
     * @param dimension2 int
     * @param dimension3 int
     * @return result int[][][]
     */
    public static int[][][] arrayListOfIntToIntMatrix3D(ArrayList<Integer> arrayList, int dimension1, int dimension2, int dimension3) {
        int[][][] result = new int[dimension1][dimension2][dimension3];
        int index = 0;
        for (int i = 0; i < dimension1; i++) {
            for (int j = 0; j < dimension2; j++) {
                for (int k = 0; k < dimension3; k++) {
                    result[i][j][k] = arrayList.get(index++);
                }
            }
        }
        return result;
    }
}
