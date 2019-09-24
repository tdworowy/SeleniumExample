
var reporter = require('cucumber-html-reporter'); 
exports.config = {

    chromeDriver: '../chromedriver/chromedriver.exe',
    getPageTimeout: 60000,
    allScriptsTimeout: 500000,
    framework: 'custom',
    frameworkPath: require.resolve('./node_modules/protractor-cucumber-framework'),
    capabilities: {
      'browserName': 'chrome'
    },
  
    specs: [
      './features/*.feature'
    ],
    params: {
      baseURL: "http://przyklady.javastart.pl/jpetstore/",
    },
    onComplete: () => { reporter.generate({ jsonFile: 'results.json', 
    output: "cucumber_reporter.html", 
    reportSuiteAsScenarios: true, theme: "bootstrap", })},
  
    cucumberOpts: {
      require: './features/step_definitions/main_page_sep_definition.js',
      tags: false,
      format: ['progress', './node_modules/cucumber-pretty', 'json:results.json'],
      profile: false,
      'no-source': true
    }
  };
  
  