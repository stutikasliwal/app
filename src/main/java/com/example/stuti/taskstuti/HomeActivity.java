package com.example.stuti.taskstuti;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.stuti.taskstuti.adapter.ViewPagerAdapter;
import com.example.stuti.taskstuti.fragments.FormFragment;
import com.example.stuti.taskstuti.fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    //XML Variables
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    //Class variables
    private Context context;
    private CircleImageView circleImageViewUser;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        context=this;

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_title.setText("Home");


        //setup viewpager for fragments
        setupViewPager(viewPager);

        // initializing navigation menu
        setUpNavigationView();
    }

    /**
     * Method used to set view pager for fragment.
     */
    private void setupViewPager(ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FormFragment().newInstance("HOME"), getResources().getString(R.string.fragment_form));
        adapter.addFragment(new ListFragment().newInstance("HOME"), getResources().getString(R.string.fragment_list));
        viewPager.setAdapter(adapter);
    }

    /**
     * Method used to set navigation view.
     */
    private void setUpNavigationView() {

        View headerLayout = navigationView.getHeaderView(0);
        circleImageViewUser = (CircleImageView) headerLayout.findViewById(R.id.circleImageViewUser);
        textViewName = (TextView) headerLayout.findViewById(R.id.textViewUserName);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check to see which item was being clicked and perform appropriate action

                loadClass(menuItem.getItemId());
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                //loadHomeFragment();
                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //After instantiating your ActionBarDrawerToggle
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_drawer, HomeActivity.this.getTheme());
        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    /**
     * Method used to open items in Navigation View.
     */
    private void loadClass(int menuId) {
        Intent intent = null;
        switch (menuId) {
            case R.id.menuAbout:
                intent = new Intent(context, AboutUsActivity.class);
                break;
            case R.id.menuLogout:
                logoutAlertDialog();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
        drawer.closeDrawers();
    }


    /**
     * Method used to logout from application.
     */
    private void logoutAlertDialog() {
        final Dialog dialog = new Dialog(context, R.style.Dialog_No_Border);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Grab the window of the dialog, and change the width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();

        lp.copyFrom(window.getAttributes());

        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        dialog.setContentView(R.layout.custom_alert_dialog);
        Button buttonPositive = (Button) dialog.findViewById(R.id.buttonPositive);
        Button buttonNegative = (Button) dialog.findViewById(R.id.buttonNegative);
        TextView textViewTitle = (TextView) dialog.findViewById(R.id.textViewTitle);
        TextView textViewMessage = (TextView) dialog.findViewById(R.id.textViewMessage);

        textViewTitle.setTextColor(context.getResources().getColor(R.color.color_text));
        textViewMessage.setText(Html.fromHtml("<small>" + "Are you sure you want to logout?" + "</small>"));
        textViewMessage.setTextColor(context.getResources().getColor(R.color.color_text));
        buttonPositive.setText("Yes");
        buttonNegative.setText("No");
        buttonNegative.setVisibility(View.VISIBLE);

        // if button is clicked, perform action
        buttonPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // Calling logout service
              finish();
                // Closing logout dialog
                dialog.cancel();

            }
        });

        buttonNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Apply animation on alert dialog
       // dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //style id
        dialog.show();
    }
}
