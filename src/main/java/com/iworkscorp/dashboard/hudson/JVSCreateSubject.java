package com.iworkscorp.dashboard.hudson;


import org.openqa.selenium.By;

public class JVSCreateSubject {
    private final JVSReusableFunctions jvsReusableFunctions;

    public JVSCreateSubject() {
        this.jvsReusableFunctions = new JVSReusableFunctions();
    }

    public void createSubject(SmokeTest.UserInfo userInfo) throws Exception {
        ReusableFunctions.waitUntilElementExistsAndClick(By.id("cpanelForm:CreateSubjectCommandLink"));
        enterSsn(userInfo.ssn);
        enterFirstName(userInfo.fName);
        enterLastName(userInfo.lName);
        enterCity("Little Rock");
        enterState("Arkansas");
        enterGender("Male");
        enterMaritalStatus("Married");
        enterCategoryType("Academy");
        jvsReusableFunctions.selectDate(JVSReusableFunctions.MAJOR_TAB_PANEL + "dobCalender", "1");
        ReusableFunctions.waitUntilElementExistsAndClick(By.id(JVSReusableFunctions.MAJOR_TAB_PANEL + "jvsCreateSubjectCB"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.id("doCreateSubjectBtn"), 1500);
    }

    private void enterCategoryType(String categoryType) {
        jvsReusableFunctions.selectFromDropdown(JVSReusableFunctions.MAJOR_TAB_PANEL + "categoryTypeSelectMenu", categoryType);
    }

    private void enterMaritalStatus(String maritalStatus) {
        jvsReusableFunctions.selectFromDropdown(JVSReusableFunctions.MAJOR_TAB_PANEL + "maritalStatusSelectMenu", maritalStatus);
    }

    private void enterGender(String gender) {
        jvsReusableFunctions.selectFromDropdown(JVSReusableFunctions.MAJOR_TAB_PANEL + "genderSelectMenu", gender);
    }

    private void enterState(String state) {
        jvsReusableFunctions.selectFromDropdown(JVSReusableFunctions.MAJOR_TAB_PANEL + "birthUSStateSelectMenu", state);
    }

    private void enterCity(String city) {
        jvsReusableFunctions.clearAndEnterText(JVSReusableFunctions.MAJOR_TAB_PANEL + "birthUSCityInputText", city);
    }

    private void enterLastName(String lastName) {
        jvsReusableFunctions.clearAndEnterText(JVSReusableFunctions.MAJOR_TAB_PANEL + "lastNameInputText", lastName);
    }

    private void enterFirstName(String firstName) {
        jvsReusableFunctions.clearAndEnterText(JVSReusableFunctions.MAJOR_TAB_PANEL + "firstNameInputText", firstName);
    }

    private void enterSsn(String ssn) {
        jvsReusableFunctions.clearAndEnterText(JVSReusableFunctions.MAJOR_TAB_PANEL + "createSubjectSsn", ssn);
    }
}