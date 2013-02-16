import com.ejt.vaadin.sizereporter.ComponentResizeEvent;
import com.ejt.vaadin.sizereporter.ComponentResizeListener;
import com.ejt.vaadin.sizereporter.SizeReporter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

import java.util.Date;

public class TestUi extends UI{
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        final Panel panel = new Panel();
        panel.setContent(new Label("Why do you want to know my size?"));
        panel.setWidth("100%");
        layout.addComponent(panel);

        final Label sizeLabel = new Label();
        layout.addComponent(sizeLabel);

        Button button = new Button("Add single use size reporter");
        layout.addComponent(button);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new SizeReporter(panel).addResizeListenerOnce(new ComponentResizeListener() {
                    @Override
                    public void sizeChanged(ComponentResizeEvent event) {
                        layout.addComponent(new Label("Panel size at " + new Date() + ": " + event.getWidth() + " x " + event.getHeight()));
                    }
                });
            }
        });

        SizeReporter sizeReporter = new SizeReporter(panel);
        sizeReporter.addResizeListener(new ComponentResizeListener() {
            @Override
            public void sizeChanged(ComponentResizeEvent event) {
                sizeLabel.setValue("Panel size: " + event.getWidth() + " x " + event.getHeight());
            }
        });

        setContent(layout);
    }
}
