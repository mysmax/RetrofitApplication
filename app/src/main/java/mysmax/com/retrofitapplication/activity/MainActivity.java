package mysmax.com.retrofitapplication.activity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import mysmax.com.retrofitapplication.R;
import mysmax.com.retrofitapplication.model.Child;
import mysmax.com.retrofitapplication.model.ExpandInfoList;
import mysmax.com.retrofitapplication.model.Group;
import mysmax.com.retrofitapplication.model.UserInfo;
import mysmax.com.retrofitapplication.receiver.MyBroadcastReceiver;
import mysmax.com.retrofitapplication.webservice.retrofit.ApiClient;
import mysmax.com.retrofitapplication.webservice.retrofit.ApiInterface;
import mysmax.com.retrofitapplication.webservice.retrofit.EventProperties.Event;
import mysmax.com.retrofitapplication.webservice.retrofit.FeedProperties;
import mysmax.com.retrofitapplication.webservice.retrofit.RetrofitSingleton;
import mysmax.com.retrofitapplication.webservice.retrofit.feedProperties.Feeds;
import mysmax.com.retrofitapplication.workers.MyJetPackWorker;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    /*
- 60% - Coding to develop features of a Native Android App as per requirements of the customer.
- 20% - Unit Testing your code to meet acceptance criteria.
- 10% - Code Review with Team Leader & subsequent corrections.
- 5% - Code Optimization to improve performance.
- 5% - Export UI assets from a developer-designer collaboration tool like zeplin.io or Avocode.
Skills :
- Proficient with Android Development Studio.
- Clear Understanding of Clean Architecture, Dependency Injection and RESTful APIs.
- Experience in using Dagger2 and Retrofit is a plus.
- Experience in using github & git tools.
 - Stickler for code hygiene.

Understanding of Android design patterns, memory management, file I/O, concurrency, and multithreading
Core Java, Android UX, DB & REST services, TDD using Junit\Mockito & Espresso, basic design patterns
Unit test case development
Good logical and analytical thinking
Good knowledge in Source Control Management (Git/SVN)
Problem-solving skills
Knowledge into BLE
Experience in Android framework such as Media Framework, Connectivity, System UI, View Systems,
Software Update, Diagnostics.
Good knowledge of binder, shared memory and IPC mechanisms in Android.
Good understanding of overall Android Architecture, Android Eco system & debugging tools &
google certification like CTS, VTS, GTS etc.
 Proficient in integration of third party libraries OkHttp, Retrofit, ButterKnife, Image caching libraries
- Working knowledge of RxJava, RxAndroid
- Expert in debugging, troubleshooting, memory optimization, performance and scalability of mobile app.
- Perform performance optimization, Apk size and battery optimization
- Perform code-review and help the team in timely releases

Strong knowledge of Android SDK, different versions of Android
Added advantage if one has exposure to Android MDM APIs
Familiarity with RESTful APIs to connect Android applications to back-end services
Strong knowledge of Android UI design principles, patterns, and best practices
Knowledge of the open-source Android ecosystem and the libraries available for common tasks
Ability to understand business requirements and translate them into technical requirements
Familiarity with cloud message APIs and push notifications
Proficient understanding of code versioning tools, such as Git
Familiarity with continuous integration

Expert at Location,fit, Motion/Sensor APIs, store and manage large app data natively.
expert at writing readable code, expert at writing background services efficiently,
build solid instrumentation using Firebase, pixel-perfect conversation of PSD-APP

The Project Manager performs a wide range of duties including some or all of the following:
Ensures open communication on the project team. Addresses delicate situations and handles conflicts in such a way as to maximize opportunity and minimize exposure to risk.
Demonstrates superior inter-personal skills, conflict resolution, and negotiating skills.
Ensures open communication across project teams.
Handles broad-based, often complex, communication for internal and/or external audiences. Creates a forum and format for ongoing open communication within functional area or among departments.
Defuses emotional charged situations and uses them to constructively build greater shared commitment to end goals.
Promotes knowledge of team's work to gain support for ongoing and future initiatives.
Decision Making Understands how and when to make a choice; how and when to escalate issues to higher levels. Analyzes the risks and future impact of decisions.
Makes decisions and takes timely independent action in pursuit of priorities.
Regularly makes decisions and takes independent action on matters directly affecting strategic goals. Guides staff and project managers in learning and applying useful decision making approaches.
Partners with sponsors in understanding and creating opportunities and in making timely choices.
Participates in strategy development.

Experience with offline storage, threading, and performance tuning
Guava is a set of core libraries that includes new collection types (such as multimap and multiset),
immutable collections, a graph library, functional types, an in-memory cache, and APIs/utilities for concurrency,
I/O, hashing, primitives, reflection, string processing, and much more!
Guava comes in two flavors.

The JRE flavor requires JDK 1.8 or higher.
If you need support for JDK 1.7 or Android, use the Android flavor

• Translate designs and wireframes into high quality code.
• Full Android Stack: Activities, Fragments, views, Services, Broadcast
receivers, Content providers, Sqlite
• Design, build, and maintain high performance, reusable, and reliable Java
code.
• Ensure the best possible performance, quality, and responsiveness of the
application.
• Identify and correct bottlenecks and fix bugs using tools such as ADB.
• Help maintain code quality, organisation, and automatisation.
• Strong knowledge of Android SDK, different versions of Android, and how to
deal with different screen sizes.
• Familiarity with RESTful APIs to connect Android applications to back-end
services.
• Strong knowledge of Android UI design principles, patterns, and best
practices.
• Experience with offline storage, threading, and performance tuning.
• A knack for benchmarking and optimisation.
• Understanding of Googles Android design principles and interface guidelines.
• Familiarity with continuous integration.
• Proficient understanding of code versioning tools, such as Git.
• Familiarity with cloud message APIs and push notifications.
• Ability to understand business requirements and translate them into
technical requirements.
• Knowledge of the open-source Android ecosystem and the libraries available
for common tasks.
• Ability to design applications around natural user interfaces, such as touch.
• Experience on Firebase and tracking tools.
• Experience on Android framework.
• Extensive experience working in TDD(Test Driven Development) / BDD
(Behaviour Driven Development) and

• CI/CD (Continuous Integration / Development) environment.
Skills & Responsibilities:
• Design and Build advanced applications for the Android Platform using Java.
• Collobrate with cross-functional teams to define, design, and ship new
features.
• Work with outside data sources and API's.
• Should be able to develop the application with effective coding with minimal
errors/bugs.
• Unit-test cide for edge cases, usablity, and general reliablity.
• Work on bug fixing and improving application performance.
• Continuously discover, evaluate, and implement new technologies to
maximize development effiency.
• Ability to be a self-starter, prioritize your work, stay organized, self
motivating and work and motivate well in team
• environment is critical.
• Should be able to manage the whole project solely or in team.
• Application version control management on SCRUM

 Strong knowledge of Android SDK, different versions of Android, and how to deal with different screen sizes
â¢ Familiarity with RESTful APIs to connect Android applications to back-end services
â¢ Strong knowledge of Android UI design principles, patterns, and best practices
â¢ Experience with Unit Testing, offline storage, threading, and performance tuning
â¢ Proficient understanding of code versioning tools, such as Git
â¢ Familiarity with continuous integration
â¢ Ability to understand business requirements and translate them into technical requirements
· OOP basics, Programming Skills - Java language, Web Services.

· Experience on Android studio & Android SDK
· Design - Basics, UML notations, HLD, LLD, Patterns, etc - interpretation, conceptualization
HLD generally includes product flows, how modules interact with each other.
For an instance it will include the information about the flow of how would a user register to a particular service.
How several modules like information verification, user registration etc. would work together.
LLD is more about the classes which will be written for a particular feature. What methods would those classes contain.

· Architecture - Basics, Patterns - interpretation, conceptualization
· Knowledge of Android Architecture
-Javascript integration with native android application.
-Has worked with connected devices or internet of things using technologies like WiFi, BT, NFC etc.
-preferably working experience in agile/SAFe development methodologies

Strong Core Java (multithreading, exception handling, I/O framework, collections etc.)
Android Framework developer Camera App/Frame work (Camera feature knowledge like launch, preview, capture, recording, etc.),
 Telephony/SMS/Surface manager/activity/ multimedia
 Hands-on GIT, Android Studio, Debugging tools gdb, adb, JIRA, systrace, etc
 -Experience in the integration of HTML into applications
 //Harm
  Experience using and championing features from the Android platform such as Fragments, Action Bars, Volley, Transitions, Translucent Styling and more
â¢ Experience with mobile architecture and separate areas of responsibility for UI, data storage and network access
â¢ At least one published application that has gone through multiple releases to the play store
â¢ A passion for clean and testable code
â¢ Familiarity with OOP, design patterns with strong CS fundamentals
â¢ Should be able to maintain code repository
â¢ Excellent interpersonal skills, problem solving and analytical abilities
â¢ Demonstrate strong written and oral communication skills.
â¢ Has worked with Large Agile teams using Scrum/Kanban development methodologies
â¢ Self-motivated, detail-oriented and strong analytical / problem solving skills
â¢ Strong written and verbal communication skills

Core Java, Android – UX, DB & REST services, TDD using Junit\Mockito & Espresso, basic design patterns
Unit test case development
Good logical and analytical thinking
Good knowledge in Source Control Management (Git/SVN)
Problem solving skills
Knowledge into BLE
    */

    /*
    Model’s responsibilities include using APIs, caching data, managing databases.
    Document services getFilesDir, getAppDataDir, getCacheDir
    java.io file input/output APIs we can start reading and writing files to the Android filesystem
    */

    /*
       Loaders have been deprecated as of Android P (API 28). The recommended option for dealing with loading data while handling
       the Activity and Fragment lifecycles is to use a combination of ViewModels and LiveData
       Loaders run on separate threads to prevent janky or unresponsive UI.
       Loaders persist and cache results across configuration changes to prevent duplicate queries.
     */

    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //delayTripAccept();
        //setContentView(R.layout.activity_with_fragments);
        //setContentView(R.layout.activity_drag1);
        //findViewById(R.id.separator).setOnTouchListener(this);
        //findViewById(R.id.info).setOnTouchListener(this);

        // Explicit Intent
        /*
        Intent intent = new Intent(getBaseContext(),UserDetailsActivity.class);
        startActivityForResult(intent,123);
        */
        //Intent intent = new Intent(getBaseContext(),TwoSidesActivity.class);
        //startActivity(intent);

        // Implicit Intent
        /*
        // To Send Mail
        String[] to = {"rchthn.g@gmail.com"};
        String[] cc = {"r.chethan@yahoo.co.in"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        intent.putExtra(Intent.EXTRA_CC,cc);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Check this mail");
        intent.putExtra(Intent.EXTRA_TEXT,"Main Details Here");
        startActivity(Intent.createChooser(intent,"Send Email"));
        */
        // Locales, input methods(Keyboard), Screen size, Screen Orientation
        //Configuration config = getResources().getConfiguration();

        /*
        // Screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        Log.e("Screen ", " Width : " + width + " Height : "  + height);
        */

        /*
        // Add Portion with in the screen
        //Fragment parent = new FragOne();
        Fragment parent = new Trips();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trn = fm.beginTransaction();
        trn.replace(R.id.container, parent).commitAllowingStateLoss();
        */

         /*
        Fragment parentMap = new FragOne();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trn = fm.beginTransaction();
        trn.replace(R.id.container, parentMap).commitAllowingStateLoss();

        Fragment parentInfo = new FragTwo();
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction trn1 = fm1.beginTransaction();
        trn1.replace(R.id.info, parentInfo).commitAllowingStateLoss();
        */

        // List View
        /*
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new UserInfoLvAdapter(createInfoList()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Clicked "+ position,Toast.LENGTH_SHORT).show();
            }
        });
        */

        // Recycler View
        /*
        RecyclerView rView = (RecyclerView) findViewById(R.id.rclv);
        // LinearLayoutManager
        // StaggeredGridLayoutManager
        // GridLayoutManager
        // WearableLinearLayoutManager
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext());
        rView.setLayoutManager(layout);
        //rView.setAdapter(new RclvAdapter(getBaseContext(),createInfoList()));
        rView.setAdapter(new UserInfoAdapter(createInfoList()));
        */

        /*
        // Expandable List View
        ExpandableListView eplv = (ExpandableListView) findViewById(R.id.eplv);
        eplv.setAdapter(new ExpandAdapter(createExpandInfo()));
        eplv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                showToast("Group Clicked " + groupPosition);
                return false;
            }
        });
        eplv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                showToast("Child Clicked  group " + groupPosition + " child " + childPosition);
                return false;
            }
        });

        eplv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                showToast("Expand Clicked " + groupPosition);
            }
        });

        eplv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                showToast("Collapse Clicked " + groupPosition);
            }
        });
        */

        // Service
        //startService(new Intent(getApplicationContext(), PreferenceChangeService.class));
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("PostDelay","Entered to call Service");
                startService(new Intent(getApplicationContext(), PreferenceChangeService.class));
            }
        },60*1000);
        */

        /*
        final Intent intent = new Intent(getBaseContext(), Gps.class);
        //intent.putExtra("blick","blick");
        intent.setAction("create");
        startService(intent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopService(intent);
            }
        },60000);
        */

        // Web Service Hit
        //callRetroApi();
        //callFeedApi();
        //callEventsApi();

        // Data, Category and Action
        // Action -   ACTION_SEND, ACTION_DIAL, ACTION_DELETE, ACTION_VIEW
        // Category - DEFAULT, LAUNCHER
        // Data - Uri.parse("http://www.domain.com/index"), mimeType="text/plain"

        // Send Data
        //Bundle bundle = new Bundle();
        //bundle.putString("key","value");
        //bundle.putParcelable("MyParcel",new MyParcel(1,"someVal"));

        //Intent myBundleIntent = new Intent();
        //myBundleIntent.putExtras(bundle);

        // Catch Data
        //Bundle holdBundle = getIntent().getExtras();
        //MyParcel parcelData = holdBundle.getParcelable("MyParcel");

        // Check Permission
        //checkPermission();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this))
        {
            //EventBus.getDefault().register(this);
        }
        // Uninstall App
        /*
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:mysmax.com.retrofitapplication"));
        startActivity(intent);
        */
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
        {
           // EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Subscribe
    public void onEvent()
    {

    }

    public void delayTripAccept()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"DelayStopTriggered",Toast.LENGTH_LONG).show();
                Log.e("BackGroundCheck","DelayStopTriggered At " + Calendar.getInstance().getTime());
            }
        },2*60*1000);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123)
        {
            if (resultCode == RESULT_OK)
            {
                Toast.makeText(getBaseContext(),"I AM BACK",Toast.LENGTH_LONG).show();
                /*
                try {
                */
                    // Web page open
                    /*
                    Intent impIntent = Intent.parseUri("http://www.google.com",0);
                    startActivity(impIntent);
                    */
                    /*
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,"I AM NEW");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                    */

                    // Dial
                    /*
                    Intent dial = new Intent();
                    dial.setAction("android.intent.action.DIAL");
                    dial.setData(Uri.parse("tel:"));
                    startActivity(dial);
                    */
                    // Call
                    /*
                    String uri = "tel:" + "9999999999".trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                    */
                /*
                } catch (Exception e) {
                    e.printStackTrace();
                }
                */
            } else
            if (resultCode == RESULT_CANCELED)
            {

            }
        }
    }

    private void showToast(String msg)
    {
        Toast.makeText(getBaseContext(),""+msg,Toast.LENGTH_LONG).show();
    }

    private void retroGenerate()
    {
        ApiInterface intfce = RetrofitSingleton.getInstance().getRetrofit().create(ApiInterface.class);
        Call<FeedProperties> call = intfce.getApiJson();
        call.enqueue(new Callback<FeedProperties>() {
            @Override
            public void onResponse(Call<FeedProperties> call, Response<FeedProperties> response) {

            }

            @Override
            public void onFailure(Call<FeedProperties> call, Throwable t) {

            }
        });
    }

    /**
     * Add Retrofit Call
     */
    private  void callRetroApi()
    {
        ApiInterface inter =  ApiClient.getInstance().getRetrofit().create(ApiInterface.class);

        Call<FeedProperties> call = inter.getApiJson();
        call.enqueue(new Callback<FeedProperties>() {
            @Override
            public void onResponse(Call<FeedProperties> call, Response<FeedProperties> response) {
                Log.e("Retrofit","Response : " + response.toString());
                FeedProperties properties = response.body();
                Log.e("Retrofit","Response Body: " + properties.getTimelineUrl());
                Log.e("Retrofit","Response Body: " + properties.getUserUrl());
            }
            @Override
            public void onFailure(Call<FeedProperties> call, Throwable t) {
                Log.e("Retrofit","Response Error: " + t.toString());
            }
        });
    }

    private void callFeedApi()
    {
        ApiInterface inter = ApiClient.getInstance().getRetrofit().create(ApiInterface.class);
        Call<Feeds> call = inter.getFeeds();
        call.enqueue(new Callback<Feeds>() {
            @Override
            public void onResponse(Call<Feeds> call, Response<Feeds> response) {
                if (response == null) return;
                Feeds feeds = response.body();
                Log.e("Retrofit","Response Body: " + feeds.getTimelineUrl());
                Log.e("Retrofit","Response Body: " + feeds.getTimelineUrl());
                Log.e("Retrofit","Response Body: " + feeds.getSecurityAdvisoriesUrl());
                Log.e("Retrofit","Response Body: " + feeds.getLinks());
                Log.e("Retrofit","Response Body: " + feeds.getLinks().getUser().getHref());
            }

            @Override
            public void onFailure(Call<Feeds> call, Throwable t) {
                Log.e("Retrofit","Response Error: " + t.toString());
            }
        });
    }

    private void callEventsApi()
    {
        ApiInterface api = ApiClient.getInstance().getRetrofit().create(ApiInterface.class);
        Call<List<Event>> call = api.getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response == null) return;
                List<Event> eventsList = response.body();
                Log.e("Event List Size"," " +eventsList.size());
                for (Event item : eventsList)
                {
                    Log.e("Event",item.getId());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("Retrofit","Response Error: " + t.toString());
            }
        });
    }

    private void getEvent()
    {
        ApiInterface inter = ApiClient.getInstance().getRetrofit().create(ApiInterface.class);
        inter.getToken(new HashMap<String, String>(), new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
              Gson gson = new Gson();
              Object item = gson.fromJson(response.body().toString(),Object.class);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static ArrayList<ExpandInfoList> createExpandInfo()
    {
        ArrayList<ExpandInfoList> list = new ArrayList<>();

        for(int i = 1; i<16 ;i++)
        {
            ExpandInfoList infoItem = new ExpandInfoList();
            // Group Data
            Group groupData = new Group();
            groupData.setTitle("Title " + i);
            infoItem.setGroup(groupData);
            // Child Data
            ArrayList<Child> child = new ArrayList<>();
            for(int j = 1; j<2 ; j++)
            {
                Child item = new Child();
                item.setChildName("Group " + i + " Child " + j);
                child.add(item);
            }
            infoItem.setChildrenList(child);
            // Add
            list.add(infoItem);
        }

        return list;
    }

    private ArrayList<UserInfo> createInfoList()
    {
        ArrayList<UserInfo> info = new ArrayList<>();

        UserInfo info1 = new UserInfo();
        info1.setPosition("1");
        info1.setName("ONe");
        info1.setAddress("Space");
        info.add(info1);

        UserInfo info2 = new UserInfo();
        info2.setPosition("2");
        info2.setName("TWo");
        info2.setAddress("Space");
        info.add(info2);

        UserInfo info3 = new UserInfo();
        info3.setPosition("3");
        info3.setName("THree");
        info3.setAddress("Space");
        info.add(info3);

        UserInfo info4 = new UserInfo();
        info4.setPosition("4");
        info4.setName("FOur");
        info4.setAddress("Space");
        info.add(info4);

        for (int i=5; i<15; i++)
        {
            UserInfo info14 = new UserInfo();
            info14.setPosition("" + i);
            info14.setName("Name"+i);
            info14.setAddress("Space "+i);
            info.add(info14);
        }

        return info;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getAction())
        {
            case KeyEvent.KEYCODE_BACK : break;
            case KeyEvent.KEYCODE_CLEAR : break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int Y = (int) event.getRawY();
        Log.e("Layout Move", " Bottom Margin : " + _yDelta + " Value : " + Y);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                /*
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _yDelta = Y - lParams.bottomMargin;
                */
                LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                _yDelta = Y - lParams.bottomMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                /*
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.bottomMargin = (Y - _yDelta);
                layoutParams.topMargin = -layoutParams.bottomMargin;
                view.setLayoutParams(layoutParams);
                view.animate().translationY(Y - _yDelta).setDuration(0);
                 */
                LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) view.getLayoutParams();
                layoutParams1.bottomMargin = (Y - _yDelta);
                layoutParams1.topMargin = -layoutParams1.bottomMargin;
                view.setLayoutParams(layoutParams1);
                view.animate().translationY(Y - _yDelta).setDuration(0);

                break;
        }
        //findViewById(R.id.root).invalidate();
        findViewById(R.id.child).invalidate();
        findViewById(R.id.root).invalidate();
        return true;
    }

    private void wakeAnEvent(int i)
    {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);
    }

    private void downLoadManager()
    {
        // Make sure the READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE permissions
        String url = "url you want to download";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Some descrition");
        request.setTitle("Some title");
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");
        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    class MyTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private void useHandler()
    {
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },200);
        //Message.obtain();
    }

    private void downloadData()
    {
        String urlStr = "http://www.google.com/images.jpg";
        try {
            URL url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.connect();
            //int lof = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream(),8192);
            OutputStream output = new FileOutputStream("path"); // give the store path with file name
            byte[] data = new byte[1024]; // read size, holder
            int count = 0;

            while ((count = input.read(data)) != -1)
            {
                //total +=count;
                output.write(data,0,count);
            }

            output.flush();
            output.close();
            input.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void workerManager()
    {
        // android jetpack package
        /*
           WorkManager is part of Android Jetpack. WorkManager helps us to execute our tasks immediately or an appropriate time.
           Supports all versions It gives us a clean interface hiding all the complexities and giving the guaranteed execution of the task
           Tasks can be chained as well, for example when one task is finished it can start another.
        */
        //This is the subclass of our WorkRequest
        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyJetPackWorker.class).build();
        //Enqueuing the work request
        WorkManager.getInstance().enqueue(workRequest);

    }

    private void checkPermission()
    {   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;
        if (resultPermission())
        {
            // Do Something
            showToast("checkPermission Permission Granted");
        } else
        {
            requestPermission();
        }
    }

    private boolean resultPermission()
    {
        int fineLocation = ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION);

        return fineLocation== PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 101)
        {
            boolean fineLoc = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if (fineLoc)
            {
                showToast("onRequestPermissionsResult Permission Granted");
            } else
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                        showMessageOKCancel("You need to allow to access the permissions",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            // CAMERA,   + bluethooth + " " +cameraAccepted + " "
                                            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 101);
                                        }
                                    }
                                });
                        return;
                    } else
                    {
                        showToast("Enable Location permission by navigating to settings screen of the app");
                    }
                }
            }
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkPermission();
                    }
                })
                .create()
                .show();
    }

}
