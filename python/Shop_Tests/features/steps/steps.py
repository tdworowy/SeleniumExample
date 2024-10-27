from behave import given, When, Then


def search_for_product(context, name: str):
    context.main_page.enter_search_text(name).click_search_button()


def check_link(context, link: str):
    assert context.main_page.check_if_link_is_displayed(link)


@given("Main page is opened")
def open_main_page(context):
    context.main_page.open_page()


@given("Entry store link is clicked")
def click_enter_store_link(context):
    context.main_page.click_enter_store_link()


@When("Search for cat {cat_name}")
def search_for_cat(context, cat_name: str):
    search_for_product(context, cat_name)


@When("Search for dog {dog_name}")
def search_for_dog(context, dog_name: str):
    search_for_product(context, dog_name)


@Then("Cat link Text is found {cat_link}")
def check_cat_link(context, cat_link: str):
    check_link(context, cat_link)


@Then("Dog link Text is found {dog_link}")
def check_dog_link(context, dog_link: str):
    check_link(context, dog_link)
