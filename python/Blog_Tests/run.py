import os
from screens.screen_path import get_screen_path


# need to set PYTHONPATH=%PYTHONPATH%;%cd% from python folder
def run_behave():
    path = os.path.dirname(os.path.abspath(__file__))
    screen_path = get_screen_path()
    commend = (
        'cd "%s" && behave -f allure_behave.formatter:AllureFormatter -o "%s" ./features'
        % (path, screen_path)
    )
    print(commend)
    os.system(commend)


if __name__ == "__main__":
    run_behave()
