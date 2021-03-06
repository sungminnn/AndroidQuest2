package com.mulcam.c901.ari.androidquest;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Jin on 2017-06-14.
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private Button todo_btn;
    private Button welldo_btn;
    private ImageView write_btn;
    private ImageView login_btn;
    private int boardNo;


    private TodoBoardFragment todoBoard;
    private WelldoBoardFragment welldoBoard;
    private ViewBoardFragement viewBoard;
    private WriteBoardFragment writeboard;
    private LoginFragment loginPage;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        todo_btn = (Button) findViewById(R.id.main_todo_btn);
        welldo_btn = (Button) findViewById(R.id.main_welldo_btn);
        write_btn = (ImageView) findViewById(R.id.main_write_btn);
        login_btn = (ImageView) findViewById(R.id.main_login_btn);



        todoBoard = new TodoBoardFragment();
        welldoBoard = new WelldoBoardFragment();
        viewBoard = new ViewBoardFragement();
        writeboard = new WriteBoardFragment();
        loginPage = new LoginFragment();



        todo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "해주세요 게시판에 들어오셨습니다", Toast.LENGTH_SHORT).show();
                Log.i("main","해주세요게시판 in");
                todo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_success)
                );
                welldo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_thema)
                );

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, todoBoard).commit();
            }
        });

        welldo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, " 잘해요 게시판에 들어옴", Toast.LENGTH_SHORT).show();
                Log.i("main", "잘해요 게시판 in");
                todo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_thema)
                );
                welldo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_success)
                );
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, welldoBoard).commit();
            }
        });

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, writeboard).commit();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, loginPage).commit();
            }
        });


    }

        @Override
        public void onBackPressed() {
            Log.d("main_nav","onBackPressed");
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            Log.d("main_nav","onCreateOptionsMenu");
                    // Inflate the menu; this adds items to the action bar if it is present.
                    getMenuInflater().inflate(R.menu.main, menu);

                    return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            Log.d("main_nav","options in");
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();


            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Log.d("main_nav","navigation in");
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_todolist) {
                Log.d("main_nav","해주세요");
                welldo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_thema)
                );
                todo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_success)
                );
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, todoBoard).commit();

            } else if (id == R.id.nav_welldolist) {
                Log.d("main_nav","잘해요");
                todo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_thema)
                );
                welldo_btn.setBackgroundColor(
                        getResources().getColor(R.color.bg_success)
                );

                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.main_view, welldoBoard).commit();

            } else if (id == R.id.nav_mylist_write) {
                Log.d("main_nav","내가쓴글");

            } else if (id == R.id.nav_mylist_apply) {
                Log.d("main_nav","신청한글");

            } else if (id == R.id.nav_starpoint) {
                Log.d("main_nav","평가하기");

            } else if (id == R.id.nav_bookmark) {
                Log.d("main_nav","즐겨찾기");

            }else if (id == R.id.nav_profile) {
                Log.d("main_nav","정보수정");

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        public void setBoardNoforviewBoard(String boardNo) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.main_view, viewBoard).commit();
            viewBoard.setBoard(boardNo);
//            Log.d("mainViewBoard","boardNo"+boardNo);
        }
}
