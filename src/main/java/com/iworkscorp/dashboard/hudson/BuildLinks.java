package com.iworkscorp.dashboard.hudson;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by jshih on 6/30/2016.
 */
public class BuildLinks {


    /*private static String baseURL = "https://office.iworkscorp.com/hudson/";
    private static String fullConsoleURLTail = "/lastBuild/consoleFull";*/

    public static Properties CONFIG = null;
    public static final String CONFIG_PATH = "//src//main//resources//";

    private static String[] Environments = {"Baseline", "DEMO", "DEV", "GAT", "QA", "UAT"};
    private static String[] BaseLine_BuildTags;
    private static String[] DEMO_BuildTags;
    private static String[] DEV_BuildTags;
    private static String[] GAT_BuildTags;
    private static String[] QA_BuildTags;
    private static String[] UAT_BuildTags;

    private static String[][] All_BuildTags;

    static ArrayList<String> BaseLineBuildLinks = new ArrayList<String>();
    static ArrayList<String> DemoBuildLinks = new ArrayList<String>();
    static ArrayList<String> DevBuildLinks = new ArrayList<String>();
    static ArrayList<String> GATBuildLinks = new ArrayList<String>();
    static ArrayList<String> QABuildLinks = new ArrayList<String>();
    static ArrayList<String> UATBuildLinks = new ArrayList<String>();

    static ArrayList<ArrayList<String>> All_Links_Array = new ArrayList<ArrayList<String>>();

    public static void populate_ALL_Links_Array(){
        All_Links_Array.add(BaseLineBuildLinks);
        All_Links_Array.add(DemoBuildLinks);
        All_Links_Array.add(DevBuildLinks);
        All_Links_Array.add(GATBuildLinks);
        All_Links_Array.add(QABuildLinks);
        All_Links_Array.add(UATBuildLinks);

    }


    public static void makeBuildLinks(){
        for(int i = 0; i < Integer.parseInt(CONFIG.getProperty("numEnvironments")); i++){
            //System.out.println(Environments[i]);
            makeLinks(Environments[i], All_BuildTags[i], All_Links_Array.get(i));

        }

    }

    public static void makeLinks(String environment, String[] tags, ArrayList<String> envBuildLinks){
        for(int i = 0; i < tags.length; i++){
            String URL = addEnvToLink(environment);
            URL = addBuildTagToLink(URL, tags[i]);
//            String successURL = addSuccess(URL);
//            String failURL = addFail(URL);
            String fullURL = addFullConsole(URL);
            envBuildLinks.add(fullURL);
//            envBuildLinks.add(successURL);
//            envBuildLinks.add(failURL);

        }
    }
    public static String addEnvToLink(String environment){
        return CONFIG.getProperty("baseURL") + "view/" + environment + "/job/";
    }

    public static String addBuildTagToLink(String URL, String buildtags){
        return URL + buildtags;
    }


    public static String addFullConsole(String URL){
        return URL + CONFIG.getProperty("fullConsoleURLTail");
    }


    public static void initialize() throws IOException {
        CONFIG = new Properties();
        FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
        CONFIG.load(fn);

        BaseLine_BuildTags = new String[]{CONFIG.getProperty("baseline_Tag_1"), CONFIG.getProperty("baseline_Tag_2"), CONFIG.getProperty("baseline_Tag_3")};
        DEMO_BuildTags = new String[]{CONFIG.getProperty("DEMO_Tag_1"), CONFIG.getProperty("DEMO_Tag_2"), CONFIG.getProperty("DEMO_Tag_3")};
        DEV_BuildTags = new String[]{CONFIG.getProperty("DEV_Tag_1"), CONFIG.getProperty("DEV_Tag_2"), CONFIG.getProperty("DEV_Tag_3"), CONFIG.getProperty("DEV_auto"), CONFIG.getProperty("DEV_Junit")};
        GAT_BuildTags = new String[]{CONFIG.getProperty("GAT_Tag_1"), CONFIG.getProperty("GAT_Tag_2"), CONFIG.getProperty("GAT_Tag_3")};
        QA_BuildTags = new String[]{CONFIG.getProperty("QA_Tag_1"), CONFIG.getProperty("QA_Tag_2"), CONFIG.getProperty("QA_Tag_3")};
        UAT_BuildTags = new String[]{CONFIG.getProperty("UAT_Tag_1"), CONFIG.getProperty("UAT_Tag_2"), CONFIG.getProperty("UAT_Tag_3")};

        All_BuildTags = new String[][]{BaseLine_BuildTags, DEMO_BuildTags, DEV_BuildTags, GAT_BuildTags, QA_BuildTags, UAT_BuildTags};
    }


    /*public void main(String args[]){
        populate_ALL_Links_Array();
        makeBuildLinks();
        for(String s: BaseLineBuildLinks){
            System.out.println(s);
        }
        for(String s: DemoBuildLinks){
            System.out.println(s);
        }
        for(String s: DevBuildLinks){
            System.out.println(s);
        }
        for(String s: GATBuildLinks){
            System.out.println(s);
        }
        for(String s: QABuildLinks){
            System.out.println(s);
        }
        for(String s: UATBuildLinks){
            System.out.println(s);
        }
    }*/
}





