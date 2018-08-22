package com.vimalcvs.aboutpage.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vansuita.library.Icon;
import com.vimalcvs.aboutpage.R;
import com.vimalcvs.aboutpage.builder.AboutBuilder;
import com.vimalcvs.aboutpage.builder.Item;
import com.vimalcvs.aboutpage.util.RippleUtil;
import com.vimalcvs.aboutpage.util.VisibleUtil;

/**
 * Created by VimalCVS on 22/08/18.
 */

public final class AboutView extends FrameLayout {

    private LayoutInflater layoutInflater;

    private CardView cvHolder;
    private CircleImageView ivPhoto;
    private ImageView ivCover;
    private TextView tvName;
    private TextView tvSubTitle;
    private TextView tvBrief;

    private TextView tvAppName;
    private TextView tvAppTitle;
    private ImageView ivAppIcon;

    private View appHolder;
    private AutoFitGridLayout vLinks;
    private AutoFitGridLayout vActions;

    private Boolean isDarker;
    private int iconColor = 0;
    private int animationDelay = 200;


    public AboutView(Context context) {
        this(context, null);
    }

    public AboutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AboutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(AboutBuilder bundle) {
        layoutInflater = LayoutInflater.from(getContext());

        ViewGroup holder = this;
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (bundle.isWrapScrollView()) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ScrollView scrollView = new ScrollView(getContext());
            scrollView.setLayoutParams(lp);
            addView(scrollView);
            holder = new FrameLayout(getContext());
            holder.setLayoutParams(lp);
            scrollView.addView(holder);
        }

        setLayoutParams(lp);

        layoutInflater.inflate(R.layout.xab_about_layout_card, holder);
    }

    private void bind() {
        cvHolder = (CardView) findViewById(R.id.card_holder);
        ivPhoto = (CircleImageView) findViewById(R.id.photo);
        ivCover = (ImageView) findViewById(R.id.cover);
        tvName = (TextView) findViewById(R.id.name);
        tvSubTitle = (TextView) findViewById(R.id.sub_title);
        tvBrief = (TextView) findViewById(R.id.brief);
        tvAppName = (TextView) findViewById(R.id.app_name);
        tvAppTitle = (TextView) findViewById(R.id.app_title);
        ivAppIcon = (ImageView) findViewById(R.id.app_icon);

        vLinks = (AutoFitGridLayout) findViewById(R.id.links);
        vActions = (AutoFitGridLayout) findViewById(R.id.actions);
        appHolder = findViewById(R.id.app_holder);
    }

    public void build(AboutBuilder bundle) {
        init(bundle);
        bind();

        setupCard(bundle);

        tvName.setText(bundle.getName());
        VisibleUtil.handle(tvName, bundle.getName());

        tvSubTitle.setText(bundle.getSubTitle());
        VisibleUtil.handle(tvSubTitle, bundle.getSubTitle());

        tvBrief.setText(bundle.getBrief());
        VisibleUtil.handle(tvBrief, bundle.getBrief());

        tvAppName.setText(bundle.getAppName());
        tvAppTitle.setText(bundle.getAppTitle());

        setBitmap(ivCover, bundle.getCover());
        setBitmap(ivPhoto, bundle.getPhoto());
        setBitmap(ivAppIcon, bundle.getAppIcon());

        setTextColor(tvName, bundle.getNameColor());
        setTextColor(tvSubTitle, bundle.getSubTitleColor());
        setTextColor(tvBrief, bundle.getBriefColor());

        this.iconColor = bundle.getIconColor();

        if (bundle.getBackgroundColor() != 0)
            cvHolder.setCardBackgroundColor(bundle.getBackgroundColor());

        VisibleUtil.handle(appHolder, bundle.getAppName());

        if (appHolder.getVisibility() == VISIBLE)
            setDivider(bundle, appHolder);

        setDivider(bundle, vLinks);

        if (bundle.getLinksColumnsCount() != 0)
            vLinks.setColumnCount(bundle.getLinksColumnsCount());

        if (bundle.getActionsColumnsCount() != 0)
            vActions.setColumnCount(bundle.getActionsColumnsCount());

        vLinks.setVisibility(bundle.getLinks().isEmpty() ? GONE : VISIBLE);
        vActions.setVisibility(bundle.getActions().isEmpty() ? GONE : VISIBLE);

        loadLinks(bundle);
        loadActions(bundle);
    }


    private void setTextColor(TextView tv, int color) {
        if (color != 0)
            tv.setTextColor(color);
    }

    @SuppressWarnings("ResourceAsColor")
    private void setDivider(AboutBuilder bundle, View holder) {
        if (bundle.isShowDivider()) {

            int color = bundle.getDividerColor();

            if (color == 0)
                color = isDarker() ? Color.GRAY : getNameColor();

            GradientDrawable drawable = ((GradientDrawable) ((LayerDrawable) holder.getBackground()).findDrawableByLayerId(R.id.stroke));

            if (drawable != null) {
                drawable.setStroke(bundle.getDividerHeight(), color, bundle.getDividerDashWidth(), bundle.getDividerDashGap());
            }
        } else {
            RippleUtil.background(holder, (Drawable) null);
        }
    }

    private int getNameColor() {
        return tvName.getCurrentTextColor();
    }

    private boolean isDarker() {
        if (isDarker == null)
            isDarker = RippleUtil.isDark(getCardColor());

        return isDarker;
    }

    private int getCardColor() {
        return cvHolder.getCardBackgroundColor().getDefaultColor();
    }

    private int getIconColor() {
        if (iconColor == 0)
            iconColor = isDarker() ? Color.WHITE : getNameColor();

        return iconColor;
    }

    private void setBitmap(ImageView iv, Bitmap bitmap) {
        if (bitmap == null) {
            iv.setVisibility(GONE);
        } else {
            iv.setImageBitmap(bitmap);
        }
    }

    private void loadLinks(AboutBuilder bundle) {
        for (Item item : bundle.getLinks()) {
            View v = addItem(vLinks, R.layout.xab_each_link, item);

            if (bundle.isLinksAnimated())
                animate(v);
        }
    }

    private void animate(final View v) {
        v.setVisibility(INVISIBLE);

        animationDelay += 20;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setVisibility(VISIBLE);
                v.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.expand_in));
            }
        }, animationDelay);
    }

    private void loadActions(AboutBuilder bundle) {
        for (Item item : bundle.getActions()) {
            addItem(vActions, R.layout.xab_each_action, item);
        }
    }

    private View addItem(ViewGroup holder, int layout, Item item) {
        View view = layoutInflater.inflate(layout, null);
        view.setId(item.getId());

        TextView tvLabel = (TextView) view.findViewById(R.id.label);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.icon);

        Icon.on(ivIcon).bitmap(item.getIcon()).color(getIconColor()).put();

        tvLabel.setText(item.getLabel());
        view.setOnClickListener(item.getOnClick());

        RippleUtil.backgroundRipple(view, getCardColor());

        holder.addView(view);
        return view;
    }

    private void setupCard(AboutBuilder bundle) {
        if (!bundle.isShowAsCard()) {
            cvHolder.setCardElevation(0);
            cvHolder.setRadius(0);
            cvHolder.setUseCompatPadding(false);
            cvHolder.setMaxCardElevation(0);
            cvHolder.setPreventCornerOverlap(false);
        }
    }

    public CardView getHolder() {
        return cvHolder;
    }

    public View findItem(int id) {
        return cvHolder.findViewById(id);
    }

    public View findItem(Item item) {
        return findItem(item.getId());
    }

}
