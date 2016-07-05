package com.iworkscorp.dashboard.hudson;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static com.iworkscorp.dashboard.hudson.TestBase.driver;

/**
 * Created by tmadison on 6/30/16.
 */
public class StatusFetcher {



    ArrayList<Build> baselineBuilds = new ArrayList<Build>();
    ArrayList<Build> demoBuilds = new ArrayList<Build>();
    ArrayList<Build> devBuilds = new ArrayList<Build>();
    ArrayList<Build> gatBuilds = new ArrayList<Build>();
    ArrayList<Build> qaBuilds = new ArrayList<Build>();
    ArrayList<Build> uatBuilds = new ArrayList<Build>();
    ArrayList<Build> mostRecentBuilds = new ArrayList<Build>();

    public static Properties CONFIG = null;
    public static final String CONFIG_PATH = "//src//main//resources//";

    public void initialize() throws IOException {
        CONFIG = new Properties();
        FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
        CONFIG.load(fn);
    }

    public void logIn(){

        //System.out.println(CONFIG.getProperty("username"));
        driver.navigate().to(CONFIG.getProperty("baseURL") + "login?from=%2Fhudson");
        //driver.navigate().to(sbaseURL + "login?from=%2Fhudson");

        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[text()='log in']"), 1500);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_username']"), CONFIG.getProperty("username"));
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_password']"), CONFIG.getProperty("password"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='loginForm']//input[@id='loginButton']"), 2000);
    }
    public void connectToHudson() throws Exception {

        logIn();

        BuildLinks.initialize();
        BuildLinks.populate_ALL_Links_Array();
        BuildLinks.makeBuildLinks();
        createAllBuilds();


        Build mostRecentBaselineBuild = findMostRecentinArray(baselineBuilds);
        mostRecentBuilds.add(mostRecentBaselineBuild);

        Build mostRecentDemoBuild = findMostRecentinArray(demoBuilds);
        mostRecentBuilds.add(mostRecentDemoBuild);

        Build mostRecentDEVBuild = findMostRecentinArray(devBuilds);
        mostRecentBuilds.add(mostRecentDEVBuild);

        Build mostRecentGATBuild = findMostRecentinArray(gatBuilds);
        mostRecentBuilds.add(mostRecentGATBuild);

        Build mostRecentQABuild = findMostRecentinArray(qaBuilds);
        mostRecentBuilds.add(mostRecentQABuild);

        Build mostRecentUATBuild = findMostRecentinArray(uatBuilds);
        mostRecentBuilds.add(mostRecentUATBuild);

        for(Build b: mostRecentBuilds){
            System.out.println(b.builder + ":  " + b.dateBuiltFull);
        }

        //System.out.println("dfa");
    }

    private Build findMostRecentinArray(ArrayList<Build> buildList) {
        Build mostRecent = buildList.get(0);
        for (int buildListIdx = 0; buildListIdx < buildList.size(); buildListIdx++) {
            if (!mostRecent.isMostRecent(buildList.get(buildListIdx))) {
                mostRecent =buildList.get(buildListIdx);
            }
        }
        return mostRecent;
    }

    private void createAllBuilds(){
        createBuildObjects(baselineBuilds, BuildLinks.BaseLineBuildLinks);
        createBuildObjects(demoBuilds, BuildLinks.DemoBuildLinks);
        createBuildObjects(devBuilds, BuildLinks.DevBuildLinks);
        createBuildObjects(gatBuilds, BuildLinks.GATBuildLinks);
        createBuildObjects(qaBuilds, BuildLinks.QABuildLinks);
        createBuildObjects(uatBuilds, BuildLinks.UATBuildLinks);
    }

    private void createBuildObjects(ArrayList<Build> environmentBuilds, ArrayList<String> buildURLS){
        for(String buildURL: buildURLS){
            Build build = new Build(buildURL);
            environmentBuilds.add(build);
        }
    }
}
