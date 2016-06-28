package com.iworks.DISS.test.common.properties;


// import org.openqa.selenium.By;

public enum CATSProperties {
	
	
	
 //~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
	//this is an example how to use properties
	defaultPassword("Usethispass!"),
	
//~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*
// Start writing new properties from Here: 

	investigation("//*[@id='majorTabPanel:subjtabview']/ul/li[2]/a"),
	countryDropDown1("//div[starts-with(@id,'majorTabPanel:createsub:j_id_')]/div[3]/span"),
	countryDropDown("span[class='ui-icon ui-icon-triangle-1-s ui-c']"),
	verifyMessage("//*[@id='majorTabPanel:missinginfomessages']/div/ul/li/span[2]"),
	verifySaveMsg("//*[@id='maingrowl_container']/div/div/div[2]/span"),
	
	adjudicationGrid("//*[@id='majorTabPanel:adjPanel:catsAdjSummary']"),
	
	container2("//*[@id='container_2']"),
	container1("//*[@id='containers_1']"),

	verifySubjectMessage("//*[@id='majorTabPanel:missinginfomessages']/div/ul/li/span[2]"),
	quickSubjectErrMsg("cpanelForm:fsmsgValidation"),
	loginErrMessage("//*[@id='sub-navigation_bar']/div[2]"),
	loginUserNamePortal("//*[@id='global_nav']/div/div/p[1]/span"),
	loginUserNameCATS("//*[@id='top_navigation']/div/div/div[2]/div/div/p[1]/span"),
	loginUserRole("//*[@id='roleStringId']"),

//************************************CATS_Subject Properties*************************************   
	subjectManagementPanel("//*[@id='cpanelForm:subjetPanel']"),
	subPersonalInfoSec("//*[@id='majorTabPanel:subjtabview:j_id_31k']"),
	subjectActionsDropDown("majorTabPanel:subjtabview:subactions_button"),

	subectActionsMenu("majorTabPanel:subjtabview:subActionsMenu"),
//	subectActionsMenu("majorTabPanel:subjtabview:j_id_345"),
//	personOfInterestWindow("//*[@id='personOfInterestForm:addPotDialog_title']"),
	personOfInterest("//*[@id='majorTabPanel:subjtabview:tagPersonInterestAction']/span"),
	
	subjectActionsbtn("//*[@id='majorTabPanel:subjtabview:subactions_button']"),
	subjectActionsMenu("majorTabPanel:subjtabview:j_id_345"),
	personOfInterestWindow("//*[@id='addPotDialog']"),    //*[@id='personOfInterestForm:addPotDialog_title']
	tagPersonOfInterest("//*[@id='majorTabPanel:subjtabview:tagPersonInterestAction']/span"),
	personOfInterestTypeDropDown("//*[@id='personOfInterestForm:potTypeInput']/div[3]"),
	poiType1("//*[@id='personOfInterestForm:potTypeInput_panel']/div/ul/li[2]"),
	poiType2("//*[@id='personOfInterestForm:potTypeInput_panel']/div/ul/li[3]"),
	poiType3("//*[@id='personOfInterestForm:potTypeInput_panel']/div/ul/li[4]"),
	editFName("subjectEditForm:j_id_615"),
	editMName("subjectEditForm:j_id_618"), //subjectEditForm:j_id_5yx
	editLName("subjectEditForm:j_id_61b"),
	stateOfBirthDropDown("//*[@id='subjectEditForm:birtsState']/div[3]/span"),
	selectState("//*[@id='subjectEditForm:birtsState_panel']/div/ul/li[6]"),  // California
	editDOB("subjectEditForm:dobDate_input"),
	updateSubjectCalendar("//*[@id='subjectEditForm:dobDate']/button"),
	updateSubjectDaySelect("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[7]/a"),
	updateSubjectDOB("//*[@id='ui-datepicker-div']/div[1]/div/select[2]"),
	updateSubjectbtn("subjectEditForm:j_id_613"),      //*[@id='subjectEditForm:j_id_63i'] //subjectEditForm:j_id_60c

	updateSSNDialog("//*[@id='updateSSNDialog']/div[2]"),
	editPencilIcon("majorTabPanel:buttonChangeSSN"),
	VerifySSNOnUpdateWindow("//*[@id='ssnChangeForm:ssnChangeAttributes']/tbody/tr[1]/td[1]/label"),
	NewSSNTextOnUpdateWindow("//*[@id='ssnChangeForm:ssnChangeAttributes']/tbody/tr[2]/td[1]"),
	newSSNTextArea("ssnChangeForm:newSsn"),
	changeReasonText("//*[@id='ssnChangeForm:ssnChangeAttributes']/tbody/tr[3]/td[1]/label"),
	selectReasonDropDown("//*[@id='ssnChangeForm:reason']/div[3]/span"),
	DuplicateSSNInSystem("//*[@id='ssnChangeForm:reason_panel']/div/ul/li[2]"),
	GovernmentDirective("//*[@id='ssnChangeForm:reason_panel']/div/ul/li[3]"),
	IdentifyTheft("//*[@id='ssnChangeForm:reason_panel']/div/ul/li[4]"),
	IncorrectSSNInSystem("//*[@id='ssnChangeForm:reason_panel']/div/ul/li[5]"),
	beginDateTextOnUpdateSSN("//*[@id='ssnChangeForm:ssnChangeAttributes']/tbody/tr[4]/td[1]/label"),
	CalendarIconOnUpdateSSNWindow("//*[@id='ssnChangeForm:beDate']/button"),
	UpdateSSNBtn("ssnChangeForm:j_id_6ql"),  
	OtherSubDetailsTab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[2]/a"),
	SSNHisInfoText("//*[@id='majorTabPanel:subjtabview:panel_JSDTO_SSN_History_header']/span"),
	createSubjectSSN("//*[@id='majorTabPanel:createsub:cSSN']"),
	createSubjectLname("//*[@id='majorTabPanel:createsub:clastName']"),
	createSubjectFname("//*[@id='majorTabPanel:createsub:cfirstName']"),
	createSubjectCalender("//*[@id='majorTabPanel:createsub:cDOB']/button"),
	createSubjectDOB("//*[@id='ui-datepicker-div']/div[1]/div/select[2]"),
	createSubjectYrSelect("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[77]"),
	createSubjectDaySelect("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a"),
	Create_Sub_Yes("//*[@id='majorTabPanel:createsub:yesGrandForm']"),
	createSubjectCurrentDate("//*[@id='ui-datepicker-div']/div[2]/button[1]"),
    createSubjectCountryDropMenu("majorTabPanel:createsub:birthCountrySelectMenu"),
    selectCountry("//*[@id='majorTabPanel:createsub:birthCountrySelectMenu']"), // United States 
    createSubjectBtn("majorTabPanel:createsub:grantForm"),
    createSubjectConfirmWindow("//*[@id='majorTabPanel:createsub:j_id_fo']"),
    createSubjectClaimGrowlerMessage("//*[@id='maingrowl_container']/div/div/div[2]/span"),
    //createSubjectConfirmWindow("//div[starts-with(@id='majorTabPanel:createsub:j_id_e')]/div[1]/span"),
    //verifySubjectFName("//*[@id='majorTabPanel:subjectName']"),
    verifySubjectFName("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[1]/td[1]/label"),
    verifySubjectLName("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[1]/td[5]/label"),
    verifySubjectMName("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[1]/td[3]/label"),
    verifySSNonSubjectDetail("//*[@id='majorTabPanel:subjectDetssnUpTop']"),
    quickSubjectSearch("//*[@id='cpanelForm:pinp']"),
    quickSubjectSearchBtn("//*[@id='cpanelForm:findSubjectId']"),
    verifySubDOB("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[2]/td[3]/label"),
    verifySubCountryOfBirth("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[3]/td[1]/label"),
    verifySubBirthState("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[3]/td[5]/label"),
    verifySubBirthCity("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[3]/td[3]/label"),
    verifySubMaritalStatus("//*[@id='majorTabPanel:subjtabview:j_id_312_content']/table/tbody/tr[4]/td[1]"),
    //quickSubjectSearchBtn("//*[@id='cpanelForm:j_id_4h']"),
    //quickSubjectSearchBtn("//input[@type='submit'][last()]"),
    editSubjectInfoBtn("majorTabPanel:subjtabview:j_id_2x1"),
    updateSubjectFName("//*[@id='subjectEditForm:j_id_6lb']"),
    updateSubjectLName("//*[@id='subjectEditForm:j_id_6lh']"),
    updateSubjectSuffix("//*[@id='subjectEditForm:j_id_6lk']"),
    updateSubjectNaturId("//*[@id='subjectEditForm:natid']"),
    updateSubjectAlienID("//*[@id='subjectEditForm:alienid']"),
    contactEmail("//*[@id='majorTabPanel:subjtabview:emailDataTable:0:j_id_34n']"),
    contactPhone("//*[@id='majorTabPanel:subjtabview:phoneTable:0:j_id_359']"),
    addForeignRelBtn("html/body/div[3]/div/div[2]/div/div/div[2]/div/form[1]/div/div/div[2]/div[2]/div/div[2]/table/tbody/tr[3]/td/div/div[2]/div/div[3]/div/button"),
    incidentTab("//*[@id='majorTabPanel:subjtabview']//a[text()='Incidents']"),
    tripsTab("//*[@id='majorTabPanel:subjtabview']/ul/li[5]/a"),
    contactTab("//*[@id='majorTabPanel:subjtabview']/ul/li[2]/a"),
    otherSubjectDetailsTab("//*[@id='majorTabPanel:subjtabview']//a[text()='Other Subject Details']"),
    subjectCitizenshipInfo("//*[@id='majorTabPanel:subjtabview:j_id_311_data']/tr[*]/td[1]"),
    subjectCaseHistory("//*[@id='majorTabPanel:subjtabview:subCaseDat_data']/tr[*]/td[2]"),
    subjectCategory("//*[@id='majorTabPanel:subjtabview:categoriesDataTable_data']/tr[*]/td[1]"),
    subjectRelatioships("//*[@id='majorTabPanel:subjtabview:relationshipTable_data']/tr[*]/td[1]"),
    smoRelationships("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[3]/a"), //R
    smoPointOfContact("//*[@id='majorTabPanel:smoDetailTabId:SmoPocTable_data']/tr[*]/td[2]"),
    subjectIncidents("//*[@id='majorTabPanel:subjtabview:incidentsDataTable_data']/tr[*]/td[2]"),
    subjectAliases("//*[@id='majorTabPanel:subjtabview:j_id_356_data']/tr[*]/td[1]"),
    subjectSSNHistory("//*[@id='majorTabPanel:subjtabview:ssnHistoryData_data']/tr[*]/td[2]"),
    subjectForeignRelationship("//*[@id='majorTabPanel:subjtabview:foreignRel_data']/tr[*]/td[1]"),
    
  
// ***************************************Task Inbox Properties*********************************************//
    
    activeTaskListCount("//*[@id='majorTabPanel:SubTabletasks_data']/tr[*]/td[1]"),
    claimBtn("//*[@id='majorTabPanel:taskDetailFormId:j_id_4xk']"),
    rejectBtn("//*[@id='majorTabPanel:taskDetailFormId:j_id_4xj']"),
    approveBtn("//*[@id='majorTabPanel:taskDetailFormId:j_id_4xi']"),
    closeBtn("//*[@id='majorTabPanel:taskDetailFormId:j_id_4xm']"),
    TaskInboxLink("commPanel:taskInboxLink"),  
    QCInboxLink("//*[@id='commPanel:qcInboxLink']"),	
    
    
    
 // *****************************Task Details for CSR- Reciprocity CATS Hetal**************************************//
    
    SubCategory("//*[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[1]/td[1]/label"),
    InvServiceProvider("//*[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[2]/td[1]/label"),
    DateInvCompleted("//*[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[3]/td[1]/label"),
    AgencyGrantedEli("//*[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[1]/td[3]/label"),
    InvLocation("//*[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[2]/td[3]/label"),
    EliLevelReq("//*[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[1]/td[5]/label"),
    DateEliGranted("//*k[@id='majorTabPanel:taskDetailFormId:j_id_3ro']/tbody/tr[2]/td[5]/label"),
    CommentsField("//*[@id='majorTabPanel:taskDetailFormId:j_id_3sf']/tbody/tr[2]/td[1]"),
    CSRDocumentTable("//*[@id='majorTabPanel:taskDetailFormId:j_id_3sq']/div[1]/table"),
    CSRResponseReciprocity("//*[@id='majorTabPanel:taskDetailFormId:catsCsrRequestReciprocityResponseComment']"),
    ClaimButtonReciprocity("majorTabPanel:taskDetailFormId:csrRequestRecipClaim"),
    ApproveBtnReciprocity("majorTabPanel:taskDetailFormId:csrRequesrtRecipApprove"),
    UnreadNotifications("//*[@id='commPanel:undreadNotificationLink']"),
    SubjectEnterSSNField("//*[@id='cpanelForm:pinp']"),
    SubCaseHistory("//*[@id='majorTabPanel:subjtabview:j_id_23z_header']/span"),
    
    
//***********************************************Task Details for CSR-Expedite CATS-Hetal************************************//
    
