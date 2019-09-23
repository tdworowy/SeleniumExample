Feature: Fail
    Scenario:  Always fail
        Given Main page is opened
        When Pass
        Then Fail
