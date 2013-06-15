package com.google.gerrit.plugins;

import static com.google.gerrit.extensions.config.CapabilityDefinitionResource.EXTERNAL_CAPABILITY_KIND;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.inject.AbstractModule;
import static com.google.gerrit.plugins.PrintHelloCapability.PRINT_HELLO_CAPABILITY;


public class HelloWorldModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(EXTERNAL_CAPABILITY_KIND).annotatedWith(
        Exports.named(PRINT_HELLO_CAPABILITY)).to(PrintHelloCapability.class);
  }
}
