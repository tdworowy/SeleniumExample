Feature: Main Page Search
    Scenario Outline: Search for cat
        Given Entry store link is clicked
        When Search for cat '<name>'
        Then Cat link Text is found '<link>'

        Examples:
            | name          | link          |
            | Persian       | FL-DLH-02     |
            | Manx          | FL-DSH-01     |