import time

from Shop_Tests.pages.main_page import MainPage
from Shop_Tests.webdriver_wrapper.webdriver_wrapper import WebDriverWrapper
from Utils.utils import create_dir, take_screenshot, MyLogging

BEHAVE_DEBUG = True

logs_path = "logs"


def before_feature(context, feature):
    context.mylogging = MyLogging()
    context.log_feature_file = logs_path + "\\%s_Log.log" % feature.name
    context.mylogging.log_file = context.log_feature_file
    context.web_driver_wrapper = WebDriverWrapper(context)
    context.main_page = MainPage(context, context.web_driver_wrapper)

    create_dir(context, logs_path)

    context.mylogging.log(context.log_feature_file).info("Start Feature: " + feature.name)


def before_scenario(context, scenario):
    context.scenario_name = scenario.name.replace(" ", "_")
    context.time_stump = str(time.strftime('%Y-%m-%d_%H_%M_%S'))
    context.logs_dir_name = logs_path + "\\" + context.scenario_name + "_" + context.time_stump
    create_dir(context, context.logs_dir_name)
    context.log_file = context.logs_dir_name + "\\%s_Log_%s.log" % (context.scenario_name, context.time_stump)
    context.mylogging.log_file = context.log_file
    context.mylogging.log().info("Scenario started: " + scenario.name)


def before_step(context, step):
    context.mylogging.log().info("Step: " + step.name)


def after_scenario(context, scenario):
    context.mylogging.log().info("Test Finished: " + context.scenario_name)
    context.mylogging.log().info("Scenario status: " + str(scenario.status))
    context.web_driver_wrapper.tear_down()


def after_step(context, step):
    take_screenshot(context, context.logs_dir_name + "\\", "%s_%s" % (context.logs_dir_name, step.name))
    context.mylogging.log().info("Step status: " + str(step.status))
    if BEHAVE_DEBUG and str(step.status) == "Status.failed":
        import ipdb
        context.mylogging.log().error("TEST FAIL")
        context.mylogging.log().error(str(ipdb.post_mortem(step.exc_traceback)))
        context.mylogging.log().error(context.get_log('browser'), )
        context.mylogging.log().error(context.get_log('driver'))


def after_feature(context, feature):
    context.mylogging.log_file = context.log_feature_file
    context.mylogging.log()
    context.mylogging.log().info("Feature Finished: " + feature.name)
    context.mylogging.log().info("Feature status: " + str(feature.status))
