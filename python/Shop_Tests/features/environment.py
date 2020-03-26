import time

from Shop_Tests.pages.main_page import MainPage
from Shop_Tests.webdriver_wrapper.webdriver_wrapper import WebDriverWrapper
from Utils.utils import create_dir, MyLogging, take_screenshot

BEHAVE_DEBUG = True
logs_path = "logs"


def before_feature(context, feature):
    create_dir(logs_path)

    context.feature_logging = MyLogging()
    context.log_feature_file = logs_path + "\\%s_Log.log" % feature.name
    context.feature_logging.add_log_file(context.log_feature_file)
    context.feature_logging.log().info("Start Feature: " + feature.name)


def before_scenario(context, scenario):
    context.scenario_logging = MyLogging()

    context.scenario_name = scenario.name.replace(" ", "_")
    context.time_stump = str(time.strftime('%Y-%m-%d_%H_%M_%S'))
    context.logs_dir_name = logs_path + "\\" + context.scenario_name + "_" + context.time_stump
    create_dir(context.logs_dir_name)
    context.log_file = context.logs_dir_name + "\\%s_Log_%s.log" % (context.scenario_name, context.time_stump)
    context.scenario_logging.add_log_file(context.log_file)

    context.web_driver_wrapper = WebDriverWrapper()
    context.main_page = MainPage(context.scenario_logging, context.web_driver_wrapper)

    context.scenario_logging.log().info("Scenario started: " + scenario.name)


def before_step(context, step):
    context.scenario_logging.log().info("Step: " + step.name)


def after_scenario(context, scenario):
    context.scenario_logging.log().info("Test Finished: " + context.scenario_name)
    context.scenario_logging.log().info("Scenario status: " + str(scenario.status))
    context.web_driver_wrapper.tear_down()


def after_step(context, step):
    take_screenshot(context.web_driver_wrapper.driver, context.logs_dir_name + "\\", "%s" % step.name)
    context.scenario_logging.log().info("Step status: " + str(step.status))
    if BEHAVE_DEBUG and str(step.status) == "Status.failed":
        import ipdb
        context.scenario_logging.log().error("TEST FAIL")
        context.scenario_logging.log().error(str(ipdb.post_mortem(step.exc_traceback)))
        context.scenario_logging.log().error(context.get_log('browser'), )
        context.scenario_logging.log().error(context.get_log('driver'))


def after_feature(context, feature):
    context.feature_logging.log().info("Feature Finished: " + feature.name)
    context.feature_logging.log().info("Feature status: " + str(feature.status))
