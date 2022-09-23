package com.company;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String s:fr.lines())
        {
            records.add(WebLogParser.parseEntry(s)); // returns a LogEntry type data i.e LogEntry(ip, date, request, status, bytes) this gets stored in the records list
        }
    }
    public int countUniqueIps()
    {
        ArrayList<String> temp = new ArrayList<>();
        for(LogEntry le:records)
        {
            if(!temp.contains(le.getIpAddress()))
                temp.add(le.getIpAddress());
        }
        return temp.size();
    }

    public void printAllHigherThanNum(int num)
    {
        for(LogEntry le:records)
        {
            if(le.getStatusCode()>num)
                System.out.println(le);// it is same as le.toString()
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String> temp = new ArrayList<>();
        String dates = someday.substring(0,someday.indexOf(' '))+" "+someday.substring(someday.indexOf(' ')+1);

        for(LogEntry le:records)
        {
            String date = le.getDateAndTime().toString();
            if(date.indexOf(dates)>0)
            {
                if(!temp.contains(le.getIpAddress()))
                {
                    temp.add(le.getIpAddress());
                }
            }
        }
        return temp;
    }

    public int countUniqueIPsInRange(int num1,int num2)
    {
        HashSet<String> set = new HashSet<>();
        for(LogEntry le:records)
        {
            if(le.getStatusCode()>=num1 && le.getStatusCode()<=num2)
            {
                if(!set.contains(le.getIpAddress()))
                    set.add(le.getIpAddress());
            }
        }
        return set.size();
    }

    public HashMap<String,Integer> countVisitsPerIP()
    {
        HashMap<String,Integer> map = new HashMap<>();
        for(LogEntry le:records)
        {
            if(!map.containsKey(le.getIpAddress()))
                map.put(le.getIpAddress(),1);
            else
                map.put(le.getIpAddress(),map.get(le.getIpAddress())+1);
        }
        return map;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> map)
    {
        return Collections.max(map.values());
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> map)
    {
        ArrayList<String> arr = new ArrayList<>();
        int max = Collections.max(map.values());
        for(String s:map.keySet())
        {
            if(map.get(s)==max)
                arr.add(s);
        }
        return arr;
    }

    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(LogEntry le:records)
        {
//            Thu Mar 26 18:48:04 IST 2015
            String time = le.getDateAndTime().toString();
            String date = time.substring(time.indexOf(' ')+1,time.indexOf(':')-3);
            if(!map.containsKey(date))
            {
               addToMap(map,date,le);
            }
            else
            {
                append(map,date,le);
            }
        }
        return map;
    }

    public void addToMap(HashMap<String, ArrayList<String>> map,String d,LogEntry le)
    {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(le.getIpAddress());
        map.put(d,temp);
    }

    public void append(HashMap<String, ArrayList<String>> map,String d,LogEntry le)
    {
        ArrayList<String> temp = map.get(d);
        temp.add(le.getIpAddress());
        map.put(d,temp);
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map)
    {
        map = iPsForDays();
        int max=0;
        for(String s:map.keySet())
        {
            if(map.get(s).size()>max)
                max = map.get(s).size();
        }
        String res="";
        for(String s: map.keySet())
        {
            if(map.get(s).size()==max)
            {
                res+=s;
            }
        }
        return res;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map,String day)
    {
        ArrayList<String> res = new ArrayList<>();
        HashMap<String,Integer> temp = new HashMap<>();
        for(String s:map.keySet())
        {
            if(s.equals(day))
            {
                for(String s1:map.get(s))
                {
                    if(!temp.containsKey(s1))
                    {
                        temp.put(s1,1);
                    }
                    else
                    {
                        temp.put(s1,temp.get(s1)+1);
                    }
                }
            }
        }
        int max = Collections.max(temp.values());
        for(String s:temp.keySet())
        {
            if(temp.get(s)==max)
                res.add(s);
        }
        return res;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le.toString());
        }
    }
}
