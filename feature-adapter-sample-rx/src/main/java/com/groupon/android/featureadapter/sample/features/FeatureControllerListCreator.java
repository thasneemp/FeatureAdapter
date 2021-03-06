/*
 * Copyright (c) 2017, Groupon, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.groupon.android.featureadapter.sample.features;

import com.groupon.android.featureadapter.sample.features.badges.BadgeController;
import com.groupon.android.featureadapter.sample.features.collapsible.CollapsibleController;
import com.groupon.android.featureadapter.sample.features.header.HeaderController;
import com.groupon.android.featureadapter.sample.features.options.OptionsController;
import com.groupon.android.featureadapter.sample.state.SampleModel;
import com.groupon.featureadapter.FeatureController;

import java.util.List;

import javax.inject.Inject;

import static java.util.Arrays.asList;

public class FeatureControllerListCreator {

  private final List<FeatureController<SampleModel>> featureControllers;

  @Inject
  public FeatureControllerListCreator(OptionsController optionsController,
                                      CollapsibleController collapsibleController) {
    featureControllers = asList(
      new HeaderController(),
      optionsController,
      collapsibleController,
      new BadgeController()
    );
  }

  public List<FeatureController<SampleModel>> getFeatureControllerList() {
    return featureControllers;
  }
}
