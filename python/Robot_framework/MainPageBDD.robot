*** Settings ***
Library     MainPageSteps.py

*** Test Cases ***
Search for cat Persian
        Given main page is opened
        And entry store link is clicked
        When search for cat "Persian"
        Then Cat link Text is found "FL-DLH-02"

*** Keywords ***
Main page is opened
    open main page

Entry store link is clicked
   click enter store link

Search for cat "${cat_name}"
   search for product   ${cat_name}

Cat link Text is found "${cat_link}"
    check link  ${cat_link}