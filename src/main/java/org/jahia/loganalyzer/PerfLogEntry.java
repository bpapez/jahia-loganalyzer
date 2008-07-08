package org.jahia.loganalyzer;

import java.util.Date;
import java.text.DateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Serge Huber
 * Date: 22 ao�t 2007
 * Time: 09:19:24
 * To change this template use File | Settings | File Templates.
 */
public class PerfLogEntry implements LogEntry {
    private Date logDate;
    private int pid = -1;
    private String language;
    private String user;
    private String cacheMode;
    private String engineName;
    private String url;
    private String esi;
    private String ipAddress;
    private long processingTime = 0;

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(String cacheMode) {
        this.cacheMode = cacheMode;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEsi() {
        return esi;
    }

    public void setEsi(String esi) {
        this.esi = esi;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String[] toStringArray(DateFormat dateFormat) {
        String[] result = new String[10];
        result[0] = dateFormat.format(logDate);
        result[1] = Integer.toString(pid);
        result[2] = language;
        result[3] = user;
        result[4] = cacheMode;
        result[5] = engineName;
        result[6] = esi;
        result[7] = ipAddress;
        result[8] = Long.toString(processingTime);
        result[9] = url;
        return result;
    }

    public String[] getColumnKeys() {
        String[] result = new String[10];
        result[0] = "date";
        result[1] = "pid";
        result[2] = "language";
        result[3] = "user";
        result[4] = "cacheMode";
        result[5] = "engineName";
        result[6] = "esi";
        result[7] = "ipAddress";
        result[8] = "processingTime";
        result[9] = "url";
        return result;
    }
    
}