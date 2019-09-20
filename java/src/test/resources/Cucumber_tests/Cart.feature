Feature: Place order
    Scenario Outline: Add fish to cart '<fishID>'
        Given Main page is opened
        And Entry store link is clicked
        And user is log in 'j2ee' 'j2ee'
        And fish page is opened
        When Add '<fishID>' '<itemID>' to cart
        Then fish '<itemID>' is in the cart

        Examples:
            | fishID        | itemID   |
            | FI-SW-01      | EST-1    |
            | FI-FW-02      | EST-20   |
