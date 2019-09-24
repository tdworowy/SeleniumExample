"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var cucumber_1 = require("cucumber");
var protractor_1 = require("protractor");
var chai = require("chai");
var chaiAsPromised = require("chai-as-promised");
chai.use(chaiAsPromised);
var expect = chai.expect;
function searchForProduct(productName) {
    protractor_1.element(protractor_1.by.name("keyword")).sendKeys(productName);
    protractor_1.element(protractor_1.by.name("searchProducts")).click();
}
function isProductLinkDisplayed(productLink) {
    var product_link = protractor_1.element(protractor_1.by.linkText(productLink));
    expect(product_link.isPresent()).to.eventually.equal(true);
    expect(product_link.isDisplayed()).to.eventually.equal(true);
}
cucumber_1.BeforeAll(function () {
    protractor_1.browser.ignoreSynchronization = true;
    protractor_1.browser.manage().timeouts().implicitlyWait(1000);
});
cucumber_1.Given(/^Main page is opened$/, function (callback) {
    protractor_1.browser.driver.get(protractor_1.browser.params.baseURL).then(callback);
});
cucumber_1.Given(/^Entry store link is clicked$/, function () {
    protractor_1.browser.wait(protractor_1.ExpectedConditions.visibilityOf(protractor_1.element(protractor_1.by.css("a[href=\"actions/Catalog.action\"")))).then(function () {
        protractor_1.browser.driver.findElement(protractor_1.element(protractor_1.by.css("a[href=\"actions/Catalog.action\""))).click();
        protractor_1.browser.sleep(500);
    });
});
cucumber_1.When(/^Search for cat '([^\"]*)'$/, function (cat_name) {
    searchForProduct(cat_name);
});
cucumber_1.When(/^Search for dog '([^\"]*)'$/, function (dog_name) {
    searchForProduct(dog_name);
});
cucumber_1.Then(/^Cat link Text is found '([^\"]*)'$/, function (cat_link) {
    isProductLinkDisplayed(cat_link);
});
cucumber_1.Then(/^Dog link Text is found '([^\"]*)'$/, function (dog_link) {
    isProductLinkDisplayed(dog_link);
});
cucumber_1.AfterAll(function () {
    protractor_1.browser.driver.quit();
});
