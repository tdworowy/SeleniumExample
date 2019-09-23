from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions

from Chrome_Driver_Folder.driver_path import get_driver_path


class WebDriverWrapper:
    def __init__(self):
        chrome_driver_path = get_driver_path() + '\\chromedriver.exe'
        self.driver = webdriver.Chrome(chrome_driver_path)

    def open_page(self, server: str):
        self.driver.get(server)
        self.driver.implicitly_wait(1)

    def tear_down(self):
        self.driver.quit()

    def wait_for_element(self, locator, element: str):
        wait = WebDriverWait(self.driver, 10)
        wait.until(expected_conditions.visibility_of_element_located((locator, element)))
