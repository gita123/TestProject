package uk.gov.ho.domain.component.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.openqa.selenium.WebDriver;



//@DefaultUrl("https://www3.points.homeoffice.gov.uk/gui-sms-jsf/SMS-001-Landing.faces")

public class Page_SingleCoS extends PageObject {

//	@FindBy(xpath = "//*[@id=\"j_id11:smsNavigation:j_id73:tiers2And5\"]") WebElement workersLink;
    @FindBy(name = "j_idt11:smsNavigation:j_idt73:tiers2And5") WebElement workersLink;


	@FindBy(xpath = "//*[@id=\"formTiers2And5Home\"]/div[1]/a") WebElement createAndAssignLink;

	@FindBy(name = "j_username") WebElement userIDField;
	@FindBy(id = "smsPrepareCoSSelectTier:accessionCountrySelect") WebElement accessionCountrySelect;
	@FindBy(name = "smsPrepareCoSSelectTier:next") WebElement cosSelectTierNext;
	@FindBy(id = "smsPrepareCoSSelectTier:tier") WebElement cosSelectTierTier;
	@FindBy(id = "smsPrepareCoSSelectTier:category") WebElement cosSelectTierCategory;
	@FindBy(id = "smsPrepareCoSSelectTier:subCategory") WebElement cosSelectTierSubCategory;
	@FindBy(css = "input[id='smsPrepareCoSSelectOp:options:0']") WebElement cosSelectOp;
	@FindBy(name = "smsPrepareCoSSelectOp:next") WebElement cosSelectOpNext;
	@FindBy(name = "smsPrepareCoS:familyName") WebElement familyName;
	@FindBy(id = "smsPrepareCoS:nationality") WebElement nationality;
	@FindBy(name = "smsPrepareCoS:placeOfBirth") WebElement placeOfBirth;
	@FindBy(id = "smsPrepareCoS:countryBirth") WebElement countryOfBirth;
	@FindBy(id = "smsPrepareCoS:dateofBirth:day") WebElement dateOfBirthDay;
	@FindBy(id = "smsPrepareCoS:dateofBirth:month") WebElement dateOfBirthMonth;
	@FindBy(id = "smsPrepareCoS:dateofBirth:year") WebElement dateOfBirthYear;
	@FindBy(id = "smsPrepareCoS:genderCode") WebElement genderCode;
	@FindBy(id = "smsPrepareCoS:countryResidence") WebElement countryResidence;
	@FindBy(name = "smsPrepareCoS:passportNo") WebElement passportNo;
	@FindBy(id = "smsPrepareCoS:issueExpiryDatedateFrom:day") WebElement issueExpiryDatedateFromDay;
	@FindBy(id = "smsPrepareCoS:issueExpiryDatedateFrom:month") WebElement issueExpiryDatedateFromMonth;
	@FindBy(id = "smsPrepareCoS:issueExpiryDatedateFrom:year") WebElement issueExpiryDatedateFromYear;
	@FindBy(id = "smsPrepareCoS:issueExpiryDatedateTo:day") WebElement issueExpiryDatedateToDay;
	@FindBy(id = "smsPrepareCoS:issueExpiryDatedateTo:month") WebElement issueExpiryDatedateToMonth;
	@FindBy(id = "smsPrepareCoS:issueExpiryDatedateTo:year") WebElement issueExpiryDatedateToYear;

	@FindBy(name = "smsPrepareCoS:placeOfIssue") WebElement placeOfIssue;

	//@FindBy(id = "smsPrepareCoS:dateofBirth:year") WebElement dateOfBirthYear;
	//@FindBy(id = "smsPrepareCoS:genderCode") WebElement genderCode;
	//@FindBy(id = "smsPrepareCoS:countryResidence") WebElement countryResidence;


