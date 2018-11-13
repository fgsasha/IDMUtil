/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idmutil;

/**
 *
 * @author o.nekriach
 */
public  class Constants {
static String INTENT ="default";
static String PROJECTROLEDESCRIPTION="projection only";
static String FOCUS="%FOCUS%";
static String FOCUSTYPE="%FOCUSTYPE%";
static String GROUPNAME="%GROUPNAME%";
static String FOLDERS="%FOLDERS%";
static String ROLETYPE="%ROLETYPE%";
static String OBJECTHEADER="<objects xmlns=\"http://midpoint.evolveum.com/xml/ns/public/common/common-3\"\n" +
"         xmlns:c=\"http://midpoint.evolveum.com/xml/ns/public/common/common-3\"\n" +
"         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
"		 xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
"         xmlns:q=\"http://prism.evolveum.com/xml/ns/public/query-3\">";
static String OBJECTFOOTER="</objects>";
// For more details see https://dyninno.atlassian.net/wiki/x/5ACZE
static String CREATEROLETASK1="<c:task xmlns=\"http://midpoint.evolveum.com/xml/ns/public/common/common-3\" xmlns:c=\"http://midpoint.evolveum.com/xml/ns/public/common/common-3\" xmlns:t=\"http://prism.evolveum.com/xml/ns/public/types-3\" xmlns:org=\"http://midpoint.evolveum.com/xml/ns/public/common/org-3\" xmlns:icfs=\"http://midpoint.evolveum.com/xml/ns/public/connector/icf-1/resource-schema-3\" xmlns:ri=\"http://midpoint.evolveum.com/xml/ns/public/resource/instance-3\" xmlns:s=\"http://midpoint.evolveum.com/xml/ns/public/model/scripting-3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:q=\"http://prism.evolveum.com/xml/ns/public/query-3\"><c:name>Create roles</c:name><c:extension><scext:executeScript xmlns:scext=\"http://midpoint.evolveum.com/xml/ns/public/model/scripting/extension-3\"><s:action><s:type>add</s:type></s:action><s:input>";
static String CREATEROLETASK2="</s:input></scext:executeScript></c:extension><c:ownerRef oid=\"00000000-0000-0000-0000-000000000002\"/><c:executionStatus>runnable</c:executionStatus><c:category>BulkActions</c:category><c:handlerUri>http://midpoint.evolveum.com/xml/ns/public/model/scripting/handler-3</c:handlerUri><c:recurrence>single</c:recurrence></c:task>";
// For Nextcloud AD resource
static String ADOID="bc9d21ce-62dd-46bc-0044-65c4296cbbb7";
static String ADSEARCHATTRIBUTE="ri:sAMAccountName";
static String ADROLEDISPLAYNAME="AD role "+GROUPNAME;
static String ADROLEDESCRIPTION="AD role to provide full access to "+FOLDERS+" folder and subfolders on nextcloud_ch";
static String ADROLEINDUCEMENT="<c:inducement><c:construction><c:resourceRef oid=\""+ADOID+"\" relation=\"org:default\" type=\"c:ResourceType\"><!-- Resource --></c:resourceRef><c:kind>account</c:kind><c:intent>"+INTENT+"</c:intent><c:association><c:ref>ri:group</c:ref><c:outbound><c:strength>strong</c:strength><c:expression><associationTargetSearch xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"c:SearchObjectExpressionEvaluatorType\"><filter><q:equal><q:path>declare namespace icfs='http://midpoint.evolveum.com/xml/ns/public/connector/icf-1/resource-schema-3'; declare namespace ri='http://midpoint.evolveum.com/xml/ns/public/resource/instance-3'; attributes/"+ADSEARCHATTRIBUTE+"</q:path><q:value>"+GROUPNAME+"</q:value></q:equal></filter><c:searchOnResource>true</c:searchOnResource></associationTargetSearch></c:expression></c:outbound></c:association></c:construction></c:inducement>";
static String ACTIONROLECREATION="<s:value xsi:type=\"c:RoleType\"><c:name>"+GROUPNAME+"</c:name><c:displayName>"+GROUPNAME+"</c:displayName><c:description>"+PROJECTROLEDESCRIPTION+"</c:description><c:roleType>"+ROLETYPE+"</c:roleType></s:value><s:value xsi:type=\"c:RoleType\"><c:name>role-"+GROUPNAME+"</c:name><c:displayName>"+ADROLEDISPLAYNAME+"</c:displayName><c:description>"+ADROLEDESCRIPTION+"</c:description><c:roleType>"+ROLETYPE+"</c:roleType>"+ADROLEINDUCEMENT+"</s:value>";
static String ACTIONCROUPREATION="<s:value xsi:type=\"c:RoleType\"><c:name>"+GROUPNAME+"</c:name><c:displayName>"+GROUPNAME+"</c:displayName><c:description>"+ADROLEDESCRIPTION+"</c:description><c:roleType>"+ROLETYPE+"</c:roleType></s:value>";
//Assign role to focus -Start
static String SEARCHFILTER="%SEARCHFILTER%";
static String ASSIGNTASK="<task>\n" +
"        <name>Assign "+FOCUSTYPE+" to "+GROUPNAME+"</name>\n" +
"		<extension>\n" +
"            <scext:executeScript xmlns:scext=\"http://midpoint.evolveum.com/xml/ns/public/model/scripting/extension-3\">\n" +
"                <s:search xmlns:s=\"http://midpoint.evolveum.com/xml/ns/public/model/scripting-3\">\n" +
"                    <s:type>c:"+FOCUSTYPE+"</s:type>\n" +
"                    <s:searchFilter>\n" +
"						"+SEARCHFILTER+"\n" +
"                    </s:searchFilter>\n" +
"                    <s:action>\n" +
"                        <s:type>assign</s:type>\n" +
"							<s:parameter>\n" +
"                            <s:name>role</s:name>\n" +
"							<c:value xsi:type=\"q:SearchFilterType\">\n" +
"                                <q:equal>								\n" +
"								<q:matching>strictIgnoreCase</q:matching>\n" +
"                                    <q:path>name</q:path>\n" +
"                                    <q:value>"+GROUPNAME+"</q:value>\n" +
"                                </q:equal>\n" +
"                            </c:value>\n" +
"						</s:parameter>\n" +
"                    </s:action>\n" +
"                </s:search>\n" +
"            </scext:executeScript>			\n" +
"		</extension>				\n" +
"        <ownerRef oid=\"00000000-0000-0000-0000-000000000002\"/>\n" +
"        <executionStatus>runnable</executionStatus>\n" +
"        <category>BulkActions</category>\n" +
"        <handlerUri>http://midpoint.evolveum.com/xml/ns/public/model/scripting/handler-3</handlerUri>\n" +
"        <recurrence>single</recurrence>\n" +
"    </task>";
//Assign role to focus -End

