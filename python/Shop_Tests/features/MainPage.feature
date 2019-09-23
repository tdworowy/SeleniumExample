Feature: Main Page Search
    Scenario Outline: Search for cat <cat_name>
        Given Main page is opened
        And Entry store link is clicked
        When Search for cat <cat_name>
        Then Cat link Text is found <cat_link>

        Examples:
            | cat_name      | cat_link      |
            | Persian       | FL-DLH-02     |
            | Manx          | FL-DSH-01     |

    Scenario Outline: Search for dog <dog_name>
        Given Main page is opened
        And Entry store link is clicked
        When Search for dog <dog_name>
        Then dog link Text is found <dog_link>

        Examples:
            | dog_name      | dog_link     |
            | Chihuahua     | K9-CW-01     |
            | Dalmation     | K9-DL-01     |