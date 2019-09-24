import { Given, Then, When} from 'cucumber';
import {browser, element,by} from 'protractor'
import {config} from '../../protractor.conf.js'
import { assert } from 'chai';

function searchForProduct(productName:string):void {
    element(by.name("keyword")).sendKeys(productName)
    element(by.name("searchProducts")).click()
}

module.exports = function() {

    Given(/^Main page is opened$/, ()=> {
        browser.get(config.baseURL)
      });
    Given(/^Entry store link is clicked$/, ()=> {
        element(by.linkText("Enter the Store")).click
      });
    When(/^Search for cat "([^"]*)"$/, (cat_name:string)=> {
        searchForProduct(cat_name)
      });
    When(/^Search for dog "([^"]*)"$/, (dog_name:string)=> {
        searchForProduct(dog_name)
      });
}