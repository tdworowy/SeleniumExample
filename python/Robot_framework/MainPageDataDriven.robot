*** Settings ***
Test Template     Find animal
Library     MainPageSteps.py

*** Test Cases ***    Animal_name    Link
Find animal           Persian        FL-DLH-02
                      Manx           FL-DSH-01
                      Chihuahua      K9-CW-01
                      Dalmation      K9-DL-01


*** Keywords ***
Find animal
    [Arguments]    ${animal_name}    ${link}
    open main page
    click enter store link
    search for product   ${animal_name}
    check link   ${link}
