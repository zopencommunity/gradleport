node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/Gradleport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/Gradleport.git'), string(name: 'PORT_DESCRIPTION', value: 'Gradle build system, adapted for use on z/OS' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
