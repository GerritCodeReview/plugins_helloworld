// Copyright (C) 2013 The Android Open Source Project
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

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.extensions.webui.UiCommand;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.project.ProjectResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

class HelloProjectCommand implements UiCommand<ProjectResource>,
    RestModifyView<ProjectResource, HelloProjectCommand.Input> {
  static final Logger log = LoggerFactory.getLogger(HelloProjectCommand.class);

  static class Input {
  }

  private Provider<CurrentUser> cu;

  @Inject
  HelloProjectCommand(Provider<CurrentUser> cu) {
    this.cu = cu;
  }

  @Override
  public EnumSet<Place> getPlaces() {
    return EnumSet.of(UiCommand.Place.PROJECT_INFO_ACTION_PANEL);
  }

  @Override
  public boolean isVisible(ProjectResource rev) {
    return cu.get().getUserName() != null;
  }

  @Override
  public boolean isEnabled(ProjectResource rev) {
    return true;
  }

  @Override
  public String getLabel(ProjectResource rev) {
    return "Hello";
  }

  @Override
  public String getConfirmationMessage(ProjectResource rev) {
    return String.format("Are you sure you want to delete the project %s?",
        rev.getName());
  }

  @Override
  public String getTitle(ProjectResource rev) {
    return String.format("Deleting project %s", rev.getName());
  }

  @Override
  public Result apply(ProjectResource rev, Input input) {
    String msg =
        String.format("The project %s was successfully deleted", rev.getName());
    log.info(msg);
    Result result = new Result();
    result.message = msg;
    result.action = Result.Action.REDIRECT;
    result.location = "/admin/projects";
    return result;
  }
}
