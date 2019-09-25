from Utils.utils import create_dir

from Robot_framework.pages.main_page import MainPage
from Robot_framework.webdriver_wrapper.webdriver_wrapper import WebDriverWrapper


class MainPageSteps:

    def __init__(self):
        web_driver_wrapper = WebDriverWrapper()
        self.main_page = MainPage(web_driver_wrapper)

    def search_for_product(self,name: str):
        self.main_page \
            .enter_search_text(name) \
            .click_search_button()


    def check_link(self, link: str):
        assert self.main_page.check_if_link_is_displayed(link)

    def open_main_page(self):
        self.main_page.open_page()

    def click_enter_store_link(self):
        self.main_page.click_enter_store_link()