	@FindBy(name = "smsPrepareCoS:j_nonUkAddress1migrantHomeAddress") WebElement  smsPrepareCoS_j_nonUkAddress1migrantHomeAddress;
	@FindBy(name = "smsPrepareCoS:j_nonUkCitymigrantHomeAddress") WebElement  smsPrepareCoS_j_nonUkCitymigrantHomeAddress;
	@FindBy(name = "smsPrepareCoS:j_nonUkPostcodemigrantHomeAddress") WebElement  smsPrepareCoS_j_nonUkPostcodemigrantHomeAddress;
	@FindBy(id = "smsPrepareCoS:j_countrymigrantHomeAddress") WebElement  smsPrepareCoS_j_countrymigrantHomeAddress;
	@FindBy(id = "smsPrepareCoS:startEndDatedateFrom:day") WebElement  smsPrepareCoS_startEndDatedateFrom_day;
	@FindBy(id = "smsPrepareCoS:startEndDatedateFrom:month") WebElement  smsPrepareCoS_startEndDatedateFrom_month;
	@FindBy(id = "smsPrepareCoS:startEndDatedateFrom:year") WebElement  smsPrepareCoS_startEndDatedateFrom_year;
	@FindBy(id = "smsPrepareCoS:startEndDatedateTo:day") WebElement  smsPrepareCoS_startEndDatedateTo_day;
	@FindBy(id = "smsPrepareCoS:startEndDatedateTo:month") WebElement  smsPrepareCoS_startEndDatedateTo_month;
	@FindBy(id = "smsPrepareCoS:startEndDatedateTo:year") WebElement  smsPrepareCoS_startEndDatedateTo_year;
	@FindBy(name = "smsPrepareCoS:totalHours") WebElement  smsPrepareCoS_totalHours;
	@FindBy(name = "smsPrepareCoS:addPWSAddress") WebElement  smsPrepareCoS_addPWSAddress;

	@FindBy(name = "smsAddressDetails:j_address1selectedAddress") WebElement  smsAddressDetails_j_address1selectedAddress;
	@FindBy(name = "smsAddressDetails:j_cityselectedAddress") WebElement  smsAddressDetails_j_cityselectedAddress;
	@FindBy(name = "smsAddressDetails:j_postcodeselectedAddress") WebElement  smsAddressDetails_j_postcodeselectedAddress;
	@FindBy(name = "smsAddressDetails:save") WebElement  smsAddressDetails_save;
	@FindBy(name = "smsPrepareCoS:jobTitle") WebElement  smsPrepareCoS_jobTitle;
	@FindBy(id = "smsPrepareCoS:jobType") WebElement  smsPrepareCoS_jobType;
	@FindBy(name = "smsPrepareCoS:jobDescription") WebElement  smsPrepareCoS_jobDescription;
	@FindBy(name = "smsPrepareCoS:grossSalary") WebElement  smsPrepareCoS_grossSalary;
	@FindBy(id = "smsPrepareCoS:salaryPeriod") WebElement  smsPrepareCoS_salaryPeriod;
	@FindBy(css = "input[id='smsPrepareCoS:jobLevel']") WebElement smsPrepareCoS_jobLevel;
	@FindBy(css = "input[id='smsPrepareCoS:rlmt']") WebElement  smsPrepareCoS_rlmt;
	@FindBy(name = "smsPrepareCoS:labourMarketDets") WebElement  smsPrepareCoS_labourMarketDets;
	@FindBy(name = "smsPrepareCoS:save") WebElement  smsPrepareCoS_save;
	@FindBy(name = "j_idt11:smsNavigation:logout") WebElement  j_id11_smsNavigation_logout;
	@FindBy(name = "smsPrepareCoSSingleSaved:assign") WebElement  smsPrepareCoSSingleSaved_assign;
	@FindBy(name = "smsAssignCoSConfirm:assignCos") WebElement  smsAssignCoSConfirm_assignCos;
	@FindBy(name = "epaynext") WebElement  epaynext;





//	@FindBy(xpath = "//*[@id=\"centralContent\"]/div[1]/a") WebElement loginLink;
//    @FindBy(id = "j_idt205:smsNavigation:login") WebElement loginLink;
//	@FindBy(css = "input[id$='smsNavigation:login']") WebElement loginLink;
	@FindBy(linkText = "Log in") WebElement loginLink;

//	@FindBy(name = "j_username") WebElement userIDField;
	@FindBy(name = "j_password") WebElement passwordField;
	@FindBy(name = "j_idt241:Cancel") WebElement cancelButton;

