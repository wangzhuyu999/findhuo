// Generated code from Butter Knife. Do not modify!
package com.jinyuankeji.yxm.findhuo;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class RegistActivity$$ViewInjector<T extends com.jinyuankeji.yxm.findhuo.RegistActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558581, "field 'etRegisterPhone'");
    target.etRegisterPhone = finder.castView(view, 2131558581, "field 'etRegisterPhone'");
    view = finder.findRequiredView(source, 2131558651, "field 'etRegisterPwd'");
    target.etRegisterPwd = finder.castView(view, 2131558651, "field 'etRegisterPwd'");
    view = finder.findRequiredView(source, 2131558652, "method 'onClick'");
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
    target.etRegisterPhone = null;
    target.etRegisterPwd = null;
  }
}
