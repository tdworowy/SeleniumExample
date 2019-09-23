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

    def open_page(self):
        self.context.mylogging.log("Open page %s" % MainPage.url)
        self.web_driver_wrapper.open_page(MainPage.url)

    def click_enter_store_link(self):
        self.context.mylogging.log("Click enter store link")
        self.web_driver_wrapper.context.driver.find_element(*MainPage.enter_store_link) \
            .click()
        return self

    def click_search_button(self):
        self.context.mylogging.log("Click search button")
        self.web_driver_wrapper.context.driver.find_element(*MainPage.search_button) \
            .click()
        return self

    def enter_search_text(self, text: str):
        self.context.mylogging.log("Enter text: %s" % text)
        self.web_driver_wrapper.context.driver.find_element(*MainPage.search_field) \
            .send_keys(text)
        return self

    def check_if_link_is_displayed(self, link: str):
        try:
            self.web_driver_wrapper.context.wait_for_element(By.LINK_TEXT, link)
            return True
        except Exception as ex:
            self.context.mylogging.error(ex)
            return False
