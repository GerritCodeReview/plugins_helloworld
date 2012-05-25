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

import com.google.gerrit.reviewdb.client.Project;
import com.google.gerrit.server.git.GitRepositoryManager;
import com.google.gerrit.server.git.LocalDiskRepositoryManager;
import com.google.gerrit.server.git.RepositoryCaseMismatchException;
import com.google.gerrit.server.plugins.PluginLoader;
import com.google.inject.Inject;

import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.SortedSet;

public class RepositoryManagerWrapper implements GitRepositoryManager {
  static final Logger log =
          LoggerFactory.getLogger(RepositoryManagerWrapper.class);

  private final LocalDiskRepositoryManager impl;

  @Inject
  RepositoryManagerWrapper(LocalDiskRepositoryManager impl) {
    this.impl = impl;
  }

  @Override
  public Repository openRepository(Project.NameKey name)
      throws RepositoryNotFoundException, IOException {
    log.info("Opening repository via wrapper: " + name);
    return impl.openRepository(name);
  }

  @Override
  public Repository createRepository(Project.NameKey name)
      throws RepositoryCaseMismatchException, RepositoryNotFoundException,
      IOException {
    return impl.createRepository(name);
  }

  @Override
  public SortedSet<Project.NameKey> list() {
    return impl.list();
  }

  @Override
  public String getProjectDescription(Project.NameKey name)
      throws RepositoryNotFoundException, IOException {
    return impl.getProjectDescription(name);
  }

  @Override
  public void setProjectDescription(Project.NameKey name, String description) {
    impl.setProjectDescription(name, description);
  }

}
