package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Utility_Intr {

    public  String readConfig(String key) throws IOException;
    public String readFile(String FileName) throws IOException;
    public String readFile(String append, String inputDir) throws IOException;
    public String movePorcessedFile(String inputDir ,String outDir) throws IOException;
    public String getTime();
    public List<String> getSplit(String str , String delimiter);

    public  Map<String, Long> getCountOccurrence(String str, String delimiter) ;
    public  Map<String, Long> getCountOccurrence(String sourceFile, String delimiter,String ignoreCase) throws IOException;

    public boolean createFile(String dir , String filename ,String text) throws IOException;




}
