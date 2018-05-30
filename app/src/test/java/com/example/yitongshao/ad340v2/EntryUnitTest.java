package com.example.yitongshao.ad340v2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import com.example.yitongshao.ad340v2.MainActivity;

public class EntryUnitTest {
    private MainActivity mActivity = new MainActivity();

    @Test
    public void validInput_ReturnsTrue() {
        assertThat(mActivity.checkInput ("hello"), is(true));
    }

    @Test
    public void emptyInput_ReturnsFalse() {
        assertThat(mActivity.checkInput (""), is(false));
    }
}
