package common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

/**
 * @author nghi.doan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DefineEncrypt {
        Random rd = new Random();
        private String sub_encryptedString="";
        private String sub_decryptedString="";
        private String [] data=
        {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "0",
                "-",
                "=",
                "!",
                "@",
                "#",
                "$",
                "%",
                "^",
                "&",
                "*",
                "(",
                ")",
                "_",
                "+",
                "{",
                "}",
                "[",
                "]",
                "|",
                ";",
                "'",
                " ",
                ",",
                "<",
                ".",
                ">",
                "?",
                "/",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z",
                "`",
                "~"
        };
        private String [] data1=
        {
                "a",
                "b",
                "c",
                "d",
                "e",
                "f",
                "g",
                "h",
                "i",
                "j",
                "k",
                "l",
                "m",
                "n",
                "o",
                "p",
                "q",
                "r",
                "s",
                "t",
                "u",
                "v",
                "w",
                "x",
                "y",
                "z",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "0",
                "-",
                "=",
                "!",
                "@",
                "#",
                "$",
                "%",
                "^",
                "&",
                "*",
                "(",
                ")",
                "_",
                "+",
                "{",
                "}",
                "[",
                "]",
                "|",
                "'",
                " ",
                ",",
                "<",
                ".",
                ">",
                "?",
                "/",
                "A",
                "B",
                "C",
                "D",
                "E",
                "F",
                "G",
                "H",
                "I",
                "J",
                "K",
                "L",
                "M",
                "N",
                "O",
                "P",
                "Q",
                "R",
                "S",
                "T",
                "U",
                "V",
                "W",
                "X",
                "Y",
                "Z",
                "`",
                "~"
        };
        private String defineEnc(String a){
                String result="";
                int i=0;
                int keyNum = 0;
                int keyNum1 = 0;
                boolean flag=false;
                for (i=0; i<data.length;i++){
                        if (a.equals(data[i])){
                                keyNum1 = rd.nextInt(91);
                                keyNum = i + keyNum1;
                                if (keyNum<10){
                                        result = "00" + keyNum + data1[keyNum1];
                                }else if(keyNum > 9 && keyNum <100){
                                        result = "0" + keyNum + data1[keyNum1];
                                }else if(keyNum > 99){
                                        result = keyNum + data1[keyNum1];
                                }
//				result = data1[i];
                                break;
                        }
                }
                return result;
        }
        private String defineDec(String a){
                String result="";
                int position = Integer.parseInt(a.substring(0,3));
                String strCipher = a.substring(3);
                int i=0;
                boolean flag=false;
                for (i=0; i<data1.length;i++){
                        if (strCipher.equals(data1[i])){
                                result = data[position - i];
//				result = data[i];
                                break;
                        }
                }
                return result;
        }

        public String encryptData(String encryptedString){
                int i=0;
                encryptedString.trim();
                String result_encryptData="";
                for(i=0;i<=encryptedString.length()-1;i++){
                        sub_encryptedString = encryptedString.substring(i,i+1);
                        result_encryptData = result_encryptData + defineEnc(sub_encryptedString);
                }
                return result_encryptData;
        }
        public String decryptData(String decryptedString){
                int i=0;
                String result="";
                decryptedString.trim();
                for(i=0; i<=decryptedString.length()- 4; i=i+4){
                        sub_decryptedString = decryptedString.substring(i,i+4);
                        result = result + defineDec(sub_decryptedString);
                }
                return result;
        }

}
