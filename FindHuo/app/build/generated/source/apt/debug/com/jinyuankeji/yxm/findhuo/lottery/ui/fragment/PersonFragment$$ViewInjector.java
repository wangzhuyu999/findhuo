// Generated code from Butter Knife. Do not modify!
package com.jinyuankeji.yxm.findhuo.lottery.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class PersonFragment$$ViewInjector<T extends com.jinyuankeji.yxm.findhuo.lottery.ui.fragment.PersonFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558659, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131558659, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131558844, "field 'tvPersonUsername'");
    target.tvPersonUsername = finder.castView(view, 2131558844, "field 'tvPersonUsername'");
    view = finder.findRequiredView(source, 2131558845, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558847, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131558846, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void reset(T target) {
    target.tvTitle = null;
    target.tvPersonUsername = null;
  }
}