    TaskDetailsWindow("//*[@id='majorTabPanel:taskdetail']/div[2]"),
    SubjectSummary("majorTabPanel:taskDetailFormId:csrSubjectDetail"),
    CaseSummary("//*[@id='majorTabPanel:taskDetailFormId:j_id_48x_content']"),
    CSRDocuments("majorTabPanel:taskDetailFormId:expeditePanel1_content"),
    AdHocPriPrgm("//*[@id='majorTabPanel:taskDetailFormId:expeditePanelGrid']/tbody/tr[1]/td[2]"),
    CommentsTextField("//*[@id='majorTabPanel:taskDetailFormId:expeditePanelGrid']/tbody/tr[1]/td[2]"),
    CSRResponseText("//*[@id='majorTabPanel:taskDetailFormId:csrExpediteResponseResponseComment']"),
    ClaimBtnExpedite("majorTabPanel:taskDetailFormId:claimCSRER"),
    ApproveBtnExpedite("majorTabPanel:taskDetailFormId:approveCSRER"),
    
     // Create CASE //
    InvTypeDropDown("//*[@id='createCaseForm:createCaseInvType']/div[3]/span"),
    InvTypeNACLC("//*[@id='createCaseForm:createCaseInvType_panel']/div/ul/li[7]"),
    DivisionDropDown("//*[@id='createCaseForm:selectDivisionMenu']/div[3]/span"),
    CaseTypeDropDown("//*[@id='createCaseForm:createCaseType']/div[3]/span"),
    CaseTypeSecret("//*[@id='createCaseForm:createCaseType_panel']/div/ul/li[2]"),

    CaseTypeSecretforSSBI("//*[@id='createCaseForm:createCaseType_panel']/div/ul/li[4]"),

    CreateCaseBtn("createCaseForm:createCaseButton"),
    CreateCaseCancelBtn("createCaseForm:createCaseClearButton"),

    Birth_Country("//*[@id='majorTabPanel:createsub:birthCountrySelectMenu_panel']/div[2]/ul/li[4]"),    
    CreateCase("//*[@id='majorTabPanel:subjtabview:createCaseActionsButton']/span"),
    CreateCaseDialog_1("createCaseForm:createCaseDialog_title"),
    CreateCaseInvType("//*[@id='createCaseForm:createCaseInvType']/div[3]/span"),
    SelectDivisionBArmy("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[2]"),
    ReqSMODropDown("//*[@id='createCaseForm:j_id_6fd']/div[3]/span"),
    CreateCaseArmySMO(".//*[@id='createCaseForm:j_id_6fd_panel']/div/ul/li[2]"),
    

    CreateCaseDialog_1_xpath("//*[@id='createCaseForm:createCaseDialog_title']"),				//R
    CreateCaseBtn_xpath("//*[@idcreateCaseForm:createCaseButton']"),							//R   

    OSelectDivisionDAirForce("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[6]"),	//R
    SelectDivisionDAirForce("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[text()='Division D (Air Force)']"),	//R

    ReqSMODropDown_new("//*[@id='createCaseForm:j_id_6dr']/div[3]/span"),						//R
    CreateCaseWithoutSMO("//*[@id='createCaseForm:j_id_6dr_panel']/div/ul/li[1]"),				//R
    CaseID("majorTabPanel:caseDetailsPanel:caseIDValue"),

 // *************************************************CATS Case Properties *****************************************************//

    CaseInox("//*[@id='majorTabPanel']/ul/li/a"),
    openCasesInbox("//*[@id='majorTabPanel:SubTable']/div[1]"),   //majorTabPanel:SubTable

    caseCountXPath("//tbody[@id='majorTabPanel:SubTable_data']/tr[*]/td[2]"),
    caseHistoryCount("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']/tr[*]/td[1]"),
    caseSelection("//*[@id='majorTabPanel:SubTable_data']/tr[1]/td[3]"),
    caseTypeDropMenu("//*[@id='majorTabPanel:caseDetailsPanel:caseTypeSelectOne']/div[3]/span"),
    casePriorityProgramMenu("//*[@id='majorTabPanel:caseDetailsPanel:casePrioProgSelectOne']/div[3]/span"),
    Assignee_Link("//*[@id='majorTabPanel:caseDetailsPanel:assigneeLinkDisplay']"),
    Assignee_Window("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul"),

    mpcCodeMenu("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu']/div[3]/span"),
    //mpcCodeMenu("//*[@id='majorTabPanel:caseDetailsPanel:j_id_ks']/div[3]/span"),
   
    caseRequestingSMOMenu("//*[@id='majorTabPanel:caseDetailsPanel:caseSMOSelectOne']/div[3]/span"),
    //caseSearchBtn("//*[@id='cpanelForm:j_id_3p']"),
    caseSearchBtn("//*[@id='cpanelForm:searchCaseManagement']"),
    Cancel_Status("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']/tr[1]/td[2]"),
    SOR_Industry("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']/tr[1]/td[1]"),
    caseQuickCaseSearchBox("//*[@id='cpanelForm:mainSearchCase']"),
    ErrorMSGCaseSearch("//*[@id='cpanelForm:casePanel_content']/div[3]"),
    caseSearchErrXpath("//*[@id='cpanelForm:findCaseMsg']/span"),
    caseInfoExpend("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseNotesTableId_data']/tr[1]/td[5]/div"),
    CaseNoteTxt("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseNotesTableId:0:caseNotePanelDisable']"),
    EditNotes("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseNotesTableId:0:j_id_m4']"),
    
