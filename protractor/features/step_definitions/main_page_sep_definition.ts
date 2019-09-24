import { Given, Then, When, After, AfterAll, BeforeAll} from 'cucumber';
import {browser, element,by, By, $, $$,ExpectedConditions} from 'protractor'
var chai = require("chai");
var chaiAsPromised = require("chai-as-promised");

chai.use(chaiAsPromised);
var expect = chai.expect;

function searchForProduct(productName:string):void {
    var search_field = element(by.name("keyword"))
    var search_button = element(by.name("searchProducts")).click()
    browser.wait(ExpectedConditions.visibilityOf(search_field)).then(()=>{
      browser.driver.findElement(search_field).sendKeys(productName);
      browser.driver.findElement(search_button).click()
      browser.sleep(500);
    }) 
}
function isProductLinkDisplayed(productLink:string):void {
     var product_link = element(by.linkText(productLink))
     expect(product_link.isPresent()).to.eventually.equal(true); 
     expect(product_link.isDisplayed()).to.eventually.equal(true); 
}

BeforeAll(() => {
  browser.ignoreSynchronization = true
  browser.waitForAngularEnabled(false)
 // browser.manage().timeouts().implicitlyWait(1000)
})
Given(/^Main page is opened$/, (callback)=> {
        browser.driver.get(browser.params.baseURL).then(callback);
        
      });
Given(/^Entry store link is clicked$/, ()=> {
  var store_link = element(by.linkText("Enter the Store"))
        browser.wait(ExpectedConditions.visibilityOf(store_link)).then(()=>{
          browser.driver.findElement(store_link).click();
          browser.sleep(500);
        }) // Don't work, protractor is fucking garbage      
  //element(by.linkText("Enter the Store")).click() //Don't work, but should
        // browser.wait(ExpectedConditions.visibilityOf( element(by.css("a[href=\"actions/Catalog.action\"")))).then(()=>{
        //   browser.driver.findElement( element(by.css("a[href=\"actions/Catalog.action\""))).click();
        //   browser.sleep(500);
        // }) // Still don't work
        
        // browser.wait(ExpectedConditions.visibilityOf(element(by.xpath("#Content > p:nth-child(2) > a")))).then(()=>{
        //   browser.driver.findElement( element(by.xpath("#Content > p:nth-child(2) > a"))).click();
        //   browser.sleep(5000);
        // }) // Nope
        // var enter_store = element(by.css("a[href=\"actions/Catalog.action\""))
        // browser.wait(ExpectedConditions.elementToBeClickable(enter_store), 5000);  
        // browser.actions().mouseMove(enter_store).click().perform(); // Still nope
        
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
