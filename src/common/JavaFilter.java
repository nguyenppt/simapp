package common;


import java.io.*;

// This class implements a filename filter that only allows
// files that have specific prefix and suffix

public class JavaFilter extends Object implements FilenameFilter
{
        String prefix="";
        String suffix="";
     public JavaFilter(String prefix,String suffix)
     {
             this.prefix=prefix;
             this.suffix=suffix;
     }

         /** Get file have specific prefix and suffix
     * @param   dir    the directory in which the file was found.
     * @param   name   the name of the file.
     * @return  <code>true</code> if and only if the name should be
     * included in the file list; <code>false</code> otherwise.
    */

     public boolean accept(File dir, String name)
     {
             name=name.toLowerCase();
      // Only return true for accept if the file contains filter string
       return (name.indexOf(prefix)==0 && name.endsWith(suffix));
     }
}
