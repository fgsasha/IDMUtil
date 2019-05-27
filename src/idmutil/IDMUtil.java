/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idmutil;

import hrdata.JiraEmployeesData;
import hrdata.util.FileUtil;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author o.nekriach
 */
public class IDMUtil {    

    private static String PROPERTIESFILE = "idmutil.properties";
    static String OUTPUTFILE = "/home/o.nekriach/Downloads/Tasks/automatictask.xml";
    static String INPUTFILE = "/home/o.nekriach/Downloads/Tasks/assign-users-to-group.csv";
    private static Properties prop;

    private void taskFromFile(String taskType, String primarykey, String filepath) throws IOException {
        FileUtil file = new hrdata.util.FileUtil();
        if (!file.fileExist(filepath)) {
            // TODO error output
        }
        BufferedReader br = file.readDataFromFile(filepath);

        Tasks task = new Tasks();
        switch (taskType) {
            case "CREATENEXTCLOUDGROUPSWITHROLES":
                Map<String, Map<String, String>> input = file.getCsvToMap(primarykey, br);
                String c = task.bulkCreateRoles(input, primarykey);
                file.writeStringToFile(OUTPUTFILE, c);
                System.out.println("CREATEROLES Task was created: " + OUTPUTFILE);
                break;
            case "CREATENEXTCLOUDGROUPS":
                Map<String, Map<String, String>> input3 = file.getCsvToMap(primarykey, br);
                Constants.setACTIONROLECREATION(Constants.ACTIONCROUPREATION);
                String c3 = task.bulkCreateRoles(input3, primarykey);
                file.writeStringToFile(OUTPUTFILE, c3);
                System.out.println("CREATEADGROUPS Task was created: " + OUTPUTFILE);
            break;
            case "CREATEGSUITEGROUPS":
                Map<String, Map<String, String>> input4 = file.getCsvToMap(primarykey, br);
                Constants.setACTIONROLECREATION(Constants.ACTIONCROUPREATION_SWISSDC);
                String c4 = task.bulkCreateRoles(input4, primarykey);
                file.writeStringToFile(OUTPUTFILE, c4);
                System.out.println("CREATEGSUITEGROUPS for GSUITE Task was created: " + OUTPUTFILE);
                break;
            case "ASSIGNROLE":
                Map<String, ArrayList<Map<String, String>>> input2 = file.getCsvToComplexMap(primarykey, br);
                String a = task.bulkAssignRoles(input2, primarykey);
                file.writeStringToFile(OUTPUTFILE, a);
                System.out.println("ASSIGNROLE Task was created: " + OUTPUTFILE);
                break;
            default:
                System.out.println("Wrong Task Type");
        }

    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Reading from " + PROPERTIESFILE);
        String inputParameter = null;
        try {
            inputParameter = args[0];
            if (!inputParameter.equalsIgnoreCase("-v")) {
                System.out.println("Hint: use -v to see startup parameters");
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            // Without output of file
        }

        prop = new Properties();
        InputStream input = null;
        input = new FileInputStream(PROPERTIESFILE);

        // load a properties file
        prop.load(input);
        //get the properties value

        String taskType = prop.getProperty("taskType", "ASSIGNROLE");
        String primaryKey = prop.getProperty("primaryKey", "%GROUPNAME%");
        String inputFileName = prop.getProperty("inputFileName", INPUTFILE);
        String outFileName = prop.getProperty("outFileName", OUTPUTFILE);
        OUTPUTFILE = outFileName;

        if (inputParameter != null && inputParameter.equalsIgnoreCase("-v")) {
            //Print properties value
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("############################# jsonparser.properties #################################");
            System.out.println("taskType: " + prop.getProperty("taskType"));
            System.out.println("primaryKey: " + prop.getProperty("primaryKey"));
            System.out.println("inputFileName: " + prop.getProperty("inputFileName"));
            System.out.println("outFileName: " + prop.getProperty("outFileName"));
            System.out.println("######################################################################################");
            System.out.println("OutputFile: " + OUTPUTFILE);
        }

        IDMUtil util = new IDMUtil();
        // util.taskFromFile("CREATEROLES", "%GROUPNAME%", "nextcloud-group-cut.csv");

        util.taskFromFile(taskType, primaryKey, inputFileName);
        //util.taskFromFile("ASSIGNROLE", "%GROUPNAME%", "/home/o.nekriach/Downloads/Tasks/bulkassign.csv");

        input.close();
    }
}