	// Several attempts to obtain the dynamic cancel button - not working - taken out of the feature file
	//	@FindBy(linkText = "Cancel") WebElement cancelButton;
    //    @FindBy(xpath=".//button[text()='Cancel']") WebElement cancelButton;

	@FindBy(name = "login") WebElement loginButton;
	@FindBy(name = "smsNoActiveMessages:ackNoActiveMessages") WebElement smsHomePageOKButton;
	@FindBy(name = "j_idt11:smsNavigation:logout") WebElement logout;


	public void verifyPageDisplaysLogInLink() {
		// check login link is displayed
		if(loginLink.isDisplayed())
		{
			System.out.println("The Login link is displayed");
		} else {
			System.out.println("The Login link is NOT displayed");
		}
	}

	public void selectLogInLink() {
		// click login link
		try{Thread.sleep(2000);}catch(InterruptedException ie){}
		loginLink.click();
		System.out.println("Select Login link");
		try{Thread.sleep(2000);}catch(InterruptedException ie){}
	}

	public void verifySMSLogInPageDisplaysUserIdField() {
		// check user id field is displayed
		if (userIDField.isDisplayed()) {
			System.out.println("UserID field is displayed");
		} else {
			System.out.println("UserID field is NOT displayed");
		}
	}

	public void pageDisplaysPasswordField() {
		// check password field is displayed
		if(passwordField.isDisplayed()) {
			System.out.println("Password field is displayed");
		} else {
			System.out.println("Password field is NOT displayed");
		}
	}

	public void pageDisplaysCancelButton() {


		// not called at the moment as commented out on feature file

		if(cancelButton.isDisplayed()) {
			System.out.println("Cancel button is displayed");
		} else {
			System.out.println("Cancel button is NOT displayed");
		}
	}

	public void pageDisplaysLogInButton() {
		// check login button is displayed
		if(loginButton.isDisplayed()) {
			System.out.println("Login button is displayed");
		} else {
			System.out.println("Login button is NOT displayed");
		}
	}

	public void enterLoginCredentials() {
		// enter login details and select login button
		userIDField.sendKeys("56u7UTGXk");
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		passwordField.sendKeys("Password1");
		loginButton.submit();
		try{Thread.sleep(50000);}catch(InterruptedException ie){}
		System.out.println("Enter login credentials");

		String expectedTitle = "SMS message board - no active messages";
		String actualTitle = "";


		// get the actual value of the title
		// DGB - actualTitle = driver.getTitle();


		actualTitle = this.getTitle();

		/*
		 * compare the actual title of the page with the expected one and print
		 * the result
		 */
		if (actualTitle.contentEquals(expectedTitle) || actualTitle.contentEquals("SMS message board - active messages")){
			System.out.println("Login Successful");
		} else {
			System.out.printf("Login Unsuccessful, actual webpage is:" + actualTitle );
			// close the Browser
			//	driver.close();
			System.out.println("Test terminated as login failed");
		}
	}

	public void verifySMSHomepageDisplaysOkButton() {
		// check ok button is displayed for
		if(smsHomePageOKButton.isDisplayed()) {
			System.out.println("SMS HomePage OK button is displayed");
		} else {
			System.out.println("SMS HomePage OK button is NOT displayed");
		}
	}

	public void clickOKbutton() {
		smsHomePageOKButton.click();
		System.out.println("Click OK button verification");
	}

	public void logout() {
		logout.click();
		System.out.println("Logout");
		//driver.close();
	}




	////////////////////////////////////////////////////////////////////////////////////






	public void selectWorkersLink() {
		// click select Workers link
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
			workersLink.click();
		// 5 second pause to slow script execution	
			try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("Select Workers link");
		
		String expectedTitle = "Workers";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	    
	    //compare the actual title of the page with the expected one and print the result
	    if (actualTitle.contentEquals(expectedTitle)){
	        System.out.println("Workers page displayed");
	    } else {
	        System.out.printf("Workers page NOT displayed, actual webpage is:" + actualTitle );
	     // close the Browser 
		 //	driver.close();
			System.out.println("Test terminated as page not found");
	    }
	}
	
