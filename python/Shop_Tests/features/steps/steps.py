from behave import given


@given("Main page is opened")
def open_main_page(context):
    context.main_page.open_page()

