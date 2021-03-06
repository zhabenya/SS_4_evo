import data.Advertisement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

/**
 * Created by zhabenya on 16.03.16.
 */
public class SiteTest extends ClassFixture {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void CheckAdsTest(){
        site.mainPage.chooseLanguage("RU");
        site.mainPage.chooseElectronicsCategory();
        site.mainPage.goToSearch();
        site.searchPage.searchByKeyword("Компьютер");
        site.searchPage.selectCity("Рига");
        site.searchPage.selectPeriod("За последний месяц");
        site.searchPage.startSearch();
        site.adsPage.sortResultsByPrice();
        site.adsPage.selectDealCategory("Продажа");
        site.adsPage.goToAdvancedSearch();
        site.searchPage.setPriceLimits("0", "300");
        site.searchPage.startSearch();
        List<Advertisement> ads = site.adsPage.selectAds(3);
        site.adsPage.showSelectedAds();

        softAssert.assertTrue(site.adsPage.checkSelectedAd(ads.get(0)));
        softAssert.assertTrue(site.adsPage.checkSelectedAd(ads.get(1)));
        softAssert.assertTrue(site.adsPage.checkSelectedAd(ads.get(2)));
        softAssert.assertAll();

//        Assert.assertTrue(site.adsPage.checkSelectedElements(ads));
    }

    @AfterMethod
    public void afterTest(){
        site.adsPage.clearSelectedAds();
    }
}
