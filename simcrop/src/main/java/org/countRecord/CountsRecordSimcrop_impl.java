package org.countRecord;

import org.example.Utility_Impl;
import org.example.Utility_Intr;

import java.io.IOException;
import java.util.Map;

public class CountsRecordSimcrop_impl extends Utility_Impl implements CountsRecordSimcrop_intr  {

    Utility_Intr util = new Utility_Impl();
    @Override
    public Map<String, Long> read_file_getcount(String source, String delimiter,String ignoreCase) throws IOException {
        Map<String, Long> countMap=null;
        try {
             countMap = util.getCountOccurrence("fileDir", delimiter, ignoreCase);

            if (countMap.size() > 0) {
                util.movePorcessedFile("fileDir", "moveFileDir");
            }
            return countMap;
        } catch (Exception exception) {

            exception.printStackTrace();
        }


        return  countMap ;
    }}