	public void selectCreateAndAssignLink() {
		// click create and assign link
			createAndAssignLink.click();
			try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("Select Create and Assign link");
		
		String expectedTitle = "Create and assign CoS";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	  
	    //compare the actual title of the page with the expected one and print the result
	    
	    if (actualTitle.contentEquals(expectedTitle)){
	        System.out.println("Create and assign CoS page displayed");
	    } else {
	        System.out.printf("Create and assign CoS page NOT displayed, actual webpage is:" + actualTitle );
	     // close the Browser 
		 //	driver.close();
			System.out.println("Test terminated as page not found");
	    }
	}
	
	public void selectEuropeanUnionAccession() {
		
		// select European Union Accession country answer and select Next button

		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		Select dropdown = new Select(accessionCountrySelect);
		dropdown.selectByValue("No");

		try{Thread.sleep(5000);}catch(InterruptedException ie){}

		//To get all the option selected in the dropdown.
				List<WebElement> allSelectedOptions = dropdown.getAllSelectedOptions();
				for (WebElement webElement : allSelectedOptions)
				{
					System.out.println("Select European Union Accession country:"+ webElement.getText());
					
					cosSelectTierNext.click();
					try{Thread.sleep(5000);}catch(InterruptedException ie){}
				}
	}
	
	public void selectTier() {
		// select Tier and select Next button
				
		Select dropdown = new Select(cosSelectTierTier);
		dropdown.selectByValue("2");
		
		//To get all the option selected in the dropdown.
				List<WebElement> allSelectedOptions = dropdown.getAllSelectedOptions();
				for (WebElement webElement : allSelectedOptions)
				{
					System.out.println("Select the tier:"+ webElement.getText());
					
					cosSelectTierNext.click();
					try{Thread.sleep(5000);}catch(InterruptedException ie){}
				}
	}	
	
	public void selectCategory() {
		// select Category and select Next button
				
		Select dropdown = new Select(cosSelectTierCategory);
		dropdown.selectByValue("GEN");
		
		//To get all the option selected in the dropdown.
				List<WebElement> allSelectedOptions = dropdown.getAllSelectedOptions();
				for (WebElement webElement : allSelectedOptions)
				{
					System.out.println("Select the tier:"+ webElement.getText());
					
					cosSelectTierNext.click();
					try{Thread.sleep(5000);}catch(InterruptedException ie){}
				}
	}
	
	public void selectSubCategory() {
		// select Sub-Category and select Next button
					
		Select dropdown = new Select(cosSelectTierSubCategory);
		dropdown.selectByValue("GEE");
		
		//To get all the option selected in the dropdown.
				List<WebElement> allSelectedOptions = dropdown.getAllSelectedOptions();
				for (WebElement webElement : allSelectedOptions)
				{
					System.out.println("Select the tier:"+ webElement.getText());
					
					cosSelectTierNext.click();
					try{Thread.sleep(5000);}catch(InterruptedException ie){}
				}
	}
	
	public void selectCreateAndAssign() {
		// select Create And Assign and select Next button
		    cosSelectOp.click();
		    cosSelectOpNext.click();
			try{Thread.sleep(5000);}catch(InterruptedException ie){}
			System.out.println("Select Create And Assign");
			
			String expectedTitle = "Create a CoS";
		    String actualTitle = "";
					    
		   // get the actual value of the title
		    actualTitle = this.getTitle();
		  
		    //compare the actual title of the page with the expected one and print the result
		    
		    if (actualTitle.contentEquals(expectedTitle)){
		        System.out.println("Create a CoS page displayed");
		    } else {
		        System.out.printf("Create a CoS page NOT displayed, actual webpage is:" + actualTitle );
		     // close the Browser 
			 //	driver.close();
				System.out.println("Test terminated as page not found");
		    	}

		    try{Thread.sleep(5000);}catch(InterruptedException ie){}
	}
	
