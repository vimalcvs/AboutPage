package com.vimalcvs.aboutpage.builder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.View;

import com.vimalcvs.aboutpage.R;
import com.vimalcvs.aboutpage.util.ColorUtil;
import com.vimalcvs.aboutpage.util.IconUtil;
import com.vimalcvs.aboutpage.util.IntentUtil;
import com.vimalcvs.aboutpage.views.AboutView;

import java.util.LinkedList;

import static com.vimalcvs.aboutpage.R.mipmap.share;

/**
 * Used to build an {@link AboutView}.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class AboutBuilder {

    private Context context;
    private IntentUtil util;

    private String name;
    private String subTitle;
    private String brief;
    private String appName;
    private String appTitle;

    private Bitmap photo;
    private Bitmap cover;
    private Bitmap appIcon;

    private int nameColor;
    private int subTitleColor;
    private int briefColor;
    private int iconColor;
    private int backgroundColor;

    private boolean showDivider = true;
    private int dividerColor = 0;
    private int dividerHeight = 4;
    private int dividerDashWidth = 15;
    private int dividerDashGap = 15;

    private boolean linksAnimated = true;
    private int linksColumnsCount = 5;
    private int actionsColumnsCount = 2;

    private boolean wrapScrollView = false;
    private boolean showAsCard = true;

    private LinkedList<Item> links = new LinkedList<>();
    private LinkedList<Item> actions = new LinkedList<>();

    /**
     * @deprecated Used {@link #with(Context)} instead.
     */
    @Deprecated
    AboutBuilder(Context context) {
        this.context = context;
        this.util = new IntentUtil(context);
    }

    public static AboutBuilder with(Context context) {
        //noinspection deprecation
        return new AboutBuilder(context);
    }

    private String getApplicationID() {
        return context.getPackageName();
    }

    private PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(getApplicationID(), 0);
    }

    /**
     * Gets the id of the last action added
     */
    public int getLastActionId() {
        return getLastAction().getId();
    }

    /**
     * Gets the last action item added
     */
    public Item getLastAction() {
        return actions.getLast();
    }

    /**
     * Gets the id of the last link added
     */
    public int getLastLinkId() {
        return getLastLink().getId();
    }

    /**
     * Gets the last link item added
     */
    public Item getLastLink() {
        return links.getLast();
    }

    /**
     * Sets the developer name
     *
     * @param text the name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setName(String text) {
        this.name = text;
        return this;
    }

    /**
     * Sets the developer name
     *
     * @param text the name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setName(int text) {
        return setName(context.getString(text));
    }

    /**
     * Sets the sub title. It will be place below the developer name
     *
     * @param text the sub title
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setSubTitle(String text) {
        this.subTitle = text;
        return this;
    }

    /**
     * Sets the sub title. It will be place below the developer name
     *
     * @param text the sub title
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setSubTitle(int text) {
        return setSubTitle(context.getString(text));
    }

    /**
     * Sets a personal brief
     *
     * @param text the brief
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setBrief(String text) {
        this.brief = text;
        return this;
    }

    /**
     * Sets a personal brief
     *
     * @param text the brief
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setBrief(int text) {
        return setBrief(context.getString(text));
    }

    /**
     * Sets the app name
     *
     * @param text the app name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppName(String text) {
        this.appName = text;
        return this;
    }

    /**
     * Sets the app name
     *
     * @param text the app name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppName(int text) {
        return setAppName(context.getString(text));
    }

    /**
     * Sets the app title
     *
     * @param text the title
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppTitle(String text) {
        this.appTitle = text;
        return this;
    }

    /**
     * Sets the app title
     *
     * @param text the title
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppTitle(int text) {
        return setAppTitle(context.getString(text));
    }

    /**
     * Displays the app version below the app name
     *
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setVersionNameAsAppSubTitle() {
        try {
            return setAppTitle(context.getString(com.vimalcvs.aboutpage.R.string.version, getPackageInfo().versionName));
        } catch (PackageManager.NameNotFoundException e) {
            return setAppTitle(R.string.error);
        }
    }

    /**
     * Sets the developer photo
     *
     * @param photo the image
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setPhoto(Bitmap photo) {
        this.photo = photo;
        return this;
    }

    /**
     * Sets the developer photo
     *
     * @param photo the image
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setPhoto(int photo) {
        return setPhoto(IconUtil.getBitmap(context, photo));
    }

    /**
     * Sets the developer photo
     *
     * @param photo the image
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setPhoto(BitmapDrawable photo) {
        return setPhoto(IconUtil.getBitmap(photo));
    }

    /**
     * Sets a about cover
     *
     * @param cover the image
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setCover(Bitmap cover) {
        this.cover = cover;
        return this;
    }

    /**
     * Sets a about cover
     *
     * @param cover the image
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setCover(int cover) {
        return setCover(IconUtil.getBitmap(context, cover));
    }

    /**
     * Sets a about cover
     *
     * @param cover the image
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setCover(BitmapDrawable cover) {
        return setCover(IconUtil.getBitmap(cover));
    }

    /**
     * Sets an icon to display as app icon
     *
     * @param icon the app icon
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppIcon(Bitmap icon) {
        this.appIcon = icon;
        return this;
    }

    /**
     * Sets an icon to display as app icon
     *
     * @param icon the app icon
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppIcon(int icon) {
        return setAppIcon(IconUtil.getBitmap(context, icon));
    }

    /**
     * Sets an icon to display as app icon
     *
     * @param icon the app icon
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setAppIcon(BitmapDrawable icon) {
        return setAppIcon(IconUtil.getBitmap(icon));
    }

    /**
     * Sets the name text color
     *
     * @param color the color resource or the real color.
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setNameColor(int color) {
        this.nameColor = ColorUtil.get(context, color);
        return this;
    }

    /**
     * Sets the sub title text color
     *
     * @param color the color resource or the real color.
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setSubTitleColor(int color) {
        this.subTitleColor = ColorUtil.get(context, color);
        return this;
    }

    /**
     * Sets the brief text color
     *
     * @param color the color resource or the real color.
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setBriefColor(int color) {
        this.briefColor = ColorUtil.get(context, color);
        return this;
    }

    /**
     * Sets the divider color
     *
     * @param color the color resource or the real color.
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setDividerColor(int color) {
        this.dividerColor = ColorUtil.get(context, color);
        return this;
    }

    /**
     * Sets the icons color
     *
     * @param color the color resource or the real color.
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setIconColor(int color) {
        this.iconColor = ColorUtil.get(context, color);
        return this;
    }

    /**
     * Sets the about view background color
     *
     * @param color the color resource or the real color.
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setBackgroundColor(int color) {
        this.backgroundColor = ColorUtil.get(context, color);
        return this;
    }

    /**
     * Sets the maximum number of columns for the actions section
     *
     * @param count number of columns
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setActionsColumnsCount(int count) {
        this.actionsColumnsCount = count;
        return this;
    }


    /**
     * Sets the maximum number of columns for the links section
     *
     * @param count number of columns
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setLinksColumnsCount(int count) {
        this.linksColumnsCount = count;
        return this;
    }

    /**
     * Sets an animation when displaying the actions
     *
     * @param animate true if you want it
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setLinksAnimated(boolean animate) {
        this.linksAnimated = animate;
        return this;
    }

    /**
     * Sets the divider height
     *
     * @param dividerHeight size of the height
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setDividerHeight(int dividerHeight) {
        this.dividerHeight = dividerHeight;
        return this;
    }

    /**
     * Sets the divider dash width
     *
     * @param dividerDashWidth size of the width
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setDividerDashWidth(int dividerDashWidth) {
        this.dividerDashWidth = dividerDashWidth;
        return this;
    }

    /**
     * Sets the divider dash gap
     *
     * @param dividerDashGap size of the gap
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setDividerDashGap(int dividerDashGap) {
        this.dividerDashGap = dividerDashGap;
        return this;
    }

    /**
     * Displays a divider line between the about sections
     *
     * @param showDivider true if you want it
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
        return this;
    }

    /**
     * Places the about view inside a {@link android.widget.ScrollView}
     *
     * @param wrapScrollView true if you want to wrap
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder setWrapScrollView(boolean wrapScrollView) {
        this.wrapScrollView = wrapScrollView;
        return this;
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, String label, View.OnClickListener onClickListener) {
        links.add(new Item(icon, label, onClickListener));
        return this;
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, String label, Intent intent) {
        return addLink(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, String label, Uri uri) {
        return addLink(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, String label, String url) {
        return addLink(icon, label, Uri.parse(url));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, int label, View.OnClickListener onClickListener) {
        return addLink(icon, context.getString(label), onClickListener);
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, int label, Intent intent) {
        return addLink(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, int label, Uri uri) {
        return addLink(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(Bitmap icon, int label, String url) {
        return addLink(icon, label, Uri.parse(url));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, int label, View.OnClickListener onClickListener) {
        return addLink(IconUtil.getBitmap(context, icon), context.getString(label), onClickListener);
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, int label, Intent intent) {
        return addLink(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, int label, Uri uri) {
        return addLink(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, int label, String url) {
        return addLink(icon, label, Uri.parse(url));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, String label, View.OnClickListener onClickListener) {
        return addLink(IconUtil.getBitmap(context, icon), label, onClickListener);
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, String label, Intent intent) {
        return addLink(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, String label, Uri uri) {
        return addLink(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(int icon, String label, String url) {
        return addLink(icon, label, Uri.parse(url));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, int label, View.OnClickListener onClickListener) {
        return addLink(IconUtil.getBitmap(icon), context.getString(label), onClickListener);
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, int label, Intent intent) {
        return addLink(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, int label, Uri uri) {
        return addLink(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, int label, String url) {
        return addLink(icon, label, Uri.parse(url));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, String label, View.OnClickListener onClickListener) {
        return addLink(IconUtil.getBitmap(icon), label, onClickListener);
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, String label, Intent intent) {
        return addLink(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, String label, Uri uri) {
        return addLink(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an link on the links section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLink(BitmapDrawable icon, String label, String url) {
        return addLink(icon, label, Uri.parse(url));
    }


    /**
     * Adds a GitHub profile link on the links section
     *
     * @param user a GitHub user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGitHubLink(int user) {
        return addGitHubLink(context.getString(user));
    }

    /**
     * Adds a GitHub profile link on the links section
     *
     * @param user a GitHub user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGitHubLink(String user) {
        return addLink(R.mipmap.github, R.string.github, util.uri(R.string.url_github, user));
    }

    /**
     * Adds a Bitbucket profile link on the links section
     *
     * @param user a Bitbucket user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addBitbucketLink(int user) {
        return addBitbucketLink(context.getString(user));
    }

    /**
     * Adds a Bitbucket profile link on the links section
     *
     * @param user a Bitbucket user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addBitbucketLink(String user) {
        return addLink(R.mipmap.whatsapp, R.string.whastapp, util.uri(R.string.url_bitbucket, user));
    }

    /**
     * Adds a Facebook profile link on the links section
     *
     * @param user a Facebook user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFacebookLink(int user) {
        return addFacebookLink(context.getString(user));
    }

    /**
     * Adds a Facebook profile link on the links section
     *
     * @param user a Facebook user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFacebookLink(String user) {
        return addLink(R.mipmap.facebook, R.string.facebook, util.openFacebook(user));
    }

    /**
     * Adds a Instagram profile link on the links section
     *
     * @param user a Instagram user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addInstagramLink(int user) {
        return addInstagramLink(context.getString(user));
    }

    /**
     * Adds a Instagram profile link on the links section
     *
     * @param user a Instagram user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addInstagramLink(String user) {
        return addLink(R.mipmap.instagram, R.string.instagram, util.openInstagram(user));
    }

    /**
     * Adds a Twitter profile link on the links section
     *
     * @param user a Twitter user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addTwitterLink(int user) {
        return addTwitterLink(context.getString(user));
    }

    /**
     * Adds a Twitter profile link on the links section
     *
     * @param user a Twitter user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addTwitterLink(String user) {
        return addLink(R.mipmap.twitter, R.string.twitter, util.openTwitter(user));
    }

    /**
     * Adds a Google link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGoogleLink(int url) {
        return addGoogleLink(context.getString(url));
    }

    /**
     * Adds a Google link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGoogleLink(String url) {
        return addLink(R.mipmap.google, R.string.google, url);
    }

    /**
     * Adds a Google Plus profile link on the links section
     *
     * @param user a Google Plus user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGooglePlusLink(int user) {
        return addGooglePlusLink(context.getString(user));
    }

    /**
     * Adds a Google Plus profile link on the links section
     *
     * @param user a Google Plus user name or id
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGooglePlusLink(String user) {
        return addLink(R.mipmap.google_plus, R.string.google_plus, util.openGooglePlus(user));
    }

    /**
     * Adds a Google Play Store profile link on the links section
     *
     * @param user a Google Play Store user name or id
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGooglePlayStoreLink(int user) {
        return addGooglePlayStoreLink(context.getString(user));
    }

    /**
     * Adds a Google Play Store profile link on the links section
     *
     * @param user a Google Play Store user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGooglePlayStoreLink(String user) {
        return addLink(R.mipmap.google_play_store, R.string.google_play_store, util.openGooglePlayDev(user));
    }

    /**
     * Adds a Google Games link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGoogleGamesLink(int url) {
        return addGoogleGamesLink(context.getString(url));
    }

    /**
     * Adds a Google Games link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addGoogleGamesLink(String url) {
        return addLink(R.mipmap.google_play_games, R.string.google_play_games, url);
    }

    /**
     * Adds a Youtube channel link on the links section
     *
     * @param user a Youtube channel name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addYoutubeChannelLink(int user) {
        return addYoutubeChannelLink(context.getString(user));
    }

    /**
     * Adds a Youtube channel link on the links section
     *
     * @param user a Youtube channel name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addYoutubeChannelLink(String user) {
        return addLink(R.mipmap.youtube, R.string.youtube, util.openYoutubeChannel(user));
    }

    /**
     * Adds a Youtube user link on the links section
     *
     * @param user a Youtube user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addYoutubeUserLink(int user) {
        return addYoutubeUserLink(context.getString(user));
    }

    /**
     * Adds a Youtube user link on the links section
     *
     * @param user a Youtube user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addYoutubeUserLink(String user) {
        return addLink(R.mipmap.youtube, R.string.youtube, util.openYoutubeUser(user));
    }

    /**
     * Adds a LinkedIn profile link on the links section
     *
     * @param user a LinkedIn user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLinkedInLink(int user) {
        return addLinkedInLink(context.getString(user));
    }

    /**
     * Adds a LinkedIn profile link on the links section
     *
     * @param user a LinkedIn user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLinkedInLink(String user) {
        return addLink(R.mipmap.linkedin, R.string.linkedin, util.openLinkedIn(user));
    }

    /**
     * Adds a Skype phone call on the links section
     *
     * @param phone a valid skype phone number
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addSkypeLink(int phone) {
        return addSkypeLink(context.getString(phone));
    }

    /**
     * Adds a Skype phone call on the links section
     *
     * @param phone a valid skype phone number
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addSkypeLink(String phone) {
        return addLink(R.mipmap.skype, R.string.skype, util.openSkype(phone));
    }

    /**
     * Adds a new contact button on the links section
     *
     * @param phone any phone number
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addWhatsappLink(int name, int phone) {
        return addWhatsappLink(context.getString(name), context.getString(phone));
    }

    /**
     * Adds a new contact button on the links section
     *
     * @param phone any phone number
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addWhatsappLink(String name, String phone) {
        return addLink(R.mipmap.contacts, R.string.contacts, util.openAddContact(name, phone));
    }

    /**
     * Adds a Android link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAndroidLink(int url) {
        return addAndroidLink(context.getString(url));
    }

    /**
     * Adds a Android link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAndroidLink(String url) {
        return addLink(R.mipmap.android, R.string.android, url);
    }

    /**
     * Adds a Dribbble link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addDribbbleLink(int url) {
        return addDribbbleLink(context.getString(url));
    }

    /**
     * Adds a Dribbble link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addDribbbleLink(String url) {
        return addLink(R.mipmap.dribbble, R.string.dribbble, url);
    }

    /**
     * Adds a website link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addWebsiteLink(int url) {
        return addWebsiteLink(context.getString(url));
    }

    /**
     * Adds a website link on the links section
     *
     * @param url any url
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addWebsiteLink(String url) {
        return addLink(R.mipmap.website, R.string.website, url);
    }

    /**
     * Adds a e-mail link on the links section
     *
     * @param email   addressee e-mail
     * @param subject the subject of the e-mail
     * @param message the contect of the e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(int email, int subject, int message) {
        return addEmailLink(context.getString(email), context.getString(subject), context.getString(message));
    }

    /**
     * Adds a e-mail link on the links section
     *
     * @param email   addressee e-mail
     * @param subject the subject of the e-mail
     * @param message the contect of the e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(int email, String subject, String message) {
        return addEmailLink(context.getString(email), subject, message);
    }

    /**
     * Adds a e-mail link on the links section
     *
     * @param email   addressee e-mail
     * @param subject the subject of the e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(int email, String subject) {
        return addEmailLink(context.getString(email), subject, null);
    }

    /**
     * Adds a e-mail link on the links section
     *
     * @param email   addressee e-mail
     * @param subject the subject of the e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(int email, int subject) {
        return addEmailLink(context.getString(email), context.getString(subject), null);
    }

    /**
     * Adds a e-mail link on the links section
     *
     * @param email   addressee e-mail
     * @param subject the subject of the e-mail
     * @param message the contect of the e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(String email, String subject, String message) {
        return addLink(R.mipmap.email, R.string.email, util.sendEmail(email, subject, message));
    }

    /**
     * Adds a e-mail link on the links section without ant subject or contents
     *
     * @param email addressee e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(int email) {
        return addEmailLink(context.getString(email));
    }

    /**
     * Adds a e-mail link on the links section without ant subject or contents
     *
     * @param email addressee e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addEmailLink(String email) {
        return addLink(R.mipmap.email, R.string.email, util.sendEmail(email, null, null));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, String label, View.OnClickListener onClickListener) {
        actions.add(new Item(icon, label, onClickListener));
        return this;
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, String label, Intent intent) {
        return addAction(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, String label, Uri uri) {
        return addAction(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url to browse
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, String label, String url) {
        return addAction(icon, label, Uri.parse(url));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, int label, View.OnClickListener onClickListener) {
        return addAction(icon, context.getString(label), onClickListener);
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, int label, Intent intent) {
        return addAction(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, int label, Uri uri) {
        return addAction(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url to browse
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(Bitmap icon, int label, String url) {
        return addAction(icon, label, Uri.parse(url));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, int label, View.OnClickListener onClickListener) {
        return addAction(IconUtil.getBitmap(context, icon), context.getString(label), onClickListener);
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, int label, Intent intent) {
        return addAction(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, int label, Uri uri) {
        return addAction(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url to browse
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, int label, String url) {
        return addAction(icon, label, Uri.parse(url));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, String label, View.OnClickListener onClickListener) {
        return addAction(IconUtil.getBitmap(context, icon), label, onClickListener);
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, String label, Intent intent) {
        return addAction(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, String label, Uri uri) {
        return addAction(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url to browse
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(int icon, String label, String url) {
        return addAction(icon, label, Uri.parse(url));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, int label, View.OnClickListener onClickListener) {
        return addAction(IconUtil.getBitmap(icon), context.getString(label), onClickListener);
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, int label, Intent intent) {
        return addAction(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, int label, Uri uri) {
        return addAction(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url to browse
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, int label, String url) {
        return addAction(icon, label, Uri.parse(url));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon            the action icon
     * @param label           the action title
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, String label, View.OnClickListener onClickListener) {
        return addAction(IconUtil.getBitmap(icon), label, onClickListener);
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon   the action icon
     * @param label  the action title
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, String label, Intent intent) {
        return addAction(icon, label, util.clickIntent(intent));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param uri   the action uri
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, String label, Uri uri) {
        return addAction(icon, label, util.clickUri(uri));
    }

    /**
     * Adds an action button on the actions section.
     *
     * @param icon  the action icon
     * @param label the action title
     * @param url   any url to browse
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addAction(BitmapDrawable icon, String label, String url) {
        return addAction(icon, label, Uri.parse(url));
    }

    /**
     * Adds an action button to rate the app on Google Play Store
     *
     * @param appId the application id
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFiveStarsAction(int appId) {
        return addFiveStarsAction(context.getString(appId));
    }

    /**
     * Adds an action button to rate the app on Google Play Store
     *
     * @param appId the application id
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFiveStarsAction(String appId) {
        return addAction(R.mipmap.star, R.string.rate_five_stars, util.openPlayStoreAppPage(appId));
    }

    /**
     * Adds an action button to rate the app on Google Play Store.
     * This method use the default application id from the context
     *
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFiveStarsAction() {
        return addFiveStarsAction(getApplicationID());
    }

    /**
     * Adds an action button to update the app using Google Play Store
     *
     * @param appId the application id
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addUpdateAction(int appId) {
        return addUpdateAction(context.getString(appId));
    }


    /**
     * Adds an action button to update the app using Google Play Store
     *
     * @param appId the application id
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addUpdateAction(String appId) {
        return addAction(R.mipmap.update, R.string.update_app, util.openPlayStoreAppPage(appId));
    }

    /**
     * Adds an action button to update the app using Google Play Store
     * This method use the default application id from the context
     *
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addUpdateAction() {
        return addUpdateAction(getApplicationID());
    }

    /**
     * Adds an action button to browse more apps from the developer
     *
     * @param userName the developer user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addMoreFromMeAction(int userName) {
        return addMoreFromMeAction(context.getString(userName));
    }

    /**
     * Adds an action button to browse more apps from the developer
     *
     * @param userName the developer user name
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addMoreFromMeAction(String userName) {
        return addAction(R.mipmap.google_play_store, R.string.more_apps, util.openPlayStoreAppsList(userName));
    }

    /**
     * Adds a share action button
     *
     * @param subject the subject
     * @param message the content message
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addShareAction(int subject, int message) {
        return addShareAction(context.getString(subject), context.getString(message));
    }

    /**
     * Adds a share action button
     *
     * @param subject the subject
     * @param message the content message
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addShareAction(String subject, String message) {
        return addAction(share, R.string.share_app, util.shareThisApp(subject, message));
    }

    /**
     * Adds a share action button. No content message will be placed
     *
     * @param subject the subject
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addShareAction(String subject) {
        return addShareAction(subject, context.getString(R.string.uri_play_store_app_website, context.getPackageName()));
    }

    /**
     * Adds a share action button. No content message will be placed
     *
     * @param subject the subject
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addShareAction(int subject) {
        return addShareAction(context.getString(subject));
    }


    /**
     * Adds a feedback action button
     *
     * @param email   the developer e-mail
     * @param subject the subject
     * @param content the e-mail content
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(int email, int subject, int content) {
        return addFeedbackAction(context.getString(email), context.getString(subject), context.getString(content));
    }

    /**
     * Adds a feedback action button
     *
     * @param email   the developer e-mail
     * @param subject the subject
     * @param content the e-mail content
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(int email, String subject, String content) {
        return addAction(R.mipmap.feedback, R.string.feedback_app, util.sendEmail(context.getString(email), subject, content));
    }

    /**
     * Adds a feedback action button
     *
     * @param email   the developer e-mail
     * @param subject the subject
     * @param content the e-mail content
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(String email, String subject, String content) {
        return addAction(R.mipmap.feedback, R.string.feedback_app, util.sendEmail(email, subject, content));
    }

    /**
     * Adds a feedback action button. No content message will be placed
     *
     * @param email   the developer e-mail
     * @param subject the subject
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(int email, int subject) {
        return addFeedbackAction(context.getString(email), context.getString(subject));
    }

    /**
     * Adds a feedback action button. No content message will be placed
     *
     * @param email   the developer e-mail
     * @param subject the subject
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(String email, String subject) {
        return addFeedbackAction(email, subject, null);
    }

    /**
     * Adds a feedback action button. No content message will be placed
     *
     * @param email   the developer e-mail
     * @param subject the subject
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(int email, String subject) {
        return addFeedbackAction(context.getString(email), subject, null);
    }

    /**
     * Adds a feedback action button. No subject or content message will be placed
     *
     * @param email the developer e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(int email) {
        return addFeedbackAction(context.getString(email));
    }

    /**
     * Adds a feedback action button. No subject or content message will be placed
     *
     * @param email the developer e-mail
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addFeedbackAction(String email) {
        return addFeedbackAction(email, null);
    }

    /**
     * Adds an introduce action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addIntroduceAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.intrduce, R.string.introduce_app, onClickListener);
    }

    /**
     * Adds an introduce action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addIntroduceAction(Intent intent) {
        return addIntroduceAction(util.clickIntent(intent));
    }

    /**
     * Adds an help action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addHelpAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.help, R.string.help, onClickListener);
    }

    /**
     * Adds an help action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addHelpAction(Intent intent) {
        return addHelpAction(util.clickIntent(intent));
    }

    /**
     * Adds an license action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLicenseAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.license, R.string.license, onClickListener);
    }

    /**
     * Adds an license action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addLicenseAction(Intent intent) {
        return addLicenseAction(util.clickIntent(intent));
    }

    /**
     * Adds an changelog action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addChangeLogAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.changelog, R.string.changelog, onClickListener);
    }

    /**
     * Adds an changelog action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addChangeLogAction(Intent intent) {
        return addChangeLogAction(util.clickIntent(intent));
    }

    /**
     * Adds an remove ads action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addRemoveAdsAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.ads, R.string.remove_ads, onClickListener);
    }


    /**
     * Adds an remove ads action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addRemoveAdsAction(Intent intent) {
        return addRemoveAdsAction(util.clickIntent(intent));
    }

    /**
     * Adds an donate action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addDonateAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.donate, R.string.donate, onClickListener);
    }

    /**
     * Adds an donate action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addDonateAction(Intent intent) {
        return addDonateAction(util.clickIntent(intent));
    }

    /**
     * Wraps the content in a {@link android.support.v7.widget.CardView}
     *
     * @param showAsCard true if show in a CardView
     * @return the same {@link AboutBuilder} instance
     */

    public AboutBuilder setShowAsCard(boolean showAsCard) {
        this.showAsCard = showAsCard;
        return this;
    }

    /**
     * Adds a privacy policy action button
     *
     * @param url the url to privacy policy web page
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addPrivacyPolicyAction(String url) {
        return addAction(R.mipmap.privacy, R.string.privacy, util.intent(url));
    }

    /**
     * Adds a privacy policy action button
     *
     * @param onClickListener the click callback
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addPrivacyPolicyAction(View.OnClickListener onClickListener) {
        return addAction(R.mipmap.privacy, R.string.privacy, onClickListener);
    }

    /**
     * Adds a privacy policy action button
     *
     * @param intent the action intent
     * @return the same {@link AboutBuilder} instance
     */
    public AboutBuilder addPrivacyPolicyAction(Intent intent) {
        return addAction(R.mipmap.privacy, R.string.privacy, util.clickIntent(intent));
    }

    public boolean isShowAsCard() {
        return showAsCard;
    }

    public String getName() {
        return name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getBrief() {
        return brief;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public Bitmap getCover() {
        return cover;
    }

    public Bitmap getAppIcon() {
        return appIcon;
    }

    public int getNameColor() {
        return nameColor;
    }

    public int getSubTitleColor() {
        return subTitleColor;
    }

    public int getBriefColor() {
        return briefColor;
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public int getIconColor() {
        return iconColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getLinksColumnsCount() {
        return linksColumnsCount;
    }

    public int getActionsColumnsCount() {
        return actionsColumnsCount;
    }

    public boolean isShowDivider() {
        return showDivider;
    }

    public int getDividerHeight() {
        return dividerHeight;
    }

    public int getDividerDashWidth() {
        return dividerDashWidth;
    }

    public int getDividerDashGap() {
        return dividerDashGap;
    }

    public boolean isLinksAnimated() {
        return linksAnimated;
    }

    public boolean isWrapScrollView() {
        return wrapScrollView;
    }

    public LinkedList<Item> getLinks() {
        return links;
    }

    public LinkedList<Item> getActions() {
        return actions;
    }

    public AboutView build() {
        AboutView aboutView = new AboutView(context);
        aboutView.build(this);
        return aboutView;
    }

}
