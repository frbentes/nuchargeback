package nubank.com.br.nuchargeback;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import nubank.com.br.nuchargeback.ui.notice.NoticePresenterImpl;
import nubank.com.br.nuchargeback.ui.notice.NoticeView;

@RunWith(MockitoJUnitRunner.class)
public class NoticePresenterTest {

    @Mock
    private NoticeView view;

    @Mock
    private Context context;

    @InjectMocks
    private NoticePresenterImpl presenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Whitebox.setInternalState(this.presenter, "view", this.view);
        Whitebox.setInternalState(this.presenter, "context", this.context);
    }

    @Test
    public void setViewTest() {
        // given
        Whitebox.setInternalState(this.presenter, "view", null);

        // when
        this.presenter.setView(this.view);

        // then
        Assert.assertNotNull(Whitebox.getInternalState(this.presenter, "view"));
    }

}