	public void enterPersonalInformation() {
		// enter Personal Information
		// enter Family Name
			familyName.sendKeys("Test Family Name");
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		// enter Nationality			
			Select dropdownNationality = new Select(nationality);
			dropdownNationality.selectByValue("AF ");
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		// enter Family Name
			placeOfBirth.sendKeys("Kabul");
		// enter Country of birth			

		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownCountryOfBirth = new Select(countryOfBirth);
			dropdownCountryOfBirth.selectByValue("AF ");
		// enter Date of Birth - Day
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownDayOfBirth = new Select(dateOfBirthDay);
			dropdownDayOfBirth.selectByIndex(1);
		// enter Date of Birth - Month
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownMonthOfBirth = new Select(dateOfBirthMonth);
			dropdownMonthOfBirth.selectByIndex(1);
		// enter Date of Birth - Year
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownYearOfBirth = new Select(dateOfBirthYear);
			dropdownYearOfBirth.selectByIndex(25);	
		// enter Gender
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownGender = new Select(genderCode);
			dropdownGender.selectByIndex(1);
		// enter Country of Residence
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownCountryOfResidence = new Select(countryResidence);
			dropdownCountryOfResidence.selectByValue("GB ");
			try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("Personal Information Entered");
	}
	
	public void enterPassportTraveldoc() {
		// enter Passport or Travel document
		// enter Passport Number
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			passportNo.sendKeys("P12345");
		// enter Issue Date - Day
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownIssueDay = new Select(issueExpiryDatedateFromDay);
			dropdownIssueDay.selectByIndex(1);

		// enter Issue Date - Month
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		    Select dropdownIssueMonth = new Select(issueExpiryDatedateFromMonth);
			dropdownIssueMonth.selectByIndex(1);
		// enter Issue Date - Year
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownIssueYear = new Select(issueExpiryDatedateFromYear);
			dropdownIssueYear.selectByIndex(13);
		// enter Issue Date - Day
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownExpiryDay = new Select(issueExpiryDatedateToDay);
			dropdownExpiryDay.selectByIndex(1);
		// enter Issue Date - Month
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownExpiryMonth = new Select(issueExpiryDatedateToMonth);
			dropdownExpiryMonth.selectByIndex(1);
		// enter Issue Date - Year
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownExpiryYear = new Select(issueExpiryDatedateToYear);
			dropdownExpiryYear.selectByIndex(3);			
		// enter Place of Issue
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			placeOfIssue.sendKeys("Kabul");

		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("Passport Information Entered");		
	}
	
	public void enterCurrentAddress() {
		// enter Current Address
		// enter Address
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			smsPrepareCoS_j_nonUkAddress1migrantHomeAddress.sendKeys("1 Test Avenue");
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			smsPrepareCoS_j_nonUkCitymigrantHomeAddress.sendKeys("Timbucktoo");
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			smsPrepareCoS_j_nonUkPostcodemigrantHomeAddress.sendKeys("T10 2AB");
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		// enter Country			
			Select dropdownCountry = new Select(smsPrepareCoS_j_countrymigrantHomeAddress);
			dropdownCountry.selectByValue("GB ");
		try{Thread.sleep(5000);}catch(InterruptedException ie){}

		System.out.println("Current Address Entered");
	}
	
	public void enterWorkDates() {
		// enter Work Date
		// enter Start Date - Day
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownStartDay = new Select(smsPrepareCoS_startEndDatedateFrom_day);
			dropdownStartDay.selectByIndex(1);
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		// enter Start Date - Month
			Select dropdownStartMonth = new Select(smsPrepareCoS_startEndDatedateFrom_month);
			dropdownStartMonth.selectByIndex(1);
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
		// enter Start Date - Year
			Select dropdownStartYear = new Select(smsPrepareCoS_startEndDatedateFrom_year);
			dropdownStartYear.selectByIndex(3);
		// enter End Date - Day
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownEndDay = new Select(smsPrepareCoS_startEndDatedateTo_day);
			dropdownEndDay.selectByIndex(1);
		// enter End Date - Month
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownEndMonth = new Select(smsPrepareCoS_startEndDatedateTo_month);
			dropdownEndMonth.selectByIndex(1);
		// enter End Date - Year
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			Select dropdownEndYear = new Select(smsPrepareCoS_startEndDatedateTo_year);
			dropdownEndYear.selectByIndex(4);			
		// enter Total Weekly Hours
		try{Thread.sleep(1000);}catch(InterruptedException ie){}
			smsPrepareCoS_totalHours.sendKeys("40");
			try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("Work Dates Entered");		
	}
	
