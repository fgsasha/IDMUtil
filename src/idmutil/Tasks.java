/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idmutil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author o.nekriach
 */
public class Tasks {

    String bulkCreateRoles(Map<String, Map<String, String>> input, String primaryKey) {
        String output = "";
        ArrayList<String> outputArray = new <String>ArrayList();
        ArrayList<String> fields = new <String>ArrayList();
        Iterator<String> it = input.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            Map<String, String> map = input.get(key);
            if (fields.isEmpty()) {
                Iterator<String> itkey = map.keySet().iterator();
                while (itkey.hasNext()) {
                    fields.add(itkey.next());
                }
            }
            String items = Constants.ACTIONROLECREATION;

            Iterator itf = fields.iterator();
            while (itf.hasNext()) {
                String fieldKey = (String) itf.next();
                items = items.replace(fieldKey, map.get(fieldKey));
            }
            outputArray.add(items);
        }

        for (String s : outputArray) {
            output += s + " ";
        }
        output = Constants.CREATEROLETASK1 + " " + output + "" + Constants.CREATEROLETASK2;
        System.out.println(output);
        return output;
    }

    String bulkAssignRoles(Map<String, ArrayList<Map<String, String>>> input, String primarykey) {
        String output = "";
//        ArrayList<String> outputArray = new <String>ArrayList();
        ArrayList<String> fields = new <String>ArrayList();
        Iterator<String> it = input.keySet().iterator();
        // Cycle by primarykey
        while (it.hasNext()) {
            String key = it.next();
            ArrayList<Map<String, String>> arr = input.get(key);
            if (fields.isEmpty() && !arr.isEmpty()) {
                // Get csv fields list
                Iterator<String> itkey = arr.get(0).keySet().iterator();
                while (itkey.hasNext()) {
                    fields.add(itkey.next());
                }
            }
            // Create Search Filter for focuses
            String filter = "";
            String task="";
            for (int k = 0; k < arr.size(); k++) {
                Map<String, String> inmap = arr.get(k);
                String focus = inmap.get(Constants.FOCUS).trim();
                if (filter.isEmpty()) {
                    filter = "						  <q:equal>\n"
                            + "						     <q:matching>strictIgnoreCase</q:matching>\n"
                            + "							 <q:path>name</q:path>\n"
                            + "							 <q:value>" + focus + "</q:value>\n"
                            + "						  </q:equal>";
                } else {
                    filter = filter + "\n" + "						  <q:equal>\n"
                            + "						     <q:matching>strictIgnoreCase</q:matching>\n"
                            + "							 <q:path>name</q:path>\n"
                            + "							 <q:value>" + focus + "</q:value>\n"
                            + "						  </q:equal>";

                }

                

            
            task = Constants.ASSIGNTASK;

            Iterator itf = fields.iterator();
            while (itf.hasNext()) {
                String fieldKey = (String) itf.next();
                task = task.replace(fieldKey, inmap.get(fieldKey).trim());
            }
            
            }
            filter = "<q:or>\n" + filter + "</q:or>";
            task = task.replace(Constants.SEARCHFILTER, filter);
            if (output.isEmpty()) {
                output = task;
            } else {
                output = output + "\n" + task;
            }

        }
        output=Constants.OBJECTHEADER + "\n" + output + "\n" +Constants.OBJECTFOOTER;
        return output;
    }

}
