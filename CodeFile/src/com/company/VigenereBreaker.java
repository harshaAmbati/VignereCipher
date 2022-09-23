package com.company;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices)
    {
        String res = "";
        for(int i=whichSlice;i<message.length();i+=totalSlices)
        {
            res+=message.substring(i,i+1);
        }
        return res;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0;i<klength;i++)
        {
            String out = sliceString(encrypted,i,klength);
            CaesarCracker obj = new CaesarCracker(mostCommon);
            key[i] = obj.getKey(out);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> set = new HashSet<>();
        for(String s:fr.lines())
        {
            set.add(s.toLowerCase());
        }
        return set;
    }

    public int countWords(String msg,HashSet<String> dict)
    {
        int c=0;
        for(String s:msg.split("\\W+"))
        {
            if(dict.contains(s))
            {
                c++;
            }
        }
        return c;
    }


    public char  mostCommonCharIn(HashSet<String> dict)
    {
        HashMap<Character,Integer> freqs = new HashMap<>();
        for(String s:dict)
        {
            for(int i=0;i<s.length();i++)
            {
                if(!freqs.containsKey(s.charAt(i)))
                {
                    freqs.put(s.charAt(i),1);
                }
                else
                {
                    freqs.put(s.charAt(i),freqs.get(s.charAt(i))+1);
                }
            }
        }
        int max = Collections.max(freqs.values());
        char ch='.';
        for(Character c:freqs.keySet())
        {
            if(freqs.get(c)==max)
                ch = c;
        }
        return ch;
    }

    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages)
    {
        HashMap<String,Integer> counts = new HashMap<>();
        for(String lang:languages.keySet())
        {
            String story = breakForLanguage(encrypted,languages.get(lang));
            counts.put(lang,countWords(story,languages.get(lang)));
        }

        int max = Collections.max(counts.values());
        String finalLang = "";
        for(String s:counts.keySet())
        {
            if(counts.get(s)==max)
            {
                finalLang+=s;
            }
        }
        String story=null;
        String crackedLang=null;
        for(String s:languages.keySet())
        {
            if(s.equals(finalLang))
            {
                crackedLang =s;
                story = breakForLanguage(encrypted,languages.get(s));
                break;
            }
        }
        char keyParam = mostCommonCharIn(languages.get(crackedLang));
        System.out.println(story);
        System.out.println("File cracked with "+crackedLang+" as Language"+getKeyForAllLang(encrypted,story,keyParam));
    }

    public String breakForLanguage(String enc, HashSet<String> dict)
    {
        ArrayList<Integer> set = new ArrayList<>();
        char ch = mostCommonCharIn(dict);
        for(int i=1;i<=100;i++)
        {
            int c=0;
            int keys[] = tryKeyLength(enc,i,ch);
            VigenereCipher vc = new VigenereCipher(keys);
            String story = vc.decrypt(enc);
            for(String s:story.split("\\W+"))
            {
                if(dict.contains(s))
                {
                    c++;
                }
            }
            set.add(c);
        }
//        for(Integer i:set)
//        {
//            System.out.print(i+",");
//        }
        int keyLength=0;
        int max= Collections.max(set);
        for(int i=0;i<set.size();i++)
        {
            if(set.get(i)==max)
            {
                keyLength = i+1;
                break;
            }
        }
//        System.out.println("keyLength is: "+keyLength);
//        System.out.println(max);
        int keys[] = tryKeyLength(enc,keyLength,ch);
        VigenereCipher vc = new VigenereCipher(keys);
        return vc.decrypt(enc);
    }


    public void breakVigenere ()
    {
        HashMap<String,HashSet<String>> dictionary = new HashMap<>();
        System.out.println("Please provide dictionaries to store words of their respective languages");
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            String name = f.getName();
            if(!dictionary.containsKey(name))
            {
                dictionary.put(name,readDictionary(fr));
            }
            System.out.println(name+".txt is successfully read");
        }
        System.out.println("All Dictionaries read!");
        System.out.println("Please select a story file");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
//        int[] keys = tryKeyLength(encrypted,4,'e'); //example
//        VigenereCipher vc = new VigenereCipher(keys);
//        System.out.println(vc.decrypt(encrypted));
//        FileResource fr2 = new FileResource();
//        HashSet<String> dict =  readDictionary(fr2);
//        System.out.println(breakForLanguage(encrypted.toLowerCase(),dict));
        breakForAllLangs(encrypted,dictionary);
    }

    public String getKeyForAllLang(String original,String output,char ch)
    {
        for(int i=1;i<=100;i++)
        {
            int keys[] = tryKeyLength(original,i,ch);
            VigenereCipher vc = new VigenereCipher(keys);
            String story = vc.decrypt(original);
            if(story.equals(output))
            {
                return Arrays.toString(keys);
            }
        }
        return "";
    }

}
