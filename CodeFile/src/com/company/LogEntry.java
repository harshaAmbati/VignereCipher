package com.company;

import java.util.Date;

public class LogEntry {
    private String ipAddress;
    private Date dateAndTime;
    private String method;
    private int statusCode;
    private int noOfBytes;

    public LogEntry(String ipAddress,Date dateAndTime,String method,int statusCode,int noOfBytes)
    {
        this.ipAddress = ipAddress;
        this.dateAndTime = dateAndTime;
        this.method = method;
        this.statusCode = statusCode;
        this.noOfBytes = noOfBytes;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public Date getDateAndTime()
    {
        return dateAndTime;
    }

    public String getMethod()
    {
        return method;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public int getNoOfBytes()
    {
        return noOfBytes;
    }

    public String toString()
    {
        return ipAddress+" "+dateAndTime+" "+method+" "+statusCode+" "+noOfBytes;
    }

}
