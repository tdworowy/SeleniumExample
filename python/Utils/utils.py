import os
import time
import logging
from importlib import reload


class MyLogging:

    def __init__(self):
        logging.shutdown()
        reload(logging)
        logging.basicConfig(format="%(levelname)s|%(asctime)s|%(message)s")
        self.loggers = []

    def add_log_file(self, path):
        self.log_file = path
        for logger in self.loggers:
            if self.log_file in [handler.baseFilename for handler in logger.handlers if
                                 hasattr(handler, 'baseFilename')]:
                self.logger = logger
        else:
            file_handler = logging.FileHandler(self.log_file)
            file_handler.setFormatter(logging.Formatter("%(levelname)s|%(asctime)s|%(message)s"))
            file_handler.setLevel(logging.DEBUG)

            new_logger = logging.getLogger("Logger%s" % get_millis())
            new_logger.setLevel(logging.DEBUG)
            new_logger.addHandler(file_handler)

            self.loggers.append(new_logger)
            self.logger = new_logger

    def clear_loggers(self):
        while self.loggers: self.loggers.pop()

    def log(self):
        return self.logger


def create_dir(name):
    if not os.path.exists(name):
        os.makedirs(name)


def take_screenshot(driver, path, file):
    driver.save_screenshot(os.path.join(path, file.replace(' ', '_') + '.png'))


def get_millis():
    return int(round(time.time() * 1000))