    public static void setINTENT(String INTENT) {
        Constants.INTENT = INTENT;
    }

    public static void setPROJECTROLEDESCRIPTION(String PROJECTROLEDESCRIPTION) {
        Constants.PROJECTROLEDESCRIPTION = PROJECTROLEDESCRIPTION;
    }

    public static void setFOCUS(String FOCUS) {
        Constants.FOCUS = FOCUS;
    }

    public static void setFOCUSTYPE(String FOCUSTYPE) {
        Constants.FOCUSTYPE = FOCUSTYPE;
    }

    public static void setGROUPNAME(String GROUPNAME) {
        Constants.GROUPNAME = GROUPNAME;
    }

    public static void setFOLDERS(String FOLDERS) {
        Constants.FOLDERS = FOLDERS;
    }

    public static void setROLETYPE(String ROLETYPE) {
        Constants.ROLETYPE = ROLETYPE;
    }

    public static void setOBJECTHEADER(String OBJECTHEADER) {
        Constants.OBJECTHEADER = OBJECTHEADER;
    }

    public static void setOBJECTFOOTER(String OBJECTFOOTER) {
        Constants.OBJECTFOOTER = OBJECTFOOTER;
    }

    public static void setCREATEROLETASK1(String CREATEROLETASK1) {
        Constants.CREATEROLETASK1 = CREATEROLETASK1;
    }

    public static void setCREATEROLETASK2(String CREATEROLETASK2) {
        Constants.CREATEROLETASK2 = CREATEROLETASK2;
    }

    public static void setADOID(String ADOID) {
        Constants.ADOID = ADOID;
    }

    public static void setADSEARCHATTRIBUTE(String ADSEARCHATTRIBUTE) {
        Constants.ADSEARCHATTRIBUTE = ADSEARCHATTRIBUTE;
    }

    public static void setADROLEDISPLAYNAME(String ADROLEDISPLAYNAME) {
        Constants.ADROLEDISPLAYNAME = ADROLEDISPLAYNAME;
    }

    public static void setADROLEDESCRIPTION(String ADROLEDESCRIPTION) {
        Constants.ADROLEDESCRIPTION = ADROLEDESCRIPTION;
    }

    public static void setADROLEINDUCEMENT(String ADROLEINDUCEMENT) {
        Constants.ADROLEINDUCEMENT = ADROLEINDUCEMENT;
    }

    public static void setACTIONROLECREATION(String ACTIONROLECREATION) {
        Constants.ACTIONROLECREATION = ACTIONROLECREATION;
    }

    public static void setSEARCHFILTER(String SEARCHFILTER) {
        Constants.SEARCHFILTER = SEARCHFILTER;
    }

    public static void setTASK(String ASSIGNTASK) {
        Constants.ASSIGNTASK = ASSIGNTASK;
    }


}
