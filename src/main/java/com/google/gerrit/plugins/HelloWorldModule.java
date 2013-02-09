// Copyright (C) 2012 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.plugins;

import static com.google.gerrit.server.change.RevisionResource.REVISION_KIND;
import static com.google.gerrit.server.project.ProjectResource.PROJECT_KIND;

import com.google.gerrit.extensions.restapi.RestApiModule;
import com.google.inject.AbstractModule;

public class HelloWorldModule extends AbstractModule {
  @Override
  protected void configure() {
    install(new RestApiModule() {
      @Override
      protected void configure() {
        post(REVISION_KIND, "say-hello").to(HelloCommand.class);
        post(PROJECT_KIND, "say-hello-project").to(HelloProjectCommand.class);
      }
    });
  }
}