	public void selectMainWorkAddressUK() {
		// click select Add button and enter a Main UK address and Save
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
			smsPrepareCoS_addPWSAddress.click();
		// 5 second pause to slow script execution	
			try{Thread.sleep(5000);}catch(InterruptedException ie){}
		System.out.println("Select Add button to enter main UK work address");
		
		String expectedTitle = "Add or amend a work address";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	    
	    //compare the actual title of the page with the expected one and print the result
	    if (actualTitle.contentEquals(expectedTitle)){
	        System.out.println("Add or amend a work address page displayed");
	     // enter Address
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsAddressDetails_j_address1selectedAddress.sendKeys("10 Test Lane");
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsAddressDetails_j_cityselectedAddress.sendKeys("London");
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsAddressDetails_j_postcodeselectedAddress.sendKeys("NW1 2PL");
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	try{Thread.sleep(5000);}catch(InterruptedException ie){}
	     // click Save button
	     	smsAddressDetails_save.click();
	     	System.out.println("Main UK work address saved");	     	     	
	    } else {
	        System.out.printf("Add or amend a work address page is NOT displayed, actual webpage is:" + actualTitle );
	     // close the Browser 
		 //	driver.close();
			System.out.println("Test terminated as page not found");
	    }
	}
	
	public void enterMigrantDetailsSaveForm() {
		// enter Migrant Employment Details and Save form		
		String expectedTitle = "Create a CoS";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	    
	    //compare the actual title of the page with the expected one and print the result
	    if (actualTitle.contentEquals(expectedTitle)){
	        System.out.println("Create a CoS page displayed after entering main UK work address");
	     // enter Job title

			try{Thread.sleep(1000);}catch(InterruptedException ie){}

	     	smsPrepareCoS_jobTitle.sendKeys("Tester Job");
	     // enter Job type
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	Select dropdownJobType = new Select(smsPrepareCoS_jobType);
	     	dropdownJobType.selectByIndex(9);
	     // enter Job description
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsPrepareCoS_jobDescription.sendKeys("Tester job description");
	     // enter Salary
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsPrepareCoS_grossSalary.sendKeys("41000");
	     // enter Job type
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	Select dropdownSalaryPeriod = new Select(smsPrepareCoS_salaryPeriod);
	     	dropdownSalaryPeriod.selectByIndex(6);
	     // click appropriate skill check box
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsPrepareCoS_jobLevel.click();
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     // click resident labour market test check box
	     	smsPrepareCoS_rlmt.click();
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     // enter resident labour market test details
			try{Thread.sleep(1000);}catch(InterruptedException ie){}
	     	smsPrepareCoS_labourMarketDets.sendKeys("RMLT test details");
	     	try{Thread.sleep(5000);}catch(InterruptedException ie){}
	     // click Save button
	     	smsPrepareCoS_save.click();
	     	System.out.println("Migrant Details entered");
	     	System.out.println("CoS details saved");	     		     	
	    } else {
	        System.out.printf("Create a CoS page after entering main UK work address is NOT displayed, actual webpage is:" + actualTitle );
	     // close the Browser 
		//	driver.close();
			System.out.println("Test terminated as page not found");
	    }
	}
	
	public void checkAssignButtonAvailable() {
		// check Assign button is available

		    try{Thread.sleep(5000);}catch(InterruptedException ie){}
			if(smsPrepareCoSSingleSaved_assign.isEnabled()) {
			System.out.println("Assign button is available to select");
			} else {
		// logout of SMS as create CoS form details not completed successfully
				try{Thread.sleep(1000);}catch(InterruptedException ie){}
			j_id11_smsNavigation_logout.click();
			System.out.println("Assign button is NOT available to select - form details not completed successfully");
			System.out.println("Logout");
			//driver.close();
		}
	}
	