    caseNoVerify("//*[@id='majorTabPanel:caseDetailsPanel:caseInfo']/div[3]/span[2]"),
   // caseSMOicon("html/body/div[3]/div/div[2]/div/div/div[2]/div/form[1]/div/div/div[2]/div[1]/div[2]/table/tbody/tr[4]/td[2]/a[2]"),
   // caseSMOicon1("html/body/div[3]/div/div[2]/div/div/div[2]/div/form[1]/div/div/div[2]/div[1]/div[2]/table/tbody/tr[4]/td[2]/a"),
    caseExpend("//*[@id='majorTabPanel:SubTable_data']/tr[1]/td[6]/div"),
    investigationDetailsExpend("//*[@id='majorTabPanel:caseDetailsPanel']/h3[3]/span"),
    adjudicationExpend("//*[@id='majorTabPanel:adjPanel']/h3/span"),
    caseNoteTab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[1]/a"),
    caseNoteCount("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseNotesTableId_data']/tr[*]/td[2]"),
    ReviewNoteCount("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseReviewTableId_data']/tr[*]/td[1]"),
    addNoteBtn("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseNotesTableId:addNoteButton']"),
    AddNoteDropDown("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:addEditCaseNoteForm:noteTypeMenu']/div[3]/span"),
    DefaultGeneral_Case("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:addEditCaseNoteForm:noteTypeMenu_label']"),
    General_Casedp("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:addEditCaseNoteForm:noteTypeMenu_panel']/div/ul/li"),
    NoteDetails("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:addEditCaseNoteForm:NoteDetailsTextArea']"),
    noteSaveBtn("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:addEditCaseNoteForm:addNoteSubmit']"),
    DetailsText("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseNotesTableId_data']/tr/td[4]"),
    noteCancelbtn("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:addEditCaseNoteForm:cancelNewCaseNoteButton']"),
    which ("//*[@id='majorTabPanel:subjtabview:createCaseActionsButton']/span"),
    docAddbutton("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    csrHistory("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[6]/a"),
    csrHistoryCount("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCsrTableId_data']/tr[*]/td"),
    Hist_Date("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId:chCol3']"),
   	ActionBy("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId:chCol2']"),
   	Hist_Action("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId:chCol1']"),
   	HDate("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']/tr[*]/td[3]"),
   	HUser("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']/tr[*]/td[2]"),
   	HAction("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']/tr[*]/td[1]"),	   	
    
    
//********************* CATS Manage Hierarchy******************************
    manageHierarchy("//*[@id='cpanelForm:manageHierarchyLink']"), 
    lookUpExecutiveChief("//*[@id='majorTabPanel:cafHierarchyTreeTableId:0:buttonLookupChiefUser']"),
    cafExpend("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]/span"),
    addDivision("//*[@id='majorTabPanel:cafAddDivisionItem']"),
    editDivision("//*[@id='majorTabPanel:divisionEditItem']"),
    deleteDivsion("//*[@id='majorTabPanel:divisionDeleteItem']"),
    addBranch("//*[@id='majorTabPanel:addBranchItem']"),
    editBranch("//*[@id='majorTabPanel:branchEditItem']"),
    deleteBranch("//*[@id='majorTabPanel:branchDeleteItem']"),
    addTeam("//*[@id='majorTabPanel:addTeamItem']"),
    editTeam("//*[@id='majorTabPanel:teamEditItem']"),
    deleteTeam("//*[@id='majorTabPanel:j_id_aw']"),
    teamDetails("//*[@id='majorTabPanel:teamDetailsItem']"),
    cafTreeYesBtn("//*[@id='majorTabPanel:j_id_bg']"),
    addTeamMember("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:buttonLookupAddNewMember']"),
    selectTeamMember("//*[@id='cafSearchUserTableId_data']/tr[1]/td[1]"),
    saveManage("//*[@id='majorTabPanel:saveid']"),
    
    
//*****************************Adjudication Panel DueProcessProperties***************************
   // FilesReviewedTabComplete("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:fileRvwTableTaskNotComplete']/tbody/tr/td[1]"),
   // FilesReviewedTabIncomplete("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:fileRvwTableTaskNotComplete']/tbody/tr/td[1]"),
    //GENERAL
    SearchCaseInputBox("cpanelForm:mainSearchCase"),
    SearchButton("cpanelForm:searchCaseManagement"),
    AdjudicationPanel("//*[@id='majorTabPanel:adjPanel']/h3/span"),
    RAISESave("majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:saveSurvey"),
    TaskInbox1("commPanel:j_id_2c"),
    FilesReviewedTab("//*[@id='majorTabPanel:adjPanel:catsAdjSummary']/ul/li[2]/a"),
    FilesReviewed_FileSelection("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:fileReviewedFormId:fileReviewedDataGridId:0:j_id_xg']"),
    //FilesReviewedSave("majorTabPanel:adjPanel:catsAdjSummary:rvFrm:btnForm"), //old
    //FilesReviewedSave("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:fileReviewedFormId:saveFileReviewButtonId']"), //old
    FilesReviewedSave("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:saveFileReviewButtonId']"),
    
    GuidelinesTab("//*[@id='majorTabPanel:adjPanel:catsAdjSummary']/ul/li[3]/a"),
    //NoDisqualifyingConditionsRadioButton("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:guidelineRadioButtonId']/tbody/tr/td[3]/div/div[2]"),//old
    NoDisqualifyingConditionsRadioButton("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineRadioButtonId']/tbody/tr/td[3]/div/div[2]"),//R
    AdminDetermination_GuidelinesNotAppliedButton("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineRadioButtonId']/tbody/tr/td[5]/div/div[2]"),//R - 7/1/15
	    
    AlcoholConsumptionPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcoholConsumptionTab"),
	AlcoholDisqualifyingConditionSecondChoice("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcForm:j_id_1j7']/div[2]"),
	AlcoholMitigatingConditionFirstChoice("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcForm:j_id_1kf']/div[2]"),
	AlcoholConsumptionDisqualifyingCondition1("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:alcConguidelineTab:alcCondisCondTable:0:alcCondisCondBoolean']/div[2]"),
	AlcoholConsumptionDisqualifyingCondition2("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcForm:j_id_1jh"),
	AlcoholConsumptionMitigatingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcForm:j_id_1kk"),
    AlcoholConsumptionSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcForm:ACSave"),
    AlcoholConsumptionClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlcForm:j_id_1lb"),
    Review_Tab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[4]/a"),
    
		
	AllegianceToUnitedStatesPanel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[2]/span"),
	AllegianceDisqualifyingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlgForm:j_id_z5"),
	AllegianceDisqualifyingCondition2("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlgForm:j_id_za"),
	AllegianceMitigatingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlgForm:j_id_10j"),
	AllegianceSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlgForm:allgianceSave"),
	AllegianceClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:AlgForm:j_id_11f"),
	ForeignInfluencePanel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[3]/span"),
    ForeignInfluenceDisqualifyingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForForm:j_id_11n"),
    ForeignInfluenceSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForForm:FISave"),
    ForeignInfluenceClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForForm:j_id_14h"),
    

    ForeignPreferencePanel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[4]/span"),
    ForeignPreferenceDisqulaifyingCondition1("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForPrefForm:j_id_17n']/div[2]"),
    ForeignPreferenceDisqualifyingCondition2("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForPrefForm:j_id_182']/div[2]"),
    ForeignPreferenceMitigtingCondition1("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForPrefForm:j_id_19a']/div[2]"),
    ForeignPreferenceSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForPrefForm:FPSave"),
    ForeignPreferenceClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ForPrefForm:j_id_1ab"),
    SexualBehaviorPanel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[5]/span"),
    SexualBehaviorDisqualifyingCondition("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:SexForm:j_id_1b3']/div[2]"),
    SexualBehaviorDisqualifyingConditionOtherTextBox("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:SexForm:SBDisQother"),
    SexualBehaviorSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:SexForm:SBSave"),
    SexualBehaviorClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:SexForm:j_id_1cd"),
    
 
    DrugInvolvementPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:DrugInvolvementTab"),
    DrugInvolvementDisqualifyingGuideline1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:DrugForm:j_id_1lj"),
    DrugInvolvementDisqualifyingGuideline2("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:DrugForm:j_id_1md"),
    DrugInvSave(""),
    DrugInvClear(""),
    
    
    EmotionalMentalandPersonalityDisordersPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:PsychologicalConditionsTab"),
    EmotionalMentalandPersonalityDisordersDisqualifyingGuideline1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:PsycForm:j_id_1o5"),
    EmotionalMentalandPersonalityDisordersMitigatingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:PsycForm:j_id_1pi"),
    EmotionalMentalandPersonalityDisordersSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:PsycForm:PCSave"),
    EmotionalMentalandPersonalityDisordersClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:PsycForm:j_id_1pz"),
    
    PersonalConductPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:CriminalConductTab"),
    PersonalConductDisqulaifyingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:CrimConForm:j_id_1q7"),
    PersonalConductMitigtingCondition1(""),
    PersonalConductSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:CrimConForm:CCSave"),
    PersonalConductClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:CrimConForm:j_id_1sg"),
    
    FinancialConsiderationsPanel(""),
    FinancialConsiderationsDisqulaifyingCondition1(""),
    FinancialConsiderationsSave(""),
    FinancialConsiderationsClear(""),
    
    SecurityViolationsPanel(""),
    SecurityViolationsDisqualifyingGuideline1(""),
    SecurityViolationsMitigatingCondition1(""),
    SecurityViolationsSave(""),
    SecurityViolationsClear(""),
    
    
    
    HandlingProtectedInformationPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:HandlingProtectedInformationTab"),
    HandlingProtectedInforamtionDisqualifyingGuideline1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:HandForm:j_id_1st"),
    HandlingProtectedInformationMitigatingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:HandForm:j_id_1ul"),
    HandlingProtectedInformationSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:HandForm:HPISave"),
    HandlingProtectedInformationClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:HandForm:j_id_1v2"),
    OutsideActivitiesPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:OutsideActivitiesTab"),
    OutsideActivitiesPanelDisqualifyingGuideline1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:OutForm:j_id_1va"),
    OutsideActivitiesPanelMitigatingCondition1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:OutForm:j_id_1wi"),
    OutsideActivitiesSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:OutForm:OASave"),
    OutsideActivitiesClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:OutForm:j_id_1x4"),
    UseofInformationTechnologySystemsPanel("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:UseofITSystemsTab"),
    UseOfITSystemsDisqualifyingGuideline1("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ITForm:j_id_1xr"),
    UseOfITSystemsSave("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ITForm:UOITSSave"),
    UseOfITSystemsClear("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:ITForm:j_id_1zl"),

    
        
 
    //DeterminationTab("//*[@id='majorTabPanel:adjPanel:catsAdjSummary']/ul/li[4]/a"),//old
    DeterminationTab("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[4]/a"),
     
     
    FavorableFromDropdown("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[2]"),

 
    //DeterminationDropdownClick("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision']/div[3]/span"),//old
    DeterminationDropdownClick("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationFormId:adjudicationDeterminationCodeId']/div[3]/span"),

    NoneFromDropDown("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[1]"),
    DeterminationTypeSecurity("majorTabPanel:adjPanel:catsAdjSummary:elgFrm:typeSelected"),
    DeterminationSelectedFavorable("majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_label"),
    SecurityDeterminationPanel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityPanel']/h3/span"),
    ConditionCodeDropDown("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityPanel:securitydeterCondition']/div[3]/span"),
    ConditionFromDropDown("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityPanel:securitydeterCondition_panel']/div/ul/li[1]"),
    
    
    
    //DeterminationUnclassifiedCommentsLabel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:determinationAddlComments_header']/span"),//old
    DeterminationUnclassifiedCommentsLabel("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationFormId:determinationCommentPanelId_header']/span"),
    //DeterminationUnclassifiedCommentsTextBox("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:deterComments']"),//old
    DeterminationUnclassifiedCommentsTextBox("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:determinationInputAreaId']"),
    
    //DeterminationSave("majorTabPanel:adjPanel:catsAdjSummary:elgFrm:elgForm"),//old 
    DeterminationSave("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSaveDeterminationId']"),
    DeterminationSavedGrowler("//*[@id='maingrowl_container']/div/div/div[2]/span"),
    DeterminationClear("majorTabPanel:adjPanel:catsAdjSummary:elgFrm:j_id_20y"),
    //FavorableDeterminationSecurityCase("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[2]"),//old
    FavorableDeterminationSecurityCase("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul/li[2]"),
    //SummaryTab("//*[@id='majorTabPanel:adjPanel:catsAdjSummary']/ul/li[5]/a"),//old
    SummaryTab("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[5]/a"),
    
    CompleteButton("majorTabPanel:adjPanel:catsAdjSummary:summaryForm:grantForm"),
    ReferButton("majorTabPanel:adjPanel:catsAdjSummary:summaryForm:deferGrant"),
    UpdatingCaseDialog(""),
    CompleteYesButton("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2u4']"),
    CompleteNoButton(""),
    //CloseCaseButton("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:grantForm']"),//old
    CloseCaseButton("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryCloseCaseId']"),
    
    CloseCaseButtonForHSPD12("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:grantHspd12Form']"),
    CloseCaseButtonForSuitability("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:grantSuitabilityForm']"),
            
    CompleteYes1Button("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2ud']"),
    //CompleteYes2Button("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2p8']"),//old
    CompleteYes2Button("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryUpdatingCaseYesButton']"),
    
          
    Inbox("majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2p1"),
    Inventory("majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_362"),
    Reassign("majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_363"),
    
    PhaseDueProcess("majorTabPanel:caseDetailsPanel:caseDetermination"),
    SORClosed("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']/tr[1]/td[2]"),
    ActionsDropDown("majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button"),

                
   
    
    //SUPPLEMENTAL SOR RFA
    SupplementalSORRequest("majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu5"),
    
   
    createCaseDivision("//*[@id='createCaseForm:j_id_6tm']/div[3]/span"),
    
    //SUPPLEMENTAL INFORMATION RFA
    SelectSupplementalInformationRequest("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu4']/span"),
    RFAWindowForSupplementalInformationRequest("RFARequestForm:selectedRfaDescr"),
    CommentsCharacterCount("RFARequestForm:supInfoCommentCounter"),
    
    ///SOR Civ/Mil
    ActionsDropdown("majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button"),
    StatementofReasonSORLink("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu3']/span"),
    
    
    
    
    
    
    
    //CONDITIONAL DETERMINATION RFA
    RFAsTab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[7]/a"),
    ConditionalDeterminationRequest("majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu6"),
    SendRFA("RFARequestForm:sendRFA"),
    AddDocumentButtonInRFA("RFARequestForm:j_id_4zu:j_id_50d"),
    DocumentTypeDropDown("documentUploaderForm:editAddDocumentMessagesDocType_label"),
    ConditionalDeterminationTypeFromDropdown("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[2]"),
    AddDocumentInsideUploader("documentUploaderForm:documentUploadAddButtonId"),
    SendButtonInsideUploader("RFARequestForm:sendRFA"),
    PendingCSRRFAsInPortal("commPanel:pendingCsrRfa"), 
    ClaimTask("majorTabPanel:j_id_1l0:conditionalPortalClaim"),
    PortalAddDocumentButton("majorTabPanel:j_id_1oi:j_id_1zg:j_id_1zz"),
    PortalAddDocumentButtonInsideUploader("documentUploaderForm:documentUploadAddButtonId"),
    AcknowledgementDateCalendar("majorTabPanel:j_id_1oi:conditionalAckDateId_input"),
    SendConditionalDeterminationFromPortal("majorTabPanel:j_id_1oi:conditionalPortalSubmit"),
    
    //MEDICAL EVALUATION RFA
    RFATab("majorTabPanel:caseDetailsPanel:subjtabview1"),
    CaseInboxInput("cpanelForm:mainSearchCase"),
    SearchCase("cpanelForm:searchCaseManagement"),
    RequestingSMODropDown("majorTabPanel:caseDetailsPanel:caseSMOSelectOne_label"),
    SelectSMOFromDropDown("majorTabPanel:caseDetailsPanel:caseSMOSelectOne_panel"),
    SelectMedicalEvaluationRequest("majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu7"),
    RequestForActionWindow("RFARequestDialog"),
    RFAWindowForMedEval("RFARequestForm:selectedRfaDescr"),
    AddDocumentButtonInRFAWindow("RFARequestForm:j_id_4z3"),
    UploadDocumentWindowMedEval("documentUploaderDialogId_title"),
    DocumentNameInput("documentUploaderForm:editAddDocumentMessagesDocName"),
    DocumentDescriptionInput("documentUploaderForm:editAddDocumentMessagesDocDescription"),
    DocumentTypeDropDownMedEval("documentUploaderForm:editAddDocumentMessagesDocType_label"),
    RequestForMedicalEvalSelectFromDropDown("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[2]"),
    ChooseDocumentButton("documentUploaderForm:fileUploadId_input"),
    AddDocButtonInUploadDocumentWindow("documentUploaderForm:documentUploadAddButtonId"),
    CancelUploadDocumentWindow("documentUploaderForm:documentUploadCancelButtonId"),
    SendButtonRFAMedEval("RFARequestForm:sendRFA"),
    PointAfterSendingRFA("prodPanel:earnedPointsValue"),
    CloseRFAMedEvalWindow("RFARequestForm:closeWindowId"),
    PortalTaskInbox(""),
    PortalMedEvalClaimTask("majorTabPanel:j_id_1oi:medEvalPortalClaim"),
    PortalAddDocButton("majorTabPanel:j_id_1oi:j_id_22c:j_id_22v"),
    PortalAddDocButtonInUploadDocWindow("documentUploaderForm:documentUploadAddButtonId"),
    PortalAckDateCalendarIcon("majorTabPanel:j_id_1oi:medEvalAckDateId_input"),
    PortalMedEvalReqExtensionInput("majorTabPanel:j_id_1oi:medEvalExtJustificationId"),
    PortalMedEvalReqExtensionButton("majorTabPanel:j_id_1oi:medEvalRequestExtId"),
    PortalMedEvalSendButton("majorTabPanel:j_id_1oi:medEvalPortalSubmit"),
    PortalRequestExtensionInput("majorTabPanel:j_id_1n9:medEvalExtJustificationId "),
   VerifyRFAInProcess ("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']/tr[*]/td[2]"),
   PortalLogOff("j_id_17:logoff"),
   PortalSelectTaskFromInbox("majorTabPanel:SubTabletasks:2:jvsedtdescr"),
   
    //LOD/LOR Case In Appeals
   CATSActionsDropDown("majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button"),
   LetterOfDenialAndRevocationFromActionsDropDown("majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu1"),
   AddDocButtonInsideLODLORRFAWindow("RFARequestForm:j_id_4i8:j_id_4ir"),
   AddDocButtonInLODLORUploadDocumentWindow("documentUploaderForm:documentUploadAddButtonId"),
   RequestForLODLORSelectFromDropDown("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[2]"),
   SendButtonLODLORRFA("RFARequestForm:sendRFA"),
   PortalLODClaimButton("majorTabPanel:j_id_1n9:lodPortalClaim"),
   PortalLODAddDocumentButton("majorTabPanel:j_id_1n9:j_id_1sa:j_id_1st"),
   PortalLODAckDateCalendar("majorTabPanel:j_id_1n9:lodAckDateId_input"),
   PortalSubjectWillAppealCheckBox("majorTabPanel:j_id_1n9:subjWillAppealId"),
   PortalSaveLOD("majorTabPanel:j_id_1n9:lodPortalSave"),
   PortalLODSendButton("majorTabPanel:j_id_1n9:lodPortalSubmit"),
   VerifyLODTaskIsPresentInPortal("//*[@id='majorTabPanel:SubTabletasks_data']/tr[*]/td[2]"),
   LogOff("j_id_17:logoff"),
   
    
    //RECONSIDERATION MEMO
    SelectFavorableDetermination(""),
    SaveButtonForDetermination(""),
    ReconsiderationCSRPresent(""),
    AddDocButton(""),
    UploadDocumentWindow(""),
    DocumentName(""),
    DocumentDescription(""),
    DocumentTypeDropDown1(""),
    CaseIsDP(""),
    ReconsiderationMemoFromDropdown(""),
    SelectNotificationDetailsInPortal(""),
    NotificationDetailsWindowOpens(""),
    SelectAttachedDocumentInNotificationDetails(""),
    
   // INTERROGATORY INDUSTRY
   SelectInterrogatoryRequestIndustry("majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu8"),
   RFAWindowForInterrogatory("RFARequestForm:selectedRfaDescr"),
   AddDocumentButtonInInterrogatoryRFAWindow("RFARequestForm:j_id_52m:j_id_535"),
   AddDocButtonInUploadDocInterrogatoryWindow("documentUploaderForm:documentUploadAddButtonId"),
   DocumentTypeDropDownInterrogatory ("documentUploaderForm:editAddDocumentMessagesDocType_label"),
   RequestForIndustryInterrogatoryFromDropDown("documentUploaderForm:editAddDocumentMessagesDocType_panel"),
   SendButtonForInterrogatory("RFARequestForm:sendRFA"),
   ClaimTaskAsProcessTeam("majorTabPanel:taskDetailFormId:interrogatoryClaimButtonId"),
   AddDocButtonInProcessTeamTask("majorTabPanel:taskDetailFormId:j_id_46t:j_id_47c"),
   AddDocButtonInProcessTeamUploader("documentUploaderForm:documentUploadAddButtonId"),
   ProcessTeamMailedDateCalendarIcon("majorTabPanel:taskDetailFormId:interrogatoryMailedDateId_input"),
   ProcessTeamInterrogatorySendButton("majorTabPanel:taskDetailFormId:interrogatorySendButtonId"),
   ProcessTeamAddDocButtonInUploader("documentUploaderForm:documentUploadAddButtonId"),
   
   ProcessTeamDocTypeDropDown("documentUploaderForm:editAddDocumentMessagesDocType_label"),
   ProcessTeamSelectFromDOCTypeDropDown("documentUploaderForm:editAddDocumentMessagesDocType_panel"),
   
   PortalDocTypeDropDown("documentUploaderForm:editAddDocumentMessagesDocType_label"),
   PortalSelectTypeFromDropDown("documentUploaderForm:editAddDocumentMessagesDocType_panel"),
   
   PortalInterrogatoryClaim("majorTabPanel:j_id_1oi:interrogatoryPortalClaim"),
   AddDocButtonInPortalTask("majorTabPanel:j_id_1oi:j_id_25d:j_id_25w"),
   AddDocButtonInPortalUploader("documentUploaderForm:documentUploadAddButtonId"),
   PortalInterrrogatoryCalendarIcon("majorTabPanel:j_id_1oi:interrogatoryAckDateId_input"),
   
   PortalInterrogatorySendButton("majorTabPanel:j_id_1oi:InterrogatoryPortalSubmit"),
   
   
   
   
   
   
   
   
    
    
//*****************************************************************************************************    
    
    
    //Hierarchy
    ManageHierarchyLink("cpanelForm:manageHierarchyLink"),
    LookUpUserForExecChief("majorTabPanel:cafHierarchyTreeTableId:0:buttonLookupChiefUser"),
    SelectZaneSchroeder("cafSearchUserTableId_data"),
    ExpandCAF("majorTabPanel:cafHierarchyTreeTableId_node_0"),
    LookUpUserForDivAF("majorTabPanel:cafHierarchyTreeTableId:0_6:buttonLookupChiefUser"),
    SelectHazelButlerAsDivChief("cafSearchUserTableId_data"),
    LookUpUserForBranchB("majorTabPanel:cafHierarchyTreeTableId:0_6_0:buttonLookupChiefUser"),
    SelectTracyBeckerAsBranchChief("cafSearchUserTableId_data"),
    ExpandBranchB("majorTabPanel:cafHierarchyTreeTableId_node_0_6_0"),
    LookUpUserforTeam6("majorTabPanel:cafHierarchyTreeTableId:0_6_0_0:buttonLookupChiefUser"),
    SelectNolanCarrilloAsTeamChief("cafSearchUserTableId_data"),

    
    AddMember("majorTabPanel:HierarchyEntityUserListTabId:buttonLookupAddNewMember"),
    AddLydiaPadillaAsAdj("//*[@id='cafSearchUserTableId_data']/tr[6]/td[2]"),
    SaveMember("majorTabPanel:HierarchyEntityUserListTabId:saveTeamDetails"),
    AssertConfirmation("majorTabPanel:HierarchyEntityUserListTabId:j_id_d3"),
    ConfirmationYes("majorTabPanel:HierarchyEntityUserListTabId:saveTeamDetails"),
    MemberAddedSuccessfully("maingrowl_container"),
    
    
    
//*************************************REPORTING*****************************************************
    SelectReporting("appMenu:transReporting"),
    TransferToDISSReportingYes("appMenu:transfer1DissPortalYes"),
    TransferToDISSReportingNo ("appMenu:transfer1DissPortalNo"),
    ReportingLogOff("j_id_17:logoff"),
    ViewReportButton("majorTabPanel:activeCaseBeanreportFields:j_id_5n"),
    ResetButton("majorTabPanel:activeCaseBeanreportFields:j_id_5p"),
    OutputFormatDropDownReports("majorTabPanel:activeCaseBeanreportCntl:activeCaseBeanselect1"),
    SelectExcelFromDropDown(""),
    SelectOrgLevelDropDown("majorTabPanel:activeCaseBeanreportFields:orglevelSelect"),
    SelectAirForceDiv("//*[@id='majorTabPanel:activeRolesPermissionBeanreportFields:divSelection']/option[4]"),
  
//ACTIVE CASE REPORT    
    ExpandCATSStandardReports("cpanelForm:reportPanel2_toggler"),
    
    SelectActiveCaseLink("cpanelForm:j_id_2v"),
    WorkflowReportLink("cpanelForm:workFlowLinkId"),
    ActiveCasePDFReportOpen("majorTabPanel:activeCaseBeanreportForm:activeCaseBeanrptpnl1_content"),
    LastActionButton("majorTabPanel:activeCaseBeanreportFields:activeCaseBeanrdo1:1"),
    IRTPACasesButton("majorTabPanel:activeCaseBeanreportFields:activeCaseBeanrdo2:1"),
    NonIRTPACasesButton("majorTabPanel:activeCaseBeanreportFields:activeCaseBeanrdo2:2"),
    
//WORKFLOW DETAIL REPORT
    ExpandCATSManagementReports("cpanelForm:reportManagePanel_toggler"),
    SelectWorkflowDetailLink("cpanelForm:workFlowLinkId"),
    
    
  
   
    

//*************************************Letter Template******************************
    templateCount("//*[@id='majorTabPanel:templatemanageform:templatetable_data']/tr[*]/td[1]"),
    
    docName("//*[@id='majorTabPanel:templatemanageform:templatetable_data']/tr[14]/td[3]"),
    docType("//*[@id='majorTabPanel:templatemanageform:templatetable_data']/tr[14]/td[1]"),
    uploadDocLink("//*[@id='majorTabPanel:templatemanageform:templatetable:13:j_id_5b3']"),
    
    
// ******************************** Creating USER varible ****************************
    generateUserID("//*[@id='majorTabPanel:catsUserDetailForm:genId']"),
    userID("//*[@id='majorTabPanel:catsUserDetailForm:regid']"),
    userPassword("//*[@id='majorTabPanel:catsUserDetailForm:regpwd']"),

    
    userPasswrod("//*[@id='majorTabPanel:catsUserDetailForm:regpwd']"),
    userPasswordConfirm("//*[@id='majorTabPanel:catsUserDetailForm:pwdconf']"),
    userSSN("//*[@id='cpanelForm:catsUserSsn']"),
    userSSNSearch("//*[@id='cpanelForm:searchUserManagement']"),
    userCAF("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailOrganizationPickList']/tbody/tr/td[1]/ul/li/table/tbody/tr/td"),
    userCAFSelect("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailOrganizationPickList']/tbody/tr/td[2]/button[1]"),
    userAdjudicator("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
    userProcessTeam("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td"),
    userExecutive("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
    userDivision("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[4]/table/tbody/tr/td"),
    userBranch("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[5]/table/tbody/tr/td"),
    userTeam("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[6]/table/tbody/tr/td"),
    userGeneralCounsel("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[7]/table/tbody/tr/td"),
    userTECT("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[8]/table/tbody/tr/td"),
    userQCReviewer("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[9]/table/tbody/tr/td"),
    userTrainee("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[11]/table/tbody/tr/td"),
    userITTFScreener("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[12]/table/tbody/tr/td"),
    userPsychEvaluation("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[13]/table/tbody/tr/td"),
    userOPMLiaison("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[14]/table/tbody/tr/td"),
    
    userRoleSelect("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[2]/button[1]"),
    userSelectSave("//*[@id='majorTabPanel:catsUserDetailForm:userDetailsSave']"),
    userJpass("//*[@id='majorTabPanel:catsUserDetailForm:jpasUserId']"),
    removeUserAdjudicator("//*[@id='majorTabPanel:catsUserDetailForm:userDetailsSave']"),
    removeRole("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[2]/button[3]"),
    verifyRole("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolesFieldSet_content']"),
    
//---------SB-SMO Relationship---------------------------------
SMO_Tab("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[4]/a"),
Create_New_Cat("//*[@id='majorTabPanel:subjtabview:categoriesDataTable:j_id_ys']"),
//Create_New_Cat("//span[text()='Create New Category']"), 
New_Cat_dropdown("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1']/div[3]/span"),
Civ_Employee("//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']/div/ul/li[4]"),
Next_Button("//*[@id='categoryCreateForm:j_id_3nm']"),
Category_CheckBox("//*[@id='categoryCreateForm:j_id_3nx']/div[2]"),
Category_NextButton("//*[@id='categoryCreateForm:j_id_3p6']"),
Create_BT("//*[@id='categoryCreateForm:categorycreatesubmitbuttonid']"),
AF_SMO("//li[@data-label='AF']"),
//Establish New Relationship
Estab_New("//*[@id='majorTabPanel:subjtabview:relationshipTable:j_id_zk']"),
//Estab_New("//span[text()='Establish New Relationship']"),
Category_RadioBT("//*[@id='addRelationshipWizardForm:subjcategorytable:0:selcatval']/span[1]"),
owning("//*[@id='addRelationshipWizardForm:relationshiptypeselection']/div/span"),
Relationship_NextBT("//*[@id='addRelationshipWizardForm:j_id_4p1']"),
SaveBT("//*[@id='addRelationshipWizardForm:j_id_4td']"),

//------------ RFA ------------------------//
    RFAType("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']/tr[*]/td[2]"),
    RFASendBtn("//*[@id='RFARequestForm:sendRFA']"), 
    CaseStatus("//*[@id='majorTabPanel:caseDetailsPanel:caseStatusValue']"),
    Upload_AddBtn("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    RFA_ActionBtn("//span[text()='Actions']"), 
    CATSUsrRole("//*[@id='top_navigation']/div/div/div[2]/div/div/p[2]/span"),
    TECT_RFA("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu12']/span"),
    Psychologist_RFA("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu11']/span"),
    GeneralCounsel_RFA("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu10']/span"),
    RFA_Window("//*[@id='RFARequestForm:selectedRfaDescr']"),
    RFA_Comment("RFARequestForm:tectReviewCommentId"),
    RFA_GCComment("//*[@id='RFARequestForm:counselReviewCommentId']"),
    RFA_PsychComment("RFARequestForm:medicalReviewCommentId"),
    SOR_RFA("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu3']/span"),
    RFA_Tab("//a[contains(@href,'RFAs')]"),
    RFA_Sent("RFARequestForm:sendRFA"),
    SOR_Doc_Type("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Statement of Reason (SOR) Letter']"),
    RFA_Cancel("RFARequestForm:cancelRFA"),
    RFACancelConfrm("cancelRfaConfirmYes"),
    MPC_Dropdown("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu']/div[3]/span"),
    MPC_Civilian("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu_panel']/div/ul/li[@data-label='Civilian']"),
    MPC_Contractor("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu_panel']/div/ul/li[@data-label='Contractor']"),
    MPC_WarOffReserve("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu_panel']/div/ul/li[@data-label='Warrant Officer Reserve']"),
    NoRFAS("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']/tr/td"),
    RFA_Task_Inbox("//*[@id='commPanel:taskInboxLink']"),
    RFA_DOHA("//td[text()='RFA Industry SOR : Send to DOHA']"),
    RFA_Claim("//*[@id='majorTabPanel:taskDetailFormId:sorClaimButton']"),
    RFA_Case_phase("//*[@id='majorTabPanel:caseDetailsPanel:caseDetermination']"),
    RFA_Case_Status("//*[@id='majorTabPanel:caseDetailsPanel:caseStatusValue']"),
    Prod_Points("//*[@id='prodPanel:prodDashboard2']/tbody/tr[1]/td[2]"),
    DOHAMailDt("//*[@id='majorTabPanel:taskDetailFormId:popupDOHADATE_input']"),
    DOHAResponse("//*[@id='majorTabPanel:taskDetailFormId:popupDOHAResponseDate_input']"),
    DOHA_Doc("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='DOHA Recommendation']"),
    Save_SOR("//*[@id='majorTabPanel:taskDetailFormId:sorSaveButton']"),
    PT_Growler("//*[@id='maingrowl_container']/div/div/div[2]/span"),
    PT_SaveBT("//*[@id='majorTabPanel:taskDetailFormId:sorSaveButton']"),
    PT_SendBT("//*[@id='majorTabPanel:taskDetailFormId:sorSendButtonId']"),
    PT_UploadDoc("//*[@id='majorTabPanel:taskDetailFormId:j_id_2fq']"),
    SMOMailDt("//*[@id='majorTabPanel:taskDetailFormId:popupSMOSendDate_input']"),
    Adj_Notification("//*[@id='majorTabPanel:SubTableNotifications_data']/tr[*]/td[4]"),
    MedicalEvalReq("//span[text()='Medical Evaluation Request']"),
    UploadDocRFA("//*[@id='RFARequestForm:j_id_3e7']"),
    AddDocRFA("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    RFA_Choose("//*[@id='documentUploaderForm:fileUploadId_input']"),
    Supp_SOR_RFA("//span[text()='Supplemental SOR Request']"),
    SOR("//span[text()='Statement of Reason(SOR)']"),
    SORWindow("//*[@id='RFARequestForm:rfagroup']/span"),
    RescindErrorMsg("//*[@id='RFARequestForm:RFARequestFor']/div/ul/li/span[1]"),
    SOR_Rescind("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='SOR Rescinded - Pending Investigation Letter']"),
    OtherAgency("//span[text()='Other Agency Files Request']"),
    OtherAgencyComment("RFARequestForm:rfaOtherAgencyCommentId"),
    RFA_Guidelines("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[3]/a"),
    ApplyGuide("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_xo:guidelineRadioButtonId']/tbody/tr/td[1]/div/div[2]/span"),
    Allegiance("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[2]/span"),
    No_Disqualify_Cond("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:guidelineRadioButtonId']/tbody/tr/td[3]/div/div[2]"),
    DTMN_Save_Bt("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationFormId:adjudicationSaveDeterminationId']"),
    SummaryYesBT("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2th']"),
    SummaryTb("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[5]/a"),
    Referbt("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_11o:adjudicationSummaryReferCaseId']"),
    ReferYesBT("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2pg']"),
    DTMN_Favorable("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[2]"),
    Req_Extension_bt("majorTabPanel:j_id_1s3:sorRequestExtId"),
    ReqExt_ErrMsg("//*[@id='majorTabPanel:j_id_1s3:rfaSorSecurityOfficerErrorMessagesId']/div/ul/li/span[1]"),
    ErrMSG_Add_Doc("//*[@id='documentUploaderForm:documentUploaderFormMessagesId']/div/ul/li"),
    SORPortalClaim("//*[@id='majorTabPanel:j_id_1s3:SORPortalClaim']"),
    SORAcknowDueDate("majorTabPanel:j_id_1s3:sorAckDueDateId_input"),
    SORAcknowDate("//*[@id='majorTabPanel:j_id_1s3:sorAckDateId_input']"),
    CondAckDate("//*[@id='majorTabPanel:j_id_1sd:conditionalAckDateId_input']"),
    ExtJustTxtBx("//*[@id='majorTabPanel:j_id_1s3:sorExtJustificationId']"),
    SORPortalSV("//*[@id='majorTabPanel:j_id_1s3:SorPortalSave']"),
    SORPortalSend("//*[@id='majorTabPanel:j_id_1s3:SORPortalSubmit']"),
    Portal_UpDoc("//*[@id='majorTabPanel:j_id_1s3:j_id_1ul']"),
    Portal_AddDoc("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    ACKReceipt("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Acknowledgement of Receipt']"),
    PT_SORResponseDt("majorTabPanel:taskDetailFormId:sorResponseDueDateId"),
    SentAppealsDt("//*[@id='majorTabPanel:taskDetailFormId:sorSentToAppealsDateId_input']"),
    Files_Reviewed_Tb("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[2]/a"),
    Files_Reviewed_SV("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:fileReviewedFormId:saveFileReviewButtonId']"),
    AppealsAdjWindow("//*[@id='appealsDialogId_title']"),
    Close_Appealsbt("//*[@id='j_id_50a:closeAppeals']"),
    SubjectResponse("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Subject Response']"),
    lvlCodeDp("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId']/div[3]"),
    SecretLvlCode("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_panel']/div/ul/li[@data-label='Secret']"),
    ExcepCode("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationExceptionId_panel']/div/ul/li[@data-label='None']"),
    DTMN_Save("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSaveDeterminationId']"),
    FilesSave("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:fileReviewedFormId:saveFileReviewButtonId']"),
    Conditional_Alert("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryId']/div"),
    CaseStatusValue("//*[@id='majorTabPanel:caseDetailsPanel:caseStatusValue']"),
    Reciept("//*[@id='documentUploaderForm:editAddDocumentMessagesDocName']"),
    NoAction("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[@data-label='No Action']"),
    Favorable_DTMN("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul/li[@data-label='Favorable']"),
    LOJ_DTMN("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[@data-label='Loss Of Jurisdiction']"),
    NDM_DTMN("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[@data-label='No Determination Made']"),
    Denied_DTMN("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[@data-label='Denied']"),
    Revoke_DTMN("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[@data-label='Revoke']"),
    None_DTMN("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationFormId:adjudicationDeterminationCodeId_panel']/div/ul/li[@data-label='None']"),
    DueProcessInbox("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_11o:j_id_14i']"),
    Expand_Guidelines("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_xd:j_id_y1']/h3[5]/span"),
    PersonalCondDisql("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_xd:j_id_y1:4:j_id_y3:0:j_id_y5:0:j_id_y8']/div[2]"),
    MTGT_PersonalCond("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_xw:4:j_id_xy:1:j_id_y0:0:j_id_y3']/div[2]/span"),
    Cond_RFA("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu6']/span"),
    Cond_letter("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Conditional Determination Letter']"),
    
  //Add Notes and Documents to Subject 
    Add_DocBT("//*[@id='majorTabPanel:subjtabview:subjectDocumentDetailTable:addDocumentButton']"),
    Add_NoteBT("//*[@id='majorTabPanel:subjtabview:subjectDetailNotes:addNoteButton']"),
    Case_DetailsTab("//a[contains(@href, '#majorTabPanel:CaseDetail')]"),
    Note_Details("//*[@id='majorTabPanel:subjtabview:addEditCaseNoteForm:subjectNoteDetailsTextArea']"),
    Cancel_Note("//*[@id='majorTabPanel:subjtabview:addEditCaseNoteForm:cancelNewCaseNoteButton']"),
    Save_Note("//*[@id='majorTabPanel:subjtabview:addEditCaseNoteForm:addSubjectNoteSubmit']"),
    Note_Created("//*[@id='majorTabPanel:subjtabview:addEditCaseNoteForm:addSubjectNoteSubmit']"),
    Edit_Note("//*[@id='majorTabPanel:subjtabview:subjectDetailNotes:0:jvsSubjectNotesEditNoteButton']"),
    Remove_Note("//*[@id='majorTabPanel:subjtabview:subjectDetailNotes:0:jvsSubjectNotesRemoveNoteButton']"),
    Edited_text("//*[@id='majorTabPanel:subjtabview:subjectDetailNotes_data']/tr/td[3]"),
    Add_DocumentBT("//*[@id='majorTabPanel:subjtabview:subjectDocumentDetailTable:addDocumentButton']"),
    Doc_CancelBt("//*[@id='documentUploaderForm:documentUploadCancelButtonId']"),
    Uploaded_By("//*[@id='majorTabPanel:subjtabview:subjectDocumentDetailTable_data']/tr/td[3]"),
    Doc_Delete("//*[@id='majorTabPanel:subjtabview:subjectDocumentDetailTable:0:jvsSubjectDocumentsRemoveDocButton']"),
    Conf_DeleteNote("//*[@id='majorTabPanel:subjtabview:removeSubjectNoteConfirmId']"),
    
  //Upload Documents from Documents tab
    Doc_Tab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[3]/a"),
    Doc_Add("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseDocumentsTableId:addDocumentButton']"),
    Other_Doc("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[@data-label='Other']"),
    Existing_Docdropdown("//*[@id='RFARequestForm:j_id_3eb']/div[3]/span"),
    Existing_Other_Doc("//*[@id='RFARequestForm:j_id_3ns_panel']/div/ul/li[@data-label='Other Doc']"),
    Add_BT("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
    Ex_Add_BT("//*[@id='RFARequestForm:j_id_3nu']"),
    DocumentTypedp("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType']/div[3]/span"),
        
 //Assign case to Adjudicator
   NotAssigned("//*[@id='majorTabPanel:caseDetailsPanel:assigneeLinkDisplay']"),
   Member_dp("//*[@id='majorTabPanel:catsToUserId']/div[3]/span"),
  // SB_Adjudicator("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[@data-label='Rich greg']]"),
   Case_InvenDp("//*[@id='majorTabPanel:catsToUserLocation']/div[3]/span"),
   Inventory_Adj("//*[@id='majorTabPanel:catsToUserLocation_panel']/div/ul/li[@data-label='Inventory']"),
   Case_Assign("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul/li/table/tbody/tr/td[1]/span"),
   MoveCase("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[2]/button[1]"),
   AssignSave("//*[@id='majorTabPanel:maSubmits']"),
   SB_Adj("//*[@id='top_navigation']/div/div/div[2]/div/div/p[1]/span"),
   Assign_Adj("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[18]"),
   //Assign_Adj("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[@data-label='Rich greg']"),
   Adj_dp("//*[@id='majorTabPanel:catsToUserId']/div[3]/span"),
 //********************* CAT Manage Priorities******************************
    
	caseAssignmentTab("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab']/ul/li[1]/a"),
	inProgressCaseAssignment("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']/tbody/tr[1]/td[1]/div/div[2]"),
	newCaseAssignment("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']/tbody/tr[2]/td[1]/div/div[2]"),
	newCasesTab("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab']/ul/li[2]/a"),
	chooseCaseTypeSelection("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
	selectSingleCaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[2]/button[1]"),
	selectAllCasesArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[2]/button[2]"),
	removeSingleCaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[2]/button[3]"),
	removeAllCasesArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[2]/button[4]"),
	selectedCaseInvType("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
	rankingSingleCaseUpArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[4]/button[1]"),
	rankingAllCasesUpArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[4]/button[2]"),
	rankingSingleCaseDownArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[4]/button[3]"),
	rankingAllCaseDownArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserInvTypePickList']/tbody/tr/td[4]/button[4]"),
	inProcessCasesTab("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab']/ul/li[3]/a"),
	choosePhase("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[1]/ul/li/table/tbody/tr/td"),
	removeSelectedPhase("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[3]/ul/li/table/tbody/tr/td"),
	selectSinglePhaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[2]/button[1]"),
	selectAllPhasesArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[2]/button[2]"),
	removePhaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[2]/button[3]"),
	removeAllPhasesArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[2]/button[4]"),
	upPhaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[4]/button[1]"),
	upAllPhaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[4]/button[2]"),
	downPhaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[4]/button[3]"),
	downAllPhaseArrow("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[4]/button[4]"),
	priorityProgramsTab("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab']/ul/li[4]/a"),
	selectTier4("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:priorityProgramTier4']/div[2]"),
	selectTier3("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:j_id_ez']/div[2]"),
	selectTier2("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:priorityProgramTier2']/div[2]"),
	saveSelections("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"),
	saveSelectionConfirmation("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"),
	autoAssignEligible("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:memberAssignCasesCB']/div[2]"),
	expandDoDCAF("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]/span"),
	expandNavyDivision("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_4']/td[1]/span[2]"),
	executiveExpandNavyDivision("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_6']/td[1]/span[2]"),
	expandDivision("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]/span"),
	expandBranch("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]/span"),
	expandBranchB("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_0']/td[1]/span[2]"),
	executiveExpandBranchB("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_6_0']/td[1]/span[3]"),
	adjudicatorOne("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:0:tdMemberName']"),
	adjudicatorTwo("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:1:tdMemberName']"),
	adjudicatorThree("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:2:tdMemberName']"),
	adjudicatorFour("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:3:tdMemberName']"),
	adjudicatorFive("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:4:tdMemberName']"),
	addPermissions("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[1]"),
	addAllPermissions("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[2]"),
	removePermissions("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[3]"),
	removeAllPermissions("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[4]"),
	
	    
//********************* CATS Manual Assignment******************************   
	manualAssignment("//*[@id='cpanelForm:manualAssignmentLink']"),
	fromAdjudicatorList("//*[@id='majorTabPanel:catsFromUserId_label']"),
	manualFromAdjOne("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[1]"),
	manualFromAdjTwo("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[2]"),
	manualFromAdjThree("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[3]"),
	manualFromAdjFour("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[4]"),
	manualFromAdjFive("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[5]"),
	manualFromAdjSix("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[6]"),
	manualFromAdjSeven("//*[@id='majorTabPanel:catsFromUserId_panel']/div/ul/li[7]"),
	catsFromUserLocationDropDown("//*[@id='majorTabPanel:catsFromUserLocation_label']"),
	catsFromUserLocationInbox("//*[@id='majorTabPanel:catsFromUserLocation_panel']/div/ul/li[3]"),
	catsFromUserLocationInventory("//*[@id='majorTabPanel:catsFromUserLocation_panel']/div/ul/li[2]"),
	catsFromUserLocationUnassigned("//*[@id='majorTabPanel:catsFromUserLocation_panel']/div/ul/li[1]"),
	assignCaseRightArrow("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[2]/button[1]"),
	assignAllCasesRightArrow("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[2]/button[2]"),
	assignCaseLeftArrow("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[2]/button[3]"),
	assignAllCasesLeftArrow("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[2]/button[4]"),
	caseOneLeftSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td[2]/span"),
	caseTwoLefttSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td[1]"),
	caseThreeLeftSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td[1]"),
	caseFourLeftSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul/li[4]/table/tbody/tr/td[1]"),
	caseFiveLefttSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[1]/ul/li[5]/table/tbody/tr/td[1]"),
	toAdjudicatorList("//*[@id='majorTabPanel:catsToUserId_label']"),
	manualToAdjOne("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[1]"),
	manualToAdjTwo("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[2]"),
	manualToAdjThree("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[3]"),
	manualToAdjFour("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[4]"),
	manualToAdjFive("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[5]"),
	manualToAdjSix("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[6]"),
	manualToAdjSeven("//*[@id='majorTabPanel:catsToUserId_panel']/div/ul/li[7]"),
	catsToUserLocationDropDown("//*[@id='majorTabPanel:catsToUserLocation_label']"),
	catsToUserLocationInbox("//*[@id='majorTabPanel:catsToUserLocation_panel']/div/ul/li[3]"),
	catsToUserLocationInventory("//*[@id='majorTabPanel:catsToUserLocation_panel']/div/ul/li[2]"),
	catsToUserLocationUnassigned("//*[@id='majorTabPanel:catsToUserLocation_panel']/div/ul/li[1]"),
	caseOneRightSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[3]/ul/li[1]/table/tbody/tr/td[1]"),
	caseTwoRightSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[3]/ul/li[2]/table/tbody/tr/td[1]"),
	caseThreeRightSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[3]/ul/li[3]/table/tbody/tr/td[1]"),
	caseFourRightSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[3]/ul/li[4]/table/tbody/tr/td[1]"),
	caseFiveRightSide("//*[@id='majorTabPanel:casePickListId']/tbody/tr/td[3]/ul/li[5]/table/tbody/tr/td[1]"),
	saveAssignment("//*[@id='majorTabPanel:maSubmits']"),
	verifySaveAssignment("//*[@id='majorTabPanel:yesMASubmits']"),
	noSaveAssignment("//*[@id='majorTabPanel:noMASumbits']"),
	createSubject("//*[@id='cpanelForm:createSubjectLink']"),
	createSubjectComplete("//*[@id='majorTabPanel:createsub:grantForm']"),
	confirmCreateSubjectYes("//*[@id='majorTabPanel:createsub:yesGrandForm']"),
	confirmCreateSubjectNo("//*[@id='majorTabPanel:createsub:donotGrantForm']"),
	createCaseFromSubjectActions("//*[@id='majorTabPanel:subjtabview:subactions_button']"),
	SB_Case_id("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"),
	investigationTypeDropDown("//*[@id='createCaseForm:createCaseInvType_label']"),
	naclcInvestigationType("//*[@id='createCaseForm:createCaseInvType_panel']/div/ul/li[2]"),
	caseTypeDropDown("//*[@id='createCaseForm:createCaseType_label']"),
	secretCaseType("//*[@id='createCaseForm:createCaseType_panel']/div/ul/li[2]"),
	divisionDropDown("//*[@id='createCaseForm:selectDivisionMenu_label']"),   
	navyDivision("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[7]"),
	createCase("//*[@id='majorTabPanel:subjtabview:createCaseActionsButton']"),
//	createCase("//*[@id='createCaseForm:createCaseButton']"),
	
	
//********************************** Productivity Points******************************************   
	
	productivityLevelDropDown("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId_label']"),  
	executiveTeamDetails("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_6_0_0']/td[1]"),
	divisionTeamDetails("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_0_0']/td[1]"),
	branchTeamDetails("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_0']/td[1]"),
	team5Details("//*[@id='majorTabPanel:teamDetailsItem']/span[2]"),
	enterProductivityHours("//*[@id='prodPanel:eanterProductivtiyHoursLink']"),
	enterRegHoursForMonday("//*[@id='majorTabPanel:tmProdTime:timecardForOneWeek:0:timeweekCellEditor1']/div[1]"),


//***************************************Upload Document************************************************   

	 documentsTab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[5]/a"),
	 addDocumentButton("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseDocumentsTableId:addDocumentButton']"),
	 addDocumnetButtonPopUpWindow("//*[@id='documentUploaderForm:documentUploadAddButtonId']"),
	 UploadDocName("documentUploaderForm:editAddDocumentMessagesDocName"),
	 UploadDocDescp("documentUploaderForm:editAddDocumentMessagesDocDescription"),

//********************************************* CATS User Management Hetal***********************************************************

availablepermissions("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[1]"),
userManagementTabLabel("//*[@id='cpanelForm:userPanel_header']/span"),
userSearchButton("//*[@id='cpanelForm:searchUserManagement']"),
userInformation("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailSubPanel_header']/span"),	
statusDropDown("//*[@id='majorTabPanel:catsUserDetailForm:catsUserStatus']/div[3]/span"),
subjectDetailsLink("//*[@id='majorTabPanel:catsUserDetailForm:subjectDetailButton']"),
adjudicatorRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
selectRoleRightArrow("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[2]/button[1]"),
adjudicatorDropDown("//*[@id='majorTabPanel:catsUserDetailForm:roleMenu_panel']/div/ul/li[2]"),
assignAllPermissionRigthArrow("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[2]"),	
clickOnSaveButton("//*[@id='majorTabPanel:catsUserDetailForm:userDetailsSave']"),	
removeRoleLeftArrow("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[2]/button[3]"),	
clickOnGeneratePassword("majorTabPanel:catsUserDetailForm:genpwd"),
tectRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td"),
qcReviewerRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
executiveChiefRole("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[1]/table/tbody/tr/td"),
divisionChiefRole("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td"),	
branchChiefRole("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
teamChiefRole("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[4]/table/tbody/tr/td"),
ittfScreenerRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[6]/table/tbody/tr/td"),
processTeamRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[2]/table/tbody/tr/td"),
generalCounselRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[3]/table/tbody/tr/td"),
psychEveTeamRole1("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[7]/table/tbody/tr/td"),	
tectRole2("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[4]/table/tbody/tr/td"),
qcReviewerRole2("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[5]/table/tbody/tr/td"),
tectRole5("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[5]/table/tbody/tr/td"),
qcReviewerRole6("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[6]/table/tbody/tr/td"),
ittfScreenerRole7("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[7]/table/tbody/tr/td"),
opmLiaison8("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[8]/table/tbody/tr/td"),
removeAllPermissionsLeftArrow("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[4]"),

//*************Send for Review*******************
Reviewer_Revise("//*[@id='sendForReviewPopupForm:selectReviewDecisionRadio']/tbody/tr[2]/td[1]/div/div[2]"),
ReviewTab("//a[contains(@href,'#majorTabPanel:caseDetailsPanel:subjtabview1:crTab')]"),
DocumentsTab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[3]/a"),
letter_SendForReview("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:templetterForm:lettertable:0:j_id_ps']"),
SendForReviewBT("majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseReviewTableId:SendForReviewButtonId"),
ReviewType("//*[@id='sendForReviewPopupForm:sendForReviewSelectReviewTypeId']/tbody/tr[1]/td[2]"),
ReviewNote("//*[@id='sendForReviewPopupForm:sendForReviewNote']"),
Cancel_Request("majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseReviewTableId:0:j_id_nx"),
Cancel_Window("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:cancelReviewConfirmDialogId']/div[1]"),
Yes_Confirm("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:cancelReviewConfirmId']"),
No_Confirm("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:declineCancelReviewId']"),
ReviewSubmit("//*[@id='sendForReviewPopupForm:sendForCaseReviewButton']"),
ErrorMg("//*[@id='sendForReviewPopupForm:sendForReviewReviewNotesMsgId']/div/ul/li/span[2]"), //error message without adding review notes
Reviewer("//*[@id='majorTabPanel:caseDetailsPanel:reviewerValueCD']"),
AutoAssign("//*[@id='sendForReviewPopupForm:selectReviewTypeRadio']/tbody/tr[1]/td[1]/div/div[2]/span"),
TeamChief("//*[@id='sendForReviewPopupForm:selectReviewTypeRadio']/tbody/tr[3]/td[1]/div/div[2]"),  
DefaultTeamChief("//*[@id='sendForReviewPopupForm:reviewerDescriptionOutputId']"),
RespondToReviewBT("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseReviewTableId:RespondToReviewButtonId']"), //login as reviewer to check this button
ManuallyAssign("//*[@id='sendForReviewPopupForm:selectReviewTypeRadio']/tbody/tr[2]/td[1]/div/div[2]"), //Manually Assign Radio Button
LastAction("//*[@id='majorTabPanel:caseDetailsPanel:lastActionValueCD']"),
Lookup_User("//*[@id='sendForReviewPopupForm:buttonLookupManualAssignedReviewId']"),
Adj_Phase("//*[@id='majorTabPanel:caseDetailsPanel:caseDetermination']"),

//Return to Original adjudicator Window
ReviewDecisionApprove("//*[@id='sendForReviewPopupForm:selectReviewDecisionRadio']/tbody/tr[1]/td[1]/div/div[2]/span"), //Approve
ReviewDecisionRevise("//*[@id='sendForReviewPopupForm:selectReviewDecisionRadio']/tbody/tr[2]/td[1]/div/div[2]"), //Revise
Note("//*[@id='sendForReviewPopupForm:sendForReviewNote']"), //Review Note
SubmitOrigAdj("//*[@id='sendForReviewPopupForm:srSendOriginalAdjudicator']"), //Submit button to send it to original adjudicator
Case_In("//*[@id='majorTabPanel']/ul/li/a"),
//History Tab
//HistoryTab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[2]/a"),
History_Tab("//a[contains(@href,'History')]"),
Action("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId:chCol1']/span"),
Action_Taken("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId:chCol2']/span"),
DateAndTime("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId:chCol3']/span[1]"),
ReviewStatus("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseReviewTableId_data']/tr[1]/td[5]"), //Status Column
//Create RSI/REO PDF
ManageTemplate("//*[@id='cpanelForm:manageTemplatesLink']"),
EditPencil("//*[@id='majorTabPanel:templatemanageform:templatetable:13:documentAddManageTemplates']"),
//Adjudication Panel
AdjPanel("//*[@id='majorTabPanel:adjPanel']/h3/a"),
RaiseTab("//*[@id='majorTabPanel:adjPanel:catsAdjSummary']/ul/li[1]/a"), // Raise Tab under Adjudication Grid
RSI("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:dispositionMenuButtons']/div[4]/span"),
REO("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:dispositionMenuButtons']/div[1]/span"),
FirstScope("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:scopeSelectOneMenu']/div[3]/span"),
DropdwnFirst("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:scopeSelectOneMenu_panel']/div/ul/li[4]"), //Select Option from the drop down
Secondscope("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:ScopeJustification']/div[3]/span"),
DropdwnSecond("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:ScopeJustification_panel']/div/ul/li[1]"),
RaiseComments("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:raiseTabForm:raiseComments']"),


//**************Files In Safe *******************************
FilesDropDown("//*[@id='majorTabPanel:caseDetailsPanel:caseFilesInSAFE']/div[3]/span"),
YesOption("//*[@id='majorTabPanel:caseDetailsPanel:caseFilesInSAFE_panel']/div/ul/li[2]"),
NoOption("//*[@id='majorTabPanel:caseDetailsPanel:caseFilesInSAFE_panel']/div/ul/li[1]"),

//*************************Determination****************************
Adj_Panel("//*[@id='majorTabPanel:adjPanel']/h3/span"),
Case_Info_Grid("//*[@id='majorTabPanel:caseDetailsPanel']/h3[2]/span"),
Case_Type_Dropdwn("//*[@id='majorTabPanel:caseDetailsPanel:caseTypeSelectOne']/div[3]/span"),
TOP_Secret_SCI("//*[@id='majorTabPanel:caseDetailsPanel:caseTypeSelectOne_panel']/div/ul/li[2]"),
Port_Eligibility("//*[@id='majorTabPanel:subjectEligibility']/div[2]/div"),
SubjectDetails("//*[@id='majorTabPanel:subjSummaryViewDetails']"),
Port_SSN("cpanelForm:pinp"),
Port_SearchBT("cpanelForm:subjectQuickSearchActionId"),
CATS_SSN("//*[@id='majorTabPanel:caseDetailsPanel:ssnPseudoSsnNumberId']"),
CATS_Subject_Detail_View("majorTabPanel:caseDetailsPanel:viewSubjectDetailsLink"),
SubjectNotesDoc("//*[@id='majorTabPanel:subjtabview']/div[1]/ul/li[6]/a"),
Subject_Detail1("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"),
Yes_BT("majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2ju"),
Complete_BT("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:grantForm']"),
No_Disqualify_Ckbox("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:j_id_x5']/div[2]/span"),
Check_Allegiance("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline:allegguidelineTab:allegdisCondTable:0:allegdisCondBoolean']/div[2]"),
Save_Guide("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_xo:saveAllGuidelineId']"),
DTMN_Dropdown("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId']/div[3]"),
DTMN_Menu("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul"),
Loss_Of_Jurisdiction("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityddecision_panel']/div/ul/li[3]"),
Dis_Allegiance("majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[2]/span"),
CATS_Current_Eligibility("majorTabPanel:caseDetailsPanel:currentEligibilityValue"),
SSN_Num("//*[@id='majorTabPanel:caseDetailsPanel:ssnPseudoSsnNumberId']"),
Assignee("//*[@id='majorTabPanel:caseDetailsPanel:assigneeValueCD']"),
Subject_View_Eligibility("//*[@id='majorTabPanel:subjectEligibility']/div[2]/div"),
View_Subject_Details("majorTabPanel:caseDetailsPanel:viewSubjectDetailsLink"),
SubjectDetailsTab("//*[@id='majorTabPanel:subjtabview']"),
SubjectHistoryCase("//*[@id='majorTabPanel:subjtabview:subCaseDat_data']/tr/td[1]"),
CaseIdValue("majorTabPanel:caseDetailsPanel:caseIDValue"),

//***************************Conditional Memo************************************
Security_Determination("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:elgFrm:securityPanel']/h3/span"),
Exception_Codedp("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationExceptionId']/div[3]/span"),
With_Cond("//li[@data-label='Condition']"),
Cond_Yes_BT("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_20v"),
Alert_Ok("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2ul']"),
Close_Bt("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:grantForm']"),
FilesSaved("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:saveFileReviewButtonId']"),
MitigationConditionA("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:j_id_xw:0:j_id_xy:1:j_id_y0:0:j_id_y3']/div[2]"),

//********************************************* CATS Team User Profile Management ***********************************************************
expandArmyDivision("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_5']/td[1]/span[2]"),
expandBranchE("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_5_0']/td[1]/span[3]"),
divisionChiefExpandArmyDivision("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]/span"),
divisionChiefExpandBranchE("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_0']/td[1]/span[2]"),
branchChiefExpandBranchE("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]/span"),
team13("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_5_0_0']/td[1]"),
team13AsDivisionChief("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_0_0']/td[1]"),
team13AsBranchChief("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0_0']/td[1]"),
team13AsTeamChief("//*[@id='majorTabPanel:cafHierarchyTreeTableId_node_0']/td[1]"),
team13Details("//*[@id='majorTabPanel:teamDetailsItem']/span[2]"),
manageTeamUserOne("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:0:tdMemberName']"),
manageTeamUserTwo("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:1:tdMemberName']"),
manageTeamUserThree("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:2:tdMemberName']"),
manageTeamUserFour("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:3:tdMemberName']"),
manageTeamUserFive("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:4:tdMemberName']"),


//********************************************* CATS Supplemental Information CSR ***********************************************************
adjudicatorUnreadNotification("//*[@id='commPanel:unreadNotificationsLInk']"),
existingCase("//*[@id='majorTabPanel:subjtabview:subCaseDat_data']/tr/td[1]"),
viewCSRtab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[6]/a"),
viewDocumentTab("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[5]/a"),
clickCSRTaskInbox("//*[@id='majorTabPanel:SubTabletasks:0:edtTaskName']"),

//********************Create Case For Subject *********************
Inv_Type_Dropdown("//*[@id='createCaseForm:createCaseInvType']/div[3]/span"),
NACLC("//*[@id='createCaseForm:createCaseInvType_panel']/div/ul/li[7]"), //NACLC
Division_Dropdown("//*[@id='createCaseForm:selectDivisionMenu']/div[3]/span"),
Air_Force_Division("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division D (Air Force)']"),
Case_Type_Dropdown("//*[@id='createCaseForm:createCaseType']/div[3]/span"),
Case_Type_Secret("//*[@id='createCaseForm:createCaseType_panel']/div/ul/li[2]"),
Req_SMO_Dropdown("//*[@id='majorTabPanel:caseDetailsPanel:caseSMOSelectOne']/div[3]"),
Select_SMO("//*[@id='majorTabPanel:caseDetailsPanel:caseSMOSelectOne_panel']/div/ul/li[2]"),
Create_Case_BT("//*[@id='createCaseForm:createCaseButton']"),

//-----------------Reporting-Workforce performance------------------/
Exp_MGMT_Reports("//*[@id='cpanelForm:reportManagePanel_toggler']/span"),
Reporting_Link("//*[@id='appMenu:transReporting']"),
Transfer_Rep_yes("//*[@id='appMenu:transfer1DissPortalYes']"),
Transfer_Rep_No("//*[@id='appMenu:transfer1DissPortalNo']"),
Workforce_Link("cpanelForm:workForcePerfLink"),
FromDate("majorTabPanel:workForceReportreportFields:workflowCal1_input"),
ToDate("majorTabPanel:workForceReportreportFields:workflowCal2_input"),
Output("//*[@id='majorTabPanel:workForceReportreportCntl:workForceReportselect1']"),
organizationLevel("majorTabPanel:workForceReportreportFields:orglevelSelect"),
GeneratedReport("//*[@id='workForceReportembedded_iframe17']"),

//*************************************************** Active User Roles & Permissions Reports Hetal****************************************************************************************  


//---------------------Final Actions---------------------------------/
FinalActionsLink("cpanelForm:j_id_2q"),
ViewRptBt("//span[text()='View Report']"),
FAOrgLevel("//*[@id='majorTabPanel:finalActionReportBeanreportFields:orglevelSelect']"),
FAPDFReport("//*[@id='finalActionReportBeanembedded_iframe17']"),
FAOutput("//*[@id='majorTabPanel:finalActionReportBeanreportCntl:finalActionReportBeanselect1']"),

//--------------------Cases In Appeals----------------------------
EXP_Standard_Reports("//*[@id='cpanelForm:reportPanel2_toggler']/span"),
Appeals_Link("//*[@id='cpanelForm:j_id_2y']"),
Appeals_PDF("//*[@id='casesAppealReportembedded_iframe17']"),
ApOrglevel("//*[@id='majorTabPanel:casesAppealReportreportFields:orglevelSelect']"),
ApOutput("//*[@id='majorTabPanel:casesAppealReportreportCntl:casesAppealReportselect1']"),
ApFromDate("majorTabPanel:casesAppealReportreportFields:appealCal1_input"),
ApToDate("majorTabPanel:casesAppealReportreportFields:appealCal2_input"),

//*******************************************************************************************************************************************  

 ReportingTab("appMenu:transReporting"),
 TransferToDISSReportingNoButton("appMenu:transfer1DissPortalNo"),
 ActiveUserRolesAndOptionalPermissionsLink("cpanelForm:j_id_38"),
 PDFReport("majorTabPanel:activeRolesPermissionBeanrpt17_content"),
 OutputFormatText("majorTabPanel:activeRolesPermissionBeanreportCntl:activeRolesPermissionBeanrpt1-l0"),
 OutputFormatDropDown("majorTabPanel:activeRolesPermissionBeanreportCntl:activeRolesPermissionBeanselect1"),
 Org("majorTabPanel:activeRolesPermissionBeanreportFields:orglevelSelect"),
 ViewReportBtn("majorTabPanel:activeRolesPermissionBeanreportFields:j_id_yz"),
 ResetBtn("majorTabPanel:activeRolesPermissionBeanreportFields:j_id_z1"),
 DivisionBArmy("//*[@id='majorTabPanel:activeRolesPermissionBeanreportFields:divSelection']/option[2]"),
 BranchE("//*[@id='majorTabPanel:activeRolesPermissionBeanreportFields:branchManyList']/option[5]"),
 Team13("//*[@id='majorTabPanel:activeRolesPermissionBeanreportFields:teamSelectBox']/option[1]"),
 MemberSelectList("majorTabPanel:activeRolesPermissionBeanreportFields:memberListSelect']"),
 PDFReportD("majorTabPanel:activeRolesPermissionBeanrpt17_content"),
 PDFReportB("majorTabPanel:activeRolesPermissionBeanrpt17_content"),
 PDFReportT("majorTabPanel:activeRolesPermissionBeanrpt17_content"),
 CATSSTANDReport("cpanelForm:reportPanel2_content"),
 LogoffBtn("j_id_17:logoff"),
 
//*************************************************** User Account Audit Reports Hetal****************************************************************************************  
 
 CATSStandardReport("cpanelForm:reportPanel2"),
 UserAccountAuditLink("//*[@id='cpanelForm:j_id_39']"),
 AccountStatusSelection("majorTabPanel:userAccountAuditBeanreportFields:userAccountAuditBeanstatusSelectOne"),
 OrgAudit("majorTabPanel:userAccountAuditBeanreportFields:orglevelSelect"),
 ActiveStatus("//*[@id='majorTabPanel:userAccountAuditBeanreportFields:userAccountAuditBeanstatusSelectOne']/ul/li[1]"),
 ArchivedStatus("//*[@id='majorTabPanel:userAccountAuditBeanreportFields:userAccountAuditBeanstatusSelectOne']/ul/li[2]"),
 SuspendedStatus("//*[@id='majorTabPanel:userAccountAuditBeanreportFields:userAccountAuditBeanstatusSelectOne']/ul/li[3]"),
 DeactivatedStatus("//*[@id='majorTabPanel:userAccountAuditBeanreportFields:userAccountAuditBeanstatusSelectOne']/ul/li[4]"),
 PDFActiveStatusCAF("//*[@id='majorTabPanel:userAccountAuditBeanrpt17_content']"),
 
 
 
 //**********************************************************REPORTING UI*********************************************************************************  
caseAgingScroll("//*[@id='cpanelForm:j_id_2j']/ul/li/a/span[1]"),



//*********************************************************Asha Automation Properties*********************************************************************************
/**
 * @author vshivaraman
 * These are the property values to be set.
 */
Container2("//*[@id='container_2']"),
Container1("//*[@id='containers_1']"),
SelectDivision("//*[@id='createCaseForm:selectDivisionMenu_label']"),
DivisionAuto("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division Auto']"),////div[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division Auto']
DivisionSmoke("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division Smoke Test']"),
DivisionTestGen("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division DataGen Test']"),
DivisionTestGen1("//div[@id='createCaseForm:selectDivisionMenu_panel']//li[@data-label='Division DataGen Test1']"),////div[@id='createCaseForm:selectDivisionMenu_panel']//li[@data-label='tesing']
DivisionDAirForce("//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division D (Air Force)']"), //R - 7/2/15
CaseDetailsCasetypeDropDown("//label[@id='majorTabPanel:caseDetailsPanel:caseTypeSelectOne_label']"),
CaseDetailsPriorityProgramDropDown("//label[@id='majorTabPanel:caseDetailsPanel:casePrioProgSelectOne_label']"),
InvestigationType("//*[@id='createCaseForm:createCaseInvType_label']"),
InvestigationSSBI("//*[@id='createCaseForm:createCaseInvType_panel']//li[@data-label='SSBI']"),
CaseType("//*[@id='createCaseForm:createCaseType_label']"),
CaseTypeSCI("//*[@id='createCaseForm:createCaseType_panel']//li[@data-label='Top Secret/SCI']"),
CaseTypeTS("//*[@id='createCaseForm:createCaseType_panel']//li[@data-label='Top Secret']"),
CaseTypeSecret1("//*[@id='createCaseForm:createCaseType_panel']//li[@data-label='Secret']"),
CaseTypeConfedential("//*[@id='createCaseForm:createCaseType_panel']//li[@data-label='Confidential']"),
CaseTypeHSPD12("//*[@id='createCaseForm:createCaseType_panel']//li[@data-label='HSPD-12']"),
CaseTypeSuitability("//*[@id='createCaseForm:createCaseType_panel']//li[@data-label='Suitability']"),
RequestingSMO("//label[contains(text(),'Create case without SMO')]"),
SelectSMO("//li[@data-label='DISS']"),
SelectSMOAF("//li[@data-label='AF']"),
WithoutSMO("//li[@data-label='Create case without SMO']"), //R - 7/2/15
CreateCaseButton("//*[@id='createCaseForm:createCaseButton']"),
SsnSearch("//input[@id='cpanelForm:pinp']"),
SsnSearchBtn("//*[@id='cpanelForm:findSubjectId']"),
CreateSubjectSSN("//*[@id='majorTabPanel:cSSN']"),//majorTabPanel:cSSN
CreateSubjectLname("//*[@id='majorTabPanel:clastName']"),//majorTabPanel:clastName
CreateSubjectFname("//*[@id='majorTabPanel:cfirstName']"),//majorTabPanel:cfirstName
CreateSubjectCalender("//*[@id='majorTabPanel:cDOB']/button"),//majorTabPanel:cDOB
CreateSubjectdob("//*[@id='majorTabPanel:cDOB_input']"),//majorTabPanel:cDOB_input
CreateSubjectDOB("//*[@id='ui-datepicker-div']/div[1]/div/select[2]"),
CreateSubjectYrSelect("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[77]"),
CreateSubjectDaySelect("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a"),
CreateSubjectCurrentDate("//*[@id='ui-datepicker-div']/div[2]/button[1]"),
CreateSubjectCountryDropMenu("majorTabPanel:createsub:birthCountrySelectMenu"),
SelectCountry("//*[@id='majorTabPanel:createsub:birthCountrySelectMenu']"), 
CreateSubjectBtn("//*[@id='majorTabPanel:grantForm']"),//majorTabPanel:grantForm
CreateSubjectLink("//a[contains(text(),'Create Subject')]"),
CreateSubjectYesBtn("//*[@id='majorTabPanel:yesGrantForm']"),//majorTabPanel:createsub:yesGrandForm
CreateSubjectClaimGrowlerMessage("//*[@id='maingrowl_container']/div/div/div[2]/span"),
SubjectActionBtn("//*[@id='majorTabPanel:subjtabview:subactions_button']"),
CreateCaseDropdownOption("//a[@id='majorTabPanel:subjtabview:createCaseActionsButton']/span[text()='Create Case']"),
NoDisqualifyingConditionRadio("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelinesTabId']/form/table[3]/tbody/tr/td/table/tbody/tr/td[3]/div/div[2]/span"),
ApplyGuidelinesRadio("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:guidelineRadioButtonId']/tbody/tr/td[1]/div/div[2]"),
//FilesReviewTAb("/*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[2]/a"),
//FilesReviewTAb("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:fileReviewedTitlePanelId']/tbody/tr/td[contains(text(),'Files Reviewed']"),
FilesReviewTAb("//a[contains(@href, '#majorTabPanel:adjPanel:adjudicationTabId:fileReviewedTabId')]"),
FilesReviewSave("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:fileReviewedTabId']//button[contains(.,'Save')]"),
Guidelinestab("//a[contains(@href, '#majorTabPanel:adjPanel:adjudicationTabId:guidelinesTabId')]"),
//AllegiancePanel("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:AdjGuideline']/h3[2]/a"),
AllegiancePanel("//a[contains(text(),'A - Allegiance to the United States')]"), 
DisqualifyingCondition("//div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/div/div[2]"),
//QADisqualifyingCondition("//div[13]//div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/div/div[2]"),
MitigationCondition("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineOutputPanelId']//tr[2]/td/div/div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/div/div[2]"),
GuidelinesSave("majorTabPanel:adjPanel:catsAdjSummary:saveAllGuidelineId"),
DeterminationTab1("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[4]/a"),
DeterminationDropdownLst("//label[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_label']"),//majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_label
DeterminationNoAction("//li[text()='No Action']"),//@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel'
DeterminationFavorable("//li[text()='Favorable']"),
DeterminationLOJ("//li[text()='Loss Of Jurisdiction']"),
DeterminationNDM("//li[text()='No Determination Made']"),
DeterminationDenied("//li[text()='Denied']"),
DeterminationRevoked("//li[text()='Revoked']"),
DeterminationNone("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']//li[text()='None']"),
DeterminationSaveBtn("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:determinationTabId']//button[contains(.,'Save')]"),
Summarytab1("//*[@id='majorTabPanel:adjPanel:adjudicationTabId']/ul/li[5]/a"),
CaseClosed("//span[contains(text(),'Close Case')]"),
UpdatingCaseYesBtn("//span[contains(text(),'Yes')]"),
ReferingCaseYesBtn("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:j_id_2ph']"),
Refer("//*[@id='majorTabPanel:adjPanel:catsAdjSummary:summaryForm:deferGrant']"),
MPCCodedropDown("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu_label']"),
CivilianMPCCode("//*[@id='majorTabPanel:caseDetailsPanel:mpcCodeMenu_panel']/div/ul/li[@data-label='Civilian']"),
RFATab1("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1']/ul/li[5]/a"),
ActionsBtn("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button']"),
SOROption("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu3']/span"),
LODLOROption("//*[@id='majorTabPanel:caseDetailsPanel:subjtabview1:rfaMenu1']/span"),
//RFADocUploadDocBtn("//*[@id='RFARequestForm:j_id_4uv']"),
DocName("//*[@id='documentUploaderForm:editAddDocumentMessagesDocName']"),
DocTypeDropDown("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_label']"),
SORLetterOption("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[2]"),
LODLORDocTypeOption("//*[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[2]"),
ChooseBtn("//*/input[@type='file']"),
AddDocBtn("//button[@id='documentUploaderForm:documentUploadAddButtonId']"),

SendBtn("//*[@id='RFARequestForm:sendRFA']"),
LevelCodeSCI("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_panel']//li[text()='SCI - ICD704']"),
LevelCodeNone("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_panel']//li[text()='None']"),
LevelCodeSecret("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_panel']//li[text()='Secret']"),
LevelCodeTopSecret("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_panel']//li[text()='Top Secret']"),//
LevelCodeConfidential("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_panel']//li[text()='Confidential']"),
DocTypeNOIA("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[text()='Notice of Intent to Appeal']"),
NOIAReceivedDatePicker("//span[@id='majorTabPanel:taskDetailFormId:noiaReceivedDateId']/button"),
NOIAReceivedDateInput("//*[@id='majorTabPanel:taskDetailFormId:noiaReceivedDateId_input']"),
AppealDatePicker("//span[@id='majorTabPanel:taskDetailFormId:sentToAppealsDateId']/button"),
AppealDateInput("//*[@id='majorTabPanel:taskDetailFormId:sentToAppealsDateId_input']"),
AppealsAdjudicationChooseLevel("//label[text()='Choose Level']"),
LevelSCI("//div[contains(@id,'appealsSecurityDecision_panel')]//li[text()='SCI - ICD704']"),
LevelTopSecret("//div[contains(@id,'appealsSecurityDecision_panel')]//li[text()='Top Secret']"),
LevelSecret("//div[contains(@id,'appealsSecurityDecision_panel')]//li[text()='Secret']"),
LevelConfidential("//div[contains(@id,'appealsSecurityDecision_panel')]//li[text()='Confidential']"),// appealsSecurityDecision_panel53 in QA , Dev 53// 62 in demo//div[contains(@id,':appealsSecurityDecision_panel')]//li[text()='Confidential']
AppealAdjudicationChooseDetermination("//label[text()='Choose Determination']"),
AppealDetermiantionFavorable("//div[contains(@id,'appealsDetermination_panel')]//li[text()='Favorable']"),//63 in demo
AppealDetermiantionDenied("//div[contains(@id,'appealsDetermination_panel')]//li[text()='Denied']"),
AppealDetermiantionRevoked("//div[contains(@id,'appealsDetermination_panel')]//li[text()='Revoked']"),
AppealDetermiantionNOAction("//div[contains(@id,'appealsDetermination_panel')]//li[text()='No Action']"),
AppealDetermiantionLOJ("//div[contains(@id,'appealsDetermination_panel')]//li[text()='Loss Of Jurisdiction']"),
AppealDetermiantionNDM("//div[contains(@id,'appealsDetermination_panel')]//li[text()='No Determination Made']"),
ExceptionNone("//div[contains(@id,'appealsCondition_panel')]//li[text()='None']"),//64 in demo
AppealsAdjudicationChooseException("//label[text()='Choose Exception']"),
CAFTreeDivisionAuto("//td[contains(text(),'DIVISION-Division Auto')]/span[2]"),
IndexSSNToUse("indexASBaseline"),
BaseSSNToUse("baseSSNASBaseline"),
;
    
	private String property;
	private CATSProperties(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public static String caseQuickCaseSearchBoxgetProperty() {
		// TODO Auto-generated method stub
		return null;
	}
}
