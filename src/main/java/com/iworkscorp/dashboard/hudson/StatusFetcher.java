package com.iworkscorp.dashboard.hudson;

import org.openqa.selenium.By;

import java.util.ArrayList;
import com.iworkscorp.dashboard.hudson.BuildLinks;

import static com.iworkscorp.dashboard.hudson.TestBase.driver;

/**
 * Created by tmadison on 6/30/16.
 */
public class StatusFetcher {


    String username = "avillaflor";
    String password = "avillaflor";
    ArrayList<Build> baselineBuilds = new ArrayList<Build>();
    ArrayList<Build> demoBuilds = new ArrayList<Build>();
    ArrayList<Build> devBuilds = new ArrayList<Build>();
    ArrayList<Build> gatBuilds = new ArrayList<Build>();
    ArrayList<Build> qaBuilds = new ArrayList<Build>();
    ArrayList<Build> uatBuilds = new ArrayList<Build>();

    public void logIn(){
        driver.navigate().to(BuildLinks.getBaseURL() + "login?from=%2Fhudson");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[text()='log in']"), 1500);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_username']"), username);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_password']"), password);
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='loginForm']//input[@id='loginButton']"), 2000);
    }
    public void connectToHudson() throws Exception {

        logIn();

       /* ArrayList<Build> baselineBuilds = new ArrayList<Build>();
        Build baseline_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastSuccessfulBuild/api/json");
        baselineBuilds.add(baseline_tag_1);
        Build baseline_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_2/lastSuccessfulBuild/api/json");
        baselineBuilds.add(baseline_tag_2);
        Build baseline_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_3/lastSuccessfulBuild/api/json");
        baselineBuilds.add(baseline_tag_3);

        Build mostRecentBaseLineBuild = findMostRecent(baselineBuilds);*/

        //ArrayList<Build> demoBuilds = new ArrayList<Build>();


        BuildLinks.populate_ALL_Links_Array();
        BuildLinks.makeBuildLinks();
        createAllBuilds();

        Build mostRecentDemoBuild = findMostRecent(demoBuilds);
/*        findMostRecent(devList);

        findMostRecent(gatList);

        findMostRecent(qaList);

        findMostRecent(uatList);*/

        System.out.println("dfa");
    }

    private Build findMostRecent(ArrayList<Build> buildList) {
        Build mostRecent = buildList.get(0);
        for (int buildListIdx = 0; buildListIdx < buildList.size(); buildListIdx++) {
            if (!mostRecent.isMostRecent(buildList.get(buildListIdx))) {
                mostRecent =buildList.get(buildListIdx);
            }
        }
        return mostRecent;
    }

    private void createAllBuilds(){
        //createBuildObjects(baselineBuilds, BuildLinks.BaseLineBuildLinks);
        createBuildObjects(demoBuilds, BuildLinks.DemoBuildLinks);
        createBuildObjects(devBuilds, BuildLinks.DevBuildLinks);
//        createBuildObjects(gatBuilds, BuildLinks.GATBuildLinks);
//        createBuildObjects(qaBuilds, BuildLinks.QABuildLinks);
//        createBuildObjects(uatBuilds, BuildLinks.UATBuildLinks);
    }

    private void createBuildObjects(ArrayList<Build> environmentBuilds, ArrayList<String> buildURLS){
        for(String buildURL: buildURLS){
            Build build = new Build(buildURL);
            environmentBuilds.add(build);
        }
    }
}
