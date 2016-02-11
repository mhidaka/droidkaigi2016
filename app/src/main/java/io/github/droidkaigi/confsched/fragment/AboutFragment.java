package io.github.droidkaigi.confsched.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.droidkaigi.confsched.MainApplication;
import io.github.droidkaigi.confsched.R;
import io.github.droidkaigi.confsched.activity.ActivityNavigator;
import io.github.droidkaigi.confsched.databinding.FragmentAboutBinding;
import io.github.droidkaigi.confsched.util.AppUtil;
import io.github.droidkaigi.confsched.util.LocaleUtil;

public class AboutFragment extends Fragment {

    public static final String TAG = AboutFragment.class.getSimpleName();
    private static final String REP_TWITTER_NAME = "mhidaka";
    private static final String CONF_TWITTER_NAME = "DroidKaigi";
    private static final String CONF_FACEBOOK_NAME = "DroidKaigi";
    private static final String LICENSE_URL = "file:///android_asset/license.html";

    @Inject
    ActivityNavigator activityNavigator;

    private FragmentAboutBinding binding;
    private String siteUrl;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        siteUrl = getString(R.string.about_site_url);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainApplication.getComponent(this).inject(this);
    }

    private void initView() {
        String repText = getString(R.string.about_rep, REP_TWITTER_NAME);
        binding.txtRep.setText(repText);
        AppUtil.linkify(getActivity(), binding.txtRep, REP_TWITTER_NAME, AppUtil.getTwitterUrl(REP_TWITTER_NAME));

        binding.txtSiteUrl.setText(LocaleUtil.getRtlConsideredText(siteUrl, getContext()));
        AppUtil.linkify(getActivity(), binding.txtSiteUrl, siteUrl, siteUrl);

        binding.imgFacebookClicker.setOnClickListener(v ->
                AppUtil.showWebPage(getActivity(), AppUtil.getFacebookUrl(CONF_FACEBOOK_NAME)));
        binding.imgTwitterClicker.setOnClickListener(v ->
                AppUtil.showWebPage(getActivity(), AppUtil.getTwitterUrl(CONF_TWITTER_NAME)));

        binding.txtTerms.setOnClickListener(v -> {
            // TODO
        });
        binding.txtQuestionnaire.setOnClickListener(v -> {
            AppUtil.showWebPage(getActivity(), getString(R.string.about_inquiry_url));
        });
        binding.txtLicense.setOnClickListener(v ->
                activityNavigator.showWebView(getActivity(), LICENSE_URL, getString(R.string.about_license)));

        binding.txtVersion.setText(AppUtil.getVersionName(getActivity()));
    }

}
