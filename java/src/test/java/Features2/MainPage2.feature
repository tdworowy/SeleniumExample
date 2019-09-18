Feature: Main Page Search
    Scenario Outline: Search for dog
        Given Entry store link is clicked
        When Search for dog '<name>'
        Then Cat link Text is found '<link>'

        Examples:
            | name          | link         |
            | Chihuahua     | K9-CW-01     |
            | Dalmation     | K9-DL-01     |