// Generated by view binder compiler. Do not edit!
package com.example.mypermission.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mypermission.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btn;

  @NonNull
  public final TextView descriptionTextView;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final TextView titleTextView;

  @NonNull
  public final TextView usageAnalysisTextView;

  @NonNull
  public final LinearLayout usageItemsLayout;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull Button btn,
      @NonNull TextView descriptionTextView, @NonNull ImageView imageView2,
      @NonNull TextView titleTextView, @NonNull TextView usageAnalysisTextView,
      @NonNull LinearLayout usageItemsLayout) {
    this.rootView = rootView;
    this.btn = btn;
    this.descriptionTextView = descriptionTextView;
    this.imageView2 = imageView2;
    this.titleTextView = titleTextView;
    this.usageAnalysisTextView = usageAnalysisTextView;
    this.usageItemsLayout = usageItemsLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn;
      Button btn = ViewBindings.findChildViewById(rootView, id);
      if (btn == null) {
        break missingId;
      }

      id = R.id.descriptionTextView;
      TextView descriptionTextView = ViewBindings.findChildViewById(rootView, id);
      if (descriptionTextView == null) {
        break missingId;
      }

      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.titleTextView;
      TextView titleTextView = ViewBindings.findChildViewById(rootView, id);
      if (titleTextView == null) {
        break missingId;
      }

      id = R.id.usageAnalysisTextView;
      TextView usageAnalysisTextView = ViewBindings.findChildViewById(rootView, id);
      if (usageAnalysisTextView == null) {
        break missingId;
      }

      id = R.id.usageItemsLayout;
      LinearLayout usageItemsLayout = ViewBindings.findChildViewById(rootView, id);
      if (usageItemsLayout == null) {
        break missingId;
      }

      return new ActivityMainBinding((ConstraintLayout) rootView, btn, descriptionTextView,
          imageView2, titleTextView, usageAnalysisTextView, usageItemsLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
