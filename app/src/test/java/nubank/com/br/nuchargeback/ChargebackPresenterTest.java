package nubank.com.br.nuchargeback;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import nubank.com.br.nuchargeback.ui.chargeback.ChargebackPresenterImpl;
import nubank.com.br.nuchargeback.ui.chargeback.ChargebackView;

@RunWith(MockitoJUnitRunner.class)
public class ChargebackPresenterTest {

    @Mock
    private ChargebackView view;

    @Mock
    private Context context;

    @InjectMocks
    private ChargebackPresenterImpl presenter;


    @Before
    public void setup() {
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
