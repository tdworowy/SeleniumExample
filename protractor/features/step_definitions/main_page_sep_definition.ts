import { Given, Then, When, After, AfterAll, BeforeAll} from 'cucumber';
import {browser, element,by,ExpectedConditions} from 'protractor'
import { assert } from 'chai';

function searchForProduct(productName:string):void {
    element(by.name("keyword")).sendKeys(productName)
    element(by.name("searchProducts")).click()
}
function isProductLinkDisplayed(productLink:string):boolean {
    var product_link = element(by.linkText("productLink"))
    var until = ExpectedConditions;
    try {
        browser.wait(until.presenceOf(product_link), 5000, 'Element taking too long to appear in the DOM');
        return true
        }
    catch(e){
        return false   
    }
    
}
BeforeAll(() => {
  browser.ignoreSynchronization = true;
})
Given(/^Main page is opened$/, ()=> {
        browser.get(browser.params.baseURL)
      });
Given(/^Entry store link is clicked$/, ()=> {
        element(by.linkText("Enter the Store")).click
      });
When(/^Search for cat '([^\"]*)'$/, (cat_name:string)=> {
        searchForProduct(cat_name)
      });
When(/^Search for dog '([^\"]*)'$/, (dog_name:string)=> {
        searchForProduct(dog_name)
      });
Then(/^Cat link Text is found '([^\"]*)'$/, (cat_link:string)=> {
        assert.isTrue(isProductLinkDisplayed(cat_link))
      });
Then(/^Dog link Text is found '([^\"]*)'$/, (dog_link:string)=> {
        assert.isTrue(isProductLinkDisplayed(dog_link))
      });
AfterAll(() => {
       browser.driver.quit()
    });
