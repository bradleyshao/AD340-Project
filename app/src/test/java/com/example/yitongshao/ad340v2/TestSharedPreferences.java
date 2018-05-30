package com.example.yitongshao.ad340v2;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.SharedPreferences;

@RunWith(MockitoJUnitRunner.class)
public class TestSharedPreferences {
    SharedPreferences mockSharedPreferences;

    SharedPreferences.Editor mockEditor ;


    private SharedPreferencesHelper mockSharedPreferencesHelper;

    private String text_entry = "XXtestXX";

    @Before
    public void initMocks() {

        mockSharedPreferencesHelper = createMockSharedPreference();

    }

    @Test
    public void sharedPreferences_SaveAndReadEntry() {

        boolean success = mockSharedPreferencesHelper.saveEntry(text_entry);

        assertThat("SharedPreferenceEntry.save... returns true",
                success, is(true));

        assertEquals(text_entry, mockSharedPreferencesHelper.getEntry());

    }

    private SharedPreferencesHelper createMockSharedPreference() {

        when(mockSharedPreferences.getString(eq("input"), anyString()))
                .thenReturn(text_entry);

        when(mockEditor.commit()).thenReturn(true);

        when(mockSharedPreferences.edit()).thenReturn(mockEditor);

        return new SharedPreferencesHelper(mockSharedPreferences);
    }
}
