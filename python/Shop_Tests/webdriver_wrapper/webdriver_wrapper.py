from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions

from Chrome_Driver_Folder.driver_path import get_driver_path


class WebDriverWrapper:
    def __init__(self, context):
        chrome_driver_path = get_driver_path() + '\\chromedriver.exe'
        self.context = context
        self.context.driver = webdriver.Chrome(chrome_driver_path)

    def open_page(self, server: str):
        self.context.driver.get(server)
        self.context.driver.implicitly_wait(1)

    def tear_down(self):
        self.context.driver.quit()

    def wait_for_element(self, locator, element: str):
        wait = WebDriverWait(self.context.driver, 10)
        wait.until(expected_conditions.visibility_of_element_located((locator, element)))
