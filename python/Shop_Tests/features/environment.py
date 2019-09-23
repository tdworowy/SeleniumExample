import time

from Shop_Tests.pages.main_page import MainPage
from Shop_Tests.webdriver_wrapper.webdriver_wrapper import WebDriverWrapper
from Utils.utils import create_dir, take_screenshot, MyLogging

BEHAVE_DEBUG = True

logs_path = "logs"

def before_feature(context, feature):
    context.mylogging = MyLogging()
    context.web_driver_wrapper = WebDriverWrapper(context)
    context.main_page = MainPage(context, context.web_driver_wrapper,)

    create_dir(logs_path)
    context.log_feature_file = logs_path + "\\%s_Log.log" % feature.name
    context.mylogging.log(context.log_feature_file).info("Start Feature: " + feature.name)


def before_scenario(context, scenario):
    context.scenario_name = scenario.name.replace(" ", "_")
    context.time_stump = str(time.strftime('%Y-%m-%d_%H_%M_%S'))
    context.screen_dir_name = logs_path + "\\" + context.scenario_name + "_" + context.time_stumpr_name
    create_dir(context, context.screen_dir_name)
    context.log_file = context.screen_dir_name + "\\%s_Log_%s.log" % (context.scenario_name, context.time_stump)
    context.mylogging.log(context.log_file).info("Scenario started: " + scenario.name)


def before_step(context, step):
    context.mylogging.log(context.log_file).info("Step: " + step.name)


def after_scenario(context, scenario):
    context.mylogging.log(context.log_file).info("Test Finished: " + context.scenario_name)
    context.mylogging.log(context.log_file).info("Scenario status: " + str(scenario.status))


def after_step(context, step):
    take_screenshot(context, context.screen_dir_name + "\\", "%s_%s" % (context.scenario_name, step.name))
    context.mylogging.log(context.log_file).info("Step status: " + str(step.status))
    if BEHAVE_DEBUG and str(step.status) == "Status.failed":
        import ipdb
        context.mylogging.log(context.log_file).error("TEST FAIL")
        context.mylogging.log(context.log_file).error(str(ipdb.post_mortem(step.exc_traceback)))
        context.mylogging.log(context.log_file).error(context.get_log('browser'), )
        context.mylogging.log(context.log_file).error(context.get_log('driver'))


def after_feature(context, feature):
    context.mylogging.log(context.log_feature_file)
    context.mylogging.log(context.log_feature_file).info("Feature Finished: " + feature.name)
    context.mylogging.log(context.log_feature_file).info("Feature status: " + str(feature.status))
