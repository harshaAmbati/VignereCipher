package com.company;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
//        LogAnalyzer obj = new LogAnalyzer();
//        obj.readFile("weblog2_log");
//        System.out.println(obj.countUniqueIps());
//        System.out.println(obj.uniqueIPVisitsOnDay("Sep 27"));
//        System.out.println(obj.countUniqueIPsInRange(200,299));
//        HashMap<String,Integer> q1 = obj.countVisitsPerIP();
//        System.out.println(obj.iPsMostVisits(q1));
//        HashMap<String,ArrayList<String>> q2 = obj.iPsForDays();
//        System.out.println(obj.dayWithMostIPVisits(q2));
//        System.out.println(obj.iPsWithMostVisitsOnDay(q2,"Sep 29"));
//        System.out.println(obj.mostNumberVisitsByIP(q1));
        System.out.println("********************************************************************************************");

//        LogAnalyzer obj = new LogAnalyzer();
//        obj.readFile("weblog3-short_log");
//        HashMap<String,Integer> q1 = obj.countVisitsPerIP();
//        System.out.println(obj.mostNumberVisitsByIP(q1));


//        System.out.println();



        System.out.println("********************************************************************************************");

////        obj.printAll();
//        HashMap<String,Integer> q1 = obj.countVisitsPerIP();
//        System.out.println(obj.mostNumberVisitsByIP(q1));
//        System.out.println(obj.iPsMostVisits(q1));
//        System.out.println(obj.countUniqueIps());
//        obj.printAllHigherThanNum(400);
//        System.out.println(obj.uniqueIPVisitsOnDay("Mar 24").size());
//        System.out.println(obj.countUniqueIPsInRange(300,399));
//        System.out.println(obj.countUniqueIPsInRange(400,500));
//        HashMap<String, ArrayList<String>> map = obj.iPsForDays();
//
//        for(String s:map.keySet())
//            System.out.println(s+"-"+map.get(s).toString());
//
//        System.out.println(obj.dayWithMostIPVisits(map));
//        System.out.println(obj.iPsWithMostVisitsOnDay(map,"Mar 17"));

        System.out.println("********************************************************************************************");
//        CaesarCipher cc = new CaesarCipher(3);
//        System.out.println(cc.encryptLetter('M'));
//        System.out.println(cc.decryptLetter('P'));
//        CaesarCracker c1 = new CaesarCracker();
//        int key = c1.getKey();
////        System.out.println(c1.decrypt("Pmgx Ug Db Hdep"));
//        System.out.println(key);
//        VigenereCipher vc = new VigenereCipher( new int[]{3,8,2,4});
//        System.out.println(vc.encrypt("Meet Me At Dawn"));
//        System.out.println(vc.decrypt("Pmgx Ug Db Hdep"));
        VigenereBreaker vb = new VigenereBreaker();
//        FileResource fr = new FileResource();
//        int[] arr = vb.tryKeyLength(fr.asString(),4,'e');
//        System.out.println(Arrays.toString(arr));
        vb.breakVigenere();


    }
}
