package com.newway.morsecodetyper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * a class for morse code interpretation
 * @author wei
 *
 */
public class MorseCoder {
    private HashMap<Character,String> morseMap;
    //private String digits[];
    //private String alphabet[];
  
    public MorseCoder(){
        this.morseMap = new HashMap<Character,String>();
        //0 short, 1 long
        this.morseMap.put('0', "-----");
        this.morseMap.put('1',".----");
        this.morseMap.put('2',  "..---");
        this.morseMap.put('3',   "...--");
        this.morseMap.put('4',   "....-");
        this.morseMap.put('5',   ".....");
        this.morseMap.put('6',   "-....");
        this.morseMap.put('7',   "--...");
        this.morseMap.put('8',   "---..");
        this.morseMap.put('9',   "----.");
        this.morseMap.put('A',   ".-");
        this.morseMap.put('B',   "-...");
        this.morseMap.put('C',   "-.-.");
        this.morseMap.put('D',   "-..");
        this.morseMap.put('E',   ".");
        this.morseMap.put('F',   "..-.");
        this.morseMap.put('G',   "--.");
        this.morseMap.put('H',   "....");
        this.morseMap.put('I',   "..");
        this.morseMap.put('J',  ".---");
        this.morseMap.put('K',   "-.-");
        this.morseMap.put('L',   ".-..");
        this.morseMap.put('M',   "--");
        this.morseMap.put('N',   "-.");
        this.morseMap.put('O',   "---");
        this.morseMap.put('P',   ".--.");
        this.morseMap.put('Q',   "--.-");
        this.morseMap.put('R',   ".-.");
        this.morseMap.put('S',   "...");
        this.morseMap.put('T',   "-");
        this.morseMap.put('U',   "..-");
        this.morseMap.put('V',   "...-");
        this.morseMap.put('W',   ".--");
        this.morseMap.put('X',   "-..-");
        this.morseMap.put('Y',  "-.--");
        this.morseMap.put('Z',   "--..");
    }
    
    public List<String> getMorseCode(String text){
        List<String> list = new ArrayList<String>();
        for(int i=0; i<text.length();i++){
            Character c = text.charAt(i);
            c= c.toUpperCase(c);
            //ignore non-alphanumeric for now
            if (this.morseMap.containsKey(c) ){
                list.add(this.morseMap.get(c));
            }
        }
        return list;
    }
}
