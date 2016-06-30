package com.iworkscorp.dashboard.hudson;

import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.iworkscorp.dashboard.hudson.TestBase.driver;

/**
 * Created by tmadison on 6/30/16.
 */
public class StatusFetcher {
    public void connectToHudson() throws Exception {
        driver.navigate().to("https://office.iworkscorp.com/hudson/login?from=%2Fhudson");
        ReusableFunctions.waitUntilElementExistsAndClick(By.id("top-nav"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[text()='log in']"), 1500);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_username']"), "avillaflor");
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_password']"), "avillaflor");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='loginForm']//input[@id='loginButton']"), 2000);


       /* driver.navigate().to("https://office.iworkscorp.com/hudson/view/Baseline/");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("/*//*[@id= 'projectstatus']//[@class='sortheader']"));
        URL url = new URL("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastBuild/consoleFull");
        driver.navigate().to("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastSuccessfulBuild/api/json");
        String pageSource = driver.getPageSource();
        int revIndex = pageSource.indexOf("\"revision\":");
        String revisionNum = pageSource.substring(revIndex + 11, revIndex + 16);
        int dateIndex = pageSource.indexOf("id");
        String date = pageSource.substring(dateIndex + 3, pageSource.indexOf("keepLog") - 3);
        int userIndex = pageSource.indexOf("Started by user");
        String user = pageSource.substring(userIndex+ 16, pageSource.indexOf("userName") -3);*/


        ArrayList<Build> baselineList = new ArrayList<Build>();
        Build baseline_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastSuccessfulBuild/api/json");
        baselineList.add(baseline_tag_1);
        Build baseline_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_2/lastSuccessfulBuild/api/json");
        baselineList.add(baseline_tag_2);
        Build baseline_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_3/lastSuccessfulBuild/api/json");
        baselineList.add(baseline_tag_3);

        Build mostRecentBaseLine = baselineList.get(0);
        for (int baselineListIdx = 0; baselineListIdx < baselineList.size(); baselineListIdx++) {
            if (!mostRecentBaseLine.isMostRecent(baselineList.get(baselineListIdx))) {
                mostRecentBaseLine = baselineList.get(baselineListIdx);
            }
        }

        ArrayList<Build> demoList = new ArrayList<Build>();
        Build DEMO_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_1/lastSuccessfulBuild/api/json");
        demoList.add(DEMO_tag_1);
        Build DEMO_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_2/lastSuccessfulBuild/api/json");
        demoList.add(DEMO_tag_2);
        Build DEMO_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_3/lastSuccessfulBuild/api/json");
        demoList.add(DEMO_tag_3);

        Build mostRecentDEMO = demoList.get(0);
        for (int demoListIdx = 0; demoListIdx < demoList.size(); demoListIdx++) {
            if (!mostRecentDEMO.isMostRecent(demoList.get(demoListIdx))) {
                mostRecentDEMO = demoList.get(demoListIdx);
            }
        }


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


        Build mostRecentDEV = devList.get(0);
        for (int devListIdx = 0; devListIdx < devList.size(); devListIdx++) {
            if (!mostRecentDEV.isMostRecent(devList.get(devListIdx))) {
                mostRecentDEV = devList.get(devListIdx);
            }
        }


        ArrayList<Build> gatList = new ArrayList<Build>();
        Build Gat_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_1/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_1);
        Build Gat_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_2/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_2);
        Build Gat_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_3/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_3);

        Build mostRecentGAT = gatList.get(0);
        for (int gatListIdx = 0; gatListIdx < gatList.size(); gatListIdx++) {
            if (!mostRecentGAT.isMostRecent(gatList.get(gatListIdx))) {
                mostRecentGAT = gatList.get(gatListIdx);
            }
        }

        ArrayList<Build> qaList = new ArrayList<Build>();
        Build QA_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_1/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_1);
        Build QA_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_2/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_2);
        Build QA_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_3/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_3);

        Build mostRecentQA = qaList.get(0);
        for (int qaListIdx = 0; qaListIdx < qaList.size(); qaListIdx++) {
            if (!mostRecentQA.isMostRecent(qaList.get(qaListIdx))) {
                mostRecentQA = qaList.get(qaListIdx);
            }
        }

        ArrayList<Build> uatList = new ArrayList<Build>();
        Build UAT_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_1/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_1));
        Build UAT_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_2/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_2));
        Build UAT_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_3/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_3));

        Build mostRecentUAT = uatList.get(0);
        for (int uatListIdx = 0; uatListIdx < uatList.size(); uatListIdx++) {
            if (!mostRecentUAT.isMostRecent(uatList.get(uatListIdx))) {
                mostRecentUAT = uatList.get(uatListIdx);
            }
        }

        System.out.println("dfa");
    }
}
