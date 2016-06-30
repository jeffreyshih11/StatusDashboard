package com.iworkscorp.dashboard.hudson;

import java.util.ArrayList;

/**
 * Created by jshih on 6/30/2016.
 */
public class BuildLinks {

    private static final int numEnvironments = 6;
    private static String baseURL = "https://office.iworkscorp.com/hudson/";

    private static String success_json_URLTail = "/lastSuccessfulBuild/api/json";
    private static String fail_json_URLTail = "/lastFailedBuild/api/json";

    private static String[] Environments = {"Baseline", "DEMO", "DEV", "GAT", "QA", "UAT"};


    private static String[] BaseLine_BuildTags = {"App_build_and_deploy_baseline_build_tag_1", "App_build_and_deploy_baseline_build_tag_2", "App_build_and_deploy_baseline_build_tag_3"};
    private static String[] DEMO_BuildTags = {"App_build_and_deploy_demo_build_tag_1", "App_build_and_deploy_demo_build_tag_2", "App_build_and_deploy_demo_build_tag_3"};
    private static String[] DEV_BuildTags = {"App_build_and_deploy_dev_automation", "App_build_and_deploy_dev_run_JUnit", "App_build_and_deploy_dev_build_tag_1", "App_build_and_deploy_dev_build_tag_2", "App_build_and_deploy_dev_build_tag_3"};
    private static String[] GAT_BuildTags = {"App_build_and_deploy_qa_build_tag_1", "App_build_and_deploy_qa_build_tag_2", "App_build_and_deploy_qa_build_tag_3"};
    private static String[] QA_BuildTags = {"App_build_and_deploy_qa_build_tag_1", "App_build_and_deploy_qa_build_tag_2", "App_build_and_deploy_qa_build_tag_3"};
    private static String[] UAT_BuildTags = {"App_build_and_deploy_uat_build_tag_1", "App_build_and_deploy_uat_build_tag_2", "App_build_and_deploy_uat_build_tag_3"};

    private static String[][] All_BuildTags = {BaseLine_BuildTags, DEMO_BuildTags, DEV_BuildTags, GAT_BuildTags, QA_BuildTags, UAT_BuildTags};

    static ArrayList<String> BaseLineBuilds = new ArrayList<String>();
    static ArrayList<String> DemoBuilds = new ArrayList<String>();
    static ArrayList<String> DevBuilds = new ArrayList<String>();
    static ArrayList<String> GATBuilds = new ArrayList<String>();
    static ArrayList<String> QABuilds = new ArrayList<String>();
    static ArrayList<String> UATBuilds = new ArrayList<String>();

    static ArrayList<ArrayList<String>> All_Links_Array = new ArrayList<ArrayList<String>>();

    public static void populate_ALL_Links_Array(){
        All_Links_Array.add(BaseLineBuilds);
        All_Links_Array.add(DemoBuilds);
        All_Links_Array.add(DevBuilds);
        All_Links_Array.add(GATBuilds);
        All_Links_Array.add(QABuilds);
        All_Links_Array.add(UATBuilds);

    }

    public static String getBaseURL(){
        return baseURL;
    }

    public static void makeBuildLinks(){
        for(int i = 0; i < numEnvironments; i++){
            System.out.println(Environments[i]);
            makeLinks(Environments[i], All_BuildTags[i], All_Links_Array.get(i));

        }

    }

    public static void makeLinks(String environment, String[] tags, ArrayList<String> envBuildLinks){
        for(int i = 0; i < tags.length; i++){
            String URL = addEnvToLink(environment);
            URL = addBuildTagToLink(URL, tags[i]);
            String successURL = addSuccess(URL);
            String failURL = addFail(URL);

            envBuildLinks.add(successURL);
            envBuildLinks.add(failURL);

        }
    }
    public static String addEnvToLink(String environment){
        return baseURL + "view/" + environment + "/job/";
    }

    public static String addBuildTagToLink(String URL, String buildtags){
        return URL + buildtags;
    }

    public static String addSuccess(String URL){
        return URL + success_json_URLTail;
    }

    public static String addFail(String URL){
        return URL + fail_json_URLTail;
    }

    public static void main(String args[]){
        populate_ALL_Links_Array();
        makeBuildLinks();
        for(String s: BaseLineBuilds){
            System.out.println(s);
        }
        for(String s: DemoBuilds){
            System.out.println(s);
        }
        for(String s: DevBuilds){
            System.out.println(s);
        }
        for(String s: GATBuilds){
            System.out.println(s);
        }
        for(String s: QABuilds){
            System.out.println(s);
        }
        for(String s: UATBuilds){
            System.out.println(s);
        }
    }
}
