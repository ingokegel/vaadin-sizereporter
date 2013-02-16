import com.ejt.vaadin.sizereporter.ComponentResizeEvent;
import com.ejt.vaadin.sizereporter.ComponentResizeListener;
import com.ejt.vaadin.sizereporter.SizeReporter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class TestUi extends UI{
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        Panel panel = new Panel();
        panel.setContent(new Label("Why do you want to know my size?"));
        panel.setWidth("100%");
        layout.addComponent(panel);

        final Label sizeLabel = new Label();
        layout.addComponent(sizeLabel);

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