	public void clickAssignButton() {
		// click Assign button

		try{Thread.sleep(5000);}catch(InterruptedException ie){}
		smsPrepareCoSSingleSaved_assign.click();
		
		String expectedTitle = "Confirm CoS details before assigning";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	    
	    //compare the actual title of the page with the expected one and print the result
	    if (actualTitle.contentEquals(expectedTitle)){
	       	System.out.println("Confirm CoS details before assigning page is displayed");
	     	try{Thread.sleep(5000);}catch(InterruptedException ie){}
	    } else {
	        System.out.printf("Confirm CoS details before assigning page is NOT displayed, actual webpage is:" + actualTitle );
	     // close the Browser 
			//driver.close();
			System.out.println("Test terminated as page not found");
	    }
	}
	
	public void clickAssignCoSButton() {
		// click AssignCoS button
		try{Thread.sleep(2000);}catch(InterruptedException ie){}
		smsAssignCoSConfirm_assignCos.click();
		
		String expectedTitle = "Online payment";
		String insufficientAllocation = "Confirm CoS details before assigning";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	    
	    //compare the actual title of the page with the expected one and print the result
	    // check if sufficient CoS allocation
	    if (actualTitle.contentEquals(expectedTitle)){
	       	System.out.println("Online payment page is displayed");
	     	try{Thread.sleep(2000);}catch(InterruptedException ie){}
	    } else if (actualTitle.contentEquals(insufficientAllocation)) {
	    		 System.out.printf("The assignment could not be processed due to insufficient CoS/CAS remaining in your allocation.");
	    // logout of SMS as create CoS form details not completed successfully	
	    		try{Thread.sleep(2000);}catch(InterruptedException ie){}
	    		j_id11_smsNavigation_logout.click();
	 			System.out.println("Logout");
	 			//driver.close();
	    } else  {
	    		System.out.printf("Online payment page is NOT displayed, actual webpage is:" + actualTitle );
	    		//close the Browser 
	    		//driver.close();
				System.out.println("Test terminated as page not found");
	    }
	   }
	
	public void clickOnlinePaymentOkButton() {
		// click Online Payment Ok button
		try{Thread.sleep(2000);}catch(InterruptedException ie){}
		epaynext.click();
		
		String expectedTitle = "Welcome to WorldPay";
	    String actualTitle = "";
				    
	   // get the actual value of the title
	    actualTitle = this.getTitle();
	    
	    //compare the actual title of the page with the expected one and print the result
	    if (actualTitle.contentEquals(expectedTitle)){
	       	System.out.println("Worldpay payment page is displayed");
	     	try{Thread.sleep(2000);}catch(InterruptedException ie){}
	    } else {
	        System.out.printf("Worldpay payment page is NOT displayed, actual webpage is:" + actualTitle );
	     // close the Browser 
		//	driver.close();
			System.out.println("Test terminated as page not found");
	    }
	}
	
	public void checkWorldpayURL() {
	 	// declare and initialise the variable to store the expected title of the web page
		String worldpayUrl = "https://secure-test.worldpay.com/wcc/purchase";
		String currentUrl = this.getDriver().getCurrentUrl();
        // direct it to the Base URL
               
        try{Thread.sleep(2000);}catch(InterruptedException ie){}
                 
        // get current URL after the Base URL has loaded
        System.out.printf("The current URL is: " + currentUrl );
        	       
        /*
         * compare the actual URL of the page with the expected one and print
         * the result
         */
        if (currentUrl.contentEquals(worldpayUrl)){
            System.out.println("The correct WorldPay URL is displayed");
         
         // logout of SMS as test completed successfully	
 			//driver.findElement(By.name("j_id11:smsNavigation:logout")).click();
 			//System.out.println("Logout");
 			System.out.println("Test completed successfully");
 			//driver.close();
        } else {
            System.out.printf("The WorldPay URL is NOT Displayed, it's: " + currentUrl );
         // close the Browser 
    	//	driver.close();
    		System.out.println("Test terminated as desired webpage not loaded");
        }	     
	}
}	
