from selenium import webdriver
from selenium.webdriver.support import expected_conditions as ec
from Chrome_Driver_Folder.driver_path import get_driver_path
from selenium.webdriver.support.wait import WebDriverWait


class WebDriverWrapper:
    def __init__(self, context):
        chrome_driver_path = get_driver_path() + '\\chromedriver.exe'
        self.context = context
        self.context.driver = webdriver.Chrome(chrome_driver_path)

    def open_page(self, server):
        self.context.driver.get(server)
        self.context.driver.implicitly_wait(1)

    def wait_for_element(self, locator, text: str):
        WebDriverWait(self.context.driver, 10).until(
                ec.visibility_of_element_located(locator, text))

    def tear_down(self):
        self.context.driver.quit()
