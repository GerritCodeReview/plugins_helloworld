gerrit_plugin(
  name = 'helloworld',
  srcs = glob(['src/main/java/**/*.java']),
  resources = glob(['src/main/resources/**/*']),
  manifest_entries = [
    'Gerrit-HttpModule: com.google.gerrit.plugins.HelloWorldHttpModule',
    'Gerrit-SshModule: com.google.gerrit.plugins.HelloWorldCommandModule'
  ]
)
