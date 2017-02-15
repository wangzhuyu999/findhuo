// Generated code from Butter Knife. Do not modify!
package com.jinyuankeji.yxm.findhuo;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class LoginActivity$$ViewInjector<T extends com.jinyuankeji.yxm.findhuo.LoginActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558581, "field 'etMainUserName'");
    target.etMainUserName = finder.castView(view, 2131558581, "field 'etMainUserName'");
    view = finder.findRequiredView(source, 2131558582, "field 'etMainPwd'");
    target.etMainPwd = finder.castView(view, 2131558582, "field 'etMainPwd'");
    view = finder.findRequiredView(source, 2131558585, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick();
        }
      });
  }

  @Override public void reset(T target) {
    target.etMainUserName = null;
    target.etMainPwd = null;
  }
}
