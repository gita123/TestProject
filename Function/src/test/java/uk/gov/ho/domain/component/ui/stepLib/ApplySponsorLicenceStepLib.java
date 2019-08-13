package uk.gov.ho.domain.component.ui.stepLib;

import uk.gov.ho.domain.component.ui.pages.Page_SponserLicence;

public class ApplySponsorLicenceStepLib {

    private Page_SponserLicence sponserLicencePage = new Page_SponserLicence();

    public void applySponsorLicence()
    {
        sponserLicencePage.navigateToHomePage();
        sponserLicencePage.applySponsorLicence();
        sponserLicencePage.completeOrganisationDetails();
        sponserLicencePage.applyCOSCASDetails();
        sponserLicencePage.supportingDocumentCheck();
        sponserLicencePage.completeContactDetails();

    }
}
