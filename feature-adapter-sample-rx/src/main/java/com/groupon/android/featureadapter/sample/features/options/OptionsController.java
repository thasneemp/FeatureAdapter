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
package com.groupon.android.featureadapter.sample.features.options;

import android.app.Activity;

import com.groupon.android.featureadapter.sample.model.Deal;
import com.groupon.android.featureadapter.sample.model.Option;
import com.groupon.android.featureadapter.sample.state.SampleModel;
import com.groupon.featureadapter.AdapterViewTypeDelegate;
import com.groupon.featureadapter.FeatureAdapterItemDecoration;
import com.groupon.featureadapter.FeatureController;
import com.groupon.featureadapter.ViewItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class OptionsController extends FeatureController<SampleModel> {

  private final OptionsAdapterViewTypeDelegate optionsDelegate = new OptionsAdapterViewTypeDelegate();

  @Inject Activity activity;
  @Inject FeatureAdapterItemDecoration featureAdapterItemDecoration;

  private OptionsItemDecoration decoration;

  @Override
  public Collection<AdapterViewTypeDelegate> getAdapterViewTypeDelegates() {
    return singletonList(optionsDelegate);
  }

  @Override
  public List<ViewItem> buildItems(SampleModel sampleModel) {
    Deal deal = sampleModel.deal();
    if (deal == null) {
      return emptyList();
    }

    if (decoration == null) {
      decoration = new OptionsItemDecoration(activity);
      featureAdapterItemDecoration.registerFeatureDecoration(decoration, optionsDelegate);
    }

    List<ViewItem> items = new ArrayList<>(deal.options.size());
    for (Option option : deal.options) {
      items.add(new ViewItem<>(fromOption(option, sampleModel.selectedOption()), optionsDelegate));
    }
    return items;
  }

  private static OptionsModel fromOption(Option option, Option selectedOption) {
    return OptionsModel.builder()
      .setUuid(option.uuid)
      .setTitle(option.title)
      .setPrice(option.price)
      .setSelected(option == selectedOption)
      .build();
  }
}
