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



    ArrayList<Build> baselineBuilds;
    ArrayList<Build> demoBuilds;
    ArrayList<Build> devBuilds;
    ArrayList<Build> gatBuilds;
    ArrayList<Build> qaBuilds;
    ArrayList<Build> uatBuilds;

    public static Properties CONFIG = null;
    public static final String CONFIG_PATH = "\\src\\main\\resources\\";

    public void initialize() throws IOException {
        CONFIG = new Properties();
        FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
        CONFIG.load(fn);
    }

    /**
     * Logs into Hudson
     */
    public boolean logIn(){
        //System.out.println(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
        driver.navigate().to(CONFIG.getProperty("baseURL") + "login?from=%2Fhudson");


        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[text()='log in']"), 1500);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_username']"), CONFIG.getProperty("username"));
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_password']"), CONFIG.getProperty("password"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='loginForm']//input[@id='loginButton']"), 2000);

        return true;
    }

    /*
     * Logs out of Hudson
     */
    public void logOut(){
        driver.navigate().to(CONFIG.getProperty("baseURL") + "logout");
        driver.close();
    }

    public boolean collectFromHudson() throws Exception {

        if(logIn()) {

            BuildLinks.initialize();
            BuildLinks.populate_ALL_Links_Array();
            BuildLinks.makeBuildLinks();
            createAllBuilds();
        }

        /*for(Build b: mostRecentBuilds){
            System.out.println(b.environment + ":: " + b.builder + ":  " + b.dateBuiltFull);
        }*/

        logOut();
        return true;

        //System.out.println("dfa");
    }

    /**
     * Gets the most recent build from each environment
     */
    public ArrayList<Build> getAllMostRecents() {
        ArrayList<Build> mostRecentBuilds = new ArrayList<>();

        mostRecentBuilds.add(findMostRecentinArray(baselineBuilds));
        mostRecentBuilds.add(findMostRecentinArray(demoBuilds));
        mostRecentBuilds.add(findMostRecentinArray(devBuilds));
        mostRecentBuilds.add(findMostRecentinArray(gatBuilds));
        mostRecentBuilds.add(findMostRecentinArray(qaBuilds));
        mostRecentBuilds.add(findMostRecentinArray(uatBuilds));

        return mostRecentBuilds;

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
        baselineBuilds = createBuildObjects(BuildLinks.BaseLineBuildLinks);
        demoBuilds = createBuildObjects(BuildLinks.DemoBuildLinks);
        devBuilds = createBuildObjects(BuildLinks.DevBuildLinks);
        gatBuilds = createBuildObjects(BuildLinks.GATBuildLinks);
        qaBuilds = createBuildObjects(BuildLinks.QABuildLinks);
        uatBuilds = createBuildObjects(BuildLinks.UATBuildLinks);
    }

    /**
     * Creates build objects for each build tag
     * @param buildURLS
     */
    private ArrayList<Build> createBuildObjects(ArrayList<String> buildURLS){
        ArrayList<Build> environmentBuilds = new ArrayList<>();
        for(String buildURL: buildURLS){
            Build build = new Build(buildURL);
            environmentBuilds.add(build);
        }
        return environmentBuilds;
    }
}
