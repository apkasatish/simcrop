import org.countRecord.CountsRecordSimcrop_impl;
import org.countRecord.CountsRecordSimcrop_intr;
import org.example.Utility_Impl;
import org.example.Utility_Intr;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test_Count_Occurrence_simCrop {

    static Utility_Intr  utils ;
    static CountsRecordSimcrop_intr  count_sim;


    //Given - Initliaze the requried object and prepared the test file for tes case
    @BeforeAll
    static void setup() {
           utils = new Utility_Impl();
           count_sim=new CountsRecordSimcrop_impl();
        System.out.println("@BeforeAll - executes once before all test methods in this class");
    }

    //When - String occurrence count with case senstive
    //Then - Should match expected vs actual
    @Test
    public void test_Count_Occurrence_withFile_ignoreCaseY() throws IOException {
        utils.createFile("fileDir","Test1","Go do that thing that you do so well");
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/Test1.txt"," ","Y");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",2l);
        expectedHmap.put("go",1l);
        expectedHmap.put("well",1l);
        expectedHmap.put("do",2l);
        expectedHmap.put("so",1l);
        expectedHmap.put("thing",1l);
        expectedHmap.put("you",1l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(expectedHmap.equals(actualHmap));
    }

    //When - String occurrence count with no case senstive
    //Then - Should match expected vs actual
    @Test
    public void test_Count_Occurrence_withFile_ignoreCaseN() throws IOException {
        utils.createFile("fileDir","Test2","Go do that thing that you do so well");
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/Test2.txt"," ","N");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",2l);
        expectedHmap.put("go",1l);
        expectedHmap.put("well",1l);
        expectedHmap.put("do",2l);
        expectedHmap.put("so",1l);
        expectedHmap.put("thing",1l);
        expectedHmap.put("you",1l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(!expectedHmap.equals(actualHmap));
    }

    //When - String occurrence count with asymmetric space in between text
    //Then - Should match expected vs actual
    @Test
    public void test_Count_Occurrence_withFile_withSpacew() throws IOException {
        utils.createFile("fileDir","Test3","go do       that thing that     you do so   well");
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/Test3.txt"," ","N");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",2l);
        expectedHmap.put("go",1l);
        expectedHmap.put("well",1l);
        expectedHmap.put("do",2l);
        expectedHmap.put("so",1l);
        expectedHmap.put("thing",1l);
        expectedHmap.put("you",1l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(expectedHmap.equals(actualHmap));
    }

    //When - String occurrence count with different Delimeter
    //Then - Should match expected vs actual
    @Test
    public void test_Count_Occurrence_withFile_differntDelimeter() throws IOException {
        utils.createFile("fileDir","Test4","go/do/that/thing/that/you/do/so/well");
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/Test4.txt","/","N");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",2l);
        expectedHmap.put("go",1l);
        expectedHmap.put("well",1l);
        expectedHmap.put("do",2l);
        expectedHmap.put("so",1l);
        expectedHmap.put("thing",1l);
        expectedHmap.put("you",1l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(expectedHmap.equals(actualHmap));
    }


    //When - String occurrence count with  multiple files in dir
    //Then - Should match expected vs actual
    @Test
    public void test_Count_Occurrence_withMultipleFiles() throws IOException, InterruptedException {
        utils.createFile("fileDir","MultiFile1"," go do that thing that you do so well");
        utils.createFile("fileDir","MultiFile2","go do that thing that you do so well");
        Thread.sleep(100);
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/"," ","N");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",4l);
        expectedHmap.put("go",2l);
        expectedHmap.put("well",2l);
        expectedHmap.put("do",4l);
        expectedHmap.put("so",2l);
        expectedHmap.put("thing",2l);
        expectedHmap.put("you",2l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(expectedHmap.equals(actualHmap));
    }

    //When - String occurrence count without file in dir
    //Then - Should not match expected vs actual
    @Test
    public void test_Count_Occurrence_withNoFiles() throws IOException, InterruptedException {
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/"," ","N");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",4l);
        expectedHmap.put("go",2l);
        expectedHmap.put("well",2l);
        expectedHmap.put("do",4l);
        expectedHmap.put("so",2l);
        expectedHmap.put("thing",2l);
        expectedHmap.put("you",2l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(!expectedHmap.equals(actualHmap));
    }


    //When - String occurrence count without  any content in file  (0 kb file)
    //Then - Should not match expected vs actual
    @Test
    public void test_Count_Occurrence_with_NoContenInFile() throws IOException {
        utils.createFile("fileDir","Test5","");
        Map<String ,Long> actualHmap= count_sim.read_file_getcount("src/main/resources/file/Test5.txt"," ","N");
        Map<String ,Long> expectedHmap= new HashMap<String ,Long>();
        expectedHmap.put("that",2l);
        expectedHmap.put("go",1l);
        expectedHmap.put("well",1l);
        expectedHmap.put("do",2l);
        expectedHmap.put("so",1l);
        expectedHmap.put("thing",1l);
        expectedHmap.put("you",1l);
        System.out.println("Expected Result --> " + expectedHmap);
        System.out.println("Actual Result   --> " + actualHmap);
        assertTrue(!expectedHmap.equals(actualHmap));
    }

    @AfterAll
    static void tearDown(){
    System.gc();
    }



}
