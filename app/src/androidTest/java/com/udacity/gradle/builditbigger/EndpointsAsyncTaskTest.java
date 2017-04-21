package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;

import com.udacity.gradle.builditbigger.activities.MainActivity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class EndpointsAsyncTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final int COUNTDOWN_LATCH_WAIT_PERIOD = 30;

    public EndpointsAsyncTaskTest() {
        super(MainActivity.class);
    }

    public void testAsyncExecution() throws Throwable {
        final String[] strings = new String[1];
        final CountDownLatch latch = new CountDownLatch(1);

        final EndpointsAsyncTask task = new EndpointsAsyncTask(getActivity()) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                strings[0] = result;
                latch.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                task.execute(getActivity());
            }
        });

        latch.await(COUNTDOWN_LATCH_WAIT_PERIOD, TimeUnit.SECONDS);
        assertFalse(strings[0].equals(""));
    }
}