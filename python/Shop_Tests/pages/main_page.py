from selenium.webdriver.common.by import By

from Shop_Tests.webdriver_wrapper.webdriver_wrapper import WebDriverWrapper


class MainPage:
    url = "http://przyklady.javastart.pl/jpetstore/"
    enter_store_link = (By.LINK_TEXT, "Enter the Store")
    search_button = (By.NAME, "searchProducts")
    search_field = (By.NAME, "keyword")

    def __init__(self, context, web_driver_wrapper: WebDriverWrapper):
        self.context = context
        self.web_driver_wrapper = web_driver_wrapper

    def click_enter_store_link(self):
        self.context.mylogging.log("Click enter store link")
        self.web_driver_wrapper.context.driver.find_element(*MainPage.enter_store_link) \
            .click()
        return self
