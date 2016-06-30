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

        ArrayList<Build> demoBuilds = new ArrayList<Build>();
        Build DEMO_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_1/lastSuccessfulBuild/api/json");
        demoBuilds.add(DEMO_tag_1);
        Build DEMO_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_2/lastSuccessfulBuild/api/json");
        demoBuilds.add(DEMO_tag_2);
        Build DEMO_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_3/lastSuccessfulBuild/api/json");
        demoBuilds.add(DEMO_tag_3);

        Build mostRecentDemoBuild = findMostRecent(demoBuilds);


        ArrayList<Build> devList = new ArrayList<Build>();
        Build DEV_automation = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_automation/lastSuccessfulBuild/api/json");
        devList.add(DEV_automation);
        Build DEV_JUnit = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_run_JUnit/lastSuccessfulBuild/api/json");
        devList.add(DEV_JUnit);
        Build DEV_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_build_tag_1/lastSuccessfulBuild/api/json");
        devList.add(DEV_tag_1);
        Build DEV_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_build_tag_2/lastSuccessfulBuild/api/json");
        devList.add(DEV_tag_2);
        Build DEV_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_build_tag_3/lastSuccessfulBuild/api/json");
        devList.add(DEV_tag_3);


        findMostRecent(devList);


        ArrayList<Build> gatList = new ArrayList<Build>();
        Build Gat_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_1/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_1);
        Build Gat_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_2/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_2);
        Build Gat_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_3/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_3);

        findMostRecent(gatList);

        ArrayList<Build> qaList = new ArrayList<Build>();
        Build QA_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_1/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_1);
        Build QA_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_2/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_2);
        Build QA_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_3/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_3);

        findMostRecent(qaList);

        ArrayList<Build> uatList = new ArrayList<Build>();
        Build UAT_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_1/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_1));
        Build UAT_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_2/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_2));
        Build UAT_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_3/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_3));

        findMostRecent(uatList);

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
}
