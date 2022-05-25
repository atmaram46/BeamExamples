package com.java.examples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateTransform {

        public static boolean isValidDate(SimpleDateFormat formatComing, String input) {
            try {
                formatComing.parse(input);
                return true;
            } catch(ParseException e){
                return false;
            }
        }

        public static List<String> transformDateFormat(List<String> dates) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat format3 = new SimpleDateFormat("MM-dd-yyyy");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy][yyyy-MM-dd]");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            List<String> output = new ArrayList<>();
            for(String data: dates) {
                if(isValidDate(format, data)) {
                    Date date = null;
                    try {
                        date = formatter.parse(data);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    output.add(sdf.format(date));
                } else if(isValidDate(format2, data)) {
                    Date date = null;
                    try {
                        date = format2.parse(data);
                        System.out.println("Date:" + date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    output.add(sdf.format(date));
                } else if(isValidDate(format3, data)) {
                    Date date = null;
                    try {
                        date = format3.parse(data);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    output.add(sdf.format(date));
                }
            }
            return output;
        }

        public static void main(String[] args) {
            List<String> dates = transformDateFormat(Arrays.asList("2010/02/20", "19/12/2016", "11-18-2012", "20130720"));
            for(String date : dates) {
                System.out.println(date);
            }
        }

}
