@ui @cos

Feature: SMS Create Single CoS
This feature creates and assigns a single CoS
 
Scenario: Check that main elements on SMS Homepage are displayed and user can login
# Generic error handling in place to handle expected page titles when loading webpages

When I open SMS Homepage
Then I verify that the page displays Log in link
When I select the Log in link
Then I verify that the SMS Log in page displays UserID field
And the page displays the Password field
#And the page displays Cancel button
And the page displays Login button


Then I enter login credentials
#And verify the SMS Homepage displays Ok button
#Then I click the OK button

#Scenario: Checks an SMS user can enter details and assign a form for creating and assigning a single CoS application
# Generic error handling in place to handle expected page titles when loading webpages

# SMS Homepage

Then I select the Workers link
Then I select the Create and Assign link

# Tiers and Categories Selection

Then I choose European Union accession country answer from List of Values
Then I choose the Tier from List of Values
Then I choose the Category from List of Values
Then I choose the Sub-Category from List of Values
Then I select create a new single certificate

# Create a CoS - Enter Details

And Enter Personal information
And Enter Passport or travel document
And Enter current home address
And Enter Work dates
And Enter Main work address in UK
And Enter Migrants employment details and Save form

# Confirmation of Saved CoS
# Error handling in place to handle Assign button not being available

And check the Assign button is available
Then I click on the Assign button

# Confirm CoS details before assigning
# Error handling in place to handle insufficient CoS allocation

Then I click on the Assign CoS button

# Online payment

And I click on the Ok button

# Worldpay Test Harness

Then I check the correct Worldpay URL is displayed


