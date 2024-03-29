// Copyright (C) 2016 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.hooks;

import com.google.gerrit.common.Nullable;
import java.nio.file.Path;

class AsynchronousHook extends Hook {
  private final HookQueue queue;

  AsynchronousHook(HookQueue queue, Path path) {
    super(path);
    this.queue = queue;
  }

  @Nullable
  @Override
  HookResult execute(HookArgs args) {
    queue.submit(path, args);
    return null;
  }

  @Nullable
  @Override
  HookResult execute(String projectName, HookArgs args) {
    queue.submit(projectName, path, args);
    return null;
  }
}
