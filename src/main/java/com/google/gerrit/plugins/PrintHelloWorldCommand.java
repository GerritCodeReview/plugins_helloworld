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

import com.google.gerrit.sshd.BaseCommand;

import org.apache.sshd.server.Environment;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.io.PrintWriter;

public final class PrintHelloWorldCommand extends BaseCommand {

  @Argument(usage = "name of user")
  private String name = "world";

  @Option(name = "french", usage = "output in French?")
  private boolean french = false;

  @Override
  public void start(final Environment env) {
    startThread(new CommandRunnable() {
      @Override
      public void run() throws Failure {
        parseCommandLine();

        final PrintWriter stdout = toPrintWriter(out);
        final String greeting = (french ? "Bonjour " : "Hello ");
        stdout.println(greeting + name + "!");
        stdout.flush();
      }
    });
  }
}
