package uk.gov.ho.domain.component.ui.stepLib;

import uk.gov.ho.cts.utils.Configuration;
import uk.gov.ho.domain.component.ui.pages.PageMetastorm;
import uk.gov.ho.domain.component.ui.pages.Page_Metastorm;


public class MetastormStepLib {

    private String PBS_METASTORM_URL="pbs.Metastorm.url";
    private PageMetastorm metastormPage;
    Page_Metastorm msPage = new Page_Metastorm();

    public void navigateToHomePage() {
        metastormPage.openUrl(Configuration.get(PBS_METASTORM_URL));
    }

    public void checkToDoButton() { metastormPage.checkToDoButton(); }

    public void navigateToMetastorm()
    {
        msPage.openMetaStorm();
    }



}


