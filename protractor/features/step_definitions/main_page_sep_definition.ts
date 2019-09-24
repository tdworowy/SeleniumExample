import { Given, Then, When, After, AfterAll, BeforeAll} from 'cucumber';
import {browser, element,by, By, $, $$,ExpectedConditions} from 'protractor'
var chai = require("chai");
var chaiAsPromised = require("chai-as-promised");

chai.use(chaiAsPromised);
var expect = chai.expect;

function searchForProduct(productName:string):void {
    element(by.name("keyword")).sendKeys(productName)
    element(by.name("searchProducts")).click()
    
}
function isProductLinkDisplayed(productLink:string) {
     var product_link = element(by.linkText(productLink))
     expect(product_link.isPresent()).to.eventually.equal(true); 
     expect(product_link.isDisplayed()).to.eventually.equal(true); 
}

BeforeAll(() => {
  browser.ignoreSynchronization = true
  browser.manage().timeouts().implicitlyWait(1000)
})
Given(/^Main page is opened$/, (callback)=> {
        browser.driver.get(browser.params.baseURL).then(callback);
        
      });
Given(/^Entry store link is clicked$/, ()=> {
        browser.wait(ExpectedConditions.visibilityOf( element(by.css("a[href=\"actions/Catalog.action\"")))).then(()=>{
          browser.driver.findElement( element(by.css("a[href=\"actions/Catalog.action\""))).click();
          browser.sleep(500);
        })
      });
When(/^Search for cat '([^\"]*)'$/, (cat_name:string)=> {
        searchForProduct(cat_name)
      });
When(/^Search for dog '([^\"]*)'$/, (dog_name:string)=> {
        searchForProduct(dog_name)
      });
Then(/^Cat link Text is found '([^\"]*)'$/, (cat_link:string)=> {
        isProductLinkDisplayed(cat_link)
      });
Then(/^Dog link Text is found '([^\"]*)'$/, (dog_link:string)=> {
        isProductLinkDisplayed(dog_link)
      });
AfterAll(() => {
       browser.driver.quit()
    });
