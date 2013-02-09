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

import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.extensions.webui.UiCommand;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.change.RevisionResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HelloCommand implements UiCommand<RevisionResource>,
    RestModifyView<RevisionResource, HelloCommand.Input> {
  static final Logger log = LoggerFactory.getLogger(HelloCommand.class);

  static class Input {
  }

  private Provider<CurrentUser> cu;

  @Inject
  HelloCommand(Provider<CurrentUser> cu) {
    this.cu = cu;
  }

  @Override
  public Place getPlace() {
    return UiCommand.Place.CURRENT_PATCHSET_ACTION_PANEL;
  }

  @Override
  public boolean isVisible(RevisionResource rev) {
    return cu.get().getUserName() != null;
  }

  @Override
  public boolean isEnabled(RevisionResource rev) {
    return isActive(rev);
  }

  @Override
  public String getLabel(RevisionResource rev) {
    return "Hello";
  }

  @Override
  public String getTitle(RevisionResource rev) {
    return isActive(rev)
        ? "Say Hello to odd patch number"
        : "No Hello to even patch number, try an odd one";
  }

  private static boolean isActive(RevisionResource rev) {
    return rev.getPatchSet().getPatchSetId() % 2 == 1;
  }

  @Override
  public String apply(RevisionResource rev, Input input) {
    String msg = String.format(
        "Hello %s, greetings from the core gerrit page (for the patch set %d)",
        cu.get().getUserName(), rev.getPatchSet().getPatchSetId());
    log.info(msg);
    return msg;
  }
}
