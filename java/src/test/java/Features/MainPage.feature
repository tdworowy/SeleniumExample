Feature: Main Page Search
    Scenario Outline: Search for cat '<name>'
        Given Main page is opened
        And Entry store link is clicked
        When Search for cat '<name>'
        Then Cat link Text is found '<link>'

        Examples:
            | name          | link          |
            | Persian       | FL-DLH-02     |
            | Manx          | FL-DSH-01     |

     Scenario Outline: Search for dog '<name>'
             Given Main page is opened
             And Entry store link is clicked
             When Search for dog '<name>'
             Then Cat link Text is found '<link>'

        Examples:
            | name          | link         |
            | Chihuahua     | K9-CW-01     |
            | Dalmation     | K9-DL-01     |