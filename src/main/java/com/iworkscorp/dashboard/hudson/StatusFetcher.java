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

    /**
     * Logs into Hudson
     */
    public void logIn(){
        driver.navigate().to(CONFIG.getProperty("baseURL") + "login?from=%2Fhudson");

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

        getAllMostRecents();

        for(Build b: mostRecentBuilds){
            System.out.println(b.environment + ":: " + b.builder + ":  " + b.dateBuiltFull);
        }

        //System.out.println("dfa");
    }

    /**
     * Gets the most recent build from each environment
     */
    private void getAllMostRecents() {

        mostRecentBuilds.add(findMostRecentinArray(baselineBuilds));
        mostRecentBuilds.add(findMostRecentinArray(demoBuilds));
        mostRecentBuilds.add(findMostRecentinArray(devBuilds));
        mostRecentBuilds.add(findMostRecentinArray(gatBuilds));
        mostRecentBuilds.add(findMostRecentinArray(qaBuilds));
        mostRecentBuilds.add(findMostRecentinArray(uatBuilds));

    }

    /**
     * Finds the most recent build given an array of build objects
     * @param buildList
     * @return
     */
    private Build findMostRecentinArray(ArrayList<Build> buildList) {
        Build mostRecent = buildList.get(0);
        for (int buildListIdx = 0; buildListIdx < buildList.size(); buildListIdx++) {
            if (!mostRecent.isMostRecent(buildList.get(buildListIdx))) {
                mostRecent =buildList.get(buildListIdx);
            }
        }
        return mostRecent;
    }

    /**
     * Creates build objects for all environments and build tags
     */
    private void createAllBuilds(){
        createBuildObjects(baselineBuilds, BuildLinks.BaseLineBuildLinks);
        createBuildObjects(demoBuilds, BuildLinks.DemoBuildLinks);
        createBuildObjects(devBuilds, BuildLinks.DevBuildLinks);
        createBuildObjects(gatBuilds, BuildLinks.GATBuildLinks);
        createBuildObjects(qaBuilds, BuildLinks.QABuildLinks);
        createBuildObjects(uatBuilds, BuildLinks.UATBuildLinks);
    }

    /**
     * Creates build objects for each build tag
     * @param environmentBuilds
     * @param buildURLS
     */
    private void createBuildObjects(ArrayList<Build> environmentBuilds, ArrayList<String> buildURLS){
        for(String buildURL: buildURLS){
            Build build = new Build(buildURL);
            environmentBuilds.add(build);
        }
    }
}
