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

import com.google.gerrit.extensions.annotations.RequiresCapability;
import com.google.gerrit.extensions.restapi.RestModifyView;
import com.google.gerrit.extensions.webui.UiAction;
import com.google.gerrit.server.change.RevisionResource;

@RequiresCapability(PrintHelloCapability.PRINT_HELLO_CAPABILITY)
class SayHelloAction extends UiAction<RevisionResource> implements
    RestModifyView<RevisionResource, SayHelloAction.Input> {
  static class Input {
  }

  @Override
  public String apply(RevisionResource rsrc, Input input) {
    return "Hello world!";
  }

  @Override
  public UiAction.Description getDescription(RevisionResource resource) {
    return new UiAction.Description()
      .setLabel("Say-hello")
      .setTitle("Say hello in different languages")
      .setVisible(true);
  }
}
