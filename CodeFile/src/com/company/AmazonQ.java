package com.company;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
//longest decreasing sub sequence
public class AmazonQ {
    public static void main(String[] args) {
        String input = "BBAASSSRPPOCCBAAACBABA";
        HashMap<String,Integer> map = new HashMap<>();
        String res="";
        for(int i=1;i<input.length();i++)
        {
            if(input.charAt(i-1)>input.charAt(i) || input.charAt(i-1)==input.charAt(i))
            {
                res+=input.charAt(i-1);
//                System.out.println(res);
            }
            else
            {
                res=res+input.charAt(i-1);
                map.put(res,res.length());
                res = "";
            }
        }
        for(String s:map.keySet())
        {
            System.out.println(s+" "+map.get(s));
        }
    }
}
