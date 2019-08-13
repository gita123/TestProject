package uk.gov.ho.domain.component.ui.steps;

import cucumber.api.java.en.And;
import net.thucydides.core.annotations.Steps;
import uk.gov.ho.domain.component.ui.stepLib.ApplySponsorLicenceStepLib;

public class StepDefs_SponsorLicence {

    @Steps
    ApplySponsorLicenceStepLib sponsorLicenceStepLib;

    @And("^apply for sponsor licence$")
    public void apply_for_sponsor_licence()
    {
        sponsorLicenceStepLib.applySponsorLicence();
    }
}
