# TodoList
these are the 2 exercises

exercic1 :algorithme 

 public class Calculator {    
    private static int mItest1MinAndMax = 5;
    private static int mItest2Min = 0;
    private static int mItest3Min = 5;
    private static int mItest4Min = 10;
    private static int mItest2Max = 10;
    private static int mItest3Max = 20;
    private static int mItest4Max = 5;
    private static String mStest1Result = "5 ";
    private static String mStest2Result = "0 1 2 3 4 5 H 7 8 H S ";
    private static String mStest3Result = "5 H 7 8 H S 11 H 13 14 HS 16 17 H 19 S ";
  
 public static void main(String []args) {

       String functionResult1 = Calculator.displayMagicNumber(Calculator.mItest1MinAndMax, Calculator.mItest1MinAndMax);

       String functionResult2 = Calculator.displayMagicNumber(Calculator.mItest2Min, Calculator.mItest2Min);

       String functionResult3 = Calculator.displayMagicNumber(Calculator.mItest3Min, Calculator.mItest3Min);

       String functionResult4 = Calculator.displayMagicNumber(Calculator.mItest4Max, Calculator.mItest4Max);

       System.out.println("Min = " + Calculator.mItest1MinAndMax + ", Max = " + Calculator.mItest1MinAndMax + ", rSresult : " + functionResult1 + (functionResult1.equals(Calculator.mStest1Result) ? " OK" : " FAILED"));

       System.out.println("Min = " + Calculator.mItest2Min + ", Max = " + Calculator.mItest2Max + ", rSresult : " + functionResult2 + (functionResult2.equals(Calculator.mStest2Result) ? " OK" : " FAILED"));

       System.out.println("Min = " + Calculator.mItest3Min + ", Max = " + Calculator.mItest3Max + ", rSresult : " + functionResult3 + (functionResult3.equals(Calculator.mStest3Result) ? " OK" : " FAILED"));

       System.out.println("Min = " + Calculator.mItest4Min + ", Max = " + Calculator.mItest4Max + ", rSresult : " + (functionResult4 == null ? "OK: Returned null successfully because pImin > pImax" : "FAILED: should return null" ));
 
}

  
public static String displayMagicNumber(int pImin, int pImax)
   {
       String rSresult = "";
       if (pImin > pImax) {

           return null;
       }

       while (pImin <= pImax) {

           boolean isMultipleOf3 = pImin % 3 == 0 && pImin / 3 > 1;
           boolean isMultipleOf5 = pImin % 5 == 0 && pImin / 5 > 1;

           if (isMultipleOf3 && isMultipleOf5) {

               rSresult += "HS ";
           }
 else if (isMultipleOf3) {
               rSresult += "H ";
           }
 else if (isMultipleOf5) {
               rSresult += "S ";
           } else {
               rSresult += (Integer.toString(pImin)) + " ";
           }

           pImin++;

       }

      return rSresult;
   
}

}

