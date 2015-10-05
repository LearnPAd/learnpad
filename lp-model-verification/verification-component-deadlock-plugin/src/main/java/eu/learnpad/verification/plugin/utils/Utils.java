/**
 * LearnPAd - Verification Component - Deadlock Check Plugin
 * 
 *  Copyright (C) 2015 Unicam
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Damiano Falcioni - Unicam <damiano.falcioni@gmail.com>
 */

package eu.learnpad.verification.plugin.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {
    public static String getUTCTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date());
    }
    
    public static Date stringToDate(String dateTime) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.parse(dateTime);
    }
    
    public static <T> T[] concatenate (T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
    
    public enum LogType{
        INFO, ERROR, DEBUG;
    }
    public static void log(Error e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log(sw.toString(), LogType.ERROR);
    }
    public static void log(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log(sw.toString(), LogType.ERROR);
    }
    public static void log(String message, LogType logType){
        try{
            String folderPath = Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(new File(folderPath).isDirectory())
                folderPath = folderPath.substring(0, folderPath.length()-1);
            String logFile = folderPath.substring(0, folderPath.lastIndexOf("/")+1) + "verification.log";
            //System.err.println("INFO: updated log file " + logFile);
            String callerClassName = new Exception().getStackTrace()[1].getClassName();
            IOUtils.writeFile(("\n"+logType.toString()+" "+getUTCTime()+" "+callerClassName+" \n"+message).getBytes(), logFile, true);
        }catch(Exception ex){ex.printStackTrace();}
    }
    
    public static int[][] generateBinaryMatrix(int num){
        int rows = (int) Math.pow(2, num);
        int[][] ret = new int[rows][num];
        for(int iRow=0;iRow<rows;iRow++)
            for(int iColumn=0;iColumn<num;iColumn++)
                ret[iRow][num-1-iColumn] = (iRow/((int) Math.pow(2, iColumn))) % 2;
        return ret;
    }

	/*
     // Gaussian elimination with partial pivoting
    public static double[] lsolve(double[][] A, double[] b) {
        final double EPSILON = 1e-10;
        int N  = b.length;

        for (int p = 0; p < N; p++) {

            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }
            }
            double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            double   t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (Math.abs(A[p][p]) <= EPSILON) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                double alpha = A[i][p] / A[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < N; j++) {
                    A[i][j] -= alpha * A[p][j];
                }
            }
        }

        // back substitution
        double[] x = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
    */
}
