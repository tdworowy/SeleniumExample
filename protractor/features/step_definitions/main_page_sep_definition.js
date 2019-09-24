"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var cucumber_1 = require("cucumber");
var protractor_1 = require("protractor");
var protractor_conf_js_1 = require("../../protractor.conf.js");
var chai_1 = require("chai");
function searchForProduct(productName) {
    protractor_1.element(protractor_1.by.name("keyword")).sendKeys(productName);
    protractor_1.element(protractor_1.by.name("searchProducts")).click();
}
function isProductLinkDisplayed(productLink) {
    var product_link = protractor_1.element(protractor_1.by.linkText("productLink"));
    var until = protractor_1.ExpectedConditions;
    try {
        protractor_1.browser.wait(until.presenceOf(product_link), 5000, 'Element taking too long to appear in the DOM');
        return true;
    }
    catch (e) {
        return false;
    }
}
module.exports = function () {
    cucumber_1.Given(/^Main page is opened$/, function () {
        protractor_1.browser.get(protractor_conf_js_1.config.baseURL);
    });
    cucumber_1.Given(/^Entry store link is clicked$/, function () {
        protractor_1.element(protractor_1.by.linkText("Enter the Store")).click;
    });
    cucumber_1.When(/^Search for cat "([^"]*)"$/, function (cat_name) {
        searchForProduct(cat_name);
    });
    cucumber_1.When(/^Search for dog "([^"]*)"$/, function (dog_name) {
        searchForProduct(dog_name);
    });
    cucumber_1.Then(/^Cat link Text is found "([^"]*)"$/, function (cat_link) {
        chai_1.assert.isTrue(isProductLinkDisplayed(cat_link));
    });
    cucumber_1.Then(/^Dog link Text is found "([^"]*)"$/, function (dog_link) {
        chai_1.assert.isTrue(isProductLinkDisplayed(dog_link));
    });
};
