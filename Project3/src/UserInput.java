import java.util.*;



public class UserInput {
      
    static Scanner input = new Scanner(System.in);
   
    
    // INPUT METHODS
    public static int getInt(){
        System.out.print("Input an int: ");
        int number = input.nextInt();
        while(checkInt(number) == false){
            System.out.println("Invalid input. Input an int: ");
            number = input.nextInt();
        }
        return number;
    }
    
    public static char getChar(){
        System.out.print("Input a character: ");
        char c = input.next().charAt(0);
//        while(checkChar(c) == false || c < 'A' || c < 'a' || c > 'Z' || c > 'z'){
//            System.out.println("Invalid input. Input a character in the alphebet: ");
//            c = input.next().charAt(0);
//        }
        return c;
    }
    
    
    public static double getDouble(){
        System.out.println("Input a double: ");
        double d = input.nextDouble();
        while(checkDouble(d) == false){
            System.out.println("Invalid input. Input a double:");
            d = input.nextDouble();
        }
        
        return d;
        
    }
    
    public static String getString(){
        System.out.println("Input a String: ");
        String s = input.nextLine();
        while(checkString(s) == false){
        	
//            System.out.println("Invalid input.");
            s = input.nextLine();
        }
        return s;
        
    }
    
    
    //OVERLOADED INPUT METHODS
    public static int getInt(int min, int max){
        System.out.println("Input a number from " + min + " - " + max + ": ");
        int number = input.nextInt();
        while(checkInt(number) == false || number < min || number > max){
            System.out.println("Invalid input. Input an int: ");
            number = input.nextInt();
        }
        return number;
    }
              
    
    public static char getChar(char min, char max){       // min char 'A', max char 'Z'
        System.out.println("Input a letter from " + min + " - " + max + ": ");
        char c = input.next().charAt(0);
        while(checkChar(c) == false || c > max || c < min){
            System.out.println("Invalid input. Input a character from " + min + " to " + max + ": ");
            c = input.next().charAt(0);
        }
        return c;
    }
    
                
    public static double getDouble(double min, double max){
        System.out.println("Input a double from " + min + " - " + max + ": ");
        double d = input.nextDouble();
        while(checkDouble(d) == false || d < min || d > max){
            System.out.println("Invalid input. Input a double from " + min + " - " + max + ": ");
            d = input.nextDouble();
        }
        return d;
        
    }
                
    public static String getString(int min, int max){      // min and max length
        System.out.print("Input a String from " + min + " to " + max + " letters:");
        String s = input.next();
        while(checkString(s) != true || (s.length() >= min && s.length() <= max) != true){
            System.out.println("Invalid input. Input a String with " + min + " to " + max + " letters: ");
            s = input.next();
        }
        return s;
        
    }
    
    //VALIDATOR METHODS
    public static boolean checkInt(int input){
        if (input == (int)input){
            return true;
        }
        else return false;
    }
    
    public static boolean checkDouble(double input){
        if (input == (double)input){
            return true;
        }
        else return false;
    }
    
    public static boolean checkChar(char input){
        if(input == (char)input){
            return true;
        }
        else return false;
        
    }
    
    public static boolean checkString(String input){
        if(input.equalsIgnoreCase((String)input) && input.isEmpty() == false){
            return true;
        }
        else return false;
    }
    
    /*
     * public static String getString(){
     * 	Scanner input = new Scanner(System.in);
     *  String temp = input.next();
     *  if((temp.isEmpty()) == false){
     *  	return temp;
     *  }
     * */
     
    
    
    
    
    
    
    
    //Just because it's nice having this method in my toolkit
    public static int randomWithRange(int min, int max){
           int range = (max - min) + 1;     
           return (int)(Math.random() * range) + min;
    }
    
    
    
}
