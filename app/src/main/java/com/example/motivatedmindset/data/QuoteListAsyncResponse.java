package com.example.motivatedmindset.data;

import com.example.motivatedmindset.models.Quote;

import java.util.ArrayList;

public interface QuoteListAsyncResponse {
    void processFinished(ArrayList<Quote> quotes);
}
