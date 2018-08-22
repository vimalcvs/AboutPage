package com.vimalcvs.colledge.aboutus.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.vimalcvs.aboutpage.builder.AboutBuilder;
import com.vimalcvs.colledge.R;
import com.vimalcvs.aboutpage.views.AboutView;

/**
 * Created by VimalCVS on 22/08/18.
 */
public class SampleHelper implements View.OnClickListener {

    private Activity activity;


    private SampleHelper(Activity activity) {
        this.activity = activity;
    }

    public static SampleHelper with(Activity activity) {
        return new SampleHelper(activity);
    }

    public SampleHelper init() {
        return this;
    }

    public void loadAbout() {
        final FrameLayout flHolder = activity.findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(activity)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.drawable.profile_pic)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Vimal K. Vishwakarma")
                .setSubTitle("Android Developer")
                .setLinksColumnsCount(4)
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover. I tried my best to create this app user friendly. I hope you enjoy it.")
                .addFacebookLink("vimalcvs")
                .addTwitterLink("vimalvishwakar6")
                .addInstagramLink("vimal.vkv")
                .addBitbucketLink("3WJbPRQyy89AKlltS56lYy")
                .addGitHubLink("vimalcvs")
                .addGooglePlayStoreLink("6064542819837033805")
                .addGooglePlusLink("+VIMALvimalcvs")
                .addYoutubeChannelLink("UC-mogeuYT8Gn6DWvvP4DCnQ")
                .addEmailLink("vimalcvs29@gmail.com")
                .addWhatsappLink("Vimal CVS", "+919971770331")
                .addGoogleLink("user")
                .addAndroidLink("user")
                .addWebsiteLink("http://ducslecture.blogspot.in")
                .addFiveStarsAction()
                .addMoreFromMeAction("Vimal CVS")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("vimalcvs29@gmail.com")
                .addPrivacyPolicyAction("https://raw.githubusercontent.com/vimalcvs/DUCSLectures/master/LICENSE")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addRemoveAdsAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }
    }
}
