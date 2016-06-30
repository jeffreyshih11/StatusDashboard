
package com.iworkscorp.dashboard.hudson;

public enum JVSProperties {
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
    //this is an example how to use properties
    SBProbeAgent_NY("//*[@id='isc_GH']/option[1]"),
    SBProbeAgent_PA("//*[@id='isc_GH']/option[2]"),

//~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
// Start writing new properties from Here: 

    // ********************** Creating USER variable *****************
    Logoff("j_id_17:logoff"),
    userSSN("//*[@id='cpanelForm:ssnInput']"),
    userSSNSearch("//*[@id='cpanelForm:searchUserJVSLink']"),
    smoListOptions("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul"),
    SMOOne("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    SMOTwo("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td"),
    SMOThree("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
    SMOFour("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[4]/table/tbody/tr/td"),
    SMOFive("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[5]/table/tbody/tr/td"),
    SMOSix("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[6]/table/tbody/tr/td"),
    SMOSeven("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[7]/table/tbody/tr/td"),
    SMOEight("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[8]/table/tbody/tr/td"),
    SMONine("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[9]/table/tbody/tr/td"),
    userSMOSelect("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul"),
    RightArrowToAssignSMO("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[2]/button[1]"),
    ArmySMO("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td"),
    userSecurityOfficer("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    userHierarchyManager("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[6]/table/tbody/tr/td"),
    userAccountManager("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[5]/table/tbody/tr/td"),
    userRoleSelect("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[2]/button[1]"),
    userSelectSave("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    SaveAction("//*[@id='majorTabPanel:saveUserDetailJvs']"),
    verifySaveMsg("//*[@id='maingrowl_container']/div/div/div[2]/span"),
    cancelSave("//*[@id='majorTabPanel:cancelUserDetailJvs']"),
    userID("//*[@id='majorTabPanel:regid']"),
    userPassword("//*[@id='majorTabPanel:regpwd']"),
    userPasswordConfirm("//*[@id='majorTabPanel:pwdconf']"),
    viewSmoDetailsText("majorTabPanel:jvsUserDetailSmosFieldSet_header"),
    RolesText("majorTabPanel:jvsUserDetailRolesFieldSet_header"),
    RolesTable("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul"),
    SecurityOfficerRole("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    RightArrowToAssignRole("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[2]/button[1]"),
    AssignedSORoleInDropDown("majorTabPanel:permMenu_label"),
    NoOptPerForSO("//*[@id='majorTabPanel:jvsUserDetailPermPickList']/tbody/tr/td[1]"),
    PermissionText("//*[@id='majorTabPanel:jvsUserDetailPermsFieldSet_header']"),
    viewPermissionDetails("//*[@id='majorTabPanel:jvsUserDetailPermsFieldSet_content']"),
    selectPermission("//*[@id='majorTabPanel:jvsUserDetailPermPickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    GenerateUserId("majorTabPanel:genId"),
    GeneratePassword("majorTabPanel:genpwd"),
    SaveBtnToCreateUser("majorTabPanel:saveUserDetailJvs"),
    JVSLeftPanel("//*[@id='pcnt1']"),
    addRoleToUser("//*[@id='majorTabPanel:jvsUserDetailPermPickList']/tbody/tr/td[2]/button[1]"),
    selectPermissionToRemove("//*[@id='majorTabPanel:jvsUserDetailPermPickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
    removePermission("//*[@id='majorTabPanel:jvsUserDetailPermPickList']/tbody/tr/td[2]/button[3]"),


    //************************** Create Subject Relationships Hetal******************************//

    //ClickonSubjectRelationshipsTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[4]/a"),
    ClickOnSMORelationshipTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[4]/a"),
    ClickonCreateNewCategoryButton("majorTabPanel:subjtabview:categoriesDataTable:j_id_yk"),
    CategoryTypeDropDown("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1']/div[3]/span"),
    CivilianEmployeeCategory("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']/div/ul/li[4]"),
    NextBtnId("categoryCreateForm:j_id_3cu"),
    SelectCheckBoxtoSkipWorkInfo("//*[@id='categoryCreateForm:j_id_3d5']/div[2]"),
    ClickonNextButtontoSaveCategory("//*[@id='categoryCreateForm:j_id_3ee']"),
    ClickOnCreateButtonToCreateCategory("//*[@id='categoryCreateForm:categorycreatesubmitbuttonid']"),
    EstablishNewRelationshipButton("//*[@id='majorTabPanel:subjtabview:relationshipTable:j_id_zc']"),
    ClickTheRadioButtonforCurrentSMORelationship("//*[@id='addRelationshipWizardForm:subjcategorytable:0:selcatval']/span[1]"),
    OwningButton("//*[@id='addRelationshipWizardForm:relationshiptypeselection']/div[1]/span"),
    ClickOnNextButtonToSaveOwingRelationShip("//*[@id='addRelationshipWizardForm:j_id_4f0']"),
    ClickOnSaveButton("//*[@id='addRelationshipWizardForm:j_id_4jc']"),
    SelectCSRReciprocity("//*[@id='majorTabPanel:subjtabview:csrRequestReciprocityAction']/span"),
    CommontsTextFieldOnCSRWindow("//*[@id='csrForm:j_id_3ey']"),


    //******************************************Common Portal Subject Tab Hetal***************************************************//

    SubjectSearchButton("cpanelForm:subjectQuickSearchActionId"),
    ViewSubjectsLink("//*[@id='cpanelForm:viewSubjectsJVSLink']"),
    VerifySubjectDetails("//*[@id='majorTabPanel']/ul/li[1]/a"),
    BasicInfoTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[1]/a"),
    SubjectActionsDropDown("majorTabPanel:subjtabview:subactions_button"),
    closeBtnOnNotification("majorTabPanel:closeNotificationjvs"),
    UnreadNotificationText("commPanel:undreadNotificationLink"),


//********************************************CSR-Reciprocity Window Hetal *******************************************************//   

    AgencyName("csrForm:agencyName"),
    InvServiceProvider("csrForm:investigationServiceProvider"),
    LocationOfInvestigation("csrForm:investigationLocation"),
    AddDocBtnOnCSRWindow("csrForm:j_id_3qg:j_id_3qz']"),
    DocumentUploaderTitle("//*[@id='documentUploaderDialogId_title']"),
    ChooseText("//*[@id='documentUploaderForm:fileUploadId']/div[1]"),
    DocName("documentUploaderForm:editAddDocumentMessagesDocName"),
    DocDescription("documentUploaderForm:editAddDocumentMessagesDocDescription"),
    DocTypeDropDownArrow("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType']/div[3]/span"),
    CAFWorkingPaperDoc("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[7]"),
    ChooseDocBtn("documentUploaderForm:fileUploadId_input"),
    AddDocBtnOnUploadWindow("documentUploaderForm:documentUploadAddButtonId"),
    CommentsTextAreaReci("csrForm:j_id_3r4"),
    SendBtnReciprocity("csrForm:sendCsr"),


    //*****************************************CSR-Expedite Hetal *****************************************************************//
    SelectCSRExpedite("//*[@id='majorTabPanel:subjtabview:csrExpediteProcessAction']/span"),
    CSRSubjectDetail("csrForm:subjectDetail_header"),
    FirstNameOnCSR("//*[@id='csrForm:csrSubjectStub']/tbody/tr[1]/td[1]/span"),
    LastNameOnCSR("//*[@id='csrForm:csrSubjectStub']/tbody/tr[1]/td[5]/span"),
    SSNOnCSR("//*[@id='csrForm:csrSubjectStub']/tbody/tr[1]/td[7]/span"),
    DOBOnCSR("//*[@id='csrForm:csrSubjectStub']/tbody/tr[2]/td[1]/span"),
    CSRCaseSummary("csrForm:caseSummary_header"),
    CaseIdOnCSR("//*[@id='csrForm:caseSummaryStub']/tbody/tr/td[1]/span"),
    ReqSMOOnCSR("//*[@id='csrForm:caseSummaryStub']/tbody/tr/td[3]/span"),
    CaseTypeOnCSR("//*[@id='csrForm:caseSummaryStub']/tbody/tr/td[5]/span"),
    CSRDocSec("//*[@id='csrForm:j_id_3sc_header']/span"),
    PriPrgmTxt("//*[@id='csrForm:j_id_3td_content']/table/tbody/tr[1]/td[1]/span"),
    PriorityPrgmDropDown("//*[@id='csrForm:CSRReqExpediteTargetPrioritySelectOneMenu']/div[3]/span"),
    AdHocExepeditePriPrgm("//*[@id='csrForm:CSRReqExpediteTargetPrioritySelectOneMenu_panel']/div/ul/li[4]"),
    CommentsTxt("//*[@id='csrForm:j_id_3td_content']/table/tbody/tr[2]/td[1]"),
    CommentsCSRInputArea("csrForm:j_id_3tk"),    //csrForm:j_id_3h1
    SendBtnExedite("csrForm:sendCsr"),
    UnreadNotificationLink("commPanel:undreadNotificationLink"),


// ********************** Creating SMO and Managing SMO Details *****************

    createSMO("//*[@id='cpanelForm:j_id_2s']"),
    smoNameText("//*[@id='majorTabPanel:nametext']"),
    smoNameIndex("//*[@id='majorTabPanel:nameindex']"),
    nextButton("//*[@id='majorTabPanel:j_id_f2']"),
    saveSMObutton("//*[@id='majorTabPanel:j_id_f3']"),
    viewSMOtree("//*[@id='cpanelForm:j_id_2q']"),
    smoActions("//*[@id='majorTabPanel:smoTree:0_0']/span/span[3]/div"),
    showSMOdetail("//*[@id='majorTabPanel:mitem2']/span"),
    changeSMOname("//*[@id='majorTabPanel:smoDetailTabId:j_id_1dz']"),
    smoNamePrefix("//*[@id='majorTabPanel:smoDetailTabId:prefixnameDetail']"),
    editSMOnameText("//*[@id='majorTabPanel:smoDetailTabId:nametextDetail']"),
    editSMOnameIndex("//*[@id='majorTabPanel:smoDetailTabId:nameindexDetail']"),
    saveSMOeditDetails("//*[@id='majorTabPanel:smoDetailTabId:j_id_1f9']"),


//************************INDHU***********************************************************


    // SET UP USER ROLES AND PERMISSIONS IN PORTAL
    SearchUser("cpanelForm:ssnInput"),
    SearchUserButton("cpanelForm:searchUserJVSLink"),
    EnterUserSSN("cpanelForm:ssnInput"),
    Searchbutton("cpanelForm:searchUserJVSLink"),
    SelectSMO("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[@data-item-label='AF']/table/tbody/tr/td"), //AF
    SelectRoleAccountManager("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='Account Manager']/table/tbody/tr/td"),
    SelectRoleHierarchyManager("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@ data-item-label='Hierarchy Manager']/table/tbody/tr/td"),
    SelectRoleSecurityOfficerStandard("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='Security Officer Standard']/table/tbody/tr/td"),
    SystemGeneratePassword("majorTabPanel:genpwd"),
    UserRegistrationID("majorTabPanel:regid"),
    SelectAFAct1("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
    SelectAFActFromRight("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
    ArrowRight1("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[2]/button[1]"),
    ArrowLeft1("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']/tbody/tr/td[2]/button[3]"),
    RoleSOStandardFromRight("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
    ArrowLeftt2("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[2]/button[3]"),
    RoleSOStandard("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    RoleAccountManagerFromRight("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
    RoleAccountManager("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
    RoleHierarchyManagerFromRight("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
    RoleHierarchyManager("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[@data-item-label='Hierarchy Manager']/ul/li[4]"),
    ArrowRight2("//*[@id='majorTabPanel:jvsUserDetailRolePickList']/tbody/tr/td[2]/button[1]"),
    SaveRole("majorTabPanel:saveUserDetailJvs"),

    //Left panel links
    QuickSubjectSearchButton("cpanelForm:subjectQuickSearchActionId"),
    QuickSubjectInput("cpanelForm:pinp"),
    viewsubjects("cpanelForm:viewSubjectsJVSLink"),
    viewcurrentsmolink("cpanelForm:j_id_2w"),
    ViewSMOTreeLink("cpanelForm:j_id_2x"),

    //CREATE ORGANIZATION BY TYPES
    CreateOrganizationLinkInLeftPanel("cpanelForm:CreateOrganizationCommandLink"),
    OrganizationTypeDropDown("majorTabPanel:COTD_label"),
    OrganizationNameInput("majorTabPanel:CONa"),
    AddressLine1Input("majorTabPanel:crOrgStr1"),
    AddressLine2Input("majorTabPanel:crOrgStr2"),
    AddressLine3Input("majorTabPanel:crOrgStr3"),
    CityInput("majorTabPanel:crOrgCity"),
    ZipCodeInput("majorTabPanel:createOrgZipCode1"),
    ZipExtensionInput("majorTabPanel:createOrgZipCode2"),
    OrganizationStateDropDown("majorTabPanel:crOrgStat_label"),
    OrganizationCountryDropDown("majorTabPanel:cOCu0_label"),


    //#1. CONTRACTING ORGANIZATION OR VENDOR

    OrganizationType_ContractingOrgORVendor("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@data-label='Contracting Organization or Vendor']"),
    FacilityClearanceDropDown("majorTabPanel:COCC_label"),
    FacilityClearance_Confidential("//*[@id='majorTabPanel:COCC_panel']/div/ul/li[@data-label='Confidential']"),
    FacilityClearance_None("//*[@id='majorTabPanel:COCC_panel']/div/ul/li[@data-label='None']"),
    FacilityClearance_Secret("//*[@id='majorTabPanel:COCC_panel']/div/ul/li[@ data-label='Secret']"),
    FacilityClearance_TopSecret("//*[@id='majorTabPanel:COCC_panel']/div/ul/li[@data-label='Top Secret']"),

    FacilityClearanceStatusDropDown("//*[@id='majorTabPanel:COFCC3_label']"),
    FCLStatus_Active("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@ data-label='ACTIVE']"),
    FCLStatus_Dormant("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@data-label='DORMANT']"),
    FCLStatus_InProcess("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@data-label='INPROCESS']"),
    FCLStatus_Interim("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@data-label='INTERIM']"),
    FCLStatus_Invalid("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@data-label='INVALID']"),
    FCLStatus_Revoked("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@data-label='REVOKED']"),
    FCLStatus_Suspended("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@data-label='SUSPENDED']"),
    FCLStatus_Terminated("//*[@id='majorTabPanel:COFCC3_panel']/div/ul/li[@ data-label='TERMINATED']"),
    CAGECodeInput("majorTabPanel:cOrgCa"),
    FacilityClearanceIssueDateCalendar("//*[@id='majorTabPanel:COFCID']/button"),
    FacilityClearanceStatusDate("//*[@id='majorTabPanel:coFCSD']/button"),
    HomeOfficeCAGECodeInput("majorTabPanel:cOrgCC"),

    //#2. DoD CIVILIAN AGENCY
    OrganizationType_DoDCivA("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@data-label='DoD Civilian Agency']"),
    USAgencyCodeInput("majorTabPanel:cOrgGA"),
    ServiceOrganizationCodeInput("majorTabPanel:cOrgSO"),

    //#3. JOINT SERVICE ACTIVITY
    OrganizationType_JointServiceActivity("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@data-label='Joint Service Activity']"),
    USJointOrganizationCodeDropDown("majorTabPanel:cOrgJO_label"),
    JointOrgCode_USAfricaCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Africa Command']"),
    JointOrgCode_USCentralCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Central Command']"),
    JointOrgCode_USEuropeanCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. European Command']"),
    JointOrgCode_USJointForcesCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Joint Forces Command']"),
    JointOrgCode_USNorthernCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Northern Command']"),
    JointOrgCode_PacificCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Pacific Command']"),
    JointOrgCode_USSouthernCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@ data-label='U.S. Southern Command']"),
    JointOrgCode_USSpecialOperationsCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Special Operations Command']"),
    JointOrgCode_USStrategicCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@data-label='U.S. Strategic Command']"),
    JointOrgCode_USTransportationCommand("//*[@id='majorTabPanel:cOrgJO_panel']/div/ul/li[@ data-label='U.S. Transportation Command']"),

    //#4. NON-FEDERAL GOVERNMENT ORGANIZATION
    OrganizationType_NonFederalGovtOrg("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@data-label='Non Federal Government Organization']"),
    NonUSAgencyCodeInput("majorTabPanel:cOrgNU"),

    //#5. OTHER FEDERAL AGENCY
    OrganizationType_OtherFederalAgency("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@ data-label='Other Federal Agency']"),
    USAgencyCodeInputField("majorTabPanel:cOrgGA"),


    //#6. UNIFORMED SERVICES
    OrganizationType_UniformedService("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@ data-label='Uniformed Service']"),
    UnitIdentificationCodeInput("majorTabPanel:cOrgCo"),

    // #7. UNKNOWN
    OrganizationType_Unknown("//*[@id='majorTabPanel:COTD_panel']/div/ul/li[@data-label='Unknown']"),


    //CREATE SMO
    CreateSMOLinkInLeftPanel("cpanelForm:jvsPortalCreateSmoLink"),

    SelectOrganizationTypeDropDown("//*[@id='majorTabPanel:j_id_ex']/div[3]"),
    Select_ContractingOrganizationOrVendor("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@ data-label='Contracting Organization or Vendor']"),
    Select_DoDCivilianAgency("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@ data-label='DoD Civilian Agency']"),
    Select_JointServiceActivity("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@data-label='Joint Service Activity']"),
    Select_NonFederalGovtOrg("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@data-label='Non Federal Government Organization']"),
    Select_otherFederalAgency("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@ data-label='Other Federal Agency']"),
    Select_UniformedService("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@ data-label='Uniformed Service']"),
    Select_Unknown("//*[@id='majorTabPanel:j_id_ex_panel']/div/ul/li[@ data-label='Unknown']"),

    OrganizationDropDown("//*[@id='majorTabPanel:j_id_f0']/div[3]/span"),
    ActivationDateCalendar("//*[@id='majorTabPanel:j_id_fj']/button"),
    SMONamePrefixInput("majorTabPanel:prefixname"),
    SMONameTextInput("majorTabPanel:nametext"),
    SMONameIndexInput("majorTabPanel:nameindex"),
    NextButton("majorTabPanel:jvsCreateSMONextButton"),
    CreateSMO_AddLocationButton("majorTabPanel:CreateSmoLocTable:jvsCreateSMOLOCAddLocationButton"),
    SMOLocation_CountryDropDown("majorTabPanel:creason2_label"),
    SMOLocation_StreetInput("majorTabPanel:cstreet"),
    SMOLocationStreet2Input("majorTabPanel:cstreet2"),
    SMOLocation_CityInput("majorTabPanel:ccity"),
    SMOLocation_StateDropDown("majorTabPanel:cstate_label"),
    SMOLocation_ZipCodeInput("majorTabPanel:czipcode"),
    SMOLocationZipCodeExtensionInput("majorTabPanel:czipcodeextensionmajorTabPanel:czipcodeextension"),
    SaveLocationButtonInWindow("majorTabPanel:jvsCreateSMOLOCSaveLocationButton"),
    NextButtonInContactInfoSubTab("majorTabPanel:jvsCreateSMONextButton"),
    ConfirmationSaveSMOButton("majorTabPanel:jvsCreateSaveSMOButton"),


    //VIEW CURRENT SMO_ADD LOCATION
    AddLocationButton("majorTabPanel:smoDetailTabId:SmoLocTable:j_id_1iq"),
    AddSMOLocationStreet("majorTabPanel:smoDetailTabId:street"),
    AddSMOLocationCity("majorTabPanel:smoDetailTabId:city"),
    AddSMOLocationStateDropdown("//*[@id='majorTabPanel:smoDetailTabId:state']/div[3]/span"),
    AddSMOZip("majorTabPanel:smoDetailTabId:zipcode"),
    AddSMOZipExt("majorTabPanel:smoDetailTabId:zipcodeextension"),
    AddSMOCountryDropdown("//*[@id='majorTabPanel:smoDetailTabId:reason2']/div[3]/span"),
    SaveLocationButtonforSMO("majorTabPanel:smoDetailTabId:j_id_1je"),
    SMOLocationDeleteTrash("majorTabPanel:smoDetailTabId:SmoLocTable:0:j_id_1io"),
    LocDeleteConfirmation("//*[@id='majorTabPanel:smoDetailTabId:deleteLOCConfirmation1']/div[1]"),
    DeleteYesForLoc("majorTabPanel:smoDetailTabId:confirmdeleteloc"),
    CancelButtonAddSMO("majorTabPanel:smoDetailTabId:j_id_1jf"),
    AddSMOLocationStreet2("majorTabPanel:smoDetailTabId:street2"),

    //VIEW CURRENT SMO_EDIT POC_ADD POC
    SMOPOCEditPencil(""),
    SMOPhoneEdit(""),
    SMOEmailEdit(""),
    SMOTypeDropdown(""),
    SOFromDropDown(""),
    AdminFromDropDown(""),
    SMOPOCEditGreenCheck(""),
    SMOPointOfContactUpdatedGrowler(""),
    SMOPOCEditRedX(""),
    SMOPOCTrash(""),
    SMOPOCDeleteYESButton(""),
    PrimaryPOCDeleteMessage(""),
    POCDeleteConfirmation(""),
    GeneralInformation(""),
    AddPOCButton(""),
    SMOPOCDialogWindowOpen(""),
    AddPOCCheck(""),
    AddPOCTypeDropdown(""),
    AddPOCPhone(""),
    AddPOCEmail(""),
    SavePOCsButtonForSMO(""),
    DeletePOCTrash(""),
    DeleteYesForPOC(""),


    //SEARCH SMO
    SearchBySMOName("majorTabPanel:securityManagementOfficeName"),
    SearchSMOButton("majorTabPanel:ajaxsearchsmo"),
    ResetButton("majorTabPanel:ajaxclearcsearchsmo"),
    SearchByCAGECode("majorTabPanel:cagecode"),
    SearchByJointOrganizationCode("majorTabPanel:usjointorganizationcode"),
    SearchByServiceOrganizationCode("majorTabPanel:serviceorganizationcode"),
    SearchByUIC("majorTabPanel:unitidentificationcode"),
    SearchByUSGovtAgencyCode("majorTabPanel:usgovernementagencycode"),
    SearchByNonUSGovtAgencyCode("majorTabPanel:nonusgovernmentagencycode"),


    //Location Attributes in SMO Search:
    SMOCountryDropDown("//*[@id='majorTabPanel:reason3']/div[2]"),
    SMOState("majorTabPanel:statesmo_label"),
    SMOStreet("majorTabPanel:streetsmo"),
    SMOCity("majorTabPanel:citysmo"),
    SMOZipCode("majorTabPanel:zipcodesmo"),


    //SUBJECT DETAILS_BASIC INFO
    SubjectDetailsLink("majorTabPanel:subjSummaryViewDetails"),
    CitizenshipInformationGrid("majorTabPanel:subjtabview:j_id_q1_toggler"),
    InvestigationHistory("majorTabPanel:subjtabview:j_id_qw_toggler"),
    AdjudicationHistory("majorTabPanel:subjtabview:adjhistory_toggler"),
    PositionOfTrustInformation("majorTabPanel:subjtabview:j_id_r3_toggler"),
    SubjectPersonalInformation("majorTabPanel:subjtabview:j_id_rn_toggler"),
    EditSubjectInformationButton("majorTabPanel:subjtabview:j_id_t0"),
    EditFirstNameInput("subjectEditForm:j_id_4t9"),
    EditMiddleNameInput("subjectEditForm:j_id_4tc"),
    EditSuffixInput("subjectEditForm:j_id_4ti"),
    EditMaritalStatusDropDown("subjectEditForm:maritalStatus_label"),
    EditBirthCityInput("subjectEditForm:j_id_4tq"),
    EditNaturalizationInput("subjectEditForm:natid"),
    EditAlienRegistrationIdInput("subjectEditForm:alienid"),
    UpdateSubjectButtonUpdateSubjectWindow("subjectEditForm:jvsPopupSubjectEditUpdateSubjectButton"),


    //ADD CITIZENSHIP
    AddCitizenshipButton("majorTabPanel:subjtabview:j_id_py:btnAdd_Citizenship"),
    CitizenshipTypeDropDown("newCitizenShipFrm:citisenshipType"),
    SelectCitizenshipType_BornAbroadOfUSparents("//*[@id='newCitizenShipFrm:citisenshipType_panel']/div/ul/li[@data-label='Born Abroad of U.S. Parents']"),
    SelectCitizenshipType_NaturalBornCitizen("//*[@id='newCitizenShipFrm:citisenshipType_panel']/div/ul/li[@data-label='Natural Born Citizen']"),
    SelectCitizenshipType_NaturalizedCitizen("//*[@id='newCitizenShipFrm:citisenshipType_panel']/div/ul/li[@data-label='Naturalized Citizen']"),
    SelectCitizenshipType_NonUSCitizen("//*[@id='newCitizenShipFrm:citisenshipType_panel']/div/ul/li[@data-label='Non-U.S. citizen']"),

    CountryDropDown("newCitizenShipFrm:citizenshipOfBirtheditId"),
    CitizenshipDateCalendarIcon("newCitizenShipFrm:citizenshipDate"),
    CitizenshipDateInput("newCitizenShipFrm:citizenshipDate"),
    RenunciationDateCalendarIcon("newCitizenShipFrm:renuniciationDate"),
    RenunciationDateInput("newCitizenShipFrm:renuniciationDate_input"),
    AddButtonToAddCitizenship("newCitizenShipFrm:j_id_4w9"),


    //EDIT SUBJECT INFORMATION FOR SMOKE
    SearchSubjectBySSNInput("cpanelForm:pinp"),
    SearchBySSNButton("cpanelForm:subjectQuickSearchActionId"),
    MiddleNameInputBox("subjectEditForm:j_id_4ss"),
    UpdateSubjectButton("subjectEditForm:jvsPopupSubjectEditUpdateSubjectButton"),
    AddEmailAddressButton("majorTabPanel:subjtabview:emailDataTable:j_id_u6"),
    AddEmailInputField("addEmailForm:emailAddInputText"),
    AddEmailTypeDropDown("addEmailForm:CreateemailTypeInput"),
    SaveButton("addEmailForm:emailAddSubmitButton"),
    EditEmailPencil("majorTabPanel:subjtabview:emailDataTable:0:j_id_tj"),
    EmailAddressEditInput("majorTabPanel:subjtabview:emailDataTable:0:j_id_tc"),
    EmailEditCheck("majorTabPanel:subjtabview:emailDataTable:0:j_id_tj"),
    EmailEditTrash("majorTabPanel:subjtabview:emailDataTable:0:buttonDeleteEmailAddress"),

    //SUBJECT DETAILS_FOREIGN TRAVEL
    ForeignTravelTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[8]/a"),
    CreateForeignTravelButton("majorTabPanel:subjtabview:tripDataTable:jvsSubjectDetailTabsCreateTripsButton"),
    ForeignTravelStartDateInput("newTripForm:j_id_46t_input"),
    ForeignTravelStartDateCalendarIcon("newTripForm:j_id_46t_input"),
    ForeignTravelEndDateInput("newTripForm:j_id_46v_input"),
    ForeignTravelEndDateCalendarIcon("newTripForm:j_id_46v_input"),
    ForeignTravelReportedDateInput("newTripForm:j_id_46x_input"),
    ForeignTravelReportedDateCalendarIcon("newTripForm:j_id_46x_input"),
    ForeignTravelReasonDropDown("newTripForm:j_id_46z_label"),
    ForeignTrelReason1_FRVisit("//*[@id='newTripForm:j_id_46z_panel']/div/ul/li[2]"),
    ForeignTrelReason2_NGOMissService("//*[@id='newTripForm:j_id_46z_panel']/div/ul/li[3]"),
    ForeignTrelReason3_OfficialTripNonDoDPurpose("//*[@id='newTripForm:j_id_46z_panel']/div/ul/li[4]"),
    ForeignTrelReason4_Vacation("//*[@id='newTripForm:j_id_46z_panel']/div/ul/li[4]"),
    ForeignTravelComment("newTripForm:j_id_473"),
    ForeignTravelSaveButton("newTripForm:jvsPopupNewTripSaveButton"),
    ForeignTravelCancelButton(""),


    //CONTACT INFO-ADD & EDIT
    AddPhoneNumberButton("majorTabPanel:subjtabview:phoneTable:j_id_u9"),
    AddPhoneNumberInputField("phoneAddForm:phoneAddInputText"),
    PhoneNumberTypeDropDown("phoneAddForm:pType_label"),
    SelectPhoneTypeFAX("//*[@id='phoneAddForm:pType_panel']/div/ul/li[@data-label='FAX']"),
    SelectPhoneTypeHome("//*[@id='phoneAddForm:pType_panel']/div/ul/li[@data-label='Home']"),
    SelectPhoneTypeMobileOrCell("//*[@id='phoneAddForm:pType_panel']/div/ul/li[@data-label='Mobile or Cell phone']"),
    SelectPhoneTypeTemporary("//*[@id='phoneAddForm:pType_panel']/div/ul/li[@data-label='Temporary']"),
    SelectPhoneTypeWork("//*[@id='phoneAddForm:pType_panel']/div/ul/li[@data-label='Work']"),
    SaveButtonInAddPhoneNumber("phoneAddForm:addPhoneNumberSaveButton"),
    EmailAddressEditPencil("//*[@id='majorTabPanel:subjtabview:emailDataTable:0:j_id_tk']/span[1]"),
    EmailEditInput("majorTabPanel:subjtabview:emailDataTable:0:j_id_td"),
    EmailCheckIcon("majorTabPanel:subjtabview:emailDataTable:0:j_id_tk"),
    PhoneNumberEditPencil("majorTabPanel:subjtabview:phoneTable:0:j_id_u6"),
    PhoneNumberEditInput("majorTabPanel:subjtabview:phoneTable:0:j_id_tz"),
    PhoneNumberCheckIcon("majorTabPanel:subjtabview:phoneTable:0:j_id_u6"),


//SUBJECT DETAILS_OTHER SUBJECT DETAILS

    OtherSubDetailsSubTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[3]/a"),
    AliasesGrid("majorTabPanel:subjtabview:jSDTO_pPanel_aliases1_toggler"),
    SSNHistoryInfoGrid("majorTabPanel:subjtabview:panel_JSDTO_SSN_History_toggler"),
    ForeignRelInfoGrid("majorTabPanel:subjtabview:panel_JSDTO_RelationshipPanel_toggler"),
    DomesticRelInfoGrid("majorTabPanel:subjtabview:panel_JSDTO_Domestic_RelationshipPanel_toggler"),


    ContactInfoSubTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[2]/a"),
    EmailAddressGrid("majorTabPanel:subjtabview:emailAddressFieldSet_toggler"),
    Pagination2("majorTabPanel:subjtabview:phoneTable_paginator_bottom"),
    Pagination1("majorTabPanel:subjtabview:phoneTable_paginator_bottom"),
    PhoneNumbersGrid("majorTabPanel:subjtabview:phoneNumberFieldSet_toggler"),

    FirstNameFilter("majorTabPanel:SubTable:fnam:filter"),
    LastNameFilter("majorTabPanel:SubTable:lnam:filter"),
    SSNFilter("majorTabPanel:SubTable:ssn:filter"),
    MaritalStatusDropDown("majorTabPanel:SubTable:mstat:filter"),
    BirthDateFilter("majorTabPanel:SubTable:bdat:filter"),

    //SUBJECT DETAILS_SUBJECT RELATIONSHIPS
    SubRelationshipsSubTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[4]/a"),
    CategoriesGrid("majorTabPanel:subjtabview:subjectCategoryFieldSetId_toggler"),
    RealtionshipsGrid("majorTabPanel:subjtabview:subjectRelationshipFieldSetId_toggler"),
    //VIEW CURRENT SMO_GENERAL INFORMATION_EDIT
    generalinformationpencil("majorTabPanel:smoDetailTabId:j_id_1g6"),
    SMONameWindow("//*[@id='majorTabPanel:smoDetailTabId:smoNameDialogId']/div[1]"),
    SMONamePrefix("majorTabPanel:smoDetailTabId:prefixnameDetail"),
    SMONameText("majorTabPanel:smoDetailTabId:nametextDetail"),
    SMONameIndex("majorTabPanel:smoDetailTabId:nameindexDetail"),
    SaveSMOName("majorTabPanel:smoDetailTabId:j_id_1hd"),
    CancelSMOName("majorTabPanel:smoDetailTabId:j_id_1he"),

    //VIEW CURRENT SMO_SMO ACTIONS DROP DOWN
    SMOActionsDropdown(""),
    ShowSMODetailLinkOnSMOTree(""),

    //SMO Relationships
    CategoriesListInDropDown("categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel"),
    IndustryType("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']/div/ul/li[@data-label='Industry']"),


    //INCIDENTS
    IncidentsSubTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[5]/a"),
    CreateNewIncidentButton("majorTabPanel:subjtabview:incidentsDataTable:jvsSubDetailIncidentsCreateNewIncidentButton"),
    IncidentDateCalendarButton("//*[@id='incidentCreateForm:incidentCreateChangeDateInput']/button"),
    createIncidentYrSelect("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[77]"),
    createIncidentDaySelect("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a"),

    IncidentReportedDateCalendarButton("//*[@id='incidentCreateForm:incidentCreateChangeReportDateInput']/button"),
    createReportedYrSelect("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[77]"),
    createReportedDaySelect("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a"),
    IncidentReportedDateFromCalendar("ui-datepicker-div"),
    SelfReportedYes("//*[@id='incidentCreateForm:incidentCreateSelfReportedRadioButton']/div[1]/span"),
    SelfReportedNo("incidentCreateForm:incidentCreateSelfReportedRadioButton:1"),
    SelectIncidentTypesButton("//*[@id='incidentCreateForm:incidentCreateChangeTypeButton2']"),
    IncidentTypeAlcoholConsumption("//*[@id='formIncidentEditChangeType:incdentTypeSelectionId']/tbody/tr/td[7]/div/div[2]"),
    IncidentTypeAllegianceToUnitedStates("formIncidentEditChangeType:j_id_2rx"),
    IncidentTypeCriminalConduct("formIncidentEditChangeType:j_id_35c"),
    IncidentTypeDrugInvolvement("formIncidentEditChangeType:j_id_35e"),
    IncidentTypePsychologicalConditions("formIncidentEditChangeType:j_id_2s3"),
    IncidentTypeFinanacialConsideration("formIncidentEditChangeType:j_id_2s5"),
    IncidentTypeForeignInfluence("formIncidentEditChangeType:j_id_2s7"),
    IncidentTypeForeignPreference("formIncidentEditChangeType:j_id_2s9"),
    IncidentTypeUseOfITSystems("formIncidentEditChangeType:j_id_2sb"),
    IncidentTypeOutsideActivities("formIncidentEditChangeType:j_id_2sd"),
    IncidentTypePersonalConduct("formIncidentEditChangeType:j_id_2sf"),
    IncidentTypeHandlingProtectedInformation("formIncidentEditChangeType:j_id_2sh"),
    IncidentTypeSexualBehavior("formIncidentEditChangeType:j_id_2sj"),
    SaveButtonForSelectedIncidenttypes("formIncidentEditChangeType:jvsPopupIncidentEditChangeTypeButton"),
    CancelButtonForSelectedIncidentTypes("formIncidentEditChangeType:j_id_2sn"),
    IncidentNotesInputBox("//*[@id='incidentCreateForm:incidentCreateNotesInput']"),
    AddDocumentButtonInCreateIncidentWindow("//*[@id='incidentCreateForm:j_id_38z']"),
    IncidentDocNameInput("incidentEditAddDocumentFormUpload:incidentEditAddDocumentMessagesdocname"),
    IncidentDocDescInput("incidentEditAddDocumentFormUpload:incidentEditAddDocumentMessagesdocdescription"),
    IncidentDocTypeDropDown("incidentEditAddDocumentFormUpload:incidentEditAddDocumentMessagesdoctype_label"),
    IncidentChooseDocButton("incidentEditAddDocumentFormUpload:docfileuploaded_input"),
    AddNewDocButtonInDocUploaderWindow("//*[@id='incidentEditAddDocumentFormUpload:incidentCreateAddButtonDocument']"),
    CancelbuttonInDocUploaderWindow("incidentEditAddDocumentFormUpload:incidentCreateCancelButtonDocument"),
    CreateIncidentButton("incidentCreateForm:incidentCreateButton"),


    //ADD FOREIGN RELATIONSHIP
    AddForeignRelationshipButton("//*[@id='majorTabPanel:subjtabview:foreignRel:btnAdd_Foreign_Relationship']"),
    BeginDateCalendarButton("//*[@id='frAddForm:beDate']/button"),
    CurrentBeginDate("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a"),

    //*********************************INDHU************************************************************************************************************************
    //FOR SMOKE REUSABLE FUNCTIONS
    QuickUserSearch("cpanelForm:ssnInput"),


//*********************************INDHU*******************************************************************************************************************************//	


    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*CSR - Supplemental Information~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
    quickSubjectSearch("cpanelForm:subjectQuickSearchActionId"),
    performQuickSubjectSearch("cpanelForm:subjectQuickSearchActionId"),
    subjectActionsDropdown("//*[@id='majorTabPanel:subjtabview:subactions_button']"),
    CSRSupplementalInfoOption("//*[@id='majorTabPanel:subjtabview:csrSuppInfoAction']/span"),
    commentsSection("//*[@id='csrForm:j_id_37y']"),
    submitSuppInfoCSR("//*[@id='csrForm:sendCsr']"),
    cancelSuppInfoCSR("//*[@id='csrForm:cancel']"),
    addDocumentlink("//*[@id='csrForm:j_id_37a:j_id_37t']"),
    documentName("//*[@id='documentUploaderForm:editAddDocumentMessagesDocName']"),
    documentDescription("//*[@id='documentUploaderForm:editAddDocumentMessagesDocDescription']"),
    documentType("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType']/div[3]/span"),
    chooseDocumentType("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[2]"),
    chooseDocumentUpload("//*[@id='documentUploaderForm:fileUploadId_input']"),
    addDocument("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    sendCSR("//*[@id='csrForm:sendCsr']"),


    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*CSR - Recertify ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
    CSRRecertifyLink("//*[@id='majorTabPanel:subjtabview:csrRecertifyAction']/span"),
    CSRRecertifySend("//*[@id='csrForm:sendCsr']"),
    CSRRecertifyCancel("//*[@id='csrForm:cancel']"),
    breakInServiceDropdown("//*[@id='csrForm:breakInService']/div[3]"),
    noBreakInService("//*[@id='csrForm:breakInService_panel']/div/ul/li[2]"),
    yesBreakInService("//*[@id='csrForm:breakInService_panel']/div/ul/li[1]"),
    recertifyReasonDropdown("//*[@id='csrForm:recertifyReasonSelect']/div[3]/span"),
    otherRecertifyReason("//*[@id='csrForm:recertifyReasonSelect_panel']/div/ul/li[1]"),
    NoDeterminationMade("//*[@id='csrForm:recertifyReasonSelect_panel']/div/ul/li[2]"),
    lossOfJurisdiction("//*[@id='csrForm:recertifyReasonSelect_panel']/div/ul/li[3]"),
    addRecertifyDocument("//*[@id='csrForm:j_id_3d5:j_id_3do']"),
    recertifyCommentsSection("//*[@id='csrForm:j_id_3dt']"),
    eligibilityLevelRequestedDropdown("//*[@id='csrForm:eligibilityLevelRequestedSelect']/div[3]/span"),
    eligibilityLevelRequestedTopSecret("//*[@id='csrForm:eligibilityLevelRequestedSelect_panel']/div/ul/li[2]"),
    clickCalendarStartDate("//*[@id='csrForm:bissDate']/button"),
    clickCalendarEndDate("//*[@id='csrForm:biseDate']/button"),
    createSubjectYrSelectStart("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[7]"),
    createSubjectYrSelectEnd("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[10]"),
    createSubjectDaySelect("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a"),

    //*********************RFAs*********************************
    SubResponse("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Subject Response']"),
    AckReceipt("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Acknowledgement of Receipt']"),
    UploadDoc("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    RequestExErr("//*[@id='majorTabPanel:j_id_1s3:rfaSorSecurityOfficerErrorMessagesId']/div/ul/li/span[1]"),
    SORAckDueDt("majorTabPanel:j_id_1s3:sorAckDueDateId"),
    SORResponseDt("majorTabPanel:j_id_1s3:sorResponseDueDateId"),
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
//***********************************************Asha******************************************************************
    //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~JVS_Control panel*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
    Container2("//*[@id='container_2']"),
    Container1("//*[@id='containers_1']"),
    SubjectSearch("//*[@id='cpanelForm:pinp']"),
    SubjectSearchbtn("//*[@id='cpanelForm:subjectQuickSearchActionId']"),
    SubjectDetailsLink1("//a[contains(text(),'Subject Details')]"),
    SMORelationshipTab("//*[@id='majorTabPanel:subjtabview']//a[text()='SMO Relationships']"),
    NewcategoryButton("//*[@id='majorTabPanel:subjtabview:categoriesDataTable:jvsSubjectDetailTabRelationshipsCreateNewCategoryButton']"),
    CategoryTypeDropDown1("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_label']"),
    CivilianCategory("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']/div/ul/li[text()='Civilian Employee']"),
    CivilianRetiree("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']//li[text()='Civilian Retirees']"),////changed in 5.4
    CreateCategoryNextBtn("//form[@id='categoryCreateForm']/div[2]/div/div/div/button[2]"),
    SkipWorkInfocheckbox("//form[@id='categoryCreateForm']/div[2]/div/table[2]/tbody/tr/td[2]/div/div[2]"),
    WorkOrgNextBtn("//div[@id='categoryCreateForm:createCategoroyWizardPopup_content']//button/span[contains(text(),'Next')]"),
    CreateCategoryBtn("//*[@id='categoryCreateForm:categorycreatesubmitbuttonid']"),
    EstablishNewRelationBtn("//*[@id='majorTabPanel:subjtabview:relationshipTable:jvsSubjectDetailTabRelationshipsEstablishNewRelationshipButton']"),
    SelectSubjectCategoryRadioBtn("//*[@id='addRelationshipWizardForm:subjcategorytable:0:selcatval']/span[1]"),
    OwningBtn("//*[@id='addRelationshipWizardForm:relationshiptypeselection']//span[text()='Owning']"),
    //OwningBtn("//span[contains(text(),'Owning')]"),
    NewRelationNextBtn("//*[@id='addRelationshipWizardForm:jvsAddRelationshipWizardNextButton']/span[text()='Next']"),
    SummarySaveBt("//div[@id='addRelationshipWizardForm:relwizid_content']/table[2]/tbody/tr/td[2]/div/div/button[2]"),
    //ChooseBtn("//span[text()='Choose']"),
    ChooseBtn("//*[@id='documentUploaderForm:fileUploadId_input']"),
    InputField("//*/input[@type='file']"),
    AddDocBtn("//button[@id='documentUploaderForm:documentUploadAddButtonId']"),
    ClaimButton("//*[text()[contains(.,'Claim')]]"),
    SendButton("//*[text()[contains(.,'Send')]]"),
    UploadDocBtn("//*[text()[contains(.,'Upload Document')]]"),
    DatePickerIcon("//button[@type='button']"),
    AckDate("//div[@id='ui-datepicker-div']/table/tbody/tr/td/a[text()='1']"),
    AcknowledgementDateDatepicker("//tr[2]/td[2]/span/input"),
    LORAcknowledgementDAte("//tr[2]/td[2]/span/input"),
    DocTypeAcknowledgementReceipt("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[text()='Acknowledgement of Receipt']"),
    DocTypeRFAMedEvalResponse("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']//li[text()='RFA Med Evaluation Response']"),
    DocTypeNOIA("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[text()='Notice of Intent to Appeal']"),
    NOIAReceivedDatePicker("//span[@id='majorTabPanel:taskDetailFormId:noiaReceivedDateId']/button"),
    AppealDatePicker("//span[@id='majorTabPanel:taskDetailFormId:sentToAppealsDateId']/button"),
    DocTypeSubjectResponse("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[text()='Subject Response']"),
    SubjectAppealCheckBox("//div[contains(@id,':subjWillAppealId')]//div[contains(@class,'ui-chkbox-box')]");
    private String property;

    private JVSProperties(String property) {
        this.setProperty(property);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
