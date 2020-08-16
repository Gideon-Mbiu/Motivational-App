package com.example.motivatedmindset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.motivatedmindset.data.QuoteData;
import com.example.motivatedmindset.data.QuoteListAsyncResponse;
import com.example.motivatedmindset.data.QuoteViePagerAdapter;
import com.example.motivatedmindset.models.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private QuoteViePagerAdapter quoteViePagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteViePagerAdapter = new QuoteViePagerAdapter(getSupportFragmentManager(), getFragments());

        viewPager = findViewById(R.id.viewPager_id);
        viewPager.setAdapter(quoteViePagerAdapter);

    }

    private List<Fragment> getFragments(){
        final List<Fragment> fragmentList = new ArrayList<>();

        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++){
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentList.add(quoteFragment);
                }

                quoteViePagerAdapter.notifyDataSetChanged();

            }
        });


        return fragmentList;
    }
}
