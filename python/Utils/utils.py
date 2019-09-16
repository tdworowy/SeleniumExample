import calendar
import logging
import os
import time
from datetime import date

loggers = []


class MyLogging:
    def __init__(self):
        logging.basicConfig(format="%(levelname)s|%(asctime)s|%(message)s")

    @staticmethod
    def clear_loggers():
        while loggers: loggers.pop()

    @staticmethod
    def log(path=os.path.dirname(os.path.abspath(__file__)) + "\\log.log"):
        for logger in loggers:
            if path in [handler.baseFilename for handler in logger.handlers if
                        hasattr(handler, 'baseFilename')]:
                return logger
        else:

            file_handler = logging.FileHandler(path)
            file_handler.setFormatter(logging.Formatter("%(levelname)s|%(asctime)s|%(message)s"))
            file_handler.setLevel(logging.DEBUG)

            new_logger = logging.getLogger("Logger%s" % get_millis())
            new_logger.setLevel(logging.DEBUG)
            new_logger.addHandler(file_handler)

            loggers.append(new_logger)
            return new_logger

    def log_result(self, test_name, result):
        message = "Name: {x} Result {y}".format(x=test_name, y=result)
        self.log("TestsResultLog.txt").info(message)


def create_dir(context, name):
    if not os.path.exists(name):
        os.makedirs(name)


def take_screenshot(context, path, file):
    context.driver.save_screenshot(os.path.join(path, file.replace(' ', '_') + '.png'))


def take_screenshot_(driver, path, file):
    driver.save_screenshot(os.path.join(path,file.replace(' ', '_') + '.png'))

def get_millis():
    return int(round(time.time() * 1000))
