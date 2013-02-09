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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gerrit.common.data.PluginAction;
import com.google.gerrit.common.data.PluginAction.Place;
import com.google.gerrit.reviewdb.client.PatchSet.Id;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.plugins.actions.Action;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class HelloAction extends Action {

  static final Logger log = LoggerFactory.getLogger(HelloAction.class);

  @Inject
  Provider<CurrentUser> cu;

  @Override
  public String getName() {
    return "Hello";
  }

  @Override
  public Place getPlace() {
    return PluginAction.Place.patchSetActionPanel;
  }

  @Override
  public boolean isVisible() {
    return cu.get().getUserName() != null;
  }

  @Override
  public boolean isCurrentPatchSet() {
    return true;
  }

  @Override
  public boolean isEnabled(Id id) {
    return isActive(id);
  }

  @Override
  public String getTitle(Id id) {
    return isActive(id) ? "Say Hello to odd patch number"
        : "No Hello to even patch number, try an odd one";
  }

  private boolean isActive(Id id) {
    return id.get() % 2 == 1;
  }

  @Override
  public void firePatchSetAction(Id patchSetId) {
    log.info(String.format(
        "Hello %s, greetings from the core gerrit page (for the patch set %s)", cu.get()
            .getUserName(), patchSetId));
  }
}
