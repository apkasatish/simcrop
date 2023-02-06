package org.countRecord;

import java.io.IOException;
import java.util.Map;

public interface CountsRecordSimcrop_intr {



    public Map<String, Long> read_file_getcount(String source, String delimiter,String ignoreCase) throws IOException;
}
