package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Utility_Impl implements Utility_Intr{

    static Logger log = Logger.getLogger(Utility_Impl.class.getName());

    @Override
    public String readFile(String FileName) throws IOException {
        File file = new File(FileName);
        String fileContent = "";
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
            } catch (IOException e) {
             e.printStackTrace();
        }
        fileContent = contentBuilder.toString();

        log.info(" Consolidated file text -->  "  + fileContent);
        return fileContent;
    }

    @Override
    public String readFile(String append , String importDir) throws IOException {
        File f=null;
        String fileContent = "";
        StringBuilder contentBuilder = new StringBuilder();
        try {
             f = new File(readConfig(importDir).toString());
       }catch(Exception e){
            f = new File(importDir.toString());
             fileContent= readFile(importDir.toString());
            log.info(" Consolidated file text-->  "  + fileContent);
             return fileContent;
        }
        for(File ff : f.listFiles()){
            String fName =ff.getName();
            String filePath =ff.getAbsolutePath();
            contentBuilder.append(readFile(filePath)).append(append);

        }
            fileContent = contentBuilder.toString();

        log.info(" Consolidated file text -->  "  + fileContent);
        return fileContent;

    }

    @Override
    public String movePorcessedFile(String inputDir ,String outDir) throws IOException {

        File f = new File(readConfig(inputDir.toString()));
        String fileContent = "";
        StringBuilder contentBuilder = new StringBuilder();

        Path temp=null;

        for(File ff : f.listFiles()){
            String fName =ff.getName();
            String filePath =ff.getAbsolutePath();
            Path path = Paths.get(ff.getAbsolutePath());
            long bytes = Files.size(path);
            System.out.println(bytes);
            if(bytes>0 || !readFile(filePath).isBlank() || !readFile(filePath).isEmpty() ){

                log.info("Consider file names -->  "  +  ff.getName());
            temp = Files.move
                    (Paths.get(filePath),
                            Paths.get(readConfig(outDir)+"Count_Record_File_Proceed"+String.valueOf(getTime())+String.valueOf(Math.random())+"01.txt"));

        }
        }
        if(temp != null )
        {
            //  System.out.println("File renamed and moved successfully");
            log.info("File renamed and moved successfully");

            return "File renamed and moved successfully";

        }
        else
        {
            //System.out.println("Failed to move the file");
            log.info("Failed to move the file");
            return "Failed to move the file";
        }

    }

    @Override
    public String getTime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return dtf.format(now);
    }


    @Override
    public String readConfig(String key) throws IOException {
        FileReader reader=new FileReader("src/main/resources/config.properties");
        Properties p=new Properties();
        p.load(reader);
        log.info("readConfig -Properties file value for Key --> "+ key +"and value ---- > "  +  p.get(key).toString());
          return  p.get(key).toString();
    };
    public List<String> getSplit(String str , String delimiter){

       String [] ss=  str.split(delimiter);
       List<String> Listss =Arrays.asList(ss).stream().filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());


       return Listss;

    };

    @Override
    public Map<String, Long> getCountOccurrence(String text, String delimiter) {
        Map<String ,Long>countMap=null;
        countMap= getSplit(text,delimiter).stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        return countMap;
    };

    @Override
    public Map<String, Long> getCountOccurrence(String sourceFile, String delimiter, String ignoreCase) throws IOException {
        Map<String, Long> countMap = null;
        String text = readFile(" ", sourceFile);
        if(ignoreCase.equalsIgnoreCase("N")) {
            countMap =  getSplit(text, delimiter).stream().map(x->x.trim()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            return countMap;
        }else{
            countMap= getSplit(text,delimiter).stream().map(x->x.toLowerCase().trim().toString()).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
            return countMap;
        }



    }

    @Override
    public boolean createFile(String dir, String filename, String text) throws IOException {


        File file = new File(readConfig(dir.toString())+"/"+filename); //initialize File object and passing path as argument
        boolean result;
        try
        {
            result = file.createNewFile();  //creates a new file
            if(result)      // test if successfully created a new file
            {
                System.out.println("file created "+file.getCanonicalPath()); //returns the path string
                FileOutputStream fos=new FileOutputStream(file, true);
               // String str=sc.nextLine()+"\n";      //str stores the string which we have entered
                byte[] b= text.getBytes();       //converts string into bytes
                fos.write(b);           //writes bytes into file
                fos.close();            //close the file
                System.out.println("file saved.");
                log.info("File Create in dir --- > " + filename);

            }
            else
            {
                System.out.println("File already exist at location: "+file.getCanonicalPath());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();    //prints exception if any
        }


        return true;
    }




    ;

    public static void main(String[] args) throws IOException {
        Utility_Intr utils = new Utility_Impl();
       // System.out.println(f.movePorcessedFile("fileDir","moveFileDir"));
      //  System.out.println( utils.getCountOccurrence("Go do that thing that you do so well"," "));
        System.out.println( utils.createFile("fileDir","Test01","Tetetetetes jjjsjsjsj"));

    }


}
