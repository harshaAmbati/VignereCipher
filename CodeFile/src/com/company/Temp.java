package com.company;

import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Temp {
    public static void main(String[] args) {
//        String time = "Thu Mar 26 18:48:04 IST 2015";
//        String time = le.getDateAndTime().toString();
//        String date = time.substring(time.indexOf(' ')+1,time.indexOf(':')-3);
//        System.out.println(date);
//        System.out.println(date.length());
//        String message = "abcdefghijklm";
//        int whichSlice = 0;
//        int totalSlices = 3;
//        String res = "";
//        for(int i=whichSlice;i<message.length();i+=totalSlices)
//        {
//            res+=message.substring(i,i+1);
//        }
//        System.out.println(res);
        HashSet<String> dict = new HashSet<>();
        ArrayList<Integer> arr = new ArrayList<>();
        int c=0;
        FileResource fr = new FileResource();
        for(String s:fr.lines())
        {
            dict.add(s);
        }
        FileResource fr1 = new FileResource();
        String www = fr1.asString();
//        for(String s:www.split("\\W+"))
//        {
////            System.out.println(s);
//            if(dict.contains(s) && s.length()!=1)
//            {
//                System.out.println(s);
//                c++;
//            }
//        }
//        System.out.println(c);
        for(int i=1;i<=5;i++)
        {
            c=0;
            VigenereBreaker vb = new VigenereBreaker();
            int key[] = vb.tryKeyLength(www,i,'e');
            VigenereCipher vc = new VigenereCipher(key);
            String story = vc.decrypt(www);
            for(String s:story.split("\\W+"))
            {
//            System.out.println(s);
                if(dict.contains(s) && s.length()!=1)
                {
                    System.out.println(s);
                    c++;
                }
            }
            System.out.println(c);
            System.out.println(story);
        }
    }
}
