package sample;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {
    public static String regexChecker(String theRegex,String str2Check){

        Pattern CheckRegex = Pattern.compile(theRegex);
        Matcher regexMatcher = CheckRegex.matcher(str2Check);
        String op="";
        while(regexMatcher.find()){
            if(regexMatcher.group().length() !=0){
                //System.out.println(regexMatcher.group().trim());
                op=op+regexMatcher.group().trim();

            }
            //System.out.println("Start index:"+ regexMatcher.start());
            //System.out.println("End index:" + regexMatcher.end());
        }
        return op;
    }

    public static boolean stringChecker(String patternString,String line){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(line);
        boolean matches = matcher.matches();
        return matches;
    }
    public static boolean subNetCheck(String ip1,String ip2){
        int count=0;
        ip1=ip1.replace('.', ' ');
        ip2=ip2.replace('.', ' ');
        String [] s1=ip1.split(" ");
        String [] s2=ip2.split(" ");
        for(int i=0 ; i <3 ; i++){
            if(s1[i].equals(s2[i])){

                count++;
            }
        }
        if((count==3) && !s1[3].equals(s2[3])){
            return true;
        }
        else{
            return false;
        }
    }

    public static String Subnet(String ip){

        int count=0;
        ip=ip.replace('.', ' ');
        String [] s=ip.split(" ");
        String r=s[0]+"."+s[1]+"."+s[2]+"."+"0";
        return r;
    }

    public static long Timed(String t1, String t2) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(t1);
        Date date2 = format.parse(t2);
        long difference = date2.getTime() - date1.getTime();
        System.out.println(difference);
        return difference;
    }


}
