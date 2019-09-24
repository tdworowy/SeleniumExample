exports.config = {
    seleniumAddress: 'http://127.0.0.1:4444/wd/hub',
    getPageTimeout: 60000,
    allScriptsTimeout: 500000,
    framework: 'custom',
    frameworkPath: require.resolve('protractor-cucumber-framework'),
    capabilities: {
      'browserName': 'chrome'
    },
  
    specs: [
      'features/*.feature'
    ],
  
    baseURL: '"http://przyklady.javastart.pl/jpetstore/"',
  
    cucumberOpts: {
      require: 'features/step_definitions/stepDefinitions.js',
      tags: false,
      format: 'pretty',
      profile: false,
      'no-source': true
    }
  };